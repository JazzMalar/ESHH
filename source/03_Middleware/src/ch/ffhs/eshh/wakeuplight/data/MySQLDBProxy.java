
package ch.ffhs.eshh.wakeuplight.data;

import java.sql.SQLException;
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

			actionGroup.setGroupId(groupId);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveActionGroup(int groupId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveActionGroupMember(int groupId, int memberId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveActionGroupMemberFromAllGroups(int memberId)
	{
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveDeviceAction(String stringId, int id)
	{
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveAlarm(int alarmId)
	{
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveDevice(String stringId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void RemoveDevice(int deviceId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ActivateNightLight(String stringId, int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void DisableNightLight(String stringId, int id)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void ActivateActionGroup(int groupId)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void DisableActionGroup(int groupId)
	{
		// TODO Auto-generated method stub

	}
}
