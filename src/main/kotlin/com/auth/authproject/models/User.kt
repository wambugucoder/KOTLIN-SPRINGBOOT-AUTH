package com.auth.authproject.models

import javax.persistence.*

@Entity
@Table(name="user_table")
open class User(
        @Column(nullable=false)
        open var username:String="",

        @Column(unique = true,nullable = false)
       open  var email:String="",

        @Column(nullable=false)
        open var password:String="",

        @Enumerated(value = EnumType.STRING)
       open  var role:roles =roles.ROLE_USER,

        open var isEnabled:Boolean=true,

       open  var isNotLocked:Boolean=true,

        open var isNotExpired:Boolean=true,

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        open var id:Long=0

        )
{
       public constructor(user:User) :this(user.username,user.email, user.password){
                id=user.id
                username=user.username
                password=user.password
                email=user.email
                role=user.role
                isEnabled=user.isEnabled
                isNotExpired=user.isNotExpired
                isNotLocked=user.isNotLocked
        }
}