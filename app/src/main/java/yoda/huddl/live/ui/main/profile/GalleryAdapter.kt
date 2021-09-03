package yoda.huddl.live.ui.main.profile

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gallery_item_layout.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yoda.huddl.live.R
import yoda.huddl.live.models.GalleryItem


class GalleryAdapter(
    private val mContext: Context,
    private var photosList: MutableList<GalleryItem>,
    var listener: GalleryItemSelectedListener
) :
    RecyclerView.Adapter<GalleryAdapter.GalleryItemViewHolder>() {

    private val TAG: String? = "Adapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryItemViewHolder {
        val vi = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = vi.inflate(R.layout.gallery_item_layout, parent, false)
        return GalleryItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: GalleryItemViewHolder, position: Int) {
//        updateData(holder, position)
    }

    fun didUpdateItem(position: Int) {
        val positions_updated = arrayListOf(position)
        val selected_photos = listener.selectedGalleryItems()
        for (photo in selected_photos) {
            val position_img = photosList.indexOf(photo)
            positions_updated.add(position_img)
        }
        apply {
            for (index in positions_updated)
                notifyItemChanged(index)
        }
    }

    class GalleryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var currentPhoto: GalleryItem? = null
    }

//    private fun updateData(holder: GalleryItemViewHolder, position: Int) =
//        GlobalScope.launch(Dispatchers.Main) {
//            val singlePhotoItem = photosList[position]
//            val existingPhotoPath = holder?.currentPhoto?.content_uri?.path
//            val newPhotoPath = singlePhotoItem?.content_uri?.path
//            Log.d("gallery_path", "${existingPhotoPath} ---- ${newPhotoPath}")
//            if (existingPhotoPath == null || newPhotoPath == null || !newPhotoPath.equals(
//                    existingPhotoPath,
//                    true
//                )
//            ) {
//                Log.d("gallery", "here")
//                try {
//                    // background thread
//                    val bmp = withContext(Dispatchers.Default) {
//                        return@withContext Utils.decodeURI(singlePhotoItem!!.getUri(), true)
//                    }
//                    bmp?.let { holder.itemView.thumbImage.setImageBitmap(it) }
//                } catch (e: Exception) {
//                    Log.e(TAG, e.stackTrace.toString())
//                }
//            }
//            val selected_photos = listener.selectedGalleryItems()
//            if (singlePhotoItem.is_selected) {
//                holder.itemView.item_index_label.visible()
//                val seq_position = selected_photos.indexOf(singlePhotoItem)
//                holder.itemView.item_index_label.text = (seq_position + 1).toString()
//            } else {
//                holder.itemView.item_index_label.gone()
//            }
//            holder.currentPhoto = singlePhotoItem
//
//            holder.itemView.setOnClickListener {
//                singlePhotoItem.is_selected = !singlePhotoItem.is_selected
//                listener.didTapOnPicture(singlePhotoItem)
////                addBorder(holder)
//                Log.d(TAG, "onBindViewHolder: " + singlePhotoItem.file_path)
//                didUpdateItem(holder.position)
//            }
//        }

//    private fun addBorder(holder: GalleryItemViewHolder) {
//        val border = GradientDrawable()
//        border.setColor(ContextCompat.getColor(mContext, R.color.icon_border)) //white background
//        border.setStroke(
//            4,
//            ContextCompat.getColor(mContext, R.color.icon_border)
//        ) //black border with full opacity
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//            holder.itemView.thumbImage.setBackgroundDrawable(border)
//        } else {
//            holder.itemView.thumbImage.background = border
//        }
//    }
}
