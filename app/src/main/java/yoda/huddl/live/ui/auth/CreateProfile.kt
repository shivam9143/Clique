package yoda.huddl.live.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.R
import yoda.huddl.live.databinding.FragmentCreateProfileBinding
import yoda.huddl.live.ui.auth.authListener.AuthenticationListener
import yoda.huddl.live.ui.auth.dialog.AuthenticationDialog
import javax.inject.Inject


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class CreateProfile : Fragment(), View.OnClickListener, AuthenticationListener {

    private var param1: String? = null
    private var param2: String? = null
    var token: String? = ""
    var code: String? = ""
    lateinit var fragmentCreateProfileBinding: FragmentCreateProfileBinding

    lateinit var authenticationDialog: AuthenticationDialog

//    private val authViewModel = (activity as AuthActivity).authViewModel


//    @Inject
//    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
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
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateProfile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun createInstaAuthenticationDialog() {
        context?.let {
            authenticationDialog = AuthenticationDialog(it, this)
            authenticationDialog.setCancelable(true)
            authenticationDialog.show()
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.connectToInstaTv -> {
                createInstaAuthenticationDialog()
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
            getIgUserProfile(it.access_token)
        })
    }

    private fun getIgUserProfile(access_token: String) {
        (activity as AuthActivity).authViewModel.getIgUserProfile(access_token)
            .observe(viewLifecycleOwner, Observer {
                Toast.makeText(context, it.username, Toast.LENGTH_SHORT).show()
            })
    }

}