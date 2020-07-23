package dzq.sandbox.spring.app.propmanage.internal;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import dzq.sandbox.spring.app.propmanage.internal.envproperties.EnvPropertiesHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
class AppRoutes {

  @Bean
  RouterFunction<ServerResponse> internalEnvironmentProperties(EnvPropertiesHandler handler) {
    return nest(
        path("internal/properties"),
        route(GET(""), handler::propertiesFromConfig)
            .andRoute(GET("config"), handler::propertiesFromConfig)
            .andRoute(GET("all"), handler::propertiesAll)
            .andRoute(GET("origin"), handler::propertiesPerOrigin));
  }
}
