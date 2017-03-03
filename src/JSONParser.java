/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

import java.io.BufferedReader;
import java.io.IOException;

//When parsing rather than using method and causing lossy conversion 
//just use to string

/**
 * A JSONParser takes a string and extracts characters and string values from it.
 * It is used by the JSONObject and JSONArray constructors to parse JSON strings.
 * 
 */
public class JSONParser {
    
    //if null pointer exception change int values back to null
    
    private BufferedReader parser;
    private int charachter ;
    private boolean eof; //determines wether at the last charachter in the file
    private int index;
    private int  line;
    private char previous;
    private boolean usePrevious; //checks to see if we have used the previous char
    //before testing the next one
      
    

    public JSONParser(BufferedReader source) {
        
        this.parser = new BufferedReader(source);
        this.eof = false;
        this.usePrevious = false;
        this.index = 0;
        this.charachter = 0;
        this.previous = 0; 
        this.line = 1;
          
    }
    //write find  function for parser()
    
    //Buffered reader allows for parsing on charachter at a time
    
    /**
     * backs up one character in the string, allowing you to check the previous
     * character before checking the next one.
     * 
     */
    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0){
            throw new JSONException("Can not step back twice");
        }else{
            this.eof = false ;
            this.usePrevious = true;
            --this.index;
        }
    }
    
    /**
     * determines if there are still characters left in the string to be parsed
     * 
     * @return  true if chars left to be parsed
     */
    
    public boolean more(){
        this.next(); //moves onto the next charachter in the buffer
        if (this.end()){
        
           return false;
        }
        this.back();
        return true;
        
    }
            
        
    
    
    /**
     * checks if at the end of file and if we haven't moved back a charachter
     * @return  stringEnd   true if reached end of file without stepping back
     */
    public boolean end () throws IOException{
        /*boolean stringEnd;
        stringEnd = this.eof && !this.usePrevious;
        return stringEnd;*/
        int c;
        c = this.parser.read() ;
        if (c < 0 && !this.usePrevious) {
            
            this.eof = true;
            return this.eof;
            
        }
        return this.eof;
    
    }

    private void next() {
       int c; //number of characheters added to buffer
       if (this.usePrevious){
           c = this.previous;
           this.usePrevious = false;
           /*checks that the previous charachter has been used assigns 
           the value to c and resets usePrevious to false
           */
           
       } else {
           try {
               c = this.parser.read();
               
               ++this.index; 
               
           } catch (IOException e){
               System.out.println(e);
           }
       }
       
    }
    
    //nextchar
    //nextn
    //nextstring
    
    //syntax error handling
    
}
