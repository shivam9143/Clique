package yoda.huddl.live.ui.main

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationBarView
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.HuddleBaseActivity
import yoda.huddl.live.R
import yoda.huddl.live.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : HuddleBaseActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initNav()
        initActionBar()
    }

    private fun initActionBar() {
        supportActionBar?.setLogo(R.drawable.ic_logo_200)
        supportActionBar?.setDisplayUseLogoEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.bottomNav.visibility = View.VISIBLE
    }

    private fun initNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mobileNumberFrag,
                R.id.otpVerification,
                R.id.createProfile
            )
        )
        NavigationUI.setupWithNavController(binding.bottomNav, navController)
//        binding.bottomNav.itemIconTintList = ColorStateList.valueOf(Color.parseColor("#EA2323"))
        binding.bottomNav.setOnItemSelectedListener(NavigationBarView.OnItemSelectedListener { item ->
            val id = item.itemId
            when (id) {
                R.id.profile_menu_item -> {
                    navController.navigate(R.id.profileFragment)
                }
                R.id.revenue_menu_item -> {
                    navController.navigate(R.id.revenueFragment)
                }
                R.id.home_menu_item -> {
                    navController.navigate(R.id.homeFragment)
                }
            }
            true
        })

            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                // react on change
                // you can check destination.id or destination.label and act based on that
                when (destination.id) {
                    R.id.addImagesVideosProfileFragment -> {
                        hideBottomNav()
                    }
                    else -> {
                        showBottomNav()
                    }
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.context_menu, menu)
        return true
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.profile_menu_item -> {
//                navController.navigate(R.id.profileFragment)
//            }
//            R.id.revenue_menu_item -> {
//                navController.navigate(R.id.revenueFragment)
//            }
//            R.id.home_menu_item -> {
//                navController.navigate(R.id.homeFragment)
//            }
//        }
//        return true
//    }

}