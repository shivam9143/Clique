package yoda.huddl.live.models

data class UserProfile (
    val id : Int,
    val email : String,
    val username : String,
    val name : String,
    val bio : String,
    val category : Long,
    val mobile_number : String,
    val huddl_link : String,
    val instagram_link : String,
    val youtube_link : String,
    val is_instagram_connected : Boolean,
    val links : List<String>,
    val photos : List<String>,
    val videos : List<String>
)