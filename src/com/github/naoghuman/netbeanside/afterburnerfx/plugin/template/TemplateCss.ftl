<#assign licenseFirst = "/*">
<#assign licensePrefix = "">
<#assign licenseLast = "*/">
<#include "${project.licensePath}">

/* 
 * This file can be manually injected into your file ${fxml}.fxml via:
 * 
 * <AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" styleClass="mainFxmlClass" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${package2}.${controller}">
 *     <stylesheets><br>
 *         <URL value="@${css}.css"/>
 *     </stylesheets>
 * </AnchorPane>
 * 
 * The above shown soucecode will be automatically generated if you have the
 * options to create (and to inject) an 'optional .css' file into your 
 * file ${fxml}.fxml selected in the page 'Optional Files'.
 * 
 *  Created on : ${date}, ${time}
 *  Author     : ${user}
 */
