package yoda.huddl.live.ui.auth

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.*
import yoda.huddl.live.Offline.HuddlOfflineDataManager
import yoda.huddl.live.R
import yoda.huddl.live.databinding.ActivityAuthBinding
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class AuthActivity : HuddleBaseActivity(), View.OnClickListener {

    private val TAG = "AuthActivity"

    private val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"

    private val STATE_INITIALIZED = 1
    private val STATE_CODE_SENT = 2
    private val STATE_VERIFY_FAILED = 3
    private val STATE_VERIFY_SUCCESS = 4
    private val STATE_SIGNIN_FAILED = 5
    private val STATE_SIGNIN_SUCCESS = 6

    // [START declare_auth]
    private lateinit var mAuth: FirebaseAuth
    // [END declare_auth]

    lateinit var binding: ActivityAuthBinding

    val authViewModel: AuthViewModel by viewModels()

    lateinit var phoneNumber: String


    // [END declare_auth]
    private var mVerificationInProgress = false
    private var mVerificationId: String? = null
    private var mResendToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCallbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null

    private lateinit var mPhoneNumberViews: ViewGroup
    private lateinit var mSignedInViews: ViewGroup

    private lateinit var mStatusText: TextView
    private lateinit var mDetailText: TextView

    private lateinit var mPhoneNumberField: EditText
    private lateinit var mVerificationField: EditText

    private lateinit var mStartButton: Button
    private lateinit var mVerifyButton: Button
    private lateinit var mResendButton: Button
    private lateinit var mSignOutButton: Button

//    @Inject
//    lateinit var daggerViewModelFactory: DaggerViewModelFactory<AuthViewModel>

//    lateinit var authViewModel: AuthViewModel

    lateinit var facebookCallbackManager: CallbackManager

    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var loginBtn: LoginButton
    lateinit var navController: NavController


//    private var activityComponent: ActivityComponent? = null

    private val mTvUserInfo: TextView? = null
    private val mTvAccessToken: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
//        initViewModel()
        initNav()
//        getActivityComponent()?.inject(this)
        if (savedInstanceState != null) {
            onRestoreInstanceState(savedInstanceState)
        }
    }

    private fun initNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.authNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraphIds = listOf(R.navigation.nav_graph_main)
        observeAuthState()
//        navController = Navigation.findNavController(binding.authNavHostFragment)
//        appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.mobileNumberFrag,
//                R.id.otpVerification,
//                R.id.createProfile
//            )
//        )
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    private fun init() {
//        mPhoneNumberViews = binding.phoneAuthFields
//        mSignedInViews = binding.signedInButtons
//
//        mStatusText = binding.status
//        mDetailText = binding.detail
//
//        mPhoneNumberField = binding.fieldPhoneNumber
//        mVerificationField = binding.fieldVerificationCode
//
//        mVerifyButton = binding.buttonVerifyPhone
//        mResendButton = binding.buttonResend
//        mSignOutButton = binding.signOutButton
//
//        // Assign click listeners
//
//        // Assign click listeners
//        mVerifyButton?.setOnClickListener(this)
//        mResendButton?.setOnClickListener(this)
//        mSignOutButton?.setOnClickListener(this)

        // [START initialize_auth]

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance()
        // [END initialize_auth]
        // [END initialize_auth]
        mCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
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
                updateUI(
                    STATE_VERIFY_SUCCESS,
                    credential
                )
                // [END_EXCLUDE]
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                // [START_EXCLUDE silent]
                mVerificationInProgress = false
                // [END_EXCLUDE]
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
//                    mPhoneNumberField?.setError("Invalid phone number.")
                    // [END_EXCLUDE]
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Snackbar.make(
                        findViewById(android.R.id.content), "Quota exceeded.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    // [END_EXCLUDE]
                }

                // Show a message and update the UI
                // [START_EXCLUDE]
                updateUI(STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(
                    TAG,
                    "onCodeSent:$verificationId"
                )

                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId
                mResendToken = token

                // [START_EXCLUDE]
                // Update UI
                updateUI(STATE_CODE_SENT)
                // [END_EXCLUDE]
            }
        }
    }


