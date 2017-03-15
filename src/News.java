import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/*
 * Powered by News API
 */

public class News {
    static int newsIndex = 0;
    static int endNewsIndex = 0;
    public static void main(String[] args) throws IOException{
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
        
        
        //Currently only outputs the top news story (only one)
        if (news.contains("\"status") && news.contains("\"ok")){
            newsIndex = news.indexOf("\"title")+6;
            endNewsIndex = news.indexOf("\",", newsIndex);
            String headline = news.substring(newsIndex, endNewsIndex);
            
            headline=headline.replaceAll("[^A-Za-z0-9 .',&+()|-]", "");
            System.out.println(headline);
            AudioOutput.playSound(TextToSpeech.convertStringToSpeech(headline));
        }
        
        //Read from "title" up until "description"
        else{
            System.out.println("Nope");
                
            }
        
        
    }
    
        
}