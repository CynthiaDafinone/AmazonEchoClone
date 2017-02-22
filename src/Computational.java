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
        
/*
 *
 */
public class Computational {
  final static String PROBLEM = "What is the square root of 4165?";
  //Pods->title=result->subpods->title
  final static String APPID   = "J66HRA-W47APJEV7R";

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
  public static void main( String[] argv ) throws FileNotFoundException, IOException {
    
    String answer = new String ("");  
      
    final String solution = solve( PROBLEM );
    readJsonFile();
    System.out.println( solution ); 
    
    //Everything below will be changed
    //A way of reading the 53rd line in the file we output with readJsonFile()
    FileInputStream fs = new FileInputStream("output.txt");
    BufferedReader br = new BufferedReader(new InputStreamReader(fs));
    for(int i=0; i<52; ++i)
        br.readLine();
    String line53 = br.readLine();
    
    //Create a new file for just our answer
    File file = new File("answer.txt"); 
    FileOutputStream fos = new FileOutputStream(file);
    PrintStream ps = new PrintStream(fos);
    System.setOut(ps);
    
    
    line53 = line53.replaceAll("title","").replaceAll(":","")
            .replaceAll("\"","").replaceAll(",","").replaceAll("alt","");
    answer = line53.trim();
    System.out.printf(answer);
    }
    
}