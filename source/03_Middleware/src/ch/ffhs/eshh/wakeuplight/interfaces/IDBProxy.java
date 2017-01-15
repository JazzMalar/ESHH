/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Z�ger & Markus Schenk
 * IDBProxy.java
 * Interface dass die Methoden vorgibt, die ein DBProxy zwischen DB und API beherrschen muss. 
 */
package ch.ffhs.eshh.wakeuplight.interfaces;

import java.util.List;

import ch.ffhs.eshh.wakeuplight.model.ActionGroup;
import ch.ffhs.eshh.wakeuplight.model.Alarm;
import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;

public interface IDBProxy
{
	/**
	 * Gibt die ActionGroup zur�ck, die mit dieser groupId assoziert ist.
	 * @param groupId - zu selektierende Gruppe 
	 * @return ActionGroup der selektierten Gruppe
	 */
	ActionGroup GetActionGroup(int groupId);

	/**
	 * Gibt alle ActionGroups zur�ck, die zurzeit im System erfasst sind. 
	 * @return Liste von allen ActionGroups
	 */
	List<ActionGroup> GetAllActionGroups();

	/**
	 * F�gt dem System eine ActionGroup hinzu. 
	 * @param actionGroup Hinzuzuf�gende Gruppe. Muss ActionGroupMembers enthalten.
	 */
	void AddActionGroup(ActionGroup actionGroup);

	/**
	 * L�scht eine ActionGroup aus dem System
	 * @param groupId - zu l�schende ActionGroup
	 */
	void RemoveActionGroup(int groupId);

	/**
	 * L�scht einen einzelnen Member (anzusprechendes Device) aus der ActionGroup
	 * @param groupId - betroffene ActionGroup
	 * @param deviceId - zu l�schendes Ger�t 
	 */
	void RemoveActionGroupMember(int groupId, int deviceId);

	/**
	 * L�scht das angegebene Ger�t aus allen ActionGroups im System. 
	 * @param deviceId - zu l�schendes Ger�t
	 */
	void RemoveActionGroupMemberFromAllGroups(int deviceId);

	/**
	 * Gibt eine spezifische Ger�teaktion zur�ck. 
	 * @param stringId - String-Identifier f�r das Ger�t
	 * @param id - Eindeutige ID, die die gew�nschte Aktion referenziert
	 * @return Parameter f�r das Ger�t, die mit dieser Aktion assoziiert sind als DeviceAction
	 */
	DeviceAction GetDeviceAction(String stringId, int id);

	/**
	 * F�gt ein Set von Parametern als neue DeviceAction zu einem Ger�t hinzu. 
	 * @param deviceAction - Die hinzuzuf�gende Aktion (das betroffene Ger�t wird dieser DeviceAction entnommen)
	 */
	void AddDeviceAction(DeviceAction deviceAction);

	/**
	 * L�scht eine spezifische Ger�teaktion aus dem System
	 * @param stringId - String-Identifier f�r das Ger�t
	 * @param id - Eindeutige ID der Aktion, die gel�scht werden soll. 
	 */
	void RemoveDeviceAction(String stringId, int id);

	/**
	 * Gibt einen spezifischen Alarm (Task) zur�ck
	 * @param alarmId - der zur�ckzugebende Alarm
	 * @return Einzelner Alarm
	 */
	Alarm GetAlarm(int alarmId);

	/**
	 * Gibt alle definierten Alarme (Task) zur�ck
	 * @return Alle definierten Alarme
	 */
	List<Alarm> GetAllAlarms();

	/**
	 * F�gt dem System einen neuen Alarm hinzu
	 * @param newAlarm - der hinzuzuf�gende Alarm
	 */
	void AddAlarm(Alarm newAlarm);

	/**
	 * L�scht einen Alarm aus dem System
	 * @param alarmId - Referenz auf den zu l�schenden Alarm
	 */
	void RemoveAlarm(int alarmId);

