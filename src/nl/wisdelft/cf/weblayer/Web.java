package nl.wisdelft.cf.weblayer;

import java.lang.annotation.*;


@Target({ElementType.METHOD})

public @interface Web {

    String info() default "[ Web Call] to crowdflower";
}
