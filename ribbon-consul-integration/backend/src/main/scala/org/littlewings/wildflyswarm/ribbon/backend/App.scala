package org.littlewings.wildflyswarm.ribbon.backend

import org.jboss.shrinkwrap.api.ShrinkWrap
import org.wildfly.swarm.Swarm
import org.wildfly.swarm.jaxrs.JAXRSArchive
import org.wildfly.swarm.netflix.ribbon.RibbonArchive
import org.wildfly.swarm.topology.TopologyArchive

object App {
  def main(args: Array[String]): Unit = {
    val swarm = new Swarm(args: _*)

    val deployment = ShrinkWrap.create(classOf[JAXRSArchive])
    deployment.addResource(classOf[TimeResource])
    deployment.addResource(classOf[MessageResource])
    deployment.addAllDependencies()
    deployment.as(classOf[TopologyArchive]).advertise("backend")
    // deployment.as(classOf[RibbonArchive]).advertise("backend")  // もしくはこちら

    swarm.start().deploy(deployment)
  }
}
