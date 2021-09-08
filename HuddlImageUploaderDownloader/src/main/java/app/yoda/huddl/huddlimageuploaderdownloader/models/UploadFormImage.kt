package app.yoda.huddl.huddlimageuploaderdownloader.models

import android.net.Uri

data class UploadFormImage(  val image_path: String,
                             var original_pos: Long,
                             val is_server_image: Boolean = false,
                             val content_uri: String? = null,
                             val creative_id: Long? = null,
                             var is_uploaded: Boolean = false,
                             var is_allowed_to_upload: Boolean = false,
                             var uploaded_id: Long = -1,
                             var is_failed_to_upload : Boolean = false) {
    fun getUri(): Uri? {
        return Uri.parse(content_uri)
    }
}