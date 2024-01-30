package com.example.dogtranslate.ui.language

import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.dogtranslate.R
import com.example.dogtranslate.data.local.model.Language
import com.example.dogtranslate.databinding.FragmentLanguageBinding
import com.example.dogtranslate.ui.base.BaseFragment
import com.example.dogtranslate.ui.language.adapter.LanguageAdapter
import java.util.ArrayList

class LanguageFragment : BaseFragment<FragmentLanguageBinding, LanguageViewModel>() {
    private var adapter: LanguageAdapter? = null
    private val languageList = ArrayList<Language>()
    override fun layoutRes(): Int {
        return R.layout.fragment_language
    }

    override fun viewModelClass(): Class<LanguageViewModel> {
        return LanguageViewModel::class.java
    }

    override fun initView() {
        handleAdapter()
        adapter?.isoLanguage(viewModel?.getLanguagePreference()!!)

        binding?.abLanguage?.setOnClickBack {
            findNavController().popBackStack()
        }
    }

    private fun handleAdapter() {

        setDataLanguage()
        adapter = LanguageAdapter(languageList)
        binding?.rcvLanguage?.adapter = adapter

        adapter?.onClickChecked = { selectedLanguage ->
            LocaleHelper.setLocale(requireContext(), "${selectedLanguage.isoLanguage}")
            viewModel?.saveLanguagePreference(selectedLanguage.isoLanguage!!)
            adapter?.isoLanguage(selectedLanguage.isoLanguage!!)
            restartActivity()
        }
    }

    private fun restartActivity() {
        findNavController().popBackStack()
    }

    private fun setDataLanguage() {
        val languageEnglish = Language().apply {
            languageName = "English"
            isoLanguage = "en"
            image = R.drawable.ic_english
        }
        languageList.add(languageEnglish)

        val languageVietName = Language().apply {
            languageName = "Viet Nam"
            isoLanguage = "vi"
            image = R.drawable.ic_vietnam
        }
        languageList.add(languageVietName)

        val languagePortugal = Language().apply {
            languageName = "Portugal"
            isoLanguage = "pt"
            image = R.drawable.ic_portugal
        }
        languageList.add(languagePortugal)

        val languageSpanish = Language().apply {
            languageName = "Spanish"
            isoLanguage = "es"
            image = R.drawable.ic_spanish
        }
        languageList.add(languageSpanish)

        val languageIndia = Language().apply {
            languageName = "India"
            isoLanguage = "hi"
            image = R.drawable.ic_india
        }
        languageList.add(languageIndia)
    }

    override fun onDestroyView() {
        binding?.fragmentContainer?.removeAllViews()
        languageList.clear()
        adapter = null
        binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel = null
    }
}