package org.hbjs.hrsapi;

import com.hbjs.hrsapi.HRSApplication;
import com.hbjs.hrsservice.service.RegionService;
import com.hbjs.hrsservice.service.StaffService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = HRSApplication.class)
class HRSApplicationTests {

    @Autowired
    private RegionService regionService;

    @Autowired
    private StaffService staffService;

    @Test
    void contextLoads() {
    }

    @Test
    void testRegion() {
        regionService.refreshRegion();
    }

    @Test
    void testPrintStaff() {
        staffService.printStaff(1L);
    }

}
