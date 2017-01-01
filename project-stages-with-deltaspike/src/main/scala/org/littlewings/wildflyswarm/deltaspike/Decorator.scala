package org.littlewings.wildflyswarm.deltaspike

import javax.enterprise.context.ApplicationScoped

import org.apache.deltaspike.core.api.exclude.Exclude
import org.apache.deltaspike.core.api.projectstage.ProjectStage

trait Decorator {
  def decorate(message: String): String
}

@ApplicationScoped
@Exclude(ifProjectStage = Array(classOf[ProjectStage.Development]))
class AsteriskDecorator extends Decorator {
  override def decorate(message: String) = s"***${message}***"
}

@ApplicationScoped
@Exclude(exceptIfProjectStage = Array(classOf[ProjectStage.Development]))
class PipeDecorator extends Decorator {
  override def decorate(message: String) = s"[Development] |${message}|"
}
