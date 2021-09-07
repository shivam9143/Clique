package yoda.huddl.live.ui.main.profile

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.AppUtils.FileUploader
import yoda.huddl.live.AppUtils.FileUploader.FileUploaderCallback
import yoda.huddl.live.databinding.FragmentAddImagesVideosProfileBinding
import yoda.huddl.live.huddlgallery.adapter.GalleryPicturesAdapter
import yoda.huddl.live.huddlgallery.adapter.SpaceItemDecoration
import yoda.huddl.live.huddlgallery.model.GalleryPicture
import yoda.huddl.live.huddlgallery.viewmodel.GalleryViewModel
import java.io.File


@AndroidEntryPoint
class AddImagesVideosProfileFragment : Fragment() {

    lateinit var fragmentAddImagesVideosProfileBinding: FragmentAddImagesVideosProfileBinding

    lateinit var pDialog: ProgressBar


    private val adapter by lazy {
        pictures?.let { GalleryPicturesAdapter(it, 3) }
    }

    private val galleryViewModel: GalleryViewModel by viewModels()

    private val pictures by lazy {
        context?.let {
            ArrayList<GalleryPicture>(galleryViewModel.getGallerySize(it))
        }
    }

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
        requestReadStoragePermission()
        pDialog = ProgressBar(context)
        return binding.root
    }

    private fun requestReadStoragePermission() {
        val readStorage = Manifest.permission.READ_EXTERNAL_STORAGE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    readStorage
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(readStorage), 3)
        } else init()
    }

    private fun observeSelectedImagesList() {
        adapter?.getSelectedItemsLiveData()?.observe(viewLifecycleOwner, {
//            for (i in 0 until it.size) {
//                if (i == 0)
//                    loadImage(it[i], fragmentAddImagesVideosProfileBinding.previewCard1IV)
//                else if (i == 1)
//                    loadImage(it[i], fragmentAddImagesVideosProfileBinding.previewCard2IV)
//                else if (i == 2)
//                    loadImage(it[i], fragmentAddImagesVideosProfileBinding.previewCard3IV)
//            }
        })
    }

    private fun loadImage(index: Int, viewINdex: Int, isSelected: Boolean) {

//        for(i in 0 until 3) {
        when (adapter?.getSelectedItems()?.size) {
            3 -> {
                context?.let {
                    adapter?.getSelectedItems()!![0]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard1IV)
                    }
                }
                context?.let {
                    adapter?.getSelectedItems()!![1]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard2IV)
                    }
                }
                context?.let {
                    adapter?.getSelectedItems()!![2]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard3IV)
                    }
                }
            }
            2 -> {
                context?.let {
                    adapter?.getSelectedItems()!![0]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard1IV)
                    }
                }
                context?.let {
                    adapter?.getSelectedItems()!![1]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard2IV)
                    }
                }
                context?.let {
//                    adapter?.getSelectedItems()!![2]?.path?.let { path ->
                    Glide.with(it).load("")
                        .into(fragmentAddImagesVideosProfileBinding.previewCard3IV)
//                    }
                }
            }
            1 -> {
                context?.let {
                    adapter?.getSelectedItems()!![0]?.path?.let { path ->
                        Glide.with(it).load(path)
                            .into(fragmentAddImagesVideosProfileBinding.previewCard1IV)
                    }
                }
                context?.let {
//                    adapter?.getSelectedItems()!![1]?.path?.let { path ->
                    Glide.with(it).load("")
                        .into(fragmentAddImagesVideosProfileBinding.previewCard2IV)
                }
//                }
                context?.let {
//                    adapter?.getSelectedItems()!![2]?.path?.let { path ->
                    Glide.with(it).load("")
                        .into(fragmentAddImagesVideosProfileBinding.previewCard3IV)
                }
//                }
            }
        }
