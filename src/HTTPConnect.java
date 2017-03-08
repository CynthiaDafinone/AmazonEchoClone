import javax.net.ssl.SSLProtocolException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.HttpURLConnection;

/**
 * Much of this code is based on David Wakeling's Workshops, modified to suit our purposes.
 */
class HTTPConnect {
    private final static int TIMEOUT  = 5000; // Timeout in ms
    private final static int BUFFSIZE = 4096; // Buffer response size

    private final static String KEY = "110c24ab25804509a223bac18251d6f2";
    // An extra access key to use, should we need it
    // private final static String KEY = "ea072146f15446ed89d1c9f2498c0d87";


    /**
     * Method to renew an access token to the cognitive services
     * @return  the access token
     */
    static String renewAccessToken() {
        final String method = "POST";
        final String url =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] body = {};
        final String[][] headers
                = { { "Ocp-Apim-Subscription-Key", KEY}
                , { "Content-Length"           , String.valueOf( body.length ) }
        };
        byte[] response = HTTPConnect.httpConnect( method, url, headers, body );
        return new String( response );
    }

    /**
     * Method creates a HTTP connection, sends and receives data
     * @param m the method of sending the request
     * @param u the URL to send the request to
     * @param h the headers to attach to the request
     * @param body the body of the request
     * @return a byte array containing the response
     */
    static byte[] httpConnect(String m, String u, String[][] h, byte[] body) {
        try {
            URL url = new URL(u);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(m);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(TIMEOUT);
            conn.setReadTimeout(TIMEOUT);
            for (String[] aH : h) {
                conn.setRequestProperty(aH[0], aH[1]);
            }
            conn.connect();



            // Send the outgoing data
            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
            dos.write(body);
            dos.flush();
            dos.close();


            // Receive the incoming data
            DataInputStream dis = new DataInputStream(conn.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[BUFFSIZE];
            while (true) {
                int n = dis.read(buffer);
                if (n > 0) {
                    bos.write(buffer, 0, n);
                } else {
                    break;
                }
            }
            byte[] response = bos.toByteArray();

            dis.close();
            conn.disconnect();
            return response;
        } catch (SocketTimeoutException e) {
            // Placeholder - we may want to do something specific with timeouts (retry) later
            e.printStackTrace();
            System.out.println(e.getMessage());
            for (String s[] : h) {
                System.out.println(s[0] + " : " + s[1]);
            }
            System.exit(1);
            return null;
        } catch (SSLProtocolException e) {
            System.out.println("There was an error connecting to the server - the SSL certificate was invalid");
            System.exit(1);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
