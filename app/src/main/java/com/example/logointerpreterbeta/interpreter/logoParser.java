package com.example.logointerpreterbeta.interpreter;// Generated from logo.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class logoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, T__60=61, T__61=62, T__62=63, T__63=64, T__64=65, T__65=66, 
		T__66=67, T__67=68, T__68=69, T__69=70, T__70=71, T__71=72, T__72=73, 
		T__73=74, T__74=75, T__75=76, T__76=77, T__77=78, T__78=79, T__79=80, 
		T__80=81, T__81=82, T__82=83, T__83=84, T__84=85, T__85=86, T__86=87, 
		T__87=88, T__88=89, T__89=90, T__90=91, T__91=92, T__92=93, T__93=94, 
		T__94=95, T__95=96, T__96=97, T__97=98, T__98=99, T__99=100, T__100=101, 
		T__101=102, T__102=103, T__103=104, T__104=105, T__105=106, T__106=107, 
		T__107=108, STRINGLITERAL=109, LITERAL=110, STRING=111, NUMBER=112, FLOAT=113, 
		BOOLEAND=114, COMMENT=115, EOL=116, WS=117;
	public static final int
		RULE_prog = 0, RULE_line = 1, RULE_cmd = 2, RULE_procedureInvocation = 3, 
		RULE_procedureDeclaration = 4, RULE_parameterDeclarations = 5, RULE_fd = 6, 
		RULE_bk = 7, RULE_rt = 8, RULE_lt = 9, RULE_cs = 10, RULE_pu = 11, RULE_pd = 12, 
		RULE_ht = 13, RULE_st = 14, RULE_home = 15, RULE_setx = 16, RULE_sety = 17, 
		RULE_settextsize = 18, RULE_setbg = 19, RULE_fill = 20, RULE_setpensize = 21, 
		RULE_arc = 22, RULE_setxy = 23, RULE_make = 24, RULE_print_ = 25, RULE_setcornerrounding = 26, 
		RULE_setpencolor = 27, RULE_repeat_ = 28, RULE_ife = 29, RULE_func_ = 30, 
		RULE_stop = 31, RULE_random = 32, RULE_fore = 33, RULE_label = 34, RULE_use = 35, 
		RULE_block = 36, RULE_comparison = 37, RULE_comparisonOperator = 38, RULE_name = 39, 
		RULE_value = 40, RULE_signExpression = 41, RULE_multiplyingExpression = 42, 
		RULE_expression = 43, RULE_deref = 44, RULE_number = 45, RULE_comment = 46;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "line", "cmd", "procedureInvocation", "procedureDeclaration", 
			"parameterDeclarations", "fd", "bk", "rt", "lt", "cs", "pu", "pd", "ht", 
			"st", "home", "setx", "sety", "settextsize", "setbg", "fill", "setpensize", 
			"arc", "setxy", "make", "print_", "setcornerrounding", "setpencolor", 
			"repeat_", "ife", "func_", "stop", "random", "fore", "label", "use", 
			"block", "comparison", "comparisonOperator", "name", "value", "signExpression", 
			"multiplyingExpression", "expression", "deref", "number", "comment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'to'", "'end'", "'TO'", "'END'", "':'", "','", "'fd'", "'forward'", 
			"'FD'", "'FORWARD'", "'bk'", "'backward'", "'BK'", "'BACKWARD'", "'back'", 
			"'BACK'", "'rt'", "'right'", "'RT'", "'RIGHT'", "'lt'", "'left'", "'LT'", 
			"'LEFT'", "'cs'", "'clearscreen'", "'CS'", "'CLEARSCREEN'", "'pu'", "'penup'", 
			"'PU'", "'PENUP'", "'pd'", "'pendown'", "'PD'", "'PENDOWN'", "'ht'", 
			"'hideturtle'", "'HT'", "'HIDETURTLE'", "'st'", "'showturtle'", "'ST'", 
			"'SHOWTURTLE'", "'home'", "'HOME'", "'setx'", "'SETX'", "'sety'", "'SETY'", 
			"'setts'", "'SETTS'", "'setpensize'", "'SETPENSIZE'", "'setbg'", "'SETBG'", 
			"'setbackground'", "'SETBACKGROUND'", "'fill'", "'FILL'", "'setps'", 
			"'SETPS'", "'arc'", "'ARC'", "'setxy'", "'SETXY'", "'setpos'", "'SETPOS'", 
			"'make'", "'MAKE'", "'print'", "'PRINT'", "'setcornerrounding'", "'SETCORNERROUNDING'", 
			"'setcr'", "'SETCR'", "'setpc'", "'['", "']'", "'SETPC'", "'setpencolor'", 
			"'SETPENCOLOR'", "'repeat'", "'REPEAT'", "'if'", "'IF'", "'stop'", "'STOP'", 
			"'random'", "'RANDOM'", "'for'", "'FOR'", "'label'", "'LABEL'", "'use'", 
			"'USE'", "'<'", "'>'", "'='", "'<='", "'>='", "'<>'", "'+'", "'-'", "'*'", 
			"'/'", "'0'", "'1'", null, "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "STRINGLITERAL", "LITERAL", "STRING", "NUMBER", "FLOAT", "BOOLEAND", 
			"COMMENT", "EOL", "WS"
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
	public String getGrammarFileName() { return "logo.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public logoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(logoParser.EOF, 0); }
		public List<TerminalNode> EOL() { return getTokens(logoParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(logoParser.EOL, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitProg(this);
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
			setState(98); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2392545791262719L) != 0)) {
						{
						setState(94);
						line();
						}
					}

					setState(97);
					match(EOL);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(100); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(103);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2392545791262719L) != 0)) {
				{
				setState(102);
				line();
				}
			}

			setState(105);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(CmdContext.class,i);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public Print_Context print_() {
			return getRuleContext(Print_Context.class,0);
		}
		public ProcedureDeclarationContext procedureDeclaration() {
			return getRuleContext(ProcedureDeclarationContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		int _la;
		try {
			setState(121);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
			case T__7:
			case T__8:
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
			case T__27:
			case T__28:
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
			case T__40:
			case T__41:
			case T__42:
			case T__43:
			case T__44:
			case T__45:
			case T__46:
			case T__47:
			case T__48:
			case T__49:
			case T__50:
			case T__51:
			case T__52:
			case T__53:
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
			case T__72:
			case T__73:
			case T__74:
			case T__75:
			case T__76:
			case T__79:
			case T__80:
			case T__81:
			case T__82:
			case T__83:
			case T__84:
			case T__85:
			case T__86:
			case T__87:
			case T__90:
			case T__91:
			case T__92:
			case T__93:
			case T__94:
			case T__95:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(108); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(107);
					cmd();
					}
					}
					setState(110); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -128L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 140745977577087L) != 0) );
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(112);
					comment();
					}
				}

				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(115);
				comment();
				}
				break;
			case T__70:
			case T__71:
				enterOuterAlt(_localctx, 3);
				{
				setState(116);
				print_();
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(117);
					comment();
					}
				}

				}
				break;
			case T__0:
			case T__2:
				enterOuterAlt(_localctx, 4);
				{
				setState(120);
				procedureDeclaration();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CmdContext extends ParserRuleContext {
		public Repeat_Context repeat_() {
			return getRuleContext(Repeat_Context.class,0);
		}
		public FdContext fd() {
			return getRuleContext(FdContext.class,0);
		}
		public BkContext bk() {
			return getRuleContext(BkContext.class,0);
		}
		public RtContext rt() {
			return getRuleContext(RtContext.class,0);
		}
		public LtContext lt() {
			return getRuleContext(LtContext.class,0);
		}
		public CsContext cs() {
			return getRuleContext(CsContext.class,0);
		}
		public PuContext pu() {
			return getRuleContext(PuContext.class,0);
		}
		public PdContext pd() {
			return getRuleContext(PdContext.class,0);
		}
		public HtContext ht() {
			return getRuleContext(HtContext.class,0);
		}
		public StContext st() {
			return getRuleContext(StContext.class,0);
		}
		public HomeContext home() {
			return getRuleContext(HomeContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public SetxyContext setxy() {
			return getRuleContext(SetxyContext.class,0);
		}
		public MakeContext make() {
			return getRuleContext(MakeContext.class,0);
		}
		public ProcedureInvocationContext procedureInvocation() {
			return getRuleContext(ProcedureInvocationContext.class,0);
		}
		public IfeContext ife() {
			return getRuleContext(IfeContext.class,0);
		}
		public StopContext stop() {
			return getRuleContext(StopContext.class,0);
		}
		public ForeContext fore() {
			return getRuleContext(ForeContext.class,0);
		}
		public ArcContext arc() {
			return getRuleContext(ArcContext.class,0);
		}
		public SetpensizeContext setpensize() {
			return getRuleContext(SetpensizeContext.class,0);
		}
		public SetpencolorContext setpencolor() {
			return getRuleContext(SetpencolorContext.class,0);
		}
		public FillContext fill() {
			return getRuleContext(FillContext.class,0);
		}
		public SetbgContext setbg() {
			return getRuleContext(SetbgContext.class,0);
		}
		public SettextsizeContext settextsize() {
			return getRuleContext(SettextsizeContext.class,0);
		}
		public SetyContext sety() {
			return getRuleContext(SetyContext.class,0);
		}
		public SetxContext setx() {
			return getRuleContext(SetxContext.class,0);
		}
		public SetcornerroundingContext setcornerrounding() {
			return getRuleContext(SetcornerroundingContext.class,0);
		}
		public UseContext use() {
			return getRuleContext(UseContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitCmd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_cmd);
		try {
			setState(151);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				repeat_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(124);
				fd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(125);
				bk();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(126);
				rt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(127);
				lt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(128);
				cs();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(129);
				pu();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(130);
				pd();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(131);
				ht();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(132);
				st();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(133);
				home();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(134);
				label();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(135);
				setxy();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(136);
				make();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(137);
				procedureInvocation();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(138);
				ife();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(139);
				stop();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(140);
				fore();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(141);
				arc();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(142);
				setpensize();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(143);
				setpencolor();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(144);
				fill();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(145);
				setbg();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(146);
				settextsize();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(147);
				sety();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(148);
				setx();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(149);
				setcornerrounding();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(150);
				use();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProcedureInvocationContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public ProcedureInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureInvocation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitProcedureInvocation(this);
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
			setState(153);
			name();
			setState(157);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4 || ((((_la - 89)) & ~0x3f) == 0 && ((1L << (_la - 89)) & 26001411L) != 0)) {
				{
				{
				setState(154);
				expression();
				}
				}
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProcedureDeclarationContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<ParameterDeclarationsContext> parameterDeclarations() {
			return getRuleContexts(ParameterDeclarationsContext.class);
		}
		public ParameterDeclarationsContext parameterDeclarations(int i) {
			return getRuleContext(ParameterDeclarationsContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(logoParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(logoParser.EOL, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDeclaration; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitProcedureDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
		ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_procedureDeclaration);
		int _la;
		try {
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(160);
				match(T__0);
				setState(161);
				name();
				setState(165);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(162);
					parameterDeclarations();
					}
					}
					setState(167);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(169);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(168);
					match(EOL);
					}
					break;
				}
				setState(175); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(172);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2392545791262719L) != 0)) {
						{
						setState(171);
						line();
						}
					}

					setState(174);
					match(EOL);
					}
					}
					setState(177); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 6896145418633215L) != 0) );
				setState(179);
				match(T__1);
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(181);
				match(T__2);
				setState(182);
				name();
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__4) {
					{
					{
					setState(183);
					parameterDeclarations();
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(190);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(189);
					match(EOL);
					}
					break;
				}
				setState(196); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(193);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 2392545791262719L) != 0)) {
						{
						setState(192);
						line();
						}
					}

					setState(195);
					match(EOL);
					}
					}
					setState(198); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -118L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 6896145418633215L) != 0) );
				setState(200);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParameterDeclarationsContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<ParameterDeclarationsContext> parameterDeclarations() {
			return getRuleContexts(ParameterDeclarationsContext.class);
		}
		public ParameterDeclarationsContext parameterDeclarations(int i) {
			return getRuleContext(ParameterDeclarationsContext.class,i);
		}
		public ParameterDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterDeclarations; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitParameterDeclarations(this);
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
			setState(204);
			match(T__4);
			setState(205);
			name();
			setState(210);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(206);
					match(T__5);
					setState(207);
					parameterDeclarations();
					}
					} 
				}
				setState(212);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FdContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitFd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FdContext fd() throws RecognitionException {
		FdContext _localctx = new FdContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(213);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1920L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(214);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BkContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BkContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bk; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitBk(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BkContext bk() throws RecognitionException {
		BkContext _localctx = new BkContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_bk);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(216);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 129024L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(217);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RtContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitRt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RtContext rt() throws RecognitionException {
		RtContext _localctx = new RtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_rt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966080L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(220);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LtContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lt; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitLt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LtContext lt() throws RecognitionException {
		LtContext _localctx = new LtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_lt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 31457280L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(223);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CsContext extends ParserRuleContext {
		public CsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cs; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitCs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CsContext cs() throws RecognitionException {
		CsContext _localctx = new CsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 503316480L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PuContext extends ParserRuleContext {
		public PuContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pu; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitPu(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PuContext pu() throws RecognitionException {
		PuContext _localctx = new PuContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_pu);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8053063680L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PdContext extends ParserRuleContext {
		public PdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pd; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitPd(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PdContext pd() throws RecognitionException {
		PdContext _localctx = new PdContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_pd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 128849018880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HtContext extends ParserRuleContext {
		public HtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ht; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitHt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HtContext ht() throws RecognitionException {
		HtContext _localctx = new HtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_ht);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2061584302080L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StContext extends ParserRuleContext {
		public StContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_st; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StContext st() throws RecognitionException {
		StContext _localctx = new StContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_st);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 32985348833280L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class HomeContext extends ParserRuleContext {
		public HomeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_home; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitHome(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HomeContext home() throws RecognitionException {
		HomeContext _localctx = new HomeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_home);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			_la = _input.LA(1);
			if ( !(_la==T__44 || _la==T__45) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetxContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setx; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetx(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetxContext setx() throws RecognitionException {
		SetxContext _localctx = new SetxContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_setx);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(T__46);
				setState(238);
				expression();
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(T__47);
				setState(240);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetyContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sety; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSety(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetyContext sety() throws RecognitionException {
		SetyContext _localctx = new SetyContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_sety);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__48:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				match(T__48);
				setState(244);
				expression();
				}
				break;
			case T__49:
				enterOuterAlt(_localctx, 2);
				{
				setState(245);
				match(T__49);
				setState(246);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SettextsizeContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SettextsizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_settextsize; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSettextsize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SettextsizeContext settextsize() throws RecognitionException {
		SettextsizeContext _localctx = new SettextsizeContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_settextsize);
		try {
			setState(257);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__50:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(T__50);
				setState(250);
				expression();
				}
				break;
			case T__51:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				match(T__51);
				setState(252);
				expression();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 3);
				{
				setState(253);
				match(T__52);
				setState(254);
				expression();
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 4);
				{
				setState(255);
				match(T__53);
				setState(256);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetbgContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetbgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setbg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetbg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetbgContext setbg() throws RecognitionException {
		SetbgContext _localctx = new SetbgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_setbg);
		try {
			setState(267);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__54:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				match(T__54);
				setState(260);
				expression();
				}
				break;
			case T__55:
				enterOuterAlt(_localctx, 2);
				{
				setState(261);
				match(T__55);
				setState(262);
				expression();
				}
				break;
			case T__56:
				enterOuterAlt(_localctx, 3);
				{
				setState(263);
				match(T__56);
				setState(264);
				expression();
				}
				break;
			case T__57:
				enterOuterAlt(_localctx, 4);
				{
				setState(265);
				match(T__57);
				setState(266);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FillContext extends ParserRuleContext {
		public FillContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fill; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitFill(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FillContext fill() throws RecognitionException {
		FillContext _localctx = new FillContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fill);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			_la = _input.LA(1);
			if ( !(_la==T__58 || _la==T__59) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetpensizeContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetpensizeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setpensize; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetpensize(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetpensizeContext setpensize() throws RecognitionException {
		SetpensizeContext _localctx = new SetpensizeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_setpensize);
		try {
			setState(279);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__60:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(T__60);
				setState(272);
				expression();
				}
				break;
			case T__61:
				enterOuterAlt(_localctx, 2);
				{
				setState(273);
				match(T__61);
				setState(274);
				expression();
				}
				break;
			case T__52:
				enterOuterAlt(_localctx, 3);
				{
				setState(275);
				match(T__52);
				setState(276);
				expression();
				}
				break;
			case T__53:
				enterOuterAlt(_localctx, 4);
				{
				setState(277);
				match(T__53);
				setState(278);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(ExpressionContext.class,i);
		}
		public ArcContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitArc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArcContext arc() throws RecognitionException {
		ArcContext _localctx = new ArcContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_arc);
		try {
			setState(289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__62:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				match(T__62);
				setState(282);
				expression();
				setState(283);
				expression();
				}
				break;
			case T__63:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(T__63);
				setState(286);
				expression();
				setState(287);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(ExpressionContext.class,i);
		}
		public SetxyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setxy; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetxy(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetxyContext setxy() throws RecognitionException {
		SetxyContext _localctx = new SetxyContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_setxy);
		try {
			setState(307);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__64:
				enterOuterAlt(_localctx, 1);
				{
				setState(291);
				match(T__64);
				setState(292);
				expression();
				setState(293);
				expression();
				}
				break;
			case T__65:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(T__65);
				setState(296);
				expression();
				setState(297);
				expression();
				}
				break;
			case T__66:
				enterOuterAlt(_localctx, 3);
				{
				setState(299);
				match(T__66);
				setState(300);
				expression();
				setState(301);
				expression();
				}
				break;
			case T__67:
				enterOuterAlt(_localctx, 4);
				{
				setState(303);
				match(T__67);
				setState(304);
				expression();
				setState(305);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MakeContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(logoParser.STRINGLITERAL, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public MakeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_make; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitMake(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MakeContext make() throws RecognitionException {
		MakeContext _localctx = new MakeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_make);
		try {
			setState(315);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__68:
				enterOuterAlt(_localctx, 1);
				{
				setState(309);
				match(T__68);
				setState(310);
				match(STRINGLITERAL);
				setState(311);
				value();
				}
				break;
			case T__69:
				enterOuterAlt(_localctx, 2);
				{
				setState(312);
				match(T__69);
				setState(313);
				match(STRINGLITERAL);
				setState(314);
				value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Print_Context extends ParserRuleContext {
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode STRINGLITERAL() { return getToken(logoParser.STRINGLITERAL, 0); }
		public Print_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitPrint_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Print_Context print_() throws RecognitionException {
		Print_Context _localctx = new Print_Context(_ctx, getState());
		enterRule(_localctx, 50, RULE_print_);
		try {
			setState(327);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__70:
				enterOuterAlt(_localctx, 1);
				{
				setState(317);
				match(T__70);
				setState(320);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(318);
					value();
					}
					break;
				case 2:
					{
					setState(319);
					match(STRINGLITERAL);
					}
					break;
				}
				}
				break;
			case T__71:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				match(T__71);
				setState(325);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(323);
					value();
					}
					break;
				case 2:
					{
					setState(324);
					match(STRINGLITERAL);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetcornerroundingContext extends ParserRuleContext {
		public TerminalNode BOOLEAND() { return getToken(logoParser.BOOLEAND, 0); }
		public SetcornerroundingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setcornerrounding; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetcornerrounding(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetcornerroundingContext setcornerrounding() throws RecognitionException {
		SetcornerroundingContext _localctx = new SetcornerroundingContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_setcornerrounding);
		try {
			setState(337);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__72:
				enterOuterAlt(_localctx, 1);
				{
				setState(329);
				match(T__72);
				setState(330);
				match(BOOLEAND);
				}
				break;
			case T__73:
				enterOuterAlt(_localctx, 2);
				{
				setState(331);
				match(T__73);
				setState(332);
				match(BOOLEAND);
				}
				break;
			case T__74:
				enterOuterAlt(_localctx, 3);
				{
				setState(333);
				match(T__74);
				setState(334);
				match(BOOLEAND);
				}
				break;
			case T__75:
				enterOuterAlt(_localctx, 4);
				{
				setState(335);
				match(T__75);
				setState(336);
				match(BOOLEAND);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SetpencolorContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public SetpencolorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setpencolor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetpencolor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetpencolorContext setpencolor() throws RecognitionException {
		SetpencolorContext _localctx = new SetpencolorContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_setpencolor);
		try {
			setState(375);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(339);
				match(T__76);
				setState(340);
				expression();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(341);
				match(T__76);
				setState(342);
				match(T__77);
				setState(343);
				number();
				setState(344);
				number();
				setState(345);
				number();
				setState(346);
				match(T__78);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(348);
				match(T__79);
				setState(349);
				expression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(350);
				match(T__79);
				setState(351);
				match(T__77);
				setState(352);
				number();
				setState(353);
				number();
				setState(354);
				number();
				setState(355);
				match(T__78);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(357);
				match(T__80);
				setState(358);
				expression();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(359);
				match(T__80);
				setState(360);
				match(T__77);
				setState(361);
				number();
				setState(362);
				number();
				setState(363);
				number();
				setState(364);
				match(T__78);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(366);
				match(T__81);
				setState(367);
				expression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(368);
				match(T__81);
				setState(369);
				match(T__77);
				setState(370);
				number();
				setState(371);
				number();
				setState(372);
				number();
				setState(373);
				match(T__78);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Repeat_Context extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public Repeat_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeat_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitRepeat_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Repeat_Context repeat_() throws RecognitionException {
		Repeat_Context _localctx = new Repeat_Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_repeat_);
		try {
			setState(385);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__82:
				enterOuterAlt(_localctx, 1);
				{
				setState(377);
				match(T__82);
				setState(378);
				number();
				setState(379);
				block();
				}
				break;
			case T__83:
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				match(T__83);
				setState(382);
				number();
				setState(383);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfeContext extends ParserRuleContext {
		public ComparisonContext comparison() {
			return getRuleContext(ComparisonContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ife; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitIfe(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfeContext ife() throws RecognitionException {
		IfeContext _localctx = new IfeContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_ife);
		try {
			setState(395);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__84:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				match(T__84);
				setState(388);
				comparison();
				setState(389);
				block();
				}
				break;
			case T__85:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				match(T__85);
				setState(392);
				comparison();
				setState(393);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_Context extends ParserRuleContext {
		public RandomContext random() {
			return getRuleContext(RandomContext.class,0);
		}
		public Func_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitFunc_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_Context func_() throws RecognitionException {
		Func_Context _localctx = new Func_Context(_ctx, getState());
		enterRule(_localctx, 60, RULE_func_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(397);
			random();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StopContext extends ParserRuleContext {
		public StopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitStop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StopContext stop() throws RecognitionException {
		StopContext _localctx = new StopContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_stop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			_la = _input.LA(1);
			if ( !(_la==T__86 || _la==T__87) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RandomContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RandomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_random; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitRandom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RandomContext random() throws RecognitionException {
		RandomContext _localctx = new RandomContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_random);
		try {
			setState(405);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__88:
				enterOuterAlt(_localctx, 1);
				{
				setState(401);
				match(T__88);
				setState(402);
				expression();
				}
				break;
			case T__89:
				enterOuterAlt(_localctx, 2);
				{
				setState(403);
				match(T__89);
				setState(404);
				expression();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForeContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fore; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitFore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeContext fore() throws RecognitionException {
		ForeContext _localctx = new ForeContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_fore);
		try {
			setState(425);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__90:
				enterOuterAlt(_localctx, 1);
				{
				setState(407);
				match(T__90);
				setState(408);
				match(T__77);
				setState(409);
				name();
				setState(410);
				expression();
				setState(411);
				expression();
				setState(412);
				expression();
				setState(413);
				match(T__78);
				setState(414);
				block();
				}
				break;
			case T__91:
				enterOuterAlt(_localctx, 2);
				{
				setState(416);
				match(T__91);
				setState(417);
				match(T__77);
				setState(418);
				name();
				setState(419);
				expression();
				setState(420);
				expression();
				setState(421);
				expression();
				setState(422);
				match(T__78);
				setState(423);
				block();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(logoParser.STRINGLITERAL, 0); }
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_label);
		try {
			setState(437);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__92:
				enterOuterAlt(_localctx, 1);
				{
				setState(427);
				match(T__92);
				setState(430);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRINGLITERAL:
					{
					setState(428);
					match(STRINGLITERAL);
					}
					break;
				case T__4:
					{
					setState(429);
					deref();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case T__93:
				enterOuterAlt(_localctx, 2);
				{
				setState(432);
				match(T__93);
				setState(435);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case STRINGLITERAL:
					{
					setState(433);
					match(STRINGLITERAL);
					}
					break;
				case T__4:
					{
					setState(434);
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
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class UseContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public UseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitUse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UseContext use() throws RecognitionException {
		UseContext _localctx = new UseContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_use);
		try {
			setState(443);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__94:
				enterOuterAlt(_localctx, 1);
				{
				setState(439);
				match(T__94);
				setState(440);
				name();
				}
				break;
			case T__95:
				enterOuterAlt(_localctx, 2);
				{
				setState(441);
				match(T__95);
				setState(442);
				name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(CmdContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(logoParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(logoParser.WS, i);
		}
		public List<TerminalNode> EOL() { return getTokens(logoParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(logoParser.EOL, i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(445);
			match(T__77);
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -128L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 13651544859688575L) != 0)) {
				{
				{
				setState(449);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL || _la==WS) {
					{
					{
					setState(446);
					_la = _input.LA(1);
					if ( !(_la==EOL || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(451);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(452);
				cmd();
				setState(456);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(453);
						_la = _input.LA(1);
						if ( !(_la==EOL || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(458);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
				}
				}
				}
				setState(463);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(464);
			match(T__78);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(ExpressionContext.class,i);
		}
		public ComparisonOperatorContext comparisonOperator() {
			return getRuleContext(ComparisonOperatorContext.class,0);
		}
		public ComparisonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparison; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitComparison(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonContext comparison() throws RecognitionException {
		ComparisonContext _localctx = new ComparisonContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(466);
			expression();
			setState(467);
			comparisonOperator();
			setState(468);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ComparisonOperatorContext extends ParserRuleContext {
		public ComparisonOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonOperator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitComparisonOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonOperatorContext comparisonOperator() throws RecognitionException {
		ComparisonOperatorContext _localctx = new ComparisonOperatorContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(470);
			_la = _input.LA(1);
			if ( !(((((_la - 97)) & ~0x3f) == 0 && ((1L << (_la - 97)) & 63L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(logoParser.STRING, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(472);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ValueContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(logoParser.STRINGLITERAL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_value);
		try {
			setState(477);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(474);
				match(STRINGLITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(476);
				deref();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SignExpressionContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DerefContext deref() {
			return getRuleContext(DerefContext.class,0);
		}
		public Func_Context func_() {
			return getRuleContext(Func_Context.class,0);
		}
		public SignExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSignExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignExpressionContext signExpression() throws RecognitionException {
		SignExpressionContext _localctx = new SignExpressionContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_signExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__102 || _la==T__103) {
				{
				{
				setState(479);
				_la = _input.LA(1);
				if ( !(_la==T__102 || _la==T__103) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(484);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(488);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__106:
			case T__107:
			case NUMBER:
			case FLOAT:
				{
				setState(485);
				number();
				}
				break;
			case T__4:
				{
				setState(486);
				deref();
				}
				break;
			case T__88:
			case T__89:
				{
				setState(487);
				func_();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(SignExpressionContext.class,i);
		}
		public MultiplyingExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplyingExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitMultiplyingExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplyingExpressionContext multiplyingExpression() throws RecognitionException {
		MultiplyingExpressionContext _localctx = new MultiplyingExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_multiplyingExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(490);
			signExpression();
			setState(495);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(491);
					_la = _input.LA(1);
					if ( !(_la==T__104 || _la==T__105) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(492);
					signExpression();
					}
					} 
				}
				setState(497);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
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
			return getRuleContext(MultiplyingExpressionContext.class,i);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(498);
			multiplyingExpression();
			setState(503);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(499);
					_la = _input.LA(1);
					if ( !(_la==T__102 || _la==T__103) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(500);
					multiplyingExpression();
					}
					} 
				}
				setState(505);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DerefContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public DerefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_deref; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitDeref(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DerefContext deref() throws RecognitionException {
		DerefContext _localctx = new DerefContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(506);
			match(T__4);
			setState(507);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(logoParser.NUMBER, 0); }
		public TerminalNode FLOAT() { return getToken(logoParser.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			_la = _input.LA(1);
			if ( !(((((_la - 107)) & ~0x3f) == 0 && ((1L << (_la - 107)) & 99L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(logoParser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001u\u0202\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0001\u0000\u0003\u0000`\b\u0000\u0001\u0000\u0004"+
		"\u0000c\b\u0000\u000b\u0000\f\u0000d\u0001\u0000\u0003\u0000h\b\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001m\b\u0001\u000b\u0001"+
		"\f\u0001n\u0001\u0001\u0003\u0001r\b\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0003\u0001w\b\u0001\u0001\u0001\u0003\u0001z\b\u0001\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0098\b\u0002\u0001\u0003"+
		"\u0001\u0003\u0005\u0003\u009c\b\u0003\n\u0003\f\u0003\u009f\t\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00a4\b\u0004\n\u0004\f\u0004"+
		"\u00a7\t\u0004\u0001\u0004\u0003\u0004\u00aa\b\u0004\u0001\u0004\u0003"+
		"\u0004\u00ad\b\u0004\u0001\u0004\u0004\u0004\u00b0\b\u0004\u000b\u0004"+
		"\f\u0004\u00b1\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004\u00b9\b\u0004\n\u0004\f\u0004\u00bc\t\u0004\u0001\u0004\u0003"+
		"\u0004\u00bf\b\u0004\u0001\u0004\u0003\u0004\u00c2\b\u0004\u0001\u0004"+
		"\u0004\u0004\u00c5\b\u0004\u000b\u0004\f\u0004\u00c6\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00cb\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0005\u0005\u00d1\b\u0005\n\u0005\f\u0005\u00d4\t\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00f2"+
		"\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00f8"+
		"\b\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0102\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u010c\b\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0003\u0015\u0118\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016\u0122"+
		"\b\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0003\u0017\u0134"+
		"\b\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0003\u0018\u013c\b\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0003"+
		"\u0019\u0141\b\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0146"+
		"\b\u0019\u0003\u0019\u0148\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0003\u001a"+
		"\u0152\b\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0003\u001b\u0178\b\u001b\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c"+
		"\u0182\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0001\u001d\u0001\u001d\u0003\u001d\u018c\b\u001d\u0001\u001e"+
		"\u0001\u001e\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0003"+
		" \u0196\b \u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0001!\u0003"+
		"!\u01aa\b!\u0001\"\u0001\"\u0001\"\u0003\"\u01af\b\"\u0001\"\u0001\"\u0001"+
		"\"\u0003\"\u01b4\b\"\u0003\"\u01b6\b\"\u0001#\u0001#\u0001#\u0001#\u0003"+
		"#\u01bc\b#\u0001$\u0001$\u0005$\u01c0\b$\n$\f$\u01c3\t$\u0001$\u0001$"+
		"\u0005$\u01c7\b$\n$\f$\u01ca\t$\u0005$\u01cc\b$\n$\f$\u01cf\t$\u0001$"+
		"\u0001$\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001"+
		"(\u0001(\u0001(\u0003(\u01de\b(\u0001)\u0005)\u01e1\b)\n)\f)\u01e4\t)"+
		"\u0001)\u0001)\u0001)\u0003)\u01e9\b)\u0001*\u0001*\u0001*\u0005*\u01ee"+
		"\b*\n*\f*\u01f1\t*\u0001+\u0001+\u0001+\u0005+\u01f6\b+\n+\f+\u01f9\t"+
		"+\u0001,\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001.\u0000\u0000"+
		"/\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.02468:<>@BDFHJLNPRTVXZ\\\u0000\u0011\u0001\u0000"+
		"\u0007\n\u0001\u0000\u000b\u0010\u0001\u0000\u0011\u0014\u0001\u0000\u0015"+
		"\u0018\u0001\u0000\u0019\u001c\u0001\u0000\u001d \u0001\u0000!$\u0001"+
		"\u0000%(\u0001\u0000),\u0001\u0000-.\u0001\u0000;<\u0001\u0000WX\u0001"+
		"\u0000tu\u0001\u0000af\u0001\u0000gh\u0001\u0000ij\u0002\u0000klpq\u0230"+
		"\u0000b\u0001\u0000\u0000\u0000\u0002y\u0001\u0000\u0000\u0000\u0004\u0097"+
		"\u0001\u0000\u0000\u0000\u0006\u0099\u0001\u0000\u0000\u0000\b\u00ca\u0001"+
		"\u0000\u0000\u0000\n\u00cc\u0001\u0000\u0000\u0000\f\u00d5\u0001\u0000"+
		"\u0000\u0000\u000e\u00d8\u0001\u0000\u0000\u0000\u0010\u00db\u0001\u0000"+
		"\u0000\u0000\u0012\u00de\u0001\u0000\u0000\u0000\u0014\u00e1\u0001\u0000"+
		"\u0000\u0000\u0016\u00e3\u0001\u0000\u0000\u0000\u0018\u00e5\u0001\u0000"+
		"\u0000\u0000\u001a\u00e7\u0001\u0000\u0000\u0000\u001c\u00e9\u0001\u0000"+
		"\u0000\u0000\u001e\u00eb\u0001\u0000\u0000\u0000 \u00f1\u0001\u0000\u0000"+
		"\u0000\"\u00f7\u0001\u0000\u0000\u0000$\u0101\u0001\u0000\u0000\u0000"+
		"&\u010b\u0001\u0000\u0000\u0000(\u010d\u0001\u0000\u0000\u0000*\u0117"+
		"\u0001\u0000\u0000\u0000,\u0121\u0001\u0000\u0000\u0000.\u0133\u0001\u0000"+
		"\u0000\u00000\u013b\u0001\u0000\u0000\u00002\u0147\u0001\u0000\u0000\u0000"+
		"4\u0151\u0001\u0000\u0000\u00006\u0177\u0001\u0000\u0000\u00008\u0181"+
		"\u0001\u0000\u0000\u0000:\u018b\u0001\u0000\u0000\u0000<\u018d\u0001\u0000"+
		"\u0000\u0000>\u018f\u0001\u0000\u0000\u0000@\u0195\u0001\u0000\u0000\u0000"+
		"B\u01a9\u0001\u0000\u0000\u0000D\u01b5\u0001\u0000\u0000\u0000F\u01bb"+
		"\u0001\u0000\u0000\u0000H\u01bd\u0001\u0000\u0000\u0000J\u01d2\u0001\u0000"+
		"\u0000\u0000L\u01d6\u0001\u0000\u0000\u0000N\u01d8\u0001\u0000\u0000\u0000"+
		"P\u01dd\u0001\u0000\u0000\u0000R\u01e2\u0001\u0000\u0000\u0000T\u01ea"+
		"\u0001\u0000\u0000\u0000V\u01f2\u0001\u0000\u0000\u0000X\u01fa\u0001\u0000"+
		"\u0000\u0000Z\u01fd\u0001\u0000\u0000\u0000\\\u01ff\u0001\u0000\u0000"+
		"\u0000^`\u0003\u0002\u0001\u0000_^\u0001\u0000\u0000\u0000_`\u0001\u0000"+
		"\u0000\u0000`a\u0001\u0000\u0000\u0000ac\u0005t\u0000\u0000b_\u0001\u0000"+
		"\u0000\u0000cd\u0001\u0000\u0000\u0000db\u0001\u0000\u0000\u0000de\u0001"+
		"\u0000\u0000\u0000eg\u0001\u0000\u0000\u0000fh\u0003\u0002\u0001\u0000"+
		"gf\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000"+
		"\u0000ij\u0005\u0000\u0000\u0001j\u0001\u0001\u0000\u0000\u0000km\u0003"+
		"\u0004\u0002\u0000lk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000"+
		"nl\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000oq\u0001\u0000\u0000"+
		"\u0000pr\u0003\\.\u0000qp\u0001\u0000\u0000\u0000qr\u0001\u0000\u0000"+
		"\u0000rz\u0001\u0000\u0000\u0000sz\u0003\\.\u0000tv\u00032\u0019\u0000"+
		"uw\u0003\\.\u0000vu\u0001\u0000\u0000\u0000vw\u0001\u0000\u0000\u0000"+
		"wz\u0001\u0000\u0000\u0000xz\u0003\b\u0004\u0000yl\u0001\u0000\u0000\u0000"+
		"ys\u0001\u0000\u0000\u0000yt\u0001\u0000\u0000\u0000yx\u0001\u0000\u0000"+
		"\u0000z\u0003\u0001\u0000\u0000\u0000{\u0098\u00038\u001c\u0000|\u0098"+
		"\u0003\f\u0006\u0000}\u0098\u0003\u000e\u0007\u0000~\u0098\u0003\u0010"+
		"\b\u0000\u007f\u0098\u0003\u0012\t\u0000\u0080\u0098\u0003\u0014\n\u0000"+
		"\u0081\u0098\u0003\u0016\u000b\u0000\u0082\u0098\u0003\u0018\f\u0000\u0083"+
		"\u0098\u0003\u001a\r\u0000\u0084\u0098\u0003\u001c\u000e\u0000\u0085\u0098"+
		"\u0003\u001e\u000f\u0000\u0086\u0098\u0003D\"\u0000\u0087\u0098\u0003"+
		".\u0017\u0000\u0088\u0098\u00030\u0018\u0000\u0089\u0098\u0003\u0006\u0003"+
		"\u0000\u008a\u0098\u0003:\u001d\u0000\u008b\u0098\u0003>\u001f\u0000\u008c"+
		"\u0098\u0003B!\u0000\u008d\u0098\u0003,\u0016\u0000\u008e\u0098\u0003"+
		"*\u0015\u0000\u008f\u0098\u00036\u001b\u0000\u0090\u0098\u0003(\u0014"+
		"\u0000\u0091\u0098\u0003&\u0013\u0000\u0092\u0098\u0003$\u0012\u0000\u0093"+
		"\u0098\u0003\"\u0011\u0000\u0094\u0098\u0003 \u0010\u0000\u0095\u0098"+
		"\u00034\u001a\u0000\u0096\u0098\u0003F#\u0000\u0097{\u0001\u0000\u0000"+
		"\u0000\u0097|\u0001\u0000\u0000\u0000\u0097}\u0001\u0000\u0000\u0000\u0097"+
		"~\u0001\u0000\u0000\u0000\u0097\u007f\u0001\u0000\u0000\u0000\u0097\u0080"+
		"\u0001\u0000\u0000\u0000\u0097\u0081\u0001\u0000\u0000\u0000\u0097\u0082"+
		"\u0001\u0000\u0000\u0000\u0097\u0083\u0001\u0000\u0000\u0000\u0097\u0084"+
		"\u0001\u0000\u0000\u0000\u0097\u0085\u0001\u0000\u0000\u0000\u0097\u0086"+
		"\u0001\u0000\u0000\u0000\u0097\u0087\u0001\u0000\u0000\u0000\u0097\u0088"+
		"\u0001\u0000\u0000\u0000\u0097\u0089\u0001\u0000\u0000\u0000\u0097\u008a"+
		"\u0001\u0000\u0000\u0000\u0097\u008b\u0001\u0000\u0000\u0000\u0097\u008c"+
		"\u0001\u0000\u0000\u0000\u0097\u008d\u0001\u0000\u0000\u0000\u0097\u008e"+
		"\u0001\u0000\u0000\u0000\u0097\u008f\u0001\u0000\u0000\u0000\u0097\u0090"+
		"\u0001\u0000\u0000\u0000\u0097\u0091\u0001\u0000\u0000\u0000\u0097\u0092"+
		"\u0001\u0000\u0000\u0000\u0097\u0093\u0001\u0000\u0000\u0000\u0097\u0094"+
		"\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0097\u0096"+
		"\u0001\u0000\u0000\u0000\u0098\u0005\u0001\u0000\u0000\u0000\u0099\u009d"+
		"\u0003N\'\u0000\u009a\u009c\u0003V+\u0000\u009b\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009f\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e\u0007\u0001\u0000\u0000"+
		"\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005\u0001\u0000"+
		"\u0000\u00a1\u00a5\u0003N\'\u0000\u00a2\u00a4\u0003\n\u0005\u0000\u00a3"+
		"\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a7\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a9\u0001\u0000\u0000\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8"+
		"\u00aa\u0005t\u0000\u0000\u00a9\u00a8\u0001\u0000\u0000\u0000\u00a9\u00aa"+
		"\u0001\u0000\u0000\u0000\u00aa\u00af\u0001\u0000\u0000\u0000\u00ab\u00ad"+
		"\u0003\u0002\u0001\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ac\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u00b0"+
		"\u0005t\u0000\u0000\u00af\u00ac\u0001\u0000\u0000\u0000\u00b0\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b1\u00af\u0001\u0000\u0000\u0000\u00b1\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005"+
		"\u0002\u0000\u0000\u00b4\u00cb\u0001\u0000\u0000\u0000\u00b5\u00b6\u0005"+
		"\u0003\u0000\u0000\u00b6\u00ba\u0003N\'\u0000\u00b7\u00b9\u0003\n\u0005"+
		"\u0000\u00b8\u00b7\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001\u0000\u0000"+
		"\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00ba\u00bb\u0001\u0000\u0000"+
		"\u0000\u00bb\u00be\u0001\u0000\u0000\u0000\u00bc\u00ba\u0001\u0000\u0000"+
		"\u0000\u00bd\u00bf\u0005t\u0000\u0000\u00be\u00bd\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\u00c4\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c2\u0003\u0002\u0001\u0000\u00c1\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c1\u00c2\u0001\u0000\u0000\u0000\u00c2\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c5\u0005t\u0000\u0000\u00c4\u00c1\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c6\u0001\u0000\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0005\u0004\u0000\u0000\u00c9\u00cb\u0001\u0000\u0000\u0000\u00ca"+
		"\u00a0\u0001\u0000\u0000\u0000\u00ca\u00b5\u0001\u0000\u0000\u0000\u00cb"+
		"\t\u0001\u0000\u0000\u0000\u00cc\u00cd\u0005\u0005\u0000\u0000\u00cd\u00d2"+
		"\u0003N\'\u0000\u00ce\u00cf\u0005\u0006\u0000\u0000\u00cf\u00d1\u0003"+
		"\n\u0005\u0000\u00d0\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d4\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d0\u0001\u0000\u0000\u0000\u00d2\u00d3\u0001\u0000"+
		"\u0000\u0000\u00d3\u000b\u0001\u0000\u0000\u0000\u00d4\u00d2\u0001\u0000"+
		"\u0000\u0000\u00d5\u00d6\u0007\u0000\u0000\u0000\u00d6\u00d7\u0003V+\u0000"+
		"\u00d7\r\u0001\u0000\u0000\u0000\u00d8\u00d9\u0007\u0001\u0000\u0000\u00d9"+
		"\u00da\u0003V+\u0000\u00da\u000f\u0001\u0000\u0000\u0000\u00db\u00dc\u0007"+
		"\u0002\u0000\u0000\u00dc\u00dd\u0003V+\u0000\u00dd\u0011\u0001\u0000\u0000"+
		"\u0000\u00de\u00df\u0007\u0003\u0000\u0000\u00df\u00e0\u0003V+\u0000\u00e0"+
		"\u0013\u0001\u0000\u0000\u0000\u00e1\u00e2\u0007\u0004\u0000\u0000\u00e2"+
		"\u0015\u0001\u0000\u0000\u0000\u00e3\u00e4\u0007\u0005\u0000\u0000\u00e4"+
		"\u0017\u0001\u0000\u0000\u0000\u00e5\u00e6\u0007\u0006\u0000\u0000\u00e6"+
		"\u0019\u0001\u0000\u0000\u0000\u00e7\u00e8\u0007\u0007\u0000\u0000\u00e8"+
		"\u001b\u0001\u0000\u0000\u0000\u00e9\u00ea\u0007\b\u0000\u0000\u00ea\u001d"+
		"\u0001\u0000\u0000\u0000\u00eb\u00ec\u0007\t\u0000\u0000\u00ec\u001f\u0001"+
		"\u0000\u0000\u0000\u00ed\u00ee\u0005/\u0000\u0000\u00ee\u00f2\u0003V+"+
		"\u0000\u00ef\u00f0\u00050\u0000\u0000\u00f0\u00f2\u0003V+\u0000\u00f1"+
		"\u00ed\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001\u0000\u0000\u0000\u00f2"+
		"!\u0001\u0000\u0000\u0000\u00f3\u00f4\u00051\u0000\u0000\u00f4\u00f8\u0003"+
		"V+\u0000\u00f5\u00f6\u00052\u0000\u0000\u00f6\u00f8\u0003V+\u0000\u00f7"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f7\u00f5\u0001\u0000\u0000\u0000\u00f8"+
		"#\u0001\u0000\u0000\u0000\u00f9\u00fa\u00053\u0000\u0000\u00fa\u0102\u0003"+
		"V+\u0000\u00fb\u00fc\u00054\u0000\u0000\u00fc\u0102\u0003V+\u0000\u00fd"+
		"\u00fe\u00055\u0000\u0000\u00fe\u0102\u0003V+\u0000\u00ff\u0100\u0005"+
		"6\u0000\u0000\u0100\u0102\u0003V+\u0000\u0101\u00f9\u0001\u0000\u0000"+
		"\u0000\u0101\u00fb\u0001\u0000\u0000\u0000\u0101\u00fd\u0001\u0000\u0000"+
		"\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0102%\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u00057\u0000\u0000\u0104\u010c\u0003V+\u0000\u0105\u0106"+
		"\u00058\u0000\u0000\u0106\u010c\u0003V+\u0000\u0107\u0108\u00059\u0000"+
		"\u0000\u0108\u010c\u0003V+\u0000\u0109\u010a\u0005:\u0000\u0000\u010a"+
		"\u010c\u0003V+\u0000\u010b\u0103\u0001\u0000\u0000\u0000\u010b\u0105\u0001"+
		"\u0000\u0000\u0000\u010b\u0107\u0001\u0000\u0000\u0000\u010b\u0109\u0001"+
		"\u0000\u0000\u0000\u010c\'\u0001\u0000\u0000\u0000\u010d\u010e\u0007\n"+
		"\u0000\u0000\u010e)\u0001\u0000\u0000\u0000\u010f\u0110\u0005=\u0000\u0000"+
		"\u0110\u0118\u0003V+\u0000\u0111\u0112\u0005>\u0000\u0000\u0112\u0118"+
		"\u0003V+\u0000\u0113\u0114\u00055\u0000\u0000\u0114\u0118\u0003V+\u0000"+
		"\u0115\u0116\u00056\u0000\u0000\u0116\u0118\u0003V+\u0000\u0117\u010f"+
		"\u0001\u0000\u0000\u0000\u0117\u0111\u0001\u0000\u0000\u0000\u0117\u0113"+
		"\u0001\u0000\u0000\u0000\u0117\u0115\u0001\u0000\u0000\u0000\u0118+\u0001"+
		"\u0000\u0000\u0000\u0119\u011a\u0005?\u0000\u0000\u011a\u011b\u0003V+"+
		"\u0000\u011b\u011c\u0003V+\u0000\u011c\u0122\u0001\u0000\u0000\u0000\u011d"+
		"\u011e\u0005@\u0000\u0000\u011e\u011f\u0003V+\u0000\u011f\u0120\u0003"+
		"V+\u0000\u0120\u0122\u0001\u0000\u0000\u0000\u0121\u0119\u0001\u0000\u0000"+
		"\u0000\u0121\u011d\u0001\u0000\u0000\u0000\u0122-\u0001\u0000\u0000\u0000"+
		"\u0123\u0124\u0005A\u0000\u0000\u0124\u0125\u0003V+\u0000\u0125\u0126"+
		"\u0003V+\u0000\u0126\u0134\u0001\u0000\u0000\u0000\u0127\u0128\u0005B"+
		"\u0000\u0000\u0128\u0129\u0003V+\u0000\u0129\u012a\u0003V+\u0000\u012a"+
		"\u0134\u0001\u0000\u0000\u0000\u012b\u012c\u0005C\u0000\u0000\u012c\u012d"+
		"\u0003V+\u0000\u012d\u012e\u0003V+\u0000\u012e\u0134\u0001\u0000\u0000"+
		"\u0000\u012f\u0130\u0005D\u0000\u0000\u0130\u0131\u0003V+\u0000\u0131"+
		"\u0132\u0003V+\u0000\u0132\u0134\u0001\u0000\u0000\u0000\u0133\u0123\u0001"+
		"\u0000\u0000\u0000\u0133\u0127\u0001\u0000\u0000\u0000\u0133\u012b\u0001"+
		"\u0000\u0000\u0000\u0133\u012f\u0001\u0000\u0000\u0000\u0134/\u0001\u0000"+
		"\u0000\u0000\u0135\u0136\u0005E\u0000\u0000\u0136\u0137\u0005m\u0000\u0000"+
		"\u0137\u013c\u0003P(\u0000\u0138\u0139\u0005F\u0000\u0000\u0139\u013a"+
		"\u0005m\u0000\u0000\u013a\u013c\u0003P(\u0000\u013b\u0135\u0001\u0000"+
		"\u0000\u0000\u013b\u0138\u0001\u0000\u0000\u0000\u013c1\u0001\u0000\u0000"+
		"\u0000\u013d\u0140\u0005G\u0000\u0000\u013e\u0141\u0003P(\u0000\u013f"+
		"\u0141\u0005m\u0000\u0000\u0140\u013e\u0001\u0000\u0000\u0000\u0140\u013f"+
		"\u0001\u0000\u0000\u0000\u0141\u0148\u0001\u0000\u0000\u0000\u0142\u0145"+
		"\u0005H\u0000\u0000\u0143\u0146\u0003P(\u0000\u0144\u0146\u0005m\u0000"+
		"\u0000\u0145\u0143\u0001\u0000\u0000\u0000\u0145\u0144\u0001\u0000\u0000"+
		"\u0000\u0146\u0148\u0001\u0000\u0000\u0000\u0147\u013d\u0001\u0000\u0000"+
		"\u0000\u0147\u0142\u0001\u0000\u0000\u0000\u01483\u0001\u0000\u0000\u0000"+
		"\u0149\u014a\u0005I\u0000\u0000\u014a\u0152\u0005r\u0000\u0000\u014b\u014c"+
		"\u0005J\u0000\u0000\u014c\u0152\u0005r\u0000\u0000\u014d\u014e\u0005K"+
		"\u0000\u0000\u014e\u0152\u0005r\u0000\u0000\u014f\u0150\u0005L\u0000\u0000"+
		"\u0150\u0152\u0005r\u0000\u0000\u0151\u0149\u0001\u0000\u0000\u0000\u0151"+
		"\u014b\u0001\u0000\u0000\u0000\u0151\u014d\u0001\u0000\u0000\u0000\u0151"+
		"\u014f\u0001\u0000\u0000\u0000\u01525\u0001\u0000\u0000\u0000\u0153\u0154"+
		"\u0005M\u0000\u0000\u0154\u0178\u0003V+\u0000\u0155\u0156\u0005M\u0000"+
		"\u0000\u0156\u0157\u0005N\u0000\u0000\u0157\u0158\u0003Z-\u0000\u0158"+
		"\u0159\u0003Z-\u0000\u0159\u015a\u0003Z-\u0000\u015a\u015b\u0005O\u0000"+
		"\u0000\u015b\u0178\u0001\u0000\u0000\u0000\u015c\u015d\u0005P\u0000\u0000"+
		"\u015d\u0178\u0003V+\u0000\u015e\u015f\u0005P\u0000\u0000\u015f\u0160"+
		"\u0005N\u0000\u0000\u0160\u0161\u0003Z-\u0000\u0161\u0162\u0003Z-\u0000"+
		"\u0162\u0163\u0003Z-\u0000\u0163\u0164\u0005O\u0000\u0000\u0164\u0178"+
		"\u0001\u0000\u0000\u0000\u0165\u0166\u0005Q\u0000\u0000\u0166\u0178\u0003"+
		"V+\u0000\u0167\u0168\u0005Q\u0000\u0000\u0168\u0169\u0005N\u0000\u0000"+
		"\u0169\u016a\u0003Z-\u0000\u016a\u016b\u0003Z-\u0000\u016b\u016c\u0003"+
		"Z-\u0000\u016c\u016d\u0005O\u0000\u0000\u016d\u0178\u0001\u0000\u0000"+
		"\u0000\u016e\u016f\u0005R\u0000\u0000\u016f\u0178\u0003V+\u0000\u0170"+
		"\u0171\u0005R\u0000\u0000\u0171\u0172\u0005N\u0000\u0000\u0172\u0173\u0003"+
		"Z-\u0000\u0173\u0174\u0003Z-\u0000\u0174\u0175\u0003Z-\u0000\u0175\u0176"+
		"\u0005O\u0000\u0000\u0176\u0178\u0001\u0000\u0000\u0000\u0177\u0153\u0001"+
		"\u0000\u0000\u0000\u0177\u0155\u0001\u0000\u0000\u0000\u0177\u015c\u0001"+
		"\u0000\u0000\u0000\u0177\u015e\u0001\u0000\u0000\u0000\u0177\u0165\u0001"+
		"\u0000\u0000\u0000\u0177\u0167\u0001\u0000\u0000\u0000\u0177\u016e\u0001"+
		"\u0000\u0000\u0000\u0177\u0170\u0001\u0000\u0000\u0000\u01787\u0001\u0000"+
		"\u0000\u0000\u0179\u017a\u0005S\u0000\u0000\u017a\u017b\u0003Z-\u0000"+
		"\u017b\u017c\u0003H$\u0000\u017c\u0182\u0001\u0000\u0000\u0000\u017d\u017e"+
		"\u0005T\u0000\u0000\u017e\u017f\u0003Z-\u0000\u017f\u0180\u0003H$\u0000"+
		"\u0180\u0182\u0001\u0000\u0000\u0000\u0181\u0179\u0001\u0000\u0000\u0000"+
		"\u0181\u017d\u0001\u0000\u0000\u0000\u01829\u0001\u0000\u0000\u0000\u0183"+
		"\u0184\u0005U\u0000\u0000\u0184\u0185\u0003J%\u0000\u0185\u0186\u0003"+
		"H$\u0000\u0186\u018c\u0001\u0000\u0000\u0000\u0187\u0188\u0005V\u0000"+
		"\u0000\u0188\u0189\u0003J%\u0000\u0189\u018a\u0003H$\u0000\u018a\u018c"+
		"\u0001\u0000\u0000\u0000\u018b\u0183\u0001\u0000\u0000\u0000\u018b\u0187"+
		"\u0001\u0000\u0000\u0000\u018c;\u0001\u0000\u0000\u0000\u018d\u018e\u0003"+
		"@ \u0000\u018e=\u0001\u0000\u0000\u0000\u018f\u0190\u0007\u000b\u0000"+
		"\u0000\u0190?\u0001\u0000\u0000\u0000\u0191\u0192\u0005Y\u0000\u0000\u0192"+
		"\u0196\u0003V+\u0000\u0193\u0194\u0005Z\u0000\u0000\u0194\u0196\u0003"+
		"V+\u0000\u0195\u0191\u0001\u0000\u0000\u0000\u0195\u0193\u0001\u0000\u0000"+
		"\u0000\u0196A\u0001\u0000\u0000\u0000\u0197\u0198\u0005[\u0000\u0000\u0198"+
		"\u0199\u0005N\u0000\u0000\u0199\u019a\u0003N\'\u0000\u019a\u019b\u0003"+
		"V+\u0000\u019b\u019c\u0003V+\u0000\u019c\u019d\u0003V+\u0000\u019d\u019e"+
		"\u0005O\u0000\u0000\u019e\u019f\u0003H$\u0000\u019f\u01aa\u0001\u0000"+
		"\u0000\u0000\u01a0\u01a1\u0005\\\u0000\u0000\u01a1\u01a2\u0005N\u0000"+
		"\u0000\u01a2\u01a3\u0003N\'\u0000\u01a3\u01a4\u0003V+\u0000\u01a4\u01a5"+
		"\u0003V+\u0000\u01a5\u01a6\u0003V+\u0000\u01a6\u01a7\u0005O\u0000\u0000"+
		"\u01a7\u01a8\u0003H$\u0000\u01a8\u01aa\u0001\u0000\u0000\u0000\u01a9\u0197"+
		"\u0001\u0000\u0000\u0000\u01a9\u01a0\u0001\u0000\u0000\u0000\u01aaC\u0001"+
		"\u0000\u0000\u0000\u01ab\u01ae\u0005]\u0000\u0000\u01ac\u01af\u0005m\u0000"+
		"\u0000\u01ad\u01af\u0003X,\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01ae"+
		"\u01ad\u0001\u0000\u0000\u0000\u01af\u01b6\u0001\u0000\u0000\u0000\u01b0"+
		"\u01b3\u0005^\u0000\u0000\u01b1\u01b4\u0005m\u0000\u0000\u01b2\u01b4\u0003"+
		"X,\u0000\u01b3\u01b1\u0001\u0000\u0000\u0000\u01b3\u01b2\u0001\u0000\u0000"+
		"\u0000\u01b4\u01b6\u0001\u0000\u0000\u0000\u01b5\u01ab\u0001\u0000\u0000"+
		"\u0000\u01b5\u01b0\u0001\u0000\u0000\u0000\u01b6E\u0001\u0000\u0000\u0000"+
		"\u01b7\u01b8\u0005_\u0000\u0000\u01b8\u01bc\u0003N\'\u0000\u01b9\u01ba"+
		"\u0005`\u0000\u0000\u01ba\u01bc\u0003N\'\u0000\u01bb\u01b7\u0001\u0000"+
		"\u0000\u0000\u01bb\u01b9\u0001\u0000\u0000\u0000\u01bcG\u0001\u0000\u0000"+
		"\u0000\u01bd\u01cd\u0005N\u0000\u0000\u01be\u01c0\u0007\f\u0000\u0000"+
		"\u01bf\u01be\u0001\u0000\u0000\u0000\u01c0\u01c3\u0001\u0000\u0000\u0000"+
		"\u01c1\u01bf\u0001\u0000\u0000\u0000\u01c1\u01c2\u0001\u0000\u0000\u0000"+
		"\u01c2\u01c4\u0001\u0000\u0000\u0000\u01c3\u01c1\u0001\u0000\u0000\u0000"+
		"\u01c4\u01c8\u0003\u0004\u0002\u0000\u01c5\u01c7\u0007\f\u0000\u0000\u01c6"+
		"\u01c5\u0001\u0000\u0000\u0000\u01c7\u01ca\u0001\u0000\u0000\u0000\u01c8"+
		"\u01c6\u0001\u0000\u0000\u0000\u01c8\u01c9\u0001\u0000\u0000\u0000\u01c9"+
		"\u01cc\u0001\u0000\u0000\u0000\u01ca\u01c8\u0001\u0000\u0000\u0000\u01cb"+
		"\u01c1\u0001\u0000\u0000\u0000\u01cc\u01cf\u0001\u0000\u0000\u0000\u01cd"+
		"\u01cb\u0001\u0000\u0000\u0000\u01cd\u01ce\u0001\u0000\u0000\u0000\u01ce"+
		"\u01d0\u0001\u0000\u0000\u0000\u01cf\u01cd\u0001\u0000\u0000\u0000\u01d0"+
		"\u01d1\u0005O\u0000\u0000\u01d1I\u0001\u0000\u0000\u0000\u01d2\u01d3\u0003"+
		"V+\u0000\u01d3\u01d4\u0003L&\u0000\u01d4\u01d5\u0003V+\u0000\u01d5K\u0001"+
		"\u0000\u0000\u0000\u01d6\u01d7\u0007\r\u0000\u0000\u01d7M\u0001\u0000"+
		"\u0000\u0000\u01d8\u01d9\u0005o\u0000\u0000\u01d9O\u0001\u0000\u0000\u0000"+
		"\u01da\u01de\u0005m\u0000\u0000\u01db\u01de\u0003V+\u0000\u01dc\u01de"+
		"\u0003X,\u0000\u01dd\u01da\u0001\u0000\u0000\u0000\u01dd\u01db\u0001\u0000"+
		"\u0000\u0000\u01dd\u01dc\u0001\u0000\u0000\u0000\u01deQ\u0001\u0000\u0000"+
		"\u0000\u01df\u01e1\u0007\u000e\u0000\u0000\u01e0\u01df\u0001\u0000\u0000"+
		"\u0000\u01e1\u01e4\u0001\u0000\u0000\u0000\u01e2\u01e0\u0001\u0000\u0000"+
		"\u0000\u01e2\u01e3\u0001\u0000\u0000\u0000\u01e3\u01e8\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e2\u0001\u0000\u0000\u0000\u01e5\u01e9\u0003Z-\u0000\u01e6"+
		"\u01e9\u0003X,\u0000\u01e7\u01e9\u0003<\u001e\u0000\u01e8\u01e5\u0001"+
		"\u0000\u0000\u0000\u01e8\u01e6\u0001\u0000\u0000\u0000\u01e8\u01e7\u0001"+
		"\u0000\u0000\u0000\u01e9S\u0001\u0000\u0000\u0000\u01ea\u01ef\u0003R)"+
		"\u0000\u01eb\u01ec\u0007\u000f\u0000\u0000\u01ec\u01ee\u0003R)\u0000\u01ed"+
		"\u01eb\u0001\u0000\u0000\u0000\u01ee\u01f1\u0001\u0000\u0000\u0000\u01ef"+
		"\u01ed\u0001\u0000\u0000\u0000\u01ef\u01f0\u0001\u0000\u0000\u0000\u01f0"+
		"U\u0001\u0000\u0000\u0000\u01f1\u01ef\u0001\u0000\u0000\u0000\u01f2\u01f7"+
		"\u0003T*\u0000\u01f3\u01f4\u0007\u000e\u0000\u0000\u01f4\u01f6\u0003T"+
		"*\u0000\u01f5\u01f3\u0001\u0000\u0000\u0000\u01f6\u01f9\u0001\u0000\u0000"+
		"\u0000\u01f7\u01f5\u0001\u0000\u0000\u0000\u01f7\u01f8\u0001\u0000\u0000"+
		"\u0000\u01f8W\u0001\u0000\u0000\u0000\u01f9\u01f7\u0001\u0000\u0000\u0000"+
		"\u01fa\u01fb\u0005\u0005\u0000\u0000\u01fb\u01fc\u0003N\'\u0000\u01fc"+
		"Y\u0001\u0000\u0000\u0000\u01fd\u01fe\u0007\u0010\u0000\u0000\u01fe[\u0001"+
		"\u0000\u0000\u0000\u01ff\u0200\u0005s\u0000\u0000\u0200]\u0001\u0000\u0000"+
		"\u00000_dgnqvy\u0097\u009d\u00a5\u00a9\u00ac\u00b1\u00ba\u00be\u00c1\u00c6"+
		"\u00ca\u00d2\u00f1\u00f7\u0101\u010b\u0117\u0121\u0133\u013b\u0140\u0145"+
		"\u0147\u0151\u0177\u0181\u018b\u0195\u01a9\u01ae\u01b3\u01b5\u01bb\u01c1"+
		"\u01c8\u01cd\u01dd\u01e2\u01e8\u01ef\u01f7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}