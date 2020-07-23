package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class PropUnionSorted implements PropUnion {

  private final PropUnion delegate;

  PropUnionSorted(PropUnion delegate) {
    this.delegate = delegate;
  }

  @Override
  public String name() {
    return delegate.name();
  }

  @Override
  public void share(BiConsumer<String, Stream<Prop>> maker) {
    delegate.share(
        (name, p) -> {
          maker.accept(name, p.sorted());
        });
  }
}
