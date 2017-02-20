import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.UUID;


/**
 * Created by 650007903 on 15/02/2017.
 * Much of this code is based on David Wakeling's Workshops, modified to suit our purposes.
 */
class SpeechToText {
    private final static String LANG  = "en-US";

    /**
     * Method to convert speech audio bytes to text
     * @param token the access token to use for Microsoft Cognitive serveices
     * @param body the audio bytes
     * @return the JSON string returned from the server
     */
    private static String recognizeSpeech( String token, byte[] body ) {
        final String method = "POST";
        final String url
                = ( "https://speech.platform.bing.com/recognize"
                + "?" + "version"    + "=" + "3.0"
                + "&" + "format"     + "=" + "json"
                + "&" + "device.os"  + "=" + "wp7"
                + "&" + "scenarios"  + "=" + "smd"
                + "&" + "locale"     + "=" + LANG
                + "&" + "appid"      + "=" + "D4D52672-91D7-4C74-8AD8-42B1D98141A5"
                + "&" + "instanceid" + "=" + UUID.randomUUID().toString()
                + "&" + "requestid"  + "=" + UUID.randomUUID().toString()
        );
        final String[][] headers
                = { { "Content-Type"   , "audio/wav; samplerate=16000"  }
                , { "Content-Length" , String.valueOf( body.length )  }
                , { "Authorization"  , "Bearer " + token              }
        };        byte[] response = HTTPConnectCognitive.httpConnect( method, url, headers, body );
        return new String( response );
    }

    /**
     * Method to read audio data from a file into a byte array
     * @param name the filename in which to load data from
     * @return the byte array containing audio information
     */
    private static byte[] readData( String name ) {
        try {
            File            file = new File( name );
            FileInputStream fis  = new FileInputStream( file );
            DataInputStream dis  = new DataInputStream( fis );
            byte[] buffer = new byte[ (int) file.length() ];
            dis.readFully( buffer );
            dis.close();
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Method to get a String mentioned within an audio file
     * @param filename the file in which to obtain data from
     * @return the string returned from the server
     */
    static String getTextFromAudio(String filename) {
        try {
            final String token = HTTPConnectCognitive.renewAccessToken();
            final byte[] speech = readData(filename);
            String JSONString = recognizeSpeech(token, speech);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(JSONString);

            JSONObject header = (JSONObject) obj.get("header");

            if (((String) header.get("status")).equals("success")) {
                return (String) header.get("name");
            } else {
                return null; // Must be checked for by calling function
            }

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
