/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
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
	 * Gibt die ActionGroup zurück, die mit dieser groupId assoziert ist.
	 * @param groupId - zu selektierende Gruppe 
	 * @return ActionGroup der selektierten Gruppe
	 */
	ActionGroup GetActionGroup(int groupId);

	/**
	 * Gibt alle ActionGroups zurück, die zurzeit im System erfasst sind. 
	 * @return Liste von allen ActionGroups
	 */
	List<ActionGroup> GetAllActionGroups();

	/**
	 * Fügt dem System eine ActionGroup hinzu. 
	 * @param actionGroup Hinzuzufügende Gruppe. Muss ActionGroupMembers enthalten.
	 */
	void AddActionGroup(ActionGroup actionGroup);

	/**
	 * Löscht eine ActionGroup aus dem System
	 * @param groupId - zu löschende ActionGroup
	 */
	void RemoveActionGroup(int groupId);

	/**
	 * Löscht einen einzelnen Member (anzusprechendes Device) aus der ActionGroup
	 * @param groupId - betroffene ActionGroup
	 * @param deviceId - zu löschendes Gerät 
	 */
	void RemoveActionGroupMember(int groupId, int deviceId);

	/**
	 * Löscht das angegebene Gerät aus allen ActionGroups im System. 
	 * @param deviceId - zu löschendes Gerät
	 */
	void RemoveActionGroupMemberFromAllGroups(int deviceId);

	/**
	 * Gibt eine spezifische Geräteaktion zurück. 
	 * @param stringId - String-Identifier für das Gerät
	 * @param id - Eindeutige ID, die die gewünschte Aktion referenziert
	 * @return Parameter für das Gerät, die mit dieser Aktion assoziiert sind als DeviceAction
	 */
	DeviceAction GetDeviceAction(String stringId, int id);

	/**
	 * Fügt ein Set von Parametern als neue DeviceAction zu einem Gerät hinzu. 
	 * @param deviceAction - Die hinzuzufügende Aktion (das betroffene Gerät wird dieser DeviceAction entnommen)
	 */
	void AddDeviceAction(DeviceAction deviceAction);

	/**
	 * Löscht eine spezifische Geräteaktion aus dem System
	 * @param stringId - String-Identifier für das Gerät
	 * @param id - Eindeutige ID der Aktion, die gelöscht werden soll. 
	 */
	void RemoveDeviceAction(String stringId, int id);

	/**
	 * Gibt einen spezifischen Alarm (Task) zurück
	 * @param alarmId - der zurückzugebende Alarm
	 * @return Einzelner Alarm
	 */
	Alarm GetAlarm(int alarmId);

	/**
	 * Gibt alle definierten Alarme (Task) zurück
	 * @return Alle definierten Alarme
	 */
	List<Alarm> GetAllAlarms();

	/**
	 * Fügt dem System einen neuen Alarm hinzu
	 * @param newAlarm - der hinzuzufügende Alarm
	 */
	void AddAlarm(Alarm newAlarm);

	/**
	 * Löscht einen Alarm aus dem System
	 * @param alarmId - Referenz auf den zu löschenden Alarm
	 */
	void RemoveAlarm(int alarmId);

	/**
	 * gibt ein Gerät aus der Device-Tabelle anhand des String-Identifiers zurück
	 * @param stringId - Eindeutiger String, der das Gerät identifiziert
	 * @return Gefundenes Gerät oder null
	 */
	Device GetDevice(String stringId);

	/**
	 * Gibt ein Gerät aus der Device-Tabelle anhand der eindeutigen ID zurück
	 * @param deviceId - Endeutige ID des Geräts
	 * @return Gefundenes Gerät oder Null
	 */
	Device GetDevice(int deviceId);

	/**
	 * Gibt alle Geräte aus dem System zurück
	 * @return Liste aller Geräte
	 */
	List<Device> GetAllDevices();

	/**
	 * Fügt dem System ein neues Gerät hinzu
	 * @param newDevice - Das hinzuzufügende Gerät
	 */
	void AddDevice(Device newDevice);

	/**
	 * Löscht ein Gerät aus dem System anhand seines Text-Identifiers
	 * @param stringId - Eindeutiger String, der das Gerät identifiziert
	 */
	void RemoveDevice(String stringId);

	/**
	 * Löscht ein Gerät aus dem System anhand seiner eindeutigen ID
	 * @param deviceId - Eindeutige ID, die das Gerät identifiziert
	 */
	void RemoveDevice(int deviceId);

	/**
	 * Aktiviert die Nachtlichtfunktion auf dem angegebenen Gerät
	 * @param stringId - Das Gerät auf dem die Nachtlichtfunktion aktiviert wreden soll
	 * @return Die damit aktivierte ActionGroup, die die zu aktivierenden Geräte und die Geräteaktionen enthält
	 */
	ActionGroup ActivateNightLight(String stringId);

	/**
	 * Deaktiviert die Nachtlichtfunktion auf dem angegebenen Gerät
	 * @param stringId - Das Gerät auf dem die Nachtlichtfuntkion deaktiviert werden soll
	 * @return Die damit deaktivierte ActionGroup
	 */
	ActionGroup DisableNightLight(String stringId);

	/**
	 * Markiert eine frei wählbare ActionGroup als Aktiv
	 * @param groupId - zu aktivierende ActionGroup
	 */
	void ActivateActionGroup(int groupId);

	/**
	 * Markiert eine frei wählbare ActionGroup als Inaktiv
	 * @param groupId - zu deaktivierende ActionGroup
	 */
	void DisableActionGroup(int groupId);

	/**
	 * Gibt alle aktiven Nachtlichter zurück
	 * @return liste von aktiven Nachtlichtern
	 */
	List<ActionGroup> GetActiveNightLights();

	/**
	 * Gibt alle aktiven ActionGroups zurück. ActionGroups können manuell aktiviert sein (ActivateActionGroup)
	 * oder sie können durch einen Alarm "inTime" sein. Die ActionGroup hat ein Flag, die den Modus anzeigt. 
	 * @return Liste von aktiven ActionGroups
	 */
	List<ActionGroup> GetActiveActionGroups();

	/**
	 * Assoziert ein Gerät mit einer ActionGroup, die aktiviert werden soll, wenn die Nachtlichtfunktion
	 * auf dem Gerät aktiviert wird. 
	 * @param stringId - Eindeutiger String-Identifier für das Gerät
	 * @param groupId - GroupID der ActionID, die aktiviert werden soll im Nachtlichmodus
	 * @return ActionGroup die aktiviert werden soll 
	 */
	ActionGroup AssociateNightLight(String stringId, int groupId);

	/**
	 * Entfernt die Assoziation zwischen Gerät und ActionGroup für den Nachtlichtmodus
	 * @param stringId - Eindeutiger String-Identifier für das Gerät
	 * @param groupId - GroupID der ActionID, die deaktiviert werden soll im Nachtlichmodus
	 * @return ActionGroup die deaktiviert werden soll 
	 */
	ActionGroup DisassociateNightLight(String stringId, int groupId);

}
