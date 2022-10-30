package artemget.example.feature_toggle.feature;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;
import java.util.stream.Collectors;

public class EnabledFeatures {
    private final Set<FeatureType> featureTypeSet;

    @JsonCreator
    public EnabledFeatures(
            @JsonProperty("features_enabled")
                    Set<String> enabledFeatures) {
        this.featureTypeSet = ejectFeatures(enabledFeatures);
    }

    public boolean checkDisabled(Set<FeatureType> featureTypeSet) {
        return this.featureTypeSet.stream()
                .filter(featureTypeSet::contains)
                .collect(Collectors.toSet())
                .isEmpty();
    }

    public boolean checkDisabled(FeatureType featureType) {
        return checkDisabled(Set.of(featureType));
    }

    private Set<FeatureType> ejectFeatures(Set<String> featureList) {
        return featureList.stream()
                .map(FeatureType::valueOf)
                .collect(Collectors.toSet());
    }
}
