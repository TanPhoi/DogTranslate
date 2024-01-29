package com.example.dogtranslate.ui.showtraining

import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.R
import com.example.dogtranslate.data.remote.model.DataTraining
import com.example.dogtranslate.databinding.FragmentShowTrainingBinding
import com.example.dogtranslate.ui.base.BaseFragment

class ShowTrainingFragment : BaseFragment<FragmentShowTrainingBinding, ShowTrainingViewModel>() {
    override fun layoutRes(): Int {
        return R.layout.fragment_show_training
    }

    override fun viewModelClass(): Class<ShowTrainingViewModel> {
        return ShowTrainingViewModel::class.java
    }

    override fun initView() {
        val data = arguments?.getParcelable("data") as? DataTraining
        if (data != null){
            binding?.tvName?.text = data.attributes?.name.toString()
            binding?.tvDescription?.text = data.attributes?.description.toString()
            binding?.tvLifeMax?.text = "max : ${data.attributes?.life?.max.toString()}"
            binding?.tvLifeMin?.text = "min : ${data.attributes?.life?.min.toString()}"
            binding?.tvMaleWeightMax?.text =  "max : ${data.attributes?.maleWeight?.max.toString()}"
            binding?.tvMaleWeightMin?.text = "min : ${data.attributes?.maleWeight?.min.toString()}"
            binding?.tvFemaleWeightMax?.text = "max : ${data.attributes?.femaleWeight?.max.toString()}"
            binding?.tvFemaleWeightMin?.text = "min : ${data.attributes?.femaleWeight?.min.toString()}"
            if (data.attributes?.hypoallergenic == false){
                binding?.tvHypoallergenic?.text = "No"
            }else{
                binding?.tvHypoallergenic?.text = "Yes"
            }
        }

        binding?.abShowTraining?.setOnClickBack {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        binding?.coordinatorLayout?.removeAllViews()
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}