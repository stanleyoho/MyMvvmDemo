package com.tianbao.ui.base

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.apkfuns.logutils.LogUtils
import com.bob.commom.CommonEngine
import com.bob.commom.cache.disk.SpManager
import com.bob.commom.cache.memory.DataManager
import com.bob.commom.cache.memory.MemoryManager
import com.bob.commom.manager.redbag.manager.RedManager
import com.bob.commom.utils.LanguageUtil
import com.bob.commom.zhuge.ZhuGeEventConfig
import com.bob.commom.zhuge.ZhuGeSender
import com.bob.mvvm.findNavController
import com.bob.mvvm.ui.BaseNavigationFragment
import com.bob.utils.RedPacketRainConfig
import com.bob.utils.SoftHideKeyBoardUtil
import com.bob.utils.StatusBarUtil
import com.bob.widget.CustomToolbar
import com.jeremyliao.liveeventbus.LiveEventBus
import com.tianbao.R
import com.bob.commom.config.Key
import com.bob.commom.umeng.UmengEventManager
import com.tianbao.manager.IntentManager
import com.tianbao.ui.login.login.migrate.MigrateUpgradeProvider
import com.tianbao.ui.redpacket.RedPacketRainManager
import com.tianbao.ui.redpacket.widget.RedPacketFloatingView
import com.tianbao.util.sdk.TitleBarUtil
import com.tianlong.domain.utils.postDelay
import com.yabo.framework.YaboLib

