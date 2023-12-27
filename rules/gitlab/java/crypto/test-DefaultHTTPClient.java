// License: LGPL-3.0 License (c) find-sec-bugs
package crypto;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class DefaultHTTPClient {
    public void danger() throws IOException {
        HttpClient client = new DefaultHttpClient(); 
        HttpUriRequest request = new HttpGet("https://test.com");
        client.execute(request);
    }
}
