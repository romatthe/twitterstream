package twitter

import play.api.libs.json._
import play.api.libs.functional.syntax._
import scala.util.{Try, Failure, Success}

/** Container for the tweet message received from the stream **/
// FIXME: Add potential Geolocation
case class Tweet(
	id: String,						// "id_str"
	createdAt: String,				// "created_at"
	createdAtMs: String,			// "timestamp_ms"
	text: String,					// "text"
	userName: String,				// "user": { ... , ... , "name": "Joe", ... }
	screenName: String				// "user": { ... , ... , "screen_name": "therealjoe", ... }
)

object Tweet {
	implicit val tweetReads: Reads[Tweet] = (
		(JsPath \ "id_str").read[String] and
		(JsPath \ "created_at").read[String] and
		(JsPath \ "timestamp_ms").read[String] and
		(JsPath \ "text").read[String] and
		(JsPath \ "user" \ "name").read[String] and
		(JsPath \ "user" \ "screen_name").read[String]
	)(Tweet.apply _ )

	implicit val tweetWrites: Writes[Tweet] = (
		(JsPath \ "id").write[String] and
		(JsPath \ "created_at").write[String] and
		(JsPath \ "created_at_ms").write[String] and
		(JsPath \ "text").write[String] and
		(JsPath \ "user_name").write[String] and
		(JsPath \ "user_screen").write[String]
	)(unlift(Tweet.unapply))
}