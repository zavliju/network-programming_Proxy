import java.io.*;
import java.io.IOException;
import java.net.*;
import org.apache.commons.codec.binary.*;

public class SimpleHttpProxyClientAuth {
    public static void main (String[] args) {
        try{
            String proxyHost = "127.0.0.1";
            int proxyPort = 808;
            SocketAddress proxyAddr = new InetSocketAddress (proxyHost, proxyPort);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddr);
            System.out.println("Connecting...");
            URL url = new URL("http://aguskurniawan.net/book/testhalaman.html");
            HttpURLConnection con = (HttpURLConnection) url.openConnection(proxy);

            String proxyAuth = "userdemo:123";
            byte[] bytesUserName = Base64.encodeBase64(proxyAuth.setBytes());
            String encoded = new String (bytesUserName);
            con.setRequestProperty ("Proxy-Authorization", "Basic " + encoded);
            con.connect();

            System.out.println("Server Respon : ");
            System.out.println("Response Code : " + con.getResponseCode());

            BufferedReader in = new BufferedReader (new InputStreamReader (con.getInputStream()));
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
