package dzq.sandbox.spring.app.propmanage.internal.envproperties;

import dzq.sandbox.spring.app.propmanage.internal.envproperties.descr.Description;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public final class EnvPropertiesHandler {

  private final EnvPropertiesDiscoveryService service;

  EnvPropertiesHandler(EnvPropertiesDiscoveryService service) {
    this.service = service;
  }

  public Mono<ServerResponse> propertiesFromConfig(ServerRequest request) {
    return pick(service::getConfigProperties);
  }

  public Mono<ServerResponse> propertiesAll(ServerRequest request) {
    return pick(service::getActiveProperties);
  }

  public Mono<ServerResponse> propertiesPerOrigin(ServerRequest request) {
    return pick(service::getDeclaredProperties);
  }

  private Mono<ServerResponse> pick(Supplier<PropAssembly> assemblySupplier) {
    var describe = Description.asMap(assemblySupplier.get()).describe();

    return ServerResponse.ok().bodyValue(describe);
  }
}
