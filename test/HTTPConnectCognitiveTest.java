import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for HTTpConnectCognitive. Contains two tests:
 * 
 * 1. Tests that the renewAccessToken method works as intended **to finish**
 * 2. Tests that the httpConnect method works as intended **to finish**
 */
public class HTTPConnectCognitiveTest {
    
    public HTTPConnectCognitiveTest() {
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
    public void testRenewAccessToken() {
        HTTPConnectCognitive hcc = new HTTPConnectCognitive();
        String resp = hcc.renewAccessToken();
        assertNotNull("The renewed access token can't be null", resp);
    }

    /**
     * Test to check that the httpConnect method works as intended
     */
    @Test
    public void testHttpConnect() {
        HTTPConnectCognitive hcc = new HTTPConnectCognitive();
        
        final String KEY = "110c24ab25804509a223bac18251d6f2";
        final String testMethod = "POST";
        final String testUrl =
                "https://api.cognitive.microsoft.com/sts/v1.0/issueToken";
        final byte[] testBody = {};
        final String[][] testHeaders
                = { { "Ocp-Apim-Subscription-Key", KEY}
                , { "Content-Length"           , String.valueOf( testBody.length ) }
        };
        
        
        byte[] resp = hcc.httpConnect(testMethod, testUrl, testHeaders, testBody);
        assertNotNull("The byte array response can't be null", resp);
        
    }
    
}
