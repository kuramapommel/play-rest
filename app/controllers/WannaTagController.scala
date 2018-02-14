package controllers

import javax.inject._

import play.api.mvc._
import play.api.libs.json.{JsError, Json}
import model._
import service.dao.WannaTagDao

import scala.concurrent._
import ExecutionContext.Implicits.global

@Singleton
case class WannaTagController @Inject()( components: ControllerComponents )( wannaTagDao: WannaTagDao ) extends AbstractController( components ) {

  def getWannaTags( compare: String, postDate: Long, limit: Int ) = Action.async {
    wannaTagDao.all.map( seq => Ok( Json.toJson( for {
          ( rWannatagRow, eUpdateWannatagRow ) <- seq
        } yield WannaTag( rWannatagRow.wannatagId, eUpdateWannatagRow.title, eUpdateWannatagRow.body, "sample user A", rWannatagRow.createDatetime.getMillis, rWannatagRow.authorId.equals( "9999" ) ) ) )
    )
  }

  def saveWannaTag = Action.async( parse.json ) { implicit request =>
    request.body.validate[PostWannaTag].map { wannatag =>
      wannaTagDao.insert( wannatag ).map { _ =>
        Ok( Json.obj( "result" -> "success" ) )
      }
    }.recoverTotal { e =>
      Future {
        BadRequest( Json.obj( "result" -> "failure", "error" -> JsError.toJson( e ) ) )
      }
    }
  }

  def deleteWannaTag( wannatagId: Long ) = Action.async( wannaTagDao.delete( wannatagId ).map( _ => Ok( "Deleted!" ) ) )

}