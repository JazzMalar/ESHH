/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * AlarmsResource.java
 * API-Endpunkt für Alarm
 */
package ch.ffhs.eshh.wakeuplight.resources;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import ch.ffhs.eshh.wakeuplight.data.DBProxyFactory;
import ch.ffhs.eshh.wakeuplight.model.Alarm;

@Path("/alarms")
public class AlarmsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * Gibt entweder alle Alarme oder einen einzelnen Alarm zurück, wenn eine AlarmID mitgegeben wurde.
	 * @param alarmId
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Alarm> getAlarmsBrowser(@QueryParam("AlarmID") int alarmId)
	{
		List<Alarm> alarms = new ArrayList<Alarm>();
		if (alarmId > 0)
		{
			Alarm t = DBProxyFactory.factory.g().GetAlarm(alarmId);
			if (t.getAlarmId() > 0)
				alarms.add(t);
		}
		else
		{
			alarms.addAll(DBProxyFactory.factory.g().GetAllAlarms());
		}
		return alarms;
	}

	/**
	 * Erstellt einen neuen alarm
	 * @param startTimeString
	 * @param offset
	 * @param repeatPattern
	 * @param enabledString
	 * @param actionGroup
	 * @param servletResponse
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newAlarm(@FormParam("startTime") String startTimeString, @FormParam("offset") int offset,
	        @FormParam("repeatPattern") String repeatPattern, @FormParam("enabled") String enabledString,
	        @FormParam("actionGroup") int actionGroup, @Context HttpServletResponse servletResponse) throws IOException
	{
		Date startTime = new Date();
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		try
		{
			startTime = formatter.parse(startTimeString);
		}
		catch (Exception e)
		{
			System.out.println("Datum hat ein falsches Format." + e.getMessage());
		}

		boolean enabled = enabledString.equals("true") ? true : false;

		Alarm alarm = new Alarm(startTime, offset, repeatPattern, enabled, actionGroup);
		DBProxyFactory.factory.g().AddAlarm(alarm);

		servletResponse.sendRedirect("../create_alarm.html");
	}

	/**
	 * Löscht einen Alarm
	 * @param alarmId
	 * @param servletResponse
	 * @throws IOException
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void deleteAlarm(@QueryParam("ID") int alarmId, @Context HttpServletResponse servletResponse)
	        throws IOException
	{
		DBProxyFactory.factory.g().RemoveAlarm(alarmId);
	}

}
