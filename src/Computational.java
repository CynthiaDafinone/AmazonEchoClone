import java.io.File;
import java.net.URLEncoder;
import java.io.*;


/*
 *
 */
class Computational {
    // APPID for the WolframAlpha servers
    private final static String APPID = "J66HRA-W47APJEV7R";

    /**
     * Method to solve the given question using WolframAlpha
     * @param question the question to be answered
     * @return a string containing the result
     * @throws IOException should there be an error with the connection to the server/answer
     */
    static String solve(String question) throws IOException {
        final String method = "POST";
        final String url
                = ("http://api.wolframalpha.com/v2/query"
                + "?" + "appid" + "=" + APPID
                + "&" + "input" + "=" + urlEncode(question)
                + "&" + "output" + "=" + "JSON"
        );
        final String[][] headers
                = {{"Content-Length", "0"}
        };
        final byte[] body = new byte[0];
        try {
            byte[] response = HTTPConnect.httpConnect(method, url, headers, body);
            return new String(response);
        } catch (IOException e) {
            System.out.println("Connection to the server timed out - invalid question?");
            return null;
        }
    }


    /**
     * Method to encode the given string in a web-compatible format
     * @param s the string to be encoded
     * @return a string in web-compatible format
     */
    static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    /**
     * Method to get the answer to the given question
     * @param question the question to be answered
     * @return the answer to the question
     */
    static String getAnswer(String question) {
        try {
            // Gather a resulting JSON string from the Wolfram servers
            String json = solve(question);
            if (json == null) {
                // If there's no string, return the answer as null (no connection/timeout)
                return null;
            }

            // For now we are just finding the answer using manual string operations, this may be replaced later
            // once Cynthia finishes the JSON parser
            if (json.contains("\"success\" : true,")) {
                int searchIndex = json.indexOf("\"id\" : \"Result\",");
                searchIndex = json.indexOf("\"img\" : {", searchIndex);
                searchIndex = json.indexOf("\"title\" : \"", searchIndex) + 11;
                int endIndex = json.indexOf("\",", searchIndex);
                String answer = json.substring(searchIndex, endIndex);

                // Removing special characters
                answer = answer.replaceAll("\\\\n", " ");
                answer = answer.replaceAll("\\\\r", " ");
                answer = answer.replaceAll("\\\\t", " ");                
                answer = answer.replaceAll("[^A-Za-z0-9 .',&:+()|-]", "");
                return answer;

            } else {
                // If WolframAlpha returned as a failure
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }
}