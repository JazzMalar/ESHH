package ch.ffhs.eshh.wakeuplight.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import ch.ffhs.eshh.wakeuplight.model.Device;
import ch.ffhs.eshh.wakeuplight.model.WakeUpLightDAO;

@Path("/devices")
public class DevicesResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Device> getDevicesBrowser(@QueryParam("StringID") String stringId)
	{
		List<Device> devices = new ArrayList<Device>();

		if (stringId != null && stringId.length() > 0)
		{
			if (WakeUpLightDAO.instance.getDevices().containsKey(stringId))
			{
				devices.add(WakeUpLightDAO.instance.getDevices().get(stringId));
			}
		}
		else
		{
			devices.addAll(WakeUpLightDAO.instance.getDevices().values());
		}

		return devices;
	}

}
