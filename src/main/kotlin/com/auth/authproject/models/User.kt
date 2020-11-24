package com.auth.authproject.models

import javax.persistence.*

@Entity
@Table(name="user_table")
open class User(
        @Column(nullable=false)
        var username:String="",

        @Column(unique = true,nullable = false)
        var email:String="",

        @Column(nullable=false)
        var password:String="",

        @Enumerated(value = EnumType.STRING)
        var role:roles =roles.ROLE_USER,

        var isEnabled:Boolean=true,

        var isNotLocked:Boolean=true,

        var isNotExpired:Boolean=true,

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long=0

        )
{
       public constructor(user:User) :this(user.username,user.email, user.password){
                username=user.username
                password=user.password
                email=user.email
                role=user.role
                isEnabled=user.isEnabled
                isNotExpired=user.isNotExpired
                isNotLocked=user.isNotLocked
        }
}