//    private fun initViewModel() {
//        authViewModel = ViewModelProvider(this)[
//
//
//                ::class.java]
//    }

    fun observeAuthState() {
        authViewModel.getAuthState().observe(this, Observer {
            when (it) {
                is AuthStateIdle -> {

                }
                is AuthStateLoading -> {

                }
                is AuthStatePhoneAuthenticated -> {

                }
                is AuthStateInstagramAuthenticated -> {

                }
                is AuthStateError -> {

                }
                is AuthStateNotAuthenticated -> {

                }
            }
        })
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG, data.toString())
        super.onActivityResult(requestCode, resultCode, data)
    }


    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser: FirebaseUser? = mAuth.currentUser
        updateUI(currentUser)

        // [START_EXCLUDE]
//        if (mVerificationInProgress && validatePhoneNumber()) {
//            startPhoneNumberVerification(mPhoneNumberField.getText().toString())
//        }
        // [END_EXCLUDE]
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(
            KEY_VERIFY_IN_PROGRESS,
            mVerificationInProgress
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        mVerificationInProgress =
            savedInstanceState.getBoolean(KEY_VERIFY_IN_PROGRESS)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        // [START start_phone_auth]
        mCallbacks?.let {
            val options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber) // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(this) // Activity (for callback binding)
                .setCallbacks(it) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        } // OnVerificationStateChangedCallbacks
        // [END start_phone_auth]
        mVerificationInProgress = true
    }


    private fun verifyPhoneNumberWithCode(
        verificationId: String,
        code: String,
        callback: (Boolean) -> Unit
    ) {
        // [START verify_with_code]
        val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code)
        // [END verify_with_code]
        signInWithPhoneAuthCredential(credential, callback)
    }

    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken
    ) {
        mCallbacks?.let {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,  // Phone number to verify
                60,  // Timeout duration
                TimeUnit.SECONDS,  // Unit of timeout
                this,  // Activity (for callback binding)
                it,  // OnVerificationStateChangedCallbacks
                token
            )
        } // ForceResendingToken from callbacks
    }

    private fun signInWithPhoneAuthCredential(
        credential: PhoneAuthCredential,
        callback: ((Boolean) -> Unit)? = null
    ) {
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, OnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(
                        TAG,
                        "signInWithCredential:success"
                    )
                    val user: FirebaseUser? = task.result?.user
                    // [START_EXCLUDE]
                    if (user != null) {
                        callback?.invoke(true)
                        user.getIdToken(false).result?.token?.let {
                            authenticateUser(it)
                        }
//                        if(callback == null)
//                        {
//                            //Auto verification Done
//                            navController.navigate(R.id.createProfile)
//                        }
                        updateUI(STATE_SIGNIN_SUCCESS, user)
                    }
                    // [END_EXCLUDE]
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(
                        TAG,
                        "signInWithCredential:failure",
                        task.exception
                    )
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // [START_EXCLUDE silent]
//                        mVerificationField.error = "Invalid code."
                        // [END_EXCLUDE]
                    }
                    // [START_EXCLUDE silent]
                    // Update UI
                    callback?.invoke(false)
                    updateUI(STATE_SIGNIN_FAILED)
                    // [END_EXCLUDE]
                }
            })
    }

    //
