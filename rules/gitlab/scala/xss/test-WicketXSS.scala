// License: LGPL-3.0 License (c) find-sec-bugs
package xss

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters

class WicketXSS extends WebPage {
  def XssWicketExamplePage(pageParameters: PageParameters): Unit = {
    add(new Label("test").setEscapeModelStrings(false))
  }
}

