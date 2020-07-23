package dzq.sandbox.spring.app.propmanage.internal.envproperties;

import dzq.sandbox.spring.app.propmanage.internal.envproperties.descr.Description;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
final class EnvPropertiesLogOnStartup {

  private final EnvPropertiesDiscoveryService propertiesDiscovery;

  private static final Logger LOG = LoggerFactory.getLogger("WATCH");

  EnvPropertiesLogOnStartup(EnvPropertiesDiscoveryService propertiesDiscovery) {
    this.propertiesDiscovery = propertiesDiscovery;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReady() {
    log(propertiesDiscovery.getConfigProperties());
  }

  private void log(PropAssembly assembly) {
    var desc = Description.asString(assembly).describe();

    LOG.info(desc);
  }
}
