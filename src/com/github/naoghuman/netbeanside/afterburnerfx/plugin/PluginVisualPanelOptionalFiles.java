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
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class PluginVisualPanelOptionalFiles extends JPanel implements IPluginSupport {
    
    private String baseName;
    private String packageName;

    public PluginVisualPanelOptionalFiles() {
        this.initComponents();
        this.initComponents2();
    }
    
    private void initComponents2() {
        // css
        cbShouldCSScreated.addActionListener((ActionEvent e) -> {
            final boolean shouldCSScreated = cbShouldCSScreated.isSelected();
            cbShouldCSScreated.setForeground(shouldCSScreated ? Color.BLACK : LIGHTGRAY_COLOR);
            
            cbCSStoLowerCase.setEnabled(shouldCSScreated);
            cbCSStoLowerCase.setForeground(shouldCSScreated ? 
                    (cbCSStoLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR) : LIGHTGRAY_COLOR);
            
            cbShouldCSSinjected.setEnabled(shouldCSScreated);
            cbShouldCSSinjected.setForeground(shouldCSScreated ? 
                    (cbShouldCSSinjected.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR) : LIGHTGRAY_COLOR);
            
            this.updateTextCreateFollowingFiles();
        });
        
        // properties
        cbShouldPropertiesCreated.addActionListener((ActionEvent e) -> {
            final boolean shouldPropertiesCreated = cbShouldPropertiesCreated.isSelected();
            cbShouldPropertiesCreated.setForeground(shouldPropertiesCreated ? Color.BLACK : LIGHTGRAY_COLOR);
            
            cbPropertiesToLowerCase.setEnabled(shouldPropertiesCreated);
            cbPropertiesToLowerCase.setForeground(shouldPropertiesCreated ? 
                    (cbPropertiesToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR) : LIGHTGRAY_COLOR);
            
            cbShouldPropertiesInjected.setEnabled(shouldPropertiesCreated);
            cbShouldPropertiesInjected.setForeground(shouldPropertiesCreated ? 
                    (cbShouldPropertiesInjected.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR) : LIGHTGRAY_COLOR);
            
            this.updateTextCreateFollowingFiles();
        });
        
        // configuration.properties
        cbShouldConfigurationPropertiesCreated.addActionListener((ActionEvent e) -> {
            final boolean shouldConfigurationPropertiesCreated = cbShouldConfigurationPropertiesCreated.isSelected();
            cbShouldConfigurationPropertiesCreated.setForeground(shouldConfigurationPropertiesCreated ? Color.BLACK : LIGHTGRAY_COLOR);
            
            cbConfigurationPropertiesToLowerCase.setEnabled(shouldConfigurationPropertiesCreated);
            cbConfigurationPropertiesToLowerCase.setForeground(shouldConfigurationPropertiesCreated ? 
                    (cbConfigurationPropertiesToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR) : LIGHTGRAY_COLOR);
            
            this.updateTextCreateFollowingFiles();
        });
        
        // TextAreas
        final JTextField tf = new JTextField();
        tf.setEditable(false);
        tf.setEnabled(false);
        
        taInfoOptionalFiles.setBackground(tf.getBackground());
        taInfoOptionalFiles.setForeground(LIGHTGRAY_COLOR);
    }
    
    private void initComponents3() {
        // CSS
        cbCSStoLowerCase.addActionListener((ActionEvent e) -> {
            cbCSStoLowerCase.setForeground(cbCSStoLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
        
        cbShouldCSSinjected.addActionListener((ActionEvent e) -> {
            cbShouldCSSinjected.setForeground(cbShouldCSSinjected.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
        
        // properties
        cbPropertiesToLowerCase.addActionListener((ActionEvent e) -> {
            cbPropertiesToLowerCase.setForeground(cbPropertiesToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
        
        cbShouldPropertiesInjected.addActionListener((ActionEvent e) -> {
            cbShouldPropertiesInjected.setForeground(cbShouldPropertiesInjected.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
        
        // configuration.properties
        cbConfigurationPropertiesToLowerCase.addActionListener((ActionEvent e) -> {
            cbConfigurationPropertiesToLowerCase.setForeground(cbConfigurationPropertiesToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
    }
    
    void initValues(String baseName, String packageName, boolean shouldFXMLtoLowerCase,
            boolean shouldCreateCSS, boolean shouldInjectCSS, boolean shouldCSStoLowerCase,
            boolean shouldCreateProperties, boolean shouldInjectProperties, boolean shouldPropertiesToLowerCase,
            boolean shouldCreateConfigurationProperties, boolean shouldConfigurationPropertiesToLowerCase
    ) {
        this.baseName = baseName;
        this.packageName = packageName;
        
        // css
        cbShouldCSScreated.setSelected(!shouldCreateCSS);
        
        final String fxmlFileName = shouldFXMLtoLowerCase ? baseName.toLowerCase() : baseName;
        cbShouldCSSinjected.setText("Inject the .css file into " + fxmlFileName + ".fxml."); // NOI18N
        cbShouldCSSinjected.setSelected(shouldInjectCSS);
        cbCSStoLowerCase.setSelected(shouldCSStoLowerCase);
        
        
        // properties
        cbShouldPropertiesCreated.setSelected(!shouldCreateProperties);
        cbShouldPropertiesInjected.setText("Inject the .properties file into " + baseName + "Presenter.java."); // NOI18N
        cbShouldPropertiesInjected.setSelected(shouldInjectProperties);
        cbPropertiesToLowerCase.setSelected(shouldPropertiesToLowerCase);
        
        // configuration.properties
        cbShouldConfigurationPropertiesCreated.setSelected(!shouldCreateConfigurationProperties);
        cbConfigurationPropertiesToLowerCase.setSelected(shouldConfigurationPropertiesToLowerCase);
        
        // update gui
        cbShouldCSScreated.doClick();
        cbShouldPropertiesCreated.doClick();
        cbShouldConfigurationPropertiesCreated.doClick();
        this.initComponents3();
    }

    @Override
    public String getName() {
        return "Optional Files"; // NOI18N
    }
    
    boolean shouldCreateCSS() {
        return cbShouldCSScreated.isSelected();
    }
    
    boolean shouldCSStoLowerCase() {
        return cbCSStoLowerCase.isSelected();
    }
    
    boolean shouldInjectCSS() {
        return cbShouldCSSinjected.isSelected();
    }
    
    boolean shouldCreateProperties() {
        return cbShouldPropertiesCreated.isSelected();
    }
    
    boolean shouldInjectProperties() {
        return cbShouldPropertiesInjected.isSelected();
    }
    
    boolean shouldPropertiesToLowerCase() {
        return cbPropertiesToLowerCase.isSelected();
    }
    
    boolean shouldConfigurationPropertiesToLowerCase() {
        return cbConfigurationPropertiesToLowerCase.isSelected();
    }
    
    boolean shouldCreateConfigurationProperties() {
        return cbShouldConfigurationPropertiesCreated.isSelected();
    }
    
    private void updateTextCreateFollowingFiles() {
        taInfoOptionalFiles.setText(null);
        
        // css
        final String cssFileName = cbCSStoLowerCase.isSelected() ? baseName.toLowerCase() : baseName;
        cbCSStoLowerCase.setText(cssFileName + ".css to lowercase."); // NOI18N
        
        final boolean shouldCSScreated = cbShouldCSScreated.isSelected();
        if (shouldCSScreated) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(cssFileName).append(".css"); // NOI18N
            sb.append(PluginSupport.extractAdditionalInformations(cbShouldCSSinjected.isSelected(), cbCSStoLowerCase.isSelected()));
            
            taInfoOptionalFiles.append(sb.toString());
        }
        
        // properties
        final String propertiesFileName = cbPropertiesToLowerCase.isSelected() ? baseName.toLowerCase() : baseName;
        cbPropertiesToLowerCase.setText(propertiesFileName + ".properties to lowercase."); // NOI18N
        
        final boolean shouldPropertiesCreated = cbShouldPropertiesCreated.isSelected();
        if (shouldPropertiesCreated) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(propertiesFileName).append(".properties"); // NOI18N
            sb.append(PluginSupport.extractAdditionalInformations(cbShouldPropertiesInjected.isSelected(), cbPropertiesToLowerCase.isSelected()));
            
            taInfoOptionalFiles.append(sb.toString());
        }
        
        // configuration.properties
        final String configurationPropertiesFileName = cbConfigurationPropertiesToLowerCase.isSelected() ? "configuration.properties" : "Configuration.properties"; // NOI18N
        cbConfigurationPropertiesToLowerCase.setText(configurationPropertiesFileName + " to lowercase."); // NOI18N
        
        final boolean shouldConfigurationPropertiesCreated = cbShouldConfigurationPropertiesCreated.isSelected();
        if (shouldConfigurationPropertiesCreated) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(configurationPropertiesFileName); // NOI18N
            sb.append(cbConfigurationPropertiesToLowerCase.isSelected() ? " (lowercase)" : ""); // NOI18N
            
            taInfoOptionalFiles.append(sb.toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        separator = new javax.swing.JSeparator();
        lInfoOptionalFiles = new javax.swing.JLabel();
        spInfoOptionalFiles = new javax.swing.JScrollPane();
        taInfoOptionalFiles = new javax.swing.JTextArea();
        cbShouldCSSinjected = new javax.swing.JCheckBox();
        cbShouldPropertiesInjected = new javax.swing.JCheckBox();
        cbShouldCSScreated = new javax.swing.JCheckBox();
        cbShouldPropertiesCreated = new javax.swing.JCheckBox();
        cbCSStoLowerCase = new javax.swing.JCheckBox();
        cbPropertiesToLowerCase = new javax.swing.JCheckBox();
        cbShouldConfigurationPropertiesCreated = new javax.swing.JCheckBox();
        cbConfigurationPropertiesToLowerCase = new javax.swing.JCheckBox();

        org.openide.awt.Mnemonics.setLocalizedText(lInfoOptionalFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.lInfoOptionalFiles.text")); // NOI18N

        spInfoOptionalFiles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spInfoOptionalFiles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoOptionalFiles.setEditable(false);
        taInfoOptionalFiles.setColumns(20);
        taInfoOptionalFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoOptionalFiles.setRows(4);
        taInfoOptionalFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInfoOptionalFiles.setViewportView(taInfoOptionalFiles);

        cbShouldCSSinjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldCSSinjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldCSSinjected.text")); // NOI18N
        cbShouldCSSinjected.setFocusable(false);
        cbShouldCSSinjected.setIconTextGap(6);
        cbShouldCSSinjected.setPreferredSize(new java.awt.Dimension(317, 23));
        cbShouldCSSinjected.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbShouldPropertiesInjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldPropertiesInjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldPropertiesInjected.text")); // NOI18N
        cbShouldPropertiesInjected.setFocusable(false);
        cbShouldPropertiesInjected.setIconTextGap(6);
        cbShouldPropertiesInjected.setPreferredSize(new java.awt.Dimension(22, 22));
        cbShouldPropertiesInjected.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbShouldCSScreated.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldCSScreated, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldCSScreated.text")); // NOI18N
        cbShouldCSScreated.setFocusable(false);
        cbShouldCSScreated.setIconTextGap(6);

        cbShouldPropertiesCreated.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldPropertiesCreated, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldPropertiesCreated.text")); // NOI18N
        cbShouldPropertiesCreated.setFocusable(false);
        cbShouldPropertiesCreated.setIconTextGap(6);

        cbCSStoLowerCase.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbCSStoLowerCase, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbCSStoLowerCase.text")); // NOI18N
        cbCSStoLowerCase.setFocusable(false);
        cbCSStoLowerCase.setIconTextGap(6);

        cbPropertiesToLowerCase.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbPropertiesToLowerCase, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbPropertiesToLowerCase.text")); // NOI18N
        cbPropertiesToLowerCase.setFocusable(false);
        cbPropertiesToLowerCase.setIconTextGap(6);

        org.openide.awt.Mnemonics.setLocalizedText(cbShouldConfigurationPropertiesCreated, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldConfigurationPropertiesCreated.text")); // NOI18N
        cbShouldConfigurationPropertiesCreated.setFocusable(false);
        cbShouldConfigurationPropertiesCreated.setIconTextGap(6);

        cbConfigurationPropertiesToLowerCase.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbConfigurationPropertiesToLowerCase, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbConfigurationPropertiesToLowerCase.text")); // NOI18N
        cbConfigurationPropertiesToLowerCase.setFocusable(false);
        cbConfigurationPropertiesToLowerCase.setIconTextGap(6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(separator)
                    .addComponent(lInfoOptionalFiles, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spInfoOptionalFiles)
                    .addComponent(cbShouldCSScreated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbShouldPropertiesCreated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbShouldCSSinjected, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                            .addComponent(cbShouldPropertiesInjected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbCSStoLowerCase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPropertiesToLowerCase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(cbShouldConfigurationPropertiesCreated, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(cbConfigurationPropertiesToLowerCase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbShouldCSScreated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbShouldCSSinjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCSStoLowerCase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShouldPropertiesCreated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbShouldPropertiesInjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPropertiesToLowerCase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShouldConfigurationPropertiesCreated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbConfigurationPropertiesToLowerCase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lInfoOptionalFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spInfoOptionalFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbCSStoLowerCase;
    private javax.swing.JCheckBox cbConfigurationPropertiesToLowerCase;
    private javax.swing.JCheckBox cbPropertiesToLowerCase;
    private javax.swing.JCheckBox cbShouldCSScreated;
    private javax.swing.JCheckBox cbShouldCSSinjected;
    private javax.swing.JCheckBox cbShouldConfigurationPropertiesCreated;
    private javax.swing.JCheckBox cbShouldPropertiesCreated;
    private javax.swing.JCheckBox cbShouldPropertiesInjected;
    private javax.swing.JLabel lInfoOptionalFiles;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoOptionalFiles;
    private javax.swing.JTextArea taInfoOptionalFiles;
    // End of variables declaration//GEN-END:variables

}
