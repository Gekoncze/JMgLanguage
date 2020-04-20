package cz.mg.compiler.entities.logic.c;

import cz.mg.collections.list.List;
import cz.mg.compiler.annotations.entity.Part;
import cz.mg.compiler.entities.logic.c.elements.directives.CInclude;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CFunctionForwardDeclaration;
import cz.mg.compiler.entities.logic.c.elements.statements.declarations.CStructureForwardDeclaration;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CFunctionDefinition;
import cz.mg.compiler.entities.logic.c.elements.statements.definitions.CStructureDefinition;


public class CSourceCode extends CEntity {
    @Part
    private final List<CInclude> includes = new List<>();

    @Part
    private final List<CStructureForwardDeclaration> structureForwardDeclarations = new List<>();

    @Part
    private final List<CStructureDefinition> structureDefinitions = new List<>();

    @Part
    private final List<CFunctionForwardDeclaration> functionForwardDeclarations = new List<>();

    @Part
    private final List<CFunctionDefinition> functionDefinitions = new List<>();

    public CSourceCode() {
    }

    public List<CInclude> getIncludes() {
        return includes;
    }

    public List<CStructureForwardDeclaration> getStructureForwardDeclarations() {
        return structureForwardDeclarations;
    }

    public List<CStructureDefinition> getStructureDefinitions() {
        return structureDefinitions;
    }

    public List<CFunctionForwardDeclaration> getFunctionForwardDeclarations() {
        return functionForwardDeclarations;
    }

    public List<CFunctionDefinition> getFunctionDefinitions() {
        return functionDefinitions;
    }
}
