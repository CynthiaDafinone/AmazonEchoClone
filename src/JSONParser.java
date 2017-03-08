///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package jsonparser;
//
//
//import java.io.StringReader;//StringBuilder
//import java.io.IOException;
//
//
////When parsing rather than using method and causing lossy conversion
////just use to string
//
///**
// * A JSONParser takes a string and extracts characters and string values from it.
// * It is used by the JSONObject and JSONArray constructors to parse JSON strings.
// *
// */
//public class JSONParser {
//
//    //explore string builder options.
//    //private InputStream is;
//    private StringReader parser;
//    private boolean eof; //determines wether at the last charachter in the file
//    private int index;
//   // private int  line;
//    private char previous;
//    private boolean usedPrevious;
//
//
//
//
//    public JSONParser() {
//
//        //is = new ByteArrayInputStream(source.getBytes());
//        this.parser = null;
//        this.eof = false;
//        this.usedPrevious = false;
//        this.index = 0;
//        this.previous = ' ';
//       // this.line = 1;
//
//    }
//
//
//
//    /**
//     * backs up one character in the string, allowing you to check the previous
//     * character before checking the next one.
//     *
//     */
//    public void back() throws JSONException {
//        if (this.usedPrevious || this.index <= 0){
//            throw new JSONException("Can not step back twice");
//        }else{
//            this.eof = false ;
//            this.usedPrevious = true;
//            --this.index;
//        }
//    }
//
//    /**
//     * determines if there are still characters left in the string to be parsed
//     *
//     * @return  true if chars left to be parsed
//     */
//
//    public boolean more(){
//        this.next(); //moves onto the next charachter in the buffer
//        try {
//            if (this.end()){
//
//                return false;
//            }
//        } catch (IOException ex) {
//            System.out.println(ex);
//        }
//        this.back();
//        return true;
//
//    }
//
//
//
//
//    /**
//     * checks if at the end of file and if we haven't moved back a charachter
//     * @return  stringEnd   true if reached end of file without stepping back
//     */
//    public boolean end () throws IOException{
//        /*boolean stringEnd;
//        stringEnd = this.eof && !this.usePrevious;
//        return stringEnd;*/
//        int c = 0;
//       // c = this.charachter;
//        if (c == -1 && !this.usedPrevious) {
//
//            this.eof = true;
//            return this.eof;
//
//
//        }
//        return this.eof;
//
//    }
//
//    public char next() {
//       int c; //number of characheters added to buffer
//
//
//           try {
//
//
//               c =  this.parser.read();
//               if (c <= 0 ){
//                   this.eof = true;
//                   return 0;
//
//               }
//               this.previous = (char) c; //set the value of previous to the read char
//               ++this.index;
//               return this.previous;
//
//           } catch (IOException e){
//               System.out.println(e);
//
//           }
//
//    return 0;
//
//    }
//    /**
//     * Get the next charachter in the string ignoring whitespace
//     * @return
//     * @throws JSONException
//     */
//    public char nextClean() throws JSONException {
//        char c = this.previous;
//        while (!this.eof) {
//            c = this.next();
//
//            if (c == 0 || c > ' '){
//                return c;
//            }
//
//
//        }
//        return 0;
//    }
//
//    public Object nextValue() {
//
//    }
//
//
//    public Object parse(String src){
//        this.parser = new StringReader (src);
//
//        return this.parser;
//    }
//
//    public void printBuffer () {
//        while (!this.eof){
//            char c;
//            c = this.nextClean();
//            System.out.print(c);
//        }
//
//    }
//
//}
