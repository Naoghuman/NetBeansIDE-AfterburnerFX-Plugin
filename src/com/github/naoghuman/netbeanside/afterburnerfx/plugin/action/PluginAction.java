/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.netbeanside.afterburnerfx.plugin.action;

import com.github.naoghuman.netbeanside.afterburnerfx.plugin.PluginWizardIterator;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import org.openide.DialogDisplayer;
import org.openide.WizardDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.loaders.DataFolder;
import org.openide.util.NbBundle.Messages;

//@ActionID(
//        category = "File",
//        id = "com.github.naoghuman.netbeanside.afterburnerfx.plugin.action.AfterburnerFXAction"
//)
//@ActionRegistration(
//        iconBase = "com/github/naoghuman/netbeanside/afterburnerfx/plugin/resources/icon-at.png",
//        displayName = "#CTL_AfterburnerFXAction"
//)
//@ActionReferences({
//    @ActionReference(path = "Menu/File", position = 300),
//    @ActionReference(path = "Toolbars/AfterburnerFX", position = 0)
//})
//@Messages("CTL_AfterburnerFXAction=New AfterburnerFX Files...")
public final class PluginAction implements ActionListener {

    private final DataFolder context;

    public PluginAction(DataFolder context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        final PluginWizardIterator pluginWizardIterator = new PluginWizardIterator();
        
//        WizardDescriptor wiz = new WizardDescriptor(new WizardDescriptor.ArrayIterator<WizardDescriptor>(pluginWizardIterator.getPanels())); 
//        // {0} will be replaced by WizardDesriptor.Panel.getComponent().getName() 
//        wiz.setTitleFormat(new MessageFormat("{0}"));
//        wiz.setTitle("...dialog title..."); 
//        if (DialogDisplayer.getDefault().notify(wiz) == WizardDescriptor.FINISH_OPTION) { 
//            // do something 
//        }
        

       final  WizardDescriptor wizardDescriptor = new WizardDescriptor(pluginWizardIterator);
        pluginWizardIterator.initialize(wizardDescriptor);

        // {0} will be replaced by WizardDescriptor.Panel.getComponent().getName()
        // {1} will be replaced by WizardDescriptor.Iterator.name()
        wizardDescriptor.setTitleFormat(new MessageFormat("{0} ({1})"));
        wizardDescriptor.setTitle("hello?");

        final Dialog dialog = DialogDisplayer.getDefault().createDialog(wizardDescriptor);
        dialog.setVisible(true);
        dialog.toFront();

        final boolean cancelled = wizardDescriptor.getValue() != WizardDescriptor.FINISH_OPTION;
        if (!cancelled) {

            // do something

        }
    }
}
