package controllers

import javax.inject._

import play.api.mvc._
import play.api.libs.json.{JsError, JsSuccess, Json}
import model._

@Singleton
class WannaTagController @Inject()( components: ControllerComponents ) extends AbstractController( components ) {

  def getWannaTags( compare: String, postDate: Long, limit: Int ) = Action {
    // TODO データはDBから取得する
    val wannatag = WannaTag( 1111, "sample1", "this wannatag is sample.", "sample user A", 1234567890123l, true )
    val wannatags = Seq( wannatag, wannatag, wannatag, wannatag )

    Ok( Json.toJson( wannatags ) )
  }

  def saveWannaTag = Action( parse.json ) { implicit request =>
    request.body.validate[PostWannaTag].map{
      case wannatag => {
        // TODO wannatagをDBに登録する処理を呼ぶ
        println( wannatag.title )
        println( wannatag.body )
        println( wannatag.userId )
        Ok( "saved!" )
      }
    }.recoverTotal{
      case _ => {
        BadRequest( "Error!" )
      }
    }
    Ok( "saved!")
  }

  // TODO wannatagIdを元に対象のwannatagを削除する
  def deleteWannaTag( wannatagId: Long ) = Action( Ok( "Deleted!" ) )

}