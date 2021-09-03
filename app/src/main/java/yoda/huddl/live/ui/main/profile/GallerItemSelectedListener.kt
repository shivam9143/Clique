package yoda.huddl.live.ui.main.profile

import yoda.huddl.live.models.GalleryItem

interface GalleryItemSelectedListener {
    fun didTapOnPicture(singlePhotoItem: GalleryItem)
    fun selectedGalleryItems(): List<GalleryItem>
}