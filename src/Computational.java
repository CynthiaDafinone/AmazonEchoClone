import java.io.File;
import java.net.URLEncoder;
import java.io.*;

/*
 *
 */
public class Computational {
    final static String APPID = "J66HRA-W47APJEV7R";

    static String solve(String q) throws IOException {
        final String method = "POST";
        final String url = ("http://api.wolframalpha.com/v1/spoken?appid=" + APPID + "&i=" + urlEncode(q));
        String[][] headers = {{"Content-Length", "0"}};
        final byte[] body = new byte[0];
        byte[] response = HTTPConnect.httpConnect(method, url, headers, body);
        String answer = new String(response);
        if (answer.startsWith("The answer is ")) {
            answer = answer.substring(14);
        }
        return answer;
    }

    /*
     * URL encode string.
     */
    static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch (Exception ex) {
            System.out.println(ex);
            System.exit(1);
            return null;
        }
    }

    /*
    * Takes cmd output and makes it more readable
    */
    public static void readJsonFile() throws FileNotFoundException {
        File file = new File("output.txt");
        FileOutputStream fos = new FileOutputStream(file);
        PrintStream ps = new PrintStream(fos);
        System.setOut(ps);
    }

    static String getAnswer(String question) {
        try {
            return solve(question);
        } catch (IOException e) {
            if (e.getMessage().contains("Server returned HTTP response code: 501 for URL:")) {
                // TODO: Play "Sorry, I don't have an answer for that question"
            }
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}