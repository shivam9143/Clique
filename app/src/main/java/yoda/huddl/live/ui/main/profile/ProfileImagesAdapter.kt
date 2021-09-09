package yoda.huddl.live.ui.main.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.yoda.huddl.huddlutils.Photos
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.profile_images_layout_item.view.*
import yoda.huddl.live.R

class ProfileImagesAdapter(val context: Context, private val urlList: List<Photos>, val minSize : Int, val callback: ((clicked: Boolean) -> Unit)) :
    RecyclerView.Adapter<ProfileImagesAdapter.ProfileImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileImagesViewHolder {
        return ProfileImagesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.profile_images_layout_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProfileImagesViewHolder, position: Int) {
        Glide.with(context).load(urlList[position].photo).into(holder.itemView.profileImage)
        holder.itemView.setOnClickListener {
            callback.invoke(true)
        }
    }

    override fun getItemCount(): Int {
        return minSize
    }

    class ProfileImagesViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView){

    }

}