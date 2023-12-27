// License: LGPL-3.0 License (c) find-sec-bugs
package templateinjection;

import com.mitchellbosecke.pebble.error.PebbleException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.VelocityContext;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.*;
import java.util.*;

public class TemplateInjection {

    public void usage1(String inputFile) throws FileNotFoundException {
        Velocity.init();

        VelocityContext context = new VelocityContext();

        context.put("author", "Elliot A.");
        context.put("address", "217 E Broadway");
        context.put("phone", "555-1337");

        FileInputStream file = new FileInputStream(inputFile);

        //Evaluate
        StringWriter swOut = new StringWriter();
        Velocity.evaluate(context, swOut, "test", file.toString());

        String result =  swOut.getBuffer().toString();
        System.out.println(result);
    }

    public void allSignatures(InputStream inputStream, Reader fileReader, String template) throws FileNotFoundException {
        VelocityContext context = new VelocityContext();
        StringWriter swOut = new StringWriter();

        Velocity.evaluate(context, swOut, "test", inputStream.toString());
        Velocity.evaluate(context, swOut, "test", fileReader);
        Velocity.evaluate(context, swOut, "test", template);
    }

    public void falsePositive() throws FileNotFoundException {
        VelocityContext context = new VelocityContext();
        StringWriter swOut = new StringWriter();

        Velocity.evaluate(context, swOut, "test", "Hello $user !");
    }

    public void simple1(String inputFile) {

        //Freemarker configuration object
        Configuration cfg = new Configuration();
        try {
            //Load template from source folder
            Template template = cfg.getTemplate(inputFile);

            // Build the data-model
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("message", "Hello World!");

            //List parsing
            List<String> countries = new ArrayList<String>();
            countries.add("India");
            countries.add("United States");
            countries.add("Germany");
            countries.add("France");

            data.put("countries", countries);

            // Console output
            Writer out = new OutputStreamWriter(System.out);
            template.process(data, out); //Vuln here
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    public void allSignatures1(String inputFile) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        Template template = cfg.getTemplate(inputFile);
        Map<String, Object> data = new HashMap<String, Object>();

        template.process(data, new OutputStreamWriter(System.out)); //TP
        template.process(data, new OutputStreamWriter(System.out), null); //TP
        template.process(data, new OutputStreamWriter(System.out), null, null); //TP
    }

    public void simple(String inputFile) throws IOException {

        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = null;
        try {
            compiledTemplate = engine.getTemplate(inputFile);
        } catch (PebbleException e) {
            e.printStackTrace();
        }

        Map<String, Object> context = new HashMap<>();
        context.put("name", "Shivam");

        Writer writer = new StringWriter();
        try {
            compiledTemplate.evaluate(writer, context);
        } catch (PebbleException e) {
            e.printStackTrace();
        }

        String output = writer.toString();
    }

    public void allSignatures(String inputFile) throws IOException, PebbleException {
        PebbleEngine engine = new PebbleEngine.Builder().build();
        PebbleTemplate compiledTemplate = null;

        compiledTemplate = engine.getTemplate(inputFile);

        Map<String, Object> data = new HashMap<String, Object>();
        compiledTemplate.evaluate(new StringWriter(), data, Locale.US);
    }

}
