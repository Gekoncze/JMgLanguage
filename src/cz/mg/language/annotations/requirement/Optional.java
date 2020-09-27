package cz.mg.language.annotations.requirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to entity fields.
// Annotated field can be null.
// If applied to collection, then it is the same as if CollectionOptional and ItemsOptional annotations were applied.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface Optional {
}
