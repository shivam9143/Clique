package app.yoda.huddl.huddlimageuploaderdownloader

import app.yoda.huddl.huddlimageuploaderdownloader.models.UploadFormImage

interface UploadImageInteractor {
    fun stopUploadOperationIfAllImagesUploaded(selectedImages : ArrayList<UploadFormImage>)
    fun startNextImageUpload(
        uploadedImage: UploadFormImage,
        canRetry: Boolean
    )
}
