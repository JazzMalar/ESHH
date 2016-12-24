package ch.ffhs.eshh.wakeuplight.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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
import ch.ffhs.eshh.wakeuplight.model.ActionGroup;
import ch.ffhs.eshh.wakeuplight.model.ActionGroupMember;

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
		actionGroupMembers.addAll(DBProxyFactory.factory.g().GetActionGroup(groupId).getMembers());
		return actionGroupMembers;
	}

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newActionGroupMember(@FormParam("GroupID") int groupId, @FormParam("DeviceID") int deviceId,
	        @FormParam("ActionID") int actionId, @FormParam("Offset") int offset,
	        @Context HttpServletResponse servletResponse) throws IOException
	{

		List<ActionGroupMember> members = new ArrayList<ActionGroupMember>();
		members.add(new ActionGroupMember(groupId, deviceId, actionId, offset));

		ActionGroup actionGroup = new ActionGroup(groupId, members);
		DBProxyFactory.factory.g().AddActionGroup(actionGroup);

		servletResponse.sendRedirect("../add_actionGroupMember.html");
	}
}
