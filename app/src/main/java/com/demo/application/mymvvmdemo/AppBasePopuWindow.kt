package com.tianbao.ui.base

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import com.bob.mvvm.ui.BaseMvvmPopuWindow

/**
 *Create By Albert on 2020/4/4
 */
abstract class AppBasePopuWindow<T : ViewDataBinding>(
    context: Context,
    lifecycleOwner: LifecycleOwner
) : BaseMvvmPopuWindow<T>(context, lifecycleOwner)