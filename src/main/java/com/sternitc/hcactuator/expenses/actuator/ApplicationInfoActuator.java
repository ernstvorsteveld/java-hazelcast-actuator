package com.sternitc.hcactuator.expenses.actuator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@Endpoint(id = "app-info")
public class ApplicationInfoActuator {
    @Value("${application_version}")
    private String versionId;

    @Value("${application_name}")
    private String applicationName;

    @Value("${application_group}")
    private String artifactId;

    @ReadOperation(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApplicationInfo> getApplicationInfo() {
        return Mono.just(ApplicationInfo.builder()
                .applicationName(applicationName)
                .version(versionId)
                .artifactId(artifactId).build());
    }
}
