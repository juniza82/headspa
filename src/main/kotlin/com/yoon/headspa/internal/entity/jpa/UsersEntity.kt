package com.yoon.headspa.intenal.entity.jpa

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.Comment
import java.time.LocalDateTime

@Entity
@Comment("회원")
@Table(name = "users")
open class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원ID")
    @Column(name = "user_id", nullable = false)
    open var userId: Int = 0

    @Size(max = 100)
    @Comment("회원이름")
    @Column(name = "user_name", length = 100)
    open var userName: String? = null

    @Comment("비밀번호")
    @Column(name = "password", length = 300)
    open var password: String? = null

    @Comment("회원성별")
    @Column(name = "gender")
    open var gender: String? = null

    @Comment("회원나이")
    @Column(name = "age")
    open var age: Int? = null

    @Size(max = 100)
    @Comment("회원 이메일")
    @Column(name = "e_mail", length = 100)
    open var eMail: String? = null

    @Size(max = 30)
    @Comment("회원 핸드폰번호")
    @Column(name = "phone", length = 30)
    open var phone: String? = null

    @Comment("회원 등급")
    @Column(name = "grade")
    open var grade: String? = null

    @NotNull
    @Comment("회원가입일")
    @Column(name = "created_at", nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now()

    @Comment("회원생성ID")
    @Column(name = "created_id", nullable = false)
    open var createdId: Int = 0

    @NotNull
    @Comment("회원수정일")
    @Column(name = "modified_at", nullable = false)
    open var modifiedAt: LocalDateTime = LocalDateTime.now()

    @Comment("회원수정ID")
    @Column(name = "modified_id", nullable = false)
    open var modifiedId: Int = 0

    @Comment("회원탈퇴일")
    @Column(name = "deleted_at", nullable = false)
    open var deletedAt: LocalDateTime = LocalDateTime.now()

    @Comment("회원탈퇴여부")
    @Column(name = "deleted_flg")
    open var deletedFlg: String? = null
}
