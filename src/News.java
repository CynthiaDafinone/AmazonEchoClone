import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/*
 * Powered by News API
 */

public class News {
    //static int endNewsIndex = 0;
    static int i=0;    
    
    static boolean playNews(){
        try {
            URL url = new URL("https://newsapi.org/v1/articles?source=bbc-news"
                    + "&sortBy=top&apiKey=756f4949b8ba49558a3e3e8b3d420e63");
            
            URLConnection con = url.openConnection();
            InputStream is = con.getInputStream();
            
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String news = "";
            if((news=br.readLine()) !=null){
                news+=br.readLine();
            }
            
            else{
                System.out.println("News could not be retrieved");
            }
            
            
            //Currently outputs top 3 news stories
            if (news.contains("\"status") && news.contains("\"ok")){
                
                
                int index = news.indexOf("\"title");
                while (index>=0 && i<3){
                    
                    index=news.indexOf("\"title", index+i);
                    int endIndex = news.indexOf("\",", index);
                    String headline = news.substring(index+9, endIndex);
                    i+=1;
                    System.out.println(headline);

                    AudioOutput.playSound(TextToSpeech.convertStringToSpeech(headline));
                    //Make sure each news story doesn't play over another
                    Thread.sleep(66*headline.length());
                    
                    if(i==3){
                        return true;
                    }
                //return true;
                }
            return true;
            }
        } catch (MalformedURLException ex) {
            return false;
        } catch (InterruptedException ex){
            return false;
        } catch (IOException ex){
            return false;
        }
        return true;
        
    }
        
}



       
    
        
