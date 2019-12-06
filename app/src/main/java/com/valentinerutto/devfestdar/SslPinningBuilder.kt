package com.valentinerutto.devfestdar

import okhttp3.CertificatePinner


class SslPinningBuilder {

    companion object {

        val domain: String = "*.github.com"
        val pin: String = "sha256/WoiWRyIOVNa9ihaBciRSC7XHjliYS9VwUGOIud4PB18="
        val pin1: String = "sha256/k2v657xBsOVe1PQRwOsHsw3bsGT2VzIqz5K+59sNQws="
        val pin2: String = "sha256/ORH27mxcLwxnNpR7e0i6pdDPWLXdpeWgr5bEfFVbxW8="

        fun getCertificatePinnerBuilder(): CertificatePinner {

            return CertificatePinner.Builder()
                .add(domain, pin)
                .add(domain, pin1)
                .add(domain, pin2)
                .build()
        }
    }

}