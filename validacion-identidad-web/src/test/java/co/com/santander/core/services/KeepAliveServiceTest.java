package co.com.santander.core.services;

import co.com.santander.core.services.impl.KeepAliveServiceImpl;
import co.com.santander.ports.primary.KeepAliveService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeepAliveServiceTest {

    private KeepAliveService keepAliveService;

    @Before
    public void setUp() {
        keepAliveService = new KeepAliveServiceImpl();
    }

    @Test
    public void testKeepAliveServiceSuccess() {
        String result = keepAliveService.getAlive();
        Assert.assertNotNull(result);
    }
}
