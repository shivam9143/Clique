package yoda.huddl.live.models

import android.net.Uri
import java.io.File

class GalleryItem (var file_path : String, var is_selected : Boolean, var content_uri: Uri) {

    fun getUri(): Uri {
        return content_uri
    }
}