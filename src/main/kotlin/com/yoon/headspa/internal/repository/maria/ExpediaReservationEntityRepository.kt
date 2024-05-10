package com.yoon.headspa.internal.repository.maria;

//import kr.co.eoding.hotelota.expedia.entity.jpa.ExpediaReservationEntity
//import org.springframework.data.domain.Pageable
//import org.springframework.data.jpa.repository.JpaRepository
//import org.springframework.data.jpa.repository.Query
//
//interface ExpediaReservationEntityRepository : JpaRepository<ExpediaReservationEntity, Int> {
//    fun findFirstByCode(code: String): ExpediaReservationEntity?
//    fun findByConfirmedId(confirmedId: String): ExpediaReservationEntity?
//
//    fun findAllByEodingId(eodingId: Int): List<ExpediaReservationEntity>
//
//    fun findFirstByEodingId(eodingId: Int): ExpediaReservationEntity?
//
//    @Query("select r " +
//            " from ExpediaReservationEntity r " +
//            " join EodingReservationEntity o " +
//            " on r.eoding.id = o.id " +
//            " where (:code is null or r.code = :code) " +
//            " and (:status is null or r.status = :status) " +
//            " and (:name is null or r.hotelName = :name) " +
//            " and (:email is null or r.occupancy like :email) " +
//            " and (:phone is null or r.occupancy like :phone) " +
//            " and (:hotelName is null or r.hotelName = :hotelName) " +
//            " and ((:checkInFrom is null and :checkInTo is null) or (r.checkInDate between :checkInFrom and :checkInTo)) " +
//            " and (:countryCode is null or r.countryCode = :countryCode) " +
//            " and (:cityCode is null or r.cityCode = :cityCode) " +
//            " and o.travelAgentId = :travelAgentId"
//    )
//    fun findReservationListByCondition(
//        code: String?,
//        status: String?,
//        name: String?,
//        email: String?,
//        phone: String?,
//        hotelName: String?,
//        checkInFrom: String?,
//        checkInTo: String?,
//        countryCode: String?,
//        cityCode: String?,
//        travelAgentId: Int,
//        pageable: Pageable
//    ): List<ExpediaReservationEntity>
//}