//    private fun signOut() {
//        mAuth.signOut()
//        updateUI(STATE_INITIALIZED)
//    }

    private fun updateUI(uiState: Int) {
        updateUI(uiState, mAuth.currentUser, null)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user)
        } else {
            updateUI(STATE_INITIALIZED)
        }
    }

    private fun updateUI(uiState: Int, user: FirebaseUser) {
        updateUI(uiState, user, null)
    }

    private fun updateUI(uiState: Int, cred: PhoneAuthCredential) {
        updateUI(uiState, null, cred)
    }

    private fun updateUI(uiState: Int, user: FirebaseUser?, cred: PhoneAuthCredential?) {
        when (uiState) {
            STATE_INITIALIZED -> {
                // Initialized state, show only the phone number field and start button
//                enableViews(mStartButton, mPhoneNumberField)
//                disableViews(mVerifyButton, mResendButton, mVerificationField)
//                mDetailText.setText(null)
            }
            STATE_CODE_SENT -> {
                // Code sent state, show the verification field, the
//                enableViews(mVerifyButton, mResendButton, mPhoneNumberField, mVerificationField)
//                disableViews(mStartButton)


                var bundle: Bundle = Bundle()
                if (this::phoneNumber.isInitialized)
                    bundle.putString("mobile", this.phoneNumber)
                navController.navigate(R.id.otpVerification, bundle)
//                mDetailText.setText(R.string.status_code_sent)
            }
            STATE_VERIFY_FAILED -> {
                // Verification has failed, show all options
//                navController.navigate(R.id.createProfile)
                navController.navigate(R.id.mobileNumberFrag)
                Toast.makeText(this, "STATE_VERIFY_FAILED", Toast.LENGTH_SHORT).show()
//                enableViews(
//                    mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
//                    mVerificationField
//                )
//                mDetailText.setText(R.string.status_verification_failed)
            }
            STATE_VERIFY_SUCCESS -> {
                // Verification has succeeded, proceed to firebase sign in
//                navController.navigate(R.id.createProfile)
//                disableViews(
//                    mStartButton, mVerifyButton, mResendButton, mPhoneNumberField,
//                    mVerificationField
//                )
//                mDetailText.setText(R.string.status_verification_succeeded)

                // Set the verification text based on the credential
                if (cred != null) {
//                    if (cred.smsCode != null) {
//                        mVerificationField.setText(cred.smsCode)
//                    } else {
//                        mVerificationField.setText(R.string.instant_validation)
//                    }
                }
            }

            STATE_SIGNIN_FAILED -> {

            }// No-op, handled by sign-in check
//                mDetailText.setText(R.string.status_sign_in_failed)
            STATE_SIGNIN_SUCCESS -> {
                saveVerifiedMobileNum()
                navController.navigate(R.id.createProfile)
            }
        }
    }

    private fun saveVerifiedMobileNum() {
        if(this::phoneNumber.isInitialized)
        HuddlOfflineDataManager.setUserMobNo(mobNo = this.phoneNumber)
    }


    private fun validatePhoneNumber(phoneNumber: String): Boolean {
//        val phoneNumber: String = mPhoneNumberField.getText().toString()
//        if (TextUtils.isEmpty(phoneNumber)) {
//            mPhoneNumberField.setError("Invalid phone number.")
//            return false
//        }
        if (phoneNumber.isNotEmpty()) {
            this.phoneNumber = phoneNumber
            return true
        }
        return false
    }

    private fun enableViews(vararg views: View) {
        for (v in views) {
            v.isEnabled = true
        }
    }

    private fun disableViews(vararg views: View) {
        for (v in views) {
            v.isEnabled = false
        }
    }

    fun clickOnSendOtpBtn(countryCode: String, mobNo: String, callback: (Boolean) -> Unit) {
        if (!validatePhoneNumber(mobNo)) {
            callback(false)
            return
        }
        this.phoneNumber = mobNo
        startPhoneNumberVerification(mobNo)
    }

    fun clickOnVerifyPhoneBtn(otp: String, callback: (Boolean) -> Unit) {
        mVerificationId?.let { verifyPhoneNumberWithCode(it, otp, callback) }
    }

    override fun onClick(view: View) {
        when (view.id) {
//            R.id.button_start_verification -> {
//                if (!validatePhoneNumber()) {
//                    return
//                }
//                startPhoneNumberVerification(mPhoneNumberField.getText().toString())
//            }
//            R.id.button_verify_phone -> {
//                val code: String = mVerificationField.getText().toString()
//                if (TextUtils.isEmpty(code)) {
//                    mVerificationField.setError("Cannot be empty.")
//                    return
//                }
//                mVerificationId?.let { verifyPhoneNumberWithCode(it, code) }
//            }
//            R.id.button_resend -> mResendToken?.let {
//                resendVerificationCode(
//                    mPhoneNumberField.getText().toString(),
//                    it
//                )
//            }
//            R.id.sign_out_button -> signOut()
        }
    }

    private fun authenticateUser(firebaseAuthToken: String) {
        authViewModel.authenticateUser(firebaseAuthToken).observe(this, Observer {
            it?.let {
                Log.e(TAG, it.key)
                HuddlOfflineDataManager.setUserAuthToken(it.key)
                HuddlOfflineDataManager.setUserPhoneAuthenticated(true)
                sessionManager.authenticateWithId(liveData { AuthStatePhoneAuthenticated })
            }
        })
    }

    private fun getAuthenticatedUserProfile() {

    }
}