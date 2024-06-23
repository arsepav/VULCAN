package good.damn.kamchatka.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import good.damn.kamchatka.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class HTTPUtils {
    companion object {
        fun loadImage(
            url: String,
            completion: ((Bitmap)->Unit)
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                val stream = URL(url).openStream()
                val b = BitmapFactory.decodeStream(
                    stream
                )
                stream.close()

                Application.ui {
                    completion(b)
                }
            }
        }
    }
}