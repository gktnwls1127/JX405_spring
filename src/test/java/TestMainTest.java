import org.junit.jupiter.api.Test;
import service.TestService;

import static org.junit.jupiter.api.Assertions.*;

class TestMainTest {

    @Test
    void testNotNull() {
        String temp = "ok";
        assertNotNull(TestMain.testNotNull(temp));
    }

    @Test
    void testService(){
        TestService service = new TestService();
        assertFalse(service.isTrue("No"));
        assertTrue(service.isTrue("yes"));
    }
}