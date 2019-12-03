%{
  #include "node.h"

  using namespace std;

  extern int yylex();
  extern const char *yytext;
  extern const char *yyval;
  void yyerror(const char *s) { printf("ERROR: %s\n", s); }

  AST::Node root;
%}

%union {
  int integer;
  long longVal;
  char *string;
}

// Keywords
%token <string> ID
%token <string> VAR
%token <string> VAL
%token <string> FUN

// Type Identifiers
%token STRING_TYPE
%token INTEGER_TYPE
%token FLOAT_TYPE
%token DOUBLE_TYPE

// Type Values
%token <integer> INTEGER
%token <longVal> LONG
%token DOUBLE
%token FLOAT
%token <string> STRING

// Arithmetic Operators
%token <string> EQ
%token PLUS
%token MINUS
%token STAR
%token FORWARD_SLASH

// Symbols
%token COLON
%token DOUBLE_QUOTE
%token SINGLE_QUOTE
%token RIGHT_ARROW

// Parentheses
%token LEFT_PAREN
%token RIGHT_PAREN
%token LEFT_BRACE
%token RIGHT_BRACE
%token RETURN

// Newline
%token NEWLINE

// Precedence
%precedence EQ
%left MINUS PLUS
%left STAR FORWARD_SLASH

%start expr_list

%%

expr_list:
    %empty
  | expr expr_list
;

expr:
    value
  | ID
  | assignment
  | function
  | lambda
  | arithmetic
;

value:
    INTEGER
  | FLOAT
  | STRING

type:
    FLOAT_TYPE
  | INTEGER_TYPE
  | STRING_TYPE

assignment:
  VAR ID EQ INTEGER
  {
    AST::Assignment a($2, AST::value($<integer>4));
  }

function:
  FUN ID LEFT_PAREN RIGHT_PAREN COLON type LEFT_BRACE
    expr_list
    RETURN expr
  RIGHT_BRACE
  {
    AST::Function f($2, $<string>6);
    f.parent = &root;
    root.children.push_back(f);
  }

lambda:
  LEFT_BRACE ID COLON type RIGHT_ARROW expr_list RIGHT_BRACE
  {
    AST::Lambda l;
    l.parent = &root;
    root.children.push_back(l);
  }

arithmetic:
    expr PLUS expr
    { $<integer>$ = $<integer>1 + $<integer>3; }
  | expr MINUS expr
    { $<integer>$ = $<integer>1 - $<integer>3; }
  | expr STAR expr
    { $<integer>$ = $<integer>1 * $<integer>3; }
  | expr FORWARD_SLASH expr
    { $<integer>$ = $<integer>1 / $<integer>3; }
  | LEFT_PAREN expr RIGHT_PAREN
    { $<integer>$ = $<integer>2; }

%%
