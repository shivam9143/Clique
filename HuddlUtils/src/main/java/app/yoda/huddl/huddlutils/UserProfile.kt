package app.yoda.huddl.huddlutils
//
//data class UserProfile(
//    val id: Int,
//    val email: String,
//    val username: String,
//    val name: String,
//    val bio: String,
//    val category: Long,
//    val mobile_number: String,
//    val huddl_link: String,
//    val instagram_link: String,
//    val youtube_link: String,
//    val is_instagram_connected: Boolean,
//    val links: List<String>,
//    val photos: List<String>,
//    val videos: List<String>
//)

data class UserProfile (

    val id : Int,
    val email : String,
    val username : String,
    val name : String,
    val bio : String,
    val category : Category,
    val mobile_number : String,
    val huddl_link : String,
    val is_verified : Boolean,
    val links : List<Links>,
    val photos : List<String>,
    val videos : List<String>
)


data class Links (
    val id : Int,
    val title : String,
    val link : String,
    val platform : String,
    val is_verified : Boolean
)

data class Category (
    val id : Int,
    val title : String
)

data class CreateUserProfile(
    val email: String,
    val username: String,
    val name: String,
    val bio: String,
    val category: Long
)