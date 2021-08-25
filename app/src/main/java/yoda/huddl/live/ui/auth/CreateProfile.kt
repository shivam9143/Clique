package yoda.huddl.live.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private val createprofileVm: CreateProfileVM by viewModels()


//    @Inject
//    lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateProfileBinding.inflate(inflater, container, false)
        fragmentCreateProfileBinding = binding
        fragmentCreateProfileBinding.connectToInstaTv.setOnClickListener {
            createInstaAuthenticationDialog()
        }
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

            R.id.connectToInstaTv1 -> {
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
        createprofileVm.getIgAuthToken(code).observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.access_token, Toast.LENGTH_SHORT).show()
        })
    }

}