package com.cw.ldaptest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class LdapTestApplication

fun main(args: Array<String>) {
    runApplication<LdapTestApplication>(*args)
}
