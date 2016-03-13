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

import java.awt.Color;

/**
 *
 * @author PRo
 */
public interface IPluginSupport {
    
    public static final Color LIGHTGRAY_COLOR = new Color(124, 127, 130);
    
    public static final String DEFAULT_MAVEN_FXML_PACKAGE = "fxml"; //NOI18N
    public static final String DEFAULT_MAVEN_IMAGES_PACKAGE = "images"; //NOI18N
    public static final String DEFAULT_MAVEN_CSS_PACKAGE = "styles"; //NOI18N
    
    public static final String MSG_INFO__FILE_NAME_ISNT_VALID = "info.filename.isnt.valid"; // NOI18N
    public static final String MSG_ERROR__FILE_NAME_CONTAINS_WRONG_CHARS = "error.filename.contains.wrong.chars"; // NOI18N
    public static final String MSG_ERROR__FILES_ALREADY_EXISTS = "error.files.already.exists"; // NOI18N
    public static final String MSG_ERROR__PACKAGE_ISNT_FOLDER = "error.package.isnt.folder"; // NOI18N
    public static final String MSG_ERROR__PACKAGE_NAME_ISNT_VALID = "error.package.name.isnt.valid"; // NOI18N
    public static final String MSG_ERROR__TARGET_FOLDER_DOESNT_EXISTS = "error.target.folder.doesnt.exists"; // NOI18N
    public static final String MSG_ERROR__TARGET_FOLDER_IS_READONLY = "error.target.folder.is.readonly"; // NOI18N
    public static final String MSG_WARNING__FILE_AND_PACKAGE_NAME_ARENT_EQUALS = "warning.file.and.package.name.arent.equals"; // NOI18N
    
    public static final String PROP__CHOOSEN_PACKAGE = "PROP__CHOOSEN_PACKAGE"; // NOI18N
    public static final String PROP__CSS_FILE_SHOULD_CREATE = "PROP__CSS_FILE_SHOULD_CREATE"; // NOI18N
    public static final String PROP__CSS_FILE_SHOULD_INJECT = "PROP__CSS_FILE_SHOULD_INJECT"; // NOI18N
    public static final String PROP__CSS_TO_LOWERCASE = "PROP__CSS_TO_LOWERCASE"; // NOI18N
    public static final String PROP__FILENAME = "PROP__FILENAME"; // NOI18N
    public static final String PROP__FILENAME_CHOOSEN = "PROP__FILENAME_CHOOSEN"; // NOI18N
    public static final String PROP__FILENAME_CHOOSEN_DEFAULT_VALUE = "Prefix"; // NOI18N
    public static final String PROP__FXML_TO_LOWERCASE = "PROP__FXML_TO_LOWERCASE"; // NOI18N
    public static final String PROP__PROPERTIES_FILE_SHOULD_CREATE = "PROP__PROPERTIES_FILE_SHOULD_CREATE"; // NOI18N
    public static final String PROP__PROPERTIES_FILE_SHOULD_INJECT = "PROP__PROPERTIES_FILE_SHOULD_INJECT"; // NOI18N
    public static final String PROP__PROPERTIES_TO_LOWERCASE = "PROP__PROPERTIES_TO_LOWERCASE"; // NOI18N
    public static final String PROP__SHOW_INFORMATION_CREATE_FOLLOWING_FILES = "PROPERTY_SHOW_INFORMATION_CREATE_FOLLOWING_FILES"; // NOI8N
    
    public static final Character SIGN_CHAR_DOT = '.';
    
    public static final String TEMPLATE_FILE__CSS = "Template.css"; // NOI18N
    public static final String TEMPLATE_FILE__FXML = "Template.fxml"; // NOI18N
    public static final String TEMPLATE_FILE__PROPERTIES = "Template.properties"; // NOI18N
    public static final String TEMPLATE_FILE__PRESENTER_JAVA = "TemplatePresenter.java"; // NOI18N
    public static final String TEMPLATE_FILE__VIEW_JAVA = "TemplateView.java"; // NOI18N
    
    public static final String TEMPLATE_PARAMETER__CONTROLLER = "controller"; // NOI18N
    public static final String TEMPLATE_PARAMETER__CSS = "css"; // NOI18N
    public static final String TEMPLATE_PARAMETER__CSS_FILE_INJECT = "cssinject"; // NOI18N
    public static final String TEMPLATE_PARAMETER__FXML = "fxml"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PACKAGE2 = "package2"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PRESENTER = "presenter"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PROPERTIES = "properties"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PROPERTIES_INJECT = "propertiesinject"; // NOI18N
    public static final String TEMPLATE_PARAMETER__VIEW = "view"; // NOI18N
    
}
