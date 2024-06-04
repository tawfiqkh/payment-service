package ma.enset.iibdcc.paymentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("file-path")
public record FileConfig(String path) {}
