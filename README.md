# Warden ![Featured on Android Weekly](https://androidweekly.net/issues/issue-508/badge)

![Android permissions as suspend functions](/assets/banner.png)

Permission handling in Android can be complicated. It requires boilerplate code to setup the
permission request, receive the result and then forward the result to the place of your codebase
where the actual handling logic takes place.

Warden removes all this boilerplate code and enables requesting permissions and receiving the result
from any coroutine in your codebase (such as your ViewModels).

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

As soon as `requestPermission()` is called, Warden will ask Android for the requested permission and
will suspend the coroutine until the permission dialog has been dismissed.

You can use the `requestPermissions()` suspend function to request multiple permissions at once.

> The `Denied` state contains a `shouldShowRationale` property. When true, it is the right
> time to show an educational message towards why your app requires the said permission.

## Limitations

### Requesting permissions on the background

The way Warden works is via launching an activity which handles the permission requests for you. This is what enables Warden to request permission and receive the results from any part of your app. 

If you need to request permissions while no activity is visible (such as from a service), you might be limited by the [restrictions on starting activities from the background](https://developer.android.com/guide/components/activities/background-starts).

### Orientation changes and process death

Warden assumes that the coroutine context you are requesting the permission from lives long enough until the permission dialog is dismissed.
For the cases the coroutine context doesn't exist, no one will be there to receive the result.

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
