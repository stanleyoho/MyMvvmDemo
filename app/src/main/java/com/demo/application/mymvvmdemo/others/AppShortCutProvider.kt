package com.demo.application.mymvvmdemo


//object AppShortCutProvider {
//    enum class AppShortCutEnum(val idStr: String, val shortName: String, val iconID: Int) {
//        GoToH5(
//            "1",
//            ResUtils.getString(com.tianbao.R.string.sport_go_to_web),
//            com.tianbao.R.mipmap.icon_getweb
//        ),
//        ClearCache("3", "应用设置", com.tianbao.R.mipmap.icon_setting_nor)
//    }
//
//    fun initShortcuts(app: Application) {
//        val goH5Intent = getGoToH5Intent()
//        val enums = ArrayList<AppShortCutEnum>()
//        val arrsIntents = ArrayList<Intent>()
//        if (goH5Intent != null) {
//            enums.add(AppShortCutEnum.GoToH5)
//            arrsIntents.add(goH5Intent)
//        }
//        enums.add(AppShortCutEnum.ClearCache)
//        arrsIntents.add(toSelfSetting(app))
//        getShortcuts(app, enums, arrsIntents)
//    }
//
//    fun getShortcuts(
//        app: Application,
//        enums: ArrayList<AppShortCutEnum>,
//        arrsIntents: ArrayList<Intent>?
//    ) {
//        if (!arrsIntents.isNullOrEmpty()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                val shortcutManager = app.getSystemService(ShortcutManager::class.java)
//                val shortcutInfos: ArrayList<ShortcutInfo> = ArrayList()
//                arrsIntents.forEachIndexed { index, intent ->
//                    val enumItem = enums[index]
//                    val info = ShortcutInfo.Builder(app, enumItem.idStr)
//                        .setShortLabel(enumItem.shortName)
//                        .setIcon(Icon.createWithResource(app, enumItem.iconID))
//                        .setIntent(intent)
//                        .build()
//                    shortcutInfos.add(info)
//                }
//                shortcutManager.dynamicShortcuts = shortcutInfos
//            }
//        }
//
//    }
//
//    fun getGoToH5Intent(): Intent? {
//        val downloadUrl: String = DomainCacheUtil.h5DoMain ?: ""
//        return open(downloadUrl)
//    }
//
//    fun toSelfSetting(app: Application): Intent {
//        val mIntent = Intent()
//        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//        if (Build.VERSION.SDK_INT >= 9) {
//            mIntent.action = "android.settings.APPLICATION_DETAILS_SETTINGS"
//            mIntent.data = Uri.fromParts("package", app.packageName, null)
//        } else if (Build.VERSION.SDK_INT <= 8) {
//            mIntent.action = Intent.ACTION_VIEW
//            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails")
//            mIntent.putExtra("com.android.settings.ApplicationPkgName", app.packageName)
//        }
//        return mIntent
//    }
//
//    fun open(url: String?): Intent? {
//        if (TextUtils.isEmpty(url)) {
//            return null
//        }
//        var newUrl = url
//        if (url?.startsWith("http", true) == false) {
//            newUrl = "https://$url"
//        }
//        val intent = Intent()
//        intent.action = Intent.ACTION_VIEW
//        intent.data = Uri.parse(newUrl)
//        return intent
//    }
//
//
//}