package ru.mks.rsoi.subs.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_subs")
class UserSubs (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val blogId: Long
)