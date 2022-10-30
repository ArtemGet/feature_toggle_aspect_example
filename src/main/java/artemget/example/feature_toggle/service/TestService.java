package artemget.example.feature_toggle.service;

import artemget.example.feature_toggle.aspect.EnableFeature;
import artemget.example.feature_toggle.feature.FeatureType;
import org.springframework.stereotype.Service;

/**
 * Test class for EnableFeature test
 */
@Service
@EnableFeature(value = FeatureType.ADMIN)
public class TestService {

    @EnableFeature(value = FeatureType.USER)
    public void testUserFeature() {
        System.out.println("user feature");
    }

    public void testAdminFeature() {
        System.out.println("admin feature");
    }
}
