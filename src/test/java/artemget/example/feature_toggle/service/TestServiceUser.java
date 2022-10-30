package artemget.example.feature_toggle.service;

import artemget.example.feature_toggle.exception.FeatureDisabledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class TestServiceUser {

    @Autowired
    private TestService testService;

    @Test
    public void testUserFeature_shouldPass_whenUserFeatureEnabled() {
        Assertions.assertDoesNotThrow(() -> testService.testUserFeature());
    }

    @Test
    public void testAdminFeature_shouldPass_whenAdminFeatureDisabled() {
        Assertions.assertThrows(FeatureDisabledException.class,
                () -> testService.testAdminFeature());
    }
}