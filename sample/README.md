# Warden Sample app

The Sample app showcases how to request and handle permission logic from a click listener using
Warden.

As soon as the 'Call' button is clicked, the `CALL_PHONE` is requested.

If the permission is granted, then the phone call takes place.

If the permission is denied, the phone number is dialed instead.

Checkout the [DialerActivity](/sample/src/main/java/com/alexstyl/warden/sample/DialerActivity.kt) to
get started.