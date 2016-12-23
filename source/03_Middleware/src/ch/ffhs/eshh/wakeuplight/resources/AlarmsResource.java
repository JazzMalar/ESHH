package ch.ffhs.eshh.wakeuplight.resources;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Alarm> getAlarmsBrowser()
	{
		List<Alarm> alarms = DBProxyFactory.factory.g().GetAllAlarms();
		return alarms;
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newTodo(@FormParam("startTime") String startTimeString, @FormParam("offset") int offset,
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

		boolean enabled = enabledString.equals("on") ? true : false;

		Alarm alarm = new Alarm(startTime, offset, repeatPattern, enabled, actionGroup);
		DBProxyFactory.factory.g().AddAlarm(alarm);

		servletResponse.sendRedirect("../create_alarm.html");
	}

}
