package yoda.huddl.live.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import yoda.huddl.live.AppUtils.GalleryDataManager
import yoda.huddl.live.databinding.FragmentAddImagesVideosProfileBinding

@AndroidEntryPoint
class AddImagesVideosProfileFragment : Fragment() {

    lateinit var fragmentAddImagesVideosProfileBinding: FragmentAddImagesVideosProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddImagesVideosProfileBinding.inflate(inflater, container, false)
        fragmentAddImagesVideosProfileBinding = binding
        return binding.root
    }

//    private fun setUpGallery() = GlobalScope.launch(Dispatchers.Main) {
//
//        activity?.let {
//            val fetchedGalleryItems = withContext(Dispatchers.IO) {
//                return@withContext GalleryDataManager.fetchGalleryImages(it)
//            }
//
//            if (fetchedGalleryItems.size == 0) {
////                ToastHelper.showErrorToast(resources.getString(R.string.no_images_found_in_gallery))
//                activity?.finish()
//            } else {
//                val list = fetchedGalleryItems
//                val first_item = list.first()
//                first_item.is_selected = true
//                selectedGalleryItems.add(first_item)
//                image_preview_adapter?.notifyDataSetChanged()
//
//                val myAdapter = GalleryAdapter(it, list, this@GalleryFragment)
//                val mLayoutManager = GridLayoutManager(it, 2, GridLayoutManager.HORIZONTAL, false)
//
//                galleryRecyclerView?.layoutManager = mLayoutManager
//                galleryRecyclerView?.setHasFixedSize(true)
//                galleryRecyclerView?.setItemViewCacheSize(20);
//                galleryRecyclerView?.adapter = myAdapter
//
//            }
//            updatePreviewLabel()
//        }
//    }
//    private fun getImageUri(filePath: String): Uri = Uri.fromFile(File(filePath))



}