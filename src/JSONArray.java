///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package jsonparser;
//
//import java.util.ArrayList;
//
///**
// *
// * @author Cynthia
// */
//public abstract class JSONArray implements Iterable<Object> {
//    private final ArrayList<Object> jsonarray ;
//
//    public JSONArray(){
//    this.jsonarray = new ArrayList<Object>();
//    }
//
//
//
//    public JSONArray(JSONParser parser){
//        this();
//        if(parser.nextClean() != '['){
//            throw new JSONException ("A JSON Object string must begin with { ");
//        }
//        if(parser.nextClean() != ']'){
//            while(!parser.end()){
//
//            }
//        }
//    }
//
//    public int length() {
//        return this.jsonarray.size();
//    }
//
//    @Override
//    public Iterator<Object> iterator() {
//        return jsonarray.iterator();
//    }
//
//}
