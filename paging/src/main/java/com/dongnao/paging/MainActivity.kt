package com.dongnao.paging

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dongnao.paging.bean.DataX
import com.dongnao.paging.databinding.ActivityMainBinding
import com.dongnao.paging.databinding.LayoutItemMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mDataBinding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mDataBinding.lifecycleOwner = this
        lifecycle.addObserver(mViewModel)
        initRecyclerView()

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                mAdapter.loadStateFlow.collect {
//                    mDataBinding.prependProgress.isVisible = it.source.prepend is LoadState.Loading
//                    mDataBinding.appendProgress.isVisible = it.source.prepend is LoadState.Loading
//                }
//            }
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mViewModel.pagingDataFlow.collectLatest {
                    mAdapter.submitData(it)

                }
            }
        }
    }

    private fun initRecyclerView() {
        with(mDataBinding.recyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            adapter = mAdapter
        }
    }

    private val mAdapter =
        object : PagingDataAdapter<DataX, ImageItemViewHolder>(ImageItemCallback()) {

            override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
                holder.load(getItem(position))
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
                val binding = DataBindingUtil.inflate<LayoutItemMainBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.layout_item_main,
                    parent,
                    false
                )
                return ImageItemViewHolder(binding)
            }
        }


    private inner class ImageItemCallback : DiffUtil.ItemCallback<DataX>() {
        override fun areItemsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataX, newItem: DataX): Boolean {
            return oldItem == newItem
        }
    }

    private inner class ImageItemViewHolder(private val itemBinding: LayoutItemMainBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun load(data: DataX?) {
            data?.also {
                itemBinding.data = it
                Glide.with(itemBinding.acIvIcon.context)
                    .load(it.envelopePic)
                    .apply(RequestOptions.bitmapTransform(TransformationUtils.getRandom()))
                    .into(itemBinding.acIvIcon)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(mViewModel)
        mDataBinding.unbind()
    }
}