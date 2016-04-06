import java.io.*;
import java.io.IOException;
import java.net.*;

public class SimpleHttpProxyClient {
    public static void main (String[] agrs) {
      try {
        String proxyHost = "127.0.0.1";
        int proxyPort = 808;
        SocketAddress proxyAddr = new InetSocketAddress (proxyHost, proxyPort);
        Proxy proxy = new Proxy (Proxy.Type.HTTP, proxyAddr);
        System.out.println("Connecting...");
        URL url = new URL("http://aguskurniawan.net/book/testhalaman.html");
        HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);
        con.connect();

        System.out.println("Server Respone : ");
        System.out.println("Response Code : " + con.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String line;
        System.out.println("Data Respon : ");
        while ((line = in.readLine()) != null) {
            System.out.println(line);
        }

        in.close();
        con.disconnect();

      } catch (UnknownHostException e) {
          System.out.println(e.getMessage());
          System.exit(1);
      } catch (IOException e) {
          System.out.println(e.getMessage());
          System.exit(1);
      }
    }
}
