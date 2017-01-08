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
import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;

@Path("/nightlight")
public class NightlightsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroup> getActiveActionGroups()
	{
		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();
		actionGroups = DBProxyFactory.factory.g().GetActiveNightLights();
		return actionGroups;

	}

	@GET
	@Path("/activate")
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroupMember> activateNightlight(@QueryParam("StringID") String stringId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();
		ActionGroup g = null;

		if (stringId.length() > 0)
		{
			g = DBProxyFactory.factory.g().ActivateNightLight(stringId);
			if (g != null && g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}
		return actionGroupMembers;
	}

	@GET
	@Path("/disable")
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroupMember> disableNightlight(@QueryParam("StringID") String stringId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();
		ActionGroup g = null;

		if (stringId.length() > 0)
		{
			g = DBProxyFactory.factory.g().DisableNightLight(stringId);
			if (g != null && g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}
		return actionGroupMembers;
	}

}
