package org.littlewings.wildflyswarm.ribbon.frontend

import java.nio.charset.StandardCharsets
import javax.ws.rs.container.{AsyncResponse, Suspended}
import javax.ws.rs.core.MediaType
import javax.ws.rs.{GET, Path, Produces, QueryParam}

import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.ribbon.Ribbon
import io.netty.buffer.ByteBufInputStream

@Path("front")
class FrontResource {
  val objectMapper: ObjectMapper = new ObjectMapper

  @GET
  @Path("get-now")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def get: java.util.Map[_, _] = {
    val byteBuf = Ribbon.from(classOf[TimeService]).now.execute()

    objectMapper.readValue(new ByteBufInputStream(byteBuf), classOf[java.util.Map[_, _]])
  }

  @GET
  @Path("get-now-async")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def getAsync(@Suspended asyncResponse: AsyncResponse): Unit = {
    val observable = Ribbon.from(classOf[TimeService]).now.observe

    observable.subscribe { byteBuf =>
      val now = objectMapper.readValue(new ByteBufInputStream(byteBuf), classOf[java.util.Map[_, _]])
      asyncResponse.resume(now)
    }
  }

  @GET
  @Path("message-echo")
  @Produces(Array(MediaType.TEXT_PLAIN))
  def messageEcho(@QueryParam("message") message: String): String = {
    val byteBuf = Ribbon.from(classOf[MessageService]).echo(message).execute()
    val is = new ByteBufInputStream(byteBuf)
    new String(
      Iterator
        .continually(is.read())
        .takeWhile(_ != -1)
        .map(_.asInstanceOf[Byte])
        .toArray,
      StandardCharsets.UTF_8
    )
  }
}
