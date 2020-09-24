package cz.mg.language.annotations.storage;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to entity fields.
// Annotated field owns another object.
// Owned object can have multiple owners.
// If applied to collection, then it is the same as if CollectionShared and ItemsShared annotations were applied.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Shared {
}
