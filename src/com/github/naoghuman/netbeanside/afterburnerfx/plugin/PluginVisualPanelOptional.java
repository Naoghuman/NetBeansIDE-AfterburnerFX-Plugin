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
        cbCreateCSS.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cbCreateCSS.setForeground(cbCreateCSS.isSelected() ? Color.BLACK : LIGHT_GRAY);
                cbInjectCSS.setEnabled(cbCreateCSS.isSelected());
                taInjectCSSfile.setDisabledTextColor(!cbCreateCSS.isSelected() 
                        ? LIGHT_GRAY : cbInjectCSS.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taInjectCSSfile.invalidate();
                taInjectCSSfile.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        cbInjectCSS.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taInjectCSSfile.setDisabledTextColor(cbInjectCSS.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taInjectCSSfile.invalidate();
                taInjectCSSfile.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        // Properties
        cbCreateProperties.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cbCreateProperties.setForeground(cbCreateProperties.isSelected() ? Color.BLACK : LIGHT_GRAY);
                cbInjectProperties.setEnabled(cbCreateProperties.isSelected());
                taInjectPropertiesFile.setDisabledTextColor(!cbCreateProperties.isSelected() 
                        ? LIGHT_GRAY : cbInjectProperties.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taInjectPropertiesFile.invalidate();
                taInjectPropertiesFile.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        cbInjectProperties.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                taInjectPropertiesFile.setDisabledTextColor(cbInjectProperties.isSelected() ? Color.BLACK : LIGHT_GRAY);
                taInjectPropertiesFile.invalidate();
                taInjectPropertiesFile.repaint();
                
                updateTextCreateFollowingFiles();
            }
        });
        
        // TextAreas
        final JTextField tf = new JTextField();
        tf.setEnabled(false);
        taInjectCSSfile.setBackground(tf.getBackground());
        taInjectPropertiesFile.setBackground(tf.getBackground());
        taInfoOptionalFiles.setBackground(tf.getBackground());
    }
    
    void initValues(String baseName, String packageName, boolean shouldCreateCSS, boolean shouldInjectCSS,
            boolean shouldCreateProperties, boolean shouldInjectProperties) {
        this.baseName = baseName;
        this.packageName = packageName;
        
        cbCreateCSS.setSelected(shouldCreateCSS);
        String info = taInjectCSSfile.getText().replace("{0}", baseName); // NOI18N
        taInjectCSSfile.setText(info);
        cbInjectCSS.setSelected(shouldInjectCSS);
        
        cbCreateProperties.setSelected(shouldCreateProperties);
        info = taInjectPropertiesFile.getText().replace("{0}", baseName); // NOI18N
        taInjectPropertiesFile.setText(info);
        cbInjectProperties.setSelected(shouldInjectProperties);
        
        updateTextCreateFollowingFiles();
    }

    @Override
    public String getName() {
        return "Optional Files"; // NOI18N
    }
    
    boolean shouldCreateCSS() {
        return cbCreateCSS.isSelected();
    }
    
    boolean shouldInjectCSS() {
        return cbInjectCSS.isSelected();
    }
    
    boolean shouldCreateProperties() {
        return cbCreateProperties.isSelected();
    }
    
    boolean shouldInjectProperties() {
        return cbInjectProperties.isSelected();
    }
    
    private void updateTextCreateFollowingFiles() {
        taInfoOptionalFiles.setText(null);
        
        final boolean shouldCreateCSS = cbCreateCSS.isSelected();
        if (shouldCreateCSS) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(baseName).append(".css"); // NOI18N
            sb.append(cbInjectCSS.isSelected() ? " (injected)\n" : "\n"); // NOI18N
            
            taInfoOptionalFiles.append(sb.toString());
        }
        
        final boolean shouldCreateProperties = cbCreateProperties.isSelected();
        if (shouldCreateProperties) {
            final StringBuilder sb = new StringBuilder();
            sb.append("- ").append(packageName).append(baseName).append(".properties"); // NOI18N
            sb.append(cbInjectProperties.isSelected() ? " (injected)\n" : "\n"); // NOI18N
            
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
        taInjectCSSfile = new javax.swing.JTextArea();
        spInjectPropertiesFile = new javax.swing.JScrollPane();
        taInjectPropertiesFile = new javax.swing.JTextArea();
        separator = new javax.swing.JSeparator();
        lInfoOptionalFiles = new javax.swing.JLabel();
        spInfoOptionalFiles = new javax.swing.JScrollPane();
        taInfoOptionalFiles = new javax.swing.JTextArea();
        cbInjectCSS = new javax.swing.JCheckBox();
        cbInjectProperties = new javax.swing.JCheckBox();
        cbCreateCSS = new javax.swing.JCheckBox();
        cbCreateProperties = new javax.swing.JCheckBox();

        spInjectCSSfile.setBorder(null);
        spInjectCSSfile.setViewportBorder(null);

        taInjectCSSfile.setEditable(false);
        taInjectCSSfile.setColumns(20);
        taInjectCSSfile.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInjectCSSfile.setLineWrap(true);
        taInjectCSSfile.setRows(3);
        taInjectCSSfile.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.taInjectCSSfile.text")); // NOI18N
        taInjectCSSfile.setWrapStyleWord(true);
        taInjectCSSfile.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        taInjectCSSfile.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taInjectCSSfile.setEnabled(false);
        taInjectCSSfile.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInjectCSSfile.setViewportView(taInjectCSSfile);

        spInjectPropertiesFile.setBorder(null);
        spInjectPropertiesFile.setViewportBorder(null);

        taInjectPropertiesFile.setEditable(false);
        taInjectPropertiesFile.setColumns(20);
        taInjectPropertiesFile.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInjectPropertiesFile.setLineWrap(true);
        taInjectPropertiesFile.setRows(3);
        taInjectPropertiesFile.setText(org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.taInjectPropertiesFile.text")); // NOI18N
        taInjectPropertiesFile.setWrapStyleWord(true);
        taInjectPropertiesFile.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 0, 0, 0));
        taInjectPropertiesFile.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        taInjectPropertiesFile.setEnabled(false);
        taInjectPropertiesFile.setMargin(new java.awt.Insets(3, 3, 0, 0));
        spInjectPropertiesFile.setViewportView(taInjectPropertiesFile);

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

        cbInjectCSS.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbInjectCSS, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbInjectCSS.text")); // NOI18N
        cbInjectCSS.setFocusable(false);
        cbInjectCSS.setPreferredSize(new java.awt.Dimension(20, 19));
        cbInjectCSS.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbInjectProperties.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbInjectProperties, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbInjectProperties.text")); // NOI18N
        cbInjectProperties.setFocusable(false);
        cbInjectProperties.setPreferredSize(new java.awt.Dimension(20, 19));
        cbInjectProperties.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        cbCreateCSS.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbCreateCSS, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbCreateCSS.text")); // NOI18N
        cbCreateCSS.setFocusable(false);

        cbCreateProperties.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(cbCreateProperties, org.openide.util.NbBundle.getMessage(PluginVisualPanelOptional.class, "PluginVisualPanelOptional.cbCreateProperties.text")); // NOI18N
        cbCreateProperties.setFocusable(false);

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
                            .addComponent(cbCreateCSS)
                            .addComponent(cbCreateProperties))
                        .addGap(0, 75, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbInjectProperties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spInjectPropertiesFile))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbInjectCSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spInjectCSSfile)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbCreateCSS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbInjectCSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spInjectCSSfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbCreateProperties)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbInjectProperties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    private javax.swing.JCheckBox cbCreateCSS;
    private javax.swing.JCheckBox cbCreateProperties;
    private javax.swing.JCheckBox cbInjectCSS;
    private javax.swing.JCheckBox cbInjectProperties;
    private javax.swing.JLabel lInfoOptionalFiles;
    private javax.swing.JSeparator separator;
    private javax.swing.JScrollPane spInfoOptionalFiles;
    private javax.swing.JScrollPane spInjectCSSfile;
    private javax.swing.JScrollPane spInjectPropertiesFile;
    private javax.swing.JTextArea taInfoOptionalFiles;
    private javax.swing.JTextArea taInjectCSSfile;
    private javax.swing.JTextArea taInjectPropertiesFile;
    // End of variables declaration//GEN-END:variables


}
