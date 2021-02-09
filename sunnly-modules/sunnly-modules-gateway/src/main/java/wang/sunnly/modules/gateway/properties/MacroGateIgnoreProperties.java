package wang.sunnly.modules.gateway.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * MacroGateIgnoreProperties
 *
 * @author Sunnly
 * @since 2021/1/25
 */
@Configuration
@EnableConfigurationProperties(MacroGateIgnoreProperties.class)
@ConfigurationProperties(prefix = "macro.gate.start-with")
@Data
public class MacroGateIgnoreProperties {
    private List<String> ignores;
}
