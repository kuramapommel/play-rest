package controllers

import javax.inject._

import play.api.mvc._

@Singleton
case class IndexController @Inject()( components: ControllerComponents ) extends AbstractController( components ) {

  def index = indexPage

  def indexAll( path: String ) = indexPage

  private[this] def indexPage = controllers.Assets.at(path="/public", file="index.html")

}