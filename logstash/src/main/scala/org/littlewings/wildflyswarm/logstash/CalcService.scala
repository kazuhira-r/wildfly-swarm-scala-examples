package org.littlewings.wildflyswarm.logstash

import javax.enterprise.context.ApplicationScoped

import org.jboss.logging.Logger

@ApplicationScoped
class CalcService {
  private[logstash] val logger: Logger = Logger.getLogger(getClass)

  def add(a: Int, b: Int): Int = {
    logger.infof("parameter a = %d, b = %d", a, b)
    a + b
  }
}
