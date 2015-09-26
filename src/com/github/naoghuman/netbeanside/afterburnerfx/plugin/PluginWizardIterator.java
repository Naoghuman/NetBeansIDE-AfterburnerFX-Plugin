/*
 * Copyright (C) 2015 Naoghuman
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.netbeanside.afterburnerfx.plugin;

import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.PluginSupport;
import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.SourceGroupSupport;
import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.IPluginSupport;
import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.event.ChangeListener;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.Sources;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObject;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

// TODO define position attribute
//@TemplateRegistration(
//        folder = "javafx",
//        displayName = "#AfterburnerWizardIterator_displayName",
//        iconBase = "de/pro/afterburner/netbeanside/plugin/Icons-Folder-Lavender-icon.png",
//        description = "afterburner.html"
//)
//@Messages("AfterburnerWizardIterator_displayName=Afterburner")
public final class PluginWizardIterator implements WizardDescriptor.InstantiatingIterator<WizardDescriptor>, IPluginSupport {

    final Map<String, String> mapTemplatesAndExtentions = new HashMap<String, String>();
        
    private int index;

    private SourceGroupSupport supportBaseName;
    private WizardDescriptor wizard;
    
    private List<WizardDescriptor.Panel<WizardDescriptor>> panels;
    
    public PluginWizardIterator() {
        this.init();
    }
    
    private void init() {
        mapTemplatesAndExtentions.put(TEMPLATE__PREFIX__CSS, TEMPLATE_PARAMETER__CSS);
        mapTemplatesAndExtentions.put(TEMPLATE__PREFIX__FXML, TEMPLATE_PARAMETER__FXML);
        mapTemplatesAndExtentions.put(TEMPLATE__PREFIX__PROPERTIES, TEMPLATE_PARAMETER__PROPERTIES);
        mapTemplatesAndExtentions.put(TEMPLATE__PREFIX_PRESENTER__JAVA, TEMPLATE_PARAMETER__PRESENTER);
        mapTemplatesAndExtentions.put(TEMPLATE__PREFIX_VIEW__JAVA, TEMPLATE_PARAMETER__VIEW);
    }

    private List<WizardDescriptor.Panel<WizardDescriptor>> getPanels() {
        if (panels != null) {
            return panels;
        }
        
        final Project project = Templates.getProject(wizard);
        final boolean isMaven = PluginSupport.isMavenProject(project);
        final Sources sources = ProjectUtils.getSources(project);
        final SourceGroup[] sourceGroupsResources = sources.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_RESOURCES);
        final SourceGroup[] sourceGroupsJava = sources.getSourceGroups(JavaProjectConstants.SOURCES_TYPE_JAVA);
        if(isMaven) {
            supportBaseName = new SourceGroupSupport(JavaProjectConstants.SOURCES_TYPE_RESOURCES);
            if(sourceGroupsResources != null && sourceGroupsResources.length > 0) {
                supportBaseName.addSourceGroups(sourceGroupsResources);
            } else {
                supportBaseName.addSourceGroupProxy(project, NbBundle.getMessage(PluginWizardIterator.class,"LAB_ProjectResources"), // NOI18N
                        new String[]{ DEFAULT_MAVEN_FXML_PACKAGE, DEFAULT_MAVEN_IMAGES_PACKAGE, DEFAULT_MAVEN_CSS_PACKAGE });
            }
        } else {
            supportBaseName = new SourceGroupSupport(JavaProjectConstants.SOURCES_TYPE_JAVA);
            supportBaseName.addSourceGroups(sourceGroupsJava);
        }
        
        panels = new ArrayList<WizardDescriptor.Panel<WizardDescriptor>>();
        panels.add(new PluginWizardPanelName(project, supportBaseName, isMaven));
        panels.add(new PluginWizardPanelOptional());
        panels.add(new PluginWizardPanelSummary());
        
        final String[] steps = createSteps();
        for (int i = 0; i < panels.size(); i++) {
            final Component c = panels.get(i).getComponent();
            if (steps[i] == null) {
                steps[i] = c.getName();
            }
            
            if (c instanceof JComponent) { // assume Swing components
                final JComponent jc = (JComponent) c;
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_SELECTED_INDEX, i);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DATA, steps);
                jc.putClientProperty(WizardDescriptor.PROP_AUTO_WIZARD_STYLE, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_DISPLAYED, true);
                jc.putClientProperty(WizardDescriptor.PROP_CONTENT_NUMBERED, true);
            }
        }
            
        return panels;
    }

    @Override
    public Set<?> instantiate() throws IOException {
        final FileObject firstTemplate = Templates.getTemplate(wizard);
        final FileObject[] fileObjects = firstTemplate.getParent().getChildren();
        final Map<String, DataObject> map1 = new HashMap<String, DataObject>();
        
        final boolean shouldCreateCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(
                PROP_CREATE_CSS_FILE, Boolean.TRUE);
        final boolean shouldCreateProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(
                PROP_CREATE_PROPERTIES_FILE, Boolean.TRUE);
        
        for (FileObject fileObject : fileObjects) {
            if (mapTemplatesAndExtentions.containsKey(fileObject.getNameExt())) {
                if (!shouldCreateCSS && fileObject.getNameExt().equals(TEMPLATE__PREFIX__CSS)) {
                    continue;
                }
                
                if (!shouldCreateProperties && fileObject.getNameExt().equals(TEMPLATE__PREFIX__PROPERTIES)) {
                    continue;
                }
                
                map1.put(mapTemplatesAndExtentions.get(fileObject.getNameExt()), DataObject.find(fileObject));
            }
        }
        
        final List<DataObject> dataObjects = new ArrayList<DataObject>();
        final DataFolder dataFolder = DataFolder.findFolder(Templates.getTargetFolder(wizard));
        final Map<String, String> parameters = this.createParameters(shouldCreateCSS, shouldCreateProperties);
        for (String key : map1.keySet()) {
            final DataObject dataObject = map1.get(key);
            dataObjects.add(dataObject.createFromTemplate(dataFolder, parameters.get(key), parameters));
        }

        final List<FileObject> fileObjects2 = new ArrayList<FileObject>();
        for (DataObject dataObject : dataObjects) {
            fileObjects2.add(dataObject.getPrimaryFile());
        }
        
        return Collections.singleton(fileObjects2);
    }
    
    private Map<String, String> createParameters(boolean shouldCreateCSS, boolean shouldCreateProperties) {
        final String targetName = NbPreferences.forModule(PluginWizardIterator.class)
                .get(PROP_BASENAME_CHOOSEN, PROP_BASENAME_CHOOSEN_DEFAULT_VALUE);
        
        final Map<String, String> parameters = new HashMap<String, String>();
        parameters.put(TEMPLATE_PARAMETER__FXML, targetName);
        parameters.put(TEMPLATE_PARAMETER__CONTROLLER, targetName + "Presenter"); // NOI18N
        parameters.put(TEMPLATE_PARAMETER__PRESENTER, targetName + "Presenter"); // NOI18N
        parameters.put(TEMPLATE_PARAMETER__VIEW, targetName + "View"); // NOI18N
        
        if (shouldCreateCSS) {
            parameters.put(TEMPLATE_PARAMETER__CSS, targetName); // NOI18N
        }
        
        if (shouldCreateProperties) {
            parameters.put(TEMPLATE_PARAMETER__PROPERTIES, targetName); // NOI18N
        }
        parameters.put(TEMPLATE_PARAMETER__RESOURCES, shouldCreateProperties ? "true" : "false"); // NOI18N
        
        return parameters;
    }
    
    @Override
    public void initialize(WizardDescriptor wizard) {
        this.wizard = wizard;
    }

    @Override
    public void uninitialize(WizardDescriptor wizard) {
        panels = null;
    }

    @Override
    public WizardDescriptor.Panel<WizardDescriptor> current() {
        return getPanels().get(index);
    }

    @Override
    public String name() {
        return index + 1 + ". from " + getPanels().size();
    }

    @Override
    public boolean hasNext() {
        return index < getPanels().size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public void nextPanel() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        index++;
    }

    @Override
    public void previousPanel() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        index--;
    }

    // If nothing unusual changes in the middle of the wizard, simply:
    @Override
    public void addChangeListener(ChangeListener l) {
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
    }
    
    // If something changes dynamically (besides moving between panels), e.g.
    // the number of panels changes in response to user input, then use
    // ChangeSupport to implement add/removeChangeListener and call fireChange
    // when needed
    // You could safely ignore this method. Is is here to keep steps which were
    // there before this wizard was instantiated. It should be better handled
    // by NetBeans Wizard API itself rather than needed to be implemented by a
    // client code.
    private String[] createSteps() {
        final String[] beforeSteps = (String[]) wizard.getProperty("WizardPanel_contentData"); // NOI18N
        assert beforeSteps != null : "This wizard may only be used embedded in the template wizard"; // NOI18N
        
        final String[] res = new String[(beforeSteps.length - 1) + panels.size()];
        for (int i = 0; i < res.length; i++) {
            if (i < (beforeSteps.length - 1)) {
                res[i] = beforeSteps[i];
            } else {
                res[i] = panels.get(i - beforeSteps.length + 1).getComponent().getName();
            }
        }
        
        return res;
    }

}
