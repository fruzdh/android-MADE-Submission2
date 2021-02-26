package com.example.submission2.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.submission2.R
import com.example.submission2.databinding.ActivityHomeBinding
import com.example.submission2.ui.fragment.home.HomeFragment
import meow.bottomnavigation.MeowBottomNavigation
import java.util.*
import kotlin.concurrent.schedule

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    private var pressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        supportActionBar?.setLogo(R.drawable.ic_baseline_local_movies)

        binding?.btmNav?.add(MeowBottomNavigation.Model(1, R.drawable.ic_baseline_home))
        binding?.btmNav?.add(MeowBottomNavigation.Model(2, R.drawable.ic_baseline_favorite))

        binding?.btmNav?.setOnClickMenuListener {
            var fragment: Fragment? = null
            when (it.id) {
                1 -> fragment = HomeFragment()
                2 -> fragment = Class.forName("com.example.favorite.ui.FavoriteFragment").newInstance() as Fragment
            }
            if (fragment != null) {
                supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                        .replace(R.id.navHostFragment, fragment)
                        .addToBackStack(null)
                        .commit()
            }
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.navHostFragment, HomeFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else if (!pressed) {
            pressed = true
            Toast.makeText(this, getString(R.string.exit), Toast.LENGTH_SHORT).show()

            supportActionBar?.hide()
            Timer().schedule(3000) {
                pressed = false
            }
        } else {
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}