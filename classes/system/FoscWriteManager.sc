/* ------------------------------------------------------------------------------------------------------------
• FoscWriteManager
------------------------------------------------------------------------------------------------------------ */
FoscWriteManager : Fosc {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    var <client;
    *new { |client|
        ^super.new.init(client);
    }
    init { |argClient|
        client = argClient;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INSTANCE PROPERTIES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • client

    Gets client of persistence manager.

    Returns component or selection.
    -------------------------------------------------------------------------------------------------------- */
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC INSTANCE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • asLY

    Writes client as LilyPond file.

    Autogenerates file path when 'path' is nil.

    Returns output path.


    • Example 1

    a = FoscNote(60, 1/4);
    b = a.write.asLY;
    openOS(b);


    • Example 2

    a = FoscNote(60, 1/4);
    b = a.write.asLY(Platform.userHomeDir ++ "/test.ly");
    openOS(b);
    -------------------------------------------------------------------------------------------------------- */
    asLY { |path, illustrateEnvir|
        var illustration, lyFileName, lyFile;

        if (illustrateEnvir.isNil) {
            illustration = client.illustrate.lilypond;
        } {
            illustration = client.performWithEnvir('illustrate', illustrateEnvir).lilypond;
        };
        
        if (path.isNil) {
            lyFileName = FoscIOManager.nextOutputFileName;
            path = "%/%".format(Fosc.outputDirectory, lyFileName);
        };
        
        lyFile = File(path, "w");
        lyFile.write(illustration);
        lyFile.close;
        
        ^path;
    }
    /* --------------------------------------------------------------------------------------------------------
    • asMIDI
    -------------------------------------------------------------------------------------------------------- */
    asMIDI {
        ^this.notYetImplemented(thisMethod);
    }
    /* --------------------------------------------------------------------------------------------------------
    • asPDF

    Writes client as PDF file.

    Autogenerates file path when 'path' is nil.

    Returns output path.
    


    • Example 1

    Automatically generate a file name and write to fosc-output directory.

    a = FoscNote(60, 1/4);
    b = a.write.asPDF;
    openOS(b);


    • Example 2

    Specify the output path.

    a = FoscNote(60, 1/4);
    b = a.write.asPDF(Platform.userHomeDir ++ "/foo.pdf");
    openOS(b);


    • Example 3

    Add file extensions automatically.

    a = FoscNote(60, 1/4);
    b = a.write.asPDF(Platform.userHomeDir ++ "/foo");
    openOS(b);
    -------------------------------------------------------------------------------------------------------- */
    asPDF { |path, illustrateEnvir, flags|  
        var lyPath;

        if (illustrateEnvir.isNil) { assert(client.respondsTo('illustrate')) };
        if (path.notNil) { path = path.splitext[0] ++ ".ly" };
        lyPath = this.asLY(path, illustrateEnvir);
        path = lyPath.splitext[0];
        FoscIOManager.runLilypond(lyPath, flags, path);
        
        ^(path ++ ".pdf");
    }
    /* --------------------------------------------------------------------------------------------------------
    • asPNG
    
    Writes client as cropped PNG file.

    Autogenerates file path when 'path' is nil.

    Returns output path.


    • Example 1

    Automatically generate a file name and write to fosc-output directory.

    a = FoscNote(60, 1/4);
    b = a.write.asPNG(resolution: 300);
    openOS(b);


    • Example 2

    Specify the output path.

    a = FoscNote(60, 1/4);
    b = a.write.asPNG(Platform.userHomeDir ++ "/foo.png");
    openOS(b);


    • Example 3

    Add file extensions automatically.

    a = FoscNote(60, 1/4);
    b = a.write.asPNG(Platform.userHomeDir ++ "/foo");
    openOS(b);
    -------------------------------------------------------------------------------------------------------- */
    asPNG { |path, illustrateEnvir, resolution=300|
        var lyPath, flags, files;
    
        flags = "-dbackend=eps -dresolution=% -dno-gs-load-fonts -dinclude-eps-fonts --png";
        flags = flags.format(resolution);
        
        if (illustrateEnvir.isNil) { assert(client.respondsTo('illustrate')) };
        if (path.notNil) { path = path.splitext[0] ++ ".ly" };
        lyPath = this.asLY(path, illustrateEnvir);
        path = lyPath.splitext[0];
        FoscIOManager.runLilypond(lyPath, flags, path);
        
        files = #["%-1.eps", "%-systems.count", "%-systems.tex", "%-systems.texi"];
        files.do { |each| File.delete(each.format(path)) };
        
        ^(path ++ ".png");
    }
    /* --------------------------------------------------------------------------------------------------------
    • asSVG
    
    Writes client as SVG file.

    Autogenerates file path when 'path' is nil.

    Returns output path.


    • Example 1

    Automatically generate a file name and write to fosc-output directory.

    a = FoscNote(60, 1/4);
    b = a.write.asSVG;
    openOS(b);


    • Example 2

    Specify the output path.

    a = FoscNote(60, 1/4);
    b = a.write.asSVG(Platform.userHomeDir ++ "/foo.svg");
    openOS(b);


    • Example 3

    Add file extensions automatically.

    a = FoscNote(60, 1/4);
    b = a.write.asSVG(Platform.userHomeDir ++ "/foo");
    openOS(b);
    -------------------------------------------------------------------------------------------------------- */
    asSVG { |path, illustrateEnvir|        
        var lyPath;

        if (illustrateEnvir.isNil) { assert(client.respondsTo('illustrate')) };
        if (path.notNil) { path = path.splitext[0] ++ ".ly" };
        lyPath = this.asLY(path, illustrateEnvir);
        path = lyPath.splitext[0];
        FoscIOManager.runLilypond(lyPath, "-dbackend=svg", path);

        ^(path ++ ".svg");
    }
}
