/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * Oracle and Java are registered trademarks of Oracle and/or its affiliates.
 * Other names may be trademarks of their respective owners.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common
 * Development and Distribution License("CDDL") (collectively, the
 * "License"). You may not use this file except in compliance with the
 * License. You can obtain a copy of the License at
 * http://www.netbeans.org/cddl-gplv2.html
 * or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
 * specific language governing permissions and limitations under the
 * License.  When distributing the software, include this License Header
 * Notice in each file and include the License file at
 * nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the GPL Version 2 section of the License file that
 * accompanied this code. If applicable, add the following below the
 * License Header, with the fields enclosed by brackets [] replaced by
 * your own identifying information:
 * "Portions Copyrighted [year] [name of copyright owner]"
 *
 * If you wish your version of this file to be governed by only the CDDL
 * or only the GPL Version 2, indicate your decision by adding
 * "[Contributor] elects to include this software in this distribution
 * under the [CDDL or GPL Version 2] license." If you do not indicate a
 * single choice of license, a recipient has the option to distribute
 * your version of this file under either the CDDL, the GPL Version 2 or
 * to extend the choice of license to its licensees as provided above.
 * However, if you add GPL Version 2 code and therefore, elected the GPL
 * Version 2 license, then the option applies only if the new code is
 * made subject to such option by the copyright holder.
 *
 * Contributor(s):
 *
 * Portions Copyrighted 2013 Sun Microsystems, Inc.
 */
package com.github.naoghuman.netbeanside.afterburnerfx.plugin.support;

import java.awt.Component;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.java.project.JavaProjectConstants;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.SourceGroup;
import org.netbeans.api.project.SourceGroupModifier;
import org.netbeans.spi.java.project.support.ui.PackageView;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

/**
 * Helper class to handle Location (Source Root) and Package selection in FXML wizards
 * 
 * @author Petr Somol
 */
public class SourceGroupSupport {

    private List<SourceGroupProxy> groups = null;
    private final String type;
    private SourceGroupProxy currentSourceGroup = null;
    private String currentPackageName = null;
    private SourceGroupSupport parent = null;
    private String currentFileName = null;    
    
