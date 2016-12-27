package ch.ffhs.eshh.wakeuplight.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/actiongroup")
public class ActionGroupsResource
{
	// Allows to insert contextual objects into the class
	@Context
	UriInfo uriInfo;

	@Context
	Request request;

	@GET
	@Path("/activate")
	@Produces(MediaType.TEXT_XML)
	public int activateActionGroup(@QueryParam("GroupID") String groupId)
	{
		return 0;

	}

	@GET
	@Path("/disable")
	@Produces(MediaType.TEXT_XML)
	public int disableActionGroup(@QueryParam("GroupID") String groupId)
	{
		return 0;

	}

}
