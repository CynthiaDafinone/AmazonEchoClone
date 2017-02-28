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
    static String solve( String input ) { 
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
    byte[] response   = HttpConnectWolfram.httpConnect( method, url, headers, body );
    String xml        = new String( response );
    return xml;
  } 

  /*
   * URL encode string.
   */ 
  private static String urlEncode( String s ) {
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

  /*
   * Solve problem giving solution.
   */
  static String getAnswer(String question) { 
    try {
        String answer = new String ("");  
        final String json = solve( question );
        
        //Pods->title=result->subpods->title

        JSONParser parser = new JSONParser();
        JSONObject main = (JSONObject) parser.parse(json);
        JSONObject result = (JSONObject) main.get("queryresult");
        if (!((boolean) result.get("success"))) {
            return null;
        }
        JSONArray pods = (JSONArray) result.get("pods");
        Iterator iterator = pods.iterator();
        
        // Horrible json - parsed using json-simple
        while(iterator.hasNext()) {
            JSONObject obj = (JSONObject) iterator.next();
            if (obj.get("title").equals("Result")) {
                JSONArray subpods = (JSONArray) obj.get("subpods");
                Iterator podIter = subpods.iterator();
                while (podIter.hasNext()) {
                    JSONObject item = (JSONObject) podIter.next();
                    if (item.get("title").equals("")) {
                        return (String) item.get("plaintext");
                    }
                }
            }
        }  
    } catch (Exception e) {
        System.out.println("Something went wrong parsing - computational");
        System.exit(1);
        return null;
    }
    return null;
  }
}