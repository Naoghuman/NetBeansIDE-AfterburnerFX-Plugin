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
package com.github.naoghuman.netbeanside.afterburnerfx.plugin.support;

import java.io.File;
import java.util.StringTokenizer;
import org.netbeans.api.annotations.common.NonNull;
import org.netbeans.api.project.Project;
import org.openide.WizardDescriptor;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

/**
 *
 * @author nadin
 */
public final class PluginSupport implements IPluginSupport {
    
    private static final char[] NO_FILENAME_CHARS = {
        '/', '<', '>', '\\', '|', '\"', '\n', '\r', //NOI18N
        '\t', '\0', '\f', '`', '?', '*', ':' }; //NOI18N
    
    private static boolean existFileName(FileObject targetFolder, String relFileName) {
        final File fold = FileUtil.toFile(targetFolder);
        return fold.exists() ? new File(fold, relFileName).exists() 
                : targetFolder.getFileObject(relFileName) != null;
    }
    
    public static String extractAdditionalInformations(boolean shouldInject, boolean shouldLowercase) {
        final StringBuilder sb = new StringBuilder();
        sb.append(""); // NOI18N
        
        if (shouldInject || shouldLowercase) {
            sb.append(" ("); // NOI18N
            sb.append((shouldInject                   ) ? "injected"  : ""); // NOI18N
            sb.append((shouldInject && shouldLowercase) ? ", "        : ""); // NOI18N
            sb.append((shouldLowercase                ) ? "lowercase" : ""); // NOI18N
            sb.append(")\n"); // NOI18N
        }
        
        return sb.toString();
    }
    
    public static String extractClassNameFromPackage(String packageName) {
        if (packageName == null) {
            return ""; // NOI18N
        }
        
        packageName = packageName.trim();
        if (packageName.isEmpty()) {
            return ""; // NOI18N
        }
        
        packageName = packageName.substring(0, 1).toUpperCase() + packageName.substring(1);
        if (!packageName.contains(".")) { // NOI18N
            return packageName;
        }
        
        int index = packageName.lastIndexOf("."); // NOI18N
        packageName = packageName.substring(index + 1);
        packageName = packageName.substring(0, 1).toUpperCase() + packageName.substring(1);
        
        return packageName;
    }
    
    /**
     * Get a package combo model item for the package the user selected before
     * opening the wizard. May return null if it cannot find it; or a String
     * instance if there is a well-defined package but it is not listed among
     * the packages shown in the list model.
     */
    public static Object getPreselectedPackage(SourceGroupSupport.SourceGroupProxy group, FileObject folder) {
        if (folder == null) {
            return null;
        }

        final FileObject root = group.getRootFolder();
        final String relPath = FileUtil.getRelativePath(root, folder);
        if (relPath == null) {
            // Group Root folder is not a parent of the preselected folder
            // No package should be selected
            return null;
        } else {
            // Find the right item.            
            final String name = relPath.replace('/', '.'); // NOI18N
            return name;
        }
    }

    // helper methods copied and refactored from JavaTargetChooserPanel
    /**
     * Checks if the given file name can be created in the target folder.
     *
     * @param targetFolder target folder (e.g. source group)
     * @param folderName name of the folder relative to target folder
     * @param newObjectName name of created file
     * @param extension extension of created file
     * @return localized error message or null if all right
     */
    public static String canUseFileName(FileObject targetFolder, String folderName, String newObjectName, String extension) {
//        String newObjectNameToDisplay = newObjectName;
        final String fileAndPackageName = newObjectName;
        if (newObjectName != null) {
            newObjectName = newObjectName.replace('.', '/'); // NOI18N
        }
        
        if (extension != null && extension.length() > 0) {
            final StringBuilder sb = new StringBuilder();
            sb.append(newObjectName);
            sb.append('.'); // NOI18N
            sb.append(extension);
            newObjectName = sb.toString();
        }

        final String relFileName = folderName + '/' + newObjectName; // NOI18N

        // test whether the selected folder on selected filesystem already exists
        if (targetFolder == null) {
            return NbBundle.getMessage(PluginSupport.class, MSG_ERROR__TARGET_FOLDER_DOESNT_EXISTS);
        }

        // target package should be writable
        final File targetPackage = folderName != null ? new File(FileUtil.toFile(targetFolder), folderName) 
                : FileUtil.toFile(targetFolder);
        if (targetPackage != null) {
            if (targetPackage.exists() && !targetPackage.canWrite()) {
                return NbBundle.getMessage(PluginSupport.class, MSG_ERROR__TARGET_FOLDER_IS_READONLY);
            }
        } else if (!targetFolder.canWrite()) {
            return NbBundle.getMessage(PluginSupport.class, MSG_ERROR__TARGET_FOLDER_IS_READONLY);
        }

        if (existFileName(targetFolder, relFileName)) {
            String msgFilesAlreadyExists = NbBundle.getMessage(PluginSupport.class, MSG_ERROR__FILES_ALREADY_EXISTS);
            msgFilesAlreadyExists = String.format(msgFilesAlreadyExists, fileAndPackageName);
            
            return msgFilesAlreadyExists;
        }

        // all ok
        return null;
    }
    
