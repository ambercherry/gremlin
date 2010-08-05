// $ANTLR 3.2 Sep 23, 2009 12:02:23 src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g 2010-08-05 02:01:29

    package com.tinkerpop.gremlin.compiler;

    import java.io.FileReader;
    
    import java.util.ArrayList;
    
    import java.util.Map;
    import java.util.HashMap;
    import java.util.Iterator;
    
    import java.util.regex.Pattern;
    import java.util.regex.Matcher;

    import java.util.Collections;

    import java.util.ServiceLoader;

    import com.tinkerpop.gremlin.GremlinScriptEngine;
    
    import com.tinkerpop.gremlin.compiler.Tokens;

    import com.tinkerpop.gremlin.compiler.context.*;

    import com.tinkerpop.gremlin.compiler.functions.Functions;
    
    // types
    import com.tinkerpop.gremlin.compiler.types.*;

    // operations
    import com.tinkerpop.gremlin.compiler.operations.Operation;
    import com.tinkerpop.gremlin.compiler.operations.UnaryOperation;

    import com.tinkerpop.gremlin.compiler.statements.*;
    import com.tinkerpop.gremlin.compiler.operations.math.*;
    import com.tinkerpop.gremlin.compiler.operations.logic.*;
    import com.tinkerpop.gremlin.compiler.operations.util.*;

    import com.tinkerpop.gremlin.compiler.functions.Function;
    import com.tinkerpop.gremlin.compiler.functions.NativeFunction;

    // blueprints
    import com.tinkerpop.blueprints.pgm.Vertex;

    // pipes
    import com.tinkerpop.pipes.Pipe;
    import com.tinkerpop.pipes.Pipeline;

    import com.tinkerpop.pipes.SingleIterator;
    import com.tinkerpop.pipes.MultiIterator;
    
    import com.tinkerpop.pipes.pgm.PropertyPipe;
    import com.tinkerpop.pipes.filter.FilterPipe;
    import com.tinkerpop.pipes.filter.FutureFilterPipe;
    
    import com.tinkerpop.gremlin.compiler.pipes.GremlinPipesHelper;


import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


