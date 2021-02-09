package wang.sunnly.modules.gateway.annotation;

import org.springframework.context.annotation.Import;
import wang.sunnly.modules.gateway.config.RouteConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableGatewayCors
 *
 * @author Sunnly
 * @since 2019/8/2 11:58
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({RouteConfiguration.class})
public @interface EnableGateWayCors {
}
