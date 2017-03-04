package org.littlewings.wildflyswarm.ribbon.backend

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

@Path("message")
class MessageResource {
  @GET
  @Path("echo")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def echo(@QueryParam("message") message: String): String =
    s"★${message}★"
}
