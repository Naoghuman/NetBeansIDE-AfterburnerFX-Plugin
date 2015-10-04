/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.naoghuman.netbeanside.afterburnerfx.plugin.support;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PRo
 */
public class PluginSupportTest {
    
    public PluginSupportTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void extractClassNameFromPackageWithNULL() {
        final String packageName = null;
        final String className = PluginSupport.extractClassNameFromPackage(packageName);
        
        assertTrue(className.isEmpty());
    }
    
    @Test
    public void extractClassNameFromPackageWithTrimEmpty() {
        final String packageName = "  "; // NOI18N
        final String className = PluginSupport.extractClassNameFromPackage(packageName);
        
        assertTrue(className.isEmpty());
    }
    
    @Test
    public void extractClassNameFromPackageWithSimplePackage() {
        final String packageName = "package"; // NOI18N
        final String className = PluginSupport.extractClassNameFromPackage(packageName);
        
        assertTrue(className.equals("Package")); // NOI18N
    }
    
    @Test
    public void extractClassNameFromPackageWithExtendedPackage() {
        final String packageName = "org.my.package"; // NOI18N
        final String className = PluginSupport.extractClassNameFromPackage(packageName);
        
        assertTrue(className.equals("Package")); // NOI18N
    }
    
    @Test
    public void isValidBaseNameNULL() {
        final String baseName = null;
        final boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameEmpty() {
        final String baseName = ""; // NOI18N
        final boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameTrimEmpty() {
        final String baseName = " "; // NOI18N
        final boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameXy() {
        final String baseName = "xy"; // NOI18N
        final boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertTrue(isValid);
    }
    
    @Test
    public void isValidPackageNameNULL() {
        final String packageName = null;
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameEmpty() {
        final String packageName = "";  // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameTrimEmpty() {
        final String packageName = " ";  // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameStartWithDot() {
        final String packageName = ".org.my.package";  // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameEndWithDot() {
        final String packageName = "org.my.package.";  // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameSimplePackage() {
        final String packageName = "mypackage"; // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertTrue(isValid);
    }
    
    @Test
    public void isValidPackageNameSimplePackageJavaKeyWord() {
        final String packageName = "package"; // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidPackageNameExtendedPackage() {
        final String packageName = "org.mypackage"; // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertTrue(isValid);
    }
    
    @Test
    public void isValidPackageNameExtendedPackageJavaKeyWord() {
        final String packageName = "org.package"; // NOI18N
        final boolean isValid = PluginSupport.isValidPackageName(packageName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void checkIfBaseNameEqualsLastPackageNameIsFalse1() {
        final String baseName = "package1"; // NOI18N
        final String packageName = "package"; // NOI18N
        
        final boolean isValid = PluginSupport.checkIfBaseNameEqualsLastPackageName(baseName, packageName);
        assertFalse(isValid);
    }
    
    @Test
    public void checkIfBaseNameEqualsLastPackageNameIsFalse2() {
        final String baseName = "package1"; // NOI18N
        final String packageName = "org.package"; // NOI18N
        
        final boolean isValid = PluginSupport.checkIfBaseNameEqualsLastPackageName(baseName, packageName);
        assertFalse(isValid);
    }
    
    @Test
    public void checkIfBaseNameEqualsLastPackageNameIsTrue1() {
        final String baseName = "package"; // NOI18N
        final String packageName = "package"; // NOI18N
        
        final boolean isValid = PluginSupport.checkIfBaseNameEqualsLastPackageName(baseName, packageName);
        assertTrue(isValid);
    }
    
    @Test
    public void checkIfBaseNameEqualsLastPackageNameIsTrue2() {
        final String baseName = "package"; // NOI18N
        final String packageName = "org.package"; // NOI18N
        
        final boolean isValid = PluginSupport.checkIfBaseNameEqualsLastPackageName(baseName, packageName);
        assertTrue(isValid);
    }
    
}