    public SourceGroupSupport(@NonNull String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
    
    /**
     * Access to parent is used to take over the last visited directory etc.
     * @param support 
     */
    public void setParent(SourceGroupSupport support) {
        this.parent = support;
    }
    
    public SourceGroupSupport getParent() {
        return parent;
    }
    
    // add
    
    public void addSourceGroups(@NonNull SourceGroup[] sourceGroups) {
        if(sourceGroups.length == 0) {
            return;
        }
        if(groups == null) {
            groups = new ArrayList<>();
        }
        for (SourceGroup sourceGroup : sourceGroups) {
            groups.add(new SourceGroupProxy(sourceGroup));
        }
        if(currentSourceGroup == null) {
            currentSourceGroup = groups.get(0);
        }
    }
    
    public void addSourceGroupProxy(@NonNull Project project, @NonNull String displayName, String[] packageProxy) {
        if(packageProxy == null || packageProxy.length == 0) {
            return;
        }
        if(groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(new SourceGroupProxy(project, displayName, packageProxy));
        if(currentSourceGroup == null) {
            currentSourceGroup = groups.get(0);
        }
    }
    
    // get
    
    public int noOfSourceGroups() {
        if(groups != null) {
            return groups.size();
        }
        return 0;
    }

    public List<SourceGroupProxy> getSourceGroups() {
        if(groups != null) {
           return Collections.unmodifiableList(groups);
        }
        return null;
    }
    
    public File[] getSourceGroupsAsFiles() {
        List<File> list = new ArrayList<>();
        groups.stream().forEach((p) -> {
            list.add(FileUtil.toFile(p.getRootFolder()));
        });
        return list.toArray(new File[0]);
    }
    
    // get/set current
    
    public void setCurrentSourceGroup(SourceGroupProxy current) {
        this.currentSourceGroup = current;
    }
    
    public SourceGroupProxy getCurrentSourceGroup() {
        return currentSourceGroup;
    }
    
    public void setCurrentPackageName(String current) {
        this.currentPackageName = current;
    }
    
    public String getCurrentPackageName() {
        return currentPackageName;
    }
    
    public void setCurrentFileName(String fileName) {
        this.currentFileName = fileName;
    }
    
    public String getCurrentFileName() {
        return currentFileName;
    }
    
    // get folders
    
    public String getCurrentPackagePath() {
        if(currentPackageName != null && currentSourceGroup != null) {
            String packageNameDecorated = (currentPackageName.startsWith("/") || currentPackageName.startsWith(File.separator) ? "" : "/") + // NOI18N
                    currentPackageName
                    + (currentPackageName.endsWith("/") || currentPackageName.endsWith(File.separator) || currentPackageName.length() == 0 ? "" : "/"); // NOI18N
            if(currentSourceGroup.isReal()) {
                 return FileUtil.getFileDisplayName(currentSourceGroup.getRootFolder()) + packageNameDecorated;
            }
            return FileUtil.getFileDisplayName(currentSourceGroup.getRootFolder()) + "/<" + currentSourceGroup.getDisplayName() + ">" + packageNameDecorated; // NOI18N
        }
        return null;
    }

    public FileObject getCurrentSourceGroupFolder() {
        if(currentSourceGroup != null) {
            return currentSourceGroup.getRootFolder();
        }
        return null;
    }
    
    public FileObject getCurrentChooserFolder() {
        FileObject folder = getCurrentPackageFolder(false);
        if(folder == null) {
            folder = getCurrentSourceGroupFolder();
        }
        return folder;
    }
    
    /**
     * Returns current package as FileObject or null if it does not exist.
     * if create==true, creates if does not exist (also creates SourceGroup if does not exist)
     * @param create
     * @return 
     */
    public FileObject getCurrentPackageFolder(boolean create) {
        if(currentPackageName != null && currentSourceGroup != null) {
            if(create == true && !currentSourceGroup.isReal()) {
                SourceGroup created = SourceGroupModifier.createSourceGroup(currentSourceGroup.getProject(), getType(), JavaProjectConstants.SOURCES_HINT_MAIN);
                if(created != null) {
                    groups.stream().filter((p) -> (!p.isReal())).forEach((p) -> {
                        p.setSourceGroup(created);
                    });
                    return getCurrentPackageFolder(create);
                }
            }
            if(currentSourceGroup.isReal()) {
                FileObject rootFolder = currentSourceGroup.getRootFolder();
                FileObject folder = rootFolder.getFileObject(currentPackageName, ""); // NOI18Ns
                if(folder == null && create) {
                    try {
                        folder = rootFolder;
                        StringTokenizer tk = new StringTokenizer(currentPackageName, "/."); // NOI18N
                        String name;
                        while (tk.hasMoreTokens()) {
                            name = tk.nextToken();
                            FileObject fo = folder.getFileObject(name, ""); // NOI18N
                            if (fo == null) {
                                folder = folder.createFolder(name);
                                break;
                            }
                            folder = fo;
                        }
                        while (tk.hasMoreTokens()) {
                            name = tk.nextToken();
                            folder = folder.createFolder(name);
                        }
                    } catch (IOException e) {
                        Exceptions.printStackTrace(e);
                    }
                }
                return folder;
            }
        }
        return null;
    }
    
    /**
     * Proxy to represent SourceGroups, to enable processing of non-existent packages
     * (and thus to enable correct Cancel in dialogs).
     */
    public static class SourceGroupProxy implements SourceGroup {

        private Project project = null;
        private SourceGroup sourceGroup = null;
        private FileObject root = null;
        private String name = "n/a"; // NOI18N
        private String displayName = "n/a"; // NOI18N
        private Icon iconTrue = null;
        private Icon iconFalse = null;
        private List<String> packageProxy = null;
      
        public SourceGroupProxy(@NonNull SourceGroup sourceGroup) {
            this.sourceGroup = sourceGroup;
        }
        
        public SourceGroupProxy(@NonNull Project project, @NonNull String displayName, String[] packageProxy) {
            this.project = project;
            this.root = project.getProjectDirectory();
            this.displayName = displayName;
            this.packageProxy = new ArrayList<>();
            this.packageProxy.addAll(Arrays.asList(packageProxy));
        }
        
        public boolean isReal() {
            return sourceGroup != null;
        }
        
        public Project getProject() {
            return project;
        }
        
        public void setSourceGroup(@NonNull SourceGroup sourceGroup) {
            this.sourceGroup = sourceGroup;
        }
        
        @SuppressWarnings({"rawtypes", "unchecked"})
        public ComboBoxModel getPackagesComboBoxModel() {
            if(sourceGroup != null) {
                return PackageView.createListView(sourceGroup);
            }
            if(packageProxy != null) {
                return new DefaultComboBoxModel(packageProxy.toArray(new String[0]));
            }
            return null;
        }
        
        @Override
        public String toString() {
            if(sourceGroup != null) {
                return sourceGroup.toString();
            }
            return name + '/' + displayName;
        }

        @Override
        public int hashCode() {
            int hash = 5;
            hash = 53 * hash + (this.sourceGroup != null ? this.sourceGroup.hashCode() : 0);
            FileObject root = getRootFolder();
            hash = 53 * hash + (root != null ? root.hashCode() : 0);
            hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
            hash = 53 * hash + (this.displayName != null ? this.displayName.hashCode() : 0);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final SourceGroupProxy other = (SourceGroupProxy) obj;
            if(this.sourceGroup != null && other.sourceGroup != null) {
                return this.sourceGroup.getRootFolder().getPath().equals(other.sourceGroup.getRootFolder().getPath());
            }
            if(this.sourceGroup == null && other.sourceGroup == null) {
                return this.displayName.equals(other.displayName);
            }
            return false;
        }
        
        @Override
        public FileObject getRootFolder() {
            if(sourceGroup != null) {
                return sourceGroup.getRootFolder();
            }
            return root;
        }

        @Override
        public String getName() {
            if(sourceGroup != null) {
                return sourceGroup.getName();
            }
            return name;
        }

        @Override
        public String getDisplayName() {
            if(sourceGroup != null) {
                return sourceGroup.getDisplayName();
            }
            return displayName;
        }

        @Override
        public Icon getIcon(boolean opened) {
            if(sourceGroup != null) {
                return sourceGroup.getIcon(opened);
            }
            return opened ? iconTrue : iconFalse;
        }        

        @Override
        public boolean contains(FileObject file) {
            if(sourceGroup != null) {
                return sourceGroup.contains(file);
            }
            return false;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            if(sourceGroup != null) {
                sourceGroup.addPropertyChangeListener(listener);
            }
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {
            if(sourceGroup != null) {
                sourceGroup.removePropertyChangeListener(listener);
            }
        }
    }
    
    /**
     * Displays a {@link SourceGroup} in {@link #rootComboBox}.
     */
    public static final class GroupListCellRenderer extends DefaultListCellRenderer {
        
//        private final JPanel wrapper = new JPanel(new BorderLayout());
        
        public GroupListCellRenderer() {
//            this.init();
        }
        
//        private void init() {
//            wrapper.add(this);
//            wrapper.setOpaque(false);
//        }

        @SuppressWarnings("rawtypes")
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            // #93658: GTK needs name to render cell renderer "natively"
            setName("ComboBox.listRenderer"); // NOI18N
            
            String name;
            Icon icon;
            if (value == null) {
                name = ""; //NOI18N
                icon = null;
            } else {
                assert value instanceof SourceGroupProxy;
                final SourceGroupProxy g = (SourceGroupProxy) value;
                name = g.getDisplayName();
                icon = g.getIcon(false);
            }
            
            setIcon(icon);
            
            super.getListCellRendererComponent(list, name, index, isSelected, cellHasFocus);
            this.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            
            return this;
        }
        
        // #93658: GTK needs name to render cell renderer "natively"
        @Override
        public String getName() {
            final String name = super.getName();
            return name == null ? "ComboBox.renderer" : name;  // NOI18N
        }
    }

    /**
     * Returns source group that contains the file object, or null if no such exists in support
     * @param support
     * @param folder
     * @return 
     */
    public static SourceGroupProxy getContainingSourceGroup(SourceGroupSupport support, FileObject folder) {
        List<SourceGroupProxy> list = null;
        if(folder != null) {
            list = support.getSourceGroups();
            for(SourceGroupProxy p : list) {
                FileObject root = p.getRootFolder();
                if (root != null && (root.equals(folder) || FileUtil.isParentOf(root, folder))) {
                    return p;
                }
            }
        }
        if(list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ComboBoxModel getWaitModel() {
        return new DefaultComboBoxModel(new String[] {
            NbBundle.getMessage(SourceGroupSupport.class, "msg.combobox.model.please.wait")});
    }
    
}
