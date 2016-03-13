NetBeansIDE-AfterburnerFX-Plugin
===



Intention
===

The `NetBeansIDE-AfterburnerFX-Plugin` is a [NetBeans IDE] plugin which supports
the file generation in **convention** with the library [afterburner.fx] in a [JavaFX] 
project.

The following primary files `[FileName].fxml`, `[FileName]Presenter.java`, `[FileName]View.java` 
and optional `[FileName].css` and `[FileName].properties` can be created in a new wizard.  
One conditional is that *[FileName].toLowerCase()* must be **equals** with the *last* choosen package name.

**Press**  
`Interview` with `Adam Bien` about me and the plugin: [afterburner.fx NetBeans plugin release - an interview]


Content
===

* [Screenshots](#Screenshots)
* [Features](#Features)
    * [General](#General)
    * [Generated Files](#GeneratedFiles)
    * [Injection from the Optional Files](#InjectionOptionalFiles)
    * [Feedback](#Feedback)
* [Requirements](#Requirements)
* [Installation](#Installation)
    * [General installation](#GeneralInstallation)
    * [Manual installation from the plugin in NetBeans IDE](#ManualInstallation)
    * [Installation through the Update Center from NetBeans IDE](#InstallationUpdateCenter)
* [Contribution](#Contribution)
* [License](#License)
* [Autor](#Autor)
* [Contact](#Contact)



Screenshots<a name="Screenshots" />
---

The following screenshots shows the workflow to create the new 'AfterburnerFX Files'. 
The screenshots are taken under [Windows 10].

##### 1. Open the Wizard
![plugin-1-open.png][plugin-1-open]

##### 2. Choose File Type  
![plugin-2-file-type.png][plugin-2-file-type]

##### 3. Primary Files 
![plugin-3-primary-files.png][plugin-3-primary-files]

##### 4. Optional Files  
![plugin-4-optional-files.png][plugin-4-optional-files]

##### 5. Summary  
![plugin-5-summary.png][plugin-5-summary]

##### 6. Generated files  
![plugin-6-generated-files.png][plugin-6-generated-files]



Features<a name="Features" />
---

##### General<a name="General" />
* The plugin create a new wizard in [NetBeans IDE] which allowed the fast generation 
  from the [Model-View-Controller] files for your [JavaFX] project in convention with 
  the library [afterburner.fx].
* The plugin is developed with the [NetBeans IDE] 8.0.2.
* The plugin is a [NetBeans IDE] plugin ![emoticon_smile.png][emoticon_smile].
* The plugin is an [Open Source] project.
* The plugin is tested with [JUnit] tests.


##### Generated Files<a name="GeneratedFiles" />
Following files can be created in the new wizard:
* Primary files are `[FileName].fxml`, `[FileName]Presenter.java` and 
  `[FileName]View.java` where *[FileName].toLowerCase()* must be **equals** with 
  the *last* choosen package name.
    * Additional option `[FileName].fxml` to lowercase.
* Optional files are `[FileName]`.css, `[FileName]`.properties.
    * Additional option `[FileName].css` and `[FileName].properties` to lowercase.
* The optional files can also additional injected into following files:
    * The **.css** file can additional injected into the `[FileName]`.fxml file.
    * The **.properties** can additional injected into into the `[FileName]Presenter.java` file.


##### Injection from the Optional Files<a name="InjectionOptionalFiles" />
Comparison with/without injection from the **Optional File** `ImportDialog.css` 
in `ImportDialog.fxml`.

```java
Without injection:
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" xmlns:fx="http://javafx.com/fxml/1" fx:controller="org.my.demoapplication.importdialog.ImportdialogPresenter">

</AnchorPane>
```


```java
With injection:
<AnchorPane id="AnchorPane" prefHeight="400.0" prefWidth="600.0" styleClass="mainFxmlClass" xmlns:fx="http://javafx.com/fxml/1" fx:controller="org.my.demoapplication.importdialog.ImportdialogPresenter">
    <stylesheets>
        <URL value="@Importdialog.css"/>
    </stylesheets>
</AnchorPane>
```

Comparison with/without injection from the **Optional File** `ImportDialog.properties` 
in ImportDialogPresenter.java`.

```java
Without injection:
public class ImportdialogPresenter implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}
```


```java
With injection:
public class ImportdialogPresenter implements Initializable {

    private ResourceBundle resources = null;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
    }
}
```


##### Feedback<a name="Feedback" />
* The wizard gives the user `feedback` if the choosen `[FileName]` and `package name`
  aren't in convention with the library [afterburner.fx]. The *[FileName].toLowerCase()* 
  and the last choosen *package name* must be **equals**.
* Feedback is also given if the choosen `[FileName]` and / or `package name` doesn't 
  follow the rules from the [Java Naming Convention].



Requirements<a name="Requirements" />
---

* On your system you need [JRE 8] or [JDK 8] installed.
* You should use [NetBeans IDE] as your development ide ![emoticon_smile.png][emoticon_smile].
* The plugin should be installed in your [NetBeans IDE] ![emoticon_grin.png][emoticon_grin].
* In your [JavaFX] project the library [afterburner.fx] should be injected 
  ![emoticon_tongue.png][emoticon_tongue].



Installation<a name="Installation" />
---

##### General installation<a name="GeneralInstallation" />
* If not installed download the [JRE 8] or the [JDK 8].
    * Optional: To work better with [FXML] files in a [JavaFX] application download 
      the [JavaFX Scene Builder] in the section 'Additional Resources'.
* If not installed download the [NetBeans IDE].


##### Manual installation from the plugin in [NetBeans IDE]<a name="ManualInstallation" />
* Download the plugin from here [NetBeansIDE-AfterburnerFX-Plugin.nbm (GitHub)]
  or from here [NetBeansIDE-AfterburnerFX-Plugin.nbm (NetBeans Plugin Portal)].
* Click in the toolbar from the [NetBeans IDE] on `Tools`, then `Plugins`.
* In the opened dialog click on the tab 'Downloaded`.
* Click on the button `Add Plugins...` and select then downloaded plugin.
* Clicking `Install` will prompt the dialog `Verify Certifcate`.
    * Optional: You can view the certificate with the button `Show certificate`.
    * Click the button `Continue` for installing the plugin.


##### Installation through the `Update Center` from [NetBeans IDE]<a name="InstallationUpdateCenter" />
* Use `Tool` -> `Plugins` action in the IDE.
* Click on the tab `Available Plugins` and then `Check for Newest`.
* Search and checked the plugin in the list from available plugins.
* Clicking `Install` will prompt the dialog `Verify Certifcate`.
    * Optional: You can view the certificate with the button `Show certificate`.
    * Click the button `Continue` for installing the plugin.



Contribution<a name="Contribution" />
---

* If you find a `Bug` I will be glad if you will report an [Issue].
* If you want to contribute to the project plz fork the project and do a [Pull Request].



License<a name="License" />
---

##### Project
* The project `NetBeansIDE-AfterburnerFX-Plugin` is licensed under [General Public License 3.0].


##### Images
* The `smilys` in this document are from [famfamfam]. Click [famfamfam ReadMe] 
  for more information.
* The action icon is the changed [NetBeans IDE] `class.png` icon from the modul
  `java.source`.



Autor<a name="Autor" />
---

* The project `NetBeansIDE-AfterburnerFX-Plugin` is maintained by me, Peter Rogge. 
  See [Contact](#Contact).



Contact<a name="Contact" />
---

* You can reach me under <peter.rogge@yahoo.de>.



[//]: # (Links)

[afterburner.fx]:https://github.com/AdamBien/afterburner.fx/
[famfamfam]:http://www.famfamfam.com/
[famfamfam ReadMe]:https://github.com/Naoghuman/NetBeansIDE-AfterburnerFX-Plugin/files/7315/readme_famfamfam.txt
[FXML]:http://docs.oracle.com/javafx/2/fxml_get_started/jfxpub-fxml_get_started.htm
[General Public License 3.0]:http://www.gnu.org/licenses/gpl-3.0.en.html
[afterburner.fx NetBeans plugin release - an interview]:http://www.adam-bien.com/roller/abien/entry/afterburner_fx_netbeans_plugin_release
[Issue]:https://github.com/Naoghuman/NetbeansIDE-AfterburnerFX-Plugin/issues
[JavaDoc]:http://www.oracle.com/technetwork/java/javase/documentation/index-jsp-135444.html
[JavaFX]:http://docs.oracle.com/javase/8/javase-clienttechnologies.htm
[JavaFX Scene Builder]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Java Naming Convention]:http://www.oracle.com/technetwork/java/codeconventions-135099.html
[JDK 8]:http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
[JRE 8]:http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html
[JUnit]:http://junit.org/
[Maven]:http://maven.apache.org/
[MIT License]:https://opensource.org/licenses/MIT
[Model-View-Controller]:https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller
[NetBeans IDE]:https://netbeans.org/
[NetBeansIDE-AfterburnerFX-Plugin.nbm (GitHub)]:https://github.com/Naoghuman/NetbeansIDE-AfterburnerFX-Plugin/releases
[NetBeansIDE-AfterburnerFX-Plugin.nbm (NetBeans Plugin Portal)]:http://plugins.netbeans.org/plugin/60847/netbeanside-afterburnerfx-plugin
[Open Source]:https://en.wikipedia.org/wiki/Open_source
[Pull Request]:https://help.github.com/articles/using-pull-requests
[Windows 7]:https://de.wikipedia.org/wiki/Microsoft_Windows_7



[//]: # (Images)

[plugin-1-open]:https://cloud.githubusercontent.com/assets/8161815/12529507/70ffbc3e-c1b9-11e5-97c5-d611fec69af9.png
[plugin-2-file-type]:https://cloud.githubusercontent.com/assets/8161815/12529508/743b7424-c1b9-11e5-85ac-d767f8cb2d70.png
[plugin-3-primary-files]:https://cloud.githubusercontent.com/assets/8161815/12529509/765e15a4-c1b9-11e5-8654-86a07535d5af.png
[plugin-4-optional-files]:https://cloud.githubusercontent.com/assets/8161815/12529510/783a2d04-c1b9-11e5-90e4-2a5e0f030a8e.png
[plugin-5-summary]:https://cloud.githubusercontent.com/assets/8161815/12529512/7a1f831c-c1b9-11e5-9a2c-4684e05a9f4e.png
[plugin-6-generated-files]:https://cloud.githubusercontent.com/assets/8161815/12529513/7bc02636-c1b9-11e5-9e5d-ed815fa17749.png

[emoticon_smile]:https://cloud.githubusercontent.com/assets/8161815/10268707/76d6c5f2-6ac1-11e5-9330-15a8943f1b0d.png
[emoticon_grin]:https://cloud.githubusercontent.com/assets/8161815/10268709/7b073800-6ac1-11e5-85b3-d0e342acc403.png
[emoticon_tongue]:https://cloud.githubusercontent.com/assets/8161815/10268706/741f41fe-6ac1-11e5-88ea-1b4d807b2283.png
[iconfinder]:https://www.iconfinder.com/icons/211625/at_icon#size=16
