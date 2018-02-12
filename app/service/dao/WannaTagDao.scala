package service.dao

import javax.inject.Inject

import org.joda.time.DateTime
import service._
import model._
import play.api.db.slick._
import slick.driver.JdbcProfile

import scala.concurrent.ExecutionContext.Implicits.global

case class WannaTagDao @Inject() ( override val dbConfigProvider: DatabaseConfigProvider ) extends HasDatabaseConfigProvider[JdbcProfile] {
  import profile.api._

  private[this] val rWannatag = TableQuery[Tables.RWannatag]
  private[this] val eUpdateWannatag = TableQuery[Tables.EUpdateWannatag]

  def all = select( None, None )

  def insert( model: PostWannaTag ) = {
    val now = DateTime.now

    val action = ( for {
        newId <- ( rWannatag returning rWannatag.map( _.wannatagId ) ) += Tables.RWannatagRow( 0, model.userId.toString, now )
        _ <- eUpdateWannatag += Tables.EUpdateWannatagRow( newId, model.title, model.body )
      } yield newId ).transactionally

    db.run( action )
  }

  def delete( wannatagId: Long ) = {
    val deleteWannatag = rWannatag.filter( wannatag => wannatag.wannatagId === wannatagId.toInt ).delete
    val deleteUpdateWannatag = eUpdateWannatag.filter( updateWannatag => updateWannatag.wannatagId === wannatagId.toInt ).delete
    val action = DBIO.seq( deleteUpdateWannatag, deleteWannatag ).transactionally

    db.run( action )
  }

  def select( wannaTagId: Option[Int], postDate: Option[Long] ) = {
    val action = ( wannaTagId, postDate ) match {
      case ( None, None ) => ( for {
        rWannatagRow <- rWannatag
        eUpdateWannatagRow <- eUpdateWannatag if rWannatagRow.wannatagId === eUpdateWannatagRow.wannatagId
      } yield ( rWannatagRow, eUpdateWannatagRow ) ).result

      case ( Some( wannatagId ), None ) => ( for {
        rWannatagRow <- rWannatag if rWannatagRow.wannatagId === wannatagId
        eUpdateWannatagRow <- eUpdateWannatag if rWannatagRow.wannatagId === eUpdateWannatagRow.wannatagId
      } yield ( rWannatagRow, eUpdateWannatagRow ) ).result

      case ( None, Some( postDate ) ) => ( for {
        rWannatagRow <- rWannatag if rWannatagRow.createDatetime == new DateTime( postDate )
        eUpdateWannatagRow <- eUpdateWannatag if rWannatagRow.wannatagId === eUpdateWannatagRow.wannatagId
      } yield ( rWannatagRow, eUpdateWannatagRow ) ).result

      case ( Some( wannatagId ), Some( postDate ) ) => ( for {
        rWannatagRow <- rWannatag if rWannatagRow.wannatagId === wannatagId && rWannatagRow.createDatetime == new DateTime( postDate )
        eUpdateWannatagRow <- eUpdateWannatag if rWannatagRow.wannatagId === eUpdateWannatagRow.wannatagId
      } yield ( rWannatagRow, eUpdateWannatagRow ) ).result
    }

    db.run( action )
  }

}