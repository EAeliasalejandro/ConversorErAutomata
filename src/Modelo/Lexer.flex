package Modelo;
import static Modelo.Tokens.*;
%%
%public %class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"*" {lexeme=yytext();return Cerradura;}
"+" {lexeme=yytext();return UnoOmas;}
"|" {lexeme=yytext();return Or;}
"." {lexeme=yytext();return Adicion;}
"(" {lexeme=yytext();return ParAb;}
")" {lexeme=yytext();return ParCe;}
{L} {lexeme=yytext(); return Elemento;}
{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}