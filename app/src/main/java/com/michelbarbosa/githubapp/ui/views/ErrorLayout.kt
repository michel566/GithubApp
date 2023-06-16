package com.michelbarbosa.githubapp.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.michelbarbosa.githubapp.R
import com.michelbarbosa.githubapp.databinding.LayoutErrorBinding

class ErrorLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding: LayoutErrorBinding =
        LayoutErrorBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    init {
        setAttrs(attrs)
        startWidgets()
    }

    private fun setAttrs(attrs: AttributeSet?) {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.ErrorLayout)

        setErrorLayoutBackground(
            attributes.getInt(
                R.styleable.ErrorLayout_backgroundColor_error_layout,
                R.color.background
            )
        )

        setErrorLayoutItensTint(
            attributes.getInt(
                R.styleable.ErrorLayout_itensTint_error_layout,
                R.color.colorAccent
            )
        )

        setText(attributes.getString(R.styleable.ErrorLayout_text_error_layout))

        attributes.recycle()
    }

    private fun startWidgets() = with(binding) {
        ivRefresh.isVisible = false
    }

    fun setErrorLayoutBackground(color: Int?) =
        with(binding) {
            color?.let {
                root.setBackgroundColor(ContextCompat.getColor(context, it))
            }
        }

    fun setErrorLayoutItensTint(color: Int?) =
        with(binding) {
            color?.let {
                tvError.setTextColor(ContextCompat.getColor(context, it))
                ivRefresh.drawable.setTint(ContextCompat.getColor(context, it))
            }
        }

    fun setText(errorText: String?) =
        with(binding) {
            errorText?.let {
                tvError.text = it
            }
        }

    fun setOnClickListener(onClick: () -> Unit?) =
        with(binding) {
            ivRefresh.isVisible = true
            root.setOnClickListener {
                onClick.invoke()
            }
        }


}