// License: LGPL-3.0 License (c) find-sec-bugs
package ldap

import java.util._
import javax.naming.Context
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext

object AnonymousLDAP {
  private val ldapURI = "ldaps://ldap.server.com/dc=ldap,dc=server,dc=com"
  private val contextFactory = "com.sun.jndi.ldap.LdapCtxFactory"

  @throws[Exception]
  private def ldapContext(env: Hashtable[String, String]) = {
    env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory)
    env.put(Context.PROVIDER_URL, ldapURI)
    env.put(Context.SECURITY_AUTHENTICATION, "none")
    val ctx = new InitialDirContext(env)
    ctx
  }

  @throws[Exception]
  def testBind(dn: String, password: String): Boolean = {
    val env = new Hashtable[String, String]
    env.put(Context.SECURITY_AUTHENTICATION, "simple") //false positive

    env.put(Context.SECURITY_PRINCIPAL, dn)
    env.put(Context.SECURITY_CREDENTIALS, password)
    try ldapContext(env)
    catch {
      case e: javax.naming.AuthenticationException =>
        return false
    }
    true
  }
}
