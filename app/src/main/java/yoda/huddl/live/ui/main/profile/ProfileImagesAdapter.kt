package yoda.huddl.live.ui.main.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import yoda.huddl.live.R

class ProfileImagesAdapter(val context: Context, private val urlList: List<String>, val minSize : Int, val callback: ((clicked: Boolean) -> Unit)) :
    RecyclerView.Adapter<ProfileImagesAdapter.ProfileImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileImagesViewHolder {
        return ProfileImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_images_layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileImagesViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            callback.invoke(true)
        }
    }

    override fun getItemCount(): Int {
        return minSize
    }

    class ProfileImagesViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)

}