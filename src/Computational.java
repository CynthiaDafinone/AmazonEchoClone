import java.io.File;
import java.net.URLEncoder;
import java.io.*;


/*
 *
 */
public class Computational {
  final static String APPID   = "J66HRA-W47APJEV7R";

  /*
   * Solve.
   */
    static String solve( String input ) throws IOException {
    final String method = "POST";
    final String url
      = ( "http://api.wolframalpha.com/v2/query"
        + "?" + "appid"  + "=" + APPID
        + "&" + "input"  + "=" + urlEncode( input )
        + "&" + "output" + "=" + "JSON"
        );
    final String[][] headers
      = { { "Content-Length", "0" }
        };
    final byte[] body = new byte[0];
    try {
        byte[] response = HTTPConnect.httpConnect(method, url, headers, body);
        String xml = new String(response);
        return xml;
    } catch (IOException e) {
        System.out.println("Connection to the server timed out - invalid question?");
        return null;
    }
  }


  /*
   * URL encode string.
   */
  static String urlEncode( String s ) {
    try {
      return URLEncoder.encode( s, "utf-8" );
    } catch ( Exception ex ) {
      System.out.println( ex ); System.exit( 1 ); return null;
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
            String json = solve(question);
            if (json == null) {
                return null;
            }
            if (json.contains("\"success\" : true,")) {
                int searchIndex = json.indexOf("\"id\" : \"Result\",");
                searchIndex = json.indexOf("\"img\" : {", searchIndex);
                searchIndex = json.indexOf("\"title\" : \"", searchIndex) + 11;
                int endIndex = json.indexOf("\",", searchIndex);
                String answer = json.substring(searchIndex, endIndex);

                answer = answer.replaceAll("[^A-Za-z0-9 .',&+()|-]", "");
                return answer;

            } else {
                //TODO: Not a question/wolfram couldn't answer
                return null;
            }

        } catch (IOException e) {
            return null;
        }
    }
}