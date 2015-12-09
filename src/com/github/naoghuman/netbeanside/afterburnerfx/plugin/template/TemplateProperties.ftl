<#-- FreeMarker template (see http://freemarker.org/) -->
<#assign licensePrefix = "# ">
<#include "${project.licensePath}">

# Access to the parameters in this files can be done in your presenter via:
#
# public class ${presenter} implements Initializable {
#
#     private ResourceBundle resources = null;
# 
#     @Override
#     public void initialize(URL location, ResourceBundle resources) {
#         this.resources = resources;
#     }
#
# }
#
# The above shown sourcecode will be automatically generated if you have the
# options to create (and to inject) an 'optional .properties' file into your 
# presenter selected in the page 'Optional Files'.
#
