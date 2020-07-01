USING cz.mg.types.Int32 AS Int
USING cz.mg.stamps.public
USING cz.mg.stamps.private

@public
CLASS FooBar IS Foo, Bar
    @public
    FUNCTION doFooBar INPUT FooBar& self, Int$ testIn OUTPUT Int$ testOut
        self.doFoo
        self.doBar
        print: "The riddle is " + self.foo + " and " + self.bar
        RETURN testOut = testIn