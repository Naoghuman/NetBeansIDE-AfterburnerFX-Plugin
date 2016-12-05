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
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openide.util.NbBundle;

public final class PluginVisualPanelSummary extends JPanel implements IPluginSupport {

    public PluginVisualPanelSummary() {
        initComponents();
        initComponents2();
    }
    
    private void initComponents2() {
        // TextAreas
        final JTextField tf = new JTextField();
        tf.setEditable(false);
        tf.setEnabled(false);
        
        taInfoPrimaryFiles.setBackground(tf.getBackground());
        taInfoPrimaryFiles.setForeground(LIGHTGRAY_COLOR);
        
        taInfoOptionalFiles.setBackground(tf.getBackground());
        taInfoOptionalFiles.setForeground(LIGHTGRAY_COLOR);
    }
    
    void initValues(String baseName, String packageName, boolean shouldFXMLtoLowerCase,
            boolean shouldCreateCSS, boolean shouldCSStoLowerCase, boolean shouldInjectCSS, 
            boolean shouldCreateProperties, boolean shouldPropertiesToLowerCase, boolean shouldInjectProperties,
            boolean shouldCreateConfigurationProperties, boolean shouldConfigurationPropertiesToLowerCase 
    ) {
        // Primary files
        lInfoPrimaryFiles.setText(NbBundle.getMessage(PluginVisualPanelSummary.class, "PluginVisualPanelSummary.lInfoPrimaryFiles.text")); // NOI18N
        taInfoPrimaryFiles.setText(null);
        
        StringBuilder sb = new StringBuilder();
        sb.append("- ").append(baseName).append("Presenter.java\n"); // NOI18N
        sb.append("- ").append(baseName).append("View.java\n"); // NOI18N
        final String fxmlFileName = shouldFXMLtoLowerCase ? baseName.toLowerCase() : baseName;
        final String hitLowerCase = shouldFXMLtoLowerCase ? " (lowercase)" : ""; // NOI18N
        sb.append("- ").append(fxmlFileName).append(".fxml").append(hitLowerCase); // NOI18N
        
        lInfoPrimaryFiles.setText(lInfoPrimaryFiles.getText().replace("%s", packageName)); // NOI18N
        taInfoPrimaryFiles.setText(sb.toString());
        
        // Optional files
        lInfoOptionalFiles.setText(NbBundle.getMessage(PluginVisualPanelSummary.class, "PluginVisualPanelSummary.lInfoOptionalFiles.text")); // NOI18N
        taInfoOptionalFiles.setText(null);
        sb = new StringBuilder();
        
        // css
        if (shouldCreateCSS) {
            final String cssFileName = shouldCSStoLowerCase ? baseName.toLowerCase() : baseName;
            sb.append("- ").append(cssFileName).append(".css"); // NOI18N
            sb.append(PluginSupport.extractAdditionalInformations(shouldInjectCSS, shouldCSStoLowerCase));
        }
        
        // properties
        if (shouldCreateProperties) {
            final String propertiesFileName = shouldPropertiesToLowerCase ? baseName.toLowerCase() : baseName;
            sb.append("- ").append(propertiesFileName).append(".properties"); // NOI18N
            sb.append(PluginSupport.extractAdditionalInformations(shouldInjectProperties, shouldPropertiesToLowerCase));
        }
        
        // configuration.properties
        if (shouldCreateConfigurationProperties) {
            final String configurationPropertiesFileName = shouldConfigurationPropertiesToLowerCase ? "configuration.properties" : "Configuration.properties"; // NOI18N
            sb.append("- ").append(configurationPropertiesFileName); // NOI18N
            sb.append(shouldConfigurationPropertiesToLowerCase ? " (lowercase)" : ""); // NOI18N
        }
        
        taInfoOptionalFiles.setText(sb.toString());
        
        final boolean shouldUpdateInfoOptionalFiles =
                shouldCreateCSS
                || shouldCreateProperties
                || shouldCreateConfigurationProperties;
        if (shouldUpdateInfoOptionalFiles) {
            lInfoOptionalFiles.setText(lInfoOptionalFiles.getText().replace("%s", packageName)); // NOI18N
        } else {
            lInfoOptionalFiles.setText(lInfoOptionalFiles.getText().replace("%s", "")); // NOI18N
        }
    }

    @Override
    public String getName() {
        return "Summary"; // NOI18N
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lInfoPrimaryFiles = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taInfoPrimaryFiles = new javax.swing.JTextArea();
        lInfoOptionalFiles = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taInfoOptionalFiles = new javax.swing.JTextArea();

        org.openide.awt.Mnemonics.setLocalizedText(lInfoPrimaryFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelSummary.class, "PluginVisualPanelSummary.lInfoPrimaryFiles.text")); // NOI18N

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoPrimaryFiles.setEditable(false);
        taInfoPrimaryFiles.setColumns(20);
        taInfoPrimaryFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoPrimaryFiles.setRows(4);
        taInfoPrimaryFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        jScrollPane2.setViewportView(taInfoPrimaryFiles);

        org.openide.awt.Mnemonics.setLocalizedText(lInfoOptionalFiles, org.openide.util.NbBundle.getMessage(PluginVisualPanelSummary.class, "PluginVisualPanelSummary.lInfoOptionalFiles.text")); // NOI18N

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        taInfoOptionalFiles.setEditable(false);
        taInfoOptionalFiles.setColumns(20);
        taInfoOptionalFiles.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        taInfoOptionalFiles.setRows(4);
        taInfoOptionalFiles.setMargin(new java.awt.Insets(3, 3, 0, 0));
        jScrollPane1.setViewportView(taInfoOptionalFiles);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lInfoPrimaryFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lInfoOptionalFiles)
                        .addGap(0, 279, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lInfoPrimaryFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lInfoOptionalFiles)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lInfoOptionalFiles;
    private javax.swing.JLabel lInfoPrimaryFiles;
    private javax.swing.JTextArea taInfoOptionalFiles;
    private javax.swing.JTextArea taInfoPrimaryFiles;
    // End of variables declaration//GEN-END:variables
}
