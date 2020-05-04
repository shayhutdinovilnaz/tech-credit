package com.technical.credit.obligationservice.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "displaynameconfig")
@Getter
@Setter
public class YAMLConfig {
    private Map<String, String> isocodes;
    private Map<String, String> patterns;
}
