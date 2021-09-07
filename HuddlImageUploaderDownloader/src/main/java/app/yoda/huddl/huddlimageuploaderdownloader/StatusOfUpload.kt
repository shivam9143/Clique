package app.yoda.huddl.huddlimageuploaderdownloader

enum class STATUS {
    INITIALIZED, STARTED, IN_PROGRESS, SUCCESS, ERROR
}

object StatusOfUpload{
    var status: STATUS = STATUS.INITIALIZED
    var percentage: Int = 0
    var content_uri : String = ""
}