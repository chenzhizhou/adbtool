package mainbody;

public class CommonCommands {
	String get_devices_info_command = "devices";
	String restart_app_command = "shell am broadcast -a com.inhand.intent.INBOXCORE_RESTART_APP";
	String get_machine_id = "shell cat sdcard/inbox/config/machine_id.txt";
	
}
