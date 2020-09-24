package cz.mg.language.annotations.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to entity fields.
// Annotated field just holds reference to another object (no ownership).
// If applied to collection, then it is the same as if CollectionLink and ItemsLink annotations were applied.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
public @interface Link {
}
