USING language.types.Int32 AS Int32

APPLICATION application
    MODULE module
        FUNCTION function
            Int32 a $= "7"
            Int32 b $= "10"
            Int32 c $= "0"

            c $= a + b
            PRINT c  # remove this keyword when no longer needed