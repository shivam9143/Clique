package yoda.huddl.live.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import yoda.huddl.live.HuddleBaseActivity
import yoda.huddl.live.R
import yoda.huddl.live.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : HuddleBaseActivity() {

    lateinit var binding : ActivityMainBinding

    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        init()
    }

    private fun init()
    {
        val bottomNavigationView = binding.bottomNav
        val navGraphIds = listOf(R.navigation.nav_graph_main)
        navController = findNavController(binding.navHostFragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment, R.id.revenueFragment, R.id.profileFragment))
        setupActionBarWithNavController(this, navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)
    }

}