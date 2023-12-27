// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import org.springframework.ldap.core.DefaultNameClassPairMapper
import org.springframework.ldap.core.DirContextProcessor
import org.springframework.ldap.core.LdapEntryIdentificationContextMapper
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.core.support.CountNameClassPairCallbackHandler
import org.springframework.ldap.core.support.DefaultIncrementalAttributesMapper
import javax.naming.Context
import javax.naming.NamingEnumeration
import javax.naming.NamingException
import javax.naming.directory.DirContext
import javax.naming.directory.InitialDirContext
import javax.naming.directory.SearchControls
import javax.naming.directory.SearchResult
import java.util
import java.util.Properties


object LDAPInjection {
  private val ldapURI = "ldaps://ldap.server.com/dc=ldap,dc=server,dc=com"
  private val contextFactory = "com.sun.jndi.ldap.LdapCtxFactory"

  /** *************** JNDI LDAP ********************* */
  private[inject] def authenticate(username: String, password: String) = try {
    val props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    props.put(Context.PROVIDER_URL, "ldap://ldap.example.com")
    props.put(Context.REFERRAL, "ignore")
    props.put(Context.SECURITY_PRINCIPAL, dnFromUser(username))
    props.put(Context.SECURITY_CREDENTIALS, password)
    new InitialDirContext(props)
    true
  } catch {
    case e: Exception =>
      false
  }

  @throws[NamingException]
  private def dnFromUser(username: String) = {
    val props = new Properties();
    props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory")
    props.put(Context.PROVIDER_URL, "ldap://ldap.example.com")
    props.put(Context.REFERRAL, "ignore")
    val context = new InitialDirContext(props)
    val ctrls = new SearchControls
    ctrls.setReturningAttributes(Array[String]("givenName", "sn"))
    ctrls.setSearchScope(SearchControls.SUBTREE_SCOPE)
    val answers = context.search("dc=People,dc=example,dc=com", "(uid=" + username + ")", ctrls)
    val result = answers.next
    result.getNameInNamespace
  }

  @throws[Exception]
  private def ldapContext(env: util.Hashtable[String,String]) = {
    env.put(Context.INITIAL_CONTEXT_FACTORY, contextFactory)
    env.put(Context.PROVIDER_URL, ldapURI)
    env.put(Context.SECURITY_AUTHENTICATION, "none")
    val ctx = new InitialDirContext(env)
    ctx
  }

  @throws[Exception]
  def testBind(dn: Nothing, password: String): Boolean = {
    val env = new util.Hashtable[String,String]
    env.put(Context.SECURITY_AUTHENTICATION, "simple") //false positive

    env.put(Context.SECURITY_PRINCIPAL, dn)
    env.put(Context.SECURITY_CREDENTIALS, password)
    try ldapContext(env)
    catch {
      case e: Exception =>
        return false
    }
    true
  }

  /** *************** JNDI LDAP SPECIAL ********************* */
  @throws[NamingException]
  def main(param: Nothing): Unit = {
    val ctx: DirContext = null
    val base = "ou=users,ou=system"
    val sc = new SearchControls()
    sc.setSearchScope(SearchControls.SUBTREE_SCOPE)
    val filter = "(&(objectclass=person))(|(uid=" + param + ")(street={0}))"
    val filters = Array[Object]("The streetz 4 Ms bar")
    System.out.println("Filter " + filter)
    ctx.search(base, filter, filters, sc)
  }
}

class LDAPInjection {
  /** *************** SPRING LDAP ********************* */
  @throws[NamingException]
  def queryVulnerableToInjection(template: LdapTemplate, jndiInjectMe: String, searchControls: SearchControls, dirContextProcessor: DirContextProcessor): Unit = {
    template.list(jndiInjectMe)
    template.list(jndiInjectMe, new DefaultNameClassPairMapper)
    template.list(jndiInjectMe, new CountNameClassPairCallbackHandler)
    template.lookup(jndiInjectMe)
    val mapper = new DefaultIncrementalAttributesMapper("")
    template.lookup(jndiInjectMe, mapper)
    template.lookup(jndiInjectMe, mapper)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", searchControls, mapper, dirContextProcessor)
    template.search(jndiInjectMe, "dn=1", mapper)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, new Array[String](0), mapper)
    template.search(jndiInjectMe, "dn=1", SearchControls.OBJECT_SCOPE, mapper)
    template.search(jndiInjectMe, "dn=1", mapper)
  }

  @throws[NamingException]
  def safeQuery(template: LdapTemplate, searchControls: String, dirContextProcessor: DirContextProcessor): Unit = {
    val safeQuery = "uid=test"
    template.list(safeQuery)
    template.list(safeQuery, new DefaultNameClassPairMapper)
    template.list(safeQuery, new CountNameClassPairCallbackHandler())
    template.lookup(safeQuery)
    val mapper = new DefaultIncrementalAttributesMapper("")
    template.lookup(safeQuery, mapper)
    template.lookup(safeQuery, mapper)
  }
}
