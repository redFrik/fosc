/* ------------------------------------------------------------------------------------------------------------
• FoscLilypondVersionToken

A LilyPond file \version token.


FoscLilypondVersionToken('2.19.0').format;

FoscLilypondVersionToken().format;
------------------------------------------------------------------------------------------------------------ */
FoscLilypondVersionToken : FoscObject {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // INIT
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    var <versionString;
    *new { |versionString|
        if (versionString.isKindOf(FoscLilypondVersionToken)) {
            versionString = versionString.versionString;
        };
        ^super.new.init(versionString);
    }
    init { |argVersionString|
        if (argVersionString.isNil) { argVersionString = FoscConfiguration.lilypondVersionString };
        versionString = argVersionString;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC METHODS: SPECIAL METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • format

    Formats LilyPond version token.
    
    Return string.
    
    def __format__(self, format_specification=''):
        from abjad.tools import systemtools
        if format_specification in ('', 'lilypond'):
            return self._get_lilypond_format()
        elif format_specification == 'storage':
            return systemtools.StorageFormatAgent(self).get_storage_format()
        return str(self)

    FoscLilypondVersionToken('2.19.0').format;
    FoscLilypondVersionToken().format;
    -------------------------------------------------------------------------------------------------------- */
    format {
        ^this.prGetLilypondFormat;
    }
    /* --------------------------------------------------------------------------------------------------------
    • asCompileString (abjad: __repr__)

    Gets interpreter representation of LilyPond version_string token.
    
    Returns string.
    
    def __repr__(self):
        return '{}({!r})'.format(type(self).__name__, self.version_string)
    -------------------------------------------------------------------------------------------------------- */
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* --------------------------------------------------------------------------------------------------------
    • prGetLilypondFormat
    
    def _get_lilypond_format(self):
            return r'\version "{}"'.format(self.version_string)
    -------------------------------------------------------------------------------------------------------- */
    prGetLilypondFormat {
        ^"\\version %".format(versionString.asString.quote);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PUBLIC PROPERTIES
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////

    /* --------------------------------------------------------------------------------------------------------
    •

    Gets version string of LilyPond version token.
    
    Returns string.
    
    @property
    def version_string(self):
        return self._version_string
    
    a = FoscLilypondVersionToken();
    a.versionString;

            
    Gets version string from install environment:

    a = FoscLilypondVersionToken();
    a.versionString;

    Gets version string from explicit input:
    
    a = FoscLilypondVersionToken("2.19.0");
    a.versionString;
    -------------------------------------------------------------------------------------------------------- */
}

