package good.damn.kamchatka

import android.app.Application
import android.content.res.Resources
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.res.ResourcesCompat

class Application
: Application() {

    companion object {
        lateinit var RESOURCES: Resources

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
    }

    override fun onCreate() {
        super.onCreate()

        RESOURCES = applicationContext
            .resources

    }

}