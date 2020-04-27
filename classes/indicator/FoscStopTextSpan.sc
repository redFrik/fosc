/* ------------------------------------------------------------------------------------------------------------
• FoscStopTextSpan (abjad 3.0)

LilyPond '\stopTextSpan' command.


• Example 1
------------------------------------------------------------------------------------------------------------ */
FoscStopTextSpan : FoscObject {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    var <command, <leak;
    //var <publishStorageFormat=true;
    *new { |command="\\stopTextSpan", leak=false|
        // assert isinstance(command, str), repr(command)
        // assert command.startswith('\\'), repr(command)
        // self._command = command
        // if leak is not None:
        //     leak = bool(leak)
        // self._leak = leak

        ^super.new.init(command, leak);
    }
    init { |argCommand, argLeak|
        command = argCommand;
        leak = argLeak;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INSTANCE PROPERTIES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • command

    Gets command.
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • enchained

    Is true.
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • leak

    Is true when stop text span leaks LilyPond '<>' empty chord.
    -------------------------------------------------------------------------------------------------------- */
    /* --------------------------------------------------------------------------------------------------------
    • spannerStop

    Is true.
    -------------------------------------------------------------------------------------------------------- */
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE INSTANCE METHODS
    /////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    /* --------------------------------------------------------------------------------------------------------
    • prGetLilypondFormatBundle
    -------------------------------------------------------------------------------------------------------- */
    prGetLilypondFormatBundle { |component|
        // bundle = LilyPondFormatBundle()
        // string = self.command
        // if self.leak:
        //     string = f'<> {string}'
        //     bundle.after.leaks.append(string)
        // else:
        //     bundle.after.spanner_stops.append(string)
        // return bundle
        var bundle, string;
        bundle = FoscLilypondFormatBundle();
        string = command;
        if (leak) {
            string = "<> %".format(string);
            bundle.after.leaks.add(string);
        } {
            bundle.after.spannerStops.add(string);
        };
        ^bundle;
    }
}