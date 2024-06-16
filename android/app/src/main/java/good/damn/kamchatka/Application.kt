package good.damn.kamchatka

import android.app.Application
import android.content.Context
import android.content.res.Resources
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.media.session.MediaSession.Token
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import good.damn.kamchatka.models.TokenAuth
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class Application
: Application() {

    companion object {
        lateinit var RESOURCES: Resources

        const val KEY_SHARED = "DATA"
        const val URL = "http://91.224.86.144:8000"

        var WIDTH = 0
        var HEIGHT = 0

        var TOKEN: TokenAuth? = null

        val JSON = "application/json; charset=utf-8"
            .toMediaTypeOrNull()

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