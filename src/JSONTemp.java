import java.io.*;

/**
 * Created by User on 08/03/2017.
 */
public class JSONTemp {

    static String getValue(String JSON, String key) {

        int start = JSON.indexOf("\"" + key + "\"") + key.length() + 5;
        // + 6 to account for both "'s and the : plus spaces
        int end = JSON.indexOf(",", start);
        return JSON.substring(start, end);
    }

    static String getValue(String JSON, String key, int from) {

        int start = JSON.indexOf("\"" + key + "\"", from) + key.length() + 5;
        // + 6 to account for both "'s and the : plus spaces
        int end = JSON.indexOf(",", start);
        return JSON.substring(start, end);
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream(new File("js.JSON"));
            BufferedInputStream bis = new BufferedInputStream(fis);
            byte[] b = new byte[32*1024];
            bis.read(b);
            String json = new String(b);
            //System.out.println(json);

            System.out.println(getValue(json, "success"));

            int index = json.indexOf("\"id\" : \"Result\"");

            String result = getValue(json, "plaintext", index);
            if (result.contains("}")) {
                result = result.substring(0, result.indexOf("}"));
            }
            if (result.contains("]")) {
                result = result.substring(0, result.indexOf("]"));
            }

            result = result.replaceAll("\"", "").replaceAll("]", "").replaceAll("}", "");

            System.out.println(result);





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
