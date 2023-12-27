// License: LGPL-3.0 License (c) find-sec-bugs
// scaffold: dependencies=com.amazonaws.aws-java-sdk-simpledb@1.12.187
package inject;
import java.io.IOException;
import java.util.Arrays;

public class CommandInjection {

     public void danger(String cmd) throws IOException {
        Runtime r = Runtime.getRuntime();
        String[] cmds = new String[] {
            "/bin/sh",
            "-c",
            cmd
        };

        r.exec(cmd);
        r.exec(new String[]{"test"});
        r.exec(new String[]{"bash", cmd},new String[]{});
        r.exec(new String[]{"bash"+ cmd},new String[]{});

        String tainted = "bash"+ cmd + "test";
        r.exec(tainted);
        r.exec(tainted + "custom");
        r.exec(new String[]{"bash", tainted},new String[]{});
        r.exec(new String[]{"/bin/sh", "-c" + tainted},new String[]{});

        r.exec(cmds);
        r.exec(cmds,new String[]{});
        r.exec(cmds,new String[]{"test"});
    }

    public void danger2(String cmd) {
        ProcessBuilder b = new ProcessBuilder();
        b.command(cmd);
        b.command("test");
        b.command(Arrays.asList("/bin/sh", "-c", cmd));

        String tainted = "test2"+ cmd + "test";
        b.command("test2"+ cmd + "test");
        b.command(tainted);
        b.command(Arrays.asList("/bin/sh", "-c", tainted));
    }
}
