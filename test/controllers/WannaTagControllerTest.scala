package controllers

import org.joda.time.DateTime
import org.scalatestplus.play._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

import play.api.test._
import play.api.test.Helpers._
import service.dao.WannaTagDao
import service.Tables._

import scala.concurrent.Future

case class WannaTagControllerTest() extends PlaySpec with MockitoSugar {

  import scala.concurrent.ExecutionContext.Implicits.global

  private[this] val CONTENT_TYPE_JSON = "application/json"

  private[this] val wannaTagDaoMock = () => {
    val wannaTagDao = mock[WannaTagDao]
    val stubDate = Seq( (RWannatagRow( 1111, "9999", DateTime.now ), EUpdateWannatagRow( 1111, "title", "body" ) ) )

    when( wannaTagDao.all ) thenReturn( Future{ stubDate } )
    wannaTagDao
  }

  private[this] val WANNATAGS_PATH = "/wannatags"
  "WannaTagController GET wannatags" should {

    "get wannatags json from a new instance of controller with default parameter" in {

      val controller = WannaTagController( stubControllerComponents() )( wannaTagDaoMock() )
      val json = controller.getWannaTags( "older", -1, -1 ).apply( FakeRequest( GET, WANNATAGS_PATH ) )

      status( json ) mustBe OK
      contentType( json ) mustBe Some( CONTENT_TYPE_JSON )
    }

  }

  private[this] val WANNATAGS_FEED_PATH = "/wannatagsFeed/"
  "WannaTagController GET wannatagsFeed" should {

    "get wannatagsFeed json from a new instance of controller with default parameter" in {
      val controller = WannaTagController( stubControllerComponents() )( wannaTagDaoMock() )
      val json = controller.getWannaTags( "older", -1, -1 ).apply( FakeRequest( GET, WANNATAGS_FEED_PATH ) )

      status( json ) mustBe OK
      contentType( json ) mustBe Some( CONTENT_TYPE_JSON )
    }

  }
  
}