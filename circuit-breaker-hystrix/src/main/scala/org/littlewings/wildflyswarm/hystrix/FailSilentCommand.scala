package org.littlewings.wildflyswarm.hystrix

import javax.ws.rs.client.ClientBuilder

import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey}
import org.jboss.logging.Logger

class FailSilentCommand extends HystrixCommand[String](HystrixCommandGroupKey.Factory.asKey("FailSilentCommand")) {
  val logger: Logger = Logger.getLogger(classOf[FailSilentCommand])

  override def run(): String = {
    logger.infof("start fail-silent request")

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

      logger.infof("end fail-silent request")

      message
    } finally {
      client.close()
    }
  }

  override def getFallback: String = "Fallback Message!!"
}
