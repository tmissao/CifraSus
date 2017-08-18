package br.com.missao.cifrasus.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import br.com.missao.cifrasus.exceptions.NoConnectivityException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to check if there is internet connection
 */
class ConnectivityInterceptor(private val context: Context) : Interceptor {

    /**
     * Intercepts the request checking if there is internet connection. In case that there is not
     * internet the exception [NoConnectivityException] is thrown
     */
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!isOnline()) {
            throw NoConnectivityException()
        }

        val builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    /**
     * Checks if there is internet connection
     */
    private fun isOnline(): Boolean {

        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = connectivityManager.activeNetworkInfo
            return netInfo.isConnected

        } catch (e: Exception) {
            Log.e("util", "", e)
            return false
        }
    }
}