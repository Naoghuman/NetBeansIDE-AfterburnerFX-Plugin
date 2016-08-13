How to release a `new` release in NetBeansIDE-AfterburnerFX-Plugin
===

This document describes the steps how to release a `release` in 
NetBeansIDE-AfterburnerFX-Plugin.  



Step 1
---

* All bugs, enhancments, features, refactorings are implemented.
* Update the new release notes from the folder `releases` for the newest 
  (greatest) version.
    * Update the name from the file.
    * Check and/or write the `summary` section in the file.
* Save changes to `GitHub`.



Step 2
---

* Create new `screenshots` from the wizard if needed.
* Save screenshots local to /Projects/NetBeansIDE-AfterburnerFX-Plugin/new-version.
* Save screenshots to `GitHub`.
* Update the `ReadMe.md`.
* Save changes to `GitHub`.



Step 3
---

* Create a new NBM (need password for keystore).
    * See the local file where the informations about this topic are.
* Save NBM local to /Projects/NetBeansIDE-AfterburnerFX-Plugin/XY.



Step 4
---

* Release the new release in `GitHub`.
    * Use the text in the `release notes` for the release message.
    * Upload the NBM in the new release.
* Create a new branch `release_vX.Y`.



Step 5
---

* Upload the new NBM to `NetBeans Portal`.
    * Check and update the `description` and `screenshots`.



Step 6
---

* Prepare the project for the new version.
    * Properties -> API Versioning -> `Specification Version`.
* Prepare the new `release notes` file for the next release.



Step 7
---

* Write message to `Adam Bien` (twitter).
* Write message to `Jonathan Giles` (FXExperience).
* Write `tweats` in twitter.
    - Repeat tweats if the plugin verified in `NetBeans Portal`.
