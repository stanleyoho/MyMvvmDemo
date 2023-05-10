package com.demo.application.mymvvmdemo


//abstract class AppBaseMvvmActivity<T : ViewDataBinding> : BaseMvvmActivity<T>() {
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        try {
//            super.onCreate(savedInstanceState)
//        } catch (e: Exception) {
//            LogUtils.e(e)
//        }
//        APPLoginUtil.outLoginReciver(this)
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        UmengEventManager.onResume(javaClass)
//    }
//
//    override fun onPause() {
//        super.onPause()
//        UmengEventManager.onPause(javaClass)
//    }
//
//
//    /**
//     * 标题栏效果
//     * 左边图片
//     */
//    fun initTitle(title: String?, leftClickListener: View.OnClickListener? = null): CustomToolbar? {
//        val customerTitlebar: CustomToolbar? = findViewById(R.id.toolbar)
//        TitleBarUtil.initTitle(customerTitlebar, title, leftClickListener ?: View.OnClickListener { finish() })
//        return customerTitlebar
//    }
//
//
//    /**
//     * 标题栏效果
//     * 右边文字
//     */
//    fun initTitleByRightTxt(title: String?, leftClickListener: View.OnClickListener? = null): CustomToolbar? {
//        val customerTitlebar: CustomToolbar? = findViewById(R.id.toolbar)
//        TitleBarUtil.initTitleByRightTxt(customerTitlebar, title, leftClickListener ?: View.OnClickListener { finish() })
//        return customerTitlebar
//    }
//
//
//    /**
//     * 标题栏效果
//     * 右边图片
//     */
//    fun initTitleByRightImage(title: String? = "", leftClickListener: View.OnClickListener? = null): CustomToolbar? {
//        val customerTitlebar: CustomToolbar? = findViewById(R.id.toolbar)
//        TitleBarUtil.initTitleByRightImage(customerTitlebar, title, leftClickListener ?: View.OnClickListener {
//            finish()
//        })
//        return customerTitlebar
//    }
//
//
//    /**
//     * 标题栏效果
//     * 右边自定义view
//     */
//    fun initTitleByRightView(title: String?, leftClickListener: View.OnClickListener? = null): CustomToolbar? {
//        val customerTitlebar: CustomToolbar? = findViewById(R.id.toolbar)
//        TitleBarUtil.initTitleByRightView(customerTitlebar, title, leftClickListener ?: View.OnClickListener {
//            finish()
//        })
//        return customerTitlebar
//    }
//
//    /**
//     * 标题栏效果
//     * 右边自定义view
//     */
//    fun initTitleByCenterView(title: String?, leftClickListener: View.OnClickListener? = null): CustomToolbar? {
//        val customerTitlebar: CustomToolbar? = findViewById(R.id.toolbar)
//        TitleBarUtil.initTitleByCenterView(customerTitlebar, title, leftClickListener ?: View.OnClickListener {
//            finish()
//        })
//        return customerTitlebar
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        when (keyCode) {
//            KeyEvent.KEYCODE_VOLUME_DOWN -> {
//                SoundPoolManage.suVolum()
//                return true
//            }
//            KeyEvent.KEYCODE_VOLUME_UP -> {
//                SoundPoolManage.addVolum()
//                return true
//            }
//            KeyEvent.KEYCODE_VOLUME_MUTE -> {
//            }
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//
//
//
//
//}