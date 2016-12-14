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

import ch.ffhs.eshh.wakeuplight.model.DeviceAction;
import ch.ffhs.eshh.wakeuplight.model.WakeUpLightDAO;

@Path("/deviceactions")
public class DeviceActionsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<DeviceAction> getDeviceActionsBrowser(@QueryParam("StringID") String stringId)
	{
		List<DeviceAction> deviceActions = new ArrayList<DeviceAction>();

		if (stringId != null && stringId.length() > 0)
		{
			if (WakeUpLightDAO.instance.getDeviceActions().containsKey(stringId))
			{
				deviceActions.add(WakeUpLightDAO.instance.getDeviceActions().get(stringId));
			}
		}

		return deviceActions;
	}

}
