package org.littlewings.wildflyswarm.deltaspike

import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

@Path("message")
@ApplicationScoped
class MessageResource {
  @Inject
  private[deltaspike] var decorator: Decorator = _

  @GET
  @Produces(Array(MediaType.TEXT_PLAIN))
  def message(@QueryParam("p") p: String): String = decorator.decorate(p)
}
