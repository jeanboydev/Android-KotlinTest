package com.jeanboy.component.network.constants

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:11
 */
interface Code {
    companion object {
        const val UNKNOWN_EXCEPTION = 0
        const val OK = 200
        const val MULTIPLE_CHOICES = 300
        const val MOVED_PERMANENTLY = 301
        const val NOT_MODIFIED = 304
        const val BAD_REQUEST = 400
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val UNSUPPORTED_MEDIA_TYPE = 415
        const val INTERNAL_SERVER_ERROR = 500
    }
}