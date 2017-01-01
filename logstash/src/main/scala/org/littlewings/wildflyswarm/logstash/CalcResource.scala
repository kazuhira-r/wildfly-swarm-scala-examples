package org.littlewings.wildflyswarm.logstash

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.{Context, MediaType, UriInfo}
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import org.jboss.logging.Logger

@Path("calc")
@ApplicationScoped
class CalcResource {
  private[logstash] val logger: Logger = Logger.getLogger(getClass)

  @Inject
  private[logstash] var calcService: CalcService = _

  @GET
  @Path("add")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def add(@QueryParam("a") a: Int, @QueryParam("b") b: Int, @Context uriInfo: UriInfo): Int = {
    // logger.debugf("url = %s, parameter a = %d, b = %d", uriInfo.getRequestUri, a, b)
    logger.infof("url = %s, parameter a = %d, b = %d", uriInfo.getRequestUri, a, b)
    calcService.add(a, b)
  }
}
