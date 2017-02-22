import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * The test class for HTTPConnectWolfram. Contains one test:
 * 
 * 1. Tests that the httpConnect method works as intended **to finish**
 * 
 * @author 650051048 17/02/2017
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
     * 
     * @author 650051048 17/02/2017
     */
    @Test
    public void testHttpConnect() {
        System.out.println("httpConnect");
        String method = "";
        String url = "";
        String[][] headers = null;
        byte[] body = null;
        byte[] expResult = null;
        byte[] result = HttpConnectWolfram.httpConnect(method, url, headers, body);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
