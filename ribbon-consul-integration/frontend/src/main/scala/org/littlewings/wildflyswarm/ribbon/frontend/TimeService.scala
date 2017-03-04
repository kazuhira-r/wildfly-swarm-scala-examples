package org.littlewings.wildflyswarm.ribbon.frontend

import com.netflix.ribbon.RibbonRequest
import com.netflix.ribbon.proxy.annotation.{Http, Hystrix, ResourceGroup, TemplateName}
import io.netty.buffer.ByteBuf

@ResourceGroup(name = "backend")
trait TimeService {
  @TemplateName("now")
  @Http(method = Http.HttpMethod.GET, uri = "/time/now")
  //@Hystrix(fallbackHandler = Array(classOf[TimeFallbackHandler]))
  def now: RibbonRequest[ByteBuf]
}
