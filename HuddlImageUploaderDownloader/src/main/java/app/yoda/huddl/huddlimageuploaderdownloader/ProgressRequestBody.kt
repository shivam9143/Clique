package app.yoda.huddl.huddlimageuploaderdownloader

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream


class ProgressRequestBody(
    var mFile: File,
    var content_type: String,
    var currentImageProgressLiveData: MutableLiveData<StatusOfUpload>,
    var content_uri: String
) : RequestBody() {

    init {
        StatusOfUpload.status = STATUS.STARTED
        StatusOfUpload.content_uri = content_uri
        StatusOfUpload.percentage = 0
        currentImageProgressLiveData.postValue(StatusOfUpload)
    }

    override fun contentType(): MediaType? {
        return "$content_type/*".toMediaTypeOrNull();
    }

    override fun writeTo(sink: BufferedSink) {
        val fileLength = mFile.length()
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val `in` = FileInputStream(mFile)
        var uploaded: Long = 0
        StatusOfUpload.content_uri = content_uri
        StatusOfUpload.status = STATUS.IN_PROGRESS

        try {
            var read: Int
            val handler = Handler(Looper.getMainLooper())
            while (`in`.read(buffer).also { read = it } != -1) {
                uploaded += read.toLong()
                sink.write(buffer, 0, read)
                handler.post(ProgressUpdater(uploaded, fileLength, currentImageProgressLiveData))
            }
        } finally {
            `in`.close()
        }
    }
        class ProgressUpdater(
            private val mUploaded: Long, private val mTotal: Long,
            private var currentImageProgressLiveData: MutableLiveData<StatusOfUpload>
        ) :
            Runnable {
            override fun run() {
                try {
                    val progress = (100 * mUploaded / mTotal).toInt()
                    StatusOfUpload.percentage = progress
                    currentImageProgressLiveData.postValue(StatusOfUpload)
                } catch (e: ArithmeticException) {
                    StatusOfUpload.status = STATUS.ERROR
                    StatusOfUpload.percentage = 0
                    currentImageProgressLiveData.postValue(StatusOfUpload)
                    e.printStackTrace()
                }
            }


    }

    override fun contentLength(): Long {
        return mFile.length()
    }
}

interface UploadCallbacks {
    fun uploadStart()
    fun onProgressUpdate(percentage: Int)
    fun onError()
    fun onFinish()
}