//            for (index in adapter?.getSelectedItems()!!) {
//                when (i) {
//                    0 -> {
//
//                        context?.let {
//                            Glide.with(it).load(pictures?.get(i)?.path)
//                                .into(fragmentAddImagesVideosProfileBinding.previewCard1IV)
//                        }
//                    }
//                    1 -> {
//                        context?.let {
//                            Glide.with(it).load(pictures?.get(i)?.path)
//                                .into(fragmentAddImagesVideosProfileBinding.previewCard2IV)
//                        }
//                    }
//                    2 -> {
//                        context?.let {
//                            Glide.with(it).load(pictures?.get(i)?.path)
//                                .into(fragmentAddImagesVideosProfileBinding.previewCard3IV)
//                        }
//                    }
//                }
//            }
//        }
    }

    private fun init() {
        // galleryViewModel = ViewModelProviders.of(this)[GalleryViewModel::class.java] /** @deprecated */
//        updateToolbar(0)
        val layoutManager = GridLayoutManager(context, 3)
        val pageSize = 20
        fragmentAddImagesVideosProfileBinding.importImagesRv.layoutManager = layoutManager
        fragmentAddImagesVideosProfileBinding.importImagesRv.addItemDecoration(SpaceItemDecoration(8))
        fragmentAddImagesVideosProfileBinding.importImagesRv.adapter = adapter

        adapter?.setOnClickListener { galleryPicture ->
//            showToast(galleryPicture.path)
        }

        adapter?.setAfterSelectionListener { position, isSelected ->
//            updateToolbar(getSelectedItemsCount())
            adapter?.getSelectedItems()?.size?.minus(1)
                ?.let { it1 -> loadImage(position, it1, isSelected) }
        }

        fragmentAddImagesVideosProfileBinding.importImagesRv.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (layoutManager.findLastVisibleItemPosition() == pictures?.lastIndex) {
//                    loadPictures(pageSize)
                }
            }
        })

        fragmentAddImagesVideosProfileBinding.submitImagesBtn.setOnClickListener {
//            uploadFiles()
        }

//        tvDone.setOnClickListener {
//            super.onBackPressed()
//        }
//
//        ivBack.setOnClickListener {
//            onBackPressed()
//        }
//        loadPictures(pageSize)
        observeSelectedImagesList()
    }

    private fun uploadImages() {

    }

//    fun uploadFiles() {
//        var files = adapter?.getSelectedItems()
//        var files_size = files?.size!!
//        val filesToUpload: Array<File?> = arrayOfNulls<File>(files_size)
//        for (i in 0 until files_size) {
//            filesToUpload[i] = File(files.get(i).path)
//        }
////        showProgress("Uploading media ...")
////        val fileUploader = FileUploader()
////        fileUploader.uploadFiles("/", "file", filesToUpload, object : FileUploaderCallback {
//            override fun onError() {
////                hideProgress()
//            }
//
//            override fun onFinish(responses: Array<String>) {
////                hideProgress()
//                for (i in responses.indices) {
//                    val str = responses[i]
//                    Log.e("RESPONSE $i", responses[i])
//                }
//            }
//
//            override fun onProgressUpdate(currentpercent: Int, totalpercent: Int, filenumber: Int) {
////                updateProgress(totalpercent, "Uploading file $filenumber", "")
//                Log.e("Progress Status", "$currentpercent $totalpercent $filenumber")
//            }
//        })


    fun updateProgress(`val`: Int, title: String?, msg: String?) {
//        pDialog.setTitle(title)
//        pDialog.setMessage(msg)
//        pDialog.progress = `val`
    }

    fun showProgress(str: String?) {
        try {
//            pDialog.setCancelable(false)
//            pDialog.setTitle("Please wait")
//            pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
//            pDialog.max = 100 // Progress Dialog Max Value
//            pDialog.setMessage(str)
//            if (pDialog.isShowing()) pDialog.dismiss()
//            pDialog.show
        } catch (e: Exception) {
        }
    }

    fun hideProgress() {
        try {
//            if (pDialog.isShowing()) pDialog.dismiss()
        } catch (e: Exception) {
        }
    }
}



//    private fun loadPictures(pageSize: Int) {
//        context?.let {
//            galleryViewModel.getImagesFromGallery(it, pageSize) {
//                if (it.isNotEmpty()) {
//                    pictures?.addAll(it)
//                    pictures?.size?.let { it1 -> adapter?.notifyItemRangeInserted(it1, it.size) }
//                }
//                Log.i("GalleryListSize", "${pictures?.size}")
//            }
//        }
//    }

    //Create extenstions fn for this
//    private fun showToast(s: String) = Toast.makeText(context, s, Toast.LENGTH_SHORT).show()

//    private fun getSelectedItems() = adapter?.getSelectedItems()


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


