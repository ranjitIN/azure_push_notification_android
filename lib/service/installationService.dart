import 'dart:io' show Platform;

import 'package:azurepush/model/deviceInstallation.dart';
import 'package:device_info_plus/device_info_plus.dart';
import 'package:firebase_messaging/firebase_messaging.dart';

class DeviceInstallationService {
  
  Future<String> getPlatform() async {
    String os = Platform.operatingSystem; //in your code
    return os;
  }

  Future<String?> getId() async {
    var deviceInfo = DeviceInfoPlugin();
    if (Platform.isIOS) {
      var iosDeviceInfo = await deviceInfo.iosInfo;
      return iosDeviceInfo.identifierForVendor!; // unique ID on iOS
    } else if (Platform.isAndroid) {
      var androidDeviceInfo = await deviceInfo.androidInfo;
      return androidDeviceInfo.id; // unique ID on Android
    }
  }

  Future<DeviceInstallation> getDeviceInstallation() async {
    DeviceInstallation deviceInstallation = DeviceInstallation(
        await getId() ?? '',
        'fcm',
        await FirebaseMessaging.instance.getToken() ?? '', []);
    return deviceInstallation;
  }
}
