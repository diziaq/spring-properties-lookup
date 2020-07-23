package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class PropUnionUnrecognized implements PropUnion {

  private final String name;
  private final String additionalInfo;

  public PropUnionUnrecognized(String name, String additionalInfo) {
    this.name = name;
    this.additionalInfo = additionalInfo;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, Stream<Prop>> maker) {
    maker.accept(name + " :: unrecognized [" + additionalInfo + "]", Stream.empty());
  }
}
