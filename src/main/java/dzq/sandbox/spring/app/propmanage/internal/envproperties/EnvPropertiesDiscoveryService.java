package dzq.sandbox.spring.app.propmanage.internal.envproperties;


import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

@Component
final class EnvPropertiesDiscoveryService {

  private final AbstractEnvironment environment;

  private final Map<String, PropAssembly> cache;

  EnvPropertiesDiscoveryService(AbstractEnvironment environment) {
    this.environment = environment;
    this.cache = new HashMap<>();
  }

  public PropAssembly getActiveProperties() {
    return cache.computeIfAbsent(
        "getActiveProperties",
        x -> fromEnv("Sandbox application properties (env)", environment, name -> true));
  }

  public PropAssembly getDeclaredProperties() {
    return cache.computeIfAbsent(
        "getDeclaredProperties",
        x ->
            fromSources(
                "Sandbox application properties (all sources)",
                environment.getPropertySources().stream()));
  }

  public PropAssembly getConfigProperties() {
    return cache.computeIfAbsent(
        "getConfigProperties",
        x ->
            fromEnv(
                "Sandbox application properties (application-[profile])",
                environment,
                name -> name.startsWith("applicationConfig")));
  }

  private static PropAssembly fromEnv(
      String title, AbstractEnvironment environment, Predicate<String> sourceNamePredicate) {

    var propsStream =
        environment.getPropertySources().stream()
            .filter(x -> sourceNamePredicate.test(x.getName()))
            .flatMap(
                ps -> {
                  if (ps instanceof EnumerablePropertySource) {
                    return Arrays.stream(((EnumerablePropertySource<?>) ps).getPropertyNames());
                  } else {
                    return Stream.empty();
                  }
                })
            .distinct()
            .map(name -> Prop.of(name, environment.getProperty(name)));

    var propUnion = PropUnion.sorted("", propsStream);

    return PropAssembly.fromUnions(title, Collections.singleton(propUnion));
  }

  private static PropAssembly fromSources(
      String title, Stream<PropertySource<?>> springPropertySources) {
    var propUnions = springPropertySources.map(x -> fromSource(x)).collect(Collectors.toList());

    return PropAssembly.fromUnions(title, propUnions);
  }

  private static PropUnion fromSource(PropertySource<?> source) {
    if (source instanceof EnumerablePropertySource) {
      var enumerableSource = (EnumerablePropertySource<?>) source;

      return PropUnion.fromNames(
          source.getName(),
          Arrays.stream(enumerableSource.getPropertyNames()),
          enumerableSource::getProperty);
    } else {
      return PropUnion.unrecognizable(source.getName(), source.getClass());
    }
  }
}
