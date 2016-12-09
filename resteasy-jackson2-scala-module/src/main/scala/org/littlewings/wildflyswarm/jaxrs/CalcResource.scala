package org.littlewings.wildflyswarm.jaxrs

import javax.ws.rs._
import javax.ws.rs.core.MediaType

@Path("calc")
class CalcResource {
  @POST
  @Path("add")
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def add(request: AddRequest): AddResponse =
    AddResponse(request.a + request.b)
}

case class AddRequest(a: Int, b: Int)

case class AddResponse(result: Int)
