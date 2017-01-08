package ch.ffhs.eshh.wakeuplight.interfaces;

import java.util.List;

import ch.ffhs.eshh.wakeuplight.model.ActionGroup;
import ch.ffhs.eshh.wakeuplight.model.Alarm;
import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;

public interface IDBProxy
{
	ActionGroup GetActionGroup(int groupId);

	List<ActionGroup> GetAllActionGroups();

	void AddActionGroup(ActionGroup actionGroup);

	void RemoveActionGroup(int groupId);

	void RemoveActionGroupMember(int groupId, int deviceId);

	void RemoveActionGroupMemberFromAllGroups(int deviceId);

	DeviceAction GetDeviceAction(String stringId, int id);

	void AddDeviceAction(DeviceAction deviceAction);

	void RemoveDeviceAction(String stringId, int id);

	Alarm GetAlarm(int alarmId);

	List<Alarm> GetAllAlarms();

	void AddAlarm(Alarm newAlarm);

	void RemoveAlarm(int alarmId);

	Device GetDevice(String stringId);

	Device GetDevice(int deviceId);

	List<Device> GetAllDevices();

	void AddDevice(Device newDevice);

	void RemoveDevice(String stringId);

	void RemoveDevice(int deviceId);

	ActionGroup ActivateNightLight(String stringId);

	ActionGroup DisableNightLight(String stringId);

	void ActivateActionGroup(int groupId);

	void DisableActionGroup(int groupId);

	List<ActionGroup> GetActiveNightLights();

	List<ActionGroup> GetActiveActionGroups();

}
