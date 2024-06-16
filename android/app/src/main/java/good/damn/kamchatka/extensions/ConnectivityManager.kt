package good.damn.kamchatka.extensions

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

fun ConnectivityManager.isAvailable(): Boolean {
    val nw = activeNetwork
        ?: return false

    getNetworkCapabilities(
        nw
    )?.apply {
        return hasTransport(
            NetworkCapabilities.TRANSPORT_WIFI
        ) || hasTransport(
            NetworkCapabilities.TRANSPORT_CELLULAR
        ) || hasTransport(
            NetworkCapabilities.TRANSPORT_ETHERNET
        ) || hasTransport(
            NetworkCapabilities.TRANSPORT_BLUETOOTH
        )
    }
    return false
}