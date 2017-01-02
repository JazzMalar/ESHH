package ch.ffhs.eshh.wakeuplight.data;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;

import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;
import ch.ffhs.eshh.wakeuplight.model.Alarm;
import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;
import ch.ffhs.eshh.wakeuplight.model.DeviceActionField;

public enum ResultSetFactory
{
	factory;

	private ResultSetFactory()
	{

	}

	public ResultSetHandler<DeviceAction> DeviceActionResultHandler()
	{
		ResultSetHandler<DeviceAction> handler = new ResultSetHandler<DeviceAction>() {
			public DeviceAction handle(ResultSet rs) throws SQLException
			{
				DeviceAction da = new DeviceAction();
				;
				if (!rs.next())
				{
					return null;
				}

				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();

				da.setDeviceActionId(rs.getInt(1));
				da.setN(cols - 1);

				List<DeviceActionField> daf = new ArrayList<DeviceActionField>();

				for (int i = 1; i < cols; i++)
				{
					switch (meta.getColumnType(i + 1))
					{
						case java.sql.Types.VARCHAR:
							daf.add(new DeviceActionField(meta.getColumnName(i + 1), rs.getString(i + 1)));
							break;
						case java.sql.Types.INTEGER:
							daf.add(new DeviceActionField(meta.getColumnName(i + 1),
							        Integer.toString((rs.getInt(i + 1)))));
							break;
					}
				}

				da.setFields(daf);

				return da;
			}
		};

		return handler;
	}

	public ResultSetHandler<List<ActionGroupMember>> AGMResultHandler()
	{
		ResultSetHandler<List<ActionGroupMember>> handler = new ResultSetHandler<List<ActionGroupMember>>() {
			public List<ActionGroupMember> handle(ResultSet rs) throws SQLException
			{
				ArrayList<ActionGroupMember> objects = new ArrayList<ActionGroupMember>();
				if (!rs.next())
				{
					return null;
				}

				do
				{
					ActionGroupMember agm = new ActionGroupMember();
					agm.setActionGroupMemberId(rs.getInt(1));
					agm.setGroupId(rs.getInt(2));
					agm.setDeviceId(rs.getInt(3));
					agm.setActionId(rs.getInt(4));
					agm.setOffset(rs.getInt(5));
					objects.add(agm);
				}
				while (rs.next());

				return objects;
			}
		};

		return handler;
	}

	public ResultSetHandler<List<Alarm>> AlarmResultHandler()
	{
		ResultSetHandler<List<Alarm>> handler = new ResultSetHandler<List<Alarm>>() {
			public List<Alarm> handle(ResultSet rs) throws SQLException
			{
				ArrayList<Alarm> objects = new ArrayList<Alarm>();
				if (!rs.next())
				{
					return null;
				}

				do
				{
					Alarm alarm = new Alarm();
					alarm.setAlarmId(rs.getInt(1));
					alarm.setStartTime(rs.getTimestamp(2));
					alarm.setRepeatPattern(rs.getString(3));
					alarm.setEnabled(rs.getBoolean(4));
					alarm.setActionGroup(rs.getInt(5));
					objects.add(alarm);
				}
				while (rs.next());

				return objects;
			}
		};

		return handler;
	}

	public ResultSetHandler<List<Device>> DeviceResultHandler()
	{
		ResultSetHandler<List<Device>> handler = new ResultSetHandler<List<Device>>() {
			public List<Device> handle(ResultSet rs) throws SQLException
			{
				ArrayList<Device> objects = new ArrayList<Device>();
				if (!rs.next())
				{
					return null;
				}

				do
				{
					Device device = new Device();
					device.setDeviceId(rs.getInt(1));
					device.setName(rs.getString(2));
					device.setStringId(rs.getString(3));
					device.setGpio(rs.getBoolean(4));
					objects.add(device);
				}
				while (rs.next());

				return objects;
			}
		};

		return handler;
	}

	public ResultSetHandler<List<Object[]>> ObjectResultHandler()
	{
		ResultSetHandler<List<Object[]>> handler = new ResultSetHandler<List<Object[]>>() {
			public List<Object[]> handle(ResultSet rs) throws SQLException
			{
				ArrayList<Object[]> objects = new ArrayList<Object[]>();
				if (!rs.next())
				{
					return null;
				}

				ResultSetMetaData meta = rs.getMetaData();
				int cols = meta.getColumnCount();
				Object[] result = new Object[cols];

				do
				{
					for (int i = 0; i < cols; i++)
					{
						result[i] = rs.getObject(i + 1);
					}
					objects.add(result);
				}
				while (rs.next());

				return objects;
			}
		};

		return handler;
	}

}
