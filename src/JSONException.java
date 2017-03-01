/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsonparser;

/**
 *
 * The JSONEXception is thrown by the other classes 
 * in the event of an error, when parsing.
 */
public class JSONException extends RuntimeException {
    /**
     * Constructs JSONException with message, as
     * reason for the exception
     * 
     * @param message 
     */
    public JSONException(final String message){
        super(message);
    }
    
    /*Constuct JSONException with measage 
    and cause constructor*/
    
    /*Construct JSONException with 
       a specific message pertaining 
        the cause of the issue*/
    
}
