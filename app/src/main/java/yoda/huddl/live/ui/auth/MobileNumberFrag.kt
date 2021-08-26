package yoda.huddl.live.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import yoda.huddl.live.databinding.FragmentMobileNumberBinding
import java.util.concurrent.TimeUnit


class MobileNumberFrag : Fragment(), View.OnClickListener {

    private val TAG = "MobileNumberFrag"

    lateinit var binding: FragmentMobileNumberBinding
    private lateinit var mAuth: FirebaseAuth
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mVerificationInProgress = false
    private var mVerificationId: String? = null
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMobileNumberBinding.inflate(layoutInflater)
        return binding.root
    }

    private fun init() {
        mAuth = FirebaseAuth.getInstance()

        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
//                 This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verificaiton without
                //     user action.
                Log.d(
                    TAG,
                    "onVerificationCompleted:$credential"
                )
                // [START_EXCLUDE silent]
                mVerificationInProgress = false
                // [END_EXCLUDE]

                // [START_EXCLUDE silent]
                // Update the UI and attempt sign in with the phone credential
//                updateUI(
//                    STATE_VERIFY_SUCCESS,
//                    credential
//                )
//                // [END_EXCLUDE]
//                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                TODO("Not yet implemented")
            }

        }

    }


    private fun updateUI(uiState: Int, user: FirebaseUser?, cred: PhoneAuthCredential?) {
//        when (uiState) {
//            STATE_INITIALIZED -> {
//                // Initialized state, show only the phone number field and start button
//                enableViews(mStartButton, mPhoneNumberField)
//                disableViews(mVerifyButton, mResendButton, mVerificationField)
//                mDetailText.setText(null)
//            }
//            STATE_CODE_SENT -> {
//                // Code sent state, show the verification field, the
//                enableViews(mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField)
//                disableViews(mStartButton)
//                mDetailText.setText(R.string.status_code_sent)
//            }
//            STATE_VERIFY_FAILED -> {
//                // Verification has failed, show all options
//                enableViews(
//                    mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
//                    mVerificationField
//                )
//                mDetailText.setText(R.string.status_verification_failed)
//            }
//            STATE_VERIFY_SUCCESS -> {
//                // Verification has succeeded, proceed to firebase sign in
//                disableViews(
//                    mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
//                    mVerificationField
//                )
//                mDetailText.setText(R.string.status_verification_succeeded)
//
//                // Set the verification text based on the credential
//                if (cred != null) {
//                    if (cred.getSmsCode() != null) {
//                        mVerificationField.setText(cred.getSmsCode())
//                    } else {
//                        mVerificationField.setText(R.string.instant_validation)
//                    }
//                }
//            }
//            STATE_SIGNIN_FAILED ->                 // No-op, handled by sign-in check
//                mDetailText.setText(R.string.status_sign_in_failed)
//            STATE_SIGNIN_SUCCESS -> {
//            }
//        }
//        if (user == null) {
//            // Signed out
//            mPhoneNumberViews.setVisibility(View.VISIBLE)
//            mSignedInViews.setVisibility(View.GONE)
//            mStatusText.setText(R.string.signed_out)
//        } else {
//            // Signed in
//            mPhoneNumberViews?.setVisibility(View.GONE)
//            mSignedInViews.setVisibility(View.VISIBLE)
//            enableViews(mPhoneNumberField, mVerificationField)
//            mPhoneNumberField.setText(null)
//            mVerificationField.setText(null)
//            mStatusText.setText(R.string.signed_in)
//            mDetailText.setText(getString(R.string.firebase_status_fmt, user.getUid()))
//        }
    }


    private fun startPhoneNumberVerification(phoneNumber: String) {

        // [START start_phone_auth]
        mCallbacks?.let {
            val options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(activity as AuthActivity) // Activity (for callback binding)
                .setCallbacks(it) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)

        } // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]

        mVerificationInProgress = true
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MobileNumberFrag().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}