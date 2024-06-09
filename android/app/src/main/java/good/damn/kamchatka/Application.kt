package good.damn.kamchatka

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat

class Application
: Application() {

    companion object {

        var WIDTH = 0
        var HEIGHT = 0

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