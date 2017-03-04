package org.littlewings.wildflyswarm.ribbon.frontend

import com.netflix.ribbon.RibbonRequest
import com.netflix.ribbon.proxy.annotation.{Http, ResourceGroup, TemplateName, Var}
import io.netty.buffer.ByteBuf

@ResourceGroup(name = "backend")
trait MessageService {
  @TemplateName("echo")
  @Http(method = Http.HttpMethod.GET, uri = "/message/echo")
  def echo(@Var("message") message: String): RibbonRequest[ByteBuf]
}
