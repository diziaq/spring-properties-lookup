package dzq.sandbox.spring.app.propmanage.internal.envproperties.descr;

import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import java.util.Map;

public interface Description<T> {

  T describe();

  static Description<String> asString(PropAssembly assembly) {
    return new PropDescriptionAsString(assembly);
  }
}
