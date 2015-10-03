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
    public void getLastPackageNameAsClassNameNULL() {
        String packageName = null;
        String className = PluginSupport.getLastPackageNameAsClassName(packageName);
        
        assertTrue(className.isEmpty());
    }
    
    @Test
    public void getLastPackageNameAsClassNameTrimEmpty() {
        String packageName = "  "; // NOI18N
        String className = PluginSupport.getLastPackageNameAsClassName(packageName);
        
        assertTrue(className.isEmpty());
    }
    
    @Test
    public void getLastPackageNameAsClassNameWithSimplePackage() {
        String packageName = "package"; // NOI18N
        String className = PluginSupport.getLastPackageNameAsClassName(packageName);
        
        assertTrue(className.equals("Package")); // NOI18N
    }
    
    @Test
    public void getLastPackageNameAsClassNameWithExtendedPackage() {
        String packageName = "org.my.package"; // NOI18N
        String className = PluginSupport.getLastPackageNameAsClassName(packageName);
        
        assertTrue(className.equals("Package")); // NOI18N
    }
    
    @Test
    public void isValidBaseNameNULL() {
        String baseName = null;
        boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameEmpty() {
        String baseName = "";
        boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameTrimEmpty() {
        String baseName = " ";
        boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertFalse(isValid);
    }
    
    @Test
    public void isValidBaseNameXy() {
        String baseName = "xy";
        boolean isValid = PluginSupport.isValidBaseName(baseName);
        
        assertTrue(isValid);
    }
    
}
