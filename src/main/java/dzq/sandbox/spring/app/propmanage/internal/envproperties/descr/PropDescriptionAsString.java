package dzq.sandbox.spring.app.propmanage.internal.envproperties.descr;

import dzq.sandbox.spring.app.propmanage.tools.properties.Prop;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropAssembly;
import dzq.sandbox.spring.app.propmanage.tools.properties.PropUnion;

final class PropDescriptionAsString implements Description<String> {

  private final PropAssembly propAssembly;

  PropDescriptionAsString(PropAssembly propAssembly) {
    this.propAssembly = propAssembly;
  }

  @Override
  public String describe() {
    var builder = new StringBuilder("\n\n");

    propAssembly.share(
        (assemblyName, propUnions) -> {
          builder.append("------> ").append(assemblyName).append("----------------------------\n");
          propUnions.forEach(x -> appendPropSet(builder, x));
          builder.append("------<\n\n");
        });

    return builder.toString();
  }

  private void appendPropSet(StringBuilder builder, PropUnion propUnion) {
    propUnion.share(
        (name, props) -> {
          builder.append("  --> ").append(name).append("---------------------\n");
          props.forEach(x -> appendProp(builder, x));
          builder.append("  --<\n");
        });
  }

  private void appendProp(StringBuilder builder, Prop prop) {
    prop.share(
        (name, value) -> {
          builder
              .append("        | ")
              .append(refine(name))
              .append(" = ")
              .append(refine(value))
              .append("\n");
        });
  }

  private static String refine(String string) {
    return string.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
  }
}
