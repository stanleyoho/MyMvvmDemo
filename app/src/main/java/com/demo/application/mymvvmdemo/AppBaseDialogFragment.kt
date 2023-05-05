package com.tianbao.ui.base

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.KeyEvent
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.BaseNavigationMvvmDialogFragment
import com.bob.commom.cache.disk.SpManager
import com.bob.commom.umeng.UmengEventManager
import com.bob.commom.utils.LanguageUtil
import com.bob.utils.StatusBarUtil
import com.jeremyliao.liveeventbus.LiveEventBus
import com.bob.commom.config.Key
import com.yabo.framework.YaboLib

abstract class AppBaseDialogFragment<T : ViewDataBinding> : BaseNavigationMvvmDialogFragment<T>(),
    DialogInterface.OnKeyListener {
    /**
     * 设置页面退出登录、关闭当前的页面
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeAppLanguage()
    }

    override fun onResume() {
        super.onResume()
        UmengEventManager.onResume(javaClass)
    }

    override fun onPause() {
        super.onPause()
        UmengEventManager.onPause(javaClass)
    }


    override fun onStart() {
        super.onStart()
        dialog?.setOnKeyListener(this)
        dialog?.setCanceledOnTouchOutside(true)
        onDialogAnim()

    }

    override fun initObserve() {
        super.initObserve()
        LiveEventBus.get(Key.event.EVENT_REFRESH_LANGUAGE).observe(this, {
            changeAppLanguage()
        })
    }

    private fun changeAppLanguage() {
        val language = LanguageUtil.getLocale(SpManager.config.systemLanguage ?: "def")
        val dm = resources.displayMetrics
        val conf = resources.configuration
        conf.locale = language
        resources.updateConfiguration(conf, dm)

        val dmApp = YaboLib.app.resources.displayMetrics
        val confApp = YaboLib.app.resources.configuration
        confApp.locale = language
        YaboLib.app.resources.updateConfiguration(confApp, dmApp)

    }

    open fun onDialogAnim() {
        getDialogAnim()
    }

    open fun getDialogAnim() {
        val window = dialog?.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window?.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_scale_anim)
    }

    override fun enableSkin(): Boolean {
        return false
    }

    override fun onKey(dialog: DialogInterface?, keyCode: Int, event: KeyEvent?): Boolean {
        return false
    }

    override fun isContentRecyclerViewNavigationBar(): Boolean {
        return StatusBarUtil.getStatusBarHeightAndroid11(context) > 0
    }


}