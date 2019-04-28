package mainbody;

public class CommonCommands {
	String get_devices_info_command = "devices";
	String restart_app_command = "shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
	String get_machine_id = "shell cat /sdcard/inbox/config/machine_id.txt";
	String list_package_3 = "shell pm list package -3";
	String uninstallString = "uninstall ";
	String remove_apps_dirString = "shell rm -rf /sdcard/inbox/apps";
	String mkdir_apps_dirString = "shell mkdir /sdcard/inbox/apps";
	String pushString = "push ";
	String apps_dirString = " /sdcard/inbox/apps";
	String start_install_activityString = "shell am start com.inhand.install/.InstallActivity";
	String installString = "install -r ";
	String grepString = " grep ";
	String findstrString = " findstr ";
	String dumpsys_packageString = "shell dumpsys package ";
	String symbol_orString = " | ";
	String versionNameString = "versionName";
	String dumpsys_wifi_String = "shell dumpsys wifi ";
	String WiFi_String = "Wi-Fi";
	String dumpsys_telephony_String = "shell dumpsys telephony.registry ";
	String mDataConnectionState_String = "mDataConnectionState";
	String set_wifi_disableString = "shell su 0 svc wifi disable";
	String set_wifi_enableString = "shell su 0 svc wifi enable";
	String set_data_disableString = "shell su 0 svc data disable";
	String set_data_enableString = "shell su 0 svc data enable";
	String pull_smartvm_cfg_xmlString = "pull /sdcard/inbox/config/smartvm_cfg.xml ";
	String pull_config_xmlString = "pull /sdcard/inbox/config/config.xml ";
}
