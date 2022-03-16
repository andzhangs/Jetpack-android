package io.dushu.livedata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe

/**
 * author: zhangshuai 6/26/21 9:57 PM
 * email: zhangshuai@dushu365.com
 * mark:
 */
class SecondFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var seekBar:SeekBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_second, container, false)
        seekBar=rootView.findViewById<SeekBar>(R.id.seekBar2)

        activity?.let {
            val viewModel= ViewModelProvider(it, ViewModelProvider.AndroidViewModelFactory(it.application)).get(
                MyViewModel::class.java
            )

            viewModel.getProgress().observe(it) { o ->
                seekBar.progress = o
            }

            seekBar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    viewModel.setProgress(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                }

            })

        }


        return rootView
    }
}