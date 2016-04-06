import java.io.*;
import java.net.*;

public class JavaSocketProxy {
    public static void main (String[] args) {
      try {
        String host = "aguskurniawan.net";
        int port = 80;

        String proxyHost = "127.0.0.1";
        int proxyPort = 1080;
        SocketAddress proxyAddr = new InetSocketAddress (proxyHost, proxyPort);
        Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddr);

        System.out.println("Connecting...");
        InetSocketAddress dest = new InetSocketAddress (host, port);
        Socket socket = new Socket(proxy);
        socket.connect(dest);
        System.out.println("Connected");

        System.out.println("Kirim Header");
        String path = "/book/testhalaman.html";
        BufferedWriter wr = new BufferedWriter (new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
        wr.write("GET " + path + "HTTP/1.0\r\n");
        wr.write("Host : "+ host + "\r\n");
        wr.write("\r\n");
        wr.flush();

        System.out.println("Hasil Response : ");
        BufferedReader rd = new BufferedReader (new InputStreamReader (socket.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
        }

        wr.close();
        rd.close();
        socket.close();

      } catch (UnknownHostException e) {
        System.err.println(e.getMessage());
        System.exit(1);
      } catch (IOException e) {
          System.err.println(e.getMessage());
          System.exit(1);
      }
    }
}
