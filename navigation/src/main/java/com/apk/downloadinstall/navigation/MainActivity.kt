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
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                Log.i("print_logs", "onDestinationChanged: ${arguments.toString()}")
            }
        })
    }

    //返回键
    override fun onSupportNavigateUp(): Boolean {
        return mFragmentContainer.navigateUp()
    }
}