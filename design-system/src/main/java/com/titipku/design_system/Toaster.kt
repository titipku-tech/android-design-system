package com.titipku.design_system

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableStringBuilder
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.titipku.design_system.databinding.LayoutToasterBinding


class Toaster {

    enum class ToasterType(var value: Int) {
        SUCCESS(1), ERROR(2), DELETED(3), PRIMARY(4), WARNING(5), SUCCESS_WITH_ACTION(6)
    }

    fun show(
        context: Context,
        message: String,
        type: ToasterType,
        layoutInflater: LayoutInflater,
        customIcon: Drawable? = null,
        customMarginBottom: Int? = null,
        spannableString: SpannableStringBuilder? = null,
        textButtonAction: String? = null,
        action: (() -> Unit)? = null,
        contextView: View? = null
    ) {
        val binding = LayoutToasterBinding.inflate(layoutInflater, null, false)
        binding.messageToast.text = message
        if (spannableString != null) {
            binding.messageToast.text = spannableString
        }
        when (type) {
            ToasterType.SUCCESS -> {
                binding.background.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.wizard_blue))
                binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_baseline_check_24, 0, 0, 0
                )
            }
            ToasterType.ERROR -> {
                binding.background.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.terra_cotta))
                binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_failed, 0, 0, 0
                )
            }
            ToasterType.DELETED -> {
                binding.background.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.terra_cotta))
                binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_warning, 0, 0, 0
                )
            }
            ToasterType.PRIMARY -> {

            }
            ToasterType.WARNING -> {
                binding.background.backgroundTintList =
                    ColorStateList.valueOf(ContextCompat.getColor(context, R.color.terra_cotta))
                binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_warning, 0, 0, 0
                )
            }
            ToasterType.SUCCESS_WITH_ACTION -> {
                if (spannableString != null && contextView != null) {
                    Snackbar.make(contextView, "", 4000).apply {
                        val snackbarLayout: Snackbar.SnackbarLayout =
                            this.view as Snackbar.SnackbarLayout
                        view.setBackgroundColor(Color.TRANSPARENT)
                        binding.btnAction.isVisible = true
                        binding.btnAction.text = textButtonAction
                        binding.btnAction.setOnClickListener {
                            action?.invoke()
                        }
                        setMargin(
                            binding.background,
                            start = (0 * Resources.getSystem().displayMetrics.density).toInt(),
                            right = (0 * Resources.getSystem().displayMetrics.density).toInt()
                        )
                        view.setPadding(0, 0, 0, 0)
                        snackbarLayout.setPadding(0, 0, 0, 0)
                        snackbarLayout.addView(binding.root, 0)
                        show()
                    }
                    binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_check, 0, 0, 0
                    )
                    binding.messageToast.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.white
                        )
                    )
                    binding.background.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(context, R.color.wizard_blue))
                    binding.messageToast.setCompoundDrawablesWithIntrinsicBounds(
                        R.drawable.ic_baseline_check_24, 0, 0, 0
                    )
                }
            }
        }

        if (type != ToasterType.SUCCESS_WITH_ACTION) {
            customIcon?.apply {
                binding.messageToast.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    customIcon, null, null, null
                )
            }

            customMarginBottom?.apply {
                setMargin(
                    binding.background,
                    bottom = (this * Resources.getSystem().displayMetrics.density).toInt()
                )
            }
            Toast(context).apply {
                duration = Toast.LENGTH_SHORT
                view = binding.root
                setGravity(Gravity.FILL_HORIZONTAL, 0, 0)
                setMargin(1.toFloat(), 1.toFloat())
            }.show()
        }
    }

    private fun setMargin(
        view: View, bottom: Int? = null, right: Int? = null, top: Int? = null, start: Int? = null
    ) {
        val params = (view.layoutParams as? ViewGroup.MarginLayoutParams)
        params?.setMargins(
            start ?: params.marginStart,
            top ?: params.topMargin,
            right ?: params.marginEnd,
            bottom ?: params.bottomMargin
        )
        view.layoutParams = params
    }

}