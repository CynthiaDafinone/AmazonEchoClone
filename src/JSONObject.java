//package jsonparser;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
//public class JSONObject {
//
//
//    private final Map <String,Object> map; //map where JSON object properties are kept
//   /**
//    *
//    * Construct empty HashMap to store key value pairs of JSONObjects
//    */
//    public JSONObject(){
//        this.map = new HashMap <String,Object>();
//    }
//
//
//    /**
//     * construct JSON object from JSONParser
//     * @param parser  a parser contanting the source string
//     */
//    public JSONObject (JSONParser parser) throws IOException{
//        this();
//        char c;
//        String key;
//
//        //checks first chachter is '{' before parsing
//        if(parser.nextClean() != '{'){
//            throw new JSONException ("A JSON Object string must begin with { ");
//        }
//        while(!parser.end()) {
//            c = parser.nextClean();
//            switch(c){
//                case 0:
//                    throw new JSONException("string must end with } ");
//                case '}':
//                    return;
//                default:
//                    key = parser.nextValue().toString();
//            }
//        }
//        //checks this is followed ':"'
//    }
//
//
//    /**
//     * Get a value from its associated key, throws exception if
//     * no value available
//     */
//    public Object get (String key) {
//    if (key == null) {
//            throw new JSONException("Null key.");
//        }
//        Object object = this.opt(key);
//        if (object == null) {
//            throw new JSONException("JSONObject not found.");
//        }
//        return object;
//    }
//
//    /**
//     * Gets an optional value from its associated key
//     * returns null if no value available
//     */
//    public Object opt(String key) {
//        Object object = this.map.get(key);
//        if (key == null || object == null){
//            return null;
//        }
//        return object;
//    }
//
//
//
//    /**
//     * Get the number of keys in the hashTable (JSON Object)
//     * @return  l the number of keys in the hashtable
//     */
//
//    public int length(){
//        int l = this.map.size();
//        return l;
//    }
//
//    /**
//     * Determines if the JSON object has a specific key
//     * @param key
//     * @return true if the key exists in the JSONObject
//     */
//    public boolean has(String key) {
//        return this.map.containsKey(key);
//    }
//
//
//    /** accumulate() similar to put but where as put overwrites
//     *a value attach to a certain key, accumalate adds this value
//        that key value pair
//    */
//
//
//
//
//
//
//
//
//}