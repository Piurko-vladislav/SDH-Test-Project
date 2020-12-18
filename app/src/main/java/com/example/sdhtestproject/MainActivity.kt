package com.example.sdhtestproject

import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.sdhtestproject.databinding.ActivityMainBinding
import com.example.sdhtestproject.db.AppDatabase
import com.example.sdhtestproject.views.PillInfoFragmentArgs
import com.example.sdhtestproject.views.PillsListFragment
import com.example.sdhtestproject.views.SearchFragmentDirections

class MainActivity : AppCompatActivity(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    val currentNavigationFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.base_fragment)
            ?.childFragmentManager
            ?.fragments
            ?.first()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpBottomNavigationAndFab()
        AppDatabase.getAppDataBase(applicationContext)
    }



    private fun setUpBottomNavigationAndFab() {
        binding.run {
            findNavController(R.id.base_fragment).addOnDestinationChangedListener(
                this@MainActivity
            )
        }
    }


    override fun onMenuItemClick(item: MenuItem?): Boolean {

        return true
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {

    }

}