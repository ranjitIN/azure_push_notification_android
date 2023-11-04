package com.example.azurepush

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.annotation.NonNull
import com.microsoft.windowsazure.messaging.NotificationHub
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.firebase.core.GeneratedAndroidFirebaseCore.PigeonFirebaseOptions.Builder


class MainActivity: FlutterActivity() {
    private val CHANNEL = "samples.flutter.dev/battery";
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
                call, result ->
            if (call.method == "azureRegisteration") {
                print("registering")
                val registerationId = azureRegisteration();
            }
            else {
                result.notImplemented()
            }
        }
    }

    private fun azureRegisteration(): String{
      try {
          val policy = ThreadPolicy.Builder().permitAll().build()
          StrictMode.setThreadPolicy(policy)
          val HubName = "HUB34454"
          val HubListenConnectionString = "Endpoint=sb://newNS1111.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=mSk/09fi+4kaEE/svX6hivDJxZeuaNClVjCduMjU8no="
          val hub = NotificationHub(
              HubName,
              HubListenConnectionString, this
          )
          var regID = hub.register("fpyT_c3FQa6-MSlKHDyA_b:APA91bGMZ0CLm-Fd-X6gQmRxdcfpo9BF4qt55Vhp4BaeLmEWlPe8HEMPHW4omVOxqnbT_gtGWwGw1-ht1AavgksogiqDMiEaImMKTn7incGK71PNeJRVPykM7CGsLGXL1jOw3Rh3D0AE").getRegistrationId();
          print(regID)
          return regID
      }catch (e:java.lang.Exception)
      {
          print(e)
          return "error"
      }
    }
}
