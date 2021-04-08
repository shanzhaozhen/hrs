package org.hbjs.hrsapi;

import com.hbjs.hrsapi.HRSApplication;
import com.hbjs.hrsservice.service.RegionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest(classes = HRSApplication.class)
class HRSApplicationTests {

    @Autowired
    private RegionService regionService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegion() {
        regionService.refreshRegion();
    }

}
