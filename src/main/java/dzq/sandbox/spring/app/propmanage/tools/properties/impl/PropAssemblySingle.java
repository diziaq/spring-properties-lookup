package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class PropAssemblySingle implements PropAssembly {

  private final String name;
  private final PropUnion propUnion;

  PropAssemblySingle(String name, PropUnion propUnion) {
    this.name = name;
    this.propUnion = propUnion;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, Stream<PropUnion>> maker) {
    maker.accept(name, Stream.of(propUnion));
  }
}
