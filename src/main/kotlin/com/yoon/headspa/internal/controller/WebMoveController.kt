package com.yoon.headspa.internal.controller

import io.swagger.v3.oas.annotations.Operation
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/main")
class WebMoveController(
) {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/main")
    @Deprecated(message = "이건 스웨거에서 사용안할때 사용")
    @Operation(summary = "API 요약", description = "API 설명")
    fun welcome(model: Model):String {
        model.addAttribute("greeting", "Hello Thymeleaf!")
        return "index"
    }

    @GetMapping("/main2")
    @Deprecated(message = "이건 스웨거에서 사용안할때 사용")
    @Operation(summary = "API 요약", description = "API 설명")
    fun second(model: Model):String {
        model.addAttribute("greeting", "Hello Thymeleaf!")
        return "sample-inner-page.html"
    }



}

