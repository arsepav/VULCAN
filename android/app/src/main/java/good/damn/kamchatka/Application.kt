package good.damn.kamchatka

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat

class Application
: Application() {

    companion object {

        var WIDTH = 0
        var HEIGHT = 0

        val KEY_SHARED = "DATA"

        lateinit var RESOURCES: Resources

        private val mHandler = Handler(
            Looper.getMainLooper()
        )

        fun drawable(
            @DrawableRes id: Int
        ): Drawable? {
            return ResourcesCompat.getDrawable(
                RESOURCES,
                id,
                null
            )
        }

        fun font(
            @FontRes id: Int,
            context: Context
        ): Typeface? {
            return ResourcesCompat.getFont(
                context,
                id
            )
        }

        @ColorInt
        fun color(
            @ColorRes id: Int
        ): Int {
            return ResourcesCompat.getColor(
                RESOURCES,
                id,
                null
            )
        }

        fun toast(
            @StringRes msgId: Int,
            context: Context
        ) {
            Toast.makeText(
                context,
                msgId,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun toast(
            msg: String,
            context: Context
        ) {
            Toast.makeText(
                context,
                msg,
                Toast.LENGTH_SHORT
            ).show()
        }

        fun ui(
            task: Runnable
        ) {
            mHandler.post(
                task
            )
        }

    }

    override fun onCreate() {
        super.onCreate()

        RESOURCES = applicationContext
            .resources

        val metrics = RESOURCES
            .displayMetrics

        HEIGHT = metrics.heightPixels
        WIDTH = metrics.widthPixels

    }

}