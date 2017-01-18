package org.littlewings.wildflyswarm.hystrix

import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces}

import org.jboss.logging.Logger

@Path("message")
class MessageResource {
  val logger: Logger = Logger.getLogger(classOf[MessageResource])

  @GET
  @Path("fail-fast")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def failFast: String = {
    logger.infof("start resource")

    val command = new FailFastMessageCommand
    val message = command.execute()

    logger.infof("end resource")

    message + System.lineSeparator
  }

  @GET
  @Path("fail-silent")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def failSilent: String = {
    logger.infof("start resource")

    val command = new FailSilentCommand
    val message = command.execute()

    logger.infof("end resource")

    message + System.lineSeparator
  }

  @GET
  @Path("configured")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def configured: String = {
    logger.infof("start configured-message resource")

    val command = new ConfiguredMessageCommand
    val message = command.execute()

    logger.infof("end configured-message resource")

    message + System.lineSeparator
  }
}
