package lines.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;

import java.io.File;
import java.io.IOException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class Main {
    public static void main(String[] args) {

        // TODO - it does not created with bellow code. need to check tomorrow
        try {
            new ByteBuddy().redefine(TargetClass.class)
                            .method(named("getValue"))
                                    .intercept(FixedValue.value("Good Night!"))
                    .make().saveIn(new File("D:/GIT/01_GIT/06_templates_for_all/01_java/bytecode_bytebuddy/build/"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(new TargetClass().getValue());
    }
}
