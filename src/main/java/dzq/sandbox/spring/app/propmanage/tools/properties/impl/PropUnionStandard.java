package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class PropUnionStandard implements PropUnion {

  private final String name;
  private final Collection<Prop> props;

  PropUnionStandard(String name, Collection<Prop> props) {
    this.name = name;
    this.props = props;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, Stream<Prop>> maker) {
    maker.accept(name, props.stream());
  }
}
