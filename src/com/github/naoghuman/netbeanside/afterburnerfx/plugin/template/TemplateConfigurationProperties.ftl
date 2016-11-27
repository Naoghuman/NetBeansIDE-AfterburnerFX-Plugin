<#-- FreeMarker template (see http://freemarker.org/) -->
<#assign licensePrefix = "# ">
<#include "${project.licensePath}">

################################################################################
#
# Access to the parameters in this files can be done via injection in your 
# presenter. Currently dot named keys aren't supported.
#
# Given is the key-value pair:
# parameter=MyParameter
#
# public class ${presenter} implements Initializable {
#
#     @javax.inject.Inject
#     private String parameter;
# 
#     @Override
#     public void initialize(URL location, ResourceBundle resources) {
#         assert (parameter.equals("MyParameter")); // true
#     }
# }
#
################################################################################
