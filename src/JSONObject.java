package jsonparser;

import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class JSONObject {
    
    
    private final Map <String,Object> map; //map where JSON object properties are kept
   /**
    * 
    * Construct empty HashMap to store key value pairs of JSONObjects
    */ 
    public JSONObject(){
        this.map = new HashMap <String,Object>();
    }
    
    //JSON object from subset of another object
    public JSONObject(JSONObject jo, String[] names){
        //this(); C
        for (int i = 0; i < names.length; i += 1){
            
                this.putOnce(names[i],jo.opt(names[i]));
          
        }
    }
    //JSON object from Json tokener ?
    
    
    
    //JSON from source JSON text sting ^^
    
    //get 
    /**
     * retrieve an optional value associated with a key 
     * @param key 
     * @return An object value or null
     */
    public Object getValue(String key){
        if (key == null){
            return null;
        }else{
        
            return this.map.get(key);
        }
    }
    //length 
    //opt STring 1126
    
    /**
     * Get the number of keys in the hashTable (JSON Object)      
     * @return  l the number of keys in the hashtable 
     */
    
    public int length(){
        int l = this.map.size();
        return l;
    }
    
    
    /** accumulate() similar to put but where as put overwrites
     *a value attach to a certain key, accumalate adds this value 
        that key value pair
    */
    
    /**append() adds values to an array under a given key, if the key
     * is not in the array that key is added to the JSONOBject with its
     * associated value, if the key is already in the jSONArray then the 
     * given value is appended 
    */
    
    /**doubleToString converts a double value into a string if the string is
     * infinite then null is returned
    */
     
    
    
    
    
}
