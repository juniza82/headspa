package com.yoon.headspa.common.rm

import com.yoon.headspa.common.rm.ParentRm

data class AnyRm (
    var data: Any? = mapOf<Any, Any>()
): ParentRm()