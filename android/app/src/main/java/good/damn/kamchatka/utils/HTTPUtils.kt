package good.damn.kamchatka.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import good.damn.kamchatka.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.IDN
import java.net.URL
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class HTTPUtils {
    companion object {
        private const val TAG = "HTTPUtils"
        fun loadImage(
            url: String,
            completion: ((Bitmap?)->Unit)
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                Log.d(TAG, "loadImage: $url")
                val stream = try {
                    URL(
                        url
                    ).openStream()
                } catch (e: Exception) {
                    Application.ui {
                        completion(null)
                    }
                    return@launch
                }
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