package lexi.frontend.kotlin.phases

import lexi.frontend.kotlin.antlr.KotlinParser.PropertyDeclarationContext
import lexi.frontend.kotlin.ast.{ASTNode, KtFunction, KtProperty}

object TypeInference:
  def apply(ast: ASTNode): ASTNode = ast
//    ast match {
//      case property: KtProperty => inferPropertyType(property)
//      case function: KtFunction => inferFunctionType(function)
//    }

//  private def inferType(astNode: ASTNode): ASTNode = {
//    val IntPattern = "\\d+".r
//    val StringPattern = """^".*"$""".r
//  }
//
//  private def inferPropertyType(ktProperty: KtProperty): KtProperty = {
//    val IntPattern = "\\d+".r
//    val StringPattern = """^".*"$""".r
//    val inferredType = ktProperty.context
//      .asInstanceOf[PropertyDeclarationContext]
//      .expression
//      .getText match {
//      case IntPattern()    => "Int"
//      case StringPattern() => "String"
//    }
//    ktProperty.copy(dataType = inferredType)
//  }
//
//  private def inferFunctionType(ktFunction: KtFunction): KtFunction = {
//    val IntPattern = "\\d+".r
//    val StringPattern = """^".*"$""".r
//    val inferredType = ktFunction.context
//      .asInstanceOf[PropertyDeclarationContext]
//      .expression
//      .getText match {
//      case IntPattern()    => "Int"
//      case StringPattern() => "String"
//    }
//    ktFunction.copy(`type` = inferredType)
//  }
