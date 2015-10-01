<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

</#if>
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 *
 * @author ${user}
 */
public class ${presenter} implements Initializable {
<#if propertiesinject?? && propertiesinject != "false">
    private ResourceBundle resources = null;

</#if>
    @Override
    public void initialize(URL location, ResourceBundle resources) {
<#if propertiesinject?? && propertiesinject != "false">
        this.resources = resources;
<#else>
        
</#if>
    }

}