public class GremlinEvaluator extends TreeParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "VAR", "ARG", "ARGS", "FUNC", "NS", "NAME", "FUNC_NAME", "PATH", "GPATH", "STEP", "TOKEN", "PREDICATE", "PREDICATES", "SELF", "HISTORY", "FUNC_CALL", "IF", "ELSE", "COND", "BLOCK", "FOREACH", "WHILE", "REPEAT", "INCLUDE", "SCRIPT", "INT", "LONG", "FLOAT", "DOUBLE", "STR", "ARR", "BOOL", "NULL", "RANGE", "PROPERTY_CALL", "VARIABLE_CALL", "COLLECTION_CALL", "COMMENT", "VARIABLE", "NEWLINE", "StringLiteral", "IDENTIFIER", "G_INT", "G_LONG", "G_FLOAT", "G_DOUBLE", "BOOLEAN", "PROPERTY", "DoubleStringCharacter", "SingleStringCharacter", "WS", "EscapeSequence", "CharacterEscapeSequence", "HexEscapeSequence", "UnicodeEscapeSequence", "SingleEscapeCharacter", "NonEscapeCharacter", "EscapeCharacter", "DecimalDigit", "HexDigit", "':='", "'/'", "'['", "']'", "'..'", "'and'", "'or'", "'include'", "'script'", "'if'", "'else'", "'end'", "'foreach'", "'in'", "'while'", "'repeat'", "'path'", "'func'", "'('", "')'", "','", "'='", "'!='", "'<'", "'<='", "'>'", "'>='", "'+'", "'-'", "'*'", "'div'", "'mod'", "':'", "NUMBER"
    };
    public static final int WHILE=25;
    public static final int DecimalDigit=62;
    public static final int EOF=-1;
    public static final int FUNC_CALL=19;
    public static final int TOKEN=14;
    public static final int SingleStringCharacter=53;
    public static final int T__93=93;
    public static final int HISTORY=18;
    public static final int T__94=94;
    public static final int T__91=91;
    public static final int NAME=9;
    public static final int T__92=92;
    public static final int T__90=90;
    public static final int ARG=5;
    public static final int PATH=11;
    public static final int G_INT=46;
    public static final int INCLUDE=27;
    public static final int SingleEscapeCharacter=59;
    public static final int DOUBLE=32;
    public static final int ARGS=6;
    public static final int VAR=4;
    public static final int GPATH=12;
    public static final int COMMENT=41;
    public static final int T__96=96;
    public static final int T__95=95;
    public static final int SCRIPT=28;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int NS=8;
    public static final int NULL=36;
    public static final int ELSE=21;
    public static final int NUMBER=97;
    public static final int BOOL=35;
    public static final int INT=29;
    public static final int DoubleStringCharacter=52;
    public static final int ARR=34;
    public static final int T__85=85;
    public static final int T__84=84;
    public static final int T__87=87;
    public static final int T__86=86;
    public static final int T__89=89;
    public static final int T__88=88;
    public static final int T__71=71;
    public static final int WS=54;
    public static final int T__72=72;
    public static final int PREDICATES=16;
    public static final int VARIABLE=42;
    public static final int T__70=70;
    public static final int G_DOUBLE=49;
    public static final int PROPERTY=51;
    public static final int FUNC=7;
    public static final int G_LONG=47;
    public static final int FOREACH=24;
    public static final int REPEAT=26;
    public static final int FUNC_NAME=10;
    public static final int CharacterEscapeSequence=56;
    public static final int T__76=76;
    public static final int T__75=75;
    public static final int T__74=74;
    public static final int T__73=73;
    public static final int EscapeSequence=55;
    public static final int T__79=79;
    public static final int T__78=78;
    public static final int T__77=77;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int HexEscapeSequence=57;
    public static final int STEP=13;
    public static final int FLOAT=31;
    public static final int HexDigit=63;
    public static final int PREDICATE=15;
    public static final int IF=20;
    public static final int STR=33;
    public static final int BOOLEAN=50;
    public static final int IDENTIFIER=45;
    public static final int EscapeCharacter=61;
    public static final int COLLECTION_CALL=40;
    public static final int G_FLOAT=48;
    public static final int PROPERTY_CALL=38;
    public static final int UnicodeEscapeSequence=58;
    public static final int RANGE=37;
    public static final int StringLiteral=44;
    public static final int NEWLINE=43;
    public static final int BLOCK=23;
    public static final int NonEscapeCharacter=60;
    public static final int LONG=30;
    public static final int COND=22;
    public static final int SELF=17;
    public static final int VARIABLE_CALL=39;

    // delegates
    // delegators


        public GremlinEvaluator(TreeNodeStream input) {
            this(input, new RecognizerSharedState());
        }
        public GremlinEvaluator(TreeNodeStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return GremlinEvaluator.tokenNames; }
    public String getGrammarFileName() { return "src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g"; }


        // debug mode
        public  static boolean DEBUG = false;
        public  static boolean EMBEDDED = false;

        private boolean isGPath = false;

        private Pattern rangePattern = Pattern.compile("^(\\d+)..(\\d+)");

        private GremlinScriptContext context = new GremlinScriptContext();

        public GremlinEvaluator(final TreeNodeStream input, final GremlinScriptContext context) {
            this(input, new RecognizerSharedState());
            this.context = context;
        }

        private Atom getVariable(String name) {
            return this.context.getVariableLibrary().getVariableByName(name);
        }

        private Function getFunction(final String ns, final String functionName) {
            return this.context.getFunctionLibrary().getFunction(ns, functionName);
        }

        private void registerFunction(final String ns, final Function func) {
            this.context.getFunctionLibrary().registerFunction(ns, func);
        }
        
        private void formProgramResult(List<Object> resultList, Operation currentOperation) {
            Atom result  = currentOperation.compute();
            Object value = null;

            try {
                value = result.getValue();
            } catch(Exception e) {}

            if (EMBEDDED) resultList.add(value);
            
            if (value != null && DEBUG) {
                if (value instanceof Iterable) {
                    for(Object o : (Iterable)value) {
                        System.out.println(Tokens.RESULT_PROMPT + o);
                    }
                } else if (value instanceof Map) {
                    Map map = (Map) value;

                    for (Object key : map.keySet()) {
                        System.out.println(Tokens.RESULT_PROMPT + key + "=" + map.get(key));
                    }
                } else if(value instanceof Iterator) {
                    Iterator itty = (Iterator) value;
                    
                    while(itty.hasNext()) {
                        System.out.println(Tokens.RESULT_PROMPT + itty.next());
                    }
                } else {
                    System.out.println(Tokens.RESULT_PROMPT + value);
                }
            }

            this.context.getVariableLibrary().setHistoryVariable(new Atom<Object>(value));
        }


    public static class program_return extends TreeRuleReturnScope {
        public Iterable results;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "program"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:130:1: program returns [Iterable results] : ( ( statement | col= collection | ^( VAR VARIABLE c= collection ) ) ( NEWLINE )* )+ ;
    public final GremlinEvaluator.program_return program() throws RecognitionException {
        GremlinEvaluator.program_return retval = new GremlinEvaluator.program_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree VAR2=null;
        CommonTree VARIABLE3=null;
        CommonTree NEWLINE4=null;
        GremlinEvaluator.collection_return col = null;

        GremlinEvaluator.collection_return c = null;

        GremlinEvaluator.statement_return statement1 = null;


        CommonTree VAR2_tree=null;
        CommonTree VARIABLE3_tree=null;
        CommonTree NEWLINE4_tree=null;


                List<Object> resultList = new ArrayList<Object>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:5: ( ( ( statement | col= collection | ^( VAR VARIABLE c= collection ) ) ( NEWLINE )* )+ )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:7: ( ( statement | col= collection | ^( VAR VARIABLE c= collection ) ) ( NEWLINE )* )+
            {
            root_0 = (CommonTree)adaptor.nil();

            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:7: ( ( statement | col= collection | ^( VAR VARIABLE c= collection ) ) ( NEWLINE )* )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==VAR||LA3_0==FUNC||(LA3_0>=PATH && LA3_0<=GPATH)||(LA3_0>=FUNC_CALL && LA3_0<=IF)||(LA3_0>=FOREACH && LA3_0<=COLLECTION_CALL)||LA3_0==IDENTIFIER||(LA3_0>=69 && LA3_0<=70)||LA3_0==82||(LA3_0>=85 && LA3_0<=95)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:8: ( statement | col= collection | ^( VAR VARIABLE c= collection ) ) ( NEWLINE )*
            	    {
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:8: ( statement | col= collection | ^( VAR VARIABLE c= collection ) )
            	    int alt1=3;
            	    switch ( input.LA(1) ) {
            	    case FUNC:
            	    case PATH:
            	    case GPATH:
            	    case FUNC_CALL:
            	    case IF:
            	    case FOREACH:
            	    case WHILE:
            	    case REPEAT:
            	    case INCLUDE:
            	    case SCRIPT:
            	    case INT:
            	    case LONG:
            	    case FLOAT:
            	    case DOUBLE:
            	    case STR:
            	    case ARR:
            	    case BOOL:
            	    case NULL:
            	    case RANGE:
            	    case PROPERTY_CALL:
            	    case VARIABLE_CALL:
            	    case IDENTIFIER:
            	    case 69:
            	    case 70:
            	    case 82:
            	    case 85:
            	    case 86:
            	    case 87:
            	    case 88:
            	    case 89:
            	    case 90:
            	    case 91:
            	    case 92:
            	    case 93:
            	    case 94:
            	    case 95:
            	        {
            	        alt1=1;
            	        }
            	        break;
            	    case VAR:
            	        {
            	        int LA1_2 = input.LA(2);

            	        if ( (LA1_2==DOWN) ) {
            	            int LA1_4 = input.LA(3);

            	            if ( (LA1_4==VARIABLE) ) {
            	                int LA1_5 = input.LA(4);

            	                if ( (LA1_5==COLLECTION_CALL) ) {
            	                    alt1=3;
            	                }
            	                else if ( (LA1_5==VAR||LA1_5==FUNC||(LA1_5>=PATH && LA1_5<=GPATH)||(LA1_5>=FUNC_CALL && LA1_5<=IF)||(LA1_5>=FOREACH && LA1_5<=VARIABLE_CALL)||LA1_5==IDENTIFIER||(LA1_5>=69 && LA1_5<=70)||LA1_5==82||(LA1_5>=85 && LA1_5<=95)) ) {
            	                    alt1=1;
            	                }
            	                else {
            	                    NoViableAltException nvae =
            	                        new NoViableAltException("", 1, 5, input);

            	                    throw nvae;
            	                }
            	            }
            	            else {
            	                NoViableAltException nvae =
            	                    new NoViableAltException("", 1, 4, input);

            	                throw nvae;
            	            }
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("", 1, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case COLLECTION_CALL:
            	        {
            	        alt1=2;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 1, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt1) {
            	        case 1 :
            	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:134:9: statement
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_statement_in_program60);
            	            statement1=statement();

            	            state._fsp--;

            	            adaptor.addChild(root_0, statement1.getTree());

            	                    formProgramResult(resultList, (statement1!=null?statement1.op:null));
            	                 

            	            }
            	            break;
            	        case 2 :
            	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:137:10: col= collection
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_collection_in_program73);
            	            col=collection();

            	            state._fsp--;

            	            adaptor.addChild(root_0, col.getTree());

            	                    formProgramResult(resultList, (col!=null?col.op:null));
            	                 

            	            }
            	            break;
            	        case 3 :
            	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:139:10: ^( VAR VARIABLE c= collection )
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            {
            	            CommonTree _save_last_1 = _last;
            	            CommonTree _first_1 = null;
            	            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            	            VAR2=(CommonTree)match(input,VAR,FOLLOW_VAR_in_program80); 
            	            VAR2_tree = (CommonTree)adaptor.dupNode(VAR2);

            	            root_1 = (CommonTree)adaptor.becomeRoot(VAR2_tree, root_1);



            	            match(input, Token.DOWN, null); 
            	            _last = (CommonTree)input.LT(1);
            	            VARIABLE3=(CommonTree)match(input,VARIABLE,FOLLOW_VARIABLE_in_program82); 
            	            VARIABLE3_tree = (CommonTree)adaptor.dupNode(VARIABLE3);

            	            adaptor.addChild(root_1, VARIABLE3_tree);

            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_collection_in_program86);
            	            c=collection();

            	            state._fsp--;

            	            adaptor.addChild(root_1, c.getTree());

            	            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            	            }


            	                    formProgramResult(resultList, new DeclareVariable((VARIABLE3!=null?VARIABLE3.getText():null), (c!=null?c.op:null), this.context)); 
            	                 

            	            }
            	            break;

            	    }

            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:141:9: ( NEWLINE )*
            	    loop2:
            	    do {
            	        int alt2=2;
            	        int LA2_0 = input.LA(1);

            	        if ( (LA2_0==NEWLINE) ) {
            	            alt2=1;
            	        }


            	        switch (alt2) {
            	    	case 1 :
            	    	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:141:9: NEWLINE
            	    	    {
            	    	    _last = (CommonTree)input.LT(1);
            	    	    NEWLINE4=(CommonTree)match(input,NEWLINE,FOLLOW_NEWLINE_in_program92); 
            	    	    NEWLINE4_tree = (CommonTree)adaptor.dupNode(NEWLINE4);

            	    	    adaptor.addChild(root_0, NEWLINE4_tree);


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop2;
            	        }
            	    } while (true);


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


                    retval.results = resultList;
                 

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "program"

    public static class statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:147:1: statement returns [Operation op] : ( if_statement | foreach_statement | while_statement | repeat_statement | path_definition_statement | function_definition_statement | include_statement | script_statement | gpath_statement | ^( VAR VARIABLE s= statement ) | ^( 'and' a= statement b= statement ) | ^( 'or' a= statement b= statement ) | expression );
    public final GremlinEvaluator.statement_return statement() throws RecognitionException {
        GremlinEvaluator.statement_return retval = new GremlinEvaluator.statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree VAR14=null;
        CommonTree VARIABLE15=null;
        CommonTree string_literal16=null;
        CommonTree string_literal17=null;
        GremlinEvaluator.statement_return s = null;

        GremlinEvaluator.statement_return a = null;

        GremlinEvaluator.statement_return b = null;

        GremlinEvaluator.if_statement_return if_statement5 = null;

        GremlinEvaluator.foreach_statement_return foreach_statement6 = null;

        GremlinEvaluator.while_statement_return while_statement7 = null;

        GremlinEvaluator.repeat_statement_return repeat_statement8 = null;

        GremlinEvaluator.path_definition_statement_return path_definition_statement9 = null;

        GremlinEvaluator.function_definition_statement_return function_definition_statement10 = null;

        GremlinEvaluator.include_statement_return include_statement11 = null;

        GremlinEvaluator.script_statement_return script_statement12 = null;

        GremlinEvaluator.gpath_statement_return gpath_statement13 = null;

        GremlinEvaluator.expression_return expression18 = null;


        CommonTree VAR14_tree=null;
        CommonTree VARIABLE15_tree=null;
        CommonTree string_literal16_tree=null;
        CommonTree string_literal17_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:148:2: ( if_statement | foreach_statement | while_statement | repeat_statement | path_definition_statement | function_definition_statement | include_statement | script_statement | gpath_statement | ^( VAR VARIABLE s= statement ) | ^( 'and' a= statement b= statement ) | ^( 'or' a= statement b= statement ) | expression )
            int alt4=13;
            switch ( input.LA(1) ) {
            case IF:
                {
                alt4=1;
                }
                break;
            case FOREACH:
                {
                alt4=2;
                }
                break;
            case WHILE:
                {
                alt4=3;
                }
                break;
            case REPEAT:
                {
                alt4=4;
                }
                break;
            case PATH:
                {
                alt4=5;
                }
                break;
            case FUNC:
                {
                alt4=6;
                }
                break;
            case INCLUDE:
                {
                alt4=7;
                }
                break;
            case SCRIPT:
                {
                alt4=8;
                }
                break;
            case GPATH:
                {
                alt4=9;
                }
                break;
            case VAR:
                {
                alt4=10;
                }
                break;
            case 69:
                {
                alt4=11;
                }
                break;
            case 70:
                {
                alt4=12;
                }
                break;
            case FUNC_CALL:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STR:
            case ARR:
            case BOOL:
            case NULL:
            case RANGE:
            case PROPERTY_CALL:
            case VARIABLE_CALL:
            case IDENTIFIER:
            case 82:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                {
                alt4=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:148:4: if_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_if_statement_in_statement120);
                    if_statement5=if_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, if_statement5.getTree());
                     retval.op = (if_statement5!=null?if_statement5.op:null); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:149:4: foreach_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_foreach_statement_in_statement150);
                    foreach_statement6=foreach_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, foreach_statement6.getTree());
                     retval.op = (foreach_statement6!=null?foreach_statement6.op:null); 

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:150:7: while_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_while_statement_in_statement178);
                    while_statement7=while_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, while_statement7.getTree());
                     retval.op = (while_statement7!=null?while_statement7.op:null); 

                    }
                    break;
                case 4 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:151:4: repeat_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_repeat_statement_in_statement205);
                    repeat_statement8=repeat_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, repeat_statement8.getTree());
                     retval.op = (repeat_statement8!=null?repeat_statement8.op:null); 

                    }
                    break;
                case 5 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:152:4: path_definition_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_path_definition_statement_in_statement231);
                    path_definition_statement9=path_definition_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, path_definition_statement9.getTree());
                     retval.op = (path_definition_statement9!=null?path_definition_statement9.op:null); 

                    }
                    break;
                case 6 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:153:4: function_definition_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_function_definition_statement_in_statement248);
                    function_definition_statement10=function_definition_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, function_definition_statement10.getTree());
                     retval.op = (function_definition_statement10!=null?function_definition_statement10.op:null); 

                    }
                    break;
                case 7 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:154:4: include_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_include_statement_in_statement261);
                    include_statement11=include_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, include_statement11.getTree());
                     retval.op = new UnaryOperation((include_statement11!=null?include_statement11.result:null)); 

                    }
                    break;
                case 8 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:155:6: script_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_script_statement_in_statement288);
                    script_statement12=script_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, script_statement12.getTree());
                     retval.op = new UnaryOperation((script_statement12!=null?script_statement12.result:null)); 

                    }
                    break;
                case 9 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:156:4: gpath_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_gpath_statement_in_statement314);
                    gpath_statement13=gpath_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, gpath_statement13.getTree());
                     retval.op = (gpath_statement13!=null?gpath_statement13.op:null); 

                    }
                    break;
                case 10 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:157:4: ^( VAR VARIABLE s= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    VAR14=(CommonTree)match(input,VAR,FOLLOW_VAR_in_statement342); 
                    VAR14_tree = (CommonTree)adaptor.dupNode(VAR14);

                    root_1 = (CommonTree)adaptor.becomeRoot(VAR14_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    VARIABLE15=(CommonTree)match(input,VARIABLE,FOLLOW_VARIABLE_in_statement344); 
                    VARIABLE15_tree = (CommonTree)adaptor.dupNode(VARIABLE15);

                    adaptor.addChild(root_1, VARIABLE15_tree);

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_statement348);
                    s=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, s.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.op = new DeclareVariable((VARIABLE15!=null?VARIABLE15.getText():null), (s!=null?s.op:null), this.context); 

                    }
                    break;
                case 11 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:158:9: ^( 'and' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal16=(CommonTree)match(input,69,FOLLOW_69_in_statement370); 
                    string_literal16_tree = (CommonTree)adaptor.dupNode(string_literal16);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal16_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_statement374);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_statement378);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.op = new And((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 12 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:159:9: ^( 'or' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal17=(CommonTree)match(input,70,FOLLOW_70_in_statement395); 
                    string_literal17_tree = (CommonTree)adaptor.dupNode(string_literal17);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal17_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_statement400);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_statement404);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.op = new Or((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 13 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:160:9: expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_statement420);
                    expression18=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression18.getTree());
                     retval.op = (expression18!=null?expression18.expr:null); 

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "statement"

    public static class script_statement_return extends TreeRuleReturnScope {
        public Atom result;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "script_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:163:1: script_statement returns [Atom result] : ^( SCRIPT StringLiteral ) ;
    public final GremlinEvaluator.script_statement_return script_statement() throws RecognitionException {
        GremlinEvaluator.script_statement_return retval = new GremlinEvaluator.script_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree SCRIPT19=null;
        CommonTree StringLiteral20=null;

        CommonTree SCRIPT19_tree=null;
        CommonTree StringLiteral20_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:164:5: ( ^( SCRIPT StringLiteral ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:164:7: ^( SCRIPT StringLiteral )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            SCRIPT19=(CommonTree)match(input,SCRIPT,FOLLOW_SCRIPT_in_script_statement466); 
            SCRIPT19_tree = (CommonTree)adaptor.dupNode(SCRIPT19);

            root_1 = (CommonTree)adaptor.becomeRoot(SCRIPT19_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            StringLiteral20=(CommonTree)match(input,StringLiteral,FOLLOW_StringLiteral_in_script_statement468); 
            StringLiteral20_tree = (CommonTree)adaptor.dupNode(StringLiteral20);

            adaptor.addChild(root_1, StringLiteral20_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.result = new Atom<Boolean>(true);

                        String filename = (StringLiteral20!=null?StringLiteral20.getText():null);
                        try {
                            final GremlinScriptEngine engine = new GremlinScriptEngine();
                            engine.eval(new FileReader(filename.substring(1, filename.length() - 1)), this.context);
                        } catch(Exception e) {
                            retval.result = new Atom<Boolean>(false);
                        }
                  

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "script_statement"

    public static class include_statement_return extends TreeRuleReturnScope {
        public Atom result;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "include_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:178:1: include_statement returns [Atom result] : ^( INCLUDE StringLiteral ) ;
    public final GremlinEvaluator.include_statement_return include_statement() throws RecognitionException {
        GremlinEvaluator.include_statement_return retval = new GremlinEvaluator.include_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree INCLUDE21=null;
        CommonTree StringLiteral22=null;

        CommonTree INCLUDE21_tree=null;
        CommonTree StringLiteral22_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:179:2: ( ^( INCLUDE StringLiteral ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:179:4: ^( INCLUDE StringLiteral )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            INCLUDE21=(CommonTree)match(input,INCLUDE,FOLLOW_INCLUDE_in_include_statement496); 
            INCLUDE21_tree = (CommonTree)adaptor.dupNode(INCLUDE21);

            root_1 = (CommonTree)adaptor.becomeRoot(INCLUDE21_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            StringLiteral22=(CommonTree)match(input,StringLiteral,FOLLOW_StringLiteral_in_include_statement498); 
            StringLiteral22_tree = (CommonTree)adaptor.dupNode(StringLiteral22);

            adaptor.addChild(root_1, StringLiteral22_tree);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.result = new Atom<Boolean>(true);

                        String className = (StringLiteral22!=null?StringLiteral22.getText():null);
                        try {
                            final Class toLoad = Class.forName(className);
                            final ServiceLoader<Functions> functionsService = ServiceLoader.load(toLoad);

                            for (final Functions functionsToLoad : functionsService) {
                                this.context.getFunctionLibrary().registerFunctions(functionsToLoad);
                            }
                        } catch(Exception e) {
                            retval.result = new Atom<Boolean>(false);
                            e.printStackTrace();
                        }
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "include_statement"

    public static class path_definition_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "path_definition_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:198:1: path_definition_statement returns [Operation op] : ^( PATH path_name= IDENTIFIER (gpath= gpath_statement | ^( PROPERTY_CALL pr= PROPERTY ) ) ) ;
    public final GremlinEvaluator.path_definition_statement_return path_definition_statement() throws RecognitionException {
        GremlinEvaluator.path_definition_statement_return retval = new GremlinEvaluator.path_definition_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree path_name=null;
        CommonTree pr=null;
        CommonTree PATH23=null;
        CommonTree PROPERTY_CALL24=null;
        GremlinEvaluator.gpath_statement_return gpath = null;


        CommonTree path_name_tree=null;
        CommonTree pr_tree=null;
        CommonTree PATH23_tree=null;
        CommonTree PROPERTY_CALL24_tree=null;


                List<Pipe> pipes = new ArrayList<Pipe>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:202:2: ( ^( PATH path_name= IDENTIFIER (gpath= gpath_statement | ^( PROPERTY_CALL pr= PROPERTY ) ) ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:202:4: ^( PATH path_name= IDENTIFIER (gpath= gpath_statement | ^( PROPERTY_CALL pr= PROPERTY ) ) )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            PATH23=(CommonTree)match(input,PATH,FOLLOW_PATH_in_path_definition_statement535); 
            PATH23_tree = (CommonTree)adaptor.dupNode(PATH23);

            root_1 = (CommonTree)adaptor.becomeRoot(PATH23_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            path_name=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_path_definition_statement539); 
            path_name_tree = (CommonTree)adaptor.dupNode(path_name);

            adaptor.addChild(root_1, path_name_tree);

            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:202:32: (gpath= gpath_statement | ^( PROPERTY_CALL pr= PROPERTY ) )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==GPATH) ) {
                alt5=1;
            }
            else if ( (LA5_0==PROPERTY_CALL) ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:202:33: gpath= gpath_statement
                    {
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_gpath_statement_in_path_definition_statement544);
                    gpath=gpath_statement();

                    state._fsp--;

                    adaptor.addChild(root_1, gpath.getTree());
                     pipes.addAll(((GPathOperation)(gpath!=null?gpath.op:null)).getPipes()); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:202:115: ^( PROPERTY_CALL pr= PROPERTY )
                    {
                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_2 = _last;
                    CommonTree _first_2 = null;
                    CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    PROPERTY_CALL24=(CommonTree)match(input,PROPERTY_CALL,FOLLOW_PROPERTY_CALL_in_path_definition_statement551); 
                    PROPERTY_CALL24_tree = (CommonTree)adaptor.dupNode(PROPERTY_CALL24);

                    root_2 = (CommonTree)adaptor.becomeRoot(PROPERTY_CALL24_tree, root_2);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pr=(CommonTree)match(input,PROPERTY,FOLLOW_PROPERTY_in_path_definition_statement555); 
                    pr_tree = (CommonTree)adaptor.dupNode(pr);

                    adaptor.addChild(root_2, pr_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
                    }

                     pipes.add(new PropertyPipe((pr!=null?pr.getText():null).substring(1))); 

                    }
                    break;

            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        this.context.getPathLibrary().registerPath((path_name!=null?path_name.getText():null), pipes);
                        retval.op = new UnaryOperation(new Atom<Boolean>(true));
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "path_definition_statement"

    protected static class gpath_statement_scope {
        int pipeCount;
        Atom<Object> root;
        List<Pipe> pipeList;
    }
    protected Stack gpath_statement_stack = new Stack();

    public static class gpath_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "gpath_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:210:1: gpath_statement returns [Operation op] : ^( GPATH ( step )+ ) ;
    public final GremlinEvaluator.gpath_statement_return gpath_statement() throws RecognitionException {
        gpath_statement_stack.push(new gpath_statement_scope());
        GremlinEvaluator.gpath_statement_return retval = new GremlinEvaluator.gpath_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree GPATH25=null;
        GremlinEvaluator.step_return step26 = null;


        CommonTree GPATH25_tree=null;


                isGPath = true;
                
                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeCount = 0;
                ((gpath_statement_scope)gpath_statement_stack.peek()).root = null;
                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList = new ArrayList<Pipe>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:227:2: ( ^( GPATH ( step )+ ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:227:4: ^( GPATH ( step )+ )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            GPATH25=(CommonTree)match(input,GPATH,FOLLOW_GPATH_in_gpath_statement614); 
            GPATH25_tree = (CommonTree)adaptor.dupNode(GPATH25);

            root_1 = (CommonTree)adaptor.becomeRoot(GPATH25_tree, root_1);



            match(input, Token.DOWN, null); 
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:227:12: ( step )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==STEP) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:227:13: step
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_step_in_gpath_statement617);
            	    step26=step();

            	    state._fsp--;

            	    adaptor.addChild(root_1, step26.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        if (((gpath_statement_scope)gpath_statement_stack.peek()).pipeList.size() > 0) {
                            retval.op = new GPathOperation(((gpath_statement_scope)gpath_statement_stack.peek()).pipeList, ((gpath_statement_scope)gpath_statement_stack.peek()).root, this.context);
                        } else {
                            retval.op = new UnaryOperation(new Atom<Object>(null));
                        }
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);


                    isGPath = false;
                
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
            gpath_statement_stack.pop();
        }
        return retval;
    }
    // $ANTLR end "gpath_statement"

    public static class step_return extends TreeRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "step"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:237:1: step : ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )* ) ) ;
    public final GremlinEvaluator.step_return step() throws RecognitionException {
        GremlinEvaluator.step_return retval = new GremlinEvaluator.step_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree STEP27=null;
        CommonTree TOKEN28=null;
        CommonTree PREDICATES30=null;
        CommonTree PREDICATE31=null;
        GremlinEvaluator.token_return token29 = null;

        GremlinEvaluator.statement_return statement32 = null;


        CommonTree STEP27_tree=null;
        CommonTree TOKEN28_tree=null;
        CommonTree PREDICATES30_tree=null;
        CommonTree PREDICATE31_tree=null;


                List<Operation> predicates = new ArrayList<Operation>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:241:5: ( ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )* ) ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:241:7: ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )* ) )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            STEP27=(CommonTree)match(input,STEP,FOLLOW_STEP_in_step655); 
            STEP27_tree = (CommonTree)adaptor.dupNode(STEP27);

            root_1 = (CommonTree)adaptor.becomeRoot(STEP27_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            TOKEN28=(CommonTree)match(input,TOKEN,FOLLOW_TOKEN_in_step658); 
            TOKEN28_tree = (CommonTree)adaptor.dupNode(TOKEN28);

            root_2 = (CommonTree)adaptor.becomeRoot(TOKEN28_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_token_in_step660);
            token29=token();

            state._fsp--;

            adaptor.addChild(root_2, token29.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            PREDICATES30=(CommonTree)match(input,PREDICATES,FOLLOW_PREDICATES_in_step664); 
            PREDICATES30_tree = (CommonTree)adaptor.dupNode(PREDICATES30);

            root_2 = (CommonTree)adaptor.becomeRoot(PREDICATES30_tree, root_2);



            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:241:42: ( ^( PREDICATE statement ) )*
                loop7:
                do {
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==PREDICATE) ) {
                        alt7=1;
                    }


                    switch (alt7) {
                	case 1 :
                	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:241:44: ^( PREDICATE statement )
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    {
                	    CommonTree _save_last_3 = _last;
                	    CommonTree _first_3 = null;
                	    CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                	    PREDICATE31=(CommonTree)match(input,PREDICATE,FOLLOW_PREDICATE_in_step669); 
                	    PREDICATE31_tree = (CommonTree)adaptor.dupNode(PREDICATE31);

                	    root_3 = (CommonTree)adaptor.becomeRoot(PREDICATE31_tree, root_3);



                	    match(input, Token.DOWN, null); 
                	    _last = (CommonTree)input.LT(1);
                	    pushFollow(FOLLOW_statement_in_step671);
                	    statement32=statement();

                	    state._fsp--;

                	    adaptor.addChild(root_3, statement32.getTree());
                	     predicates.add((statement32!=null?statement32.op:null)); 

                	    match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
                	    }


                	    }
                	    break;

                	default :
                	    break loop7;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        final Atom tokenAtom = (token29!=null?token29.atom:null);
                        final PathLibrary paths = this.context.getPathLibrary();

                        if (tokenAtom != null) {
                            if (((gpath_statement_scope)gpath_statement_stack.peek()).pipeCount == 0) {

                                if (tokenAtom instanceof DynamicEntity) {
                                    ((gpath_statement_scope)gpath_statement_stack.peek()).root = tokenAtom;    
                                } else if (paths.isPath(tokenAtom.toString())) {
                                    ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList.addAll(paths.getPath(tokenAtom.toString()));
                                    ((gpath_statement_scope)gpath_statement_stack.peek()).root = this.getVariable(Tokens.ROOT_VARIABLE);
                                } else {
                                    ((gpath_statement_scope)gpath_statement_stack.peek()).root = tokenAtom;
                                }
                            
                                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList.addAll(GremlinPipesHelper.pipesForStep(predicates, this.context));
                            } else {
                                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList.addAll(GremlinPipesHelper.pipesForStep(tokenAtom, predicates, this.context));
                            }
                        }
                        
                        ((gpath_statement_scope)gpath_statement_stack.peek()).pipeCount++;
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "step"

    public static class token_return extends TreeRuleReturnScope {
        public Atom atom;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "token"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:268:1: token returns [Atom atom] : ( expression | gpath_statement | collection | '..' );
    public final GremlinEvaluator.token_return token() throws RecognitionException {
        GremlinEvaluator.token_return retval = new GremlinEvaluator.token_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree string_literal36=null;
        GremlinEvaluator.expression_return expression33 = null;

        GremlinEvaluator.gpath_statement_return gpath_statement34 = null;

        GremlinEvaluator.collection_return collection35 = null;


        CommonTree string_literal36_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:269:5: ( expression | gpath_statement | collection | '..' )
            int alt8=4;
            switch ( input.LA(1) ) {
            case FUNC_CALL:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STR:
            case ARR:
            case BOOL:
            case NULL:
            case RANGE:
            case PROPERTY_CALL:
            case VARIABLE_CALL:
            case IDENTIFIER:
            case 82:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                {
                alt8=1;
                }
                break;
            case GPATH:
                {
                alt8=2;
                }
                break;
            case COLLECTION_CALL:
                {
                alt8=3;
                }
                break;
            case 68:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:269:8: expression
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_expression_in_token713);
                    expression33=expression();

                    state._fsp--;

                    adaptor.addChild(root_0, expression33.getTree());
                     retval.atom = (expression33!=null?expression33.expr:null).compute(); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:270:9: gpath_statement
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_gpath_statement_in_token725);
                    gpath_statement34=gpath_statement();

                    state._fsp--;

                    adaptor.addChild(root_0, gpath_statement34.getTree());
                     retval.atom = (gpath_statement34!=null?gpath_statement34.op:null).compute(); 

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:271:9: collection
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_collection_in_token737);
                    collection35=collection();

                    state._fsp--;

                    adaptor.addChild(root_0, collection35.getTree());
                     retval.atom = (collection35!=null?collection35.op:null).compute(); 

                    }
                    break;
                case 4 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:272:9: '..'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    string_literal36=(CommonTree)match(input,68,FOLLOW_68_in_token749); 
                    string_literal36_tree = (CommonTree)adaptor.dupNode(string_literal36);

                    adaptor.addChild(root_0, string_literal36_tree);



                                            List<Pipe> history = new ArrayList<Pipe>();
                                            List<Pipe> newPipes = new ArrayList<Pipe>();
                                            List<Pipe> pipes = ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList;

                                            if ((pipes.size() == 1 && (pipes.get(0) instanceof FutureFilterPipe)) || pipes.size() == 0) {
                                                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList = new ArrayList();
                                            } else {
                                                int idx;
                                                
                                                for (idx = pipes.size() - 1; idx >= 0; idx--) {
                                                    Pipe currentPipe = pipes.get(idx);
                                                    history.add(currentPipe);

                                                    if (!(currentPipe instanceof FilterPipe)) break;
                                                }

                                                for (int i = 0; i < idx; i++) {
                                                    newPipes.add(pipes.get(i));
                                                }

                                                // don't like that - fix. PY
                                                Collections.reverse(history);
                                                newPipes.add(new FutureFilterPipe(new Pipeline(history)));
                                            
                                                ((gpath_statement_scope)gpath_statement_stack.peek()).pipeList = newPipes;
                                            }
                                            
                                            retval.atom = null;
                                       

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "token"

    public static class if_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "if_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:306:1: if_statement returns [Operation op] : ^( IF ^( COND cond= statement ) if_block= block ( ^( ELSE else_block= block ) )? ) ;
    public final GremlinEvaluator.if_statement_return if_statement() throws RecognitionException {
        GremlinEvaluator.if_statement_return retval = new GremlinEvaluator.if_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree IF37=null;
        CommonTree COND38=null;
        CommonTree ELSE39=null;
        GremlinEvaluator.statement_return cond = null;

        GremlinEvaluator.block_return if_block = null;

        GremlinEvaluator.block_return else_block = null;


        CommonTree IF37_tree=null;
        CommonTree COND38_tree=null;
        CommonTree ELSE39_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:307:2: ( ^( IF ^( COND cond= statement ) if_block= block ( ^( ELSE else_block= block ) )? ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:307:4: ^( IF ^( COND cond= statement ) if_block= block ( ^( ELSE else_block= block ) )? )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            IF37=(CommonTree)match(input,IF,FOLLOW_IF_in_if_statement774); 
            IF37_tree = (CommonTree)adaptor.dupNode(IF37);

            root_1 = (CommonTree)adaptor.becomeRoot(IF37_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            COND38=(CommonTree)match(input,COND,FOLLOW_COND_in_if_statement777); 
            COND38_tree = (CommonTree)adaptor.dupNode(COND38);

            root_2 = (CommonTree)adaptor.becomeRoot(COND38_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_statement_in_if_statement781);
            cond=statement();

            state._fsp--;

            adaptor.addChild(root_2, cond.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_block_in_if_statement786);
            if_block=block();

            state._fsp--;

            adaptor.addChild(root_1, if_block.getTree());
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:307:47: ( ^( ELSE else_block= block ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==ELSE) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:307:49: ^( ELSE else_block= block )
                    {
                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_2 = _last;
                    CommonTree _first_2 = null;
                    CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    ELSE39=(CommonTree)match(input,ELSE,FOLLOW_ELSE_in_if_statement791); 
                    ELSE39_tree = (CommonTree)adaptor.dupNode(ELSE39);

                    root_2 = (CommonTree)adaptor.becomeRoot(ELSE39_tree, root_2);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_block_in_if_statement795);
                    else_block=block();

                    state._fsp--;

                    adaptor.addChild(root_2, else_block.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
                    }


                    }
                    break;

            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.op = new If((cond!=null?cond.op:null), (if_block!=null?if_block.operations:null), (else_block!=null?else_block.operations:null));
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "if_statement"

    public static class while_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "while_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:313:1: while_statement returns [Operation op] : ^( WHILE ^( COND cond= statement ) block ) ;
    public final GremlinEvaluator.while_statement_return while_statement() throws RecognitionException {
        GremlinEvaluator.while_statement_return retval = new GremlinEvaluator.while_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree WHILE40=null;
        CommonTree COND41=null;
        GremlinEvaluator.statement_return cond = null;

        GremlinEvaluator.block_return block42 = null;


        CommonTree WHILE40_tree=null;
        CommonTree COND41_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:314:2: ( ^( WHILE ^( COND cond= statement ) block ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:314:4: ^( WHILE ^( COND cond= statement ) block )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            WHILE40=(CommonTree)match(input,WHILE,FOLLOW_WHILE_in_while_statement828); 
            WHILE40_tree = (CommonTree)adaptor.dupNode(WHILE40);

            root_1 = (CommonTree)adaptor.becomeRoot(WHILE40_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            COND41=(CommonTree)match(input,COND,FOLLOW_COND_in_while_statement831); 
            COND41_tree = (CommonTree)adaptor.dupNode(COND41);

            root_2 = (CommonTree)adaptor.becomeRoot(COND41_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_statement_in_while_statement835);
            cond=statement();

            state._fsp--;

            adaptor.addChild(root_2, cond.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_block_in_while_statement838);
            block42=block();

            state._fsp--;

            adaptor.addChild(root_1, block42.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.op = new While((cond!=null?cond.op:null), (block42!=null?block42.operations:null));
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "while_statement"

    public static class foreach_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "foreach_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:320:1: foreach_statement returns [Operation op] : ^( FOREACH VARIABLE arr= statement block ) ;
    public final GremlinEvaluator.foreach_statement_return foreach_statement() throws RecognitionException {
        GremlinEvaluator.foreach_statement_return retval = new GremlinEvaluator.foreach_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree FOREACH43=null;
        CommonTree VARIABLE44=null;
        GremlinEvaluator.statement_return arr = null;

        GremlinEvaluator.block_return block45 = null;


        CommonTree FOREACH43_tree=null;
        CommonTree VARIABLE44_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:321:2: ( ^( FOREACH VARIABLE arr= statement block ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:321:4: ^( FOREACH VARIABLE arr= statement block )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            FOREACH43=(CommonTree)match(input,FOREACH,FOLLOW_FOREACH_in_foreach_statement865); 
            FOREACH43_tree = (CommonTree)adaptor.dupNode(FOREACH43);

            root_1 = (CommonTree)adaptor.becomeRoot(FOREACH43_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            VARIABLE44=(CommonTree)match(input,VARIABLE,FOLLOW_VARIABLE_in_foreach_statement867); 
            VARIABLE44_tree = (CommonTree)adaptor.dupNode(VARIABLE44);

            adaptor.addChild(root_1, VARIABLE44_tree);

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_statement_in_foreach_statement871);
            arr=statement();

            state._fsp--;

            adaptor.addChild(root_1, arr.getTree());
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_block_in_foreach_statement873);
            block45=block();

            state._fsp--;

            adaptor.addChild(root_1, block45.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.op = new Foreach((VARIABLE44!=null?VARIABLE44.getText():null), (arr!=null?arr.op:null), (block45!=null?block45.operations:null), this.context);
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "foreach_statement"

    public static class repeat_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "repeat_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:327:1: repeat_statement returns [Operation op] : ^( REPEAT timer= statement block ) ;
    public final GremlinEvaluator.repeat_statement_return repeat_statement() throws RecognitionException {
        GremlinEvaluator.repeat_statement_return retval = new GremlinEvaluator.repeat_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree REPEAT46=null;
        GremlinEvaluator.statement_return timer = null;

        GremlinEvaluator.block_return block47 = null;


        CommonTree REPEAT46_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:328:2: ( ^( REPEAT timer= statement block ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:328:4: ^( REPEAT timer= statement block )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            REPEAT46=(CommonTree)match(input,REPEAT,FOLLOW_REPEAT_in_repeat_statement901); 
            REPEAT46_tree = (CommonTree)adaptor.dupNode(REPEAT46);

            root_1 = (CommonTree)adaptor.becomeRoot(REPEAT46_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_statement_in_repeat_statement905);
            timer=statement();

            state._fsp--;

            adaptor.addChild(root_1, timer.getTree());
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_block_in_repeat_statement907);
            block47=block();

            state._fsp--;

            adaptor.addChild(root_1, block47.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        retval.op = new Repeat((timer!=null?timer.op:null), (block47!=null?block47.operations:null));
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "repeat_statement"

    public static class block_return extends TreeRuleReturnScope {
        public List<Operation> operations;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "block"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:334:1: block returns [List<Operation> operations] : ^( BLOCK ( ( statement | collection ) )+ ) ;
    public final GremlinEvaluator.block_return block() throws RecognitionException {
        GremlinEvaluator.block_return retval = new GremlinEvaluator.block_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree BLOCK48=null;
        GremlinEvaluator.statement_return statement49 = null;

        GremlinEvaluator.collection_return collection50 = null;


        CommonTree BLOCK48_tree=null;


                List<Operation> operationList = new ArrayList<Operation>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:5: ( ^( BLOCK ( ( statement | collection ) )+ ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:7: ^( BLOCK ( ( statement | collection ) )+ )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            BLOCK48=(CommonTree)match(input,BLOCK,FOLLOW_BLOCK_in_block946); 
            BLOCK48_tree = (CommonTree)adaptor.dupNode(BLOCK48);

            root_1 = (CommonTree)adaptor.becomeRoot(BLOCK48_tree, root_1);



            match(input, Token.DOWN, null); 
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:15: ( ( statement | collection ) )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==VAR||LA11_0==FUNC||(LA11_0>=PATH && LA11_0<=GPATH)||(LA11_0>=FUNC_CALL && LA11_0<=IF)||(LA11_0>=FOREACH && LA11_0<=COLLECTION_CALL)||LA11_0==IDENTIFIER||(LA11_0>=69 && LA11_0<=70)||LA11_0==82||(LA11_0>=85 && LA11_0<=95)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:17: ( statement | collection )
            	    {
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:17: ( statement | collection )
            	    int alt10=2;
            	    int LA10_0 = input.LA(1);

            	    if ( (LA10_0==VAR||LA10_0==FUNC||(LA10_0>=PATH && LA10_0<=GPATH)||(LA10_0>=FUNC_CALL && LA10_0<=IF)||(LA10_0>=FOREACH && LA10_0<=VARIABLE_CALL)||LA10_0==IDENTIFIER||(LA10_0>=69 && LA10_0<=70)||LA10_0==82||(LA10_0>=85 && LA10_0<=95)) ) {
            	        alt10=1;
            	    }
            	    else if ( (LA10_0==COLLECTION_CALL) ) {
            	        alt10=2;
            	    }
            	    else {
            	        NoViableAltException nvae =
            	            new NoViableAltException("", 10, 0, input);

            	        throw nvae;
            	    }
            	    switch (alt10) {
            	        case 1 :
            	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:18: statement
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_statement_in_block951);
            	            statement49=statement();

            	            state._fsp--;

            	            adaptor.addChild(root_1, statement49.getTree());
            	             operationList.add((statement49!=null?statement49.op:null)); 

            	            }
            	            break;
            	        case 2 :
            	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:338:68: collection
            	            {
            	            _last = (CommonTree)input.LT(1);
            	            pushFollow(FOLLOW_collection_in_block957);
            	            collection50=collection();

            	            state._fsp--;

            	            adaptor.addChild(root_1, collection50.getTree());
            	             operationList.add((collection50!=null?collection50.op:null)); 

            	            }
            	            break;

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }

             retval.operations = operationList; 

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "block"

    public static class expression_return extends TreeRuleReturnScope {
        public Operation expr;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "expression"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:341:1: expression returns [Operation expr] : ( ^( '=' a= statement b= statement ) | ^( '!=' a= statement b= statement ) | ^( '<' a= statement b= statement ) | ^( '>' a= statement b= statement ) | ^( '<=' a= statement b= statement ) | ^( '>=' a= statement b= statement ) | operation );
    public final GremlinEvaluator.expression_return expression() throws RecognitionException {
        GremlinEvaluator.expression_return retval = new GremlinEvaluator.expression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree char_literal51=null;
        CommonTree string_literal52=null;
        CommonTree char_literal53=null;
        CommonTree char_literal54=null;
        CommonTree string_literal55=null;
        CommonTree string_literal56=null;
        GremlinEvaluator.statement_return a = null;

        GremlinEvaluator.statement_return b = null;

        GremlinEvaluator.operation_return operation57 = null;


        CommonTree char_literal51_tree=null;
        CommonTree string_literal52_tree=null;
        CommonTree char_literal53_tree=null;
        CommonTree char_literal54_tree=null;
        CommonTree string_literal55_tree=null;
        CommonTree string_literal56_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:342:5: ( ^( '=' a= statement b= statement ) | ^( '!=' a= statement b= statement ) | ^( '<' a= statement b= statement ) | ^( '>' a= statement b= statement ) | ^( '<=' a= statement b= statement ) | ^( '>=' a= statement b= statement ) | operation )
            int alt12=7;
            switch ( input.LA(1) ) {
            case 85:
                {
                alt12=1;
                }
                break;
            case 86:
                {
                alt12=2;
                }
                break;
            case 87:
                {
                alt12=3;
                }
                break;
            case 89:
                {
                alt12=4;
                }
                break;
            case 88:
                {
                alt12=5;
                }
                break;
            case 90:
                {
                alt12=6;
                }
                break;
            case FUNC_CALL:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STR:
            case ARR:
            case BOOL:
            case NULL:
            case RANGE:
            case PROPERTY_CALL:
            case VARIABLE_CALL:
            case IDENTIFIER:
            case 82:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
                {
                alt12=7;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:342:9: ^( '=' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal51=(CommonTree)match(input,85,FOLLOW_85_in_expression986); 
                    char_literal51_tree = (CommonTree)adaptor.dupNode(char_literal51);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal51_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression991);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression995);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new Equality((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:343:9: ^( '!=' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal52=(CommonTree)match(input,86,FOLLOW_86_in_expression1009); 
                    string_literal52_tree = (CommonTree)adaptor.dupNode(string_literal52);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal52_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1013);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1017);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new UnEquality((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:344:9: ^( '<' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal53=(CommonTree)match(input,87,FOLLOW_87_in_expression1031); 
                    char_literal53_tree = (CommonTree)adaptor.dupNode(char_literal53);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal53_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1036);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1040);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new LessThan((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 4 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:345:9: ^( '>' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal54=(CommonTree)match(input,89,FOLLOW_89_in_expression1054); 
                    char_literal54_tree = (CommonTree)adaptor.dupNode(char_literal54);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal54_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1059);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1063);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new GreaterThan((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 5 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:346:9: ^( '<=' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal55=(CommonTree)match(input,88,FOLLOW_88_in_expression1077); 
                    string_literal55_tree = (CommonTree)adaptor.dupNode(string_literal55);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal55_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1081);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1085);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new LessThanOrEqual((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 6 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:347:9: ^( '>=' a= statement b= statement )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal56=(CommonTree)match(input,90,FOLLOW_90_in_expression1099); 
                    string_literal56_tree = (CommonTree)adaptor.dupNode(string_literal56);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal56_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1103);
                    a=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_expression1107);
                    b=statement();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.expr = new GreaterThanOrEqual((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 7 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:348:9: operation
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_expression1120);
                    operation57=operation();

                    state._fsp--;

                    adaptor.addChild(root_0, operation57.getTree());
                     retval.expr = (operation57!=null?operation57.op:null); 

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "expression"

    public static class operation_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "operation"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:351:1: operation returns [Operation op] : ( ^( '+' a= operation b= operation ) | ^( '-' a= operation b= operation ) | binary_operation );
    public final GremlinEvaluator.operation_return operation() throws RecognitionException {
        GremlinEvaluator.operation_return retval = new GremlinEvaluator.operation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree char_literal58=null;
        CommonTree char_literal59=null;
        GremlinEvaluator.operation_return a = null;

        GremlinEvaluator.operation_return b = null;

        GremlinEvaluator.binary_operation_return binary_operation60 = null;


        CommonTree char_literal58_tree=null;
        CommonTree char_literal59_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:352:5: ( ^( '+' a= operation b= operation ) | ^( '-' a= operation b= operation ) | binary_operation )
            int alt13=3;
            switch ( input.LA(1) ) {
            case 91:
                {
                alt13=1;
                }
                break;
            case 92:
                {
                alt13=2;
                }
                break;
            case FUNC_CALL:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STR:
            case ARR:
            case BOOL:
            case NULL:
            case RANGE:
            case PROPERTY_CALL:
            case VARIABLE_CALL:
            case IDENTIFIER:
            case 82:
            case 93:
            case 94:
            case 95:
                {
                alt13=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:352:9: ^( '+' a= operation b= operation )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal58=(CommonTree)match(input,91,FOLLOW_91_in_operation1165); 
                    char_literal58_tree = (CommonTree)adaptor.dupNode(char_literal58);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal58_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_operation1169);
                    a=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_operation1173);
                    b=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.op = new Addition((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:353:9: ^( '-' a= operation b= operation )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal59=(CommonTree)match(input,92,FOLLOW_92_in_operation1187); 
                    char_literal59_tree = (CommonTree)adaptor.dupNode(char_literal59);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal59_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_operation1191);
                    a=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_operation1195);
                    b=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.op = new Subtraction((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:354:9: binary_operation
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_binary_operation_in_operation1208);
                    binary_operation60=binary_operation();

                    state._fsp--;

                    adaptor.addChild(root_0, binary_operation60.getTree());
                     retval.op = (binary_operation60!=null?binary_operation60.operation:null); 

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "operation"

    public static class binary_operation_return extends TreeRuleReturnScope {
        public Operation operation;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "binary_operation"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:357:1: binary_operation returns [Operation operation] : ( ^( '*' a= operation b= operation ) | ^( 'div' a= operation b= operation ) | ^( 'mod' a= operation b= operation ) | atom );
    public final GremlinEvaluator.binary_operation_return binary_operation() throws RecognitionException {
        GremlinEvaluator.binary_operation_return retval = new GremlinEvaluator.binary_operation_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree char_literal61=null;
        CommonTree string_literal62=null;
        CommonTree string_literal63=null;
        GremlinEvaluator.operation_return a = null;

        GremlinEvaluator.operation_return b = null;

        GremlinEvaluator.atom_return atom64 = null;


        CommonTree char_literal61_tree=null;
        CommonTree string_literal62_tree=null;
        CommonTree string_literal63_tree=null;

        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:358:5: ( ^( '*' a= operation b= operation ) | ^( 'div' a= operation b= operation ) | ^( 'mod' a= operation b= operation ) | atom )
            int alt14=4;
            switch ( input.LA(1) ) {
            case 93:
                {
                alt14=1;
                }
                break;
            case 94:
                {
                alt14=2;
                }
                break;
            case 95:
                {
                alt14=3;
                }
                break;
            case FUNC_CALL:
            case INT:
            case LONG:
            case FLOAT:
            case DOUBLE:
            case STR:
            case ARR:
            case BOOL:
            case NULL:
            case RANGE:
            case PROPERTY_CALL:
            case VARIABLE_CALL:
            case IDENTIFIER:
            case 82:
                {
                alt14=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:358:9: ^( '*' a= operation b= operation )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    char_literal61=(CommonTree)match(input,93,FOLLOW_93_in_binary_operation1245); 
                    char_literal61_tree = (CommonTree)adaptor.dupNode(char_literal61);

                    root_1 = (CommonTree)adaptor.becomeRoot(char_literal61_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1249);
                    a=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1253);
                    b=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.operation = new Multiplication((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:359:9: ^( 'div' a= operation b= operation )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal62=(CommonTree)match(input,94,FOLLOW_94_in_binary_operation1272); 
                    string_literal62_tree = (CommonTree)adaptor.dupNode(string_literal62);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal62_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1276);
                    a=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1280);
                    b=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.operation = new Division((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:360:7: ^( 'mod' a= operation b= operation )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    string_literal63=(CommonTree)match(input,95,FOLLOW_95_in_binary_operation1295); 
                    string_literal63_tree = (CommonTree)adaptor.dupNode(string_literal63);

                    root_1 = (CommonTree)adaptor.becomeRoot(string_literal63_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1299);
                    a=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, a.getTree());
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_operation_in_binary_operation1303);
                    b=operation();

                    state._fsp--;

                    adaptor.addChild(root_1, b.getTree());

                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.operation = new Modulo((a!=null?a.op:null), (b!=null?b.op:null)); 

                    }
                    break;
                case 4 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:361:9: atom
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_atom_in_binary_operation1319);
                    atom64=atom();

                    state._fsp--;

                    adaptor.addChild(root_0, atom64.getTree());
                     retval.operation = new UnaryOperation((atom64!=null?atom64.value:null)); 

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "binary_operation"

    public static class function_definition_statement_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_definition_statement"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:364:1: function_definition_statement returns [Operation op] : ^( FUNC ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG VARIABLE ) )* ) block ) ;
    public final GremlinEvaluator.function_definition_statement_return function_definition_statement() throws RecognitionException {
        GremlinEvaluator.function_definition_statement_return retval = new GremlinEvaluator.function_definition_statement_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ns=null;
        CommonTree fn_name=null;
        CommonTree FUNC65=null;
        CommonTree FUNC_NAME66=null;
        CommonTree NS67=null;
        CommonTree NAME68=null;
        CommonTree ARGS69=null;
        CommonTree ARG70=null;
        CommonTree VARIABLE71=null;
        GremlinEvaluator.block_return block72 = null;


        CommonTree ns_tree=null;
        CommonTree fn_name_tree=null;
        CommonTree FUNC65_tree=null;
        CommonTree FUNC_NAME66_tree=null;
        CommonTree NS67_tree=null;
        CommonTree NAME68_tree=null;
        CommonTree ARGS69_tree=null;
        CommonTree ARG70_tree=null;
        CommonTree VARIABLE71_tree=null;


                List<String> params = new ArrayList<String>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:368:2: ( ^( FUNC ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG VARIABLE ) )* ) block ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:368:4: ^( FUNC ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG VARIABLE ) )* ) block )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            FUNC65=(CommonTree)match(input,FUNC,FOLLOW_FUNC_in_function_definition_statement1377); 
            FUNC65_tree = (CommonTree)adaptor.dupNode(FUNC65);

            root_1 = (CommonTree)adaptor.becomeRoot(FUNC65_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            FUNC_NAME66=(CommonTree)match(input,FUNC_NAME,FOLLOW_FUNC_NAME_in_function_definition_statement1380); 
            FUNC_NAME66_tree = (CommonTree)adaptor.dupNode(FUNC_NAME66);

            root_2 = (CommonTree)adaptor.becomeRoot(FUNC_NAME66_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            NS67=(CommonTree)match(input,NS,FOLLOW_NS_in_function_definition_statement1383); 
            NS67_tree = (CommonTree)adaptor.dupNode(NS67);

            root_3 = (CommonTree)adaptor.becomeRoot(NS67_tree, root_3);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            ns=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_function_definition_statement1387); 
            ns_tree = (CommonTree)adaptor.dupNode(ns);

            adaptor.addChild(root_3, ns_tree);


            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            NAME68=(CommonTree)match(input,NAME,FOLLOW_NAME_in_function_definition_statement1391); 
            NAME68_tree = (CommonTree)adaptor.dupNode(NAME68);

            root_3 = (CommonTree)adaptor.becomeRoot(NAME68_tree, root_3);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            fn_name=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_function_definition_statement1395); 
            fn_name_tree = (CommonTree)adaptor.dupNode(fn_name);

            adaptor.addChild(root_3, fn_name_tree);


            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }


            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            ARGS69=(CommonTree)match(input,ARGS,FOLLOW_ARGS_in_function_definition_statement1400); 
            ARGS69_tree = (CommonTree)adaptor.dupNode(ARGS69);

            root_2 = (CommonTree)adaptor.becomeRoot(ARGS69_tree, root_2);



            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:368:78: ( ^( ARG VARIABLE ) )*
                loop15:
                do {
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( (LA15_0==ARG) ) {
                        alt15=1;
                    }


                    switch (alt15) {
                	case 1 :
                	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:368:80: ^( ARG VARIABLE )
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    {
                	    CommonTree _save_last_3 = _last;
                	    CommonTree _first_3 = null;
                	    CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                	    ARG70=(CommonTree)match(input,ARG,FOLLOW_ARG_in_function_definition_statement1405); 
                	    ARG70_tree = (CommonTree)adaptor.dupNode(ARG70);

                	    root_3 = (CommonTree)adaptor.becomeRoot(ARG70_tree, root_3);



                	    match(input, Token.DOWN, null); 
                	    _last = (CommonTree)input.LT(1);
                	    VARIABLE71=(CommonTree)match(input,VARIABLE,FOLLOW_VARIABLE_in_function_definition_statement1407); 
                	    VARIABLE71_tree = (CommonTree)adaptor.dupNode(VARIABLE71);

                	    adaptor.addChild(root_3, VARIABLE71_tree);

                	     params.add((VARIABLE71!=null?VARIABLE71.getText():null)); 

                	    match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
                	    }


                	    }
                	    break;

                	default :
                	    break loop15;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_block_in_function_definition_statement1416);
            block72=block();

            state._fsp--;

            adaptor.addChild(root_1, block72.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        NativeFunction fn = new NativeFunction((fn_name!=null?fn_name.getText():null), params, (block72!=null?block72.operations:null));
                        this.registerFunction((ns!=null?ns.getText():null), fn);

                        retval.op = new UnaryOperation(new Atom<Boolean>(true));
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_definition_statement"

    public static class function_call_return extends TreeRuleReturnScope {
        public Atom value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "function_call"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:377:1: function_call returns [Atom value] : ^( FUNC_CALL ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG (st= statement | col= collection ) ) )* ) ) ;
    public final GremlinEvaluator.function_call_return function_call() throws RecognitionException {
        GremlinEvaluator.function_call_return retval = new GremlinEvaluator.function_call_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree ns=null;
        CommonTree fn_name=null;
        CommonTree FUNC_CALL73=null;
        CommonTree FUNC_NAME74=null;
        CommonTree NS75=null;
        CommonTree NAME76=null;
        CommonTree ARGS77=null;
        CommonTree ARG78=null;
        GremlinEvaluator.statement_return st = null;

        GremlinEvaluator.collection_return col = null;


        CommonTree ns_tree=null;
        CommonTree fn_name_tree=null;
        CommonTree FUNC_CALL73_tree=null;
        CommonTree FUNC_NAME74_tree=null;
        CommonTree NS75_tree=null;
        CommonTree NAME76_tree=null;
        CommonTree ARGS77_tree=null;
        CommonTree ARG78_tree=null;


                List<Operation> params = new ArrayList<Operation>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:2: ( ^( FUNC_CALL ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG (st= statement | col= collection ) ) )* ) ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:4: ^( FUNC_CALL ^( FUNC_NAME ^( NS ns= IDENTIFIER ) ^( NAME fn_name= IDENTIFIER ) ) ^( ARGS ( ^( ARG (st= statement | col= collection ) ) )* ) )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            FUNC_CALL73=(CommonTree)match(input,FUNC_CALL,FOLLOW_FUNC_CALL_in_function_call1453); 
            FUNC_CALL73_tree = (CommonTree)adaptor.dupNode(FUNC_CALL73);

            root_1 = (CommonTree)adaptor.becomeRoot(FUNC_CALL73_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            FUNC_NAME74=(CommonTree)match(input,FUNC_NAME,FOLLOW_FUNC_NAME_in_function_call1456); 
            FUNC_NAME74_tree = (CommonTree)adaptor.dupNode(FUNC_NAME74);

            root_2 = (CommonTree)adaptor.becomeRoot(FUNC_NAME74_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            NS75=(CommonTree)match(input,NS,FOLLOW_NS_in_function_call1459); 
            NS75_tree = (CommonTree)adaptor.dupNode(NS75);

            root_3 = (CommonTree)adaptor.becomeRoot(NS75_tree, root_3);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            ns=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_function_call1463); 
            ns_tree = (CommonTree)adaptor.dupNode(ns);

            adaptor.addChild(root_3, ns_tree);


            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            NAME76=(CommonTree)match(input,NAME,FOLLOW_NAME_in_function_call1467); 
            NAME76_tree = (CommonTree)adaptor.dupNode(NAME76);

            root_3 = (CommonTree)adaptor.becomeRoot(NAME76_tree, root_3);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            fn_name=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_function_call1471); 
            fn_name_tree = (CommonTree)adaptor.dupNode(fn_name);

            adaptor.addChild(root_3, fn_name_tree);


            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }


            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            ARGS77=(CommonTree)match(input,ARGS,FOLLOW_ARGS_in_function_call1476); 
            ARGS77_tree = (CommonTree)adaptor.dupNode(ARGS77);

            root_2 = (CommonTree)adaptor.becomeRoot(ARGS77_tree, root_2);



            if ( input.LA(1)==Token.DOWN ) {
                match(input, Token.DOWN, null); 
                // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:83: ( ^( ARG (st= statement | col= collection ) ) )*
                loop17:
                do {
                    int alt17=2;
                    int LA17_0 = input.LA(1);

                    if ( (LA17_0==ARG) ) {
                        alt17=1;
                    }


                    switch (alt17) {
                	case 1 :
                	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:85: ^( ARG (st= statement | col= collection ) )
                	    {
                	    _last = (CommonTree)input.LT(1);
                	    {
                	    CommonTree _save_last_3 = _last;
                	    CommonTree _first_3 = null;
                	    CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                	    ARG78=(CommonTree)match(input,ARG,FOLLOW_ARG_in_function_call1481); 
                	    ARG78_tree = (CommonTree)adaptor.dupNode(ARG78);

                	    root_3 = (CommonTree)adaptor.becomeRoot(ARG78_tree, root_3);



                	    match(input, Token.DOWN, null); 
                	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:91: (st= statement | col= collection )
                	    int alt16=2;
                	    int LA16_0 = input.LA(1);

                	    if ( (LA16_0==VAR||LA16_0==FUNC||(LA16_0>=PATH && LA16_0<=GPATH)||(LA16_0>=FUNC_CALL && LA16_0<=IF)||(LA16_0>=FOREACH && LA16_0<=VARIABLE_CALL)||LA16_0==IDENTIFIER||(LA16_0>=69 && LA16_0<=70)||LA16_0==82||(LA16_0>=85 && LA16_0<=95)) ) {
                	        alt16=1;
                	    }
                	    else if ( (LA16_0==COLLECTION_CALL) ) {
                	        alt16=2;
                	    }
                	    else {
                	        NoViableAltException nvae =
                	            new NoViableAltException("", 16, 0, input);

                	        throw nvae;
                	    }
                	    switch (alt16) {
                	        case 1 :
                	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:92: st= statement
                	            {
                	            _last = (CommonTree)input.LT(1);
                	            pushFollow(FOLLOW_statement_in_function_call1486);
                	            st=statement();

                	            state._fsp--;

                	            adaptor.addChild(root_3, st.getTree());
                	             params.add((st!=null?st.op:null)); 

                	            }
                	            break;
                	        case 2 :
                	            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:381:131: col= collection
                	            {
                	            _last = (CommonTree)input.LT(1);
                	            pushFollow(FOLLOW_collection_in_function_call1494);
                	            col=collection();

                	            state._fsp--;

                	            adaptor.addChild(root_3, col.getTree());
                	             params.add((col!=null?col.op:null)); 

                	            }
                	            break;

                	    }


                	    match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
                	    }


                	    }
                	    break;

                	default :
                	    break loop17;
                    }
                } while (true);


                match(input, Token.UP, null); 
            }adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                        try {
                            retval.value = new Func(this.getFunction((ns!=null?ns.getText():null), (fn_name!=null?fn_name.getText():null)), params, this.context);
                        } catch(Exception e) {
                            System.err.println(e);
                        }
                    

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "function_call"

    public static class collection_return extends TreeRuleReturnScope {
        public Operation op;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "collection"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:391:1: collection returns [Operation op] : ^( COLLECTION_CALL ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )+ ) ) ) ;
    public final GremlinEvaluator.collection_return collection() throws RecognitionException {
        GremlinEvaluator.collection_return retval = new GremlinEvaluator.collection_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree COLLECTION_CALL79=null;
        CommonTree STEP80=null;
        CommonTree TOKEN81=null;
        CommonTree PREDICATES83=null;
        CommonTree PREDICATE84=null;
        GremlinEvaluator.token_return token82 = null;

        GremlinEvaluator.statement_return statement85 = null;


        CommonTree COLLECTION_CALL79_tree=null;
        CommonTree STEP80_tree=null;
        CommonTree TOKEN81_tree=null;
        CommonTree PREDICATES83_tree=null;
        CommonTree PREDICATE84_tree=null;


                Atom<Object> root = null;
                List<Pipe> pipes = new ArrayList<Pipe>();
                List<Operation> predicates = new ArrayList<Operation>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:397:5: ( ^( COLLECTION_CALL ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )+ ) ) ) )
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:397:7: ^( COLLECTION_CALL ^( STEP ^( TOKEN token ) ^( PREDICATES ( ^( PREDICATE statement ) )+ ) ) )
            {
            root_0 = (CommonTree)adaptor.nil();

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_1 = _last;
            CommonTree _first_1 = null;
            CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            COLLECTION_CALL79=(CommonTree)match(input,COLLECTION_CALL,FOLLOW_COLLECTION_CALL_in_collection1543); 
            COLLECTION_CALL79_tree = (CommonTree)adaptor.dupNode(COLLECTION_CALL79);

            root_1 = (CommonTree)adaptor.becomeRoot(COLLECTION_CALL79_tree, root_1);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_2 = _last;
            CommonTree _first_2 = null;
            CommonTree root_2 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            STEP80=(CommonTree)match(input,STEP,FOLLOW_STEP_in_collection1546); 
            STEP80_tree = (CommonTree)adaptor.dupNode(STEP80);

            root_2 = (CommonTree)adaptor.becomeRoot(STEP80_tree, root_2);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            TOKEN81=(CommonTree)match(input,TOKEN,FOLLOW_TOKEN_in_collection1549); 
            TOKEN81_tree = (CommonTree)adaptor.dupNode(TOKEN81);

            root_3 = (CommonTree)adaptor.becomeRoot(TOKEN81_tree, root_3);



            match(input, Token.DOWN, null); 
            _last = (CommonTree)input.LT(1);
            pushFollow(FOLLOW_token_in_collection1551);
            token82=token();

            state._fsp--;

            adaptor.addChild(root_3, token82.getTree());

            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }

            _last = (CommonTree)input.LT(1);
            {
            CommonTree _save_last_3 = _last;
            CommonTree _first_3 = null;
            CommonTree root_3 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            PREDICATES83=(CommonTree)match(input,PREDICATES,FOLLOW_PREDICATES_in_collection1555); 
            PREDICATES83_tree = (CommonTree)adaptor.dupNode(PREDICATES83);

            root_3 = (CommonTree)adaptor.becomeRoot(PREDICATES83_tree, root_3);



            match(input, Token.DOWN, null); 
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:397:60: ( ^( PREDICATE statement ) )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==PREDICATE) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:397:62: ^( PREDICATE statement )
            	    {
            	    _last = (CommonTree)input.LT(1);
            	    {
            	    CommonTree _save_last_4 = _last;
            	    CommonTree _first_4 = null;
            	    CommonTree root_4 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
            	    PREDICATE84=(CommonTree)match(input,PREDICATE,FOLLOW_PREDICATE_in_collection1560); 
            	    PREDICATE84_tree = (CommonTree)adaptor.dupNode(PREDICATE84);

            	    root_4 = (CommonTree)adaptor.becomeRoot(PREDICATE84_tree, root_4);



            	    match(input, Token.DOWN, null); 
            	    _last = (CommonTree)input.LT(1);
            	    pushFollow(FOLLOW_statement_in_collection1562);
            	    statement85=statement();

            	    state._fsp--;

            	    adaptor.addChild(root_4, statement85.getTree());
            	     predicates.add((statement85!=null?statement85.op:null)); 

            	    match(input, Token.UP, null); adaptor.addChild(root_3, root_4);_last = _save_last_4;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);


            match(input, Token.UP, null); adaptor.addChild(root_2, root_3);_last = _save_last_3;
            }


            match(input, Token.UP, null); adaptor.addChild(root_1, root_2);_last = _save_last_2;
            }


            match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
            }


                    final Atom tokenAtom = (token82!=null?token82.atom:null);
                    final PathLibrary paths = this.context.getPathLibrary();

                    if (tokenAtom != null) {
                        if (tokenAtom instanceof DynamicEntity) {
                            root = tokenAtom;
                        } else if (paths.isPath(tokenAtom.toString())) {
                            pipes.addAll(paths.getPath(tokenAtom.toString()));
                            root = this.getVariable(Tokens.ROOT_VARIABLE);
                        } else {
                            root = tokenAtom;
                        }

                        pipes.addAll(GremlinPipesHelper.pipesForStep(predicates, this.context));
                    }
                    
                    retval.op = new GPathOperation(pipes, root, this.context);
                

            }

            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "collection"

    public static class atom_return extends TreeRuleReturnScope {
        public Atom value;
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start "atom"
    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:420:1: atom returns [Atom value] : ( ^( INT G_INT ) | ^( LONG G_LONG ) | ^( FLOAT G_FLOAT ) | ^( DOUBLE G_DOUBLE ) | ^( RANGE min= G_INT max= G_INT ) | ^( STR StringLiteral ) | ^( BOOL b= BOOLEAN ) | NULL | ^( ARR ( NUMBER )+ ) | ^( VARIABLE_CALL VARIABLE ) | ^( PROPERTY_CALL PROPERTY ) | IDENTIFIER | function_call | '(' statement ')' );
    public final GremlinEvaluator.atom_return atom() throws RecognitionException {
        GremlinEvaluator.atom_return retval = new GremlinEvaluator.atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        CommonTree _first_0 = null;
        CommonTree _last = null;

        CommonTree min=null;
        CommonTree max=null;
        CommonTree b=null;
        CommonTree INT86=null;
        CommonTree G_INT87=null;
        CommonTree LONG88=null;
        CommonTree G_LONG89=null;
        CommonTree FLOAT90=null;
        CommonTree G_FLOAT91=null;
        CommonTree DOUBLE92=null;
        CommonTree G_DOUBLE93=null;
        CommonTree RANGE94=null;
        CommonTree STR95=null;
        CommonTree StringLiteral96=null;
        CommonTree BOOL97=null;
        CommonTree NULL98=null;
        CommonTree ARR99=null;
        CommonTree NUMBER100=null;
        CommonTree VARIABLE_CALL101=null;
        CommonTree VARIABLE102=null;
        CommonTree PROPERTY_CALL103=null;
        CommonTree PROPERTY104=null;
        CommonTree IDENTIFIER105=null;
        CommonTree char_literal107=null;
        CommonTree char_literal109=null;
        GremlinEvaluator.function_call_return function_call106 = null;

        GremlinEvaluator.statement_return statement108 = null;


        CommonTree min_tree=null;
        CommonTree max_tree=null;
        CommonTree b_tree=null;
        CommonTree INT86_tree=null;
        CommonTree G_INT87_tree=null;
        CommonTree LONG88_tree=null;
        CommonTree G_LONG89_tree=null;
        CommonTree FLOAT90_tree=null;
        CommonTree G_FLOAT91_tree=null;
        CommonTree DOUBLE92_tree=null;
        CommonTree G_DOUBLE93_tree=null;
        CommonTree RANGE94_tree=null;
        CommonTree STR95_tree=null;
        CommonTree StringLiteral96_tree=null;
        CommonTree BOOL97_tree=null;
        CommonTree NULL98_tree=null;
        CommonTree ARR99_tree=null;
        CommonTree NUMBER100_tree=null;
        CommonTree VARIABLE_CALL101_tree=null;
        CommonTree VARIABLE102_tree=null;
        CommonTree PROPERTY_CALL103_tree=null;
        CommonTree PROPERTY104_tree=null;
        CommonTree IDENTIFIER105_tree=null;
        CommonTree char_literal107_tree=null;
        CommonTree char_literal109_tree=null;


                List<Double> array = new ArrayList<Double>();
            
        try {
            // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:424:2: ( ^( INT G_INT ) | ^( LONG G_LONG ) | ^( FLOAT G_FLOAT ) | ^( DOUBLE G_DOUBLE ) | ^( RANGE min= G_INT max= G_INT ) | ^( STR StringLiteral ) | ^( BOOL b= BOOLEAN ) | NULL | ^( ARR ( NUMBER )+ ) | ^( VARIABLE_CALL VARIABLE ) | ^( PROPERTY_CALL PROPERTY ) | IDENTIFIER | function_call | '(' statement ')' )
            int alt20=14;
            switch ( input.LA(1) ) {
            case INT:
                {
                alt20=1;
                }
                break;
            case LONG:
                {
                alt20=2;
                }
                break;
            case FLOAT:
                {
                alt20=3;
                }
                break;
            case DOUBLE:
                {
                alt20=4;
                }
                break;
            case RANGE:
                {
                alt20=5;
                }
                break;
            case STR:
                {
                alt20=6;
                }
                break;
            case BOOL:
                {
                alt20=7;
                }
                break;
            case NULL:
                {
                alt20=8;
                }
                break;
            case ARR:
                {
                alt20=9;
                }
                break;
            case VARIABLE_CALL:
                {
                alt20=10;
                }
                break;
            case PROPERTY_CALL:
                {
                alt20=11;
                }
                break;
            case IDENTIFIER:
                {
                alt20=12;
                }
                break;
            case FUNC_CALL:
                {
                alt20=13;
                }
                break;
            case 82:
                {
                alt20=14;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:424:6: ^( INT G_INT )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    INT86=(CommonTree)match(input,INT,FOLLOW_INT_in_atom1609); 
                    INT86_tree = (CommonTree)adaptor.dupNode(INT86);

                    root_1 = (CommonTree)adaptor.becomeRoot(INT86_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    G_INT87=(CommonTree)match(input,G_INT,FOLLOW_G_INT_in_atom1611); 
                    G_INT87_tree = (CommonTree)adaptor.dupNode(G_INT87);

                    adaptor.addChild(root_1, G_INT87_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom<Integer>(new Integer((G_INT87!=null?G_INT87.getText():null))); 

                    }
                    break;
                case 2 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:425:6: ^( LONG G_LONG )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    LONG88=(CommonTree)match(input,LONG,FOLLOW_LONG_in_atom1669); 
                    LONG88_tree = (CommonTree)adaptor.dupNode(LONG88);

                    root_1 = (CommonTree)adaptor.becomeRoot(LONG88_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    G_LONG89=(CommonTree)match(input,G_LONG,FOLLOW_G_LONG_in_atom1671); 
                    G_LONG89_tree = (CommonTree)adaptor.dupNode(G_LONG89);

                    adaptor.addChild(root_1, G_LONG89_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }


                    	                                                                    String longStr = (G_LONG89!=null?G_LONG89.getText():null);
                    	                                                                    retval.value = new Atom<Long>(new Long(longStr.substring(0, longStr.length() - 1)));
                    	                                                                

                    }
                    break;
                case 3 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:429:6: ^( FLOAT G_FLOAT )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    FLOAT90=(CommonTree)match(input,FLOAT,FOLLOW_FLOAT_in_atom1727); 
                    FLOAT90_tree = (CommonTree)adaptor.dupNode(FLOAT90);

                    root_1 = (CommonTree)adaptor.becomeRoot(FLOAT90_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    G_FLOAT91=(CommonTree)match(input,G_FLOAT,FOLLOW_G_FLOAT_in_atom1729); 
                    G_FLOAT91_tree = (CommonTree)adaptor.dupNode(G_FLOAT91);

                    adaptor.addChild(root_1, G_FLOAT91_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom<Float>(new Float((G_FLOAT91!=null?G_FLOAT91.getText():null))); 

                    }
                    break;
                case 4 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:430:6: ^( DOUBLE G_DOUBLE )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    DOUBLE92=(CommonTree)match(input,DOUBLE,FOLLOW_DOUBLE_in_atom1783); 
                    DOUBLE92_tree = (CommonTree)adaptor.dupNode(DOUBLE92);

                    root_1 = (CommonTree)adaptor.becomeRoot(DOUBLE92_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    G_DOUBLE93=(CommonTree)match(input,G_DOUBLE,FOLLOW_G_DOUBLE_in_atom1785); 
                    G_DOUBLE93_tree = (CommonTree)adaptor.dupNode(G_DOUBLE93);

                    adaptor.addChild(root_1, G_DOUBLE93_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }


                    	                                                                    String doubleStr = (G_DOUBLE93!=null?G_DOUBLE93.getText():null);
                    	                                                                    retval.value = new Atom<Double>(new Double(doubleStr.substring(0, doubleStr.length() - 1)));
                    	                                                                

                    }
                    break;
                case 5 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:434:6: ^( RANGE min= G_INT max= G_INT )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    RANGE94=(CommonTree)match(input,RANGE,FOLLOW_RANGE_in_atom1837); 
                    RANGE94_tree = (CommonTree)adaptor.dupNode(RANGE94);

                    root_1 = (CommonTree)adaptor.becomeRoot(RANGE94_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    min=(CommonTree)match(input,G_INT,FOLLOW_G_INT_in_atom1841); 
                    min_tree = (CommonTree)adaptor.dupNode(min);

                    adaptor.addChild(root_1, min_tree);

                    _last = (CommonTree)input.LT(1);
                    max=(CommonTree)match(input,G_INT,FOLLOW_G_INT_in_atom1845); 
                    max_tree = (CommonTree)adaptor.dupNode(max);

                    adaptor.addChild(root_1, max_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom(new Range((min!=null?min.getText():null), (max!=null?max.getText():null))); 

                    }
                    break;
                case 6 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:435:4: ^( STR StringLiteral )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    STR95=(CommonTree)match(input,STR,FOLLOW_STR_in_atom1885); 
                    STR95_tree = (CommonTree)adaptor.dupNode(STR95);

                    root_1 = (CommonTree)adaptor.becomeRoot(STR95_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    StringLiteral96=(CommonTree)match(input,StringLiteral,FOLLOW_StringLiteral_in_atom1887); 
                    StringLiteral96_tree = (CommonTree)adaptor.dupNode(StringLiteral96);

                    adaptor.addChild(root_1, StringLiteral96_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom((StringLiteral96!=null?StringLiteral96.getText():null)); 

                    }
                    break;
                case 7 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:436:9: ^( BOOL b= BOOLEAN )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    BOOL97=(CommonTree)match(input,BOOL,FOLLOW_BOOL_in_atom1940); 
                    BOOL97_tree = (CommonTree)adaptor.dupNode(BOOL97);

                    root_1 = (CommonTree)adaptor.becomeRoot(BOOL97_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    b=(CommonTree)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_atom1944); 
                    b_tree = (CommonTree)adaptor.dupNode(b);

                    adaptor.addChild(root_1, b_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom(new Boolean((b!=null?b.getText():null))); 

                    }
                    break;
                case 8 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:437:9: NULL
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    NULL98=(CommonTree)match(input,NULL,FOLLOW_NULL_in_atom1999); 
                    NULL98_tree = (CommonTree)adaptor.dupNode(NULL98);

                    adaptor.addChild(root_0, NULL98_tree);

                     retval.value = new Atom(null); 

                    }
                    break;
                case 9 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:438:9: ^( ARR ( NUMBER )+ )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    ARR99=(CommonTree)match(input,ARR,FOLLOW_ARR_in_atom2067); 
                    ARR99_tree = (CommonTree)adaptor.dupNode(ARR99);

                    root_1 = (CommonTree)adaptor.becomeRoot(ARR99_tree, root_1);



                    match(input, Token.DOWN, null); 
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:438:15: ( NUMBER )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==NUMBER) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:438:16: NUMBER
                    	    {
                    	    _last = (CommonTree)input.LT(1);
                    	    NUMBER100=(CommonTree)match(input,NUMBER,FOLLOW_NUMBER_in_atom2070); 
                    	    NUMBER100_tree = (CommonTree)adaptor.dupNode(NUMBER100);

                    	    adaptor.addChild(root_1, NUMBER100_tree);

                    	     array.add(new Double((NUMBER100!=null?NUMBER100.getText():null))); 

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     retval.value = new Atom(array); 

                    }
                    break;
                case 10 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:439:4: ^( VARIABLE_CALL VARIABLE )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    VARIABLE_CALL101=(CommonTree)match(input,VARIABLE_CALL,FOLLOW_VARIABLE_CALL_in_atom2085); 
                    VARIABLE_CALL101_tree = (CommonTree)adaptor.dupNode(VARIABLE_CALL101);

                    root_1 = (CommonTree)adaptor.becomeRoot(VARIABLE_CALL101_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    VARIABLE102=(CommonTree)match(input,VARIABLE,FOLLOW_VARIABLE_in_atom2087); 
                    VARIABLE102_tree = (CommonTree)adaptor.dupNode(VARIABLE102);

                    adaptor.addChild(root_1, VARIABLE102_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     
                                                                                          retval.value = this.getVariable((VARIABLE102!=null?VARIABLE102.getText():null)); 
                                                                                        

                    }
                    break;
                case 11 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:442:4: ^( PROPERTY_CALL PROPERTY )
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    {
                    CommonTree _save_last_1 = _last;
                    CommonTree _first_1 = null;
                    CommonTree root_1 = (CommonTree)adaptor.nil();_last = (CommonTree)input.LT(1);
                    PROPERTY_CALL103=(CommonTree)match(input,PROPERTY_CALL,FOLLOW_PROPERTY_CALL_in_atom2130); 
                    PROPERTY_CALL103_tree = (CommonTree)adaptor.dupNode(PROPERTY_CALL103);

                    root_1 = (CommonTree)adaptor.becomeRoot(PROPERTY_CALL103_tree, root_1);



                    match(input, Token.DOWN, null); 
                    _last = (CommonTree)input.LT(1);
                    PROPERTY104=(CommonTree)match(input,PROPERTY,FOLLOW_PROPERTY_in_atom2132); 
                    PROPERTY104_tree = (CommonTree)adaptor.dupNode(PROPERTY104);

                    adaptor.addChild(root_1, PROPERTY104_tree);


                    match(input, Token.UP, null); adaptor.addChild(root_0, root_1);_last = _save_last_1;
                    }

                     
                                                                                            Atom propertyAtom = new Atom((PROPERTY104!=null?PROPERTY104.getText():null).substring(1));
                                                                                            propertyAtom.setProperty(true);
                                                                                            retval.value = propertyAtom;
                                                                                        

                    }
                    break;
                case 12 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:447:4: IDENTIFIER
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    IDENTIFIER105=(CommonTree)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_atom2174); 
                    IDENTIFIER105_tree = (CommonTree)adaptor.dupNode(IDENTIFIER105);

                    adaptor.addChild(root_0, IDENTIFIER105_tree);


                    	                                                                    String idText = (IDENTIFIER105!=null?IDENTIFIER105.getText():null);
                                                                                            
                    	                                                                    if (idText.equals(".") && !isGPath) {
                    	                                                                        Atom id  = this.getVariable(Tokens.ROOT_VARIABLE);
                    	                                                                        Atom dot = new Atom(id.getValue());
                    	                                                                        dot.setIdentifier(true);
                    	                                                                        retval.value = dot;
                    	                                                                    } else if (idText.matches("^[\\d]+..[\\d]+")) {
                                                                                                Matcher range = rangePattern.matcher(idText);
                                                                                                if (range.matches())
                                                                                                    retval.value = new Atom<Range>(new Range(range.group(1), range.group(2)));
                                                                                                else
                                                                                                    retval.value = new Atom(null);
                    	                                                                    } else {
                                                                                                Atom idAtom = new Atom<String>((IDENTIFIER105!=null?IDENTIFIER105.getText():null));
                                                                                                idAtom.setIdentifier(true);
                                                                                                retval.value = idAtom;
                                                                                            }
                                                                                        

                    }
                    break;
                case 13 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:467:4: function_call
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_function_call_in_atom2230);
                    function_call106=function_call();

                    state._fsp--;

                    adaptor.addChild(root_0, function_call106.getTree());
                     retval.value = (function_call106!=null?function_call106.value:null); 

                    }
                    break;
                case 14 :
                    // src/main/java/com/tinkerpop/gremlin/compiler/GremlinEvaluator.g:468:4: '(' statement ')'
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    _last = (CommonTree)input.LT(1);
                    char_literal107=(CommonTree)match(input,82,FOLLOW_82_in_atom2283); 
                    _last = (CommonTree)input.LT(1);
                    pushFollow(FOLLOW_statement_in_atom2286);
                    statement108=statement();

                    state._fsp--;

                    adaptor.addChild(root_0, statement108.getTree());
                    _last = (CommonTree)input.LT(1);
                    char_literal109=(CommonTree)match(input,83,FOLLOW_83_in_atom2288); 

                    }
                    break;

            }
            retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "atom"

    // Delegated rules


 

    public static final BitSet FOLLOW_statement_in_program60 = new BitSet(new long[]{0x000029FFFF181892L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_collection_in_program73 = new BitSet(new long[]{0x000029FFFF181892L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_VAR_in_program80 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_program82 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_collection_in_program86 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NEWLINE_in_program92 = new BitSet(new long[]{0x000029FFFF181892L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_if_statement_in_statement120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_foreach_statement_in_statement150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_while_statement_in_statement178 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_repeat_statement_in_statement205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_path_definition_statement_in_statement231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_definition_statement_in_statement248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_include_statement_in_statement261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_script_statement_in_statement288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gpath_statement_in_statement314 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_VAR_in_statement342 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_statement344 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_statement348 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_69_in_statement370 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_statement374 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_statement378 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_70_in_statement395 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_statement400 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_statement404 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_statement420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SCRIPT_in_script_statement466 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_StringLiteral_in_script_statement468 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INCLUDE_in_include_statement496 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_StringLiteral_in_include_statement498 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PATH_in_path_definition_statement535 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_path_definition_statement539 = new BitSet(new long[]{0x0000004000001000L});
    public static final BitSet FOLLOW_gpath_statement_in_path_definition_statement544 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_CALL_in_path_definition_statement551 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PROPERTY_in_path_definition_statement555 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_GPATH_in_gpath_statement614 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_step_in_gpath_statement617 = new BitSet(new long[]{0x0000000000002008L});
    public static final BitSet FOLLOW_STEP_in_step655 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOKEN_in_step658 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_token_in_step660 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PREDICATES_in_step664 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PREDICATE_in_step669 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_step671 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_expression_in_token713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_gpath_statement_in_token725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_collection_in_token737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_token749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_if_statement774 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_COND_in_if_statement777 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_if_statement781 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_if_statement786 = new BitSet(new long[]{0x0000000000200008L});
    public static final BitSet FOLLOW_ELSE_in_if_statement791 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_block_in_if_statement795 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_WHILE_in_while_statement828 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_COND_in_while_statement831 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_while_statement835 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_while_statement838 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FOREACH_in_foreach_statement865 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_foreach_statement867 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_foreach_statement871 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_foreach_statement873 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_REPEAT_in_repeat_statement901 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_repeat_statement905 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_block_in_repeat_statement907 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BLOCK_in_block946 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_block951 = new BitSet(new long[]{0x000021FFFF181898L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_collection_in_block957 = new BitSet(new long[]{0x000021FFFF181898L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_85_in_expression986 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression991 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression995 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_86_in_expression1009 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression1013 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression1017 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_87_in_expression1031 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression1036 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression1040 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_89_in_expression1054 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression1059 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression1063 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_88_in_expression1077 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression1081 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression1085 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_90_in_expression1099 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_expression1103 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_expression1107 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_operation_in_expression1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_operation1165 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_operation1169 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_operation_in_operation1173 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_92_in_operation1187 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_operation1191 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_operation_in_operation1195 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_binary_operation_in_operation1208 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_93_in_binary_operation1245 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_binary_operation1249 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_operation_in_binary_operation1253 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_94_in_binary_operation1272 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_binary_operation1276 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_operation_in_binary_operation1280 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_95_in_binary_operation1295 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_operation_in_binary_operation1299 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_operation_in_binary_operation1303 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_atom_in_binary_operation1319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNC_in_function_definition_statement1377 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNC_NAME_in_function_definition_statement1380 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NS_in_function_definition_statement1383 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_function_definition_statement1387 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_function_definition_statement1391 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_function_definition_statement1395 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGS_in_function_definition_statement1400 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_in_function_definition_statement1405 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_function_definition_statement1407 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_block_in_function_definition_statement1416 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FUNC_CALL_in_function_call1453 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_FUNC_NAME_in_function_call1456 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NS_in_function_call1459 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_function_call1463 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NAME_in_function_call1467 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_IDENTIFIER_in_function_call1471 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_ARGS_in_function_call1476 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_ARG_in_function_call1481 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_function_call1486 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_collection_in_function_call1494 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_COLLECTION_CALL_in_collection1543 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_STEP_in_collection1546 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_TOKEN_in_collection1549 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_token_in_collection1551 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PREDICATES_in_collection1555 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PREDICATE_in_collection1560 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_statement_in_collection1562 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_INT_in_atom1609 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_G_INT_in_atom1611 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_LONG_in_atom1669 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_G_LONG_in_atom1671 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_FLOAT_in_atom1727 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_G_FLOAT_in_atom1729 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_DOUBLE_in_atom1783 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_G_DOUBLE_in_atom1785 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_RANGE_in_atom1837 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_G_INT_in_atom1841 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_G_INT_in_atom1845 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_STR_in_atom1885 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_StringLiteral_in_atom1887 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_BOOL_in_atom1940 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_BOOLEAN_in_atom1944 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_NULL_in_atom1999 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ARR_in_atom2067 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_NUMBER_in_atom2070 = new BitSet(new long[]{0x0000000000000008L,0x0000000200000000L});
    public static final BitSet FOLLOW_VARIABLE_CALL_in_atom2085 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_VARIABLE_in_atom2087 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_PROPERTY_CALL_in_atom2130 = new BitSet(new long[]{0x0000000000000004L});
    public static final BitSet FOLLOW_PROPERTY_in_atom2132 = new BitSet(new long[]{0x0000000000000008L});
    public static final BitSet FOLLOW_IDENTIFIER_in_atom2174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_function_call_in_atom2230 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_atom2283 = new BitSet(new long[]{0x000020FFFF181890L,0x00000000FFE40060L});
    public static final BitSet FOLLOW_statement_in_atom2286 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_atom2288 = new BitSet(new long[]{0x0000000000000002L});

}