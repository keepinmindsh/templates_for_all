package bong.lines.modular;

import bong.lines.modular.modules.BoundedContext;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class ModularApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .parent(ModularApplication.class)
                .child(BoundedContext.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }

}
