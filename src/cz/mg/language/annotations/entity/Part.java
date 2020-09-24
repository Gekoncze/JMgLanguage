package cz.mg.language.annotations.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to entity fields.
// Annotated field owns another object as its part.
// Owned object must not have multiple owners.
// The object can change owner.
// If applied to collection, then it is the same as if CollectionPart and ItemsPart annotations were applied.
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
public @interface Part {
}
