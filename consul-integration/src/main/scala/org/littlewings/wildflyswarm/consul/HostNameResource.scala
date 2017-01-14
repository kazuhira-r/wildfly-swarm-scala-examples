package org.littlewings.wildflyswarm.consul

import java.net.InetAddress
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

@Path("hostname")
class HostNameResource {
  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def get: String = InetAddress.getLocalHost.getHostName
}
