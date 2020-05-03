package co.com.santander.adapters.common.utilities;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JsonUtilitiesImplTest {
    private JsonUtilities jsonUtilities;

    @Before
    public void init() {
        jsonUtilities = new JsonUtilitiesImpl();
    }

    @Test
    public void testGetValueForGivenKeySuccess() {
        String result = jsonUtilities.getValueForGivenKey("RespValidacion", "resultado", MockResponse.MockResponseSuccess).get();
        Assert.assertNotNull(result);
    }
}