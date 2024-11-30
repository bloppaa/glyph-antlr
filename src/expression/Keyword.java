package expression;

import java.util.Map;

public abstract class Keyword {
    public static final String SEMICOLON = "✋";
    public static final String COMMA = "🔸";
    public static final String ASSIGN = "👉";
    public static final String NOT = "🙅";
    public static final String AND = "🤝";
    public static final String OR = "🤷";
    public static final String PLUS = "➕";
    public static final String MINUS = "➖";
    public static final String TIMES = "✖️";
    public static final String DIVIDE = "➗";
    public static final String MOD = "🪙";
    public static final String EQ = "🟰";
    public static final String NEQ = "🚫";
    public static final String GT = "▶️";
    public static final String GTE = "⏩";
    public static final String LT = "◀️";
    public static final String LTE = "⏪";
    public static final String LPAREN = "🧎‍➡️";
    public static final String RPAREN = "🧎";
    public static final String LCURLY = "🏃‍➡️";
    public static final String RCURLY = "🏃";
    public static final String IF = "🤔";
    public static final String ELSE = "👇";
    public static final String FOR = "🔁";
    public static final String TO = "➡️";
    public static final String STEP = "👟";
    public static final String WHILE = "🌀";
    public static final String PRINT = "🖨️";
    public static final String RETURN = "↩️";
    public static final String INT_TYPE = "🧮";
    public static final String FLOAT_TYPE = "☁️";
    public static final String BOOL_TYPE = "✅";
    public static final String STRING_TYPE = "📄";
    public static final String FUNC_TYPE = "🍿";
    public static final String TRUE = "👍";
    public static final String FALSE = "👎";
    public static final String STR_DELIM = "🧵";

    public static final Map<String, String> translated = Map.ofEntries(
            Map.entry(SEMICOLON, ";"),
            Map.entry(COMMA, ","),
            Map.entry(ASSIGN, "="),
            Map.entry(NOT, "!"),
            Map.entry(AND, "&&"),
            Map.entry(OR, "||"),
            Map.entry(PLUS, "+"),
            Map.entry(MINUS, "-"),
            Map.entry(TIMES, "*"),
            Map.entry(DIVIDE, "/"),
            Map.entry(MOD, "%"),
            Map.entry(EQ, "=="),
            Map.entry(NEQ, "!="),
            Map.entry(GT, ">"),
            Map.entry(GTE, ">="),
            Map.entry(LT, "<"),
            Map.entry(LTE, "<="),
            Map.entry(LPAREN, "("),
            Map.entry(RPAREN, ")"),
            Map.entry(LCURLY, "{"),
            Map.entry(RCURLY, "}"),
            Map.entry(IF, "if"),
            Map.entry(ELSE, "else"),
            Map.entry(FOR, "for"),
            Map.entry(TO, "to"),
            Map.entry(STEP, "step"),
            Map.entry(WHILE, "while"),
            Map.entry(PRINT, "print"),
            Map.entry(RETURN, "return"),
            Map.entry(INT_TYPE, "int"),
            Map.entry(FLOAT_TYPE, "float"),
            Map.entry(BOOL_TYPE, "bool"),
            Map.entry(STRING_TYPE, "string"),
            Map.entry(FUNC_TYPE, "func"),
            Map.entry(TRUE, "true"),
            Map.entry(FALSE, "false"),
            Map.entry(STR_DELIM, "\""));
}
