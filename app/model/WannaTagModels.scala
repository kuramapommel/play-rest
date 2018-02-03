package model

import play.api.libs.json.Json

case class WannaTag( wannatagId: Int, title: String, body: String, username: String, postDate: Long, isOwner: Boolean )
object WannaTag {
  implicit def jsonWrites = Json.writes[WannaTag]
}

case class PostWannaTag( title: String, body: String, userId: Long )
object PostWannaTag {
  implicit def jsonReads = Json.reads[PostWannaTag]
}