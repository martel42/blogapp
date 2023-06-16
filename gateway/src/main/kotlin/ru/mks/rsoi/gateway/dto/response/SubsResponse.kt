package ru.mks.rsoi.gateway.dto.response




class SubsResponse (
        open val id: Long,
        open val userUid: Long,

        open val userSubsResponse: UserSubsResponse
        )