package com.apk.downloadinstall.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<AppCompatButton>(R.id.acBtnHome)?.setOnClickListener {

            //传值方式一
//            val toBundle = HomeFragmentArgs("你好", 11).toBundle()
            val toBundle1 = DetailFragmentArgs("万岁").toBundle()
            val findNavController = Navigation.findNavController(it)
            findNavController.navigate(R.id.action_homeFragment_to_detailFragment,toBundle1)

            //传值方式二
//            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment("你好")
//            it.findNavController().navigate(action)

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}