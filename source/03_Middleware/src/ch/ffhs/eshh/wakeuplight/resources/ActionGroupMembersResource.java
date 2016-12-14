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

import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;
import ch.ffhs.eshh.wakeuplight.model.WakeUpLightDAO;

@Path("/actiongroupmembers")
public class ActionGroupMembersResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	// Return the list of todos to the user in the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<ActionGroupMember> getActionGroupMembersBrowser(@QueryParam("GroupID") int groupId)
	{
		List<ActionGroupMember> actionGroupMembers = new ArrayList<ActionGroupMember>();
		if (WakeUpLightDAO.instance.getActionGroupMembers().containsKey(groupId))
		{
			actionGroupMembers.add(WakeUpLightDAO.instance.getActionGroupMembers().get(groupId));
		}
		return actionGroupMembers;
	}

}
