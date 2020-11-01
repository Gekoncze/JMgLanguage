package cz.mg.language.tasks.mg.resolver.command.utilities;

import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.requirement.Optional;
import cz.mg.annotations.storage.Link;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.text.ReadableText;
import cz.mg.collections.text.ReadonlyText;
import cz.mg.language.entities.mg.runtime.components.types.functions.MgFunction;
import cz.mg.language.entities.mg.runtime.components.variables.MgInstanceVariable;
import cz.mg.language.entities.mg.runtime.parts.MgDatatype;
import cz.mg.language.entities.mg.runtime.parts.connection.MgConnection;
import cz.mg.language.entities.mg.runtime.parts.connection.MgInputConnector;
import cz.mg.language.entities.mg.runtime.parts.connection.MgOutputConnector;


public class DeclarationHelper {
    @Mandatory @Part
    private static final ThreadLocal<@Optional @Link MgFunction> function = new ThreadLocal<>();

    public static void setFunction(MgFunction function){
        DeclarationHelper.function.set(function);
    }

    public static MgFunction getFunction(){
        return DeclarationHelper.function.get();
    }

    private static MgFunction getFunctionMandatory(){
        MgFunction function = getFunction();
        if(function == null){
            throw new RuntimeException("Declaration helper can be used only in function resolve context.");
        }
        return function;
    }

    public static MgInstanceVariable newVariable(ReadableText name, MgDatatype datatype){
        MgFunction function = getFunctionMandatory();
        MgInstanceVariable variable = new MgInstanceVariable(name, function);
        variable.setDatatype(datatype);
        function.getLocalVariables().addLast(variable);
        return function.getLocalVariables().getLast();
    }

    public static MgInstanceVariable newVariable(MgDatatype datatype){
        MgFunction function = getFunctionMandatory();
        MgInstanceVariable variable = new MgInstanceVariable(new ReadonlyText(), function);
        variable.setDatatype(datatype);
        function.getLocalVariables().addLast(variable);
        return function.getLocalVariables().getLast();
    }

    public static void connect(
        @Mandatory MgInputConnector inputConnector,
        @Mandatory MgOutputConnector outputConnector
    ){
        MgConnection.connect(
            inputConnector,
            newVariable(inputConnector.getDatatype()),
            outputConnector
        );
    }

    public static void sink(){
        // todo
    }

    public static void raise(){
        // todo
    }
}
