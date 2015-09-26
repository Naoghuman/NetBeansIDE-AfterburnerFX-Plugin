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
import static com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.IPluginSupport.PROP_CHOOSEN_PACKAGE;
import static com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.IPluginSupport.SIGN_CHAR_DOT;
import java.io.File;
import javax.swing.event.ChangeListener;
import org.openide.WizardDescriptor;
import org.openide.util.HelpCtx;
import org.openide.util.NbPreferences;

public class PluginWizardPanelOptional implements WizardDescriptor.Panel<WizardDescriptor>, IPluginSupport {

    /**
     * The visual component that displays this panel. If you need to access the
     * component from this class, just use getComponent().
     */
    private PluginVisualPanelOptional component;

    // Get the visual component for the panel. In this template, the component
    // is kept separate. This can be more efficient: if the wizard is created
    // but never displayed, or not all panels are displayed, it is better to
    // create only those which really need to be visible.
    @Override
    public PluginVisualPanelOptional getComponent() {
        if (component == null) {
            component = new PluginVisualPanelOptional();
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
        // If it is always OK to press Next or Finish, then:
        return true;
        // If it depends on some condition (form filled out...) and
        // this condition changes (last form field filled in...) then
        // use ChangeSupport to implement add/removeChangeListener below.
        // WizardDescriptor.ERROR/WARNING/INFORMATION_MESSAGE will also be useful.
    }

    @Override
    public void addChangeListener(ChangeListener l) {
        
    }

    @Override
    public void removeChangeListener(ChangeListener l) {
        
    }

    @Override
    public void readSettings(WizardDescriptor wiz) {
        final String baseName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP_BASENAME_CHOOSEN, PROP_BASENAME_CHOOSEN_DEFAULT_VALUE);
        
        String packageName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP_CHOOSEN_PACKAGE, PROP_BASENAME_CHOOSEN_DEFAULT_VALUE);
        packageName = packageName.replace(SIGN_CHAR_DOT, File.separatorChar);
        if (!packageName.endsWith(File.separator)) {
            packageName += File.separator;
        }
        
        final boolean shouldCreateCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE__SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldInjectCSS = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__CSS_FILE__SHOULD_INJECT, Boolean.TRUE);
        
        final boolean shouldCreateProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE__SHOULD_CREATE, Boolean.TRUE);
        final boolean shouldInjectProperties = NbPreferences.forModule(PluginWizardIterator.class).getBoolean(PROP__PROPERTIES_FILE__SHOULD_INJECT, Boolean.TRUE);
        
        component.initValues(baseName, packageName, shouldCreateCSS, shouldInjectCSS,
                shouldCreateProperties, shouldInjectProperties);
    }

    @Override
    public void storeSettings(WizardDescriptor wiz) {
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CSS_FILE__SHOULD_CREATE, getComponent().shouldCreateCSS());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__CSS_FILE__SHOULD_INJECT, getComponent().shouldInjectCSS());
        
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__PROPERTIES_FILE__SHOULD_CREATE, getComponent().shouldCreateProperties());
        NbPreferences.forModule(PluginWizardIterator.class).putBoolean(PROP__PROPERTIES_FILE__SHOULD_INJECT, getComponent().shouldInjectProperties());
    }

}
