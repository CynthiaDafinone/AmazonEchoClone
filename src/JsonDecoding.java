import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;


class JsonDecoding {

	static String decode (String str){ //which type of object is this
		JSONParser parser = new JSONParser();
		try{
			JSONArray obj = (JSONArray) parser.parse(str);

			Iterator iterator = obj.iterator(); //generics?
			JSONObject a;
			String output = "";
			while (iterator.hasNext()){
				a = (JSONObject)iterator.next();
                                output += a.toString();
			}
                        return output;
		}catch(ParseException pe){
                    System.out.println("position: " + pe.getPosition());
                    System.out.println(pe);
                    return null;
		}
        }
}