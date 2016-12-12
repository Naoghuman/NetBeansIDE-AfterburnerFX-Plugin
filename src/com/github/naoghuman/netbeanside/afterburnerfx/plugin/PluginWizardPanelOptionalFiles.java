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
import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.PluginSupport;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class PluginWizardPanelOptionalFiles implements WizardDescriptor.Panel<WizardDescriptor>, IPluginSupport {

    private String packageName = null;
    
    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private PluginVisualPanelOptionalFiles component;
    private WizardDescriptor wizardDescriptor;
    
    PluginWizardPanelOptionalFiles() {
        
    }
    
    @Override
    public PluginVisualPanelOptionalFiles getComponent() {
        if (component == null) {
            component = new PluginVisualPanelOptionalFiles();
        }
        return component;
    }

    @Override
    public HelpCtx getHelp() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public boolean isValid() {
        final boolean isLastPackageNameConfiguration = PluginSupport.isLastPackageNameConfiguration(packageName);
        if (isLastPackageNameConfiguration) {
            PluginSupport.setWarningMessage(MSG_WARNING__OPTION_GENERATION_CONFIGURATION_PROPERTIES_DEACTIVATED, wizardDescriptor);
        }
        
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
        this.wizardDescriptor = wizardDescriptor;
        
        final String baseName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP__FILENAME_CHOOSEN, PROP__FILENAME_CHOOSEN_DEFAULT_VALUE);
        packageName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP__CHOOSEN_PACKAGE, PROP__FILENAME_CHOOSEN_DEFAULT_VALUE);
        
        final boolean shouldFXMLtoLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__FXML_TO_LOWERCASE, Boolean.TRUE);
        
        final boolean shouldCreateCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE_SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldInjectCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE_SHOULD_INJECT, Boolean.TRUE);
        final boolean shouldCSStoLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_TO_LOWERCASE, Boolean.TRUE);
        
        final boolean shouldCreateProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE_SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldInjectProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE_SHOULD_INJECT, Boolean.TRUE);
        final boolean shouldPropertiesToLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_TO_LOWERCASE, Boolean.TRUE);
        
        final boolean shouldCreateConfigurationProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CONFIGURATION_FILE_SHOULD_CREATE, Boolean.FALSE);
        final boolean shouldConfigurationPropertiesToLowerCase = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CONFIGURATION_TO_LOWERCASE, Boolean.TRUE);
        
        this.getComponent().initValues(baseName, packageName, shouldFXMLtoLowerCase,
                shouldCreateCSS, shouldInjectCSS, shouldCSStoLowerCase,
                shouldCreateProperties, shouldInjectProperties, shouldPropertiesToLowerCase,
                shouldCreateConfigurationProperties, shouldConfigurationPropertiesToLowerCase);
    }

    @Override
    public void storeSettings(WizardDescriptor wizardDescriptor) {
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CSS_FILE_SHOULD_CREATE, getComponent().shouldCreateCSS());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CSS_FILE_SHOULD_INJECT, getComponent().shouldInjectCSS());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CSS_TO_LOWERCASE, getComponent().shouldCSStoLowerCase());
        
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__PROPERTIES_FILE_SHOULD_CREATE, getComponent().shouldCreateProperties());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__PROPERTIES_FILE_SHOULD_INJECT, getComponent().shouldInjectProperties());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__PROPERTIES_TO_LOWERCASE, getComponent().shouldPropertiesToLowerCase());
        
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CONFIGURATION_FILE_SHOULD_CREATE, getComponent().shouldCreateConfigurationProperties());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CONFIGURATION_TO_LOWERCASE, getComponent().shouldConfigurationPropertiesToLowerCase());
    }

}
