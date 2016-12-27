package ch.ffhs.eshh.wakeuplight.data;

import ch.ffhs.eshh.wakeuplight.interfaces.IDBProxy;

public enum DBProxyFactory
{
	factory;

	private IDBProxy dbProxy;

	private DBProxyFactory()
	{
		try
		{
			dbProxy = new MySQLDBProxy("vaspberrypi", "mydb", "wakeuplight", "wakeuplight");
		}
		catch (Exception e)
		{

		}
	}

	public IDBProxy g()
	{
		return dbProxy;
	}

	public IDBProxy GetDBProxy()
	{
		return dbProxy;
	}

	public void SetDBProxy(IDBProxy dbProxy)
	{
		this.dbProxy = dbProxy;
	}

}
