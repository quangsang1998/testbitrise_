package co.kyash.ui.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onClick")
fun View.OnClickListener(onClickListener: View.OnClickListener?) {
    setOnClickListener(onClickListener)
}
