/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * DeviceActionsResource.java
 * API-Endpunkt für DeviceActions
 */
package ch.ffhs.eshh.wakeuplight.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import ch.ffhs.eshh.wakeuplight.data.DBProxyFactory;
import ch.ffhs.eshh.wakeuplight.model.DeviceAction;
import ch.ffhs.eshh.wakeuplight.model.DeviceActionField;

@Path("/deviceactions")
public class DeviceActionsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * Gibt entweder alle DeviceAction als XML zurück oder eine einzelne wenn die benötigten Parameter mitgegeben wurden.
	 * @param stringId
	 * @param deviceActionId
	 * @return
	 */
	@GET
	@Produces({ MediaType.TEXT_XML, "application/json" })
	public List<DeviceAction> getDeviceActionsBrowser(@QueryParam("StringID") String stringId,
	        @QueryParam("ID") int deviceActionId)
	{
		List<DeviceAction> deviceActions = new ArrayList<DeviceAction>();
		DeviceAction t = null;

		if (stringId != null && stringId.length() > 0 && deviceActionId > 0)
		{
			t = DBProxyFactory.factory.g().GetDeviceAction(stringId, deviceActionId);
			if (t.getDeviceActionId() > 0)
				deviceActions.add(t);
		}

		return deviceActions;
	}

	/**
	 * Erstellt eine neue DeviceAction
	 * @param formParams
	 * @param servletResponse
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newDeviceAction(final MultivaluedMap<String, String> formParams,
	        @Context HttpServletResponse servletResponse) throws IOException
	{

		String stringId = formParams.get("StringID").get(0);
		int n = Integer.parseInt(formParams.get("N").get(0));
		List<DeviceActionField> actionFields = new ArrayList<DeviceActionField>();

		for (int i = 1; i <= n; i++)
		{
			actionFields.add(new DeviceActionField(formParams.get("FieldName" + i).get(0),
			        formParams.get("FieldValue" + i).get(0)));
		}

		DeviceAction deviceAction = new DeviceAction(stringId, n, actionFields);
		DBProxyFactory.factory.g().AddDeviceAction(deviceAction);

		servletResponse.sendRedirect("../create_deviceAction.html");
	}

	/**
	 * Löscht eine bestehende DeviceAction
	 * @param stringId
	 * @param id
	 * @param servletResponse
	 * @throws IOException
	 */
	@DELETE
	@Produces(MediaType.APPLICATION_XML)
	public void deleteDeviceAction(@QueryParam("StringID") String stringId, @QueryParam("ID") int id,
	        @Context HttpServletResponse servletResponse) throws IOException
	{
		DBProxyFactory.factory.g().RemoveDeviceAction(stringId, id);
	}
}
