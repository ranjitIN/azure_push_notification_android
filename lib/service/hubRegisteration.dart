import 'dart:convert';

import 'package:azurepush/model/deviceInstallation.dart';
import 'package:permission_handler/permission_handler.dart';
import 'package:http/http.dart' as http;

class HubRegisteration {
  String notificationHubEndPoint = 'https://gen2testapp.azurewebsites.net/api';
  String registerationEndPoint = "Notifications/installations";

  Future<void> registerDevice(
      {required DeviceInstallation deviceInstallation}) async {
    // if (await Permission.notification.request().isGranted) {

    // } else {
    //   await Permission.notification.request();
    // }
   
    String url = "$notificationHubEndPoint/$registerationEndPoint";
    print(url);
   
    var response = await http.put(Uri.parse(url),
        headers: {"Content-Type": "application/json"}, body: jsonEncode(deviceInstallation));

    print(response.statusCode);
    if (response.statusCode == 200) {
      print("registeration succesfull");
      print(response.body);
    } else {
      print(response.body);
      print("unsucessfull");
    }
  }
}
