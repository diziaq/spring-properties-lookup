package dzq.sandbox.spring.app.propmanage.internal.envproperties.descr;


import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

final class PropDescriptionAsMap implements Description<Map<String, Object>> {

  private final PropAssembly propAssembly;

  PropDescriptionAsMap(PropAssembly propAssembly) {
    this.propAssembly = propAssembly;
  }

  @Override
  public Map<String, Object> describe() {
    Map<String, Object> map = new LinkedHashMap<>(1);
    propAssembly.share(
        (assemblyName, propUnions) -> {
          map.put("title", assemblyName);
          map.put("unions", propUnionsAsList(propUnions));
        });
    return map;
  }

  private List<?> propUnionsAsList(Stream<PropUnion> propUnions) {
    return propUnions
        .map(this::propUnionAsMap)
        .filter(u -> !u.isEmpty())
        .collect(Collectors.toCollection(LinkedList::new));
  }

  private Map<String, ?> propUnionAsMap(PropUnion propUnion) {
    Map<String, Object> map = new LinkedHashMap<>();

    propUnion.share(
        (name, props) -> {
          var content = propsAsMap(props);

          if (!content.isEmpty()) {
            map.put("union", name);
            map.put("properties", content);
          }
        });

    return map;
  }

  private Map<String, String> propsAsMap(Stream<Prop> props) {
    Map<String, String> map = new LinkedHashMap<>();

    props.forEach(prop -> prop.share((name, value) -> map.put(refine(name), refine(value))));

    return map;
  }

  private static String refine(String string) {
    return string.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
  }
}
