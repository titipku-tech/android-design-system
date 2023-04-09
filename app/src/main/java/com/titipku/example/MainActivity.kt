package com.titipku.example

import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import com.titipku.design_system.Toaster
import com.titipku.example.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val toaster = Toaster()


    override fun setupData() {

    }

    override fun setupView() {
        showToaster("hello example", Toaster.ToasterType.SUCCESS)
    }

    private fun showToaster(
        message: String,
        toasterType: Toaster.ToasterType,
        icon: Drawable? = null,
        marginBottom: Int? = null,
        spannableString: SpannableStringBuilder? = null,
        textButtonAction: String? = null,
        action: (() -> Unit)? = null,
    ) {
        toaster.show(
            context = this,
            message = message,
            type = toasterType,
            layoutInflater = layoutInflater,
            customIcon = icon,
            customMarginBottom = marginBottom,
            spannableString = spannableString,
            textButtonAction = textButtonAction,
            action = action,
            contextView = binding.root
        )
    }
}