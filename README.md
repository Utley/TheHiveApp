# Main

TODO: paste in some stuff from our docs about the background and intentions behind this app

# Organization

The main features of our app (as of sprint 4) include listing existing hives,
adding new hives, and adding inspection logs to a hive. 

# File Structure

## Kotlin

(We should really change this from `com.example.thehiveapp_android` to something
more specific...)

* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/data`
  - contains the data management classes, which are written by Zac
* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui`
  - contains several kotlin files which seem to mostly handle user interface
    related tasks. Many of these are just default templates, so we should really
    clean this up (TODO: remove unnecessary classes in the UI folder)
* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/AddHiveForm.kt`
  - default code
* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/MainActivity.kt`
  - default code for the whole activity, not specific to any fragment

### UI

#### hive

The `hive` files manage Realm-related functionality, which manages and displays
our data. (Ask Zac about this.)

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveEditorActivity.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveListFragment.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveListItemView.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveNoteActivity.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveNoteView.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hive/HiveRealmListAdapter.kt`

#### hiveForm

The `HiveForm` fragment (will) carry out functions related to adding and
modifying hive instances.

* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hiveForm/InspectionFormFragment.kt`

* `TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/hiveForm/HiveFormViewModel.kt`

#### home

The `Home` fragment is the landing screen for the app; as of Sprint 4, the plan
is to fill it with some relevant graphs (# frames over time, etc.) .

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/home/HomeFragment.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/home/HomeViewModel.kt`

#### notifications

The `Notifications` fragment was originally part of the template, but we plan on
repurposing it to manage reminders for routine beekeeping tasks such as hive
inspections.

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/notifications/NotificationsFragment.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/notifications/NotificationsViewModel.kt`

#### yard

The `Yard` fragment provides a visual display of the user's beekeeping area,
allowing them to select a hive based on its location rather than its name. This
will not be implemented for a while (as of Sprint 4), so these files will remain
stubs until then.

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/yard/Yard.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/yard/YardView.kt`

`TheHiveApp/app/src/main/java/com/example/thehiveapp_android/ui/yard/YardViewModel.kt`

## Layouts

* `TheHiveApp/app/src/main/res/layout/activity_main.xml`
  - the main layout containing an android
    [NavHostFragment](https://developer.android.com/reference/androidx/navigation/fragment/NavHostFragment)
* `TheHiveApp/app/src/main/res/layout/fragment_add_hive_form.xml`
  - blank fragment, TBD
* `TheHiveApp/app/src/main/res/layout/fragment_dashboard.xml`
  - one of the main tabs containing a `YardView`
* `TheHiveApp/app/src/main/res/layout/fragment_notifications.xml`
  - a main tab that remains from the default android template we used
* `TheHiveApp/app/src/main/res/layout/hive_diagram_fragment.xml`
  - planned feature showing diagram of hives in a yard
* `TheHiveApp/app/src/main/res/layout/hive_form_fragment.xml`
  - adds hives, with inputs for frame count, size, creation date, etc.
* `TheHiveApp/app/src/main/res/layout/hive_selection_list_item.xml`
  - part of hive listing functionality
* `TheHiveApp/app/src/main/res/layout/hive_selection_list_view.xml`
  - part of hive listing functionality
* `TheHiveApp/app/src/main/res/layout/sample_hive_diagram_view.xml`
  - planned feature showing diagram of hives in a yard
