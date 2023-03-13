package android.ncdev.mybaseretrofit

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.pm.ActivityInfo
import android.ncdev.core_db.realm.RealmMigrations
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration
import java.lang.ref.WeakReference

@HiltAndroidApp
class App : Application(), Application.ActivityLifecycleCallbacks {

    var currentActivity: WeakReference<AppCompatActivity>? = null

    companion object {
        private lateinit var app: App
        fun get(): App {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        initRealm()
//        Firebase.crashlytics.setUserId(
//            Settings.Secure.getString(
//                contentResolver,
//                Settings.Secure.ANDROID_ID
//            )
//        )
    }
    private fun initRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("app.realm")
            .allowWritesOnUiThread(true)
            .allowQueriesOnUiThread(true)
            .schemaVersion(RealmMigrations.CURRENT_VERSION.toLong())
            .migration(RealmMigrations())
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
        runCatching {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT
        }
    }

    override fun onActivityStarted(p0: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        currentActivity = WeakReference(activity as? AppCompatActivity)
    }

    override fun onActivityPaused(p0: Activity) {}

    override fun onActivityStopped(p0: Activity) {}

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

    override fun onActivityDestroyed(p0: Activity) {}
}

fun getAppContext(): App {
    return App.get()
}

fun getCurrentActivity(): AppCompatActivity? {
    return getAppContext().currentActivity?.get()
}

fun getCurrentContext(): Context {
    return getCurrentActivity() ?: getAppContext()
}