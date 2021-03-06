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

import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.IPluginSupport;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class PluginWizardPanelSummary implements WizardDescriptor.Panel<WizardDescriptor>, IPluginSupport {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private PluginVisualPanelSummary component;
    
    @Override
    public PluginVisualPanelSummary getComponent() {
        if (component == null) {
            component = new PluginVisualPanelSummary();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        
    }

    @Override
    public void readSettings(WizardDescriptor wizardDescriptor) {
        final String baseName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP__FILENAME_CHOOSEN, "Afterburner"); // NOI18N
        final String packageName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP__CHOOSEN_PACKAGE, "Afterburner"); // NOI18N
        
        final boolean shouldFXMLtoLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__FXML_TO_LOWERCASE, Boolean.TRUE);
        
        final boolean shouldCreateCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE_SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldCSStoLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_TO_LOWERCASE, Boolean.TRUE);
        final boolean shouldInjectCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE_SHOULD_INJECT, Boolean.TRUE);
        
        final boolean shouldCreateProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE_SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldPropertiesToLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_TO_LOWERCASE, Boolean.TRUE);
        final boolean shouldInjectProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE_SHOULD_INJECT, Boolean.TRUE);
        
        final boolean shouldCreateConfigurationProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CONFIGURATION_FILE_SHOULD_CREATE, Boolean.FALSE);
        final boolean shouldConfigurationPropertiesToLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CONFIGURATION_TO_LOWERCASE, Boolean.TRUE);
        
        this.getComponent().initValues(baseName, packageName, shouldFXMLtoLowerCase,
                shouldCreateCSS, shouldCSStoLowerCase, shouldInjectCSS, 
                shouldCreateProperties, shouldPropertiesToLowerCase, shouldInjectProperties,
                shouldCreateConfigurationProperties, shouldConfigurationPropertiesToLowerCase);
    }

    @Override
    public void storeSettings(WizardDescriptor wizardDescriptor) {
        
    }

}
