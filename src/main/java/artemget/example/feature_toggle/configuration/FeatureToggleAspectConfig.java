package artemget.example.feature_toggle.configuration;

import artemget.example.feature_toggle.feature.EnabledFeatures;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
@EnableAspectJAutoProxy
public class FeatureToggleAspectConfig {
    private static final ObjectMapper jsonMapper = new ObjectMapper();
    @Value(value = "${feature.file}")
    private String featureFilePath;

    @Bean
    public EnabledFeatures configureFeatureTree() throws IOException {
        return jsonMapper.readValue(new File(featureFilePath), EnabledFeatures.class);
    }
}
