package yoda.huddl.live.models

data class IGAuthTokenRes(val access_token: String, val user_id: String)

data class IgUserProfile(val id: String, val username: String, val media_count: Int)

//data class IgUserMedia(val id: String, val username: String, val media_count: Int)

data class IgUserMedia(val data: List<Data>, val paging: Paging)

data class Cursors(val before: String, val after: String)

data class Data(val id: Int, val username: String)

data class Paging(val cursors: Cursors, val next: String)
