// License: LGPL-3.0 License (c) find-sec-bugs
package xml;


import org.apache.commons.io.IOUtils;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.IOException;

public class XsltTransform {

    public static final String FOLDER = "/testcode/xsl/";

    public void xslt1SafeStaticResource() throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(getClass().getResourceAsStream(FOLDER + "xsl_safe.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(getClass().getResourceAsStream(FOLDER + "input.xml"));
        transformer.transform(text, new StreamResult(System.out));
    }

    public void xslt2UnsafeResource(String input) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(getClass().getResourceAsStream(input));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(getClass().getResourceAsStream(FOLDER + "input.xml"));
        transformer.transform(text, new StreamResult(System.out));
    }

    public void xslt3UnsafeResource(String input) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(getClass().getResourceAsStream(FOLDER + input));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(getClass().getResourceAsStream(FOLDER + "input.xml"));
        transformer.transform(text, new StreamResult(System.out));
    }

    public void xslt4UnsafeResource(String input) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        FileInputStream in = null;
        try {
            in = new FileInputStream(FOLDER + input);
            Source xslt = new StreamSource(in);
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(getClass().getResourceAsStream(FOLDER + "input.xml"));
            transformer.transform(text, new StreamResult(System.out));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }

    public void xslt5SafeResource() throws TransformerException {
        String input = "/safe.xsl";
        TransformerFactory factory = TransformerFactory.newInstance();
        FileInputStream in = null;
        try {
            in = new FileInputStream(FOLDER + input);
            Source xslt = new StreamSource(in);
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(getClass().getResourceAsStream(FOLDER + "input.xml"));
            transformer.transform(text, new StreamResult(System.out));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
