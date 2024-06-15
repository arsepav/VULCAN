package good.damn.kamchatka.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import good.damn.kamchatka.Application
import java.net.URL

class HTTPUtils {
    companion object {
        fun loadImage(
            url: String,
            completion: ((Bitmap)->Unit)
        ) {
            Thread {
                val stream = URL(url).openStream()
                val b = BitmapFactory.decodeStream(
                    stream
                )
                stream.close()

                Application.ui {
                    completion(b)
                }
            }.start()
        }
    }
}