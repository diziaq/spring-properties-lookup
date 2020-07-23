package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import java.util.function.BiConsumer;

final class PropNormal implements Prop {

  private final String name;
  private final String value;

  PropNormal(String name, String value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, String> maker) {
    maker.accept(name, value);
  }
}
