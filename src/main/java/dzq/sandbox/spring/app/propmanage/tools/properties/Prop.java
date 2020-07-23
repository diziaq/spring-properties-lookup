package dzq.sandbox.spring.app.propmanage.tools.properties;

import dzq.sandbox.spring.app.propmanage.tools.properties.impl.NamedUnit;
import dzq.sandbox.spring.app.propmanage.tools.properties.impl.PropsFacade;

public interface Prop extends NamedUnit<String, String> {

  static Prop of(String name, Object value) {
    return PropsFacade.propOf(name, value);
  }
}
