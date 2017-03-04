package org.littlewings.wildflyswarm.ribbon.backend

import java.net.InetAddress
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import scala.collection.JavaConverters._

@Path("time")
class TimeResource {
  @GET
  @Path("now")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def now: java.util.Map[String, String] =
    Map("now" -> LocalDateTime.now.format(DateTimeFormatter.ISO_DATE_TIME),
      "from" -> InetAddress.getLocalHost.getHostName)
      .asJava
}
