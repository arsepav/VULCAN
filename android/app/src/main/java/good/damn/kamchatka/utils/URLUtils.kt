package good.damn.kamchatka.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import good.damn.kamchatka.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import kotlin.math.log

class URLUtils {
    companion object {
        private const val TAG = "URLUtils"
        fun loadImage(
            url: String,
            cacheDir: File,
            completion: ((Bitmap?, Boolean)->Unit)
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {

                val cachedBitmap = loadBitmapFromCache(
                    url,
                    cacheDir
                )

                if (cachedBitmap != null) {
                    Application.ui {
                        completion(cachedBitmap, true)
                    }
                }
                
                val bitmap = urlBitmap(
                    url
                )

                bitmap?.let {
                    saveImageCache(
                        it,
                        url,
                        cacheDir
                    )
                }

                Application.ui {
                    completion(bitmap,false)
                }
            }
        }

        fun loadImage(
            url: String,
            inWidth: Int,
            inHeight: Int,
            cacheDir: File,
            completion: (Bitmap?, Boolean) -> Unit
        ) {
            CoroutineScope(
                Dispatchers.IO
            ).launch {
                
                val cachedBitmap = loadBitmapFromCache(
                    url,
                    cacheDir
                )
                
                if (cachedBitmap != null) {
                    Application.ui {
                        completion(cachedBitmap,true)
                    }
                }
                
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

                    saveImageCache(
                        scaled,
                        url,
                        cacheDir
                    )
                    
                    Application.ui {
                        completion(scaled,false)
                    }
                }

            }
        }

        private fun getCachedFile(
            url: String,
            cacheDir: File
        ): File {
            val hash = url.hashCode()
            return File("$cacheDir/$hash")
        }

        private fun saveImageCache(
            bitmap: Bitmap,
            url: String,
            cacheDir: File
        ) {
            val f = getCachedFile(
                url,
                cacheDir
            )
            
            try {
                if (!f.exists() && f.createNewFile()) {
                    Log.d(TAG, "saveImageCache: ${f.name} created")
                }

                val fos = FileOutputStream(f)
                bitmap.compress(
                    Bitmap.CompressFormat.JPEG,
                    100,
                    fos
                )
                fos.close()
            } catch (e: Exception) {
                Log.d(TAG, "saveImageCache: ERROR: ${e.message}")
            }
        }
        
        private fun loadBitmapFromCache(
            url: String,
            cacheDir: File
        ): Bitmap? {

            val file = getCachedFile(
                url,
                cacheDir
            )

            Log.d(TAG, "urlBitmap: $url $file")
            
            if (file.exists()) {
                var b: Bitmap? = null
                try {
                    val fis = FileInputStream(file)
                    b = BitmapFactory.decodeStream(
                        fis
                    )
                    fis.close()
                } catch (e: IOException) {
                    Log.d(TAG, "urlBitmap: CACHE_ERROR: ${e.message}")
                }
                return b
            }
            return null
        }
        
        private fun urlBitmap(
            url: String
        ): Bitmap? {
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