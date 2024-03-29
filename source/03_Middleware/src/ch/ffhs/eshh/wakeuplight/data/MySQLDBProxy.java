/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Z�ger & Markus Schenk
 * MySQLDBProxy.java
 * MySQL spezifische Implementierung des DBProxy-Interface
 */
package ch.ffhs.eshh.wakeuplight.data;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mysql.cj.jdbc.MysqlDataSource;

import ch.ffhs.eshh.wakeuplight.interfaces.IDBProxy;
import ch.ffhs.eshh.wakeuplight.model.ActionGroup;
import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;
import ch.ffhs.eshh.wakeuplight.model.Alarm;
import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;
import ch.ffhs.eshh.wakeuplight.model.DeviceActionField;

public class MySQLDBProxy implements IDBProxy
{

	private DataSource ds;
	private QueryRunner run;

	public MySQLDBProxy(String hostname, String database, String username, String password) throws SQLException
	{
		MysqlDataSource ds = new MysqlDataSource();
		ds.setUrl("jdbc:mysql://" + hostname + "/" + database);
		ds.setUser(username);
		ds.setPassword(password);

		run = new QueryRunner(ds);

	}

	@Override
	public ActionGroup GetActionGroup(int groupId)
	{
		ActionGroup actionGroup = new ActionGroup();

		try
		{
			List<ActionGroupMember> agms = run.query(
			        "SELECT idActionGroupMember, idGroup, idDevice, idAction, offset from ActionGroupMember WHERE idGroup = ?",
			        ResultSetFactory.factory.AGMResultHandler(), groupId);

			actionGroup.setGroupId(agms.get(0).getGroupId());
			actionGroup.setMembers(agms);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return actionGroup;
	}

	@Override
	public List<ActionGroup> GetAllActionGroups()
	{
		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();

		int actionGroupId = -1;

		try
		{
			List<Object[]> os = run.query("select distinct idGroup from ActionGroupMember",
			        ResultSetFactory.factory.ObjectResultHandler());

			for (Object o : os)
			{
				List<ActionGroupMember> agm = run.query(
				        "select idActionGroupMember, idGroup, idDevice, idAction, offset from ActionGroupMember WHERE idGroup = ?",
				        ResultSetFactory.factory.AGMResultHandler(), (int) o);
				ActionGroup ag = new ActionGroup((int) o, agm);
				actionGroups.add(ag);
			}

		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Select " + e.getMessage());
		}

		return actionGroups;
	}

	@Override
	public void AddActionGroup(ActionGroup actionGroup)
	{
		for (ActionGroupMember groupMember : actionGroup.getMembers())
		{
			try
			{
				run.update("insert into ActionGroupMember (idGroup, idDevice, idAction, Offset) VALUES (?,?,?,?)",
				        actionGroup.getGroupId(), groupMember.getDeviceId(), groupMember.getActionId(),
				        groupMember.getOffset());
			}
			catch (Exception e)
			{
				System.out.println("Fehler beim Insert" + e.getMessage());
			}
		}

	}

	@Override
	public void RemoveActionGroup(int groupId)
	{
		try
		{
			run.update("delete from ActionGroupMember WHERE idGroup = ?", groupId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public void RemoveActionGroupMember(int groupId, int deviceId)
	{
		try
		{
			run.update("delete from ActionGroupMember WHERE idGroup = ? and idDevice = ?", groupId, deviceId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public void RemoveActionGroupMemberFromAllGroups(int deviceId)
	{
		try
		{
			run.update("delete from ActionGroupMember WHERE idDevice = ?", deviceId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public DeviceAction GetDeviceAction(String stringId, int id)
	{
		DeviceAction devAction = new DeviceAction();

		String tableName = "DeviceAction_" + stringId;

		try
		{
			devAction = run.query("select * from " + tableName + " where idDeviceAction = ?",
			        ResultSetFactory.factory.DeviceActionResultHandler(), id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return devAction;
	}

	@Override
	public void AddDeviceAction(DeviceAction deviceAction)
	{

		String tableName = "DeviceAction_" + deviceAction.getStringId();
		String fields = "";
		String values = "";

		for (DeviceActionField field : deviceAction.getFields())
		{
			fields += field.getFieldName() + ",";
			values += "'" + field.getFieldValue() + "',";
		}

		fields = fields.substring(0, fields.length() - 1);
		values = values.substring(0, values.length() - 1);

		try
		{
			run.update("insert into " + tableName + " (" + fields + ") VALUES (" + values + ")");
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

	}

	@Override
	public void RemoveDeviceAction(String stringId, int id)
	{
		String tableName = "DeviceAction_" + stringId;
		try
		{
			run.update("delete from " + tableName + " WHERE idDeviceAction = ?", id);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public Alarm GetAlarm(int alarmId)
	{
		Alarm alarm = new Alarm();

		try
		{
			alarm = run
			        .query("select idTask, startTime, repeatPattern, active, idActionGroup from Alarm where idTask = ?",
			                ResultSetFactory.factory.AlarmResultHandler(), alarmId)
			        .get(0);
		}
		catch (Exception e)
		{
			System.out.println("Fehler: " + e.getMessage());
		}

		return alarm;
	}

	@Override
	public List<Alarm> GetAllAlarms()
	{
		List<Alarm> alarmList = new ArrayList<Alarm>();
		try
		{
			alarmList = run.query("select idTask, startTime, repeatPattern, active, idActionGroup from Alarm",
			        ResultSetFactory.factory.AlarmResultHandler());
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Select " + e.getMessage());
		}

		return alarmList;
	}

	@Override
	public void AddAlarm(Alarm newAlarm)
	{
		try
		{
			run.update("insert into Alarm (startTime, repeatPattern, active, idActionGroup) VALUES (?,?,?,?)",
			        newAlarm.getStartTime(), newAlarm.getRepeatPattern(), newAlarm.getEnabled(),
			        newAlarm.getActionGroup());
			;
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

	}

	@Override
	public void RemoveAlarm(int alarmId)
	{
		try
		{
			run.update("delete from Alarm WHERE idTask = ?", alarmId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public Device GetDevice(String stringId)
	{
		Device device = new Device();

		try
		{
			device = run.query("select idDevice, name, stringId, gpio from Device WHERE stringId = ?",
			        ResultSetFactory.factory.DeviceResultHandler(), stringId).get(0);
		}
		catch (Exception e)
		{

		}

		return device;
	}

	@Override
	public Device GetDevice(int deviceId)
	{
		Device device = new Device();

		try
		{
			device = run.query("select idDevice, name, stringId, gpio from Device WHERE idDevice = ?",
			        ResultSetFactory.factory.DeviceResultHandler(), deviceId).get(0);
		}
		catch (Exception e)
		{

		}

		return device;
	}

	@Override
	public List<Device> GetAllDevices()
	{
		List<Device> deviceList = new ArrayList<Device>();
		try
		{
			deviceList = run.query("select idDevice, name, stringId, gpio from Device",
			        ResultSetFactory.factory.DeviceResultHandler());
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Select " + e.getMessage());
		}

		return deviceList;
	}

	@Override
	public void AddDevice(Device newDevice)
	{
		try
		{
			run.update("insert into Device (name, stringId, gpio) VALUES (?,?,?)", newDevice.getName(),
			        newDevice.getStringId(), newDevice.getGpio());
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

	}

	@Override
	public void RemoveDevice(String stringId)
	{
		try
		{
			run.update("delete from Device WHERE stringId = ?", stringId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}
	}

	@Override
	public void RemoveDevice(int deviceId)
	{
		try
		{
			run.update("delete from Device WHERE idDevice = ?", deviceId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}
	}

	@Override
	public ActionGroup ActivateNightLight(String stringId)
	{
		ActionGroup result = new ActionGroup();

		try
		{

			List<Object[]> os = run.query("select idActionGroup from Nightlight where triggerDevice = ?",
			        ResultSetFactory.factory.ObjectResultHandler(), stringId);

			int actionGroupId = (int) os.get(0)[0];

			os = run.query("select idActionGroup from ActiveActionGroups WHERE idActionGroup = ? AND isNightlight = 1", 
					ResultSetFactory.factory.ObjectResultHandler(), actionGroupId); 
			
			if(os == null)
			{
				run.update("insert into ActiveActionGroups (idActionGroup, isNightlight) VALUES (?,1)", actionGroupId);				
			}

			result = GetActionGroup(actionGroupId);

		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

		return result;

	}

	@Override
	public ActionGroup DisableNightLight(String stringId)
	{
		ActionGroup result = new ActionGroup();

		try
		{

			List<Object[]> os = run.query("select idActionGroup from Nightlight where triggerDevice = ?",
			        ResultSetFactory.factory.ObjectResultHandler(), stringId);

			int actionGroupId = (int) os.get(0)[0];
			run.update("delete from ActiveActionGroups where idActionGroup = ? AND isNightlight = 1", actionGroupId);

			result = GetActionGroup(actionGroupId);

		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

		return result;
	}

	@Override
	public void ActivateActionGroup(int groupId)
	{
		try
		{
			List<Object[]> os = run.query("select idActionGroup from ActiveActionGroups WHERE idActionGroup = ? AND isNightlight = 1", 
					ResultSetFactory.factory.ObjectResultHandler(), groupId); 
			
			if(os == null)
			{
				run.update("insert into ActiveActionGroups (idActionGroup, isNightlight) VALUES (?,0)", groupId);		
			}
			
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

	}

	@Override
	public void DisableActionGroup(int groupId)
	{
		try
		{
			run.update("delete from ActiveActionGroups where idActionGroup = ? and isNightlight = 0", groupId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

	}

	@Override
	public List<ActionGroup> GetActiveNightLights()
	{

		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();

		try
		{
			List<Object[]> os = run.query("SELECT idActionGroup FROM mydb.ActiveActionGroups WHERE isNightlight = 1",
			        ResultSetFactory.factory.ObjectResultHandler());

			for (Object[] o : os)
			{
				int groupId = (int) o[0];

				List<ActionGroupMember> agm = run.query(
				        "select idActionGroupMember, idGroup, idDevice, idAction, offset from ActionGroupMember WHERE idGroup = ?",
				        ResultSetFactory.factory.AGMResultHandler(), groupId);
				ActionGroup ag = new ActionGroup(groupId, agm);
				actionGroups.add(ag);
			}

		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Select " + e.getMessage());
		}

		return actionGroups;

	}

	@Override
	public List<ActionGroup> GetActiveActionGroups()
	{
		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();

		LocalDate date = LocalDate.now();
		int day = date.getDayOfWeek().getValue();

		StringBuilder repeatPattern = new StringBuilder("0000000");
		repeatPattern.setCharAt(day - 1, '1');

		try
		{
			List<Object[]> os = run.query(
			        "SELECT idActionGroup as groupId, 0 as alarm FROM ActiveActionGroups WHERE isNightlight = 0 UNION SELECT idActionGroup as groupId, active as alarm FROM mydb.Alarm where repeatPattern & ? and StartTime < TIME(NOW()) and date_add(StartTime, INTERVAL 10 MINUTE) > NOW() and Active = 1",
			        ResultSetFactory.factory.ObjectResultHandler(), repeatPattern.toString());

			for (Object[] o : os)
			{
				int groupId = (int) o[0];
				int isAlarm = ((Long) o[1]).intValue();
				
				List<ActionGroupMember> agm = run.query(
				        "select idActionGroupMember, idGroup, idDevice, idAction, offset from ActionGroupMember WHERE idGroup = ?",
				        ResultSetFactory.factory.AGMResultHandler(), groupId);
				ActionGroup ag = new ActionGroup(groupId, agm);
				if (isAlarm > 0)
				{
					ag.setInTime(true);
				}
				actionGroups.add(ag);
			}

		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Select " + e.getMessage());
		}

		return actionGroups;
	}

	@Override
	public ActionGroup AssociateNightLight(String stringId, int groupId)
	{

		try
		{
			run.update("insert into Nightlight (idActionGroup, triggerDevice) VALUES (?,?)", groupId, stringId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Insert" + e.getMessage());
		}

		return GetActionGroup(groupId);

	}

	@Override
	public ActionGroup DisassociateNightLight(String stringId, int groupId)
	{
		try
		{
			run.update("delete from Nightlight where idActionGroup = ? and triggerDevice = ?", groupId, stringId);
		}
		catch (Exception e)
		{
			System.out.println("Fehler beim Delete" + e.getMessage());
		}

		return GetActionGroup(groupId);

	}
}
