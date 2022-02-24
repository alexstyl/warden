# Warden

![Android permissions as suspend functions](/assets/banner.png)

Permission handling in Android can be complicated. It requires boilerplate code to setup the
permission request, receive the result and then forward the result to the place of your codebase
where the actual handling logic takes place.

Warden removes all this boilerplate code and enables requesting permissions and receiving the result
from any coroutine in your codebase (such as your ViewModels).

Warden suspend functions block the coroutine until the requested permission is granted or denied.

Warden can be used in any coroutine, using any Context.

## Quick Start

Install Warden via Gradle by adding the dependency in your `app/build.gradle` file:

```gradle
repositories {
  ...
  mavenCentral()
}

dependencies {
    implementation 'com.alexstyl:warden:1.0.0-alpha2'   
}
```

The following example showcases how to use Warden to request the `CALL_PHONE` permission.

```kotlin
coroutineScope.launch {
    val result = Warden.with(anyContext).requestPermission(Manifest.permission.CALL_PHONE)
    when (result) {
        is PermissionState.Denied -> dialNumber(phoneNumber)
        PermissionState.Granted -> startCall(phoneNumber)
    }
}
```

As soon as `requestPermission()` is called, Warden will request for the requested permission and
will block the coroutine until the permission dialog has been interacted.

You can use the `requestPermissions()` suspend function to request multiple permissions at once.

## Sample app

The repository comes with a [sample app](/sample) for an example of usage.

## Getting Help

To report a problem or request a feature, [open a new issue on Github][1].

## License

Apache 2.0. See the [LICENSE](https://github.com/alexstyl/warden/blob/main/LICENSE) file for
details.

## Author

Made by Alex Styl. [Follow @alexstyl](https://www.twitter.com/alexstyl) on Twitter for future
updates.

[1]: https://github.com/alexstyl/warden/issues
