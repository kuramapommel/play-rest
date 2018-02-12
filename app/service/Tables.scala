package service
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.PostgresProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import com.github.tototoshi.slick.PostgresJodaSupport._
  import org.joda.time.DateTime
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = EUpdateWannatag.schema ++ PlayEvolutions.schema ++ RWannatag.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table EUpdateWannatag
   *  @param wannatagId Database column wannatag_id SqlType(int4), PrimaryKey
   *  @param title Database column title SqlType(varchar), Length(100,true)
   *  @param body Database column body SqlType(varchar), Length(500,true) */
  case class EUpdateWannatagRow(wannatagId: Int, title: String, body: String)
  /** GetResult implicit for fetching EUpdateWannatagRow objects using plain SQL queries */
  implicit def GetResultEUpdateWannatagRow(implicit e0: GR[Int], e1: GR[String]): GR[EUpdateWannatagRow] = GR{
    prs => import prs._
    EUpdateWannatagRow.tupled((<<[Int], <<[String], <<[String]))
  }
  /** Table description of table e_update_wannatag. Objects of this class serve as prototypes for rows in queries. */
  class EUpdateWannatag(_tableTag: Tag) extends profile.api.Table[EUpdateWannatagRow](_tableTag, "e_update_wannatag") {
    def * = (wannatagId, title, body) <> (EUpdateWannatagRow.tupled, EUpdateWannatagRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(wannatagId), Rep.Some(title), Rep.Some(body)).shaped.<>({r=>import r._; _1.map(_=> EUpdateWannatagRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column wannatag_id SqlType(int4), PrimaryKey */
    val wannatagId: Rep[Int] = column[Int]("wannatag_id", O.PrimaryKey)
    /** Database column title SqlType(varchar), Length(100,true) */
    val title: Rep[String] = column[String]("title", O.Length(100,varying=true))
    /** Database column body SqlType(varchar), Length(500,true) */
    val body: Rep[String] = column[String]("body", O.Length(500,varying=true))

    /** Foreign key referencing RWannatag (database name e_update_wannatag_wannatag_id_fkey) */
    lazy val rWannatagFk = foreignKey("e_update_wannatag_wannatag_id_fkey", wannatagId, RWannatag)(r => r.wannatagId, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table EUpdateWannatag */
  lazy val EUpdateWannatag = new TableQuery(tag => new EUpdateWannatag(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: DateTime, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[DateTime], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[DateTime], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends profile.api.Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[DateTime] = column[DateTime]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table RWannatag
   *  @param wannatagId Database column wannatag_id SqlType(serial), AutoInc, PrimaryKey
   *  @param authorId Database column author_id SqlType(varchar), Length(80,true)
   *  @param createDatetime Database column create_datetime SqlType(timestamptz) */
  case class RWannatagRow(wannatagId: Int, authorId: String, createDatetime: DateTime)
  /** GetResult implicit for fetching RWannatagRow objects using plain SQL queries */
  implicit def GetResultRWannatagRow(implicit e0: GR[Int], e1: GR[String], e2: GR[DateTime]): GR[RWannatagRow] = GR{
    prs => import prs._
    RWannatagRow.tupled((<<[Int], <<[String], <<[DateTime]))
  }
  /** Table description of table r_wannatag. Objects of this class serve as prototypes for rows in queries. */
  class RWannatag(_tableTag: Tag) extends profile.api.Table[RWannatagRow](_tableTag, "r_wannatag") {
    def * = (wannatagId, authorId, createDatetime) <> (RWannatagRow.tupled, RWannatagRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(wannatagId), Rep.Some(authorId), Rep.Some(createDatetime)).shaped.<>({r=>import r._; _1.map(_=> RWannatagRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column wannatag_id SqlType(serial), AutoInc, PrimaryKey */
    val wannatagId: Rep[Int] = column[Int]("wannatag_id", O.AutoInc, O.PrimaryKey)
    /** Database column author_id SqlType(varchar), Length(80,true) */
    val authorId: Rep[String] = column[String]("author_id", O.Length(80,varying=true))
    /** Database column create_datetime SqlType(timestamptz) */
    val createDatetime: Rep[DateTime] = column[DateTime]("create_datetime")
  }
  /** Collection-like TableQuery object for table RWannatag */
  lazy val RWannatag = new TableQuery(tag => new RWannatag(tag))
}
