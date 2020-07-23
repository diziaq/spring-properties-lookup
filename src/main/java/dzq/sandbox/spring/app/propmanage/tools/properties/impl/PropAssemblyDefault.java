package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class PropAssemblyDefault implements PropAssembly {

  private final String name;
  private final Collection<PropUnion> propUnions;

  PropAssemblyDefault(String name, Collection<PropUnion> propUnions) {
    this.name = name;
    this.propUnions = propUnions;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, Stream<PropUnion>> maker) {
    maker.accept(name, propUnions.stream());
  }
}
