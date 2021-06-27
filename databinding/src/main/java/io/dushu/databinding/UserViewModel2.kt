package io.dushu.databinding

import android.util.Log
import androidx.databinding.ObservableField

/**
 * author: zhangshuai 6/27/21 4:29 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
class UserViewModel2 {

    private val userField = ObservableField<UserBean>()

    init {
        val user = UserBean("使用ObservableField")
        userField.set(user)
    }

    fun getUserName(): String? = userField.get()?.userName

    fun setUserName(s: String?) {
        userField.get()?.userName = s
        Log.i("print_log", "setUserName：$s")
    }
}