import java.io.File;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.net.URLEncoder;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Computational {
  //final static String PROBLEM = "What is the melting point of silver?";
  final static String APPID   = "J66HRA-W47APJEV7R"; //old key H46UG6-XKXYP5GH4R

  /*
   * Solve.
   */
  private static String solve( String input ) { 
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
    byte[] response   = HttpConnect.httpConnect( method, url, headers, body );
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
  * Read input from command line.
  */
  private static String readCmdInput(){
    System.out.println("Enter question: ");
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  /*
  * write the returned Json object into a text file
  */
  private static void writeFileOut( String s ){
    String file = "output.txt";
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

      bw.write(s);
      bw.close();

    } catch(IOException e) {
      e.printStackTrace();
    }
  }

  /*
  *Decode JSON output into text
  */




  /*
   * Solve problem giving solution.
   */
  public static void main( String[] argv ) {
    String s;
    s = readCmdInput();
    //System.out.println(s);
    final String solution = solve( s );
    String res = JsonDecoding.decode(solution);
    System.out.println(res);
    writeFileOut(res);
   // System.out.println( solution );

    //solution is variable holding Json
  }
}
