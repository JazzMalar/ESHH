/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * DevicesResource.java
 * API-Endpunkt für Devices
 */
package ch.ffhs.eshh.wakeuplight.resources;

import java.io.IOException;
import java.util.ArrayList;
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
import ch.ffhs.eshh.wakeuplight.model.Device;

@Path("/devices")
public class DevicesResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * Gibt entweder alle oder ein einzelnes Gerät als XML zurück. 
	 * @param stringId
	 * @param deviceId
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Device> getDevicesBrowser(@QueryParam("StringID") String stringId, @QueryParam("DeviceID") int deviceId)
	{
		List<Device> devices = new ArrayList<Device>();
		Device t = null;

		if (stringId != null && stringId.length() > 0)
		{
			t = DBProxyFactory.factory.g().GetDevice(stringId);
			if (t.getDeviceId() > 0)
				devices.add(t);
		}
		else
		{
			if (deviceId > 0)
			{
				t = DBProxyFactory.factory.g().GetDevice(deviceId);
				if (t.getDeviceId() > 0)
					devices.add(t);
			}
			else
			{
				devices = (DBProxyFactory.factory.g().GetAllDevices());
			}
		}

		return devices;
	}

	/**
	 * Erstellt ein neues Gerät im System.
	 * @param deviceName
	 * @param stringId
	 * @param gpioString
	 * @param servletResponse
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newDevice(@FormParam("Name") String deviceName, @FormParam("StringID") String stringId,
	        @FormParam("GPIO") String gpioString, @Context HttpServletResponse servletResponse) throws IOException
	{

		boolean gpio = gpioString.equals("true") ? true : false;

		Device device = new Device(deviceName, stringId, gpio);
		DBProxyFactory.factory.g().AddDevice(device);

		servletResponse.sendRedirect("../create_device.html");
	}

	/**
	 * Löscht ein bestehendes Gerät aus dem System.
	 * @param stringId
	 * @param deviceId
	 * @param servletResponse
	 * @throws IOException
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void deleteDevice(@QueryParam("StringID") String stringId, @QueryParam("ID") int deviceId,
	        @Context HttpServletResponse servletResponse) throws IOException
	{
		if (stringId != null && stringId.length() > 0)
		{
			DBProxyFactory.factory.g().RemoveDevice(stringId);
		}
		else if (deviceId > 0)
		{
			DBProxyFactory.factory.g().RemoveDevice(deviceId);
		}
	}
}
