package cz.mg.language.annotations.requirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Applicable only to collection entity fields.
// See Mandatory for more details.
// Applies only to collection items, not to the collection itself.
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ItemsMandatory {
}
