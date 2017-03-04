package org.littlewings.wildflyswarm.ribbon.frontend

import com.fasterxml.jackson.databind.ObjectMapper
import com.netflix.hystrix.HystrixInvokableInfo
import com.netflix.ribbon.hystrix.FallbackHandler
import io.netty.buffer.{ByteBuf, UnpooledByteBufAllocator}
import rx.Observable

class TimeFallbackHandler extends FallbackHandler[ByteBuf] {
  private[frontend] val objectMapper = new ObjectMapper

  def getFallback(hystrixInfo: HystrixInvokableInfo[_], requestProperties: java.util.Map[String, AnyRef]): Observable[ByteBuf] = {
    val dummy = new java.util.HashMap[String, String]
    dummy.put("now", "dummy")
    dummy.put("host", "dummy")
    val binary = objectMapper.writeValueAsBytes(dummy)
    val byteBuf = UnpooledByteBufAllocator.DEFAULT.buffer(binary.length)
    Observable.just(byteBuf)
  }
}