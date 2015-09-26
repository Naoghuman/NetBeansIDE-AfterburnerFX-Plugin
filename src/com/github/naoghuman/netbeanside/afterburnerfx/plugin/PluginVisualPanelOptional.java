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

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class PluginVisualPanelOptional extends JPanel {
    
    private static final Color LIGHT_GRAY = new Color(109, 109, 109);
    
    private String baseName;
    private String packageName;

    /**
     * Creates new form AfterburnerVisualPanel2
     */
    public PluginVisualPanelOptional() {
        initComponents();
        initComponents2();
    }
    
    private void initComponents2() {
        // CSS
        cbShouldCSScreated.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cbShouldCSScreated.setForeground(cbShouldCSScreated.isSelected() ? Color.BLACK : LIGHT_GRAY);
                
                cbShouldCSSinjected.setEnabled(cbShouldCSScreated.isSelected());
                if (!cbShouldCSScreated.isSelected()) {
                    cbShouldCSSinjected.setSelected(Boolean.FALSE);
                }
                taShouldCSSinjected.setDisabledTextColor(!cbShouldCSScreated.isSelected() 
                        ? LIGHT_GRAY : cbShouldCSSinjected.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taShouldCSSinjected.invalidate();
                taShouldCSSinjected.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        cbShouldCSSinjected.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taShouldCSSinjected.setDisabledTextColor(cbShouldCSSinjected.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taShouldCSSinjected.invalidate();
                taShouldCSSinjected.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        // Properties
        cbShouldPropertiesCreated.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cbShouldPropertiesCreated.setForeground(cbShouldPropertiesCreated.isSelected() ? Color.BLACK : LIGHT_GRAY);
                
                cbShouldPropertiesInjected.setEnabled(cbShouldPropertiesCreated.isSelected());
                if (!cbShouldPropertiesCreated.isSelected()) {
                    cbShouldPropertiesInjected.setSelected(Boolean.FALSE);
                }
                taShouldPropertiesInjected.setDisabledTextColor(!cbShouldPropertiesCreated.isSelected() 
                        ? LIGHT_GRAY : cbShouldPropertiesInjected.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taShouldPropertiesInjected.invalidate();
                taShouldPropertiesInjected.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        cbShouldPropertiesInjected.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taShouldPropertiesInjected.setDisabledTextColor(cbShouldPropertiesInjected.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taShouldPropertiesInjected.invalidate();
                taShouldPropertiesInjected.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        // TextAreas
        final JTextField tf = new JTextField();
        tf.setEnabled(false);
        taShouldCSSinjected.setBackground(tf.getBackground());
        taShouldPropertiesInjected.setBackground(tf.getBackground());
        taInfoOptionalFiles.setBackground(tf.getBackground());
    }
    
    void initValues(String baseName, String packageName, boolean shouldCreateCSS, boolean shouldInjectCSS,
            boolean shouldCreateProperties, boolean shouldInjectProperties) {
        this.baseName = baseName;
        this.packageName = packageName;
        
        cbShouldCSScreated.setSelected(shouldCreateCSS);
        String info = taShouldCSSinjected.getText().replace("{0}", baseName); // NOI18N
        taShouldCSSinjected.setText(info);
        cbShouldCSSinjected.setSelected(shouldInjectCSS);
        
        cbShouldPropertiesCreated.setSelected(shouldCreateProperties);
        info = taShouldPropertiesInjected.getText().replace("{0}", baseName); // NOI18N
        taShouldPropertiesInjected.setText(info);
        cbShouldPropertiesInjected.setSelected(shouldInjectProperties);
        
        updateTextCreateFollowingFiles();
    }

    @Override
    public String getName() {
        return "Optional Files"; // NOI18N
    }
    
    boolean shouldCreateCSS() {
        return cbShouldCSScreated.isSelected();
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
    
    private void updateTextCreateFollowingFiles() {
        taInfoOptionalFiles.setText(null);
        
        final boolean shouldCSScreated = cbShouldCSScreated.isSelected();
        if (shouldCSScreated) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(baseName).append(".css"); // NOI18N
            sb.append(cbShouldCSSinjected.isSelected() ? " (injected)\n" : "\n"); // NOI18N
            
            taInfoOptionalFiles.append(sb.toString());
        }
        
        final boolean shouldPropertiesCreated = cbShouldPropertiesCreated.isSelected();
        if (shouldPropertiesCreated) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(baseName).append(".properties"); // NOI18N
            sb.append(cbShouldPropertiesInjected.isSelected() ? " (injected)\n" : "\n"); // NOI18N
            
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

        spInjectCSSfile = new javax.swing.JScrollPane();
        taShouldCSSinjected = new javax.swing.JTextArea();
        spInjectPropertiesFile = new javax.swing.JScrollPane();
        taShouldPropertiesInjected = new javax.swing.JTextArea();
        separator = new javax.swing.JSeparator();
        lInfoOptionalFiles = new javax.swing.JLabel();
        spInfoOptionalFiles = new javax.swing.JScrollPane();
        taInfoOptionalFiles = new javax.swing.JTextArea();
        cbShouldCSSinjected = new javax.swing.JCheckBox();
        cbShouldPropertiesInjected = new javax.swing.JCheckBox();
        cbShouldCSScreated = new javax.swing.JCheckBox();
        cbShouldPropertiesCreated = new javax.swing.JCheckBox();

        spInjectCSSfile.setBorder(null);

        taShouldCSSinjected.setEditable(false);
        taShouldCSSinjected.setColumns(20);
        taShouldCSSinjected.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taShouldCSSinjected.setLineWrap(true);
        taShouldCSSinjected.setRows(3);
        taShouldCSSinjected.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.taShouldCSSinjected.text")); // NOI18N
        taShouldCSSinjected.setWrapStyleWord(true);
        taShouldCSSinjected.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        taShouldCSSinjected.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taShouldCSSinjected.setEnabled(false);
        taShouldCSSinjected.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInjectCSSfile.setViewportView(taShouldCSSinjected);

        spInjectPropertiesFile.setBorder(null);

        taShouldPropertiesInjected.setEditable(false);
        taShouldPropertiesInjected.setColumns(20);
        taShouldPropertiesInjected.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taShouldPropertiesInjected.setLineWrap(true);
        taShouldPropertiesInjected.setRows(3);
        taShouldPropertiesInjected.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.taShouldPropertiesInjected.text")); // NOI18N
        taShouldPropertiesInjected.setWrapStyleWord(true);
        taShouldPropertiesInjected.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        taShouldPropertiesInjected.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taShouldPropertiesInjected.setEnabled(false);
        taShouldPropertiesInjected.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInjectPropertiesFile.setViewportView(taShouldPropertiesInjected);

        org.openide.awt.Mnemonics.setLocalizedText(lInfoOptionalFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.lInfoOptionalFiles.text")); // NOI18N

        spInfoOptionalFiles.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        spInfoOptionalFiles.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoOptionalFiles.setEditable(false);
        taInfoOptionalFiles.setColumns(20);
        taInfoOptionalFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoOptionalFiles.setRows(4);
        taInfoOptionalFiles.setEnabled(false);
        taInfoOptionalFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInfoOptionalFiles.setViewportView(taInfoOptionalFiles);

        cbShouldCSSinjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldCSSinjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbShouldCSSinjected.text")); // NOI18N
        cbShouldCSSinjected.setFocusable(false);
        cbShouldCSSinjected.setPreferredSize(new java.awt.Dimension(20, 19));
        cbShouldCSSinjected.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbShouldPropertiesInjected.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldPropertiesInjected, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbShouldPropertiesInjected.text")); // NOI18N
        cbShouldPropertiesInjected.setFocusable(false);
        cbShouldPropertiesInjected.setPreferredSize(new java.awt.Dimension(20, 19));
        cbShouldPropertiesInjected.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbShouldCSScreated.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldCSScreated, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbShouldCSScreated.text")); // NOI18N
        cbShouldCSScreated.setFocusable(false);

        cbShouldPropertiesCreated.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbShouldPropertiesCreated, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbShouldPropertiesCreated.text")); // NOI18N
        cbShouldPropertiesCreated.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separator, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lInfoOptionalFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spInfoOptionalFiles, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbShouldCSScreated)
                            .addComponent(cbShouldPropertiesCreated))
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbShouldPropertiesInjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spInjectPropertiesFile))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbShouldCSSinjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spInjectCSSfile)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbShouldCSScreated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbShouldCSSinjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spInjectCSSfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbShouldPropertiesCreated)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbShouldPropertiesInjected, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spInjectPropertiesFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lInfoOptionalFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spInfoOptionalFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbShouldCSScreated;
    private javax.swing.JCheckBox cbShouldCSSinjected;
    private javax.swing.JCheckBox cbShouldPropertiesCreated;
    private javax.swing.JCheckBox cbShouldPropertiesInjected;
    private javax.swing.JLabel lInfoOptionalFiles;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoOptionalFiles;
    private javax.swing.JScrollPane spInjectCSSfile;
    private javax.swing.JScrollPane spInjectPropertiesFile;
    private javax.swing.JTextArea taInfoOptionalFiles;
    private javax.swing.JTextArea taShouldCSSinjected;
    private javax.swing.JTextArea taShouldPropertiesInjected;
    // End of variables declaration//GEN-END:variables

}
