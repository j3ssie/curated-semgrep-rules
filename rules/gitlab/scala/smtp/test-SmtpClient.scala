// License: LGPL-3.0 License (c) find-sec-bugs
package smtp

import javax.activation.FileDataSource
import javax.mail._
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.servlet.http.HttpServletRequest
import java.net.URLEncoder
import java.util.Properties

object SmtpClient {
  @throws[Exception]
  def main(args: Array[String]): Unit = { //Example of payload that would prove the injection
    sendEmail("Testing Subject\nX-Test: Override", "test\nDEF:2", "SVW:2\nXYZ", "ou\nlalala:22\n\rlili:22", "123;\n456=889", "../../../etc/passwd")
  }

  @throws[Exception]
  def sendEmail(input1: String, input2: String, input3: String, input4: String, input5: String, input6: String): Unit = {
    val username = "email@gmail.com" //Replace by test account
    val password = "" //Replace by app password
    val props = new Properties
    props.put("mail.smtp.auth", "true")
    props.put("mail.smtp.starttls.enable", "true")
    props.put("mail.smtp.host", "smtp.gmail.com")
    props.put("mail.smtp.port", "587")
    val session = Session.getInstance(props, new Authenticator () {
    })
    val message = new MimeMessage(session)
    message.setFrom(new InternetAddress("source@gmail.com"))
    message.setSubject(input1) //Injectable API

    message.addHeader("ABC", input2) //Injectable API (value parameter)

    message.addHeader(input3, "aa") //Injectable API (key parameter)

    message.setDescription(input4)
    message.setDisposition(input5)
    message.setText("This is just a test 2.")
    Transport.send(message)
    new FileDataSource("/path/traversal/here/" + input6)
    System.out.println("Done")
  }

  @throws[MessagingException]
  def sendEmailTainted(session: Session, req: HttpServletRequest): Unit = {
    val message = new MimeMessage(session)
    message.setSubject(req.getParameter("user") + " is following you")
  }

  @throws[MessagingException]
  def sendEmailFP(session: Session, input: String): Unit = {
    val message = new MimeMessage(session)
    message.addHeader("abc", URLEncoder.encode(input))
  }
}