    public  static boolean isBaseNameContainsWrongFileNameChars(String baseName) {
        if(!isValidBaseName(baseName)) {
            return false;
        }
        
        for(int i = 0; i < baseName.length(); i++) {
            for(int j = 0; j < NO_FILENAME_CHARS.length; j++) {
                if(baseName.charAt(i) == NO_FILENAME_CHARS[j]) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Checks if the project uses the Maven build system
     * @param prj the project to check
     * @return true if is Maven project
     */
    public static boolean isMavenProject(@NonNull final Project prj) {
        FileObject fo = prj.getProjectDirectory();
        if (fo == null || !fo.isValid()) {
            return false;
        }
        
        fo = fo.getFileObject("pom.xml"); // NOI18N
        if (fo != null) {
            return true;
        }
        
        return false;
    }
    
    public  static boolean isValidBaseName(String baseName) {
        if(baseName == null || baseName.trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    public static boolean checkIfBaseNameEqualsLastPackageName(String baseName, String packageName) {
        final StringTokenizer st = new StringTokenizer(packageName, "."); // NOI18N
        if (!st.hasMoreTokens()) {
            return false;
        }
        
        if (!packageName.toLowerCase().endsWith(baseName.toLowerCase())) {
            return false;
        }
        
        return true;
    }

    public static boolean isLastPackageNameConfiguration(String packageName) {
        boolean isLastPackageNameConfiguration = false;
        if (packageName == null) {
            return isLastPackageNameConfiguration;
        }
        
        if (packageName.toLowerCase().endsWith("configuration")) { // NOI18N
            isLastPackageNameConfiguration = true;
        }
        
        return isLastPackageNameConfiguration;
    }
    
    public static boolean isValidBaseNameAndPackage(String baseName, FileObject root, String packageName) {
        if (!isValidBaseName(baseName)) {
            return false;
        }
        
        if (!isValidPackageName(packageName)) {
            return false;
        }
        
        if (!isValidPackage(root, packageName)) {
            return false;
        }
        
        if (baseName.equalsIgnoreCase(packageName)) {
            return true;
        }
        
        return checkIfBaseNameEqualsLastPackageName(baseName, packageName);
    }
    
    public static boolean isValidPackageName(String packageName) {
        if (packageName == null) {
            return false;
        }
        
        packageName = packageName.trim();
        if (packageName.isEmpty()) {
            return false;
        }
        
        if (packageName.startsWith(".") || packageName.endsWith(".")) { // NOI18N
            return false;
        }
        
        final StringTokenizer st = new StringTokenizer(packageName, "."); // NOI18N
        while (st.hasMoreTokens()) {
            final String token = st.nextToken();
            if (token.isEmpty()) {
                return false;
            }
            
            if (!Utilities.isJavaIdentifier(token)) {
                return false;
            }
        }
        
        return true;
    }

    public static boolean isValidPackage(FileObject root, final String packageName) {
        //May be null when nothing selected in the GUI.
        if (root == null || packageName == null) {
            return false;
        }

        final StringTokenizer st = new StringTokenizer(packageName, "."); // NOI18N
        while (st.hasMoreTokens()) {
            root = root.getFileObject(st.nextToken());
            if (root == null) {
                return true;
            }
            
            if (root.isData()) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void clearMessages(WizardDescriptor wizardDescriptor) {
        wizardDescriptor.getNotificationLineSupport().clearMessages();
    }
    
    public static void setErrorMessage(String key, WizardDescriptor wizardDescriptor) {
        wizardDescriptor.getNotificationLineSupport().setErrorMessage(NbBundle.getMessage(PluginSupport.class, key));
    }

    public static void setInfoMessage(String key, WizardDescriptor wizardDescriptor) {
        wizardDescriptor.getNotificationLineSupport().setInformationMessage(NbBundle.getMessage(PluginSupport.class, key));
    }

    public static void setWarningMessage(String key, WizardDescriptor wizardDescriptor) {
        wizardDescriptor.getNotificationLineSupport().setWarningMessage(NbBundle.getMessage(PluginSupport.class, key));
    }
    
}
