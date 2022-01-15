package bong.lines.db;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MyBatisConfiguration.class)
public @interface EnableMyBatisModule {
}
