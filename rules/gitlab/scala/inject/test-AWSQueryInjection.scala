// License: LGPL-3.0 License (c) find-sec-bugs
package inject

import java.io.IOException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpServlet
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.simpledb.AmazonSimpleDB
import com.amazonaws.services.simpledb.AmazonSimpleDBClient
import com.amazonaws.services.simpledb.model.SelectRequest
import com.amazonaws.services.simpledb.model.SelectResult


class AWSQueryInjection extends HttpServlet {
  @throws[IOException]
  override def doGet(request: HttpServletRequest, response: HttpServletResponse): Unit = {
    try {
      val customerID = request.getParameter("customerID")
      val awsCredentials = new BasicAWSCredentials("test", "test")
      val sdbc = new AmazonSimpleDBClient(awsCredentials)
      val query = "select * from invoices where customerID = '" + customerID
      val sdbResult = sdbc.select(new SelectRequest(query)) //BAD
      val sdbResult2 = sdbc.select(new SelectRequest(query, false))
      val sdbRequest = new SelectRequest()
      val sdbResult3 = sdbc.select(sdbRequest.withSelectExpression(query))
      val query2 = "select * from invoices where customerID = 123"
      val sdbResult4 = sdbc.select(new SelectRequest(query2)) //OK
    } catch {
      case _: Throwable =>
    }
  }

  def danger(customerID: Nothing, productCategory: Nothing): Unit = {
    val sdbc = AmazonSimpleDBClient.builder.build
    val query = "select * from invoices where productCategory = '" + productCategory + "' and customerID = '" + customerID + "' order by '"
    val sdbResult = sdbc.select(new SelectRequest(query))
  }

  def danger2(customerID: Nothing, productCategory: Nothing): Unit = {
    val sdbc = AmazonSimpleDBClient.builder.build
    val query = "select * from invoices where productCategory = '" + productCategory + "' and customerID = '" + customerID + "' order by '"
    val sdbResult = sdbc.select(new SelectRequest(query, false))
  }
}
