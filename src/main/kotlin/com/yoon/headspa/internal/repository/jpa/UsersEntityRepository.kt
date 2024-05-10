package com.yoon.headspa.internal.repository.jpa

import com.yoon.headspa.intenal.entity.jpa.UsersEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UsersEntityRepository : JpaRepository<UsersEntity, Int> {
//    fun findAll(codes: List<String>): List<UsersEntity>
}