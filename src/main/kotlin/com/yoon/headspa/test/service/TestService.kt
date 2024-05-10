package com.yoon.headspa.test.service

import com.yoon.headspa.intenal.entity.jpa.UsersEntity
import com.yoon.headspa.internal.repository.jpa.UsersEntityRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TestService(
    final val usersEntityRepository: UsersEntityRepository,
//    final val usersMapper: UsersMapper,
) {
    
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun testSer(
//        searchText: String,
//        pageable: Pageable
    ): List<UsersEntity> {
        return usersEntityRepository.findAll()
    }

}