package com.example.sdhtestproject

import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.sdhtestproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    Toolbar.OnMenuItemClickListener,
    NavController.OnDestinationChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setUpBottomNavigationAndFab()
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