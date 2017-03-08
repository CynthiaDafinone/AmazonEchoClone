import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.URLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.lang.Object;
import java.util.regex.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
    byte[] response = HTTPConnect.httpConnect( method, url, headers, body );
    String xml = new String( response );
    return xml;
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
            if (JSONTemp.getValue(json, "success").equals("true")) {
                int index = json.indexOf("\"id\" : \"Result\"");

                String result = JSONTemp.getValue(json, "plaintext", index);
                if (result.contains("}")) {
                    result = result.substring(0, result.indexOf("}"));
                }
                if (result.contains("]")) {
                    result = result.substring(0, result.indexOf("]"));
                }

                result = result.replaceAll("\"", "").replaceAll("]", "").replaceAll("}", "");
                return result;

            } else {
                //TODO: Not a question/wolfram couldn't answer
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}