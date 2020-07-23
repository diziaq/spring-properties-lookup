package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import java.util.function.BiConsumer;

final class PropObject implements Prop {

  private final String name;
  private final Object value;
  private final Class<?> valueClass;

  <T> PropObject(String name, T value, Class<?> valueClass) {
    this.name = name;
    this.value = value;
    this.valueClass = valueClass;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public void share(BiConsumer<String, String> maker) {
    maker.accept(name, value + "  ## " + valueClass.getName());
  }
}
