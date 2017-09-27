## Android Jumpstart

[![Build Status](http://jenkins.moldedbits.com/buildStatus/icon?job=Android%20Jumpstart)](http://jenkins.moldedbits.com/job/Android%20Jumpstart/)

With Android Jumpstart, we want to zero down the scaffolding time for Android apps. Most apps we
work on share the same boilerplate code, including
  * Declaring dependencies
  * Auto-increment version code
  * Setting up [Retrofit](http://square.github.io/retrofit/) for API interaction
  * Setting up [Dagger](https://github.com/google/dagger) for dependency injection
  * Setting up common utility classes
  * Creating a mocking framework for testing

Jumpstart takes care of all this, and more. Jumpstart adds support for many commonly used libraries,
  * [RxJava 2.0](https://github.com/ReactiveX/RxJava)
  * [Retrofit](http://square.github.io/retrofit/)
  * [Lombok](https://projectlombok.org/)
  * [Timber](https://github.com/JakeWharton/timber)
  * [ButterKnife](https://jakewharton.github.io/butterknife/)

### Setting up

Setting up is really easy, there's a python script
[here](https://github.com/moldedbits/JumpstartScript) that clones the Jumpstart repo, changes the
project name and package name. All the developer needs to after this is the update the API urls
and they have a base project ready.

### How to use

#### BaseActivity
Make your activities extend one of `BaseActivity`, `BaseToolbarActivity` or `BaseDrawerActivity`,
(all of which subclass `AppCompatActivity`) depending on your requirements. This gives you access
to the `APIService` which is injected into the BaseActivities.

```java
apiService.getSomeResource();
```

#### Setting up the Navigation Drawer
The BaseDrawerActivity handles the Navigation Drawer setup, and the DrawerActionToggle. You can
customize the drawer menu by modifying the file `nav_drawer_menu.xml`

#### Setting up the toolbar
The BaseToolbarActivity handles setting up the toolbar and the home as up button. Simply have your
classes extend this and there's no need to worry about the toolbar.

TODO Add information about FragmentTransactionHandler

#### BaseFragment
Similar to `BaseActivity`, extending `BaseFragment` gives you access to `apiService`. It also adds
utility methods to show and hide progress dialogs.

```java
// Show loading dialog
showLoadingDialog(R.string.loading_message);

// Hide loading dialog
cancelLoadingDialog();
```

#### LoadingDialog
A very common requirement in an app is to show a loading dialog. When you just need to show a
simple dialog with a title and a message, use the LoadingDialog class as

```java
LoadingDialog.newInstance(title, description, showCancelButton);
```

#### APIProvider
The retrofit configuration is done here.

#### Build configurations
Jumpstart adds four build types by default, _debug_, _debugProd_, _release_, _releaseProd_. You
should configure these as per your requirements.