	/**
	 * gibt ein Ger�t aus der Device-Tabelle anhand des String-Identifiers zur�ck
	 * @param stringId - Eindeutiger String, der das Ger�t identifiziert
	 * @return Gefundenes Ger�t oder null
	 */
	Device GetDevice(String stringId);

	/**
	 * Gibt ein Ger�t aus der Device-Tabelle anhand der eindeutigen ID zur�ck
	 * @param deviceId - Endeutige ID des Ger�ts
	 * @return Gefundenes Ger�t oder Null
	 */
	Device GetDevice(int deviceId);

	/**
	 * Gibt alle Ger�te aus dem System zur�ck
	 * @return Liste aller Ger�te
	 */
	List<Device> GetAllDevices();

	/**
	 * F�gt dem System ein neues Ger�t hinzu
	 * @param newDevice - Das hinzuzuf�gende Ger�t
	 */
	void AddDevice(Device newDevice);

	/**
	 * L�scht ein Ger�t aus dem System anhand seines Text-Identifiers
	 * @param stringId - Eindeutiger String, der das Ger�t identifiziert
	 */
	void RemoveDevice(String stringId);

	/**
	 * L�scht ein Ger�t aus dem System anhand seiner eindeutigen ID
	 * @param deviceId - Eindeutige ID, die das Ger�t identifiziert
	 */
	void RemoveDevice(int deviceId);

	/**
	 * Aktiviert die Nachtlichtfunktion auf dem angegebenen Ger�t
	 * @param stringId - Das Ger�t auf dem die Nachtlichtfunktion aktiviert wreden soll
	 * @return Die damit aktivierte ActionGroup, die die zu aktivierenden Ger�te und die Ger�teaktionen enth�lt
	 */
	ActionGroup ActivateNightLight(String stringId);

	/**
	 * Deaktiviert die Nachtlichtfunktion auf dem angegebenen Ger�t
	 * @param stringId - Das Ger�t auf dem die Nachtlichtfuntkion deaktiviert werden soll
	 * @return Die damit deaktivierte ActionGroup
	 */
	ActionGroup DisableNightLight(String stringId);

	/**
	 * Markiert eine frei w�hlbare ActionGroup als Aktiv
	 * @param groupId - zu aktivierende ActionGroup
	 */
	void ActivateActionGroup(int groupId);

	/**
	 * Markiert eine frei w�hlbare ActionGroup als Inaktiv
	 * @param groupId - zu deaktivierende ActionGroup
	 */
	void DisableActionGroup(int groupId);

	/**
	 * Gibt alle aktiven Nachtlichter zur�ck
	 * @return liste von aktiven Nachtlichtern
	 */
	List<ActionGroup> GetActiveNightLights();

	/**
	 * Gibt alle aktiven ActionGroups zur�ck. ActionGroups k�nnen manuell aktiviert sein (ActivateActionGroup)
	 * oder sie k�nnen durch einen Alarm "inTime" sein. Die ActionGroup hat ein Flag, die den Modus anzeigt. 
	 * @return Liste von aktiven ActionGroups
	 */
	List<ActionGroup> GetActiveActionGroups();

	/**
	 * Assoziert ein Ger�t mit einer ActionGroup, die aktiviert werden soll, wenn die Nachtlichtfunktion
	 * auf dem Ger�t aktiviert wird. 
	 * @param stringId - Eindeutiger String-Identifier f�r das Ger�t
	 * @param groupId - GroupID der ActionID, die aktiviert werden soll im Nachtlichmodus
	 * @return ActionGroup die aktiviert werden soll 
	 */
	ActionGroup AssociateNightLight(String stringId, int groupId);

	/**
	 * Entfernt die Assoziation zwischen Ger�t und ActionGroup f�r den Nachtlichtmodus
	 * @param stringId - Eindeutiger String-Identifier f�r das Ger�t
	 * @param groupId - GroupID der ActionID, die deaktiviert werden soll im Nachtlichmodus
	 * @return ActionGroup die deaktiviert werden soll 
	 */
	ActionGroup DisassociateNightLight(String stringId, int groupId);

}
