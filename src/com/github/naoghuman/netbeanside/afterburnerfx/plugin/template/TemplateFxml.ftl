<?xml version="1.0" encoding="UTF-8"?>

<?import java.lang.*?>
<#if cssinject?? && cssinject != "false">
    <?import java.net.*?>
</#if>
<?import java.util.*?>
<?import javafx.scene.*?>
<?import javafx.scene.control.*?>
<?import javafx.scene.layout.*?>

<#if package?? && package != "">
<#if cssinject?? && cssinject != "false">
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" styleClass="mainFxmlClass" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${package}.${controller}">
    <stylesheets>
        <URL value="@${css}.css"/>
    </stylesheets>
<#else>
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${package}.${controller}">

</#if>
<#else>
<#if cssinject?? && cssinject != "false">
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" styleClass="mainFxmlClass" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${controller}">
    <stylesheets>
        <URL value="@${css}.css"/>
    </stylesheets>
<#else>
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${controller}">

</#if>
</#if>
</AnchorPane>
