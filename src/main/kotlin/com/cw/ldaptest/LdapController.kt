package com.cw.ldaptest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.core.support.LdapContextSource
import org.springframework.ldap.support.LdapUtils
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.naming.directory.DirContext

@RestController
class LdapController {

    public fun contextSource(): LdapContextSource {
        val ldapContext = LdapContextSource()
        ldapContext.setUrl("ldap://ldap.forumsys.com:389")
        ldapContext.setBase("dc=example,dc=com")
        ldapContext.setPassword("password")
        ldapContext.setUserDn("cn=read-only-admin")
        ldapContext.setCacheEnvironmentProperties(false)
        return ldapContext
    }

    @RequestMapping("/ldap")
    fun authenticate(@RequestParam(name = "username") username: String, @RequestParam(name = "password") password: String): String {

        var context: DirContext? = null

        try {
            contextSource().getContext("uid=$username,dc=example,dc=com", password)
        } catch (e: Exception) {
            return e.toString()
        } finally {
            LdapUtils.closeContext(context)
        }

        return "GreatSuccess"
    }


}