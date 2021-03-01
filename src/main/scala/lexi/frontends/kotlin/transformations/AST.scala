package lexi.frontends.kotlin.transformations

import lexi.frontends.kotlin._
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters._
import scala.util.Try

object AST {
  object AdditiveExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtAdditiveExpression]] {
    override def visitAdditiveExpression(ctx: KotlinParser.AdditiveExpressionContext) = parentNode =>
      Try {
        new KtAdditiveExpression {
          parent = parentNode
          context = Some(ctx)
          ctx.multiplicativeExpression.forEach(
            MultiplicativeExpressionContext
              .visit(_)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object AsExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtAsExpression]] {
    override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) = parentNode =>
      Try {
        new KtAsExpression {
          parent = parentNode
          context = Some(ctx)
          PrefixUnaryExpressionContext
            .visit(ctx.prefixUnaryExpression)(Some(this))
            .map(children addOne _)
        }
      }.toOption
  }

  object ClassBodyContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtClassBody]] {
    override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = parentNode =>
      Try {
        new KtClassBody {
          context = Some(ctx)
          parent = parentNode
          ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
            .map(
              DeclarationContext
                .visit(_)(Some(this))
                .map(node => children = children :+ node)
            )
        }
      }.toOption
  }

  object ClassDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtClass]] {
    override def visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) = parentNode =>
      Try {
        new KtClass {
          context = Some(ctx)
          parent = parentNode
          SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this)).map { node =>
            children = children :+ node
          }
          PrimaryConstructorContext.visit(ctx.primaryConstructor)(Some(this)).map { node =>
            children = children :+ node
          }
          ClassBodyContext.visit(ctx.classBody)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object SimpleIdentifierContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtSimpleIdentifier]] {
    override def visitSimpleIdentifier(ctx: KotlinParser.SimpleIdentifierContext) = parentNode =>
      Try {
        new KtSimpleIdentifier(name = ctx.getText) {
          context = Some(ctx)
          parent = parentNode
        }
      }.toOption
  }

  object ClassParameterContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtClassParameter]] {
    override def visitClassParameter(ctx: KotlinParser.ClassParameterContext) = parentNode =>
      Try {
        new KtClassParameter {
          context = Some(ctx)
          parent = parentNode
          SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this)).map { node =>
            children = children :+ node
          }
          TypeContext.visit(ctx.`type`)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object TypeContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtType]] {
    override def visitType(ctx: KotlinParser.TypeContext) = parentNode =>
      Try {
        new KtType(ctx.getText) {
          context = Some(ctx)
          parent = parentNode
        }
      }.toOption
  }

  object ComparisonContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtComparison]] {
    override def visitComparison(ctx: KotlinParser.ComparisonContext) = parentNode =>
      Try {
        new KtComparison {
          parent = parentNode
          context = Some(ctx)
          ctx.genericCallLikeComparison.forEach(ctx =>
            GenericCallLikeComparisonContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object ConjunctionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtConjunction]] {
    override def visitConjunction(ctx: KotlinParser.ConjunctionContext) = parentNode =>
      Try {
        new KtConjunction {
          parent = parentNode
          context = Some(ctx)
          ctx.equality.forEach(ctx =>
            EqualityContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object DeclarationContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtDeclaration]] {
    override def visitDeclaration(ctx: KotlinParser.DeclarationContext) = parentNode =>
      Try {
        new KtDeclaration {
          parent = parentNode
          context = Some(ctx)
          if (ctx.classDeclaration != null)
            ClassDeclarationContext
              .visit(ctx.classDeclaration)(Some(this))
              .map(children addOne _)
          if (ctx.propertyDeclaration != null)
            PropertyDeclarationContext
              .visit(ctx.propertyDeclaration)(Some(this))
              .map(children addOne _)
          if (ctx.functionDeclaration != null)
            FunctionDeclarationContext
              .visit(ctx.functionDeclaration)(Some(this))
              .map(children addOne _)
        }
      }.toOption
  }

  object DisjunctionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtDisjunction]] {
    override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) = parentNode =>
      Try {
        new KtDisjunction {
          parent = parentNode
          context = Some(ctx)
          ctx.conjunction.forEach(ctx =>
            ConjunctionContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object ElvisExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtElvisExpression]] {
    override def visitElvisExpression(ctx: KotlinParser.ElvisExpressionContext) = { parentNode =>
      Try {
        new KtElvisExpression {
          parent = parentNode
          context = Some(ctx)
          ctx.infixFunctionCall.forEach(ctx =>
            InfixFunctionCallContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
    }
  }

  object EqualityContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtEquality]] {
    override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
      Try {
        new KtEquality {
          parent = parentNode
          context = Some(ctx)
          ctx.comparison.forEach(ctx =>
            ComparisonContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object ExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtExpression]] {
    override def visitExpression(ctx: KotlinParser.ExpressionContext) = parentNode =>
      Try {
        new KtExpression {
          parent = parentNode
          context = Some(ctx)
          DisjunctionContext
            .visit(ctx.disjunction)(Some(this))
            .map(children addOne _)
        }
      }.toOption
  }

  object BlockExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtBlockExpression]] {
    override def visitBlock(ctx: KotlinParser.BlockContext) = parentNode =>
      Try {
        new KtBlockExpression {
          ctx.statements.statement.forEach(ctx =>
            ExpressionContext
              .visit(ctx)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object FunctionDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtNamedFunction]] {
    override def visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) = parentNode =>
      Try {
        new KtNamedFunction {
          parent = parentNode
          context = Some(ctx)
          TypeContext.visit(ctx.`type`)(Some(this)).map { node =>
            children = children :+ node
          }
          SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this)).map { node =>
            children = children :+ node
          }
          if (ctx.functionBody.expression != null) {
            ExpressionContext.visit(ctx.functionBody.expression)(Some(this)).map { node =>
              children = children :+ node
            }
          } else if (ctx.functionBody.block != null) {
            BlockExpressionContext.visit(ctx.functionBody.block)(Some(this)).map { node =>
              children = children :+ node
            }
          }
        }
      }.toOption
  }

  object GenericCallLikeComparisonContext
    extends KotlinParserBaseVisitor[Option[AST] => Option[KtGenericCallLikeComparison]] {
    override def visitGenericCallLikeComparison(ctx: KotlinParser.GenericCallLikeComparisonContext) = parentNode =>
      Try {
        new KtGenericCallLikeComparison {
          parent = parentNode
          context = Some(ctx)
          InfixOperationContext
            .visit(ctx.infixOperation)(Some(this))
            .map(children addOne _)
        }
      }.toOption
  }

  object InfixFunctionCallContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtInfixFunctionCall]] {
    override def visitInfixFunctionCall(ctx: KotlinParser.InfixFunctionCallContext) = parentNode =>
      Try {
        new KtInfixFunctionCall {
          parent = parentNode
          context = Some(ctx)
          ctx.rangeExpression.forEach(
            RangeExpressionContext
              .visit(_)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object InfixOperationContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtInfixOperation]] {
    override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = parentNode =>
      Try {
        new KtInfixOperation {
          parent = parentNode
          context = Some(ctx)
          ctx.elvisExpression.forEach(
            ElvisExpressionContext
              .visit(_)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object KotlinFileContext extends KotlinParserBaseVisitor[Option[KtFile]] {
    override def visitKotlinFile(ctx: KotlinParser.KotlinFileContext) =
      Try {
        new KtFile {
          context = Some(ctx)
          ctx.topLevelObject.forEach(
            TopLevelObjectContext
              .visit(_)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object LineStringContentContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtLineStringContent]] {
    override def visitLineStringContent(ctx: KotlinParser.LineStringContentContext) = parentNode =>
      Try {
        new KtLineStringContent {
          parent = parentNode
          context = Some(ctx)
          lineStrText = Try(ctx.LineStrText.toString).toOption
        }
      }.toOption
  }

  object LineStringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtLineStringLiteral]] {
    override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
      Try {
        new KtLineStringLiteral {
          parent = parentNode
          context = Some(ctx)
          ctx.lineStringContent.forEach(
            LineStringContentContext.visit(_)(Some(this)).map(children addOne _)
          )
        }
      }.toOption
  }

  object MultiplicativeExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => Option[KtMultiplicativeExpression]] {
    override def visitMultiplicativeExpression(ctx: KotlinParser.MultiplicativeExpressionContext) = parentNode =>
      Try {
        new KtMultiplicativeExpression {
          parent = parentNode
          context = Some(ctx)
          ctx.asExpression.forEach(
            AsExpressionContext.visit(_)(Some(this)).map(children addOne _)
          )
        }
      }.toOption
  }

  object PostfixUnaryExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => Option[KtPostfixUnaryExpression]] {
    override def visitPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext) = parentNode =>
      Try {
        new KtPostfixUnaryExpression {
          parent = parentNode
          context = Some(ctx)
        }
      }.toOption
  }

  object PrefixUnaryExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtPrefixUnaryExpression]] {
    override def visitPrefixUnaryExpression(ctx: KotlinParser.PrefixUnaryExpressionContext) = parentNode =>
      Try {
        new KtPrefixUnaryExpression {
          parent = parentNode
          context = Some(ctx)
          PostfixUnaryExpressionContext.visit(ctx.postfixUnaryExpression)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object PrimaryConstructorContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtPrimaryConstructor]] {
    override def visitPrimaryConstructor(ctx: KotlinParser.PrimaryConstructorContext) = parentNode =>
      Try {
        new KtPrimaryConstructor {
          context = Some(ctx)
          parent = parentNode
          ctx.classParameters.classParameter.forEach(
            ClassParameterContext.visit(_)(Some(this)).map(children addOne _)
          )
        }
      }.toOption
  }

  object PrimaryExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtPrimaryExpression]] {
    override def visitPrimaryExpression(ctx: KotlinParser.PrimaryExpressionContext) = parentNode =>
      Try {
        new KtPrimaryExpression {
          parent = parentNode
          context = Some(ctx)
          StringLiteralContext.visit(ctx.stringLiteral)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object PropertyDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtProperty]] {
    override def visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) = parentNode =>
      Try {
        new KtProperty {
          parent = parentNode
          context = Some(ctx)
          SimpleIdentifierContext.visit(ctx.variableDeclaration.simpleIdentifier)(Some(this)).map { node =>
            children = children :+ node
          }
          ExpressionContext.visit(ctx.expression)(Some(this)).map { node =>
            children = children :+ node
          }
          TypeContext.visit(ctx.variableDeclaration.`type`)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object RangeExpressionContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtRangeExpression]] {
    override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext) = parentNode =>
      Try {
        new KtRangeExpression {
          parent = parentNode
          context = Some(ctx)
          ctx.additiveExpression.forEach(
            AdditiveExpressionContext
              .visit(_)(Some(this))
              .map(children addOne _)
          )
        }
      }.toOption
  }

  object StatementContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtStatement]] {
    override def visitStatement(ctx: KotlinParser.StatementContext) = parentNode =>
      Try {
        new KtStatement {
          parent = parentNode
          context = Some(ctx)
          ExpressionContext.visit(ctx.expression)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object StringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtStringLiteral]] {
    override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) = parentNode =>
      Try {
        new KtStringLiteral {
          parent = parentNode
          context = Some(ctx)
          LineStringLiteralContext.visit(ctx.lineStringLiteral)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }

  object TopLevelObjectContext extends KotlinParserBaseVisitor[Option[AST] => Option[KtTopLevelObject]] {
    override def visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) = parentNode =>
      Try {
        new KtTopLevelObject {
          parent = parentNode
          context = Some(ctx)
          DeclarationContext.visit(ctx.declaration)(Some(this)).map { node =>
            children = children :+ node
          }
        }
      }.toOption
  }
}
