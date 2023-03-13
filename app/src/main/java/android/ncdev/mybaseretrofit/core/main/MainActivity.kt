package android.ncdev.mybaseretrofit.core.main

import android.ncdev.mybaseretrofit.core.base.BaseActivity
import android.ncdev.common.utils.viewbinding.viewBinding
import android.ncdev.mybaseretrofit.R
import android.ncdev.mybaseretrofit.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val binding by viewBinding(ActivityMainBinding::inflate)

    private lateinit var navController: NavController

    override fun initView(bundle: Bundle?) {
        initNavigation()
    }

    private fun initNavigation() {
        navController =
            (supportFragmentManager.findFragmentById(binding.navFragmentContainer.id) as NavHostFragment)
                .navController
        with(navController) {
            graph = navInflater.inflate(R.navigation.nav_main)
            binding.bottomBar.setupWithNavController(this)
        }

    }
}