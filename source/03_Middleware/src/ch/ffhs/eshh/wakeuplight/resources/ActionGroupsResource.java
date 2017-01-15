/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * ActionGroupsResource.java
 * API-Endpunkt für ActionGroup
 */
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

import ch.ffhs.eshh.wakeuplight.data.DBProxyFactory;
import ch.ffhs.eshh.wakeuplight.model.ActionGroup;

@Path("/actiongroup")
public class ActionGroupsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * Gibt alle aktiven ActionGroups zurück
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroup> getActiveActionGroups()
	{
		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();
		actionGroups = DBProxyFactory.factory.g().GetActiveActionGroups();
		return actionGroups;

	}

	/**
	 * Aktiviert eine frei wählbare ActionGroup
	 * @param groupId
	 */
	@GET
	@Path("/activate")
	@Produces(MediaType.TEXT_XML)
	public void activateActionGroup(@QueryParam("GroupID") int groupId)
	{
		if (groupId > 0)
		{
			DBProxyFactory.factory.g().ActivateActionGroup(groupId);
		}
	}

	/**
	 * Deaktiviert eine frei wählbare ActionGroup
	 * @param groupId
	 */
	@GET
	@Path("/disable")
	@Produces(MediaType.TEXT_XML)
	public void disableActionGroup(@QueryParam("GroupID") int groupId)
	{
		if (groupId > 0)
		{
			DBProxyFactory.factory.g().DisableActionGroup(groupId);
		}
	}

}
