package yoda.huddl.live.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.AppUtils.AppUtils
import yoda.huddl.live.Offline.HuddlOfflineDataManager
import yoda.huddl.live.R
import yoda.huddl.live.SessionManager
import yoda.huddl.live.databinding.FragmentCreateProfileBinding
import yoda.huddl.live.models.CreateUserProfile
import yoda.huddl.live.models.UserProfile
import yoda.huddl.live.ui.auth.authListener.AuthenticationListener
import yoda.huddl.live.ui.auth.dialog.AuthenticationDialog
import yoda.huddl.live.ui.main.MainActivity
import javax.inject.Inject


@AndroidEntryPoint
class CreateProfile : Fragment(), View.OnClickListener, AuthenticationListener {

    var token: String? = ""
    var code: String? = ""
    lateinit var fragmentCreateProfileBinding: FragmentCreateProfileBinding
    lateinit var validatedBio: String
    lateinit var validatedEmail: String
    lateinit var validatedName: String
    lateinit var validatedIgUsername : String
    lateinit var validatedIgAuthToken : String

    lateinit var authenticationDialog: AuthenticationDialog

    @Inject
    lateinit var sessionManager: SessionManager

//    private val authViewModel = (activity as AuthActivity).authViewModel

//    @Inject

    private val TAG = "CreateProfile"
//    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun setListToCategoryMenu() {
        val items = listOf("Chennai", "Delhi", "Mumbai", "Pune")
        val adapter = context?.let { ArrayAdapter(it, R.layout.dropdown_menu_list_item, items) }
        fragmentCreateProfileBinding.selcatTv.setAdapter(adapter)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        fragmentCreateProfileBinding = binding
        setListToCategoryMenu()
        fragmentCreateProfileBinding.connectToInstaTv.setOnClickListener(this)
        setMobNo()
        return binding.root
    }

    private fun setMobNo() {
        HuddlOfflineDataManager.getUserMobNo()?.let {
            fragmentCreateProfileBinding.mobileInputEt.setText(it)
            fragmentCreateProfileBinding.mobileInputEt.isEnabled = false
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateProfile().apply {
                arguments = Bundle().apply {

                }
            }
    }

    private fun createInstaAuthenticationDialog() {
        disableInputs()
        context?.let {
            authenticationDialog = AuthenticationDialog(it, this)
            authenticationDialog.setCancelable(true)
            authenticationDialog.show()
        }
    }

    private fun disableInputs() {
        fragmentCreateProfileBinding.nameInputEt.isEnabled = false
        fragmentCreateProfileBinding.bioInputEt.isEnabled = false
        fragmentCreateProfileBinding.emailInputEt.isEnabled = false
        fragmentCreateProfileBinding.mobileInputEt.isEnabled = false
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.connectToInstaTv -> {
                validateInputFields()
            }
        }
    }

    private fun validateInputFields() {
        fragmentCreateProfileBinding.nameInputEt.text?.let { name ->
            if (AppUtils.validateName(name.toString())) {
                validatedName = name.toString()
                fragmentCreateProfileBinding.bioInputEt.text?.let { bio ->
                    if (AppUtils.validateBio(bio.toString())) {
                        validatedBio = bio.toString()
                        fragmentCreateProfileBinding.emailInputEt.text?.let { email ->
                            if (AppUtils.validateEmail(email.toString())) {
                                validatedEmail = email.toString()
                                createInstaAuthenticationDialog()
                            } else {
                                if (email.isNullOrBlank())
                                    fragmentCreateProfileBinding.emailInputEt.error =
                                        "* Required"
                                else
                                    fragmentCreateProfileBinding.emailInputEt.error =
                                        "Please enter valid Email"
                            }
                        }
                    } else {
                        if (bio.isNullOrBlank())
                            fragmentCreateProfileBinding.bioInputEt.error = "* Required"
                        else
                            fragmentCreateProfileBinding.bioInputEt.error =
                                "Please enter valid Bio > 40 characters"
                    }
                }
            } else {
                if (name.isNullOrBlank())
                    fragmentCreateProfileBinding.nameInputEt.error = "* Required"
                else
                    fragmentCreateProfileBinding.nameInputEt.error =
                        "Please enter valid name"
            }
        }
    }

    override fun onTokenReceived(auth_token: String?) {
        if (auth_token == null)
            return;
//        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        token = auth_token;
    }

    override fun onCodeReceived(code: String?) {
        if (code == null)
            return;
//        appPreferences.putString(AppPreferences.TOKEN, auth_token);
        this.code = code;
        getIgAuthToken(code)
    }

    private fun getIgAuthToken(authCode: String) {
        (activity as AuthActivity).authViewModel.getIgAuthToken(
            authCode
        ).observe(viewLifecycleOwner, Observer {
            validatedIgAuthToken = it.access_token
            getIgUserProfile(it.access_token)
        })
    }

    private fun getIgUserProfile(access_token: String) {
        (activity as AuthActivity).authViewModel.getIgUserProfile(access_token)
            .observe(viewLifecycleOwner, Observer {
                validatedIgUsername = it.username
                Toast.makeText(context, it.username, Toast.LENGTH_SHORT).show()
                createUserProfileAndAuthenticateUser()
            })
    }

    private fun createUserProfileAndAuthenticateUser() {
        (activity as AuthActivity).authViewModel.createUserProfile(CreateUserProfile(name = validatedName, bio = validatedBio, email = validatedEmail, username = validatedIgUsername, category = 1L)
        ).observe(viewLifecycleOwner, Observer {
            sessionManager.authenticateWithId(liveData { AuthStateInstagramAuthenticated })
            HuddlOfflineDataManager.setUserInstaAuthenticated(true)
            (activity as AuthActivity).navigateUserToHuddleMain()
        })
    }





}