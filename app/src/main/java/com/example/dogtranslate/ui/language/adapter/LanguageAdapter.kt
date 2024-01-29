package com.example.dogtranslate.ui.language.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dogtranslate.data.local.model.Language
import com.example.dogtranslate.databinding.ItemLanguageBinding
import com.example.dogtranslate.util.setOnSingClickListener

class LanguageAdapter(private val languageList: ArrayList<Language>) :
    RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {
    private var checkedPosition: Int = -1
    private var isoLanguage: String = ""

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {
            binding.language = language
        }
    }

    fun isoLanguage(iso: String) {
        isoLanguage = iso
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding =
            ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    var onClickChecked: ((Language) -> Unit)? = null
    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        @SuppressLint("RecyclerView") position: Int
    ) {
        val language = languageList[position]
        holder.bind(language)

        language.image.let {
            holder.binding.imgAvatar.setImageResource(it!!)
        }
        // Kiểm tra nếu là vị trí đã chọn hoặc isoLanguage tương ứng, set isChecked là true, ngược lại là false
        holder.binding.rbButton.isChecked =
            position == checkedPosition || language.isoLanguage == isoLanguage

        // Đặt lắng nghe sự kiện click cho rbButton
        holder.binding.rbButton.setOnClickListener {
            // Cập nhật checkedPosition
            checkedPosition = position

            // Gọi hàm callback nếu được đăng ký
            onClickChecked?.invoke(language)

            // Thông báo rằng dữ liệu đã thay đổi
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = if (languageList.isNotEmpty()) languageList.size else 0
}