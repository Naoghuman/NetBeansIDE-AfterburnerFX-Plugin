Welcome to `NetBeansIDE-AfterburnerFX-Plugin` with the new release `v1.3`.



#### Summary
* A new 'Optional File' `configuration.properties` can now created. The properties 
  from this file can access in the `[FileName]Presenter` through injection via 
  `@javax.inject.Inject private String myProperty;`.
* Some minor bugfixes (wrong information msg, wrong background-color) and refactoring
  (update descriptions, new screenshots).



#### New



#### Enhancement
#42 [Documentation] Add hint to ReadMe for the video from Adam Bien where he mention my plugin.
#38 [Documentation] Update ReadMe to reflect the new features.
#17 [Optional Files] Add functionality for creating/checking a 'configuration.properties'.



#### Feature



#### Bug
#36 [Primary Files] Wrong FileName + action from CheckBox shows info in TextArea.
#34 [Primary Files] Background color from CheckBox to LowerCase is wrong when opening the dialog again.
#31 [Optional Files] Background color from CheckBoxes file should injected is wrong.



#### Refactoring
#39 [Wizard] Add screenshot for the page 'Choose File Typ...'
#37 [Wizard] Add new screenshots for v1.3.
#35 [Wizard] Change description from the CheckBox xy to LowerCase.
#32 [Optional Files] Refactore the description from the CheckBox to create a .properties file.
#30 [Optional Files] Move the CheckBoxes to lowercase to bottom.
#29 [Optional Files] Change description from CheckBox for injecting automatically into the file.



#### Additional



[//]: # (Issues which will be integrated in this release)
