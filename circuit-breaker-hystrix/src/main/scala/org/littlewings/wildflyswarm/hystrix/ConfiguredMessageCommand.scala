package org.littlewings.wildflyswarm.hystrix

import javax.ws.rs.client.ClientBuilder

import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey, HystrixCommandProperties}
import org.jboss.logging.Logger

class ConfiguredMessageCommand
  extends HystrixCommand[String](
    HystrixCommand.Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("ConfiguredMessageCommand"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(5))
  ) {
  val logger: Logger = Logger.getLogger(classOf[ConfiguredMessageCommand])

  override def run(): String = {
    logger.infof("start configured-message request")

    val client =
      ClientBuilder
        .newClient

    try {
      val response =
        client
          .target("http://localhost:9000/hello-message")
          .request
          .get

      val message = response.readEntity(classOf[String])

      response.close()

      logger.infof("end configured-message request")

      message
    } finally {
      client.close()
    }
  }

  override def getFallback: String = "Fallback Message!!"
}
