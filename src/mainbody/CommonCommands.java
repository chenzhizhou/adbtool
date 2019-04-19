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
	String findstrString = " grep ";
	String dumpsys_packageString = "shell dumpsys package ";
	String symbol_orString = " | ";
	String versionNameString = "versionName";
}
