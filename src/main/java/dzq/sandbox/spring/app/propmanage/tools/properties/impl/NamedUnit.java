package dzq.sandbox.spring.app.propmanage.tools.properties.impl;

import java.util.function.BiConsumer;

public interface NamedUnit<T extends Comparable<T>, E> extends Comparable<NamedUnit<T, E>> {

  T name();

  void share(BiConsumer<T, E> maker);

  @Override
  default int compareTo(NamedUnit<T, E> other) {
    return this.name().compareTo(other.name());
  }
}
