// License: LGPL-3.0 License (c) find-sec-bugs
package strings

import java.text.Normalizer
import java.util.regex.Matcher
import java.util.regex.Pattern


class ModifyAfterValidation {
  def modifyDanger(str: String) = {
    var s = Normalizer.normalize(str, Normalizer.Form.NFKC)
    val pattern = Pattern.compile("<script>")
    val matcher = pattern.matcher(s)
    if (matcher.find) throw new IllegalArgumentException("Invalid input")
    s = s.replaceAll("[\\p{Cn}]", "") // modified after validation

    s
  }

  def modifyDanger2(str: String) = {
    var s = Normalizer.normalize(str, Normalizer.Form.NFKC)
    val pattern = Pattern.compile("<script>")
    val matcher = pattern.matcher(s)
    if (matcher.find) throw new Exception("Invalid input")
    s = s.replace("[\\p{Cn}]", "")
    s
  }
}
