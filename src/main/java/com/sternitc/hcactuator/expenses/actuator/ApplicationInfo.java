package com.sternitc.hcactuator.expenses.actuator;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationInfo {

    private String applicationName;
    private String version;
    private String artifactId;
}
