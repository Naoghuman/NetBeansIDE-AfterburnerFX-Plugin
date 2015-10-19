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
import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.SourceGroupSupport.SourceGroupProxy;
import com.github.naoghuman.netbeanside.afterburnerfx.plugin.support.IPluginSupport;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectUtils;
import org.netbeans.spi.java.project.support.ui.PackageView;
import org.openide.filesystems.FileObject;
import org.openide.util.ChangeSupport;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;

public final class PluginVisualPanelPrimaryFiles extends JPanel implements ActionListener, 
        DocumentListener, IPluginSupport, PropertyChangeListener
{
    @SuppressWarnings("rawtypes")
    private static final ComboBoxModel WAIT_MODEL = SourceGroupSupport.getWaitModel();
    
    private final ChangeSupport changeSupport;
    private final Project project;
    private final SourceGroupSupport sourceGroupSupport;
    
    private boolean ignoreRootCombo;
    
    private RequestProcessor.Task updatePackagesTask;

    public PluginVisualPanelPrimaryFiles(
            Project project, SourceGroupSupport sourceGroupSupport,
            ChangeSupport changeSupport
    ) {
        this.project = project;
        this.sourceGroupSupport = sourceGroupSupport;
        this.changeSupport = changeSupport;
        
        initComponents();
        initComponents2();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents2() {
        tfFileName.getDocument().addDocumentListener(this);
        
        cbLocation.setRenderer(new SourceGroupSupport.GroupListCellRenderer());
        cbLocation.addActionListener(this);
    
        cbPackage.getEditor().addActionListener(this);
        final Component packageEditor = cbPackage.getEditor().getEditorComponent();
        if (packageEditor instanceof JTextField) {
            ((JTextField) packageEditor).getDocument().addDocumentListener(this);
        }
        cbPackage.setRenderer(PackageView.listRenderer());
        
        taInfoPrimaryFiles.setBackground(tfProject.getBackground());
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void initValues(FileObject template, FileObject preselectedFolder) {
        if (template == null) {
                throw new IllegalArgumentException(
                        NbBundle.getMessage(PluginWizardIterator.class,
                            "MSG_ConfigureFXMLPanel_Template_Error")); // NOI18N
        }
        
        tfProject.setText(ProjectUtils.getInformation(project).getDisplayName());
        
        cbLocation.setModel(new DefaultComboBoxModel(sourceGroupSupport.getSourceGroups().toArray()));
        final SourceGroupProxy preselectedGroup = SourceGroupSupport.getContainingSourceGroup(
                sourceGroupSupport, preselectedFolder);
        ignoreRootCombo = true;
        cbLocation.setSelectedItem(preselectedGroup);
        ignoreRootCombo = false;
        
        final FileObject targetFolder = preselectedFolder;
        final Object preselectedPackage = PluginSupport.getPreselectedPackage(preselectedGroup, preselectedFolder);
        if (preselectedPackage != null) {
            cbPackage.getEditor().setItem(preselectedPackage);
        }
        
        if (tfFileName.getText().trim().length() == 0) {
            final String baseName = NbPreferences.forModule(PluginWizardIterator.class).get(PROP__FILENAME, PluginSupport.extractClassNameFromPackage(this.getPackageName()));
            String activeName = baseName;
            if (targetFolder != null) {
                int index = 0;
                while (true) {
                    FileObject fo = targetFolder.getFileObject(activeName, TEMPLATE_PARAMETER__FXML);
                    if (fo == null) {
                        break;
                    }
                    
                    activeName = baseName + ++index;
                }
            }
            
            tfFileName.setText(activeName);
            tfFileName.selectAll();
        }
        
        updatePackages();
        updateTextCreateFollowingFiles();
    }

    public String getBaseName() {
        final String text = tfFileName.getText().trim();
        return (text.length()) == 0 ? null : text;
    }
    
    public FileObject getLocationFolder() {
        final Object selectedItem  = cbLocation.getSelectedItem();
        return (selectedItem instanceof SourceGroupProxy) ? ((SourceGroupProxy)selectedItem).getRootFolder() : null;
    }

    @Override
    public String getName() {
        return "Primary Files"; // NOI18N
    }

    public String getPackageFileName() {
        final String packageName = cbPackage.getEditor().getItem().toString();
        return packageName.replace(SIGN_CHAR_DOT, File.separatorChar);
    }
    
    /**
     * Name of selected package, or "" for default package.
     */
    public String getPackageName() {
        return cbPackage.getEditor().getItem().toString();
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void updatePackages() {
        final Object item = cbLocation.getSelectedItem();
        if (!(item instanceof SourceGroupProxy)) {
            return;
        }
        WAIT_MODEL.setSelectedItem(cbPackage.getEditor().getItem());
        cbPackage.setModel(WAIT_MODEL);

        if (updatePackagesTask != null) {
            updatePackagesTask.cancel();
        }

        updatePackagesTask = new RequestProcessor("ComboBoxUpdatePackages").post(() -> { // NOI18N
            final ComboBoxModel model = ((SourceGroupProxy) item).getPackagesComboBoxModel();
            SwingUtilities.invokeLater(() -> {
                model.setSelectedItem(cbPackage.getEditor().getItem());
                cbPackage.setModel(model);
            });
        });
    }
    
    private void updateTextCreateFollowingFiles() {
        this.updateTextCreateFollowingFiles(Boolean.TRUE);
    }
    
    private void updateTextCreateFollowingFiles(boolean shouldInfoCreateFollwingFiles) {
        taInfoPrimaryFiles.setText(null);
        
        if (!shouldInfoCreateFollwingFiles) {
            return;
        }
            
        final Object selectedItem = cbLocation.getSelectedItem();
        if (!(selectedItem instanceof SourceGroupProxy)) {
            return;
        }
        
        final String fileName = this.getBaseName();
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        
        String packageName = this.getPackageName().replace(SIGN_CHAR_DOT, File.separatorChar);
        if (!packageName.endsWith(File.separator)) {
            packageName += File.separator;
        }
        taInfoPrimaryFiles.append("- " + packageName + fileName + ".fxml\n"); // NOI18N
        taInfoPrimaryFiles.append("- " + packageName + fileName + "Presenter.java\n"); // NOI18N
        taInfoPrimaryFiles.append("- " + packageName + fileName + "View.java"); // NOI18N
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("rawtypes")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lFileName = new javax.swing.JLabel();
        tfFileName = new javax.swing.JTextField();
        tfProject = new javax.swing.JTextField();
        lProject = new javax.swing.JLabel();
        cbLocation = new javax.swing.JComboBox();
        lLocation = new javax.swing.JLabel();
        cbPackage = new javax.swing.JComboBox();
        lPackage = new javax.swing.JLabel();
        lInfoPrimaryFiles = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        spInfoPrimaryFiles = new javax.swing.JScrollPane();
        taInfoPrimaryFiles = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(lFileName, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lFileName.text")); // NOI18N
        lFileName.setPreferredSize(new java.awt.Dimension(100, 20));

        tfFileName.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfFileName.text")); // NOI18N

        tfProject.setEditable(false);
        tfProject.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfProject.text")); // NOI18N
        tfProject.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lProject, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lProject.text")); // NOI18N
        lProject.setPreferredSize(new java.awt.Dimension(100, 20));

        cbLocation.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lLocation, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lLocation.text")); // NOI18N
        lLocation.setPreferredSize(new java.awt.Dimension(100, 20));

        cbPackage.setEditable(true);

        org.openide.awt.Mnemonics.setLocalizedText(lPackage, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lPackage.text")); // NOI18N
        lPackage.setPreferredSize(new java.awt.Dimension(100, 20));

        org.openide.awt.Mnemonics.setLocalizedText(lInfoPrimaryFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lInfoPrimaryFiles.text")); // NOI18N
        lInfoPrimaryFiles.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        spInfoPrimaryFiles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spInfoPrimaryFiles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoPrimaryFiles.setEditable(false);
        taInfoPrimaryFiles.setColumns(20);
        taInfoPrimaryFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoPrimaryFiles.setRows(4);
        taInfoPrimaryFiles.setEnabled(false);
        taInfoPrimaryFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInfoPrimaryFiles.setViewportView(taInfoPrimaryFiles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lInfoPrimaryFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfProject)
                            .addComponent(tfFileName)
                            .addComponent(cbLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPackage, 0, 276, Short.MAX_VALUE)))
                    .addComponent(separator)
                    .addComponent(spInfoPrimaryFiles))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lInfoPrimaryFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spInfoPrimaryFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    @SuppressWarnings("rawtypes")
    private javax.swing.JComboBox cbLocation;
    @SuppressWarnings("rawtypes")
    private javax.swing.JComboBox cbPackage;
    private javax.swing.JLabel lFileName;
    private javax.swing.JLabel lInfoPrimaryFiles;
    private javax.swing.JLabel lLocation;
    private javax.swing.JLabel lPackage;
    private javax.swing.JLabel lProject;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoPrimaryFiles;
    private javax.swing.JTextArea taInfoPrimaryFiles;
    private javax.swing.JTextField tfFileName;
    private javax.swing.JTextField tfProject;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        if (cbLocation == e.getSource()) {
            if (!ignoreRootCombo) {
                updatePackages();
            }
            
            updateTextCreateFollowingFiles();
            changeSupport.fireChange();
        } else if (cbPackage == e.getSource()) {
            updateTextCreateFollowingFiles();
            changeSupport.fireChange();
        } else if (cbPackage.getEditor() == e.getSource()) {
            updateTextCreateFollowingFiles();
            changeSupport.fireChange();
        }
    }
    
    @Override
    public void changedUpdate(DocumentEvent e) {
        updateTextCreateFollowingFiles();
        changeSupport.fireChange();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedUpdate(e);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final String propertyName = evt.getPropertyName();
        if (propertyName.equals(PROP__SHOW_INFORMATION_CREATE_FOLLOWING_FILES)) {
            final boolean shouldInfoCreateFollwingFiles = (Boolean) evt.getNewValue();
            this.updateTextCreateFollowingFiles(shouldInfoCreateFollwingFiles);
        }
    }
    
}
