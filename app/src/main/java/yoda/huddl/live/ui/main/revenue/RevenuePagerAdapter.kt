package yoda.huddl.live.ui.main.revenue

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder

class RevenuePagerAdapter (
    var fragManager: FragmentManager,
    lifeCycle: Lifecycle,
    val user_id: Long
) : FragmentStateAdapter(fragManager, lifeCycle) {

    var show_unpublished_section = true

    override fun getItemCount(): Int {
        if (isUserIdSameAsSignedInUser(user_id)) {
            if (show_unpublished_section)
                return 3
            else
                return 2
        }
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        if (isUserIdSameAsSignedInUser(user_id)) {
            if (position == 0)
                return ProfilePublishedFeedFragment.newInstance(user_id, null)
            else if (position == 1)
                return ProfileSavedPostsFragment.newInstance()
            else
                return ProfileUnpublishedFeedFragment.newInstance()
        } else {
            if (position == 0)
                return ProfilePublishedFeedFragment.newInstance(user_id, null)
            else if (position == 1) {
                var lang: Long? = YodaUserProfileManager.userProfile?.language
                if (lang != null && lang == 1L)
                    return ProfilePublishedFeedFragment.newInstance(user_id, 2)
                else
                    return ProfilePublishedFeedFragment.newInstance(user_id, 1)
            }
            else
                return ProfileUnpublishedFeedFragment.newInstance()
        }
    }

    override fun onBindViewHolder(
        holder: FragmentViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        super.onBindViewHolder(holder, position, payloads)
        if (position == 0) {
            val fragment =
                fragManager.findFragmentByTag("f$position") as? ProfilePublishedFeedFragment
            fragment?.bindData()
        } else if (position == 1) {
            val fragment = fragManager.findFragmentByTag("f$position") as? ProfileSavedPostsFragment
            fragment?.bindData()
        } else if (position == 2) {
            val fragment =
                fragManager.findFragmentByTag("f$position") as? ProfileUnpublishedFeedFragment
            fragment?.bindData()
        }
    }

}