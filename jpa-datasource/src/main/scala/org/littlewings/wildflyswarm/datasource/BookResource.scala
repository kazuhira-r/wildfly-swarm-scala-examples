package org.littlewings.wildflyswarm.datasource

import javax.enterprise.context.ApplicationScoped
import javax.persistence.{EntityManager, PersistenceContext}
import javax.transaction.Transactional
import javax.ws.rs._
import javax.ws.rs.core.{Context, MediaType, Response, UriInfo}

@Path("book")
@ApplicationScoped
@Transactional
class BookResource {
  @PersistenceContext
  private[datasource] var entityManager: EntityManager = _

  @GET
  @Path("{isbn}")
  @Produces(Array(MediaType.APPLICATION_JSON))
  def find(@PathParam("isbn") isbn: String): Book =
    entityManager.find(classOf[Book], isbn)

  @PUT
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  def register(book: Book, @Context uriInfo: UriInfo): Response = {
    entityManager.persist(book)

    Response.created(uriInfo.getRequestUriBuilder.path(book.isbn).build()).build
  }
}
