package com.yoon.headspa.common.rm

import kr.co.eoding.hotelota.common.rm.ParentRm

data class AnyRm (
    var data: Any? = mapOf<Any, Any>()
): ParentRm()