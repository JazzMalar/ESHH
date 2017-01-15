/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * DBProxyFactory.java
 * Singleton zum Zugriff auf die lokale mysql Datenbank
 */
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
			dbProxy = new MySQLDBProxy("localhost", "mydb", "wakeuplight", "wakeuplight");
		}
		catch (Exception e)
		{

		}
	}

	/**
	 * Kurzversion der Getter-Methode
	 * @return kative DBProxy Instanz
	 */
	public IDBProxy g()
	{
		return dbProxy;
	}

	/**
	 * Getter-Methode zum Zugriff auf den aktiven DBProxy
	 * @return
	 */
	public IDBProxy GetDBProxy()
	{
		return dbProxy;
	}

	/**
	 * Dependency-Injection Methode zum Überschreiben des aktiven DBProxy
	 * @param dbProxy
	 */
	public void SetDBProxy(IDBProxy dbProxy)
	{
		this.dbProxy = dbProxy;
	}

}
