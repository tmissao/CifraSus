package br.com.missao.cifrasus.exceptions

import java.io.IOException


/**
 * Exception indicating that there is not internet connection
 */
class NoConnectivityException : IOException() {

    override val message: String?
        get() = "No Connectivity Exception"
}