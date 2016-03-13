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
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
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

public final class PluginVisualPanelPrimaryFiles extends JPanel implements// ActionListener, 
        DocumentListener, IPluginSupport, PropertyChangeListener
{
//    @SuppressWarnings("rawtypes")
//    private static final ComboBoxModel WAIT_MODEL = SourceGroupSupport.getWaitModel();
    
    private final ChangeSupport changeSupport;
    private final Project project;
    private final SourceGroupSupport sourceGroupSupport;
    
    private SourceGroupProxy preselectedGroup;
    
//    private boolean ignoreRootCombo;
    private boolean shouldInfoCreateFollwingFiles = Boolean.TRUE;
    private boolean shouldFXMLtoLowerCase;
    
//    private RequestProcessor.Task updatePackagesTask;

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
        
//        cbLocation.setRenderer(new SourceGroupSupport.GroupListCellRenderer());
//        cbLocation.addActionListener(this);
    
//        cbPackage.getEditor().addActionListener(this);
//        final Component packageEditor = cbPackage.getEditor().getEditorComponent();
//        if (packageEditor instanceof JTextField) {
//            ((JTextField) packageEditor).getDocument().addDocumentListener(this);
//        }
//        cbPackage.setRenderer(PackageView.listRenderer());
        
        tfLocation.setForeground(LIGHTGRAY_COLOR);
        tfPackage.setForeground(LIGHTGRAY_COLOR);
        tfProject.setForeground(LIGHTGRAY_COLOR);
        
        cbFxmlToLowerCase.addActionListener((ActionEvent e) -> {
            cbFxmlToLowerCase.setForeground(cbFxmlToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            shouldFXMLtoLowerCase = this.shouldFXMLtoLowerCase();
            this.updateTextCreateFollowingFiles(shouldInfoCreateFollwingFiles);
            this.updateTextUpdateFxmlToLowerCase();
        });
        
        taInfoPrimaryFiles.setBackground(tfProject.getBackground());
        taInfoPrimaryFiles.setForeground(LIGHTGRAY_COLOR);
    }
    
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void initValues(FileObject template, FileObject preselectedFolder, boolean shouldFXMLtoLowerCase) {
        if (template == null) {
                throw new IllegalArgumentException(
                        NbBundle.getMessage(PluginWizardIterator.class,
                            "MSG_ConfigureFXMLPanel_Template_Error")); // NOI18N
        }
        
        this.shouldFXMLtoLowerCase = shouldFXMLtoLowerCase;
        cbFxmlToLowerCase.setSelected(shouldFXMLtoLowerCase);
        cbFxmlToLowerCase.setForeground(cbFxmlToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
        
        tfProject.setText(ProjectUtils.getInformation(project).getDisplayName());
        
//        cbLocation.setModel(new DefaultComboBoxModel(sourceGroupSupport.getSourceGroups().toArray()));
        preselectedGroup = SourceGroupSupport.getContainingSourceGroup(sourceGroupSupport, preselectedFolder);
        tfLocation.setText(String.valueOf(preselectedGroup.getDisplayName()));
//        ignoreRootCombo = true;
//        cbLocation.setSelectedItem(preselectedGroup);
//        ignoreRootCombo = false;
        
        final FileObject targetFolder = preselectedFolder;
        final Object preselectedPackage = PluginSupport.getPreselectedPackage(preselectedGroup, preselectedFolder);
        if (preselectedPackage != null) {
//            cbPackage.getEditor().setItem(preselectedPackage);
            tfPackage.setText(String.valueOf(preselectedPackage));
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
        
//        this.updatePackages();
        this.updateTextCreateFollowingFiles();
        this.updateTextUpdateFxmlToLowerCase();
    }

    public String getBaseName() {
        final String text = tfFileName.getText().trim();
        return (text.length()) == 0 ? null : text;
    }
    
    public FileObject getLocationFolder() {
//        final Object selectedItem  = cbLocation.getSelectedItem();
//        return (selectedItem instanceof SourceGroupProxy) ? ((SourceGroupProxy)selectedItem).getRootFolder() : null;
        return preselectedGroup.getRootFolder();
    }

    @Override
    public String getName() {
        return "Primary Files"; // NOI18N
    }

    public String getPackageFileName() {
//        final String packageName = cbPackage.getEditor().getItem().toString();
        final String packageName = tfPackage.getText();
        return packageName.replace(SIGN_CHAR_DOT, File.separatorChar);
    }
    
    /**
     * Name of selected package, or "" for default package.
     * @return 
     */
    public String getPackageName() {
//        return cbPackage.getEditor().getItem().toString();
        final String packageName = tfPackage.getText();
        return packageName;
    }
    
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    private void updatePackages() {
//        final Object item = cbLocation.getSelectedItem();
//        if (!(item instanceof SourceGroupProxy)) {
//            return;
//        }
//        WAIT_MODEL.setSelectedItem(cbPackage.getEditor().getItem());
//        cbPackage.setModel(WAIT_MODEL);
//
//        if (updatePackagesTask != null) {
//            updatePackagesTask.cancel();
//        }
//
//        updatePackagesTask = new RequestProcessor("ComboBoxUpdatePackages").post(() -> { // NOI18N
//            final ComboBoxModel model = ((SourceGroupProxy) item).getPackagesComboBoxModel();
//            SwingUtilities.invokeLater(() -> {
//                model.setSelectedItem(cbPackage.getEditor().getItem());
//                cbPackage.setModel(model);
//            });
//        });
//    }
    
    boolean shouldFXMLtoLowerCase() {
        return cbFxmlToLowerCase.isSelected();
    }
    
    private void updateTextCreateFollowingFiles() {
        this.updateTextCreateFollowingFiles(Boolean.TRUE);
    }
    
    private void updateTextCreateFollowingFiles(boolean shouldInfoCreateFollwingFiles) {
        taInfoPrimaryFiles.setText(null);
        
        if (!shouldInfoCreateFollwingFiles) {
            return;
        }
            
//        final Object selectedItem = cbLocation.getSelectedItem();
//        if (!(selectedItem instanceof SourceGroupProxy)) {
//            return;
//        }
        
        final String fileName = this.getBaseName();
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        
        final StringBuilder sb = new StringBuilder();
        String packageName = this.getPackageName().replace(SIGN_CHAR_DOT, File.separatorChar);
        if (!packageName.endsWith(File.separator)) {
            packageName += File.separator;
        }
        
        final String fxmlFileName = shouldFXMLtoLowerCase ? fileName.toLowerCase() : fileName;
        final String hitLowerCase = shouldFXMLtoLowerCase ? " (lowercase)\n" : "\n"; // NOI18N
        sb.append("- ").append(packageName).append(fxmlFileName).append(".fxml").append(hitLowerCase); // NOI18N
        sb.append("- ").append(packageName).append(fileName).append("Presenter.java\n"); // NOI18N
        sb.append("- ").append(packageName).append(fileName).append("View.java"); // NOI18N
        
        taInfoPrimaryFiles.append(sb.toString());
    }
    
    private void updateTextUpdateFxmlToLowerCase() {
        final String fileName = this.getBaseName();
        if (fileName == null || fileName.isEmpty()) {
            return;
        }
        
        final String fxmlFileName = shouldFXMLtoLowerCase ? fileName.toLowerCase() : fileName;
        cbFxmlToLowerCase.setText("File " + fxmlFileName + ".fxml should be lowercase."); // NOI18N
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
        lLocation = new javax.swing.JLabel();
        lPackage = new javax.swing.JLabel();
        lInfoPrimaryFiles = new javax.swing.JLabel();
        separator = new javax.swing.JSeparator();
        spInfoPrimaryFiles = new javax.swing.JScrollPane();
        taInfoPrimaryFiles = new javax.swing.JTextArea();
        tfPackage = new javax.swing.JTextField();
        tfLocation = new javax.swing.JTextField();
        cbFxmlToLowerCase = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(lFileName, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lFileName.text")); // NOI18N
        lFileName.setPreferredSize(new java.awt.Dimension(100, 20));

        tfFileName.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfFileName.text")); // NOI18N

        tfProject.setEditable(false);
        tfProject.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfProject.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lProject, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lProject.text")); // NOI18N
        lProject.setPreferredSize(new java.awt.Dimension(100, 20));

        org.openide.awt.Mnemonics.setLocalizedText(lLocation, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.lLocation.text")); // NOI18N
        lLocation.setPreferredSize(new java.awt.Dimension(100, 20));

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
        taInfoPrimaryFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInfoPrimaryFiles.setViewportView(taInfoPrimaryFiles);

        tfPackage.setEditable(false);
        tfPackage.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfPackage.text")); // NOI18N

        tfLocation.setEditable(false);
        tfLocation.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.tfLocation.text")); // NOI18N

        cbFxmlToLowerCase.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbFxmlToLowerCase, org.openide.util.NbBundle.getMessage(PluginVisualPanelPrimaryFiles.class, "PluginVisualPanelPrimaryFiles.cbFxmlToLowerCase.text")); // NOI18N
        cbFxmlToLowerCase.setFocusable(false);
        cbFxmlToLowerCase.setIconTextGap(6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lInfoPrimaryFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(separator)
                    .addComponent(spInfoPrimaryFiles)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lProject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbFxmlToLowerCase)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tfProject)
                            .addComponent(tfFileName)
                            .addComponent(tfPackage)
                            .addComponent(tfLocation))))
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
                    .addComponent(lLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPackage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbFxmlToLowerCase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lInfoPrimaryFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spInfoPrimaryFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbFxmlToLowerCase;
    private javax.swing.JLabel lFileName;
    private javax.swing.JLabel lInfoPrimaryFiles;
    private javax.swing.JLabel lLocation;
    private javax.swing.JLabel lPackage;
    private javax.swing.JLabel lProject;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoPrimaryFiles;
    private javax.swing.JTextArea taInfoPrimaryFiles;
    private javax.swing.JTextField tfFileName;
    private javax.swing.JTextField tfLocation;
    private javax.swing.JTextField tfPackage;
    private javax.swing.JTextField tfProject;
    // End of variables declaration//GEN-END:variables

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (cbLocation == e.getSource()) {
//            if (!ignoreRootCombo) {
//                updatePackages();
//            }
//            
//            updateTextCreateFollowingFiles();
//            changeSupport.fireChange();
//        } else if (cbPackage == e.getSource()) {
//            updateTextCreateFollowingFiles();
//            changeSupport.fireChange();
//        } else if (cbPackage.getEditor() == e.getSource()) {
//            updateTextCreateFollowingFiles();
//            changeSupport.fireChange();
//        }
//    }
    
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
            shouldInfoCreateFollwingFiles = (Boolean) evt.getNewValue();
            this.updateTextCreateFollowingFiles(shouldInfoCreateFollwingFiles);
        }
    }
    
}
