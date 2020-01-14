package com.jeanboy.component.network.core

import com.jeanboy.component.network.constants.Code

/**
 *
 * @author caojianbo
 * @since 2020/1/13 18:25
 */
class Result<Body> constructor() {
    var code: Int? = null
    var headers: MutableMap<String, Any?>? = null
    var body: Body? = null
    var message: String? = null

    constructor(body: Body) : this() {
        this.code = Code.OK
        this.body = body
    }

    constructor(message: String) : this() {
        this.code = Code.BAD_REQUEST
        this.message = message
    }

    constructor(code: Int, headers: MutableMap<String, Any?>, body: Body) : this() {
        this.code = code
        this.headers = headers
        this.body = body
    }
}