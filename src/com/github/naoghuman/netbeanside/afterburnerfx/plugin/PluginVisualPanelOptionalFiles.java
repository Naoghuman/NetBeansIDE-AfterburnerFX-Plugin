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
        // CSS
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
        
        // Properties
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
        
        // Properties
        cbPropertiesToLowerCase.addActionListener((ActionEvent e) -> {
            cbPropertiesToLowerCase.setForeground(cbPropertiesToLowerCase.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
        
        cbShouldPropertiesInjected.addActionListener((ActionEvent e) -> {
            cbShouldPropertiesInjected.setForeground(cbShouldPropertiesInjected.isSelected() ? Color.BLACK : LIGHTGRAY_COLOR);
            this.updateTextCreateFollowingFiles();
        });
    }
    
    void initValues(String baseName, String packageName, 
            boolean shouldCreateCSS, boolean shouldCSStoLowerCase, boolean shouldInjectCSS,
            boolean shouldCreateProperties, boolean shouldPropertiesToLowerCase, boolean shouldInjectProperties
    ) {
        this.baseName = baseName;
        this.packageName = packageName;
        
        cbShouldCSScreated.setSelected(!shouldCreateCSS);
        String info = cbShouldCSSinjected.getText().replace("{0}", baseName); // NOI18N
        cbShouldCSSinjected.setText(info);
        cbShouldCSSinjected.setSelected(shouldInjectCSS);
        cbCSStoLowerCase.setSelected(shouldCSStoLowerCase);
        
        cbShouldPropertiesCreated.setSelected(!shouldCreateProperties);
        info = cbShouldPropertiesInjected.getText().replace("{0}", baseName); // NOI18N
        cbShouldPropertiesInjected.setText(info);
        cbShouldPropertiesInjected.setSelected(shouldInjectProperties);
        cbPropertiesToLowerCase.setSelected(shouldPropertiesToLowerCase);
        
        cbShouldCSScreated.doClick();
        cbShouldPropertiesCreated.doClick();
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
    
    private void updateTextCreateFollowingFiles() {
        taInfoOptionalFiles.setText(null);
        
        final boolean shouldCSScreated = cbShouldCSScreated.isSelected();
        if (shouldCSScreated) {
            final StringBuilder sb = new StringBuilder();
            final String cssFileName = cbCSStoLowerCase.isSelected() ? baseName.toLowerCase() : baseName;
            sb.append("- ").append(packageName).append(cssFileName).append(".css"); // NOI18N
            sb.append(cbCSStoLowerCase.isSelected() ? " (lowercase)" : ""); // NOI18N
            sb.append(cbShouldCSSinjected.isSelected() ? " (injected)\n" : "\n"); // NOI18N
            
            taInfoOptionalFiles.append(sb.toString());
        }
        
        final boolean shouldPropertiesCreated = cbShouldPropertiesCreated.isSelected();
        if (shouldPropertiesCreated) {
            final StringBuilder sb = new StringBuilder();
            final String propertiesFileName = cbPropertiesToLowerCase.isSelected() ? baseName.toLowerCase() : baseName;
            sb.append("- ").append(packageName).append(propertiesFileName).append(".properties"); // NOI18N
            sb.append(cbPropertiesToLowerCase.isSelected() ? " (lowercase)" : ""); // NOI18N
            sb.append(cbShouldPropertiesInjected.isSelected() ? " (injected)" : ""); // NOI18N
            
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

        org.openide.awt.Mnemonics.setLocalizedText(lInfoOptionalFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.lInfoOptionalFiles.text")); // NOI18N

        spInfoOptionalFiles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spInfoOptionalFiles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoOptionalFiles.setEditable(false);
        taInfoOptionalFiles.setColumns(20);
        taInfoOptionalFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoOptionalFiles.setRows(3);
        taInfoOptionalFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInfoOptionalFiles.setViewportView(taInfoOptionalFiles);

        cbShouldCSSinjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldCSSinjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldCSSinjected.text")); // NOI18N
        cbShouldCSSinjected.setFocusable(false);
        cbShouldCSSinjected.setPreferredSize(new java.awt.Dimension(317, 23));
        cbShouldCSSinjected.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbShouldPropertiesInjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldPropertiesInjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptionalFiles.class, "PluginVisualPanelOptionalFiles.cbShouldPropertiesInjected.text")); // NOI18N
        cbShouldPropertiesInjected.setFocusable(false);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbShouldPropertiesCreated, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(separator, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lInfoOptionalFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spInfoOptionalFiles, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbShouldCSScreated, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbShouldCSSinjected, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
                            .addComponent(cbShouldPropertiesInjected, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCSStoLowerCase)
                                    .addComponent(cbPropertiesToLowerCase))
                                .addGap(0, 0, Short.MAX_VALUE)))))
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
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lInfoOptionalFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spInfoOptionalFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbCSStoLowerCase;
    private javax.swing.JCheckBox cbPropertiesToLowerCase;
    private javax.swing.JCheckBox cbShouldCSScreated;
    private javax.swing.JCheckBox cbShouldCSSinjected;
    private javax.swing.JCheckBox cbShouldPropertiesCreated;
    private javax.swing.JCheckBox cbShouldPropertiesInjected;
    private javax.swing.JLabel lInfoOptionalFiles;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoOptionalFiles;
    private javax.swing.JTextArea taInfoOptionalFiles;
    // End of variables declaration//GEN-END:variables

}