abstract class AppBaseMvvmFragment<T : ViewDataBinding> : BaseNavigationFragment<T>(),
    RedManager.IRedBgShow {


    var customerTitlebar: CustomToolbar? = null

    /**
     * 埋点需要
     */
    protected var pageName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeAppLanguage()
    }

    override fun onResume() {
        super.onResume()
        UmengEventManager.onResume(javaClass)
        setBarNight()
    }

    fun setY0UpdateDialog() {
        val fragmentName = this.javaClass.canonicalName
        if (CommonEngine.isY0Platform() && MigrateUpgradeProvider.containMigrateUpgradeDialogMap(
                fragmentName
            ) && (!MigrateUpgradeProvider.getFromMigrateUpgradeDialogMap(fragmentName ?: "")|| DataManager.isMigrateUpgradeDialogGoOut)
        ) {
            postDelay(1000) {
                IntentManager.goMigrateUpgradeDialog(this)
            }
        }
    }


    override fun onPause() {
        super.onPause()
        UmengEventManager.onPause(javaClass)
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        //这里用红包雨配置通用，嵌套页面不需要重复统计
        if (enableRedBag()) {
            ZhuGeSender.create().startTrack(ZhuGeEventConfig.PAGE_VIEW)
        }

        if (RedPacketRainConfig.attach && MemoryManager.isLoginSuccess() && enableRedBag()) {
            RedPacketRainManager.getInstance().startHandlerMsg()
        }
        setY0UpdateDialog()
    }

    override fun onSupportInvisible() {
        super.onSupportInvisible()
        if (pageName.isNullOrEmpty()) pageName =
            customerTitlebar?.titlebar?.centerTextView?.text?.toString()
        //LogUtils.i("zhuge ===> 当前页面名称：$pageName  ")
        LogUtils.i(" ===> 当前页面名称：${javaClass.simpleName}  ")
        if (enableRedBag()) {
            ZhuGeSender.create().put("page_name", pageName)
                .endTrack(ZhuGeEventConfig.PAGE_VIEW)
        }
    }

    open fun setBarNight() {
        if (CommonEngine.notifyApplicationInitFinished.value == true) {
            if (enableSkin()) {
                if (DataManager.appIsNightTheme.value != null && DataManager.appIsNightTheme.value == true) {
                    StatusBarUtil.setAndroidNativeLightStatusBar(requireActivity(), false)
                } else {
                    StatusBarUtil.setAndroidNativeLightStatusBar(requireActivity(), true)
                }
            } else {
                if (isSpringSkin()) {
                    StatusBarUtil.setAndroidNativeLightStatusBar(requireActivity(), false)
                } else {
                    StatusBarUtil.setAndroidNativeLightStatusBar(requireActivity(), true)
                }
            }
        }
    }

    override fun initObserve() {
        super.initObserve()
        LiveEventBus.get(Key.event.EVENT_REFRESH_LANGUAGE).observe(this, {
            changeAppLanguage()
        })
        DataManager.netwokIsConnet.observe(this, Observer {
            if (it) {
                Log.e("POP_TAG", "this.javaClass.canonicalName = ${this.javaClass.canonicalName}")
                Log.e(
                    "POP_TAG",
                    "DialogMap =${MigrateUpgradeProvider.getFromMigrateUpgradeDialogMap(this.javaClass.canonicalName ?: "").toString()}"
                )
                setY0UpdateDialog()
            }
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

    /**
     * 标题栏效果
     * 左边图片
     */
    fun initTitle(
        title: String? = "",
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        customerTitlebar?.titlebar?.centerTextView?.isSingleLine = true
        customerTitlebar?.titlebar?.centerTextView?.ellipsize = TextUtils.TruncateAt.END
        TitleBarUtil.initTitle(customerTitlebar, title, leftClickListener ?: View.OnClickListener {
            exitActivity()
        })
        return customerTitlebar
    }

    override fun onStart() {
        super.onStart()
        if (RedPacketRainConfig.attach && MemoryManager.isLoginSuccess() && enableRedBag()) {
            RedPacketFloatingView.get().attach(this.activity)
        }
    }

    override fun onStop() {
        super.onStop()
        if (RedPacketRainConfig.attach && MemoryManager.isLoginSuccess() && enableRedBag()) {
            RedPacketFloatingView.get().detach(this.activity)
        }
    }

    open fun enableRedBag(): Boolean {
        return true
    }

    /**
     * 标题栏效果
     * 右边文字
     */
    fun initTitleByRightTxt(
        title: String?,
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByRightTxt(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }


    /**
     * 标题栏效果
     * 右边图片
     */
    fun initTitleByRightImage(
        title: String? = "",
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByRightImage(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }


    /**
     * 标题栏效果
     * 右边自定义view
     */
    fun initTitleByRightView(
        title: String?,
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByRightView(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }

    /**
     * 标题栏效果
     * 右边自定义view
     */
    fun initTitleByCenterView(
        title: String? = "",
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByCenterView(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }

    /**
     * 标题栏效果
     * 右边图片
     */
    fun initTitleByRightImage2(
        title: String? = "",
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByRightImage2(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }

    fun initTitleByLeftRightImage2(
        title: String? = "",
        leftClickListener: View.OnClickListener? = null
    ): CustomToolbar? {
        this.pageName = title
        customerTitlebar = findViewById(R.id.toolbar)
        TitleBarUtil.initTitleByLeftRightImage2(
            customerTitlebar,
            title,
            leftClickListener ?: View.OnClickListener {
                exitActivity()
            })
        return customerTitlebar
    }

    fun finishActivityNoAnim() {
//		DokitServiceEnum.finish
    }

    override fun exitActivity() {
        findNavController()?.popBackStack()
    }

    override fun onBackPressedSupport(): Boolean {
        SoftHideKeyBoardUtil.instance?.destroy()
        if (isVisible) {
            exitActivity()
        }
        return true
    }

    override fun showRedBg(): Boolean {
        return true
    }

    override fun enableSkin(): Boolean {
        return false
    }

    open fun isSpringSkin(): Boolean {
        return false
    }

    override fun applySkin() {
        super.applySkin()
        setBarNight()
    }

    override fun resDestroy() {
        super.resDestroy()
        removeMigrateDialog()
    }

    fun removeMigrateDialog() {
        val fragmentName = this.javaClass.canonicalName
        if (CommonEngine.isY0Platform() && MigrateUpgradeProvider.containMigrateUpgradeDialogMap(
                fragmentName
            )
        ) {
            Log.e("POP_TAG", " removeMigrateDialog = ${this.javaClass.canonicalName}")
            MigrateUpgradeProvider.addToMigrateUpgradeDialogMap(
                fragmentName ?: "",
                false
            )
            Log.e(
                "POP_TAG",
                "removeMigrateD = ${MigrateUpgradeProvider.getFromMigrateUpgradeDialogMap(this.javaClass.canonicalName ?: "").toString()}"
            )
        }
    }

}