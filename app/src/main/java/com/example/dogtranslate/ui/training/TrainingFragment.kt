package com.example.dogtranslate.ui.training

import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.R
import com.example.dogtranslate.data.remote.model.DataTraining
import com.example.dogtranslate.databinding.FragmentTrainingBinding
import com.example.dogtranslate.ui.base.BaseActivity
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.ui.training.adapter.TrainingAdapter

class TrainingFragment : BaseFragment<FragmentTrainingBinding, TrainingViewModel>() {
    private var adapter: TrainingAdapter? = null
    private val trainingList = ArrayList<DataTraining>()
    override fun layoutRes(): Int {
        return R.layout.fragment_training
    }

    override fun viewModelClass(): Class<TrainingViewModel> {
        return TrainingViewModel::class.java
    }

    override fun initView() {

        binding?.rcvTraining?.visibility = View.GONE
        binding?.shimmerContainer?.startShimmer()

        Handler(Looper.getMainLooper()).postDelayed({
            binding?.shimmerContainer?.stopShimmer()
            binding?.shimmerContainer?.visibility = View.GONE
            binding?.rcvTraining?.visibility = View.VISIBLE
        }, 2000)

        adapter = TrainingAdapter(trainingList)
        binding?.rcvTraining?.adapter = adapter

        viewModel?.trainingList?.observe(this) {
            it.data.let { data ->
                if (data != null) {
                        trainingList.addAll(data)
                }
            }
            adapter?.notifyDataSetChanged()
        }

        adapter?.onItemClick = {
            findNavController().navigate(
                R.id.action_homeFragment_to_showTrainingFragment, bundleOf(
                    Pair("data", it)
                )
            )
        }
    }

    override fun onDestroyView() {
        binding?.relativeLayout?.removeAllViews()
        adapter = null
        trainingList.clear()
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.trainingList?.removeObservers(this)
        viewModel = null
    }
}