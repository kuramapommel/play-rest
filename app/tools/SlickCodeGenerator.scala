package tools

import slick.codegen.SourceCodeGenerator
import slick.jdbc.JdbcBackend._
import slick.{ model => m }

import scala.concurrent.{ Await, ExecutionContext }
import scala.concurrent.duration.Duration

object SlickCodeGenerator extends App {

  private[this] val slickDriver = "slick.jdbc.PostgresProfile"
  private[this] val jdbcDriver = "org.postgresql.Driver"
  private[this] val url = sys.env( "POSTGRESQL_URL" )
  private[this] val outputFolder = "app"
  private[this] val pkg = "service"
  private[this] val user = sys.env( "POSTGRESQL_USER_NAME" )
  private[this] val password = sys.env( "POSTGRESQL_USER_PASSWORD" )

  val driver = slick.jdbc.PostgresProfile
  val db = { Database.forURL( url, driver = jdbcDriver, user = user, password = password ) }
  val model = Await.result( db.run( driver.createModel( None, false )( ExecutionContext.global ).withPinnedSession ), Duration.Inf )

  CustomSourceCodeGenerator( model ).writeToFile( slickDriver, outputFolder, pkg )

}

case class CustomSourceCodeGenerator( model: m.Model ) extends SourceCodeGenerator( model ) {

  override def code = "import com.github.tototoshi.slick.PostgresJodaSupport._\n" + "import org.joda.time.DateTime\n" + super.code

  override def Table = new Table( _ ) {
    override def Column = new Column( _ ) {
      override def rawType = model.tpe match {
        case "java.sql.Timestamp" => "DateTime"
        case _ => super.rawType
      }
    }
  }
}