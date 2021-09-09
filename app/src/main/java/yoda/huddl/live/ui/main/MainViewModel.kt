package yoda.huddl.live.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import yoda.huddl.live.SessionManager
import javax.inject.Inject

class MainViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    private val TAG = "AuthViewModel"

    init {
        Log.e(TAG, TAG)
    }

    fun getIgUserMedia(token: String) = liveData {
        emit( mainRepository.getIgUserMedia(token))
    }

    fun getIgUserMediaDetail(media_id : String, token: String) = liveData {
        emit( mainRepository.getIgUserMediaDetails(media_id, token))
    }

}