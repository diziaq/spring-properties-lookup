package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import java.util.function.BiConsumer;

final class PropNameOnly implements Prop {

  private final String name;

  private static final String EMPTY_VALUE = "<empty>";

  PropNameOnly(String name) {
    this.name = name;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, String> maker) {
    maker.accept(name, EMPTY_VALUE);
  }
}
