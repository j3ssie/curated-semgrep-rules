// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=commons-beanutils.commons-beanutils@1.9.4
package inject

import org.apache.commons.beanutils.BeanUtils
import org.apache.commons.beanutils.BeanUtilsBean
import javax.servlet.http.HttpServletRequest
import java.lang.reflect.InvocationTargetException
import java.util


class BeanPropertyInjection {
  @throws[InvocationTargetException]
  @throws[IllegalAccessException]
  def danger(bean: Nothing, request: HttpServletRequest): Unit = {
    val map = new util.HashMap[String, String]()
    map.put("test", request.getParameter("test"))
    BeanUtils.populate(bean, map)
  }

  @throws[InvocationTargetException]
  @throws[IllegalAccessException]
  def danger2(bean: Nothing, request: HttpServletRequest): Unit = {
    val map = new util.HashMap[String, String]()
    val names = request.getParameterNames
    while ( {
      names.hasMoreElements
    }) {
      val name = names.nextElement.asInstanceOf[Nothing]
      map.put(name, request.getParameterValues(name).last)
    }
    BeanUtils.populate(bean, map)
  }

  @throws[InvocationTargetException]
  @throws[IllegalAccessException]
  def danger3(bean: Nothing, request: HttpServletRequest): Unit = {
    val map = new util.HashMap[String, String]()
    val names = request.getParameterNames
    while ( {
      names.hasMoreElements
    }) {
      val name = names.nextElement.asInstanceOf[Nothing]
      map.put(name, request.getParameterValues("x").last)
    }
    new BeanUtilsBean().populate(bean, map)
  }
}
