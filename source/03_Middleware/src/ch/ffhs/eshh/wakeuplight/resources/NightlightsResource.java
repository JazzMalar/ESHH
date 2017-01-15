/**
 * Projekt WakeUp-Light - ES&HH
 * Andreas Züger & Markus Schenk
 * NightlightsResource.java
 * API-Endpunkt für Nachtlichter
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
import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;

@Path("/nightlight")
public class NightlightsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	/**
	 * Gibt alle aktiven Nachtlichter zurück
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroup> getActiveNightlights()
	{
		List<ActionGroup> actionGroups = new ArrayList<ActionGroup>();
		actionGroups = DBProxyFactory.factory.g().GetActiveNightLights();
		return actionGroups;

	}

	/**
	 * Aktiviert ein Nachtlicht anhand seiner Geräte-Identifikation
	 * @param stringId
	 * @return
	 */
	@GET
	@Path("/activate")
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroupMember> activateNightlight(@QueryParam("StringID") String stringId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();
		ActionGroup g = null;

		if (stringId != null && stringId.length() > 0)
		{
			g = DBProxyFactory.factory.g().ActivateNightLight(stringId);
			if (g != null && g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}
		return actionGroupMembers;
	}

	/**
	 * Deaktiviert ein Nachtlicht
	 * @param stringId
	 * @return
	 */
	@GET
	@Path("/disable")
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroupMember> disableNightlight(@QueryParam("StringID") String stringId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();
		ActionGroup g = null;

		if (stringId != null && stringId.length() > 0)
		{
			g = DBProxyFactory.factory.g().DisableNightLight(stringId);
			if (g != null && g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}
		return actionGroupMembers;
	}

	/**
	 * Assoziert ein Gerät mit einer ActionGroup als Nachtlicht
	 * @param stringId
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/associate")
	public List<ActionGroupMember> associateNightlight(@QueryParam("StringID") String stringId,
	        @QueryParam("GroupID") int groupId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();

		if (stringId != null && stringId.length() > 0 && groupId > 0)
		{
			ActionGroup g = (DBProxyFactory.factory.g().AssociateNightLight(stringId, groupId));
			if (g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}

		return actionGroupMembers;
	}

	/**
	 * löscht die Assoziation zwischen einem Gerät und einer ActionGroup als Nachtlicht
	 * @param stringId
	 * @param groupId
	 * @return
	 */
	@GET
	@Path("/disassociate")
	public List<ActionGroupMember> disassociateNightlight(@QueryParam("StringID") String stringId,
	        @QueryParam("GroupID") int groupId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();

		if (stringId != null && stringId.length() > 0 && groupId > 0)
		{
			ActionGroup g = (DBProxyFactory.factory.g().DisassociateNightLight(stringId, groupId));
			if (g.getGroupId() > 0)
			{
				actionGroupMembers.addAll(g.getMembers());
			}
		}

		return actionGroupMembers;
	}

}
