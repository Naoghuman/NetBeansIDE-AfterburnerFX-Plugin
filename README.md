NetBeansIDE-AfterburnerFX-Plugin
===



Intention
===

The `NetBeansIDE-AfterburnerFX-Plugin` is a [NetBeans IDE] plugin which supports
the file generation in convention with the library [afterburner.fx] in a [JavaFX] 
project.

The following files `[prefix].fxml`, `[prefix]Presenter.java`, `[prefix]View.java` 
and *optional* `[prefix].css` and `[prefix].properties` will be created in a new 
wizard where *[prefix]* is your choosen `file and package` name.



Screenshots
===

The following screenshots shows the workflow to create the new 'AfterburnerFX Files' under Windows 7.

##### 1. Open the Wizard
![0-Open-Wizard-Windows7.png][0-Open-Wizard-Windows7]

##### 2. Choose File Type  
![1-Choose-File-Type-Windows7.png][1-Choose-File-Type-Windows7]

##### 3. Name and Location 
![2-Name-And-Location-Windows7.png][2-Name-And-Location-Windows7]

##### 4. Optional Files  
![3-Optional-Files-Windows7.png][3-Optional-Files-Windows7]

##### 5. Summary  
![4-Summary-Window7.png][4-Summary-Window7]

##### 6. Generated files  
![5-Generated-Files-Windows7.png][5-Generated-Files-Windows7]



Content
---

* [Features](#Features)
* [Requirements](#Requirements)
* [Installation](#Installation)
* [Documentation](#Documentation)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Features<a name="Features" />
---

* Allowed the fast generation from the [MVC] (Model-View-Controller) files for 
  your [JavaFX] project within one wizard in convention with the library 
  [afterburner.fx].
* Following files will (can) be created in one workflow with the new wizard:
    * Primary files: `BasicName`.fxml, `BasicName`Presenter.java and `BasicName`View.java 
      where `BasicName` *(lowercase)* must be equals like the last packagename 
      *(lowercase)* ([More informations about the convention JavaFX - afterburner.fx]).
    * Optional files: `BasicName`.css, `BasicName`.properties.
    * The optional files if checked for generation can also injected or not into 
      `BasicName`.fxml and / or `BasicName`Presenter.java (
      [More informations about the injection from Optional Files into Primary Files]).
* The wizard gives the user `feedback` if the choosen `BasicName` and `package name`
  aren't in convention with the library [afterburner.fx].
* Feedback is also given if the choosen `BasicName` and / or `package name` 
  doesn't follow the rules from the [Java Naming Convention].
* The plugin is developed with the [NetBeans IDE] 8.0.2.
* The plugin is a [NetBeans IDE] plugin ![emoticon_smile.png][emoticon_smile].
* The plugin is an [Open Source] project.
* Tested with [JUnit] tests.



Requirements<a name="Requirements" />
---

* You should use [NetBeans IDE] as your development ide ![emoticon_smile.png][emoticon_smile].
* The plugin should be installed in your [NetBeans IDE] ![emoticon_tongue.png][emoticon_tongue].
* In your [JavaFX] project the library [afterburner.fx] should be injected ![emoticon_grin.png][emoticon_grin].



Installation<a name="Installation" />
---

* If not installed download the [JRE 8] or the [JDK 8].
    * Optional: To work better with [FXML] files in a [JavaFX] application download 
      the [JavaFX Scene Builder] in the section 'Additional Resources'.
* If not installed download the [NetBeans IDE].
* Manual installation from the plugin in [NetBeans IDE].
    * Download the plugin [NetBeansIDE-AfterburnerFX-Plugin.nbm].
    * Click in the toolbar from the [NetBeans IDE] on `Tools`, then `Plugins`.
    * In the opened dialog click on the tab 'Downloaded`.
    * Click on the button `Add Plugins...` and select then downloaded plugin.
    * At last click `Install`.
* TODO if UC is okay, then add here the steps to install the plugin throught the UC.



Documentation<a name="Documentation" />
---

* TODO add documentation



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you will report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

* The project `NetBeansIDE-AfterburnerFX-Plugin` is licensed under [General Public License 3.0].
* The smilys icons in this document are from [famfamfam]. Click [famfamfam ReadMe] for more information.



Autor<a name="Autor" />
---

* The project `NetBeansIDE-AfterburnerFX-Plugin` is maintained by me, Peter Rogge. See [Contact](#Contact).



Contact<a name="Contact" />
---

* You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Links)

[afterburner.fx]:https://github.com/AdamBien/afterburner.fx/
[famfamfam]:http://www.famfamfam.com/
[famfamfam ReadMe]:https://github.com/Naoghuman/NetBeansIDE-AfterburnerFX-Plugin/files/7315/readme_famfamfam.txt
[FXML]:http://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[Issue]:https://github.com/Naoghuman/NetbeansIDE-AfterburnerFX-Plugin/issues
[JavaDoc]:http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JavaFX Scene Builder]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[Maven]:http://maven.apache.org/
[NetBeans IDE]:https://netbeans.org/
[NetBeansIDE-AfterburnerFX-Plugin.nbm]:https://github.com/Naoghuman/NetbeansIDE-AfterburnerFX-Plugin/releases
[Pull Request]:https://help.github.com/articles/using-pull-requests



[//]: # (Images)

[0-Open-Wizard-Windows7]:https://cloud.githubusercontent.com/assets/8161815/10226251/ba1e5170-6866-11e5-9fad-44e5ae6ad686.png
[1-Choose-File-Type-Windows7]:https://cloud.githubusercontent.com/assets/8161815/10203035/21c09472-67b4-11e5-92ed-c5826fa52349.png
[2-Name-And-Location-Windows7]:https://cloud.githubusercontent.com/assets/8161815/10203034/21c03e96-67b4-11e5-8eab-fb64eeb3dbfa.png
[3-Optional-Files-Windows7]:https://cloud.githubusercontent.com/assets/8161815/10203031/21bb4d3c-67b4-11e5-8ca8-f98700593e3d.png
[4-Summary-Window7]:https://cloud.githubusercontent.com/assets/8161815/10203032/21bd95c4-67b4-11e5-8b9a-17575955a076.png
[5-Generated-Files-Windows7]:https://cloud.githubusercontent.com/assets/8161815/10203033/21bed0e2-67b4-11e5-9a8f-bcbb7e6a15da.png

[emoticon_smile]:https://cloud.githubusercontent.com/assets/8161815/10268707/76d6c5f2-6ac1-11e5-9330-15a8943f1b0d.png
[emoticon_grin]:https://cloud.githubusercontent.com/assets/8161815/10268709/7b073800-6ac1-11e5-85b3-d0e342acc403.png
[emoticon_tongue]:https://cloud.githubusercontent.com/assets/8161815/10268706/741f41fe-6ac1-11e5-88ea-1b4d807b2283.png
