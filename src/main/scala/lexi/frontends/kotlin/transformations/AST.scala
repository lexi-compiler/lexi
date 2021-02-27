package lexi.frontends.kotlin.transformations

import lexi.frontends.kotlin._
import lexi.frontends.kotlin.antlr.KotlinParser.FunctionDeclarationContext
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}
import lexi.frontends.kotlin.transformations.AST.{FunctionDeclarationContext, PropertyDeclarationContext}

import scala.jdk.CollectionConverters._
import scala.util.Try

object AST {
  object AdditiveExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtAdditiveExpression] {
    override def visitAdditiveExpression(ctx: KotlinParser.AdditiveExpressionContext) = parentNode =>
      new KtAdditiveExpression {
        parent = parentNode
        context = Some(ctx)
        children = ctx.multiplicativeExpression.asScala.toVector
          .map(MultiplicativeExpressionContext.visit(_)(Some(this)))
      }
  }

  object AsExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtAsExpression] {
    override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) = parentNode =>
      new KtAsExpression {
        parent = parentNode
        context = Some(ctx)
        children = children :+ PrefixUnaryExpressionContext.visit(ctx.prefixUnaryExpression)(Some(this))
      }
  }

  object ClassBodyContext extends KotlinParserBaseVisitor[Option[AST] => KtClassBody] {
    override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = parentNode =>
      new KtClassBody {
        context = Some(ctx)
        parent = parentNode
        val decls = ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
          .map(DeclarationContext.visit(_)(Some(this)))
        val funcs = ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
          .map(_.declaration.functionDeclaration)
          .map(FunctionDeclarationContext.visit(_)(Some(this)))
        if (decls.nonEmpty) children = children ++ decls
        if (funcs.nonEmpty) children = children ++ funcs
      }
  }

  object ClassDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtClass] {
    override def visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) = parentNode =>
      new KtClass {
        context = Some(ctx)
        parent = parentNode
        children = Vector(
          SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this)),
          PrimaryConstructorContext.visit(ctx.primaryConstructor)(Some(this)),
          ClassBodyContext.visit(ctx.classBody)(Some(this))
        )
      }
  }

  object SimpleIdentifierContext extends KotlinParserBaseVisitor[Option[AST] => KtSimpleIdentifier] {
    override def visitSimpleIdentifier(ctx: KotlinParser.SimpleIdentifierContext) = parentNode =>
      new KtSimpleIdentifier(name = ctx.getText) {
        context = Some(ctx)
        parent = parentNode
      }
  }

  object ClassParameterContext extends KotlinParserBaseVisitor[Option[AST] => KtClassParameter] {
    override def visitClassParameter(ctx: KotlinParser.ClassParameterContext) = parentNode =>
      new KtClassParameter {
        context = Some(ctx)
        parent = parentNode
        children = Vector(
          TypeContext.visit(ctx.`type`)(Some(this)),
          SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this))
        )
      }
  }

  object TypeContext extends KotlinParserBaseVisitor[Option[AST] => KtType] {
    override def visitType(ctx: KotlinParser.TypeContext) = parentNode =>
      new KtType(ctx.getText) {
        context = Some(ctx)
        parent = parentNode
      }
  }

  object ComparisonContext extends KotlinParserBaseVisitor[Option[AST] => KtComparison] {
    override def visitComparison(ctx: KotlinParser.ComparisonContext) = parentNode =>
      new KtComparison {
        parent = parentNode
        context = Some(ctx)
        children = ctx.genericCallLikeComparison.asScala.toVector
          .map(GenericCallLikeComparisonContext.visit(_)(Some(this)))
      }
  }

  object ConjunctionContext extends KotlinParserBaseVisitor[Option[AST] => KtConjunction] {
    override def visitConjunction(ctx: KotlinParser.ConjunctionContext) = parentNode =>
      new KtConjunction {
        parent = parentNode
        context = Some(ctx)
        children = ctx.equality.asScala.toVector
          .map(EqualityContext.visit(_)(Some(this)))
      }
  }

  object DeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtDeclaration] {
    override def visitDeclaration(ctx: KotlinParser.DeclarationContext) = parentNode =>
      new KtDeclaration {
        parent = parentNode
        context = Some(ctx)
        if (ctx.classDeclaration != null)
          children = children :+ ClassDeclarationContext.visit(ctx.classDeclaration)(Some(this))
        if (ctx.propertyDeclaration != null)
          children = children :+ PropertyDeclarationContext.visit(ctx.propertyDeclaration)(Some(this))
        if (ctx.functionDeclaration != null)
          children = children :+ FunctionDeclarationContext.visit(ctx.functionDeclaration)(Some(this))
      }
  }

  object DisjunctionContext extends KotlinParserBaseVisitor[Option[AST] => KtDisjunction] {
    override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) = parentNode =>
      new KtDisjunction {
        parent = parentNode
        context = Some(ctx)
        children = ctx.conjunction.asScala.toVector
          .map(ConjunctionContext.visit(_)(Some(this)))
      }
  }

  object ElvisExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtElvisExpression] {
    override def visitElvisExpression(ctx: KotlinParser.ElvisExpressionContext) = { parentNode =>
      new KtElvisExpression {
        parent = parentNode
        context = Some(ctx)
        infixFunctionCalls = ctx.infixFunctionCall.asScala.toVector
          .map(InfixFunctionCallContext.visit(_)(Some(this)))
      }
    }
  }

  object EqualityContext extends KotlinParserBaseVisitor[Option[AST] => KtEquality] {
    override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
      new KtEquality {
        parent = parentNode
        context = Some(ctx)
        comparison = ctx.comparison.asScala.toVector
          .map(ComparisonContext.visit(_)(Some(this)))
      }
  }

  object ExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtExpression] {
    override def visitExpression(ctx: KotlinParser.ExpressionContext) = parentNode =>
      new KtExpression {
        parent = parentNode
        context = Some(ctx)
        disjunction = Try(DisjunctionContext.visit(ctx.disjunction)(Some(this))).toOption
      }
  }

  object BlockExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtBlockExpression] {
    override def visitBlock(ctx: KotlinParser.BlockContext) = parentNode =>
      new KtBlockExpression {
        children = ctx.statements.statement.asScala.toVector
          .map(ExpressionContext.visit(_)(Some(this)))
      }
  }

  object FunctionDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtNamedFunction] {
    override def visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) = parentNode =>
      new KtNamedFunction {
        parent = parentNode
        context = Some(ctx)
        `type` = Try(ctx.`type`.getText).toOption
        children = Vector(
          Try(SimpleIdentifierContext.visit(ctx.simpleIdentifier)(Some(this))),
          Try(ExpressionContext.visit(ctx.functionBody.expression)(Some(this))),
          Try(BlockExpressionContext.visit(ctx.functionBody.block)(Some(this)))
        ).map(_.toOption).flatMap(_.filterNot(_ == None))
      }
  }

  object GenericCallLikeComparisonContext extends KotlinParserBaseVisitor[Option[AST] => KtGenericCallLikeComparison] {
    override def visitGenericCallLikeComparison(ctx: KotlinParser.GenericCallLikeComparisonContext) = parentNode =>
      new KtGenericCallLikeComparison {
        parent = parentNode
        context = Some(ctx)
        children = children :+ InfixOperationContext.visit(ctx.infixOperation)(Some(this))
      }
  }

  object InfixFunctionCallContext extends KotlinParserBaseVisitor[Option[AST] => KtInfixFunctionCall] {
    override def visitInfixFunctionCall(ctx: KotlinParser.InfixFunctionCallContext) = parentNode =>
      new KtInfixFunctionCall {
        parent = parentNode
        context = Some(ctx)
        children = ctx.rangeExpression.asScala.toVector
          .map(RangeExpressionContext.visit(_)(Some(this)))
      }
  }

  object InfixOperationContext extends KotlinParserBaseVisitor[Option[AST] => KtInfixOperation] {
    override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = parentNode =>
      new KtInfixOperation {
        parent = parentNode
        context = Some(ctx)
        children = ctx.elvisExpression.asScala.toVector
          .map(ElvisExpressionContext.visit(_)(Some(this)))
      }
  }

  object KotlinFileContext extends KotlinParserBaseVisitor[KtFile] {
    override def visitKotlinFile(ctx: KotlinParser.KotlinFileContext) =
      new KtFile {
        context = Some(ctx)
        children = ctx.topLevelObject.asScala.toVector
          .map(TopLevelObjectContext.visit(_)(Some(this)))
      }
  }

  object LineStringContentContext extends KotlinParserBaseVisitor[Option[AST] => KtLineStringContent] {
    override def visitLineStringContent(ctx: KotlinParser.LineStringContentContext) = parentNode =>
      new KtLineStringContent {
        parent = parentNode
        context = Some(ctx)
        lineStrText = Try(ctx.LineStrText.toString).toOption
      }
  }

  object LineStringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => KtLineStringLiteral] {
    override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
      new KtLineStringLiteral {
        parent = parentNode
        context = Some(ctx)
        children = ctx.lineStringContent.asScala.toVector
          .map(LineStringContentContext.visit(_)(Some(this)))
      }
  }

  object MultiplicativeExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtMultiplicativeExpression] {
    override def visitMultiplicativeExpression(ctx: KotlinParser.MultiplicativeExpressionContext) = parentNode =>
      new KtMultiplicativeExpression {
        parent = parentNode
        context = Some(ctx)
        children = ctx.asExpression.asScala.toVector
          .map(AsExpressionContext.visit(_)(Some(this)))
      }
  }

  object PostfixUnaryExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtPostfixUnaryExpression] {
    override def visitPostfixUnaryExpression(ctx: KotlinParser.PostfixUnaryExpressionContext) = parentNode =>
      new KtPostfixUnaryExpression {
        parent = parentNode
        context = Some(ctx)
      }
  }

  object PrefixUnaryExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtPrefixUnaryExpression] {
    override def visitPrefixUnaryExpression(
      ctx: KotlinParser.PrefixUnaryExpressionContext
    ) = { parentNode =>
      new KtPrefixUnaryExpression {
        parent = parentNode
        context = Some(ctx)
        children = children :+ PostfixUnaryExpressionContext.visit(ctx.postfixUnaryExpression)(Some(this))
      }
    }
  }

  object PrimaryConstructorContext extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryConstructor] {
    override def visitPrimaryConstructor(
      ctx: KotlinParser.PrimaryConstructorContext
    ) = { parentNode =>
      new KtPrimaryConstructor {
        context = Some(ctx)
        parent = parentNode
        children = ctx.classParameters.classParameter.asScala.toVector
          .map(ClassParameterContext.visit(_)(Some(this)))
      }
    }
  }

  object PrimaryExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryExpression] {
    override def visitPrimaryExpression(
      ctx: KotlinParser.PrimaryExpressionContext
    ) = { parentNode =>
      new KtPrimaryExpression {
        parent = parentNode
        context = Some(ctx)
        children = children :+ StringLiteralContext.visit(ctx.stringLiteral)(Some(this))
      }
    }
  }

  object PropertyDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtProperty] {
    override def visitPropertyDeclaration(ctx: KotlinParser.PropertyDeclarationContext) = { parentNode =>
      new KtProperty {
        parent = parentNode
        context = Some(ctx)
        children = Vector(
          Try(
            SimpleIdentifierContext.visit(ctx.variableDeclaration.simpleIdentifier)(Some(this))
          ).toOption,
          Try(ExpressionContext.visit(ctx.expression)(Some(this))).toOption,
          Try(TypeContext.visit(ctx.variableDeclaration.`type`)(Some(this))).toOption
        ).flatMap(_.filterNot(_ == None))
      }
    }
  }

  object RangeExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtRangeExpression] {
    override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext) =
      parentNode =>
        new KtRangeExpression {
          parent = parentNode
          context = Some(ctx)
          children = ctx.additiveExpression.asScala.toVector
            .map(AdditiveExpressionContext.visit(_)(Some(this)))
        }
  }

  object StatementContext extends KotlinParserBaseVisitor[Option[AST] => KtStatement] {
    override def visitStatement(ctx: KotlinParser.StatementContext) = { parentNode =>
      new KtStatement {
        parent = parentNode
        context = Some(ctx)
        children = children :+ ExpressionContext.visit(ctx.expression)(Some(this))
      }
    }
  }

  object StringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => KtStringLiteral] {
    override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
      parentNode =>
        new KtStringLiteral {
          parent = parentNode
          context = Some(ctx)
          children = children :+ LineStringLiteralContext.visit(ctx.lineStringLiteral)(Some(this))
        }
  }

  object TopLevelObjectContext extends KotlinParserBaseVisitor[Option[AST] => KtTopLevelObject] {
    override def visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) = { parentNode =>
      new KtTopLevelObject {
        parent = parentNode
        context = Some(ctx)
        children = children :+ DeclarationContext.visit(ctx.declaration)(Some(this))
      }
    }
  }
}
