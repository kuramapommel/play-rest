package controllers

import org.scalatestplus.play._
import play.api.test._
import play.api.test.Helpers._

case class IndexControllerSpec() extends PlaySpec {

  private[this] val ANY_PATH = "/home"

  private[this] val REQUEST_GET_HOME = FakeRequest( GET, "/" )
  private[this] val REQUEST_GET_ANY_PATH = FakeRequest( GET, ANY_PATH )

  private[this] val CONTENT_TYPE_TEXT = "text/html"

  "IndexController GET index" should {

    "render the index page from a new instance of controller" in {
      val controller = IndexController( stubControllerComponents() )
      val home = controller.index.apply( REQUEST_GET_HOME )

      status( home ) mustBe OK
      contentType( home ) mustBe Some( CONTENT_TYPE_TEXT )
    }

  }

  "IndexController GET indexAll" should {

    "render the index page from a new instance of controller" in {
      val controller = IndexController( stubControllerComponents() )
      val home = controller.indexAll( ANY_PATH ).apply( REQUEST_GET_ANY_PATH )

      status( home ) mustBe OK
      contentType( home ) mustBe Some( CONTENT_TYPE_TEXT )
    }

  }
  
}