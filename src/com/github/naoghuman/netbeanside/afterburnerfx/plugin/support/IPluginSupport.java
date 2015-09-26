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

/**
 *
 * @author PRo
 */
public interface IPluginSupport {
    
    public static final String DEFAULT_MAVEN_FXML_PACKAGE = "fxml"; //NOI18N
    public static final String DEFAULT_MAVEN_IMAGES_PACKAGE = "images"; //NOI18N
    public static final String DEFAULT_MAVEN_CSS_PACKAGE = "styles"; //NOI18N
    
    public static final String MSG_INFO_BASE_NAME_ISNT_VALID = "info.base.name.isnt.valid"; // NOI18N
    public static final String MSG_ERROR_BASE_NAME_CONTAINS_WRONG_FILENAME_CHARS = "error.base.name.contains.wrong.filename.chars"; // NOI18N
    public static final String MSG_ERROR_FILES_ALREADY_EXISTS = "error.files.already.exists"; // NOI18N
    public static final String MSG_ERROR_PACKAGE_ISNT_FOLDER = "error.package.isnt.folder"; // NOI18N
    public static final String MSG_ERROR_PACKAGE_NAME_ISNT_VALID = "error.package.name.isnt.valid"; // NOI18N
    public static final String MSG_ERROR_TARGET_FOLDER_DOESNT_EXISTS = "error.target.folder.doesnt.exists"; // NOI18N
    public static final String MSG_ERROR_TARGET_FOLDER_IS_READONLY = "error.target.folder.is.readonly"; // NOI18N
    public static final String MSG_WARNING_BASE_AND_PACKAGE_NAME_ARENT_EQUALS = "warning.base.and.package.name.arent.equals"; // NOI18N
    
    public static final String PROP_BASENAME = "PROP_BASENAME"; // NOI18N
    public static final String PROP_BASENAME_CHOOSEN = "PROP_BASENAME_CHOOSEN"; // NOI18N
    public static final String PROP_BASENAME_CHOOSEN_DEFAULT_VALUE = "Prefix"; // NOI18N
    public static final String PROP_CHOOSEN_PACKAGE = "PROP_CHOOSEN_PACKAGE"; // NOI18N
    public static final String PROP_CREATE_CSS_FILE = "PROP_CREATE_CSS_FILE"; // NOI18N
    public static final String PROP_CREATE_PROPERTIES_FILE = "PROP_CREATE_PROPERTIES_FILE"; // NOI18N
    public static final String PROP_INJECT_CSS_FILE = "PROP_INJECT_CSS_FILE"; // NOI18N
    public static final String PROP_INJECT_PROPERTIES_FILE = "PROP_INJECT_PROPERTIES_FILE"; // NOI18N
    
    public static final Character SIGN_CHAR_DOT = '.';
    
    public static final String TEMPLATE_PARAMETER__CONTROLLER = "controller"; // NOI18N
    public static final String TEMPLATE_PARAMETER__CSS = "css"; // NOI18N
    public static final String TEMPLATE_PARAMETER__FXML = "fxml"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PRESENTER = "presenter"; // NOI18N
    public static final String TEMPLATE_PARAMETER__PROPERTIES = "properties"; // NOI18N
    public static final String TEMPLATE_PARAMETER__RESOURCES = "resources"; // NOI18N
    public static final String TEMPLATE_PARAMETER__VIEW = "view"; // NOI18N
    
    public static final String TEMPLATE__PREFIX__CSS = "Template.css"; // NOI18N
    public static final String TEMPLATE__PREFIX__FXML = "Template.fxml"; // NOI18N
    public static final String TEMPLATE__PREFIX__PROPERTIES = "Template.properties"; // NOI18N
    public static final String TEMPLATE__PREFIX_PRESENTER__JAVA = "TemplatePresenter.java"; // NOI18N
    public static final String TEMPLATE__PREFIX_VIEW__JAVA = "TemplateView.java"; // NOI18N
    
}
