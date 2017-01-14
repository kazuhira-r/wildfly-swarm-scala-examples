package org.littlewings.wildflyswarm.consul

import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.spec.WebArchive
import org.wildfly.swarm.Swarm
import org.wildfly.swarm.jaxrs.JAXRSArchive
import org.wildfly.swarm.topology.TopologyArchive

object App {
  def main(args: Array[String]): Unit = {
    val swarm = new Swarm(args: _*)
    val deployment = ShrinkWrap.create(classOf[JAXRSArchive])
    //deployment.as(classOf[TopologyArchive]).advertise()  // AdvertiseがWARの名前になる
    deployment.as(classOf[TopologyArchive]).advertise("swarm")

    deployment.addResource(classOf[HostNameResource])

    swarm.start().deploy(deployment)
  }
}
