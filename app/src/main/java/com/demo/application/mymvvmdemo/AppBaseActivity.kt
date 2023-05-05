package com.tianbao.ui.base

import android.os.Bundle
import com.bob.commom.cache.disk.SpManager
import com.bob.commom.cache.memory.DataManager
import com.bob.commom.ui.PluginActivity
import com.bob.commom.utils.LanguageUtil
import com.tianbao.ui.redpacket.RedPacketRainManager
import com.tianbao.util.APPLoginUtil
import com.yabo.framework.YaboLib

open class AppBaseActivity: PluginActivity(){

    override fun initViewsAndEvents(paramBundle: Bundle?) {
        APPLoginUtil.outLoginReciver(this)
        DataManager.redPacketRain.observe(this) {
            RedPacketRainManager.getInstance().startReadyTimer()
        }

//        changeAppLanguage()
//
//        LiveEventBus.get(Key.event.EVENT_REFRESH_LANGUAGE).observe(this, {
//            changeAppLanguage()
//        })
    }

//    override fun attachBaseContext(newBase: Context) {
//        super.attachBaseContext(MultiLanguages.attach(newBase))
//    }


    private fun changeAppLanguage() {
        val language = LanguageUtil.getLocale(SpManager.config.systemLanguage?:"def")
        val dm = resources.displayMetrics
        val conf = resources.configuration
        conf.locale = language
        resources.updateConfiguration(conf, dm)

        val dmApp = YaboLib.app.resources.displayMetrics
        val confApp = YaboLib.app.resources.configuration
        confApp.locale = language
        YaboLib.app.resources.updateConfiguration(confApp, dmApp)

    }
}