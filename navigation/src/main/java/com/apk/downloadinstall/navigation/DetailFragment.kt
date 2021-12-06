package com.apk.downloadinstall.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs

class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<AppCompatButton>(R.id.acBtnDetail)?.setOnClickListener {
            Navigation.findNavController(it).apply {
                navigate(R.id.action_detailFragment_to_homeFragment)
            }
        }
    }

    val argsDetail:DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //接收值方式一
//        val fromBundle = HomeFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(activity, "获取：${fromBundle.userName}, ${fromBundle.userAge}", Toast.LENGTH_SHORT).show()

//         val fromBundle1 = DetailFragmentArgs.fromBundle(requireArguments())
//        Toast.makeText(activity, "获取：${fromBundle1.callback}", Toast.LENGTH_SHORT).show()

        //接收值二
//        Toast.makeText(activity, "获取：${argsDetail.callback}", Toast.LENGTH_SHORT).show()


        //deeplink
        Toast.makeText(activity, "接收：${arguments?.getString("param1")}, ${arguments?.getInt("param2")}", Toast.LENGTH_SHORT).show()

    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}