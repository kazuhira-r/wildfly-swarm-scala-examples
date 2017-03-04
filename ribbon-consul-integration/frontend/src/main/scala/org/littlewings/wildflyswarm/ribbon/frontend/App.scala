package org.littlewings.wildflyswarm.ribbon.frontend

import org.jboss.shrinkwrap.api.ShrinkWrap
import org.wildfly.swarm.Swarm
import org.wildfly.swarm.jaxrs.JAXRSArchive
import org.wildfly.swarm.netflix.ribbon.RibbonArchive
import org.wildfly.swarm.topology.TopologyArchive

object App {
  def main(args: Array[String]): Unit = {
    val swarm = new Swarm(args: _*)

    val deployment = ShrinkWrap.create(classOf[JAXRSArchive])
    deployment.addResource(classOf[FrontResource])
    deployment.addClass(classOf[TimeService])
    deployment.addClass(classOf[MessageService])
    deployment.addAllDependencies()
    deployment.as(classOf[TopologyArchive]).advertise("frontend")
    // deployment.as(classOf[RibbonArchive]).advertise("frontend")  // もしくはこちら

    swarm.start().deploy(deployment)
  }
}
