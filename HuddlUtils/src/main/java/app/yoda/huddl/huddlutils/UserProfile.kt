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

data class BaseResponse<T> (val message : String, val code : String, val success : Boolean, val data : T)


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
    val photos : List<Photos>,
    val videos : List<String>
)


data class Links (
    val id : Int,
    val title : String,
    val link : String,
    val platform : String,
    val is_verified : Boolean
)

data class Photos (
    val id : Int,
    val photo : String,
    val source : String
)

data class Category (
    val id : Int,
    val title : String
)

//{
//    "id": 7,
//    "photo": "https://huddl-backend-dev-bucket.s3.ap-south-1.amazonaws.com/creator_photos/f8e2a29ef5b94374a601f753bc1ba964.jpg",
//    "source": "IG"
//}

data class CreateUserProfile(
    val email: String,
    val username: String,
    val name: String,
    val bio: String,
    val category: Long
)