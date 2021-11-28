package com.apk.downloadinstall.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private val mFragmentContainer by lazy {Navigation.findNavController(this, R.id.fragmentContainerView)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this,mFragmentContainer)

        //监听页面跳转
        mFragmentContainer.addOnDestinationChangedListener(object :NavController.OnDestinationChangedListener{
            /**
             * Callback for when the [.getCurrentDestination] or its arguments change.
             * This navigation may be to a destination that has not been seen before, or one that
             * was previously on the back stack. This method is called after navigation is complete,
             * but associated transitions may still be playing.
             *
             * @param controller the controller that navigated
             * @param destination the new destination
             * @param arguments the arguments passed to the destination
             */
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Log.i("print_logs", "arguments: ${arguments.toString()}")
                Log.i("print_logs", "onDestinationChanged: ")
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        return mFragmentContainer.navigateUp()
    }
}