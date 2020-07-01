USING cz.mg.types.Int32 AS Int
USING cz.mg.stamps.public
USING cz.mg.stamps.private

@public
CLASS Foo
    @private Int$ foo

    @public
    FUNCTION doFoo INPUT Foo& self
        print: "The riddle is " + self.foo