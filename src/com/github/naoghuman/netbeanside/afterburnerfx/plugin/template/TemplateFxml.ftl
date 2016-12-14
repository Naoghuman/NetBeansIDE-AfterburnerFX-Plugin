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
<${layoutcontainer} id="${layoutcontainer}" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8.0.60" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${package}.${controller}">
    <stylesheets> <!-- TODO check the tags 'styleClass' and 'stylesheets' same result? -->
        <URL value="@${css}.css"/>
    </stylesheets>
<#if layoutcontainer?? && layoutcontainer == "GridPane">
    <columnConstraints>
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
    </columnConstraints>
    <rowConstraints>
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
    </rowConstraints>
</#if>
<#else>
<${layoutcontainer} id="${layoutcontainer}" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8.0.60" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${package}.${controller}">
<#if layoutcontainer?? && layoutcontainer == "GridPane">
    <columnConstraints>
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
    </columnConstraints>
    <rowConstraints>
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
    </rowConstraints>
</#if>
</#if>
<#else>
<#if cssinject?? && cssinject != "false">
<${layoutcontainer} id="${layoutcontainer}" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8.0.60" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${controller}">
    <stylesheets>
        <URL value="@${css}.css"/>
    </stylesheets>
<#if layoutcontainer?? && layoutcontainer == "GridPane">
    <columnConstraints>
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
    </columnConstraints>
    <rowConstraints>
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
    </rowConstraints>
</#if>
<#else>
<${layoutcontainer} id="${layoutcontainer}" maxHeight="-Infinity" maxWidth="-Infinity" minHeight="-Infinity" minWidth="-Infinity" prefHeight="400.0" prefWidth="600.0" xmlns="http://javafx.com/javafx/8.0.60" xmlns:fx="http://javafx.com/fxml/1" fx:controller="${controller}">
<#if layoutcontainer?? && layoutcontainer == "GridPane">
    <columnConstraints>
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
        <ColumnConstraints hgrow="SOMETIMES" minWidth="10.0" prefWidth="100.0" />
    </columnConstraints>
    <rowConstraints>
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
        <RowConstraints minHeight="10.0" prefHeight="30.0" vgrow="SOMETIMES" />
    </rowConstraints>
</#if>
</#if>
</#if>
</${layoutcontainer}>
