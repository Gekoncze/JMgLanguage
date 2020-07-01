USING cz.mg.types.Int32 AS Int
USING cz.mg.stamps.public
USING cz.mg.stamps.private

@public
CLASS Bar
    @private Int$ bar

    FUNCTION doBar INPUT Bar& self
        print: "The riddle is " + self.bar