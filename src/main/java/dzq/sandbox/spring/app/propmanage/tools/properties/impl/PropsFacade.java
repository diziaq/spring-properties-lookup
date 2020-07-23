package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class PropsFacade {

  /** */
  public static Prop propOf(String name, Object value) {
    Objects.requireNonNull(name, "Prop name should be not null");

    if (value instanceof String) {
      var strValue = (String) value;
      return strValue.isEmpty() ? new PropNameOnly(name) : new PropNormal(name, strValue);
    } else if (value == null) {
      return new PropNameOnly(name);
    } else if (value instanceof Number) {
      return new PropNormal(name, value.toString());
    } else {
      return new PropObject(name, value, value.getClass());
    }
  }

  /** */
  public static PropAssembly assemblyOf(String title, Collection<PropUnion> propUnions) {
    Objects.requireNonNull(title, "PropSetAssembly title should be not null");
    Objects.requireNonNull(propUnions, "PropSetAssembly propUnions should be not null");

    int size = propUnions.size();
    if (size < 1) {
      throw new RuntimeException("PropSetAssembly propUnions should be not empty");
    } else if (size == 1) {
      return new PropAssemblySingle(title, propUnions.iterator().next());
    } else {
      return new PropAssemblyDefault(title, propUnions);
    }
  }

  /** */
  public static PropUnion unionSorted(String name, Stream<Prop> props) {
    Objects.requireNonNull(name, "PropSet name should be not null");
    Objects.requireNonNull(props, "PropSet Stream<Prop> props should be not null");

    return new PropUnionSorted(new PropUnionStandard(name, props.collect(Collectors.toList())));
  }

  /** */
  public static PropUnion unionUnrecognizable(String title, String additionalInfo) {
    return new PropUnionUnrecognized(title, additionalInfo);
  }

  /** */
  public static PropUnion unionFromNames(
      String title, Stream<String> propertyNames, Function<String, Object> getPropValue) {
    return unionSorted(title, propertyNames.map(pn -> Prop.of(pn, getPropValue.apply(pn))));
  }
}
