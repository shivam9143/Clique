package yoda.huddl.live.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.AppUtils.HuddlUserProfileManager
import yoda.huddl.live.R
import yoda.huddl.live.databinding.FragmentProfileBinding
import yoda.huddl.live.ui.main.MainActivity

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var fragmentProfileBinding: FragmentProfileBinding
    var adapter: ProfileImagesAdapter? = null
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
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        adapter = activity?.let {
            ProfileImagesAdapter(it, ArrayList<String>(), 2) { clicked ->
                if (clicked) {
                    clickedOnAddPhotosCard()
                }
            }
        }
        fragmentProfileBinding.imagesnVideosRv.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        fragmentProfileBinding.imagesnVideosRv.adapter = adapter
    }

    private fun setUserProfile() {
        fragmentProfileBinding.creatorName.text = HuddlUserProfileManager.userProfile?.name
        fragmentProfileBinding.huddlUserName.text = HuddlUserProfileManager.userProfile?.username
        fragmentProfileBinding.userBio.text = HuddlUserProfileManager.userProfile?.bio
        fragmentProfileBinding.userCategory.text =
            HuddlUserProfileManager.userProfile?.category.toString()
        fragmentProfileBinding.huddlLinkTv.text = HuddlUserProfileManager.userProfile?.huddl_link
    }

    private fun clickedOnAddPhotosCard() {
        (activity as MainActivity).navController.navigate(R.id.addImagesVideosProfileFragment)
    }

}