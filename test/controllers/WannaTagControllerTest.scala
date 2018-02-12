package controllers

import javax.inject.Inject

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._
import service.dao.WannaTagDao

case class WannaTagControllerTest @Inject()( wannaTagDao: WannaTagDao ) extends PlaySpec {

  private[this] val CONTENT_TYPE_JSON = "application/json"

  private[this] val WANNATAGS_PATH = "/wannatags"
  "WannaTagController GET wannatags" should {

    "get wannatags json from a new instance of controller with default parameter" in {

      val controller = WannaTagController( stubControllerComponents() )( wannaTagDao )
      val json = controller.getWannaTags( "older", -1, -1 ).apply( FakeRequest( GET, WANNATAGS_PATH ) )

      status( json ) mustBe OK
      contentType( json ) mustBe Some( CONTENT_TYPE_JSON )
    }

  }

  private[this] val WANNATAGS_FEED_PATH = "/wannatagsFeed/"
  "WannaTagController GET wannatagsFeed" should {

    "get wannatagsFeed json from a new instance of controller with default parameter" in {
      val controller = WannaTagController( stubControllerComponents() )( wannaTagDao )
      val json = controller.getWannaTags( "older", -1, -1 ).apply( FakeRequest( GET, WANNATAGS_FEED_PATH ) )

      status( json ) mustBe OK
      contentType( json ) mustBe Some( CONTENT_TYPE_JSON )
    }

  }
  
}