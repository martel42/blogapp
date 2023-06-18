package ru.mks.rsoi.subs.entity

import jakarta.persistence.*
import ru.mks.rsoi.subs.entity.UserSubs

@Entity
@Table(name = "subs")
class Subs (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val userUid: Long,
        open val blogId: Long
//        @ManyToOne
//        @JoinColumn(name = "user_subs_id")
//        open val userSubs: UserSubs
        )