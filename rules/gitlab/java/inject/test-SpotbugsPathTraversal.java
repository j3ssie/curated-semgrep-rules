// License: LGPL-3.0 License (c) find-sec-bugs
package inject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpotbugsPathTraversal extends HttpServlet {

    @GetMapping("/somepath")
    public String get_image(Model model, @RequestParam String input) {
        String input_file_name = "somedir/"+input;
        
        InputStream staticFile = getClass().getClassLoader().getResourceAsStream("static_file.xml"); // should not match

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        URL loaderInput = getClass().getClassLoader().getResource(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        
        InputStream classStream = getClass().getResourceAsStream(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        URL resourceInput = getClass().getResource(input_file_name); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        // ... read file ...
        return "something";
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input");
        new File(input + "/abs/path"); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    protected void danger2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input1 = req.getParameter("input1");
        new File(input1); // BAD
    }

    protected void danger3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, URISyntaxException {
        String input = req.getParameter("test");
        new File(input);
        new File("test/" + input, "misc.jpg"); // BAD, DETECTS: PT_RELATIVE_PATH_TRAVERSAL
        new RandomAccessFile(input, "r"); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        new File(new URI(input)); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL

        new FileReader(input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        new FileInputStream(input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL

        // false positive test
        new RandomAccessFile("safe", input);
        new FileWriter("safe".toUpperCase());
        new File(new URI("safe"));

        File.createTempFile(input, "safe"); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        File.createTempFile("safe", input); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
        File.createTempFile("safe", input, new File("safeDir")); // BAD, DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    }

    // nio path traversal
    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void loadFile(HttpServletRequest req, HttpServletResponse resp) {
        String path = req.getParameter("test");
        Paths.get(path);
        Paths.get(path,"foo");
        Paths.get(path,"foo", "bar");
        Paths.get("foo", path);
        Paths.get("foo", "bar", path);

        Paths.get("foo");
        Paths.get("foo","bar");
        Paths.get("foo","bar", "allsafe");

    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void tempDir(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("test");
        Path p = Paths.get("/");

        Files.createTempFile(p,input,"");
        Files.createTempFile(p,"",input);
        Files.createTempFile(input,"");
        Files.createTempFile("", input);

        Files.createTempDirectory(p,input);
        Files.createTempDirectory(input);
    }

    // DETECTS: PT_ABSOLUTE_PATH_TRAVERSAL
    public void writer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String input = req.getParameter("test");
        new FileWriter(input);
        new FileWriter(input, true);
        new FileOutputStream(input);
        new FileOutputStream(input, true);
    }


}
