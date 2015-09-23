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
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.ui.templates.support.Templates;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.util.ChangeSupport;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class PluginWizardPanelName implements WizardDescriptor.Panel<WizardDescriptor>, IPluginSupport {

    private final ChangeSupport changeSupport = new ChangeSupport(this);
    
    private final boolean isMaven;
    
    private final Project project;
    private final SourceGroupSupport sourceGroupSupport;
    
    private WizardDescriptor settings;
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private PluginVisualPanelName component;

    /**
     * Creates new form AfterburnerVisualPanel1
     */
    public PluginWizardPanelName(Project project, SourceGroupSupport sourceGroupSupport, boolean isMaven) {
        this.project = project;
        this.sourceGroupSupport = sourceGroupSupport;
        this.isMaven = isMaven;
    }
    
    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public PluginVisualPanelName getComponent() {
        if (component == null) {
            component = new PluginVisualPanelName(project, sourceGroupSupport, changeSupport, isMaven);
        }
        
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        // Show no Help button for this panel:
        return HelpCtx.DEFAULT_HELP;
        // If you have context help:
        // return new HelpCtx("help.key.here");
    }

    @Override
    public boolean isValid() {
        if (!PluginSupport.isValidBaseName(component.getBaseName())) {
            PluginSupport.setInfoMessage(MSG_INFO_BASE_NAME_ISNT_VALID, settings);
            return false;
        }
        
        if (!PluginSupport.isBaseNameContainsWrongFileNameChars(component.getBaseName())) {
            PluginSupport.setErrorMessage(MSG_ERROR_BASE_NAME_CONTAINS_WRONG_FILENAME_CHARS, settings);
            return false;
        }
          
        if (!PluginSupport.isValidPackageName(component.getPackageName())) {
            PluginSupport.setErrorMessage(MSG_ERROR_PACKAGE_NAME_ISNT_VALID, settings);
            return false;
        }
        
        if (!PluginSupport.isValidPackage(component.getLocationFolder(), component.getPackageName())) {
            PluginSupport.setErrorMessage(MSG_ERROR_PACKAGE_ISNT_FOLDER, settings);
            return false;
        }
        
        if (!PluginSupport.isValidBaseNameAndPackage(component.getBaseName(), component.getLocationFolder(), component.getPackageName())) {
            PluginSupport.setWarningMessage(MSG_WARNING_BASE_AND_PACKAGE_NAME_ARENT_EQUALS, settings);
            return false;
        }
        
        final String errorMessage = PluginSupport.canUseFileName(component.getLocationFolder(), component.getPackageFileName(), 
                component.getBaseName(), EXTENSION_FXML);
        if (errorMessage != null) {
            settings.getNotificationLineSupport().setErrorMessage(errorMessage);
            return false;
        }
        
        PluginSupport.clearMessages(settings);
            
        return true;
    }

    @Override
    public void addChangeListener(ChangeListener listener) {
        changeSupport.addChangeListener(listener);
    }

    @Override
    public void removeChangeListener(ChangeListener listener) {
        changeSupport.removeChangeListener(listener);
    }

    @Override
    public void readSettings(WizardDescriptor settings) {
        this.settings = settings;
        
        final FileObject preselectedFolder = Templates.getTargetFolder(settings);
        component.initValues(Templates.getTemplate(settings), preselectedFolder);
// XXX todo own titel for wizard
//        // XXX hack, TemplateWizard in final setTemplateImpl() forces new wizard's title
//        // this name is used in NewFileWizard to modify the title
//        Object substitute = component.getClientProperty("NewFileWizard_Title"); // NOI18N
//        if (substitute != null) {
//            settings.putProperty("NewFileWizard_Title", substitute); // NOI18N
//        }
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(PluginWizardIterator.class).put(
                PROP_BASENAME_CHOOSEN, getComponent().getBaseName());
        NbPreferences.forModule(PluginWizardIterator.class).put(
                PROP_CHOOSEN_PACKAGE, getComponent().getPackageName());
    }

}
