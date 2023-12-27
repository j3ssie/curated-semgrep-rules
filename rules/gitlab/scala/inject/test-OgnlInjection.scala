// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import com.opensymphony.xwork2.ognl.OgnlReflectionProvider
import com.opensymphony.xwork2.ognl.OgnlUtil
import com.opensymphony.xwork2.util.TextParseUtil
import ognl.OgnlException
import com.opensymphony.xwork2.ognl

import javax.management.ReflectionException
import java.beans.IntrospectionException
import java.util


class OgnlInjection {
  @throws[OgnlException]
  @throws[ReflectionException]
  def unsafeOgnlUtil(ognlUtil: OgnlUtil, input: String, propsInput: util.HashMap[String,String]): Unit = {
    ognlUtil.setValue(input, null, null, "12345")
    ognlUtil.getValue(input, null, null, null)
    ognlUtil.setProperty(input, "12345", null, null)
    ognlUtil.setProperty(input, "12345", null, null, true)
    ognlUtil.setProperties(propsInput, new Object())
    ognlUtil.setProperties(propsInput, null, null, true)
    ognlUtil.setProperties(propsInput, null, true)
    ognlUtil.setProperties(propsInput, null)
    //ognlUtil.callMethod(input, null, null);
    ognlUtil.compile(input)
    ognlUtil.compile(input)
  }

  @throws[OgnlException]
  @throws[ReflectionException]
  def safeOgnlUtil(ognlUtil: OgnlUtil): Unit = {
    val input = "thisissafe"
    val map = new util.HashMap[String,String]()
    ognlUtil.setValue(input, null, null, "12345")
    ognlUtil.getValue(input, null, null, null)
    ognlUtil.setProperty(input, "12345", null, null)
    ognlUtil.setProperty(input, "12345", null, null, true)
    ognlUtil.setProperties(map, null, null)
    ognlUtil.setProperties(map, null, null, true)
    ognlUtil.setProperties(map, null, true)
    ognlUtil.setProperties(map, null)
    ognlUtil.compile(input)
    ognlUtil.compile(input)
  }

  @throws[ReflectionException]
  @throws[IntrospectionException]
  def unsafeOgnlReflectionProvider(input: Nothing, propsInput: Nothing, reflectionProvider: Nothing, `type`: Nothing): Unit = {
    var reflectionProvider: OgnlReflectionProvider = null
    reflectionProvider.getGetMethod(`type`, input)
    reflectionProvider.getSetMethod(`type`, input)
    reflectionProvider.getField(`type`, input)
    reflectionProvider.setProperties(propsInput, null, null, true)
    reflectionProvider.setProperties(propsInput, null, null)
    reflectionProvider.setProperties(propsInput, null)
    reflectionProvider.setProperty(input, "test", null, null)
    // reflectionProvider.setProperty( input, "test",null, null, true);
    reflectionProvider.getValue(input, null, null)
    reflectionProvider.setValue(input, null, null, null)
  }

  @throws[IntrospectionException]
  @throws[ReflectionException]
  def safeOgnlReflectionProvider(reflectionProvider: Nothing, `type`: Nothing): Unit = {
    var reflectionProvider: OgnlReflectionProvider = null
    val input = "thisissafe"
    val constant1 = ""
    val constant2 = ""

    val map = new util.HashMap[String,String]()
    reflectionProvider.getGetMethod(`type`, input)
    reflectionProvider.getSetMethod(`type`, input)
    reflectionProvider.getField(`type`, input)
    reflectionProvider.setProperties(map, null, null, true)
    reflectionProvider.setProperties(map, null, null)
    reflectionProvider.setProperties(map, null)
    reflectionProvider.setProperty("test", constant1, null, null)
    // reflectionProvider.setProperty("test", constant2, null, null, true);
    reflectionProvider.getValue(input, null, null)
    reflectionProvider.setValue(input, null, null, null)
  }

  def unsafeTextParseUtil(input: String): Unit = {
    TextParseUtil.translateVariables(input, null)
    TextParseUtil.translateVariables(input, null, null)
    TextParseUtil.translateVariables('a', input, null)
    TextParseUtil.translateVariables('a', input, null, null)
    TextParseUtil.translateVariables('a', input, null, null, null, 0)
  }

  def safeTextParseUtil(stack: Nothing, parsedValueEvaluator: Nothing, `type`: Nothing): Unit = {
    val input = "1+1"
    TextParseUtil.translateVariables(input, stack)
    TextParseUtil.translateVariables(input, stack, parsedValueEvaluator)
    TextParseUtil.translateVariables('a', input, stack)
    TextParseUtil.translateVariables('a', input, stack, `type`)
    TextParseUtil.translateVariables('a', input, stack, `type`, parsedValueEvaluator, 0)
  }
}
