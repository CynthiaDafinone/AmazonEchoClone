import java.io.*;
import java.net.URLEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class ComputationalTest {
    
    public ComputationalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testSolve() throws IOException{
        Computational c = new Computational();
        String resp = c.solve("What is the melting point of silver?");
        //resp will be a JSON file
        assertNotNull("resp should not be null", resp);        
        }
    
    @Test
    public void testSolve2() throws IOException{
        Computational c = new Computational();
        String resp = c.solve("asdfghjkl;");
        if (resp.contains("success=\'false\'")){
            assertNotNull("resp shouldn't be null", resp);
        }
    }

    @Test
    public void testUrlEncode(){
        Computational c = new Computational();
        String question = "What is the melting point of silver?";
        String resp = c.urlEncode(question);
        assertEquals("What+is+the+melting+point+of+silver%3F", resp);
    }
    
    @Test
    public void testGetAnswer(){
        Computational c = new Computational();
        String question = "What is the melting point of silver?";
        String resp = c.getAnswer(question);
        assertEquals("961.78 C  (degrees Celsius)", resp);
        
    }
}
    

