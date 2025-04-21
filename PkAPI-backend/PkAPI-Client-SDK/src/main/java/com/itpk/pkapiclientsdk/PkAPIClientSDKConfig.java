package com.itpk.pkapiclientsdk;

import com.itpk.pkapiclientsdk.config.PkAPIClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("pkapi.client")
@Data
@ComponentScan
public class PkAPIClientSDKConfig {
    private String accessKey;
    private String secretKey;
@Bean
PkAPIClient client() {
    return new PkAPIClient(accessKey, secretKey);
}
}
