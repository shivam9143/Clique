package yoda.huddl.live.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.AppUtils.HuddlUserProfileManager
import yoda.huddl.live.R
import yoda.huddl.live.SessionManager
import yoda.huddl.live.databinding.FragmentCreateProfileBinding
import yoda.huddl.live.databinding.FragmentProfileBinding
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var fragmentProfileBinding: FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentProfileBinding.inflate(inflater, container, false)
        fragmentProfileBinding = binding
        setUserProfile()
        return binding.root
    }

    private fun setUserProfile()
    {
        fragmentProfileBinding.creatorName.text = HuddlUserProfileManager.userProfile?.name
        fragmentProfileBinding.huddlUserName.text = HuddlUserProfileManager.userProfile?.username
        fragmentProfileBinding.userBio.text = HuddlUserProfileManager.userProfile?.bio
        fragmentProfileBinding.userCategory.text = HuddlUserProfileManager.userProfile?.category.toString()
        fragmentProfileBinding.huddlLinkTv.text = HuddlUserProfileManager.userProfile?.huddl_link
    }

}