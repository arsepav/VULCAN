package good.damn.kamchatka.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import good.damn.kamchatka.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class URLUtils {
    companion object {
        private const val TAG = "URLUtils"
        fun loadImage(
            url: String,
            completion: ((Bitmap?)->Unit)
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {

                val bitmap = urlBitmap(
                    url
                )
                
                Application.ui {
                    completion(bitmap)
                }
            }
        }

        fun loadImage(
            url: String,
            inWidth: Int,
            inHeight: Int,
            completion: (Bitmap?) -> Unit
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                urlBitmap(
                    url
                )?.let {
                    val scaled = Bitmap.createScaledBitmap(
                        it,
                        inWidth,
                        inHeight,
                        false
                    )
                    
                    if (!it.isRecycled) {
                        it.recycle()
                    }

                    Application.ui {
                        completion(scaled)
                    }
                }

            }
        }
        
        private fun urlBitmap(
            url: String
        ): Bitmap? {
            Log.d(TAG, "urlBitmap: $url")
            val stream = try {
                URL(
                    url
                ).openStream()
            } catch (e: Exception) {
                Log.d(TAG, "urlBitmap: ERROR: ${e.message}")
                return null
            }
            
            val b = BitmapFactory.decodeStream(
                stream
            )
            stream.close()
            
            return b
        }
    }
}