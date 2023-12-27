// License: LGPL-3.0 License (c) find-sec-bugs
package templateinjection

import com.mitchellbosecke.pebble.error.PebbleException
import freemarker.template.Configuration
import freemarker.template.Template
import freemarker.template.TemplateException
import org.apache.velocity.app.Velocity
import org.apache.velocity.VelocityContext
import com.mitchellbosecke.pebble.PebbleEngine
import com.mitchellbosecke.pebble.template.PebbleTemplate
import java.io._
import java.util._


class TemplateInjection {
  @throws[FileNotFoundException]
  def usage1(inputFile: String): Unit = {
    Velocity.init
    val context = new VelocityContext
    context.put("author", "Elliot A.")
    context.put("address", "217 E Broadway")
    context.put("phone", "555-1337")
    val file = new FileInputStream(inputFile)
    //Evaluate
    val swOut = new StringWriter
    Velocity.evaluate(context, swOut, "test", file.toString)
    val result = swOut.getBuffer.toString
    System.out.println(result)
  }

  @throws[FileNotFoundException]
  def allSignatures(inputStream: InputStream, fileReader: Reader, template: String): Unit = {
    val context = new VelocityContext
    val swOut = new StringWriter
    Velocity.evaluate(context, swOut, "test", inputStream.toString)
    Velocity.evaluate(context, swOut, "test", fileReader)
    Velocity.evaluate(context, swOut, "test", template)
  }

  @throws[FileNotFoundException]
  def falsePositive(): Unit = {
    val context = new VelocityContext
    val swOut = new StringWriter
    Velocity.evaluate(context, swOut, "test", "Hello $user !")
  }

  def simple1(inputFile: String): Unit = { //Freemarker configuration object
    val cfg = new Configuration
    try { //Load template from source folder
      val template = cfg.getTemplate(inputFile)
      // Build the data-model
      val data = new HashMap[String, Object]()
      data.put("message", "Hello World!")
      //List parsing
      val countries = new ArrayList[String]()
      countries.add("India")
      countries.add("United States")
      countries.add("Germany")
      countries.add("France")
      data.put("countries", countries)
      // Console output
      val out = new OutputStreamWriter(System.out)
      template.process(data, out) //Vuln here

      out.flush
    } catch {
      case e: IOException =>
        e.printStackTrace()
      case e: TemplateException =>
        e.printStackTrace()
    }
  }

  @throws[IOException]
  @throws[TemplateException]
  def allSignatures1(inputFile: String): Unit = {
    val cfg = new Configuration
    val template = cfg.getTemplate(inputFile)
    val data = new HashMap[String, Object]
    template.process(data, new OutputStreamWriter(System.out)) //TP
    template.process(data, new OutputStreamWriter(System.out), null)
    template.process(data, new OutputStreamWriter(System.out), null, null)
  }

  @throws[IOException]
  def simple(inputFile: String): Unit = {
    val engine = new PebbleEngine.Builder().build
    var compiledTemplate: PebbleTemplate = null
    try {
      compiledTemplate = engine.getTemplate(inputFile)
    }
    catch {
      case e: PebbleException =>
        e.printStackTrace
    }
    val context = new HashMap[String, Object]
    context.put("name", "Shivam")
    val writer = new StringWriter
    try compiledTemplate.evaluate(writer, context)
    catch {
      case e: PebbleException =>
        e.printStackTrace
    }
    val output = writer.toString
  }

  @throws[IOException]
  @throws[PebbleException]
  def allSignatures(inputFile: String): Unit = {
    val engine = new PebbleEngine.Builder().build
    var compiledTemplate: PebbleTemplate = null
    compiledTemplate = engine.getTemplate(inputFile)
    val data = new HashMap[String, Object]
    compiledTemplate.evaluate(new StringWriter, data, Locale.US)
  }
}
