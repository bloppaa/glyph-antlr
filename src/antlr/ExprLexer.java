// Generated from Expr.g4 by ANTLR 4.13.2

package antlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExprLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		INT_TYPE=10, FLOAT_TYPE=11, ID=12, NUM=13, WS=14, COMMENT=15;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"INT_TYPE", "FLOAT_TYPE", "ID", "NUM", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'='", "'-'", "'('", "')'", "'*'", "'/'", "'%'", "'+'", 
			"'int'", "'float'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "INT_TYPE", 
			"FLOAT_TYPE", "ID", "NUM", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ExprLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Expr.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u000ff\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0001"+
		"\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0005\u000b>\b\u000b\n\u000b\f\u000bA\t\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0005\fF\b\f\n\f\f\fI\t\f\u0003\fK\b\f\u0001\f\u0001"+
		"\f\u0004\fO\b\f\u000b\f\f\fP\u0003\fS\b\f\u0001\r\u0004\rV\b\r\u000b\r"+
		"\f\rW\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0005\u000e`\b\u000e\n\u000e\f\u000ec\t\u000e\u0001\u000e\u0001\u000e"+
		"\u0000\u0000\u000f\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u0001\u0000\u0006\u0001\u0000az\u0004\u0000"+
		"09AZ__az\u0001\u000019\u0001\u000009\u0003\u0000\t\n\r\r  \u0002\u0000"+
		"\n\n\r\rl\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000"+
		"\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000"+
		"\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000"+
		"\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000"+
		"\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000"+
		"\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000"+
		"\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000"+
		"\u0000\u001d\u0001\u0000\u0000\u0000\u0001\u001f\u0001\u0000\u0000\u0000"+
		"\u0003!\u0001\u0000\u0000\u0000\u0005#\u0001\u0000\u0000\u0000\u0007%"+
		"\u0001\u0000\u0000\u0000\t\'\u0001\u0000\u0000\u0000\u000b)\u0001\u0000"+
		"\u0000\u0000\r+\u0001\u0000\u0000\u0000\u000f-\u0001\u0000\u0000\u0000"+
		"\u0011/\u0001\u0000\u0000\u0000\u00131\u0001\u0000\u0000\u0000\u00155"+
		"\u0001\u0000\u0000\u0000\u0017;\u0001\u0000\u0000\u0000\u0019J\u0001\u0000"+
		"\u0000\u0000\u001bU\u0001\u0000\u0000\u0000\u001d[\u0001\u0000\u0000\u0000"+
		"\u001f \u0005;\u0000\u0000 \u0002\u0001\u0000\u0000\u0000!\"\u0005=\u0000"+
		"\u0000\"\u0004\u0001\u0000\u0000\u0000#$\u0005-\u0000\u0000$\u0006\u0001"+
		"\u0000\u0000\u0000%&\u0005(\u0000\u0000&\b\u0001\u0000\u0000\u0000\'("+
		"\u0005)\u0000\u0000(\n\u0001\u0000\u0000\u0000)*\u0005*\u0000\u0000*\f"+
		"\u0001\u0000\u0000\u0000+,\u0005/\u0000\u0000,\u000e\u0001\u0000\u0000"+
		"\u0000-.\u0005%\u0000\u0000.\u0010\u0001\u0000\u0000\u0000/0\u0005+\u0000"+
		"\u00000\u0012\u0001\u0000\u0000\u000012\u0005i\u0000\u000023\u0005n\u0000"+
		"\u000034\u0005t\u0000\u00004\u0014\u0001\u0000\u0000\u000056\u0005f\u0000"+
		"\u000067\u0005l\u0000\u000078\u0005o\u0000\u000089\u0005a\u0000\u0000"+
		"9:\u0005t\u0000\u0000:\u0016\u0001\u0000\u0000\u0000;?\u0007\u0000\u0000"+
		"\u0000<>\u0007\u0001\u0000\u0000=<\u0001\u0000\u0000\u0000>A\u0001\u0000"+
		"\u0000\u0000?=\u0001\u0000\u0000\u0000?@\u0001\u0000\u0000\u0000@\u0018"+
		"\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000BK\u00050\u0000\u0000"+
		"CG\u0007\u0002\u0000\u0000DF\u0007\u0003\u0000\u0000ED\u0001\u0000\u0000"+
		"\u0000FI\u0001\u0000\u0000\u0000GE\u0001\u0000\u0000\u0000GH\u0001\u0000"+
		"\u0000\u0000HK\u0001\u0000\u0000\u0000IG\u0001\u0000\u0000\u0000JB\u0001"+
		"\u0000\u0000\u0000JC\u0001\u0000\u0000\u0000KR\u0001\u0000\u0000\u0000"+
		"LN\u0005.\u0000\u0000MO\u0007\u0003\u0000\u0000NM\u0001\u0000\u0000\u0000"+
		"OP\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000"+
		"\u0000QS\u0001\u0000\u0000\u0000RL\u0001\u0000\u0000\u0000RS\u0001\u0000"+
		"\u0000\u0000S\u001a\u0001\u0000\u0000\u0000TV\u0007\u0004\u0000\u0000"+
		"UT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000"+
		"\u0000WX\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000YZ\u0006\r\u0000"+
		"\u0000Z\u001c\u0001\u0000\u0000\u0000[\\\u0005/\u0000\u0000\\]\u0005/"+
		"\u0000\u0000]a\u0001\u0000\u0000\u0000^`\b\u0005\u0000\u0000_^\u0001\u0000"+
		"\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000ab\u0001"+
		"\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000"+
		"de\u0006\u000e\u0000\u0000e\u001e\u0001\u0000\u0000\u0000\b\u0000?GJP"+
		"RWa\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}