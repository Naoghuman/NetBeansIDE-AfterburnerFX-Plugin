<#assign licenseFirst = "/*">
<#assign licensePrefix = " * ">
<#assign licenseLast = " */">
<#include "${project.licensePath}">

<#if package?? && package != "">
package ${package};

</#if>
import com.airhacks.afterburner.views.FXMLView;

/**
 *
 * @author ${user}
 */
public class ${view} extends FXMLView {

    public ${presenter} getRealPresenter() {
        return (${presenter}) super.getPresenter();
    }

}