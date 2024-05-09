package com.yoon.headspa.common.rm

import org.springframework.http.HttpStatus

open class ParentRm(
    var status: HttpStatus = HttpStatus.OK,
    var message: String = "",
    var code: String = "",
)