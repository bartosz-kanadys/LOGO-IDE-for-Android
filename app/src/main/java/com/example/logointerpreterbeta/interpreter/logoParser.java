package com.example.logointerpreterbeta.interpreter;// Generated from logo.g4 by ANTLR 4.13.1

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class logoParser extends Parser {
    static {
        RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION);
    }

    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__0 = 1, T__1 = 2, T__2 = 3, T__3 = 4, T__4 = 5, T__5 = 6, T__6 = 7, T__7 = 8, T__8 = 9,
            T__9 = 10, T__10 = 11, T__11 = 12, T__12 = 13, T__13 = 14, T__14 = 15, T__15 = 16, T__16 = 17,
            T__17 = 18, T__18 = 19, T__19 = 20, T__20 = 21, T__21 = 22, T__22 = 23, T__23 = 24,
            T__24 = 25, T__25 = 26, T__26 = 27, T__27 = 28, T__28 = 29, T__29 = 30, T__30 = 31,
            T__31 = 32, T__32 = 33, T__33 = 34, T__34 = 35, T__35 = 36, T__36 = 37, T__37 = 38,
            T__38 = 39, T__39 = 40, T__40 = 41, T__41 = 42, T__42 = 43, T__43 = 44, T__44 = 45,
            T__45 = 46, T__46 = 47, T__47 = 48, T__48 = 49, T__49 = 50, T__50 = 51, T__51 = 52,
            T__52 = 53, T__53 = 54, T__54 = 55, T__55 = 56, T__56 = 57, T__57 = 58, T__58 = 59,
            T__59 = 60, T__60 = 61, T__61 = 62, T__62 = 63, T__63 = 64, T__64 = 65, T__65 = 66,
            T__66 = 67, T__67 = 68, T__68 = 69, T__69 = 70, T__70 = 71, T__71 = 72, T__72 = 73,
            T__73 = 74, T__74 = 75, T__75 = 76, T__76 = 77, T__77 = 78, T__78 = 79, T__79 = 80,
            T__80 = 81, T__81 = 82, T__82 = 83, T__83 = 84, T__84 = 85, T__85 = 86, T__86 = 87,
            T__87 = 88, T__88 = 89, T__89 = 90, T__90 = 91, T__91 = 92, T__92 = 93, T__93 = 94,
            T__94 = 95, T__95 = 96, T__96 = 97, T__97 = 98, T__98 = 99, T__99 = 100, T__100 = 101,
            T__101 = 102, T__102 = 103, T__103 = 104, T__104 = 105, T__105 = 106, STRINGLITERAL = 107,
            STRING = 108, NUMBER = 109, FLOAT = 110, COMMENT = 111, EOL = 112, WS = 113;
    public static final int
            RULE_prog = 0, RULE_line = 1, RULE_cmd = 2, RULE_procedureInvocation = 3,
            RULE_procedureDeclaration = 4, RULE_parameterDeclarations = 5, RULE_setcornerrounding = 6,
            RULE_setx = 7, RULE_sety = 8, RULE_settextsize = 9, RULE_setbg = 10, RULE_fill = 11,
            RULE_setpencolor = 12, RULE_setpensize = 13, RULE_arc = 14, RULE_func_ = 15,
            RULE_repeat_ = 16, RULE_block = 17, RULE_ife = 18, RULE_comparison = 19,
            RULE_comparisonOperator = 20, RULE_make = 21, RULE_print_ = 22, RULE_name = 23,
            RULE_value = 24, RULE_signExpression = 25, RULE_multiplyingExpression = 26,
            RULE_expression = 27, RULE_deref = 28, RULE_fd = 29, RULE_bk = 30, RULE_rt = 31,
            RULE_lt = 32, RULE_cs = 33, RULE_pu = 34, RULE_pd = 35, RULE_ht = 36,
            RULE_st = 37, RULE_home = 38, RULE_stop = 39, RULE_label = 40, RULE_setxy = 41,
            RULE_random = 42, RULE_fore = 43, RULE_number = 44, RULE_comment = 45;

    private static String[] makeRuleNames() {
        return new String[]{
                "prog", "line", "cmd", "procedureInvocation", "procedureDeclaration",
                "parameterDeclarations", "setcornerrounding", "setx", "sety", "settextsize",
                "setbg", "fill", "setpencolor", "setpensize", "arc", "func_", "repeat_",
                "block", "ife", "comparison", "comparisonOperator", "make", "print_",
                "name", "value", "signExpression", "multiplyingExpression", "expression",
                "deref", "fd", "bk", "rt", "lt", "cs", "pu", "pd", "ht", "st", "home",
                "stop", "label", "setxy", "random", "fore", "number", "comment"
        };
    }

    public static final String[] ruleNames = makeRuleNames();

    private static String[] makeLiteralNames() {
        return new String[]{
                null, "'to'", "'end'", "'TO'", "'END'", "':'", "','", "'setcornerrounding'",
                "'0'", "'1'", "'SETCORNERROUNDING'", "'setcr'", "'SETCR'", "'setx'",
                "'SETX'", "'sety'", "'SETY'", "'setts'", "'SETTS'", "'setpensize'", "'SETPENSIZE'",
                "'setbg'", "'SETBG'", "'setbackground'", "'SETBACKGROUND'", "'fill'",
                "'FILL'", "'setpc'", "'['", "']'", "'SETPC'", "'setpencolor'", "'SETPENCOLOR'",
                "'setps'", "'SETPS'", "'arc'", "'ARC'", "'repeat'", "'REPEAT'", "'if'",
                "'IF'", "'<'", "'>'", "'='", "'<='", "'>='", "'<>'", "'make'", "'MAKE'",
                "'print'", "'PRINT'", "'+'", "'-'", "'*'", "'/'", "'fd'", "'forward'",
                "'FD'", "'FORWARD'", "'bk'", "'backward'", "'BK'", "'BACKWARD'", "'back'",
                "'BACK'", "'rt'", "'right'", "'RT'", "'RIGHT'", "'lt'", "'left'", "'LT'",
                "'LEFT'", "'cs'", "'clearscreen'", "'CS'", "'CLEARSCREEN'", "'pu'", "'penup'",
                "'PU'", "'PENUP'", "'pd'", "'pendown'", "'PD'", "'PENDOWN'", "'ht'",
                "'hideturtle'", "'HT'", "'HIDETURTLE'", "'st'", "'showturtle'", "'ST'",
                "'SHOWTURTLE'", "'home'", "'HOME'", "'stop'", "'STOP'", "'label'", "'LABEL'",
                "'setxy'", "'SETXY'", "'setpos'", "'SETPOS'", "'random'", "'RANDOM'",
                "'for'", "'FOR'"
        };
    }

    private static final String[] _LITERAL_NAMES = makeLiteralNames();

    private static String[] makeSymbolicNames() {
        return new String[]{
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null, "STRINGLITERAL",
                "STRING", "NUMBER", "FLOAT", "COMMENT", "EOL", "WS"
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

    @Override
    public String getGrammarFileName() {
        return "logo.g4";
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public String getSerializedATN() {
        return _serializedATN;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public logoParser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProgContext extends ParserRuleContext {
        public TerminalNode EOF() {
            return getToken(logoParser.EOF, 0);
        }

        public List<TerminalNode> EOL() {
            return getTokens(logoParser.EOL);
        }

        public TerminalNode EOL(int i) {
            return getToken(logoParser.EOL, i);
        }

        public List<LineContext> line() {
            return getRuleContexts(LineContext.class);
        }

        public LineContext line(int i) {
            return getRuleContext(LineContext.class, i);
        }

        public ProgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_prog;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitProg(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProgContext prog() throws RecognitionException {
        ProgContext _localctx = new ProgContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_prog);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(96);
                _errHandler.sync(this);
                _alt = 1;
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(93);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 165476499980287L) != 0)) {
                                    {
                                        setState(92);
                                        line();
                                    }
                                }

                                setState(95);
                                match(EOL);
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(98);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 1, _ctx);
                } while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER);
                setState(101);
                _errHandler.sync(this);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 165476499980287L) != 0)) {
                    {
                        setState(100);
                        line();
                    }
                }

                setState(103);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LineContext extends ParserRuleContext {
        public List<CmdContext> cmd() {
            return getRuleContexts(CmdContext.class);
        }

        public CmdContext cmd(int i) {
            return getRuleContext(CmdContext.class, i);
        }

        public CommentContext comment() {
            return getRuleContext(CommentContext.class, 0);
        }

        public Print_Context print_() {
            return getRuleContext(Print_Context.class, 0);
        }

        public ProcedureDeclarationContext procedureDeclaration() {
            return getRuleContext(ProcedureDeclarationContext.class, 0);
        }

        public LineContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_line;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitLine(this);
            else return visitor.visitChildren(this);
        }
    }

    public final LineContext line() throws RecognitionException {
        LineContext _localctx = new LineContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_line);
        int _la;
        try {
            setState(119);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__6:
                case T__9:
                case T__10:
                case T__11:
                case T__12:
                case T__13:
                case T__14:
                case T__15:
                case T__16:
                case T__17:
                case T__18:
                case T__19:
                case T__20:
                case T__21:
                case T__22:
                case T__23:
                case T__24:
                case T__25:
                case T__26:
                case T__29:
                case T__30:
                case T__31:
                case T__32:
                case T__33:
                case T__34:
                case T__35:
                case T__36:
                case T__37:
                case T__38:
                case T__39:
                case T__46:
                case T__47:
                case T__54:
                case T__55:
                case T__56:
                case T__57:
                case T__58:
                case T__59:
                case T__60:
                case T__61:
                case T__62:
                case T__63:
                case T__64:
                case T__65:
                case T__66:
                case T__67:
                case T__68:
                case T__69:
                case T__70:
                case T__71:
                case T__72:
                case T__73:
                case T__74:
                case T__75:
                case T__76:
                case T__77:
                case T__78:
                case T__79:
                case T__80:
                case T__81:
                case T__82:
                case T__83:
                case T__84:
                case T__85:
                case T__86:
                case T__87:
                case T__88:
                case T__89:
                case T__90:
                case T__91:
                case T__92:
                case T__93:
                case T__94:
                case T__95:
                case T__96:
                case T__97:
                case T__98:
                case T__99:
                case T__100:
                case T__101:
                case T__104:
                case T__105:
                case STRING:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(106);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(105);
                                cmd();
                            }
                        }
                        setState(108);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -35604386335949696L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 24739011624959L) != 0));
                    setState(111);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == COMMENT) {
                        {
                            setState(110);
                            comment();
                        }
                    }

                }
                break;
                case COMMENT:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(113);
                    comment();
                }
                break;
                case T__48:
                case T__49:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(114);
                    print_();
                    setState(116);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    if (_la == COMMENT) {
                        {
                            setState(115);
                            comment();
                        }
                    }

                }
                break;
                case T__0:
                case T__2:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(118);
                    procedureDeclaration();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CmdContext extends ParserRuleContext {
        public Repeat_Context repeat_() {
            return getRuleContext(Repeat_Context.class, 0);
        }

        public FdContext fd() {
            return getRuleContext(FdContext.class, 0);
        }

        public BkContext bk() {
            return getRuleContext(BkContext.class, 0);
        }

        public RtContext rt() {
            return getRuleContext(RtContext.class, 0);
        }

        public LtContext lt() {
            return getRuleContext(LtContext.class, 0);
        }

        public CsContext cs() {
            return getRuleContext(CsContext.class, 0);
        }

        public PuContext pu() {
            return getRuleContext(PuContext.class, 0);
        }

        public PdContext pd() {
            return getRuleContext(PdContext.class, 0);
        }

        public HtContext ht() {
            return getRuleContext(HtContext.class, 0);
        }

        public StContext st() {
            return getRuleContext(StContext.class, 0);
        }

        public HomeContext home() {
            return getRuleContext(HomeContext.class, 0);
        }

        public LabelContext label() {
            return getRuleContext(LabelContext.class, 0);
        }

        public SetxyContext setxy() {
            return getRuleContext(SetxyContext.class, 0);
        }

        public MakeContext make() {
            return getRuleContext(MakeContext.class, 0);
        }

        public ProcedureInvocationContext procedureInvocation() {
            return getRuleContext(ProcedureInvocationContext.class, 0);
        }

        public IfeContext ife() {
            return getRuleContext(IfeContext.class, 0);
        }

        public StopContext stop() {
            return getRuleContext(StopContext.class, 0);
        }

        public ForeContext fore() {
            return getRuleContext(ForeContext.class, 0);
        }

        public ArcContext arc() {
            return getRuleContext(ArcContext.class, 0);
        }

        public SetpensizeContext setpensize() {
            return getRuleContext(SetpensizeContext.class, 0);
        }

        public SetpencolorContext setpencolor() {
            return getRuleContext(SetpencolorContext.class, 0);
        }

        public FillContext fill() {
            return getRuleContext(FillContext.class, 0);
        }

        public SetbgContext setbg() {
            return getRuleContext(SetbgContext.class, 0);
        }

        public SettextsizeContext settextsize() {
            return getRuleContext(SettextsizeContext.class, 0);
        }

        public SetyContext sety() {
            return getRuleContext(SetyContext.class, 0);
        }

        public SetxContext setx() {
            return getRuleContext(SetxContext.class, 0);
        }

        public SetcornerroundingContext setcornerrounding() {
            return getRuleContext(SetcornerroundingContext.class, 0);
        }

        public CmdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_cmd;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitCmd(this);
            else return visitor.visitChildren(this);
        }
    }

    public final CmdContext cmd() throws RecognitionException {
        CmdContext _localctx = new CmdContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_cmd);
        try {
            setState(148);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 7, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(121);
                    repeat_();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(122);
                    fd();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(123);
                    bk();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(124);
                    rt();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(125);
                    lt();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(126);
                    cs();
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(127);
                    pu();
                }
                break;
                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(128);
                    pd();
                }
                break;
                case 9:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(129);
                    ht();
                }
                break;
                case 10:
                    enterOuterAlt(_localctx, 10);
                {
                    setState(130);
                    st();
                }
                break;
                case 11:
                    enterOuterAlt(_localctx, 11);
                {
                    setState(131);
                    home();
                }
                break;
                case 12:
                    enterOuterAlt(_localctx, 12);
                {
                    setState(132);
                    label();
                }
                break;
                case 13:
                    enterOuterAlt(_localctx, 13);
                {
                    setState(133);
                    setxy();
                }
                break;
                case 14:
                    enterOuterAlt(_localctx, 14);
                {
                    setState(134);
                    make();
                }
                break;
                case 15:
                    enterOuterAlt(_localctx, 15);
                {
                    setState(135);
                    procedureInvocation();
                }
                break;
                case 16:
                    enterOuterAlt(_localctx, 16);
                {
                    setState(136);
                    ife();
                }
                break;
                case 17:
                    enterOuterAlt(_localctx, 17);
                {
                    setState(137);
                    stop();
                }
                break;
                case 18:
                    enterOuterAlt(_localctx, 18);
                {
                    setState(138);
                    fore();
                }
                break;
                case 19:
                    enterOuterAlt(_localctx, 19);
                {
                    setState(139);
                    arc();
                }
                break;
                case 20:
                    enterOuterAlt(_localctx, 20);
                {
                    setState(140);
                    setpensize();
                }
                break;
                case 21:
                    enterOuterAlt(_localctx, 21);
                {
                    setState(141);
                    setpencolor();
                }
                break;
                case 22:
                    enterOuterAlt(_localctx, 22);
                {
                    setState(142);
                    fill();
                }
                break;
                case 23:
                    enterOuterAlt(_localctx, 23);
                {
                    setState(143);
                    setbg();
                }
                break;
                case 24:
                    enterOuterAlt(_localctx, 24);
                {
                    setState(144);
                    settextsize();
                }
                break;
                case 25:
                    enterOuterAlt(_localctx, 25);
                {
                    setState(145);
                    sety();
                }
                break;
                case 26:
                    enterOuterAlt(_localctx, 26);
                {
                    setState(146);
                    setx();
                }
                break;
                case 27:
                    enterOuterAlt(_localctx, 27);
                {
                    setState(147);
                    setcornerrounding();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProcedureInvocationContext extends ParserRuleContext {
        public NameContext name() {
            return getRuleContext(NameContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ProcedureInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_procedureInvocation;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitProcedureInvocation(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProcedureInvocationContext procedureInvocation() throws RecognitionException {
        ProcedureInvocationContext _localctx = new ProcedureInvocationContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_procedureInvocation);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(150);
                name();
                setState(154);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6755399441056544L) != 0) || ((((_la - 103)) & ~0x3f) == 0 && ((1L << (_la - 103)) & 195L) != 0)) {
                    {
                        {
                            setState(151);
                            expression();
                        }
                    }
                    setState(156);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ProcedureDeclarationContext extends ParserRuleContext {
        public NameContext name() {
            return getRuleContext(NameContext.class, 0);
        }

        public List<ParameterDeclarationsContext> parameterDeclarations() {
            return getRuleContexts(ParameterDeclarationsContext.class);
        }

        public ParameterDeclarationsContext parameterDeclarations(int i) {
            return getRuleContext(ParameterDeclarationsContext.class, i);
        }

        public List<TerminalNode> EOL() {
            return getTokens(logoParser.EOL);
        }

        public TerminalNode EOL(int i) {
            return getToken(logoParser.EOL, i);
        }

        public List<LineContext> line() {
            return getRuleContexts(LineContext.class);
        }

        public LineContext line(int i) {
            return getRuleContext(LineContext.class, i);
        }

        public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_procedureDeclaration;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitProcedureDeclaration(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
        ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_procedureDeclaration);
        int _la;
        try {
            setState(199);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__0:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(157);
                    match(T__0);
                    setState(158);
                    name();
                    setState(162);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__4) {
                        {
                            {
                                setState(159);
                                parameterDeclarations();
                            }
                        }
                        setState(164);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(166);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 10, _ctx)) {
                        case 1: {
                            setState(165);
                            match(EOL);
                        }
                        break;
                    }
                    setState(172);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(169);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 165476499980287L) != 0)) {
                                    {
                                        setState(168);
                                        line();
                                    }
                                }

                                setState(171);
                                match(EOL);
                            }
                        }
                        setState(174);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 446951476690943L) != 0));
                    setState(176);
                    match(T__1);
                }
                break;
                case T__2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(178);
                    match(T__2);
                    setState(179);
                    name();
                    setState(183);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == T__4) {
                        {
                            {
                                setState(180);
                                parameterDeclarations();
                            }
                        }
                        setState(185);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(187);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 14, _ctx)) {
                        case 1: {
                            setState(186);
                            match(EOL);
                        }
                        break;
                    }
                    setState(193);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    do {
                        {
                            {
                                setState(190);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 165476499980287L) != 0)) {
                                    {
                                        setState(189);
                                        line();
                                    }
                                }

                                setState(192);
                                match(EOL);
                            }
                        }
                        setState(195);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    } while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -33915536475685750L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 446951476690943L) != 0));
                    setState(197);
                    match(T__3);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ParameterDeclarationsContext extends ParserRuleContext {
        public NameContext name() {
            return getRuleContext(NameContext.class, 0);
        }

        public List<ParameterDeclarationsContext> parameterDeclarations() {
            return getRuleContexts(ParameterDeclarationsContext.class);
        }

        public ParameterDeclarationsContext parameterDeclarations(int i) {
            return getRuleContext(ParameterDeclarationsContext.class, i);
        }

        public ParameterDeclarationsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parameterDeclarations;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitParameterDeclarations(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ParameterDeclarationsContext parameterDeclarations() throws RecognitionException {
        ParameterDeclarationsContext _localctx = new ParameterDeclarationsContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_parameterDeclarations);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(201);
                match(T__4);
                setState(202);
                name();
                setState(207);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(203);
                                match(T__5);
                                setState(204);
                                parameterDeclarations();
                            }
                        }
                    }
                    setState(209);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 18, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetcornerroundingContext extends ParserRuleContext {
        public SetcornerroundingContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setcornerrounding;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetcornerrounding(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetcornerroundingContext setcornerrounding() throws RecognitionException {
        SetcornerroundingContext _localctx = new SetcornerroundingContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_setcornerrounding);
        int _la;
        try {
            setState(218);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__6:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(210);
                    match(T__6);
                    setState(211);
                    _la = _input.LA(1);
                    if (!(_la == T__7 || _la == T__8)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case T__9:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(212);
                    match(T__9);
                    setState(213);
                    _la = _input.LA(1);
                    if (!(_la == T__7 || _la == T__8)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case T__10:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(214);
                    match(T__10);
                    setState(215);
                    _la = _input.LA(1);
                    if (!(_la == T__7 || _la == T__8)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                case T__11:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(216);
                    match(T__11);
                    setState(217);
                    _la = _input.LA(1);
                    if (!(_la == T__7 || _la == T__8)) {
                        _errHandler.recoverInline(this);
                    } else {
                        if (_input.LA(1) == Token.EOF) matchedEOF = true;
                        _errHandler.reportMatch(this);
                        consume();
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetxContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public SetxContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setx;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetx(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetxContext setx() throws RecognitionException {
        SetxContext _localctx = new SetxContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_setx);
        try {
            setState(224);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__12:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(220);
                    match(T__12);
                    setState(221);
                    expression();
                }
                break;
                case T__13:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(222);
                    match(T__13);
                    setState(223);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetyContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public SetyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_sety;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSety(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetyContext sety() throws RecognitionException {
        SetyContext _localctx = new SetyContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_sety);
        try {
            setState(230);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__14:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(226);
                    match(T__14);
                    setState(227);
                    expression();
                }
                break;
                case T__15:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(228);
                    match(T__15);
                    setState(229);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SettextsizeContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public SettextsizeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_settextsize;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSettextsize(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SettextsizeContext settextsize() throws RecognitionException {
        SettextsizeContext _localctx = new SettextsizeContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_settextsize);
        try {
            setState(240);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__16:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(232);
                    match(T__16);
                    setState(233);
                    expression();
                }
                break;
                case T__17:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(234);
                    match(T__17);
                    setState(235);
                    expression();
                }
                break;
                case T__18:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(236);
                    match(T__18);
                    setState(237);
                    expression();
                }
                break;
                case T__19:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(238);
                    match(T__19);
                    setState(239);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetbgContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public SetbgContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setbg;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetbg(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetbgContext setbg() throws RecognitionException {
        SetbgContext _localctx = new SetbgContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_setbg);
        try {
            setState(250);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__20:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(242);
                    match(T__20);
                    setState(243);
                    expression();
                }
                break;
                case T__21:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(244);
                    match(T__21);
                    setState(245);
                    expression();
                }
                break;
                case T__22:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(246);
                    match(T__22);
                    setState(247);
                    expression();
                }
                break;
                case T__23:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(248);
                    match(T__23);
                    setState(249);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FillContext extends ParserRuleContext {
        public FillContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fill;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitFill(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FillContext fill() throws RecognitionException {
        FillContext _localctx = new FillContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_fill);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(252);
                _la = _input.LA(1);
                if (!(_la == T__24 || _la == T__25)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetpencolorContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public List<NumberContext> number() {
            return getRuleContexts(NumberContext.class);
        }

        public NumberContext number(int i) {
            return getRuleContext(NumberContext.class, i);
        }

        public SetpencolorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setpencolor;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetpencolor(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetpencolorContext setpencolor() throws RecognitionException {
        SetpencolorContext _localctx = new SetpencolorContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_setpencolor);
        try {
            setState(290);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 24, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(254);
                    match(T__26);
                    setState(255);
                    expression();
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(256);
                    match(T__26);
                    setState(257);
                    match(T__27);
                    setState(258);
                    number();
                    setState(259);
                    number();
                    setState(260);
                    number();
                    setState(261);
                    match(T__28);
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(263);
                    match(T__29);
                    setState(264);
                    expression();
                }
                break;
                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(265);
                    match(T__29);
                    setState(266);
                    match(T__27);
                    setState(267);
                    number();
                    setState(268);
                    number();
                    setState(269);
                    number();
                    setState(270);
                    match(T__28);
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(272);
                    match(T__30);
                    setState(273);
                    expression();
                }
                break;
                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(274);
                    match(T__30);
                    setState(275);
                    match(T__27);
                    setState(276);
                    number();
                    setState(277);
                    number();
                    setState(278);
                    number();
                    setState(279);
                    match(T__28);
                }
                break;
                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(281);
                    match(T__31);
                    setState(282);
                    expression();
                }
                break;
                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(283);
                    match(T__31);
                    setState(284);
                    match(T__27);
                    setState(285);
                    number();
                    setState(286);
                    number();
                    setState(287);
                    number();
                    setState(288);
                    match(T__28);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetpensizeContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public SetpensizeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setpensize;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetpensize(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetpensizeContext setpensize() throws RecognitionException {
        SetpensizeContext _localctx = new SetpensizeContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_setpensize);
        try {
            setState(300);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__32:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(292);
                    match(T__32);
                    setState(293);
                    expression();
                }
                break;
                case T__33:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(294);
                    match(T__33);
                    setState(295);
                    expression();
                }
                break;
                case T__18:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(296);
                    match(T__18);
                    setState(297);
                    expression();
                }
                break;
                case T__19:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(298);
                    match(T__19);
                    setState(299);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ArcContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ArcContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arc;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitArc(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ArcContext arc() throws RecognitionException {
        ArcContext _localctx = new ArcContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_arc);
        try {
            setState(310);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__34:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(302);
                    match(T__34);
                    setState(303);
                    expression();
                    setState(304);
                    expression();
                }
                break;
                case T__35:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(306);
                    match(T__35);
                    setState(307);
                    expression();
                    setState(308);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class Func_Context extends ParserRuleContext {
        public RandomContext random() {
            return getRuleContext(RandomContext.class, 0);
        }

        public Func_Context(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_func_;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitFunc_(this);
            else return visitor.visitChildren(this);
        }
    }

    public final Func_Context func_() throws RecognitionException {
        Func_Context _localctx = new Func_Context(_ctx, getState());
        enterRule(_localctx, 30, RULE_func_);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(312);
                random();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class Repeat_Context extends ParserRuleContext {
        public NumberContext number() {
            return getRuleContext(NumberContext.class, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public Repeat_Context(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_repeat_;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitRepeat_(this);
            else return visitor.visitChildren(this);
        }
    }

    public final Repeat_Context repeat_() throws RecognitionException {
        Repeat_Context _localctx = new Repeat_Context(_ctx, getState());
        enterRule(_localctx, 32, RULE_repeat_);
        try {
            setState(322);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__36:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(314);
                    match(T__36);
                    setState(315);
                    number();
                    setState(316);
                    block();
                }
                break;
                case T__37:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(318);
                    match(T__37);
                    setState(319);
                    number();
                    setState(320);
                    block();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BlockContext extends ParserRuleContext {
        public List<CmdContext> cmd() {
            return getRuleContexts(CmdContext.class);
        }

        public CmdContext cmd(int i) {
            return getRuleContext(CmdContext.class, i);
        }

        public List<TerminalNode> WS() {
            return getTokens(logoParser.WS);
        }

        public TerminalNode WS(int i) {
            return getToken(logoParser.WS, i);
        }

        public List<TerminalNode> EOL() {
            return getTokens(logoParser.EOL);
        }

        public TerminalNode EOL(int i) {
            return getToken(logoParser.EOL, i);
        }

        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitBlock(this);
            else return visitor.visitChildren(this);
        }
    }

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_block);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(324);
                match(T__27);
                setState(340);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -35604386335949696L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 869163941756927L) != 0)) {
                    {
                        {
                            setState(328);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            while (_la == EOL || _la == WS) {
                                {
                                    {
                                        setState(325);
                                        _la = _input.LA(1);
                                        if (!(_la == EOL || _la == WS)) {
                                            _errHandler.recoverInline(this);
                                        } else {
                                            if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                            _errHandler.reportMatch(this);
                                            consume();
                                        }
                                    }
                                }
                                setState(330);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            }
                            setState(331);
                            cmd();
                            setState(335);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 29, _ctx);
                            while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                                if (_alt == 1) {
                                    {
                                        {
                                            setState(332);
                                            _la = _input.LA(1);
                                            if (!(_la == EOL || _la == WS)) {
                                                _errHandler.recoverInline(this);
                                            } else {
                                                if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                                _errHandler.reportMatch(this);
                                                consume();
                                            }
                                        }
                                    }
                                }
                                setState(337);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input, 29, _ctx);
                            }
                        }
                    }
                    setState(342);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(343);
                match(T__28);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class IfeContext extends ParserRuleContext {
        public ComparisonContext comparison() {
            return getRuleContext(ComparisonContext.class, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public IfeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_ife;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitIfe(this);
            else return visitor.visitChildren(this);
        }
    }

    public final IfeContext ife() throws RecognitionException {
        IfeContext _localctx = new IfeContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_ife);
        try {
            setState(353);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__38:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(345);
                    match(T__38);
                    setState(346);
                    comparison();
                    setState(347);
                    block();
                }
                break;
                case T__39:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(349);
                    match(T__39);
                    setState(350);
                    comparison();
                    setState(351);
                    block();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ComparisonContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ComparisonOperatorContext comparisonOperator() {
            return getRuleContext(ComparisonOperatorContext.class, 0);
        }

        public ComparisonContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparison;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitComparison(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ComparisonContext comparison() throws RecognitionException {
        ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_comparison);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(355);
                expression();
                setState(356);
                comparisonOperator();
                setState(357);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ComparisonOperatorContext extends ParserRuleContext {
        public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comparisonOperator;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitComparisonOperator(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
        ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_comparisonOperator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(359);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 138538465099776L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MakeContext extends ParserRuleContext {
        public TerminalNode STRINGLITERAL() {
            return getToken(logoParser.STRINGLITERAL, 0);
        }

        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public MakeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_make;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitMake(this);
            else return visitor.visitChildren(this);
        }
    }

    public final MakeContext make() throws RecognitionException {
        MakeContext _localctx = new MakeContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_make);
        try {
            setState(367);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__46:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(361);
                    match(T__46);
                    setState(362);
                    match(STRINGLITERAL);
                    setState(363);
                    value();
                }
                break;
                case T__47:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(364);
                    match(T__47);
                    setState(365);
                    match(STRINGLITERAL);
                    setState(366);
                    value();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class Print_Context extends ParserRuleContext {
        public ValueContext value() {
            return getRuleContext(ValueContext.class, 0);
        }

        public TerminalNode STRINGLITERAL() {
            return getToken(logoParser.STRINGLITERAL, 0);
        }

        public Print_Context(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_print_;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitPrint_(this);
            else return visitor.visitChildren(this);
        }
    }

    public final Print_Context print_() throws RecognitionException {
        Print_Context _localctx = new Print_Context(_ctx, getState());
        enterRule(_localctx, 44, RULE_print_);
        try {
            setState(379);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__48:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(369);
                    match(T__48);
                    setState(372);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 33, _ctx)) {
                        case 1: {
                            setState(370);
                            value();
                        }
                        break;
                        case 2: {
                            setState(371);
                            match(STRINGLITERAL);
                        }
                        break;
                    }
                }
                break;
                case T__49:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(374);
                    match(T__49);
                    setState(377);
                    _errHandler.sync(this);
                    switch (getInterpreter().adaptivePredict(_input, 34, _ctx)) {
                        case 1: {
                            setState(375);
                            value();
                        }
                        break;
                        case 2: {
                            setState(376);
                            match(STRINGLITERAL);
                        }
                        break;
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NameContext extends ParserRuleContext {
        public TerminalNode STRING() {
            return getToken(logoParser.STRING, 0);
        }

        public NameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_name;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitName(this);
            else return visitor.visitChildren(this);
        }
    }

    public final NameContext name() throws RecognitionException {
        NameContext _localctx = new NameContext(_ctx, getState());
        enterRule(_localctx, 46, RULE_name);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(381);
                match(STRING);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ValueContext extends ParserRuleContext {
        public TerminalNode STRINGLITERAL() {
            return getToken(logoParser.STRINGLITERAL, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public DerefContext deref() {
            return getRuleContext(DerefContext.class, 0);
        }

        public ValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_value;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitValue(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ValueContext value() throws RecognitionException {
        ValueContext _localctx = new ValueContext(_ctx, getState());
        enterRule(_localctx, 48, RULE_value);
        try {
            setState(386);
            _errHandler.sync(this);
            switch (getInterpreter().adaptivePredict(_input, 36, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(383);
                    match(STRINGLITERAL);
                }
                break;
                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(384);
                    expression();
                }
                break;
                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(385);
                    deref();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SignExpressionContext extends ParserRuleContext {
        public NumberContext number() {
            return getRuleContext(NumberContext.class, 0);
        }

        public DerefContext deref() {
            return getRuleContext(DerefContext.class, 0);
        }

        public Func_Context func_() {
            return getRuleContext(Func_Context.class, 0);
        }

        public SignExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_signExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSignExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SignExpressionContext signExpression() throws RecognitionException {
        SignExpressionContext _localctx = new SignExpressionContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_signExpression);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(391);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == T__50 || _la == T__51) {
                    {
                        {
                            setState(388);
                            _la = _input.LA(1);
                            if (!(_la == T__50 || _la == T__51)) {
                                _errHandler.recoverInline(this);
                            } else {
                                if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                _errHandler.reportMatch(this);
                                consume();
                            }
                        }
                    }
                    setState(393);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(397);
                _errHandler.sync(this);
                switch (_input.LA(1)) {
                    case T__7:
                    case T__8:
                    case NUMBER:
                    case FLOAT: {
                        setState(394);
                        number();
                    }
                    break;
                    case T__4: {
                        setState(395);
                        deref();
                    }
                    break;
                    case T__102:
                    case T__103: {
                        setState(396);
                        func_();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class MultiplyingExpressionContext extends ParserRuleContext {
        public List<SignExpressionContext> signExpression() {
            return getRuleContexts(SignExpressionContext.class);
        }

        public SignExpressionContext signExpression(int i) {
            return getRuleContext(SignExpressionContext.class, i);
        }

        public MultiplyingExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_multiplyingExpression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitMultiplyingExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public final MultiplyingExpressionContext multiplyingExpression() throws RecognitionException {
        MultiplyingExpressionContext _localctx = new MultiplyingExpressionContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_multiplyingExpression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(399);
                signExpression();
                setState(404);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 39, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(400);
                                _la = _input.LA(1);
                                if (!(_la == T__52 || _la == T__53)) {
                                    _errHandler.recoverInline(this);
                                } else {
                                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                    _errHandler.reportMatch(this);
                                    consume();
                                }
                                setState(401);
                                signExpression();
                            }
                        }
                    }
                    setState(406);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 39, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ExpressionContext extends ParserRuleContext {
        public List<MultiplyingExpressionContext> multiplyingExpression() {
            return getRuleContexts(MultiplyingExpressionContext.class);
        }

        public MultiplyingExpressionContext multiplyingExpression(int i) {
            return getRuleContext(MultiplyingExpressionContext.class, i);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitExpression(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ExpressionContext expression() throws RecognitionException {
        ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_expression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(407);
                multiplyingExpression();
                setState(412);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 40, _ctx);
                while (_alt != 2 && _alt != org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER) {
                    if (_alt == 1) {
                        {
                            {
                                setState(408);
                                _la = _input.LA(1);
                                if (!(_la == T__50 || _la == T__51)) {
                                    _errHandler.recoverInline(this);
                                } else {
                                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                                    _errHandler.reportMatch(this);
                                    consume();
                                }
                                setState(409);
                                multiplyingExpression();
                            }
                        }
                    }
                    setState(414);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 40, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class DerefContext extends ParserRuleContext {
        public NameContext name() {
            return getRuleContext(NameContext.class, 0);
        }

        public DerefContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_deref;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitDeref(this);
            else return visitor.visitChildren(this);
        }
    }

    public final DerefContext deref() throws RecognitionException {
        DerefContext _localctx = new DerefContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_deref);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(415);
                match(T__4);
                setState(416);
                name();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class FdContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public FdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fd;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitFd(this);
            else return visitor.visitChildren(this);
        }
    }

    public final FdContext fd() throws RecognitionException {
        FdContext _localctx = new FdContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_fd);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(418);
                _la = _input.LA(1);
                if (!((((_la) & ~0x3f) == 0 && ((1L << _la) & 540431955284459520L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(419);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class BkContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public BkContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bk;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitBk(this);
            else return visitor.visitChildren(this);
        }
    }

    public final BkContext bk() throws RecognitionException {
        BkContext _localctx = new BkContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_bk);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(421);
                _la = _input.LA(1);
                if (!(((((_la - 59)) & ~0x3f) == 0 && ((1L << (_la - 59)) & 63L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(422);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RtContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public RtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_rt;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitRt(this);
            else return visitor.visitChildren(this);
        }
    }

    public final RtContext rt() throws RecognitionException {
        RtContext _localctx = new RtContext(_ctx, getState());
        enterRule(_localctx, 62, RULE_rt);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(424);
                _la = _input.LA(1);
                if (!(((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(425);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LtContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public LtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_lt;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitLt(this);
            else return visitor.visitChildren(this);
        }
    }

    public final LtContext lt() throws RecognitionException {
        LtContext _localctx = new LtContext(_ctx, getState());
        enterRule(_localctx, 64, RULE_lt);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(427);
                _la = _input.LA(1);
                if (!(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
                setState(428);
                expression();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CsContext extends ParserRuleContext {
        public CsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_cs;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitCs(this);
            else return visitor.visitChildren(this);
        }
    }

    public final CsContext cs() throws RecognitionException {
        CsContext _localctx = new CsContext(_ctx, getState());
        enterRule(_localctx, 66, RULE_cs);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(430);
                _la = _input.LA(1);
                if (!(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PuContext extends ParserRuleContext {
        public PuContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pu;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitPu(this);
            else return visitor.visitChildren(this);
        }
    }

    public final PuContext pu() throws RecognitionException {
        PuContext _localctx = new PuContext(_ctx, getState());
        enterRule(_localctx, 68, RULE_pu);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(432);
                _la = _input.LA(1);
                if (!(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class PdContext extends ParserRuleContext {
        public PdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_pd;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitPd(this);
            else return visitor.visitChildren(this);
        }
    }

    public final PdContext pd() throws RecognitionException {
        PdContext _localctx = new PdContext(_ctx, getState());
        enterRule(_localctx, 70, RULE_pd);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(434);
                _la = _input.LA(1);
                if (!(((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HtContext extends ParserRuleContext {
        public HtContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_ht;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitHt(this);
            else return visitor.visitChildren(this);
        }
    }

    public final HtContext ht() throws RecognitionException {
        HtContext _localctx = new HtContext(_ctx, getState());
        enterRule(_localctx, 72, RULE_ht);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(436);
                _la = _input.LA(1);
                if (!(((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StContext extends ParserRuleContext {
        public StContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_st;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSt(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StContext st() throws RecognitionException {
        StContext _localctx = new StContext(_ctx, getState());
        enterRule(_localctx, 74, RULE_st);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(438);
                _la = _input.LA(1);
                if (!(((((_la - 89)) & ~0x3f) == 0 && ((1L << (_la - 89)) & 15L) != 0))) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class HomeContext extends ParserRuleContext {
        public HomeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_home;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitHome(this);
            else return visitor.visitChildren(this);
        }
    }

    public final HomeContext home() throws RecognitionException {
        HomeContext _localctx = new HomeContext(_ctx, getState());
        enterRule(_localctx, 76, RULE_home);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(440);
                _la = _input.LA(1);
                if (!(_la == T__92 || _la == T__93)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class StopContext extends ParserRuleContext {
        public StopContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_stop;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitStop(this);
            else return visitor.visitChildren(this);
        }
    }

    public final StopContext stop() throws RecognitionException {
        StopContext _localctx = new StopContext(_ctx, getState());
        enterRule(_localctx, 78, RULE_stop);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(442);
                _la = _input.LA(1);
                if (!(_la == T__94 || _la == T__95)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class LabelContext extends ParserRuleContext {
        public TerminalNode STRINGLITERAL() {
            return getToken(logoParser.STRINGLITERAL, 0);
        }

        public DerefContext deref() {
            return getRuleContext(DerefContext.class, 0);
        }

        public LabelContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_label;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitLabel(this);
            else return visitor.visitChildren(this);
        }
    }

    public final LabelContext label() throws RecognitionException {
        LabelContext _localctx = new LabelContext(_ctx, getState());
        enterRule(_localctx, 80, RULE_label);
        try {
            setState(454);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__96:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(444);
                    match(T__96);
                    setState(447);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case STRINGLITERAL: {
                            setState(445);
                            match(STRINGLITERAL);
                        }
                        break;
                        case T__4: {
                            setState(446);
                            deref();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
                case T__97:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(449);
                    match(T__97);
                    setState(452);
                    _errHandler.sync(this);
                    switch (_input.LA(1)) {
                        case STRINGLITERAL: {
                            setState(450);
                            match(STRINGLITERAL);
                        }
                        break;
                        case T__4: {
                            setState(451);
                            deref();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class SetxyContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public SetxyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_setxy;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitSetxy(this);
            else return visitor.visitChildren(this);
        }
    }

    public final SetxyContext setxy() throws RecognitionException {
        SetxyContext _localctx = new SetxyContext(_ctx, getState());
        enterRule(_localctx, 82, RULE_setxy);
        try {
            setState(472);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__98:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(456);
                    match(T__98);
                    setState(457);
                    expression();
                    setState(458);
                    expression();
                }
                break;
                case T__99:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(460);
                    match(T__99);
                    setState(461);
                    expression();
                    setState(462);
                    expression();
                }
                break;
                case T__100:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(464);
                    match(T__100);
                    setState(465);
                    expression();
                    setState(466);
                    expression();
                }
                break;
                case T__101:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(468);
                    match(T__101);
                    setState(469);
                    expression();
                    setState(470);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class RandomContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public RandomContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_random;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitRandom(this);
            else return visitor.visitChildren(this);
        }
    }

    public final RandomContext random() throws RecognitionException {
        RandomContext _localctx = new RandomContext(_ctx, getState());
        enterRule(_localctx, 84, RULE_random);
        try {
            setState(478);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__102:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(474);
                    match(T__102);
                    setState(475);
                    expression();
                }
                break;
                case T__103:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(476);
                    match(T__103);
                    setState(477);
                    expression();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class ForeContext extends ParserRuleContext {
        public NameContext name() {
            return getRuleContext(NameContext.class, 0);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public ForeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fore;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitFore(this);
            else return visitor.visitChildren(this);
        }
    }

    public final ForeContext fore() throws RecognitionException {
        ForeContext _localctx = new ForeContext(_ctx, getState());
        enterRule(_localctx, 86, RULE_fore);
        try {
            setState(498);
            _errHandler.sync(this);
            switch (_input.LA(1)) {
                case T__104:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(480);
                    match(T__104);
                    setState(481);
                    match(T__27);
                    setState(482);
                    name();
                    setState(483);
                    expression();
                    setState(484);
                    expression();
                    setState(485);
                    expression();
                    setState(486);
                    match(T__28);
                    setState(487);
                    block();
                }
                break;
                case T__105:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(489);
                    match(T__105);
                    setState(490);
                    match(T__27);
                    setState(491);
                    name();
                    setState(492);
                    expression();
                    setState(493);
                    expression();
                    setState(494);
                    expression();
                    setState(495);
                    match(T__28);
                    setState(496);
                    block();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class NumberContext extends ParserRuleContext {
        public TerminalNode NUMBER() {
            return getToken(logoParser.NUMBER, 0);
        }

        public TerminalNode FLOAT() {
            return getToken(logoParser.FLOAT, 0);
        }

        public NumberContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_number;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitNumber(this);
            else return visitor.visitChildren(this);
        }
    }

    public final NumberContext number() throws RecognitionException {
        NumberContext _localctx = new NumberContext(_ctx, getState());
        enterRule(_localctx, 88, RULE_number);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(500);
                _la = _input.LA(1);
                if (!(_la == T__7 || _la == T__8 || _la == NUMBER || _la == FLOAT)) {
                    _errHandler.recoverInline(this);
                } else {
                    if (_input.LA(1) == Token.EOF) matchedEOF = true;
                    _errHandler.reportMatch(this);
                    consume();
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    @SuppressWarnings("CheckReturnValue")
    public static class CommentContext extends ParserRuleContext {
        public TerminalNode COMMENT() {
            return getToken(logoParser.COMMENT, 0);
        }

        public CommentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_comment;
        }

        @Override
        public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
            if (visitor instanceof logoVisitor)
                return ((logoVisitor<? extends T>) visitor).visitComment(this);
            else return visitor.visitChildren(this);
        }
    }

    public final CommentContext comment() throws RecognitionException {
        CommentContext _localctx = new CommentContext(_ctx, getState());
        enterRule(_localctx, 90, RULE_comment);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(502);
                match(COMMENT);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static final String _serializedATN =
            "\u0004\u0001q\u01f9\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002" +
                    "\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002" +
                    "\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002" +
                    "\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002" +
                    "\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f" +
                    "\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012" +
                    "\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015" +
                    "\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018" +
                    "\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b" +
                    "\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e" +
                    "\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002" +
                    "#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002" +
                    "(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002" +
                    "-\u0007-\u0001\u0000\u0003\u0000^\b\u0000\u0001\u0000\u0004\u0000a\b\u0000" +
                    "\u000b\u0000\f\u0000b\u0001\u0000\u0003\u0000f\b\u0000\u0001\u0000\u0001" +
                    "\u0000\u0001\u0001\u0004\u0001k\b\u0001\u000b\u0001\f\u0001l\u0001\u0001" +
                    "\u0003\u0001p\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001" +
                    "u\b\u0001\u0001\u0001\u0003\u0001x\b\u0001\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001" +
                    "\u0002\u0003\u0002\u0095\b\u0002\u0001\u0003\u0001\u0003\u0005\u0003\u0099" +
                    "\b\u0003\n\u0003\f\u0003\u009c\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004" +
                    "\u0005\u0004\u00a1\b\u0004\n\u0004\f\u0004\u00a4\t\u0004\u0001\u0004\u0003" +
                    "\u0004\u00a7\b\u0004\u0001\u0004\u0003\u0004\u00aa\b\u0004\u0001\u0004" +
                    "\u0004\u0004\u00ad\b\u0004\u000b\u0004\f\u0004\u00ae\u0001\u0004\u0001" +
                    "\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00b6\b\u0004\n" +
                    "\u0004\f\u0004\u00b9\t\u0004\u0001\u0004\u0003\u0004\u00bc\b\u0004\u0001" +
                    "\u0004\u0003\u0004\u00bf\b\u0004\u0001\u0004\u0004\u0004\u00c2\b\u0004" +
                    "\u000b\u0004\f\u0004\u00c3\u0001\u0004\u0001\u0004\u0003\u0004\u00c8\b" +
                    "\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0005\u0005\u00ce" +
                    "\b\u0005\n\u0005\f\u0005\u00d1\t\u0005\u0001\u0006\u0001\u0006\u0001\u0006" +
                    "\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006" +
                    "\u00db\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007" +
                    "\u00e1\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00e7\b\b\u0001" +
                    "\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u00f1" +
                    "\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003" +
                    "\n\u00fb\b\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001" +
                    "\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u0123\b\f\u0001\r\u0001\r\u0001" +
                    "\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u012d\b\r\u0001\u000e" +
                    "\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e" +
                    "\u0001\u000e\u0003\u000e\u0137\b\u000e\u0001\u000f\u0001\u000f\u0001\u0010" +
                    "\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010" +
                    "\u0001\u0010\u0003\u0010\u0143\b\u0010\u0001\u0011\u0001\u0011\u0005\u0011" +
                    "\u0147\b\u0011\n\u0011\f\u0011\u014a\t\u0011\u0001\u0011\u0001\u0011\u0005" +
                    "\u0011\u014e\b\u0011\n\u0011\f\u0011\u0151\t\u0011\u0005\u0011\u0153\b" +
                    "\u0011\n\u0011\f\u0011\u0156\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012" +
                    "\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012" +
                    "\u0001\u0012\u0003\u0012\u0162\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013" +
                    "\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015" +
                    "\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u0170\b\u0015\u0001\u0016" +
                    "\u0001\u0016\u0001\u0016\u0003\u0016\u0175\b\u0016\u0001\u0016\u0001\u0016" +
                    "\u0001\u0016\u0003\u0016\u017a\b\u0016\u0003\u0016\u017c\b\u0016\u0001" +
                    "\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u0183" +
                    "\b\u0018\u0001\u0019\u0005\u0019\u0186\b\u0019\n\u0019\f\u0019\u0189\t" +
                    "\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u018e\b\u0019\u0001" +
                    "\u001a\u0001\u001a\u0001\u001a\u0005\u001a\u0193\b\u001a\n\u001a\f\u001a" +
                    "\u0196\t\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0005\u001b\u019b\b" +
                    "\u001b\n\u001b\f\u001b\u019e\t\u001b\u0001\u001c\u0001\u001c\u0001\u001c" +
                    "\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e" +
                    "\u0001\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001!\u0001" +
                    "!\u0001\"\u0001\"\u0001#\u0001#\u0001$\u0001$\u0001%\u0001%\u0001&\u0001" +
                    "&\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0003(\u01c0\b(\u0001(\u0001(\u0001" +
                    "(\u0003(\u01c5\b(\u0003(\u01c7\b(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001" +
                    ")\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001" +
                    ")\u0003)\u01d9\b)\u0001*\u0001*\u0001*\u0001*\u0003*\u01df\b*\u0001+\u0001" +
                    "+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001" +
                    "+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u01f3\b+\u0001,\u0001" +
                    ",\u0001-\u0001-\u0001-\u0000\u0000.\u0000\u0002\u0004\u0006\b\n\f\u000e" +
                    "\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF" +
                    "HJLNPRTVXZ\u0000\u0012\u0001\u0000\b\t\u0001\u0000\u0019\u001a\u0001\u0000" +
                    "pq\u0001\u0000).\u0001\u000034\u0001\u000056\u0001\u00007:\u0001\u0000" +
                    ";@\u0001\u0000AD\u0001\u0000EH\u0001\u0000IL\u0001\u0000MP\u0001\u0000" +
                    "QT\u0001\u0000UX\u0001\u0000Y\\\u0001\u0000]^\u0001\u0000_`\u0002\u0000" +
                    "\b\tmn\u0226\u0000`\u0001\u0000\u0000\u0000\u0002w\u0001\u0000\u0000\u0000" +
                    "\u0004\u0094\u0001\u0000\u0000\u0000\u0006\u0096\u0001\u0000\u0000\u0000" +
                    "\b\u00c7\u0001\u0000\u0000\u0000\n\u00c9\u0001\u0000\u0000\u0000\f\u00da" +
                    "\u0001\u0000\u0000\u0000\u000e\u00e0\u0001\u0000\u0000\u0000\u0010\u00e6" +
                    "\u0001\u0000\u0000\u0000\u0012\u00f0\u0001\u0000\u0000\u0000\u0014\u00fa" +
                    "\u0001\u0000\u0000\u0000\u0016\u00fc\u0001\u0000\u0000\u0000\u0018\u0122" +
                    "\u0001\u0000\u0000\u0000\u001a\u012c\u0001\u0000\u0000\u0000\u001c\u0136" +
                    "\u0001\u0000\u0000\u0000\u001e\u0138\u0001\u0000\u0000\u0000 \u0142\u0001" +
                    "\u0000\u0000\u0000\"\u0144\u0001\u0000\u0000\u0000$\u0161\u0001\u0000" +
                    "\u0000\u0000&\u0163\u0001\u0000\u0000\u0000(\u0167\u0001\u0000\u0000\u0000" +
                    "*\u016f\u0001\u0000\u0000\u0000,\u017b\u0001\u0000\u0000\u0000.\u017d" +
                    "\u0001\u0000\u0000\u00000\u0182\u0001\u0000\u0000\u00002\u0187\u0001\u0000" +
                    "\u0000\u00004\u018f\u0001\u0000\u0000\u00006\u0197\u0001\u0000\u0000\u0000" +
                    "8\u019f\u0001\u0000\u0000\u0000:\u01a2\u0001\u0000\u0000\u0000<\u01a5" +
                    "\u0001\u0000\u0000\u0000>\u01a8\u0001\u0000\u0000\u0000@\u01ab\u0001\u0000" +
                    "\u0000\u0000B\u01ae\u0001\u0000\u0000\u0000D\u01b0\u0001\u0000\u0000\u0000" +
                    "F\u01b2\u0001\u0000\u0000\u0000H\u01b4\u0001\u0000\u0000\u0000J\u01b6" +
                    "\u0001\u0000\u0000\u0000L\u01b8\u0001\u0000\u0000\u0000N\u01ba\u0001\u0000" +
                    "\u0000\u0000P\u01c6\u0001\u0000\u0000\u0000R\u01d8\u0001\u0000\u0000\u0000" +
                    "T\u01de\u0001\u0000\u0000\u0000V\u01f2\u0001\u0000\u0000\u0000X\u01f4" +
                    "\u0001\u0000\u0000\u0000Z\u01f6\u0001\u0000\u0000\u0000\\^\u0003\u0002" +
                    "\u0001\u0000]\\\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001" +
                    "\u0000\u0000\u0000_a\u0005p\u0000\u0000`]\u0001\u0000\u0000\u0000ab\u0001" +
                    "\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000" +
                    "ce\u0001\u0000\u0000\u0000df\u0003\u0002\u0001\u0000ed\u0001\u0000\u0000" +
                    "\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005\u0000" +
                    "\u0000\u0001h\u0001\u0001\u0000\u0000\u0000ik\u0003\u0004\u0002\u0000" +
                    "ji\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000" +
                    "\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000np\u0003Z-\u0000" +
                    "on\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000px\u0001\u0000\u0000" +
                    "\u0000qx\u0003Z-\u0000rt\u0003,\u0016\u0000su\u0003Z-\u0000ts\u0001\u0000" +
                    "\u0000\u0000tu\u0001\u0000\u0000\u0000ux\u0001\u0000\u0000\u0000vx\u0003" +
                    "\b\u0004\u0000wj\u0001\u0000\u0000\u0000wq\u0001\u0000\u0000\u0000wr\u0001" +
                    "\u0000\u0000\u0000wv\u0001\u0000\u0000\u0000x\u0003\u0001\u0000\u0000" +
                    "\u0000y\u0095\u0003 \u0010\u0000z\u0095\u0003:\u001d\u0000{\u0095\u0003" +
                    "<\u001e\u0000|\u0095\u0003>\u001f\u0000}\u0095\u0003@ \u0000~\u0095\u0003" +
                    "B!\u0000\u007f\u0095\u0003D\"\u0000\u0080\u0095\u0003F#\u0000\u0081\u0095" +
                    "\u0003H$\u0000\u0082\u0095\u0003J%\u0000\u0083\u0095\u0003L&\u0000\u0084" +
                    "\u0095\u0003P(\u0000\u0085\u0095\u0003R)\u0000\u0086\u0095\u0003*\u0015" +
                    "\u0000\u0087\u0095\u0003\u0006\u0003\u0000\u0088\u0095\u0003$\u0012\u0000" +
                    "\u0089\u0095\u0003N\'\u0000\u008a\u0095\u0003V+\u0000\u008b\u0095\u0003" +
                    "\u001c\u000e\u0000\u008c\u0095\u0003\u001a\r\u0000\u008d\u0095\u0003\u0018" +
                    "\f\u0000\u008e\u0095\u0003\u0016\u000b\u0000\u008f\u0095\u0003\u0014\n" +
                    "\u0000\u0090\u0095\u0003\u0012\t\u0000\u0091\u0095\u0003\u0010\b\u0000" +
                    "\u0092\u0095\u0003\u000e\u0007\u0000\u0093\u0095\u0003\f\u0006\u0000\u0094" +
                    "y\u0001\u0000\u0000\u0000\u0094z\u0001\u0000\u0000\u0000\u0094{\u0001" +
                    "\u0000\u0000\u0000\u0094|\u0001\u0000\u0000\u0000\u0094}\u0001\u0000\u0000" +
                    "\u0000\u0094~\u0001\u0000\u0000\u0000\u0094\u007f\u0001\u0000\u0000\u0000" +
                    "\u0094\u0080\u0001\u0000\u0000\u0000\u0094\u0081\u0001\u0000\u0000\u0000" +
                    "\u0094\u0082\u0001\u0000\u0000\u0000\u0094\u0083\u0001\u0000\u0000\u0000" +
                    "\u0094\u0084\u0001\u0000\u0000\u0000\u0094\u0085\u0001\u0000\u0000\u0000" +
                    "\u0094\u0086\u0001\u0000\u0000\u0000\u0094\u0087\u0001\u0000\u0000\u0000" +
                    "\u0094\u0088\u0001\u0000\u0000\u0000\u0094\u0089\u0001\u0000\u0000\u0000" +
                    "\u0094\u008a\u0001\u0000\u0000\u0000\u0094\u008b\u0001\u0000\u0000\u0000" +
                    "\u0094\u008c\u0001\u0000\u0000\u0000\u0094\u008d\u0001\u0000\u0000\u0000" +
                    "\u0094\u008e\u0001\u0000\u0000\u0000\u0094\u008f\u0001\u0000\u0000\u0000" +
                    "\u0094\u0090\u0001\u0000\u0000\u0000\u0094\u0091\u0001\u0000\u0000\u0000" +
                    "\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0093\u0001\u0000\u0000\u0000" +
                    "\u0095\u0005\u0001\u0000\u0000\u0000\u0096\u009a\u0003.\u0017\u0000\u0097" +
                    "\u0099\u00036\u001b\u0000\u0098\u0097\u0001\u0000\u0000\u0000\u0099\u009c" +
                    "\u0001\u0000\u0000\u0000\u009a\u0098\u0001\u0000\u0000\u0000\u009a\u009b" +
                    "\u0001\u0000\u0000\u0000\u009b\u0007\u0001\u0000\u0000\u0000\u009c\u009a" +
                    "\u0001\u0000\u0000\u0000\u009d\u009e\u0005\u0001\u0000\u0000\u009e\u00a2" +
                    "\u0003.\u0017\u0000\u009f\u00a1\u0003\n\u0005\u0000\u00a0\u009f\u0001" +
                    "\u0000\u0000\u0000\u00a1\u00a4\u0001\u0000\u0000\u0000\u00a2\u00a0\u0001" +
                    "\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00a6\u0001" +
                    "\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000\u0000\u00a5\u00a7\u0005" +
                    "p\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000" +
                    "\u0000\u0000\u00a7\u00ac\u0001\u0000\u0000\u0000\u00a8\u00aa\u0003\u0002" +
                    "\u0001\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa\u0001\u0000" +
                    "\u0000\u0000\u00aa\u00ab\u0001\u0000\u0000\u0000\u00ab\u00ad\u0005p\u0000" +
                    "\u0000\u00ac\u00a9\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000" +
                    "\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000" +
                    "\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005\u0002\u0000" +
                    "\u0000\u00b1\u00c8\u0001\u0000\u0000\u0000\u00b2\u00b3\u0005\u0003\u0000" +
                    "\u0000\u00b3\u00b7\u0003.\u0017\u0000\u00b4\u00b6\u0003\n\u0005\u0000" +
                    "\u00b5\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b9\u0001\u0000\u0000\u0000" +
                    "\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000\u0000" +
                    "\u00b8\u00bb\u0001\u0000\u0000\u0000\u00b9\u00b7\u0001\u0000\u0000\u0000" +
                    "\u00ba\u00bc\u0005p\u0000\u0000\u00bb\u00ba\u0001\u0000\u0000\u0000\u00bb" +
                    "\u00bc\u0001\u0000\u0000\u0000\u00bc\u00c1\u0001\u0000\u0000\u0000\u00bd" +
                    "\u00bf\u0003\u0002\u0001\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00be" +
                    "\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0" +
                    "\u00c2\u0005p\u0000\u0000\u00c1\u00be\u0001\u0000\u0000\u0000\u00c2\u00c3" +
                    "\u0001\u0000\u0000\u0000\u00c3\u00c1\u0001\u0000\u0000\u0000\u00c3\u00c4" +
                    "\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001\u0000\u0000\u0000\u00c5\u00c6" +
                    "\u0005\u0004\u0000\u0000\u00c6\u00c8\u0001\u0000\u0000\u0000\u00c7\u009d" +
                    "\u0001\u0000\u0000\u0000\u00c7\u00b2\u0001\u0000\u0000\u0000\u00c8\t\u0001" +
                    "\u0000\u0000\u0000\u00c9\u00ca\u0005\u0005\u0000\u0000\u00ca\u00cf\u0003" +
                    ".\u0017\u0000\u00cb\u00cc\u0005\u0006\u0000\u0000\u00cc\u00ce\u0003\n" +
                    "\u0005\u0000\u00cd\u00cb\u0001\u0000\u0000\u0000\u00ce\u00d1\u0001\u0000" +
                    "\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000" +
                    "\u0000\u0000\u00d0\u000b\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000" +
                    "\u0000\u0000\u00d2\u00d3\u0005\u0007\u0000\u0000\u00d3\u00db\u0007\u0000" +
                    "\u0000\u0000\u00d4\u00d5\u0005\n\u0000\u0000\u00d5\u00db\u0007\u0000\u0000" +
                    "\u0000\u00d6\u00d7\u0005\u000b\u0000\u0000\u00d7\u00db\u0007\u0000\u0000" +
                    "\u0000\u00d8\u00d9\u0005\f\u0000\u0000\u00d9\u00db\u0007\u0000\u0000\u0000" +
                    "\u00da\u00d2\u0001\u0000\u0000\u0000\u00da\u00d4\u0001\u0000\u0000\u0000" +
                    "\u00da\u00d6\u0001\u0000\u0000\u0000\u00da\u00d8\u0001\u0000\u0000\u0000" +
                    "\u00db\r\u0001\u0000\u0000\u0000\u00dc\u00dd\u0005\r\u0000\u0000\u00dd" +
                    "\u00e1\u00036\u001b\u0000\u00de\u00df\u0005\u000e\u0000\u0000\u00df\u00e1" +
                    "\u00036\u001b\u0000\u00e0\u00dc\u0001\u0000\u0000\u0000\u00e0\u00de\u0001" +
                    "\u0000\u0000\u0000\u00e1\u000f\u0001\u0000\u0000\u0000\u00e2\u00e3\u0005" +
                    "\u000f\u0000\u0000\u00e3\u00e7\u00036\u001b\u0000\u00e4\u00e5\u0005\u0010" +
                    "\u0000\u0000\u00e5\u00e7\u00036\u001b\u0000\u00e6\u00e2\u0001\u0000\u0000" +
                    "\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e7\u0011\u0001\u0000\u0000" +
                    "\u0000\u00e8\u00e9\u0005\u0011\u0000\u0000\u00e9\u00f1\u00036\u001b\u0000" +
                    "\u00ea\u00eb\u0005\u0012\u0000\u0000\u00eb\u00f1\u00036\u001b\u0000\u00ec" +
                    "\u00ed\u0005\u0013\u0000\u0000\u00ed\u00f1\u00036\u001b\u0000\u00ee\u00ef" +
                    "\u0005\u0014\u0000\u0000\u00ef\u00f1\u00036\u001b\u0000\u00f0\u00e8\u0001" +
                    "\u0000\u0000\u0000\u00f0\u00ea\u0001\u0000\u0000\u0000\u00f0\u00ec\u0001" +
                    "\u0000\u0000\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f1\u0013\u0001" +
                    "\u0000\u0000\u0000\u00f2\u00f3\u0005\u0015\u0000\u0000\u00f3\u00fb\u0003" +
                    "6\u001b\u0000\u00f4\u00f5\u0005\u0016\u0000\u0000\u00f5\u00fb\u00036\u001b" +
                    "\u0000\u00f6\u00f7\u0005\u0017\u0000\u0000\u00f7\u00fb\u00036\u001b\u0000" +
                    "\u00f8\u00f9\u0005\u0018\u0000\u0000\u00f9\u00fb\u00036\u001b\u0000\u00fa" +
                    "\u00f2\u0001\u0000\u0000\u0000\u00fa\u00f4\u0001\u0000\u0000\u0000\u00fa" +
                    "\u00f6\u0001\u0000\u0000\u0000\u00fa\u00f8\u0001\u0000\u0000\u0000\u00fb" +
                    "\u0015\u0001\u0000\u0000\u0000\u00fc\u00fd\u0007\u0001\u0000\u0000\u00fd" +
                    "\u0017\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005\u001b\u0000\u0000\u00ff" +
                    "\u0123\u00036\u001b\u0000\u0100\u0101\u0005\u001b\u0000\u0000\u0101\u0102" +
                    "\u0005\u001c\u0000\u0000\u0102\u0103\u0003X,\u0000\u0103\u0104\u0003X" +
                    ",\u0000\u0104\u0105\u0003X,\u0000\u0105\u0106\u0005\u001d\u0000\u0000" +
                    "\u0106\u0123\u0001\u0000\u0000\u0000\u0107\u0108\u0005\u001e\u0000\u0000" +
                    "\u0108\u0123\u00036\u001b\u0000\u0109\u010a\u0005\u001e\u0000\u0000\u010a" +
                    "\u010b\u0005\u001c\u0000\u0000\u010b\u010c\u0003X,\u0000\u010c\u010d\u0003" +
                    "X,\u0000\u010d\u010e\u0003X,\u0000\u010e\u010f\u0005\u001d\u0000\u0000" +
                    "\u010f\u0123\u0001\u0000\u0000\u0000\u0110\u0111\u0005\u001f\u0000\u0000" +
                    "\u0111\u0123\u00036\u001b\u0000\u0112\u0113\u0005\u001f\u0000\u0000\u0113" +
                    "\u0114\u0005\u001c\u0000\u0000\u0114\u0115\u0003X,\u0000\u0115\u0116\u0003" +
                    "X,\u0000\u0116\u0117\u0003X,\u0000\u0117\u0118\u0005\u001d\u0000\u0000" +
                    "\u0118\u0123\u0001\u0000\u0000\u0000\u0119\u011a\u0005 \u0000\u0000\u011a" +
                    "\u0123\u00036\u001b\u0000\u011b\u011c\u0005 \u0000\u0000\u011c\u011d\u0005" +
                    "\u001c\u0000\u0000\u011d\u011e\u0003X,\u0000\u011e\u011f\u0003X,\u0000" +
                    "\u011f\u0120\u0003X,\u0000\u0120\u0121\u0005\u001d\u0000\u0000\u0121\u0123" +
                    "\u0001\u0000\u0000\u0000\u0122\u00fe\u0001\u0000\u0000\u0000\u0122\u0100" +
                    "\u0001\u0000\u0000\u0000\u0122\u0107\u0001\u0000\u0000\u0000\u0122\u0109" +
                    "\u0001\u0000\u0000\u0000\u0122\u0110\u0001\u0000\u0000\u0000\u0122\u0112" +
                    "\u0001\u0000\u0000\u0000\u0122\u0119\u0001\u0000\u0000\u0000\u0122\u011b" +
                    "\u0001\u0000\u0000\u0000\u0123\u0019\u0001\u0000\u0000\u0000\u0124\u0125" +
                    "\u0005!\u0000\u0000\u0125\u012d\u00036\u001b\u0000\u0126\u0127\u0005\"" +
                    "\u0000\u0000\u0127\u012d\u00036\u001b\u0000\u0128\u0129\u0005\u0013\u0000" +
                    "\u0000\u0129\u012d\u00036\u001b\u0000\u012a\u012b\u0005\u0014\u0000\u0000" +
                    "\u012b\u012d\u00036\u001b\u0000\u012c\u0124\u0001\u0000\u0000\u0000\u012c" +
                    "\u0126\u0001\u0000\u0000\u0000\u012c\u0128\u0001\u0000\u0000\u0000\u012c" +
                    "\u012a\u0001\u0000\u0000\u0000\u012d\u001b\u0001\u0000\u0000\u0000\u012e" +
                    "\u012f\u0005#\u0000\u0000\u012f\u0130\u00036\u001b\u0000\u0130\u0131\u0003" +
                    "6\u001b\u0000\u0131\u0137\u0001\u0000\u0000\u0000\u0132\u0133\u0005$\u0000" +
                    "\u0000\u0133\u0134\u00036\u001b\u0000\u0134\u0135\u00036\u001b\u0000\u0135" +
                    "\u0137\u0001\u0000\u0000\u0000\u0136\u012e\u0001\u0000\u0000\u0000\u0136" +
                    "\u0132\u0001\u0000\u0000\u0000\u0137\u001d\u0001\u0000\u0000\u0000\u0138" +
                    "\u0139\u0003T*\u0000\u0139\u001f\u0001\u0000\u0000\u0000\u013a\u013b\u0005" +
                    "%\u0000\u0000\u013b\u013c\u0003X,\u0000\u013c\u013d\u0003\"\u0011\u0000" +
                    "\u013d\u0143\u0001\u0000\u0000\u0000\u013e\u013f\u0005&\u0000\u0000\u013f" +
                    "\u0140\u0003X,\u0000\u0140\u0141\u0003\"\u0011\u0000\u0141\u0143\u0001" +
                    "\u0000\u0000\u0000\u0142\u013a\u0001\u0000\u0000\u0000\u0142\u013e\u0001" +
                    "\u0000\u0000\u0000\u0143!\u0001\u0000\u0000\u0000\u0144\u0154\u0005\u001c" +
                    "\u0000\u0000\u0145\u0147\u0007\u0002\u0000\u0000\u0146\u0145\u0001\u0000" +
                    "\u0000\u0000\u0147\u014a\u0001\u0000\u0000\u0000\u0148\u0146\u0001\u0000" +
                    "\u0000\u0000\u0148\u0149\u0001\u0000\u0000\u0000\u0149\u014b\u0001\u0000" +
                    "\u0000\u0000\u014a\u0148\u0001\u0000\u0000\u0000\u014b\u014f\u0003\u0004" +
                    "\u0002\u0000\u014c\u014e\u0007\u0002\u0000\u0000\u014d\u014c\u0001\u0000" +
                    "\u0000\u0000\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000" +
                    "\u0000\u0000\u014f\u0150\u0001\u0000\u0000\u0000\u0150\u0153\u0001\u0000" +
                    "\u0000\u0000\u0151\u014f\u0001\u0000\u0000\u0000\u0152\u0148\u0001\u0000" +
                    "\u0000\u0000\u0153\u0156\u0001\u0000\u0000\u0000\u0154\u0152\u0001\u0000" +
                    "\u0000\u0000\u0154\u0155\u0001\u0000\u0000\u0000\u0155\u0157\u0001\u0000" +
                    "\u0000\u0000\u0156\u0154\u0001\u0000\u0000\u0000\u0157\u0158\u0005\u001d" +
                    "\u0000\u0000\u0158#\u0001\u0000\u0000\u0000\u0159\u015a\u0005\'\u0000" +
                    "\u0000\u015a\u015b\u0003&\u0013\u0000\u015b\u015c\u0003\"\u0011\u0000" +
                    "\u015c\u0162\u0001\u0000\u0000\u0000\u015d\u015e\u0005(\u0000\u0000\u015e" +
                    "\u015f\u0003&\u0013\u0000\u015f\u0160\u0003\"\u0011\u0000\u0160\u0162" +
                    "\u0001\u0000\u0000\u0000\u0161\u0159\u0001\u0000\u0000\u0000\u0161\u015d" +
                    "\u0001\u0000\u0000\u0000\u0162%\u0001\u0000\u0000\u0000\u0163\u0164\u0003" +
                    "6\u001b\u0000\u0164\u0165\u0003(\u0014\u0000\u0165\u0166\u00036\u001b" +
                    "\u0000\u0166\'\u0001\u0000\u0000\u0000\u0167\u0168\u0007\u0003\u0000\u0000" +
                    "\u0168)\u0001\u0000\u0000\u0000\u0169\u016a\u0005/\u0000\u0000\u016a\u016b" +
                    "\u0005k\u0000\u0000\u016b\u0170\u00030\u0018\u0000\u016c\u016d\u00050" +
                    "\u0000\u0000\u016d\u016e\u0005k\u0000\u0000\u016e\u0170\u00030\u0018\u0000" +
                    "\u016f\u0169\u0001\u0000\u0000\u0000\u016f\u016c\u0001\u0000\u0000\u0000" +
                    "\u0170+\u0001\u0000\u0000\u0000\u0171\u0174\u00051\u0000\u0000\u0172\u0175" +
                    "\u00030\u0018\u0000\u0173\u0175\u0005k\u0000\u0000\u0174\u0172\u0001\u0000" +
                    "\u0000\u0000\u0174\u0173\u0001\u0000\u0000\u0000\u0175\u017c\u0001\u0000" +
                    "\u0000\u0000\u0176\u0179\u00052\u0000\u0000\u0177\u017a\u00030\u0018\u0000" +
                    "\u0178\u017a\u0005k\u0000\u0000\u0179\u0177\u0001\u0000\u0000\u0000\u0179" +
                    "\u0178\u0001\u0000\u0000\u0000\u017a\u017c\u0001\u0000\u0000\u0000\u017b" +
                    "\u0171\u0001\u0000\u0000\u0000\u017b\u0176\u0001\u0000\u0000\u0000\u017c" +
                    "-\u0001\u0000\u0000\u0000\u017d\u017e\u0005l\u0000\u0000\u017e/\u0001" +
                    "\u0000\u0000\u0000\u017f\u0183\u0005k\u0000\u0000\u0180\u0183\u00036\u001b" +
                    "\u0000\u0181\u0183\u00038\u001c\u0000\u0182\u017f\u0001\u0000\u0000\u0000" +
                    "\u0182\u0180\u0001\u0000\u0000\u0000\u0182\u0181\u0001\u0000\u0000\u0000" +
                    "\u01831\u0001\u0000\u0000\u0000\u0184\u0186\u0007\u0004\u0000\u0000\u0185" +
                    "\u0184\u0001\u0000\u0000\u0000\u0186\u0189\u0001\u0000\u0000\u0000\u0187" +
                    "\u0185\u0001\u0000\u0000\u0000\u0187\u0188\u0001\u0000\u0000\u0000\u0188" +
                    "\u018d\u0001\u0000\u0000\u0000\u0189\u0187\u0001\u0000\u0000\u0000\u018a" +
                    "\u018e\u0003X,\u0000\u018b\u018e\u00038\u001c\u0000\u018c\u018e\u0003" +
                    "\u001e\u000f\u0000\u018d\u018a\u0001\u0000\u0000\u0000\u018d\u018b\u0001" +
                    "\u0000\u0000\u0000\u018d\u018c\u0001\u0000\u0000\u0000\u018e3\u0001\u0000" +
                    "\u0000\u0000\u018f\u0194\u00032\u0019\u0000\u0190\u0191\u0007\u0005\u0000" +
                    "\u0000\u0191\u0193\u00032\u0019\u0000\u0192\u0190\u0001\u0000\u0000\u0000" +
                    "\u0193\u0196\u0001\u0000\u0000\u0000\u0194\u0192\u0001\u0000\u0000\u0000" +
                    "\u0194\u0195\u0001\u0000\u0000\u0000\u01955\u0001\u0000\u0000\u0000\u0196" +
                    "\u0194\u0001\u0000\u0000\u0000\u0197\u019c\u00034\u001a\u0000\u0198\u0199" +
                    "\u0007\u0004\u0000\u0000\u0199\u019b\u00034\u001a\u0000\u019a\u0198\u0001" +
                    "\u0000\u0000\u0000\u019b\u019e\u0001\u0000\u0000\u0000\u019c\u019a\u0001" +
                    "\u0000\u0000\u0000\u019c\u019d\u0001\u0000\u0000\u0000\u019d7\u0001\u0000" +
                    "\u0000\u0000\u019e\u019c\u0001\u0000\u0000\u0000\u019f\u01a0\u0005\u0005" +
                    "\u0000\u0000\u01a0\u01a1\u0003.\u0017\u0000\u01a19\u0001\u0000\u0000\u0000" +
                    "\u01a2\u01a3\u0007\u0006\u0000\u0000\u01a3\u01a4\u00036\u001b\u0000\u01a4" +
                    ";\u0001\u0000\u0000\u0000\u01a5\u01a6\u0007\u0007\u0000\u0000\u01a6\u01a7" +
                    "\u00036\u001b\u0000\u01a7=\u0001\u0000\u0000\u0000\u01a8\u01a9\u0007\b" +
                    "\u0000\u0000\u01a9\u01aa\u00036\u001b\u0000\u01aa?\u0001\u0000\u0000\u0000" +
                    "\u01ab\u01ac\u0007\t\u0000\u0000\u01ac\u01ad\u00036\u001b\u0000\u01ad" +
                    "A\u0001\u0000\u0000\u0000\u01ae\u01af\u0007\n\u0000\u0000\u01afC\u0001" +
                    "\u0000\u0000\u0000\u01b0\u01b1\u0007\u000b\u0000\u0000\u01b1E\u0001\u0000" +
                    "\u0000\u0000\u01b2\u01b3\u0007\f\u0000\u0000\u01b3G\u0001\u0000\u0000" +
                    "\u0000\u01b4\u01b5\u0007\r\u0000\u0000\u01b5I\u0001\u0000\u0000\u0000" +
                    "\u01b6\u01b7\u0007\u000e\u0000\u0000\u01b7K\u0001\u0000\u0000\u0000\u01b8" +
                    "\u01b9\u0007\u000f\u0000\u0000\u01b9M\u0001\u0000\u0000\u0000\u01ba\u01bb" +
                    "\u0007\u0010\u0000\u0000\u01bbO\u0001\u0000\u0000\u0000\u01bc\u01bf\u0005" +
                    "a\u0000\u0000\u01bd\u01c0\u0005k\u0000\u0000\u01be\u01c0\u00038\u001c" +
                    "\u0000\u01bf\u01bd\u0001\u0000\u0000\u0000\u01bf\u01be\u0001\u0000\u0000" +
                    "\u0000\u01c0\u01c7\u0001\u0000\u0000\u0000\u01c1\u01c4\u0005b\u0000\u0000" +
                    "\u01c2\u01c5\u0005k\u0000\u0000\u01c3\u01c5\u00038\u001c\u0000\u01c4\u01c2" +
                    "\u0001\u0000\u0000\u0000\u01c4\u01c3\u0001\u0000\u0000\u0000\u01c5\u01c7" +
                    "\u0001\u0000\u0000\u0000\u01c6\u01bc\u0001\u0000\u0000\u0000\u01c6\u01c1" +
                    "\u0001\u0000\u0000\u0000\u01c7Q\u0001\u0000\u0000\u0000\u01c8\u01c9\u0005" +
                    "c\u0000\u0000\u01c9\u01ca\u00036\u001b\u0000\u01ca\u01cb\u00036\u001b" +
                    "\u0000\u01cb\u01d9\u0001\u0000\u0000\u0000\u01cc\u01cd\u0005d\u0000\u0000" +
                    "\u01cd\u01ce\u00036\u001b\u0000\u01ce\u01cf\u00036\u001b\u0000\u01cf\u01d9" +
                    "\u0001\u0000\u0000\u0000\u01d0\u01d1\u0005e\u0000\u0000\u01d1\u01d2\u0003" +
                    "6\u001b\u0000\u01d2\u01d3\u00036\u001b\u0000\u01d3\u01d9\u0001\u0000\u0000" +
                    "\u0000\u01d4\u01d5\u0005f\u0000\u0000\u01d5\u01d6\u00036\u001b\u0000\u01d6" +
                    "\u01d7\u00036\u001b\u0000\u01d7\u01d9\u0001\u0000\u0000\u0000\u01d8\u01c8" +
                    "\u0001\u0000\u0000\u0000\u01d8\u01cc\u0001\u0000\u0000\u0000\u01d8\u01d0" +
                    "\u0001\u0000\u0000\u0000\u01d8\u01d4\u0001\u0000\u0000\u0000\u01d9S\u0001" +
                    "\u0000\u0000\u0000\u01da\u01db\u0005g\u0000\u0000\u01db\u01df\u00036\u001b" +
                    "\u0000\u01dc\u01dd\u0005h\u0000\u0000\u01dd\u01df\u00036\u001b\u0000\u01de" +
                    "\u01da\u0001\u0000\u0000\u0000\u01de\u01dc\u0001\u0000\u0000\u0000\u01df" +
                    "U\u0001\u0000\u0000\u0000\u01e0\u01e1\u0005i\u0000\u0000\u01e1\u01e2\u0005" +
                    "\u001c\u0000\u0000\u01e2\u01e3\u0003.\u0017\u0000\u01e3\u01e4\u00036\u001b" +
                    "\u0000\u01e4\u01e5\u00036\u001b\u0000\u01e5\u01e6\u00036\u001b\u0000\u01e6" +
                    "\u01e7\u0005\u001d\u0000\u0000\u01e7\u01e8\u0003\"\u0011\u0000\u01e8\u01f3" +
                    "\u0001\u0000\u0000\u0000\u01e9\u01ea\u0005j\u0000\u0000\u01ea\u01eb\u0005" +
                    "\u001c\u0000\u0000\u01eb\u01ec\u0003.\u0017\u0000\u01ec\u01ed\u00036\u001b" +
                    "\u0000\u01ed\u01ee\u00036\u001b\u0000\u01ee\u01ef\u00036\u001b\u0000\u01ef" +
                    "\u01f0\u0005\u001d\u0000\u0000\u01f0\u01f1\u0003\"\u0011\u0000\u01f1\u01f3" +
                    "\u0001\u0000\u0000\u0000\u01f2\u01e0\u0001\u0000\u0000\u0000\u01f2\u01e9" +
                    "\u0001\u0000\u0000\u0000\u01f3W\u0001\u0000\u0000\u0000\u01f4\u01f5\u0007" +
                    "\u0011\u0000\u0000\u01f5Y\u0001\u0000\u0000\u0000\u01f6\u01f7\u0005o\u0000" +
                    "\u0000\u01f7[\u0001\u0000\u0000\u0000/]belotw\u0094\u009a\u00a2\u00a6" +
                    "\u00a9\u00ae\u00b7\u00bb\u00be\u00c3\u00c7\u00cf\u00da\u00e0\u00e6\u00f0" +
                    "\u00fa\u0122\u012c\u0136\u0142\u0148\u014f\u0154\u0161\u016f\u0174\u0179" +
                    "\u017b\u0182\u0187\u018d\u0194\u019c\u01bf\u01c4\u01c6\u01d8\u01de\u01f2";
    public static final ATN _ATN =
            new ATNDeserializer().deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
        for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
            _decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
        }
    }
}