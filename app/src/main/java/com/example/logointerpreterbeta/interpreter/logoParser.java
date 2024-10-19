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
		T__94=95, T__95=96, T__96=97, T__97=98, T__98=99, T__99=100, STRINGLITERAL=101, 
		STRING=102, NUMBER=103, COMMENT=104, EOL=105, WS=106;
	public static final int
		RULE_prog = 0, RULE_line = 1, RULE_cmd = 2, RULE_setcornerrounding = 3, 
		RULE_setx = 4, RULE_sety = 5, RULE_settextsize = 6, RULE_setscreencolor = 7, 
		RULE_fill = 8, RULE_setpencolor = 9, RULE_setpensize = 10, RULE_arc = 11, 
		RULE_procedureInvocation = 12, RULE_procedureDeclaration = 13, RULE_parameterDeclarations = 14, 
		RULE_func_ = 15, RULE_repeat_ = 16, RULE_block = 17, RULE_ife = 18, RULE_comparison = 19, 
		RULE_comparisonOperator = 20, RULE_make = 21, RULE_print_ = 22, RULE_name = 23, 
		RULE_value = 24, RULE_signExpression = 25, RULE_multiplyingExpression = 26, 
		RULE_expression = 27, RULE_deref = 28, RULE_fd = 29, RULE_bk = 30, RULE_rt = 31, 
		RULE_lt = 32, RULE_cs = 33, RULE_pu = 34, RULE_pd = 35, RULE_ht = 36, 
		RULE_st = 37, RULE_home = 38, RULE_stop = 39, RULE_label = 40, RULE_setxy = 41, 
		RULE_random = 42, RULE_fore = 43, RULE_number = 44, RULE_comment = 45;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "line", "cmd", "setcornerrounding", "setx", "sety", "settextsize", 
			"setscreencolor", "fill", "setpencolor", "setpensize", "arc", "procedureInvocation", 
			"procedureDeclaration", "parameterDeclarations", "func_", "repeat_", 
			"block", "ife", "comparison", "comparisonOperator", "make", "print_", 
			"name", "value", "signExpression", "multiplyingExpression", "expression", 
			"deref", "fd", "bk", "rt", "lt", "cs", "pu", "pd", "ht", "st", "home", 
			"stop", "label", "setxy", "random", "fore", "number", "comment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'setcornerrounding'", "'0'", "'1'", "'SETCORNERROUNDING'", "'setx'", 
			"'SETX'", "'sety'", "'SETY'", "'sts'", "'STS'", "'setpensize'", "'SETPENSIZE'", 
			"'ssc'", "'SSC'", "'setscreencolor'", "'SETSCREENCOLOR'", "'fill'", "'FILL'", 
			"'spc'", "'SPC'", "'setpencolor'", "'SETPENCOLOR'", "'sps'", "'SPS'", 
			"'arc'", "'ARC'", "'to'", "'end'", "'TO'", "'END'", "':'", "','", "'repeat'", 
			"'REPEAT'", "'['", "']'", "'if'", "'IF'", "'<'", "'>'", "'='", "'<='", 
			"'>='", "'<>'", "'make'", "'MAKE'", "'print'", "'PRINT'", "'+'", "'-'", 
			"'*'", "'/'", "'fd'", "'forward'", "'FD'", "'FORWARD'", "'bk'", "'backward'", 
			"'BK'", "'BACKWARD'", "'rt'", "'right'", "'RT'", "'RIGHT'", "'lt'", "'left'", 
			"'LT'", "'LEFT'", "'cs'", "'clearscreen'", "'CS'", "'CLEARSCREEN'", "'pu'", 
			"'penup'", "'PU'", "'PENUP'", "'pd'", "'pendown'", "'PD'", "'PENDOWN'", 
			"'ht'", "'hideturtle'", "'HT'", "'HIDETURTLE'", "'st'", "'showturtle'", 
			"'ST'", "'SHOWTURTLE'", "'home'", "'HOME'", "'stop'", "'STOP'", "'label'", 
			"'LABEL'", "'setxy'", "'SETXY'", "'random'", "'RANDOM'", "'for'", "'FOR'"
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
			null, null, null, null, null, "STRINGLITERAL", "STRING", "NUMBER", "COMMENT", 
			"EOL", "WS"
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
			setState(96); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(93);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 5884105195519L) != 0)) {
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
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(101);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 5884105195519L) != 0)) {
				{
				setState(100);
				line();
				}
			}

			setState(103);
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
		public List<TerminalNode> WS() { return getTokens(logoParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(logoParser.WS, i);
		}
		public List<TerminalNode> EOL() { return getTokens(logoParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(logoParser.EOL, i);
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
			int _alt;
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__3:
			case T__4:
			case T__5:
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
			case T__32:
			case T__33:
			case T__36:
			case T__37:
			case T__44:
			case T__45:
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
			case T__98:
			case T__99:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(105);
					cmd();
					setState(109);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(106);
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
						setState(111);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
					}
					}
					}
					setState(114); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -8901207917592590L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 386547056639L) != 0) );
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(116);
					comment();
					}
				}

				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(119);
				comment();
				}
				break;
			case T__46:
			case T__47:
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				print_();
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COMMENT) {
					{
					setState(121);
					comment();
					}
				}

				}
				break;
			case T__26:
			case T__28:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				procedureDeclaration();
				}
				break;
			case WS:
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				match(WS);
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
		public SetscreencolorContext setscreencolor() {
			return getRuleContext(SetscreencolorContext.class,0);
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
			setState(155);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				repeat_();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(129);
				fd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(130);
				bk();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(131);
				rt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(132);
				lt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				cs();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(134);
				pu();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(135);
				pd();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(136);
				ht();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(137);
				st();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(138);
				home();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(139);
				label();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(140);
				setxy();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(141);
				make();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(142);
				procedureInvocation();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(143);
				ife();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(144);
				stop();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(145);
				fore();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(146);
				arc();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(147);
				setpensize();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(148);
				setpencolor();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(149);
				fill();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(150);
				setscreencolor();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(151);
				settextsize();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(152);
				sety();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(153);
				setx();
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(154);
				setcornerrounding();
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
	public static class SetcornerroundingContext extends ParserRuleContext {
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
		enterRule(_localctx, 6, RULE_setcornerrounding);
		int _la;
		try {
			setState(161);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(157);
				match(T__0);
				setState(158);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				match(T__3);
				setState(160);
				_la = _input.LA(1);
				if ( !(_la==T__1 || _la==T__2) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
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
		enterRule(_localctx, 8, RULE_setx);
		try {
			setState(167);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(163);
				match(T__4);
				setState(164);
				expression();
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				match(T__5);
				setState(166);
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
		enterRule(_localctx, 10, RULE_sety);
		try {
			setState(173);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__6:
				enterOuterAlt(_localctx, 1);
				{
				setState(169);
				match(T__6);
				setState(170);
				expression();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 2);
				{
				setState(171);
				match(T__7);
				setState(172);
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
		enterRule(_localctx, 12, RULE_settextsize);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__8:
				enterOuterAlt(_localctx, 1);
				{
				setState(175);
				match(T__8);
				setState(176);
				expression();
				}
				break;
			case T__9:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				match(T__9);
				setState(178);
				expression();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(179);
				match(T__10);
				setState(180);
				expression();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(181);
				match(T__11);
				setState(182);
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
	public static class SetscreencolorContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SetscreencolorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setscreencolor; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof logoVisitor ) return ((logoVisitor<? extends T>)visitor).visitSetscreencolor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetscreencolorContext setscreencolor() throws RecognitionException {
		SetscreencolorContext _localctx = new SetscreencolorContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_setscreencolor);
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(T__12);
				setState(186);
				expression();
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(187);
				match(T__13);
				setState(188);
				expression();
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(T__14);
				setState(190);
				expression();
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				match(T__15);
				setState(192);
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
		enterRule(_localctx, 16, RULE_fill);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			_la = _input.LA(1);
			if ( !(_la==T__16 || _la==T__17) ) {
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
	public static class SetpencolorContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 18, RULE_setpencolor);
		try {
			setState(205);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				setState(197);
				match(T__18);
				setState(198);
				expression();
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				match(T__19);
				setState(200);
				expression();
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(T__20);
				setState(202);
				expression();
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(203);
				match(T__21);
				setState(204);
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
		enterRule(_localctx, 20, RULE_setpensize);
		try {
			setState(215);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__22:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				match(T__22);
				setState(208);
				expression();
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 2);
				{
				setState(209);
				match(T__23);
				setState(210);
				expression();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				match(T__10);
				setState(212);
				expression();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				match(T__11);
				setState(214);
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
		enterRule(_localctx, 22, RULE_arc);
		try {
			setState(225);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(217);
				match(T__24);
				setState(218);
				expression();
				setState(219);
				expression();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				match(T__25);
				setState(222);
				expression();
				setState(223);
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
		enterRule(_localctx, 24, RULE_procedureInvocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			name();
			setState(231);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1688852007747584L) != 0) || ((((_la - 97)) & ~0x3f) == 0 && ((1L << (_la - 97)) & 67L) != 0)) {
				{
				{
				setState(228);
				expression();
				}
				}
				setState(233);
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
		enterRule(_localctx, 26, RULE_procedureDeclaration);
		int _la;
		try {
			setState(276);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__26:
				enterOuterAlt(_localctx, 1);
				{
				setState(234);
				match(T__26);
				setState(235);
				name();
				setState(239);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(236);
					parameterDeclarations();
					}
					}
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(243);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(242);
					match(EOL);
					}
					break;
				}
				setState(249); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 5884105195519L) != 0)) {
						{
						setState(245);
						line();
						}
					}

					setState(248);
					match(EOL);
					}
					}
					setState(251); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8083128451071L) != 0) );
				setState(253);
				match(T__27);
				}
				break;
			case T__28:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				match(T__28);
				setState(256);
				name();
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__30) {
					{
					{
					setState(257);
					parameterDeclarations();
					}
					}
					setState(262);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(264);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
				case 1:
					{
					setState(263);
					match(EOL);
					}
					break;
				}
				setState(270); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(267);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 5884105195519L) != 0)) {
						{
						setState(266);
						line();
						}
					}

					setState(269);
					match(EOL);
					}
					}
					setState(272); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & -8478994781437966L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 8083128451071L) != 0) );
				setState(274);
				match(T__29);
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
		enterRule(_localctx, 28, RULE_parameterDeclarations);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			match(T__30);
			setState(279);
			name();
			setState(284);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(280);
					match(T__31);
					setState(281);
					parameterDeclarations();
					}
					} 
				}
				setState(286);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
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
		enterRule(_localctx, 30, RULE_func_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(287);
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
		enterRule(_localctx, 32, RULE_repeat_);
		try {
			setState(297);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__32:
				enterOuterAlt(_localctx, 1);
				{
				setState(289);
				match(T__32);
				setState(290);
				number();
				setState(291);
				block();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 2);
				{
				setState(293);
				match(T__33);
				setState(294);
				number();
				setState(295);
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
		enterRule(_localctx, 34, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			match(T__34);
			setState(315);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & -8901207917592590L) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & 6983616823295L) != 0)) {
				{
				{
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==EOL || _la==WS) {
					{
					{
					setState(300);
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
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(306);
				cmd();
				setState(310);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(307);
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
					setState(312);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
				}
				}
				}
				setState(317);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(318);
			match(T__35);
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
		enterRule(_localctx, 36, RULE_ife);
		try {
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__36:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				match(T__36);
				setState(321);
				comparison();
				setState(322);
				block();
				}
				break;
			case T__37:
				enterOuterAlt(_localctx, 2);
				{
				setState(324);
				match(T__37);
				setState(325);
				comparison();
				setState(326);
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
		enterRule(_localctx, 38, RULE_comparison);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			expression();
			setState(331);
			comparisonOperator();
			setState(332);
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
		enterRule(_localctx, 40, RULE_comparisonOperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(334);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 34634616274944L) != 0)) ) {
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
		enterRule(_localctx, 42, RULE_make);
		try {
			setState(342);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__44:
				enterOuterAlt(_localctx, 1);
				{
				setState(336);
				match(T__44);
				setState(337);
				match(STRINGLITERAL);
				setState(338);
				value();
				}
				break;
			case T__45:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				match(T__45);
				setState(340);
				match(STRINGLITERAL);
				setState(341);
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
		enterRule(_localctx, 44, RULE_print_);
		try {
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__46:
				enterOuterAlt(_localctx, 1);
				{
				setState(344);
				match(T__46);
				setState(347);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
				case 1:
					{
					setState(345);
					value();
					}
					break;
				case 2:
					{
					setState(346);
					match(STRINGLITERAL);
					}
					break;
				}
				}
				break;
			case T__47:
				enterOuterAlt(_localctx, 2);
				{
				setState(349);
				match(T__47);
				setState(352);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
				case 1:
					{
					setState(350);
					value();
					}
					break;
				case 2:
					{
					setState(351);
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
		enterRule(_localctx, 46, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
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
		enterRule(_localctx, 48, RULE_value);
		try {
			setState(361);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				match(STRINGLITERAL);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(359);
				expression();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(360);
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
		enterRule(_localctx, 50, RULE_signExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__48 || _la==T__49) {
				{
				{
				setState(363);
				_la = _input.LA(1);
				if ( !(_la==T__48 || _la==T__49) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(368);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(372);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				{
				setState(369);
				number();
				}
				break;
			case T__30:
				{
				setState(370);
				deref();
				}
				break;
			case T__96:
			case T__97:
				{
				setState(371);
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
		enterRule(_localctx, 52, RULE_multiplyingExpression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			signExpression();
			setState(379);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(375);
					_la = _input.LA(1);
					if ( !(_la==T__50 || _la==T__51) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(376);
					signExpression();
					}
					} 
				}
				setState(381);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,40,_ctx);
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
		enterRule(_localctx, 54, RULE_expression);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			multiplyingExpression();
			setState(387);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(383);
					_la = _input.LA(1);
					if ( !(_la==T__48 || _la==T__49) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(384);
					multiplyingExpression();
					}
					} 
				}
				setState(389);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,41,_ctx);
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
		enterRule(_localctx, 56, RULE_deref);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			match(T__30);
			setState(391);
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
		enterRule(_localctx, 58, RULE_fd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(393);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 135107988821114880L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(394);
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
		enterRule(_localctx, 60, RULE_bk);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2161727821137838080L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(397);
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
		enterRule(_localctx, 62, RULE_rt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(399);
			_la = _input.LA(1);
			if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(400);
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
		enterRule(_localctx, 64, RULE_lt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			_la = _input.LA(1);
			if ( !(((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & 15L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(403);
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
		enterRule(_localctx, 66, RULE_cs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			_la = _input.LA(1);
			if ( !(((((_la - 69)) & ~0x3f) == 0 && ((1L << (_la - 69)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 68, RULE_pu);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			_la = _input.LA(1);
			if ( !(((((_la - 73)) & ~0x3f) == 0 && ((1L << (_la - 73)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 70, RULE_pd);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(409);
			_la = _input.LA(1);
			if ( !(((((_la - 77)) & ~0x3f) == 0 && ((1L << (_la - 77)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 72, RULE_ht);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			_la = _input.LA(1);
			if ( !(((((_la - 81)) & ~0x3f) == 0 && ((1L << (_la - 81)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 74, RULE_st);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			_la = _input.LA(1);
			if ( !(((((_la - 85)) & ~0x3f) == 0 && ((1L << (_la - 85)) & 15L) != 0)) ) {
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
		enterRule(_localctx, 76, RULE_home);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			_la = _input.LA(1);
			if ( !(_la==T__88 || _la==T__89) ) {
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
		enterRule(_localctx, 78, RULE_stop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			_la = _input.LA(1);
			if ( !(_la==T__90 || _la==T__91) ) {
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
	public static class LabelContext extends ParserRuleContext {
		public TerminalNode STRINGLITERAL() { return getToken(logoParser.STRINGLITERAL, 0); }
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
		enterRule(_localctx, 80, RULE_label);
		try {
			setState(423);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__92:
				enterOuterAlt(_localctx, 1);
				{
				setState(419);
				match(T__92);
				setState(420);
				match(STRINGLITERAL);
				}
				break;
			case T__93:
				enterOuterAlt(_localctx, 2);
				{
				setState(421);
				match(T__93);
				setState(422);
				match(STRINGLITERAL);
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
		enterRule(_localctx, 82, RULE_setxy);
		try {
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__94:
				enterOuterAlt(_localctx, 1);
				{
				setState(425);
				match(T__94);
				setState(426);
				expression();
				setState(427);
				expression();
				}
				break;
			case T__95:
				enterOuterAlt(_localctx, 2);
				{
				setState(429);
				match(T__95);
				setState(430);
				expression();
				setState(431);
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
		enterRule(_localctx, 84, RULE_random);
		try {
			setState(439);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__96:
				enterOuterAlt(_localctx, 1);
				{
				setState(435);
				match(T__96);
				setState(436);
				expression();
				}
				break;
			case T__97:
				enterOuterAlt(_localctx, 2);
				{
				setState(437);
				match(T__97);
				setState(438);
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
		enterRule(_localctx, 86, RULE_fore);
		try {
			setState(459);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__98:
				enterOuterAlt(_localctx, 1);
				{
				setState(441);
				match(T__98);
				setState(442);
				match(T__34);
				setState(443);
				name();
				setState(444);
				expression();
				setState(445);
				expression();
				setState(446);
				expression();
				setState(447);
				match(T__35);
				setState(448);
				block();
				}
				break;
			case T__99:
				enterOuterAlt(_localctx, 2);
				{
				setState(450);
				match(T__99);
				setState(451);
				match(T__34);
				setState(452);
				name();
				setState(453);
				expression();
				setState(454);
				expression();
				setState(455);
				expression();
				setState(456);
				match(T__35);
				setState(457);
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
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(logoParser.NUMBER, 0); }
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
		enterRule(_localctx, 88, RULE_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(NUMBER);
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
		enterRule(_localctx, 90, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
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
		"\u0004\u0001j\u01d2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
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
		"-\u0007-\u0001\u0000\u0003\u0000^\b\u0000\u0001\u0000\u0004\u0000a\b\u0000"+
		"\u000b\u0000\f\u0000b\u0001\u0000\u0003\u0000f\b\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0001\u0001\u0001\u0005\u0001l\b\u0001\n\u0001\f\u0001o\t"+
		"\u0001\u0004\u0001q\b\u0001\u000b\u0001\f\u0001r\u0001\u0001\u0003\u0001"+
		"v\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001{\b\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001\u007f\b\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002\u009c\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0003\u0003\u00a2\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0003\u0004\u00a8\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0003\u0005\u00ae\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\u00b8"+
		"\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0003\u0007\u00c2\b\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00ce\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u00d8\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00e2\b\u000b\u0001"+
		"\f\u0001\f\u0005\f\u00e6\b\f\n\f\f\f\u00e9\t\f\u0001\r\u0001\r\u0001\r"+
		"\u0005\r\u00ee\b\r\n\r\f\r\u00f1\t\r\u0001\r\u0003\r\u00f4\b\r\u0001\r"+
		"\u0003\r\u00f7\b\r\u0001\r\u0004\r\u00fa\b\r\u000b\r\f\r\u00fb\u0001\r"+
		"\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u0103\b\r\n\r\f\r\u0106\t\r\u0001"+
		"\r\u0003\r\u0109\b\r\u0001\r\u0003\r\u010c\b\r\u0001\r\u0004\r\u010f\b"+
		"\r\u000b\r\f\r\u0110\u0001\r\u0001\r\u0003\r\u0115\b\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u011b\b\u000e\n\u000e\f\u000e"+
		"\u011e\t\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u012a\b\u0010\u0001\u0011\u0001\u0011\u0005\u0011\u012e\b\u0011\n\u0011"+
		"\f\u0011\u0131\t\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0135\b\u0011"+
		"\n\u0011\f\u0011\u0138\t\u0011\u0005\u0011\u013a\b\u0011\n\u0011\f\u0011"+
		"\u013d\t\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012"+
		"\u0149\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u0157\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0003\u0016\u015c\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u0161\b\u0016\u0003\u0016\u0163\b\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0003\u0018\u016a\b\u0018\u0001\u0019\u0005"+
		"\u0019\u016d\b\u0019\n\u0019\f\u0019\u0170\t\u0019\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u0175\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0005\u001a\u017a\b\u001a\n\u001a\f\u001a\u017d\t\u001a\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0005\u001b\u0182\b\u001b\n\u001b\f\u001b\u0185\t\u001b"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f"+
		"\u0001 \u0001 \u0001 \u0001!\u0001!\u0001\"\u0001\"\u0001#\u0001#\u0001"+
		"$\u0001$\u0001%\u0001%\u0001&\u0001&\u0001\'\u0001\'\u0001(\u0001(\u0001"+
		"(\u0001(\u0003(\u01a8\b(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		")\u0001)\u0003)\u01b2\b)\u0001*\u0001*\u0001*\u0001*\u0003*\u01b8\b*\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001"+
		"+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0001+\u0003+\u01cc\b+\u0001"+
		",\u0001,\u0001-\u0001-\u0001-\u0000\u0000.\u0000\u0002\u0004\u0006\b\n"+
		"\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.0246"+
		"8:<>@BDFHJLNPRTVXZ\u0000\u0011\u0001\u0000ij\u0001\u0000\u0002\u0003\u0001"+
		"\u0000\u0011\u0012\u0001\u0000\',\u0001\u000012\u0001\u000034\u0001\u0000"+
		"58\u0001\u00009<\u0001\u0000=@\u0001\u0000AD\u0001\u0000EH\u0001\u0000"+
		"IL\u0001\u0000MP\u0001\u0000QT\u0001\u0000UX\u0001\u0000YZ\u0001\u0000"+
		"[\\\u01f7\u0000`\u0001\u0000\u0000\u0000\u0002~\u0001\u0000\u0000\u0000"+
		"\u0004\u009b\u0001\u0000\u0000\u0000\u0006\u00a1\u0001\u0000\u0000\u0000"+
		"\b\u00a7\u0001\u0000\u0000\u0000\n\u00ad\u0001\u0000\u0000\u0000\f\u00b7"+
		"\u0001\u0000\u0000\u0000\u000e\u00c1\u0001\u0000\u0000\u0000\u0010\u00c3"+
		"\u0001\u0000\u0000\u0000\u0012\u00cd\u0001\u0000\u0000\u0000\u0014\u00d7"+
		"\u0001\u0000\u0000\u0000\u0016\u00e1\u0001\u0000\u0000\u0000\u0018\u00e3"+
		"\u0001\u0000\u0000\u0000\u001a\u0114\u0001\u0000\u0000\u0000\u001c\u0116"+
		"\u0001\u0000\u0000\u0000\u001e\u011f\u0001\u0000\u0000\u0000 \u0129\u0001"+
		"\u0000\u0000\u0000\"\u012b\u0001\u0000\u0000\u0000$\u0148\u0001\u0000"+
		"\u0000\u0000&\u014a\u0001\u0000\u0000\u0000(\u014e\u0001\u0000\u0000\u0000"+
		"*\u0156\u0001\u0000\u0000\u0000,\u0162\u0001\u0000\u0000\u0000.\u0164"+
		"\u0001\u0000\u0000\u00000\u0169\u0001\u0000\u0000\u00002\u016e\u0001\u0000"+
		"\u0000\u00004\u0176\u0001\u0000\u0000\u00006\u017e\u0001\u0000\u0000\u0000"+
		"8\u0186\u0001\u0000\u0000\u0000:\u0189\u0001\u0000\u0000\u0000<\u018c"+
		"\u0001\u0000\u0000\u0000>\u018f\u0001\u0000\u0000\u0000@\u0192\u0001\u0000"+
		"\u0000\u0000B\u0195\u0001\u0000\u0000\u0000D\u0197\u0001\u0000\u0000\u0000"+
		"F\u0199\u0001\u0000\u0000\u0000H\u019b\u0001\u0000\u0000\u0000J\u019d"+
		"\u0001\u0000\u0000\u0000L\u019f\u0001\u0000\u0000\u0000N\u01a1\u0001\u0000"+
		"\u0000\u0000P\u01a7\u0001\u0000\u0000\u0000R\u01b1\u0001\u0000\u0000\u0000"+
		"T\u01b7\u0001\u0000\u0000\u0000V\u01cb\u0001\u0000\u0000\u0000X\u01cd"+
		"\u0001\u0000\u0000\u0000Z\u01cf\u0001\u0000\u0000\u0000\\^\u0003\u0002"+
		"\u0001\u0000]\\\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^_\u0001"+
		"\u0000\u0000\u0000_a\u0005i\u0000\u0000`]\u0001\u0000\u0000\u0000ab\u0001"+
		"\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000"+
		"ce\u0001\u0000\u0000\u0000df\u0003\u0002\u0001\u0000ed\u0001\u0000\u0000"+
		"\u0000ef\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000gh\u0005\u0000"+
		"\u0000\u0001h\u0001\u0001\u0000\u0000\u0000im\u0003\u0004\u0002\u0000"+
		"jl\u0007\u0000\u0000\u0000kj\u0001\u0000\u0000\u0000lo\u0001\u0000\u0000"+
		"\u0000mk\u0001\u0000\u0000\u0000mn\u0001\u0000\u0000\u0000nq\u0001\u0000"+
		"\u0000\u0000om\u0001\u0000\u0000\u0000pi\u0001\u0000\u0000\u0000qr\u0001"+
		"\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000"+
		"su\u0001\u0000\u0000\u0000tv\u0003Z-\u0000ut\u0001\u0000\u0000\u0000u"+
		"v\u0001\u0000\u0000\u0000v\u007f\u0001\u0000\u0000\u0000w\u007f\u0003"+
		"Z-\u0000xz\u0003,\u0016\u0000y{\u0003Z-\u0000zy\u0001\u0000\u0000\u0000"+
		"z{\u0001\u0000\u0000\u0000{\u007f\u0001\u0000\u0000\u0000|\u007f\u0003"+
		"\u001a\r\u0000}\u007f\u0005j\u0000\u0000~p\u0001\u0000\u0000\u0000~w\u0001"+
		"\u0000\u0000\u0000~x\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"~}\u0001\u0000\u0000\u0000\u007f\u0003\u0001\u0000\u0000\u0000\u0080\u009c"+
		"\u0003 \u0010\u0000\u0081\u009c\u0003:\u001d\u0000\u0082\u009c\u0003<"+
		"\u001e\u0000\u0083\u009c\u0003>\u001f\u0000\u0084\u009c\u0003@ \u0000"+
		"\u0085\u009c\u0003B!\u0000\u0086\u009c\u0003D\"\u0000\u0087\u009c\u0003"+
		"F#\u0000\u0088\u009c\u0003H$\u0000\u0089\u009c\u0003J%\u0000\u008a\u009c"+
		"\u0003L&\u0000\u008b\u009c\u0003P(\u0000\u008c\u009c\u0003R)\u0000\u008d"+
		"\u009c\u0003*\u0015\u0000\u008e\u009c\u0003\u0018\f\u0000\u008f\u009c"+
		"\u0003$\u0012\u0000\u0090\u009c\u0003N\'\u0000\u0091\u009c\u0003V+\u0000"+
		"\u0092\u009c\u0003\u0016\u000b\u0000\u0093\u009c\u0003\u0014\n\u0000\u0094"+
		"\u009c\u0003\u0012\t\u0000\u0095\u009c\u0003\u0010\b\u0000\u0096\u009c"+
		"\u0003\u000e\u0007\u0000\u0097\u009c\u0003\f\u0006\u0000\u0098\u009c\u0003"+
		"\n\u0005\u0000\u0099\u009c\u0003\b\u0004\u0000\u009a\u009c\u0003\u0006"+
		"\u0003\u0000\u009b\u0080\u0001\u0000\u0000\u0000\u009b\u0081\u0001\u0000"+
		"\u0000\u0000\u009b\u0082\u0001\u0000\u0000\u0000\u009b\u0083\u0001\u0000"+
		"\u0000\u0000\u009b\u0084\u0001\u0000\u0000\u0000\u009b\u0085\u0001\u0000"+
		"\u0000\u0000\u009b\u0086\u0001\u0000\u0000\u0000\u009b\u0087\u0001\u0000"+
		"\u0000\u0000\u009b\u0088\u0001\u0000\u0000\u0000\u009b\u0089\u0001\u0000"+
		"\u0000\u0000\u009b\u008a\u0001\u0000\u0000\u0000\u009b\u008b\u0001\u0000"+
		"\u0000\u0000\u009b\u008c\u0001\u0000\u0000\u0000\u009b\u008d\u0001\u0000"+
		"\u0000\u0000\u009b\u008e\u0001\u0000\u0000\u0000\u009b\u008f\u0001\u0000"+
		"\u0000\u0000\u009b\u0090\u0001\u0000\u0000\u0000\u009b\u0091\u0001\u0000"+
		"\u0000\u0000\u009b\u0092\u0001\u0000\u0000\u0000\u009b\u0093\u0001\u0000"+
		"\u0000\u0000\u009b\u0094\u0001\u0000\u0000\u0000\u009b\u0095\u0001\u0000"+
		"\u0000\u0000\u009b\u0096\u0001\u0000\u0000\u0000\u009b\u0097\u0001\u0000"+
		"\u0000\u0000\u009b\u0098\u0001\u0000\u0000\u0000\u009b\u0099\u0001\u0000"+
		"\u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u0005\u0001\u0000"+
		"\u0000\u0000\u009d\u009e\u0005\u0001\u0000\u0000\u009e\u00a2\u0007\u0001"+
		"\u0000\u0000\u009f\u00a0\u0005\u0004\u0000\u0000\u00a0\u00a2\u0007\u0001"+
		"\u0000\u0000\u00a1\u009d\u0001\u0000\u0000\u0000\u00a1\u009f\u0001\u0000"+
		"\u0000\u0000\u00a2\u0007\u0001\u0000\u0000\u0000\u00a3\u00a4\u0005\u0005"+
		"\u0000\u0000\u00a4\u00a8\u00036\u001b\u0000\u00a5\u00a6\u0005\u0006\u0000"+
		"\u0000\u00a6\u00a8\u00036\u001b\u0000\u00a7\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a8\t\u0001\u0000\u0000\u0000\u00a9"+
		"\u00aa\u0005\u0007\u0000\u0000\u00aa\u00ae\u00036\u001b\u0000\u00ab\u00ac"+
		"\u0005\b\u0000\u0000\u00ac\u00ae\u00036\u001b\u0000\u00ad\u00a9\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ae\u000b\u0001"+
		"\u0000\u0000\u0000\u00af\u00b0\u0005\t\u0000\u0000\u00b0\u00b8\u00036"+
		"\u001b\u0000\u00b1\u00b2\u0005\n\u0000\u0000\u00b2\u00b8\u00036\u001b"+
		"\u0000\u00b3\u00b4\u0005\u000b\u0000\u0000\u00b4\u00b8\u00036\u001b\u0000"+
		"\u00b5\u00b6\u0005\f\u0000\u0000\u00b6\u00b8\u00036\u001b\u0000\u00b7"+
		"\u00af\u0001\u0000\u0000\u0000\u00b7\u00b1\u0001\u0000\u0000\u0000\u00b7"+
		"\u00b3\u0001\u0000\u0000\u0000\u00b7\u00b5\u0001\u0000\u0000\u0000\u00b8"+
		"\r\u0001\u0000\u0000\u0000\u00b9\u00ba\u0005\r\u0000\u0000\u00ba\u00c2"+
		"\u00036\u001b\u0000\u00bb\u00bc\u0005\u000e\u0000\u0000\u00bc\u00c2\u0003"+
		"6\u001b\u0000\u00bd\u00be\u0005\u000f\u0000\u0000\u00be\u00c2\u00036\u001b"+
		"\u0000\u00bf\u00c0\u0005\u0010\u0000\u0000\u00c0\u00c2\u00036\u001b\u0000"+
		"\u00c1\u00b9\u0001\u0000\u0000\u0000\u00c1\u00bb\u0001\u0000\u0000\u0000"+
		"\u00c1\u00bd\u0001\u0000\u0000\u0000\u00c1\u00bf\u0001\u0000\u0000\u0000"+
		"\u00c2\u000f\u0001\u0000\u0000\u0000\u00c3\u00c4\u0007\u0002\u0000\u0000"+
		"\u00c4\u0011\u0001\u0000\u0000\u0000\u00c5\u00c6\u0005\u0013\u0000\u0000"+
		"\u00c6\u00ce\u00036\u001b\u0000\u00c7\u00c8\u0005\u0014\u0000\u0000\u00c8"+
		"\u00ce\u00036\u001b\u0000\u00c9\u00ca\u0005\u0015\u0000\u0000\u00ca\u00ce"+
		"\u00036\u001b\u0000\u00cb\u00cc\u0005\u0016\u0000\u0000\u00cc\u00ce\u0003"+
		"6\u001b\u0000\u00cd\u00c5\u0001\u0000\u0000\u0000\u00cd\u00c7\u0001\u0000"+
		"\u0000\u0000\u00cd\u00c9\u0001\u0000\u0000\u0000\u00cd\u00cb\u0001\u0000"+
		"\u0000\u0000\u00ce\u0013\u0001\u0000\u0000\u0000\u00cf\u00d0\u0005\u0017"+
		"\u0000\u0000\u00d0\u00d8\u00036\u001b\u0000\u00d1\u00d2\u0005\u0018\u0000"+
		"\u0000\u00d2\u00d8\u00036\u001b\u0000\u00d3\u00d4\u0005\u000b\u0000\u0000"+
		"\u00d4\u00d8\u00036\u001b\u0000\u00d5\u00d6\u0005\f\u0000\u0000\u00d6"+
		"\u00d8\u00036\u001b\u0000\u00d7\u00cf\u0001\u0000\u0000\u0000\u00d7\u00d1"+
		"\u0001\u0000\u0000\u0000\u00d7\u00d3\u0001\u0000\u0000\u0000\u00d7\u00d5"+
		"\u0001\u0000\u0000\u0000\u00d8\u0015\u0001\u0000\u0000\u0000\u00d9\u00da"+
		"\u0005\u0019\u0000\u0000\u00da\u00db\u00036\u001b\u0000\u00db\u00dc\u0003"+
		"6\u001b\u0000\u00dc\u00e2\u0001\u0000\u0000\u0000\u00dd\u00de\u0005\u001a"+
		"\u0000\u0000\u00de\u00df\u00036\u001b\u0000\u00df\u00e0\u00036\u001b\u0000"+
		"\u00e0\u00e2\u0001\u0000\u0000\u0000\u00e1\u00d9\u0001\u0000\u0000\u0000"+
		"\u00e1\u00dd\u0001\u0000\u0000\u0000\u00e2\u0017\u0001\u0000\u0000\u0000"+
		"\u00e3\u00e7\u0003.\u0017\u0000\u00e4\u00e6\u00036\u001b\u0000\u00e5\u00e4"+
		"\u0001\u0000\u0000\u0000\u00e6\u00e9\u0001\u0000\u0000\u0000\u00e7\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000\u00e8\u0019"+
		"\u0001\u0000\u0000\u0000\u00e9\u00e7\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0005\u001b\u0000\u0000\u00eb\u00ef\u0003.\u0017\u0000\u00ec\u00ee\u0003"+
		"\u001c\u000e\u0000\u00ed\u00ec\u0001\u0000\u0000\u0000\u00ee\u00f1\u0001"+
		"\u0000\u0000\u0000\u00ef\u00ed\u0001\u0000\u0000\u0000\u00ef\u00f0\u0001"+
		"\u0000\u0000\u0000\u00f0\u00f3\u0001\u0000\u0000\u0000\u00f1\u00ef\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f4\u0005i\u0000\u0000\u00f3\u00f2\u0001\u0000"+
		"\u0000\u0000\u00f3\u00f4\u0001\u0000\u0000\u0000\u00f4\u00f9\u0001\u0000"+
		"\u0000\u0000\u00f5\u00f7\u0003\u0002\u0001\u0000\u00f6\u00f5\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f7\u0001\u0000\u0000\u0000\u00f7\u00f8\u0001\u0000"+
		"\u0000\u0000\u00f8\u00fa\u0005i\u0000\u0000\u00f9\u00f6\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\u0001\u0000\u0000\u0000\u00fb\u00f9\u0001\u0000\u0000"+
		"\u0000\u00fb\u00fc\u0001\u0000\u0000\u0000\u00fc\u00fd\u0001\u0000\u0000"+
		"\u0000\u00fd\u00fe\u0005\u001c\u0000\u0000\u00fe\u0115\u0001\u0000\u0000"+
		"\u0000\u00ff\u0100\u0005\u001d\u0000\u0000\u0100\u0104\u0003.\u0017\u0000"+
		"\u0101\u0103\u0003\u001c\u000e\u0000\u0102\u0101\u0001\u0000\u0000\u0000"+
		"\u0103\u0106\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000"+
		"\u0104\u0105\u0001\u0000\u0000\u0000\u0105\u0108\u0001\u0000\u0000\u0000"+
		"\u0106\u0104\u0001\u0000\u0000\u0000\u0107\u0109\u0005i\u0000\u0000\u0108"+
		"\u0107\u0001\u0000\u0000\u0000\u0108\u0109\u0001\u0000\u0000\u0000\u0109"+
		"\u010e\u0001\u0000\u0000\u0000\u010a\u010c\u0003\u0002\u0001\u0000\u010b"+
		"\u010a\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c"+
		"\u010d\u0001\u0000\u0000\u0000\u010d\u010f\u0005i\u0000\u0000\u010e\u010b"+
		"\u0001\u0000\u0000\u0000\u010f\u0110\u0001\u0000\u0000\u0000\u0110\u010e"+
		"\u0001\u0000\u0000\u0000\u0110\u0111\u0001\u0000\u0000\u0000\u0111\u0112"+
		"\u0001\u0000\u0000\u0000\u0112\u0113\u0005\u001e\u0000\u0000\u0113\u0115"+
		"\u0001\u0000\u0000\u0000\u0114\u00ea\u0001\u0000\u0000\u0000\u0114\u00ff"+
		"\u0001\u0000\u0000\u0000\u0115\u001b\u0001\u0000\u0000\u0000\u0116\u0117"+
		"\u0005\u001f\u0000\u0000\u0117\u011c\u0003.\u0017\u0000\u0118\u0119\u0005"+
		" \u0000\u0000\u0119\u011b\u0003\u001c\u000e\u0000\u011a\u0118\u0001\u0000"+
		"\u0000\u0000\u011b\u011e\u0001\u0000\u0000\u0000\u011c\u011a\u0001\u0000"+
		"\u0000\u0000\u011c\u011d\u0001\u0000\u0000\u0000\u011d\u001d\u0001\u0000"+
		"\u0000\u0000\u011e\u011c\u0001\u0000\u0000\u0000\u011f\u0120\u0003T*\u0000"+
		"\u0120\u001f\u0001\u0000\u0000\u0000\u0121\u0122\u0005!\u0000\u0000\u0122"+
		"\u0123\u0003X,\u0000\u0123\u0124\u0003\"\u0011\u0000\u0124\u012a\u0001"+
		"\u0000\u0000\u0000\u0125\u0126\u0005\"\u0000\u0000\u0126\u0127\u0003X"+
		",\u0000\u0127\u0128\u0003\"\u0011\u0000\u0128\u012a\u0001\u0000\u0000"+
		"\u0000\u0129\u0121\u0001\u0000\u0000\u0000\u0129\u0125\u0001\u0000\u0000"+
		"\u0000\u012a!\u0001\u0000\u0000\u0000\u012b\u013b\u0005#\u0000\u0000\u012c"+
		"\u012e\u0007\u0000\u0000\u0000\u012d\u012c\u0001\u0000\u0000\u0000\u012e"+
		"\u0131\u0001\u0000\u0000\u0000\u012f\u012d\u0001\u0000\u0000\u0000\u012f"+
		"\u0130\u0001\u0000\u0000\u0000\u0130\u0132\u0001\u0000\u0000\u0000\u0131"+
		"\u012f\u0001\u0000\u0000\u0000\u0132\u0136\u0003\u0004\u0002\u0000\u0133"+
		"\u0135\u0007\u0000\u0000\u0000\u0134\u0133\u0001\u0000\u0000\u0000\u0135"+
		"\u0138\u0001\u0000\u0000\u0000\u0136\u0134\u0001\u0000\u0000\u0000\u0136"+
		"\u0137\u0001\u0000\u0000\u0000\u0137\u013a\u0001\u0000\u0000\u0000\u0138"+
		"\u0136\u0001\u0000\u0000\u0000\u0139\u012f\u0001\u0000\u0000\u0000\u013a"+
		"\u013d\u0001\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013b"+
		"\u013c\u0001\u0000\u0000\u0000\u013c\u013e\u0001\u0000\u0000\u0000\u013d"+
		"\u013b\u0001\u0000\u0000\u0000\u013e\u013f\u0005$\u0000\u0000\u013f#\u0001"+
		"\u0000\u0000\u0000\u0140\u0141\u0005%\u0000\u0000\u0141\u0142\u0003&\u0013"+
		"\u0000\u0142\u0143\u0003\"\u0011\u0000\u0143\u0149\u0001\u0000\u0000\u0000"+
		"\u0144\u0145\u0005&\u0000\u0000\u0145\u0146\u0003&\u0013\u0000\u0146\u0147"+
		"\u0003\"\u0011\u0000\u0147\u0149\u0001\u0000\u0000\u0000\u0148\u0140\u0001"+
		"\u0000\u0000\u0000\u0148\u0144\u0001\u0000\u0000\u0000\u0149%\u0001\u0000"+
		"\u0000\u0000\u014a\u014b\u00036\u001b\u0000\u014b\u014c\u0003(\u0014\u0000"+
		"\u014c\u014d\u00036\u001b\u0000\u014d\'\u0001\u0000\u0000\u0000\u014e"+
		"\u014f\u0007\u0003\u0000\u0000\u014f)\u0001\u0000\u0000\u0000\u0150\u0151"+
		"\u0005-\u0000\u0000\u0151\u0152\u0005e\u0000\u0000\u0152\u0157\u00030"+
		"\u0018\u0000\u0153\u0154\u0005.\u0000\u0000\u0154\u0155\u0005e\u0000\u0000"+
		"\u0155\u0157\u00030\u0018\u0000\u0156\u0150\u0001\u0000\u0000\u0000\u0156"+
		"\u0153\u0001\u0000\u0000\u0000\u0157+\u0001\u0000\u0000\u0000\u0158\u015b"+
		"\u0005/\u0000\u0000\u0159\u015c\u00030\u0018\u0000\u015a\u015c\u0005e"+
		"\u0000\u0000\u015b\u0159\u0001\u0000\u0000\u0000\u015b\u015a\u0001\u0000"+
		"\u0000\u0000\u015c\u0163\u0001\u0000\u0000\u0000\u015d\u0160\u00050\u0000"+
		"\u0000\u015e\u0161\u00030\u0018\u0000\u015f\u0161\u0005e\u0000\u0000\u0160"+
		"\u015e\u0001\u0000\u0000\u0000\u0160\u015f\u0001\u0000\u0000\u0000\u0161"+
		"\u0163\u0001\u0000\u0000\u0000\u0162\u0158\u0001\u0000\u0000\u0000\u0162"+
		"\u015d\u0001\u0000\u0000\u0000\u0163-\u0001\u0000\u0000\u0000\u0164\u0165"+
		"\u0005f\u0000\u0000\u0165/\u0001\u0000\u0000\u0000\u0166\u016a\u0005e"+
		"\u0000\u0000\u0167\u016a\u00036\u001b\u0000\u0168\u016a\u00038\u001c\u0000"+
		"\u0169\u0166\u0001\u0000\u0000\u0000\u0169\u0167\u0001\u0000\u0000\u0000"+
		"\u0169\u0168\u0001\u0000\u0000\u0000\u016a1\u0001\u0000\u0000\u0000\u016b"+
		"\u016d\u0007\u0004\u0000\u0000\u016c\u016b\u0001\u0000\u0000\u0000\u016d"+
		"\u0170\u0001\u0000\u0000\u0000\u016e\u016c\u0001\u0000\u0000\u0000\u016e"+
		"\u016f\u0001\u0000\u0000\u0000\u016f\u0174\u0001\u0000\u0000\u0000\u0170"+
		"\u016e\u0001\u0000\u0000\u0000\u0171\u0175\u0003X,\u0000\u0172\u0175\u0003"+
		"8\u001c\u0000\u0173\u0175\u0003\u001e\u000f\u0000\u0174\u0171\u0001\u0000"+
		"\u0000\u0000\u0174\u0172\u0001\u0000\u0000\u0000\u0174\u0173\u0001\u0000"+
		"\u0000\u0000\u01753\u0001\u0000\u0000\u0000\u0176\u017b\u00032\u0019\u0000"+
		"\u0177\u0178\u0007\u0005\u0000\u0000\u0178\u017a\u00032\u0019\u0000\u0179"+
		"\u0177\u0001\u0000\u0000\u0000\u017a\u017d\u0001\u0000\u0000\u0000\u017b"+
		"\u0179\u0001\u0000\u0000\u0000\u017b\u017c\u0001\u0000\u0000\u0000\u017c"+
		"5\u0001\u0000\u0000\u0000\u017d\u017b\u0001\u0000\u0000\u0000\u017e\u0183"+
		"\u00034\u001a\u0000\u017f\u0180\u0007\u0004\u0000\u0000\u0180\u0182\u0003"+
		"4\u001a\u0000\u0181\u017f\u0001\u0000\u0000\u0000\u0182\u0185\u0001\u0000"+
		"\u0000\u0000\u0183\u0181\u0001\u0000\u0000\u0000\u0183\u0184\u0001\u0000"+
		"\u0000\u0000\u01847\u0001\u0000\u0000\u0000\u0185\u0183\u0001\u0000\u0000"+
		"\u0000\u0186\u0187\u0005\u001f\u0000\u0000\u0187\u0188\u0003.\u0017\u0000"+
		"\u01889\u0001\u0000\u0000\u0000\u0189\u018a\u0007\u0006\u0000\u0000\u018a"+
		"\u018b\u00036\u001b\u0000\u018b;\u0001\u0000\u0000\u0000\u018c\u018d\u0007"+
		"\u0007\u0000\u0000\u018d\u018e\u00036\u001b\u0000\u018e=\u0001\u0000\u0000"+
		"\u0000\u018f\u0190\u0007\b\u0000\u0000\u0190\u0191\u00036\u001b\u0000"+
		"\u0191?\u0001\u0000\u0000\u0000\u0192\u0193\u0007\t\u0000\u0000\u0193"+
		"\u0194\u00036\u001b\u0000\u0194A\u0001\u0000\u0000\u0000\u0195\u0196\u0007"+
		"\n\u0000\u0000\u0196C\u0001\u0000\u0000\u0000\u0197\u0198\u0007\u000b"+
		"\u0000\u0000\u0198E\u0001\u0000\u0000\u0000\u0199\u019a\u0007\f\u0000"+
		"\u0000\u019aG\u0001\u0000\u0000\u0000\u019b\u019c\u0007\r\u0000\u0000"+
		"\u019cI\u0001\u0000\u0000\u0000\u019d\u019e\u0007\u000e\u0000\u0000\u019e"+
		"K\u0001\u0000\u0000\u0000\u019f\u01a0\u0007\u000f\u0000\u0000\u01a0M\u0001"+
		"\u0000\u0000\u0000\u01a1\u01a2\u0007\u0010\u0000\u0000\u01a2O\u0001\u0000"+
		"\u0000\u0000\u01a3\u01a4\u0005]\u0000\u0000\u01a4\u01a8\u0005e\u0000\u0000"+
		"\u01a5\u01a6\u0005^\u0000\u0000\u01a6\u01a8\u0005e\u0000\u0000\u01a7\u01a3"+
		"\u0001\u0000\u0000\u0000\u01a7\u01a5\u0001\u0000\u0000\u0000\u01a8Q\u0001"+
		"\u0000\u0000\u0000\u01a9\u01aa\u0005_\u0000\u0000\u01aa\u01ab\u00036\u001b"+
		"\u0000\u01ab\u01ac\u00036\u001b\u0000\u01ac\u01b2\u0001\u0000\u0000\u0000"+
		"\u01ad\u01ae\u0005`\u0000\u0000\u01ae\u01af\u00036\u001b\u0000\u01af\u01b0"+
		"\u00036\u001b\u0000\u01b0\u01b2\u0001\u0000\u0000\u0000\u01b1\u01a9\u0001"+
		"\u0000\u0000\u0000\u01b1\u01ad\u0001\u0000\u0000\u0000\u01b2S\u0001\u0000"+
		"\u0000\u0000\u01b3\u01b4\u0005a\u0000\u0000\u01b4\u01b8\u00036\u001b\u0000"+
		"\u01b5\u01b6\u0005b\u0000\u0000\u01b6\u01b8\u00036\u001b\u0000\u01b7\u01b3"+
		"\u0001\u0000\u0000\u0000\u01b7\u01b5\u0001\u0000\u0000\u0000\u01b8U\u0001"+
		"\u0000\u0000\u0000\u01b9\u01ba\u0005c\u0000\u0000\u01ba\u01bb\u0005#\u0000"+
		"\u0000\u01bb\u01bc\u0003.\u0017\u0000\u01bc\u01bd\u00036\u001b\u0000\u01bd"+
		"\u01be\u00036\u001b\u0000\u01be\u01bf\u00036\u001b\u0000\u01bf\u01c0\u0005"+
		"$\u0000\u0000\u01c0\u01c1\u0003\"\u0011\u0000\u01c1\u01cc\u0001\u0000"+
		"\u0000\u0000\u01c2\u01c3\u0005d\u0000\u0000\u01c3\u01c4\u0005#\u0000\u0000"+
		"\u01c4\u01c5\u0003.\u0017\u0000\u01c5\u01c6\u00036\u001b\u0000\u01c6\u01c7"+
		"\u00036\u001b\u0000\u01c7\u01c8\u00036\u001b\u0000\u01c8\u01c9\u0005$"+
		"\u0000\u0000\u01c9\u01ca\u0003\"\u0011\u0000\u01ca\u01cc\u0001\u0000\u0000"+
		"\u0000\u01cb\u01b9\u0001\u0000\u0000\u0000\u01cb\u01c2\u0001\u0000\u0000"+
		"\u0000\u01ccW\u0001\u0000\u0000\u0000\u01cd\u01ce\u0005g\u0000\u0000\u01ce"+
		"Y\u0001\u0000\u0000\u0000\u01cf\u01d0\u0005h\u0000\u0000\u01d0[\u0001"+
		"\u0000\u0000\u0000.]bemruz~\u009b\u00a1\u00a7\u00ad\u00b7\u00c1\u00cd"+
		"\u00d7\u00e1\u00e7\u00ef\u00f3\u00f6\u00fb\u0104\u0108\u010b\u0110\u0114"+
		"\u011c\u0129\u012f\u0136\u013b\u0148\u0156\u015b\u0160\u0162\u0169\u016e"+
		"\u0174\u017b\u0183\u01a7\u01b1\u01b7\u01cb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}