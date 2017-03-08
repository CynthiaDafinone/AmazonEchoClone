import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for HTTPConnectWolfram. Contains one test:
 * 
 * 1. Tests that the httpConnect method works as intended 
 */
public class HttpConnectWolframTest {
    
    public HttpConnectWolframTest() {
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

     /**
     * Test to check that the httpConnect method works as intended
     */
    @Test
    public void testHttpConnect() {
        HttpConnectWolfram hcw = new HttpConnectWolfram(); // Instance to test created
        
        // Variables defined to test the instance
        String input = "test";
        final String APPID   = "J66HRA-W47APJEV7R";
        final String testMethod = "POST";
        final String testUrl    
          = ( "http://api.wolframalpha.com/v2/query"
            + "?" + "appid"  + "=" + APPID
            + "&" + "input"  + "=" + Computational.urlEncode( input )
            + "&" + "output" + "=" + "JSON"
            );
        final String[][] testHeaders
          = { { "Content-Length", "0" }
            };
        final byte[] testBody = new byte[0];
       
        byte[] resp = hcw.httpConnect(testMethod, testUrl, testHeaders, testBody);
        assertNotNull("The byte array response can't be null", resp);
    }
    
}
