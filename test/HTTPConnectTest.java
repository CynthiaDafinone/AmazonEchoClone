import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for HTTPConnect. Contains two tests:
 * 
 * 1. Tests that the renewAccessToken method works as intended 
 * 2. Tests that the httpConnect method works as intended
 */
public class HTTPConnectTest {
    
    public HTTPConnectTest() {
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
     * Test to check that the renewAccessToken method works as intended
     */
    @Test
    public void testRenewAccessToken() throws Exception {
        HTTPConnect hc = new HTTPConnect(); // Instance to test created
        
        String resp = hc.renewAccessToken();
        assertNotNull("The renewed access token can't be null", resp);
    }

    /**
     * Test of httpConnect method, of class HTTPConnect.
     */
    @Test
    public void testHttpConnect() throws Exception {
        HTTPConnect hc = new HTTPConnect(); // Instance to test created
        
        // Variables defined to test the instance
        String KEY = "110c24ab25804509a223bac18251d6f2";
        String testMethod = "POST";
        String testUrl =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        byte[] testBody = {};
        String[][] testHeaders
                = { { "Ocp-Apim-Subscription-Key", KEY}
                , { "Content-Length"           , String.valueOf( testBody.length ) }
        };
        
        byte[] resp = hc.httpConnect(testMethod, testUrl, testHeaders, testBody);
        assertNotNull("The byte array response can't be null", resp);
    }
    
}