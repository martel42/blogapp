package ru.mks.rsoi.user.entity

import jakarta.persistence.*

@Entity
@Table(name = "role")
class Role (
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        open val id: Long,
        open val authority: String
        )