//package cz.mg.language.entities.mg.runtime.clazzes;
//
//import cz.mg.collections.array.Array;
//import cz.mg.collections.text.ReadableText;
//import cz.mg.language.annotations.entity.Part;
//import cz.mg.language.entities.mg.runtime.other.MgParameter;
//
//
//public class MgCollection extends MgClazz {
//    @Part
//    private final Array<MgParameter> parameters;
//
//    protected MgCollection(MgClazz type, ReadableText name, Array<MgClazz> clazzes, Array<MgVariable> variables, Array<MgFunction> functions, Array<MgParameter> parameters, Array<MgStamp> stamps) {
//        super(type, name, clazzes, variables, functions, stamps);
//        this.parameters = parameters;
//    }
//
//    public MgCollection(ReadableText name, Array<MgClazz> clazzes, Array<MgVariable> variables, Array<MgFunction> functions, Array<MgParameter> parameters, Array<MgStamp> stamps) {
//        this(TYPE, name, clazzes, variables, functions, parameters, stamps);
//    }
//
//    public Array<MgParameter> getParameters() {
//        return parameters;
//    }
//}
