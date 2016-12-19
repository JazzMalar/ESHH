package ch.ffhs.eshh.wakeuplight.notused;

import java.util.HashMap;
import java.util.Map;

import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;
import ch.ffhs.eshh.wakeuplight.model.Alarm;
import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;

public enum WakeUpLightDAO
{
	instance;

	private Map<Integer, Alarm> alarms = new HashMap<>();
	private Map<String, Device> devices = new HashMap<>();
	private Map<String, DeviceAction> deviceActions = new HashMap<>();
	private Map<Integer, ActionGroupMember> actionGroupMembers = new HashMap<>();

	private WakeUpLightDAO()
	{
		Device dev1 = new Device("WS2801", "WS2801_01", true);
		Device dev2 = new Device("LIFX", "LIFX_01", false);
		devices.put("WS2801_01", dev1);
		devices.put("LIFX_01", dev2);

		// List<DeviceActionField> fieldList1 = new
		// ArrayList<DeviceActionField>();
		// fieldList1.add(new DeviceActionField<Integer>("LuxStart", 0));
		// fieldList1.add(new DeviceActionField<Integer>("LuxEnd", 255));
		// fieldList1.add(new DeviceActionField<Integer>("ColorStart", 0));
		// fieldList1.add(new DeviceActionField<Integer>("ColorEnd", 255));
		// fieldList1.add(new DeviceActionField<Integer>("NumLeds", 12));
		//
		// List<DeviceActionField> fieldList2 = new
		// ArrayList<DeviceActionField>();
		// fieldList2.add(new DeviceActionField<Integer>("LightStrength", 230));
		// fieldList2.add(new DeviceActionField<String>("Identifier",
		// "Kueche1"));
		//
		// List<DeviceActionField> fieldList3 = new
		// ArrayList<DeviceActionField>();
		// fieldList2.add(new DeviceActionField<Integer>("LightStrength", 123));
		// fieldList2.add(new DeviceActionField<String>("Identifier",
		// "Kueche2"));
		//
		// DeviceAction devAct1 = new DeviceAction("WS2801_01", 0, 5,
		// fieldList1);
		// DeviceAction devAct2 = new DeviceAction("LIFX01_01", 0, 2,
		// fieldList2);
		// DeviceAction devAct3 = new DeviceAction("LIFX01_01", 1, 2,
		// fieldList3);

		// deviceActions.put("WS2801_01", devAct1);
		// deviceActions.put("LIFX01_01", devAct2);
		// deviceActions.put("LIFX01_01", devAct3);
		//
		// // int groupId, int deviceId, int actionId, int offset
		// ActionGroupMember actMem1 = new ActionGroupMember(0, 0, 0, 0);
		// ActionGroupMember actMem2 = new ActionGroupMember(0, 1, 1, 20);
		// ActionGroupMember actMem3 = new ActionGroupMember(1, 1, 1, 15);
		//
		// actionGroupMembers.put(0, actMem1);
		// actionGroupMembers.put(1, actMem2);
		// actionGroupMembers.put(2, actMem3);
		//
		// // StartTime, Offset, RepeatPattern,Active, ActionGroup
		// Alarm alm1 = new Alarm(Time.valueOf(LocalTime.of(8, 0)), 0,
		// "1110000", true, 0);
		// Alarm alm2 = new Alarm(Time.valueOf(LocalTime.of(10, 0)), 0,
		// "0000011", true, 1);
		//
		// alarms.put(0, alm1);
		// alarms.put(1, alm2);

	}

	public Map<Integer, Alarm> getAlarms()
	{
		return alarms;
	}

	public Map<String, Device> getDevices()
	{
		return devices;
	}

	public Map<String, DeviceAction> getDeviceActions()
	{
		return deviceActions;
	}

	public Map<Integer, ActionGroupMember> getActionGroupMembers()
	{
		return actionGroupMembers;
	}
}
