package dzq.sandbox.spring.app.propmanage.tools.properties;

import dzq.sandbox.spring.app.propmanage.tools.properties.impl.NamedUnit;
import dzq.sandbox.spring.app.propmanage.tools.properties.impl.PropsFacade;
import java.util.function.Function;
import java.util.stream.Stream;

public interface PropUnion extends NamedUnit<String, Stream<Prop>> {

  static PropUnion fromNames(
      String title, Stream<String> propertyNames, Function<String, Object> getPropValue) {
    return PropsFacade.unionFromNames(title, propertyNames, getPropValue);
  }

  static PropUnion sorted(String title, Stream<Prop> props) {
    return PropsFacade.unionSorted(title, props);
  }

  static PropUnion unrecognizable(String title, Object additionalInfo) {
    return PropsFacade.unionUnrecognizable(title, additionalInfo.toString());
  }
}
