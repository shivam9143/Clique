package yoda.huddl.live.huddlgallery.model

import android.net.Uri

data class GalleryPicture(val path: String, val uri : Uri, var isSelected : Boolean = false) {

}