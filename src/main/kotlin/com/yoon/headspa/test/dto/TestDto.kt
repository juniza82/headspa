package com.yoon.headspa.test.dto

import io.swagger.v3.oas.annotations.media.Schema

class TestDto(
    @Schema(description = "회원ID") var user_id: Int = 0,
    @Schema(description = "회원이름") var user_name: String = "",
    @Schema(description = "비밀번호") var password: String = "",
    @Schema(description = "회원성별") var gender: String = "",
    @Schema(description = "회원나이") var age: Int = 0,
    @Schema(description = "회원이메일") var e_mail: String = "",
    @Schema(description = "회원핸드폰번호") var phone: String = "",
    @Schema(description = "회원등급") var grade: String = "",
    @Schema(description = "회원가입일") var created_at: String = "",
    @Schema(description = "회원ID") var created_id: Int = 0,
    @Schema(description = "회원수정일") var modified_at: String = "",
    @Schema(description = "회원ID") var modified_id: Int = 0,
    @Schema(description = "회원탈퇴일") var deleted_at: String = "",
    @Schema(description = "회원탈퇴여부") var deleted_flg: String = "",
)

