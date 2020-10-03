package cz.mg.language.annotations.storage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to entity fields.
// Annotated field owns another object as its part.
// Owned object must not have multiple owners.
// The object cannot change owner.
// If applied to collection, then it is the same as if CollectionValue and ItemsValue annotations were applied.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface Value {
}
