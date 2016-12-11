package org.littlewings.wildflyswarm.datasource

import javax.persistence.{Column, Entity, Id, Table}

import scala.beans.BeanProperty

@Entity
@Table(name = "book")
@SerialVersionUID(1L)
class Book extends Serializable {
  @Id
  @BeanProperty
  var isbn: String = _

  @Column
  @BeanProperty
  var title: String = _

  @Column
  @BeanProperty
  var price: Int = _
}
