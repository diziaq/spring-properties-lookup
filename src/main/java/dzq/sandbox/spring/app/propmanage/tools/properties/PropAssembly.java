package dzq.sandbox.spring.app.propmanage.tools.properties;

import dzq.sandbox.spring.app.propmanage.tools.properties.impl.NamedUnit;
import dzq.sandbox.spring.app.propmanage.tools.properties.impl.PropsFacade;
import java.util.Collection;
import java.util.stream.Stream;

public interface PropAssembly extends NamedUnit<String, Stream<PropUnion>> {

  static PropAssembly fromUnions(String title, Collection<PropUnion> propUnions) {
    return PropsFacade.assemblyOf(title, propUnions);
  }
}
