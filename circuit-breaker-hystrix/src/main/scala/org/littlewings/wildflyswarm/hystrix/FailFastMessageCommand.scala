package org.littlewings.wildflyswarm.hystrix

import javax.ws.rs.client.ClientBuilder

import com.netflix.hystrix.{HystrixCommand, HystrixCommandGroupKey}
import org.jboss.logging.Logger

class FailFastMessageCommand extends HystrixCommand[String](HystrixCommandGroupKey.Factory.asKey("FailFastMessageCommand")) {
  val logger: Logger = Logger.getLogger(classOf[FailFastMessageCommand])

  override def run(): String = {
    logger.infof("start fail-fast request")

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

      logger.infof("end fail-fast request")

      message
    } finally {
      client.close()
    }
  }
}
