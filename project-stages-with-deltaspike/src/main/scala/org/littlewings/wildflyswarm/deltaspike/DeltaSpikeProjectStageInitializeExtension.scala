package org.littlewings.wildflyswarm.deltaspike

import javax.enterprise.inject.spi.Extension

import org.apache.deltaspike.core.api.projectstage.ProjectStage
import org.apache.deltaspike.core.util.ProjectStageProducer
import org.jboss.logging.Logger

object DeltaSpikeProjectStageInitializeExtension {
  private[deltaspike] val logger = Logger.getLogger(classOf[DeltaSpikeProjectStageInitializeExtension])

  {
    val swarmProjectStage = System.getProperty("swarm.project.stage", "production")

    val filteredProjectStages =
      ProjectStage
        .values()
        .filter(_.toString.equalsIgnoreCase(swarmProjectStage))

    if (filteredProjectStages.nonEmpty) {
      val selectedDeltaSpikeProjectStage = filteredProjectStages(0)

      logger.infof("set DeltaSpike ProjectStage: [%s]", selectedDeltaSpikeProjectStage)
      ProjectStageProducer.setProjectStage(selectedDeltaSpikeProjectStage)
    } else {
      throw new IllegalStateException(s"Unknown ProjectStage[$swarmProjectStage]")
    }
  }
}

class DeltaSpikeProjectStageInitializeExtension extends Extension {
  DeltaSpikeProjectStageInitializeExtension
}
