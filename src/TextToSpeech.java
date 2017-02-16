import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by 650007903 on 15/02/2017.
 * Much of this code is based on David Wakeling's Workshops, modified to suit our purposes.
 */
public class TextToSpeech {
    final static String LANG   = "en-US";
    final static String GENDER = "Female";
    final static String OUTPUT = "out.wav";
    final static String FORMAT = "riff-16khz-16bit-mono-pcm";

    /*
     * Synthesize speech.
     */
    private static byte[] synthesizeSpeech( String token, String text
            , String lang,  String gender
            , String format ) {
        final String method = "POST";
        final String url = "https://speech.platform.bing.com/synthesize";
        final byte[] body
                = ( "<speak version='1.0' xml:lang='en-us'>"
                + "<voice xml:lang='" + lang   + "' "
                + "xml:gender='"      + gender + "' "
                + "name='Microsoft Server Speech Text to Speech Voice"
                + " (en-US, ZiraRUS)'>"
                + text
                + "</voice></speak>" ).getBytes();
        final String[][] headers
                = { { "Content-Type"             , "application/ssml+xml"        }
                , { "Content-Length"           , String.valueOf( body.length ) }
                , { "Authorization"            , "Bearer " + token             }
                , { "X-Microsoft-OutputFormat" , format                        }
        };
        byte[] response = HTTPConnectCognitive.httpConnect( method, url, headers, body );
        return response;
    }

    /*
     * Write data to file.
     */
    private static void writeData( byte[] buffer, String name ) {
        try {
            File file = new File( name );
            FileOutputStream fos  = new FileOutputStream( file );
            DataOutputStream dos  = new DataOutputStream( fos );
            dos.write( buffer );
            dos.flush();
            dos.close();
        } catch ( Exception ex ) {
            System.out.println( ex ); System.exit( 1 ); return;
        }
    }

    /*
     * Convert text to speech.
     */
    static String convertStringToSpeech(String text) {
        final String token  = HTTPConnectCognitive.renewAccessToken();
        final byte[] speech = synthesizeSpeech( token, text, LANG, GENDER, FORMAT );
        writeData( speech, OUTPUT );
        return OUTPUT;
    }
}
