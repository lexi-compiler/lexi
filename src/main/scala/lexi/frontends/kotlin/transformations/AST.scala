package lexi.frontends.kotlin.transformations

import lexi.frontends.kotlin._
import lexi.frontends.kotlin.antlr.KotlinParser.FunctionDeclarationContext
import lexi.frontends.kotlin.antlr.{KotlinParser, KotlinParserBaseVisitor}

import scala.jdk.CollectionConverters._
import scala.util.Try

object AST {
  object AdditiveExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => KtAdditiveExpression] {
    override def visitAdditiveExpression(ctx: KotlinParser.AdditiveExpressionContext) =
      parentNode =>
        new KtAdditiveExpression {
          parent = parentNode
          context = Some(ctx)
          multiplicativeExpression = Try(
            ctx.multiplicativeExpression.asScala.toVector
              .map(MultiplicativeExpressionContext.visit(_)(Some(this)))
          ).toOption
        }
  }

  object AsExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtAsExpression] {
    override def visitAsExpression(ctx: KotlinParser.AsExpressionContext) =
      parentNode =>
        new KtAsExpression {
          parent = parentNode
          context = Some(ctx)
          prefixUnaryExpression = Try(
            PrefixUnaryExpressionContext.visit(ctx.prefixUnaryExpression)(Some(this))
          ).toOption
        }
  }

  object ClassBodyContext extends KotlinParserBaseVisitor[Option[AST] => KtClassBody] {
    override def visitClassBody(ctx: KotlinParser.ClassBodyContext) = { parentNode =>
      new KtClassBody {
        context = Some(ctx)
        parent = parentNode
        declarations = ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
          .map(DeclarationContext.visit(_)(Some(this)))
        functions = ctx.classMemberDeclarations.classMemberDeclaration.asScala.toVector
          .map(_.declaration.functionDeclaration)
          .map(FunctionDeclarationContext.visit(_)(Some(this)))
      }
    }
  }

  object ClassDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtClass] {
    override def visitClassDeclaration(ctx: KotlinParser.ClassDeclarationContext) = { parentNode =>
      new KtClass {
        context = Some(ctx)
        parent = parentNode
        name = Some(ctx.simpleIdentifier.getText)
        primaryConstructor = Try(
          PrimaryConstructorContext.visit(ctx.primaryConstructor)(Some(this))
        ).toOption
        classBody = Try(ClassBodyContext.visit(ctx.classBody)(Some(this))).toOption
      }
    }
  }

  object ClassParameterContext extends KotlinParserBaseVisitor[Option[AST] => KtClassParameter] {
    override def visitClassParameter(ctx: KotlinParser.ClassParameterContext) = { parentNode =>
      new KtClassParameter {
        context = Some(ctx)
        parent = parentNode
        name = Some(ctx.simpleIdentifier.getText)
        `type` = Some(ctx.`type`.getText)
      }
    }
  }

  object ComparisonContext extends KotlinParserBaseVisitor[Option[AST] => KtComparison] {
    override def visitComparison(ctx: KotlinParser.ComparisonContext) =
      parentNode =>
        new KtComparison {
          parent = parentNode
          context = Some(ctx)
          genericCallLikeComparisonContext = Try(
            ctx.genericCallLikeComparison.asScala.toVector
              .map(GenericCallLikeComparisonContext.visit(_)(Some(this)))
          ).toOption
        }
  }

  object ConjunctionContext extends KotlinParserBaseVisitor[Option[AST] => KtConjunction] {
    override def visitConjunction(ctx: KotlinParser.ConjunctionContext) =
      parentNode =>
        new KtConjunction {
          parent = parentNode
          context = Some(ctx)
          equalities = Try(
            ctx.equality.asScala.toVector
              .map(EqualityContext.visit(_)(Some(this)))
          ).toOption
        }
  }

  object DeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtDeclaration] {
    override def visitDeclaration(ctx: KotlinParser.DeclarationContext) = { parentNode =>
      new KtDeclaration {
        parent = parentNode
        context = Some(ctx)
        classDeclaration = Try(
          ClassDeclarationContext.visit(ctx.classDeclaration)(Some(this))
        ).toOption
        propertyDeclaration = Try(
          PropertyDeclarationContext.visit(ctx.propertyDeclaration)(Some(this))
        ).toOption
        functionDeclaration = Try(
          FunctionDeclarationContext.visit(ctx.functionDeclaration)(Some(this))
        ).toOption
      }
    }
  }

  object DisjunctionContext extends KotlinParserBaseVisitor[Option[AST] => KtDisjunction] {
    override def visitDisjunction(ctx: KotlinParser.DisjunctionContext) =
      parentNode =>
        new KtDisjunction {
          parent = parentNode
          context = Some(ctx)
          conjunctions = Try(
            ctx.conjunction.asScala.toVector
              .map(ConjunctionContext.visit(_)(Some(this)))
          ).toOption
        }
  }

  object ElvisExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtElvisExpression] {
    override def visitElvisExpression(ctx: KotlinParser.ElvisExpressionContext) = { parentNode =>
      new KtElvisExpression {
        parent = parentNode
        context = Some(ctx)
        infixFunctionCalls = Try(
          ctx.infixFunctionCall.asScala.toVector
            .map(InfixFunctionCallContext.visit(_)(Some(this)))
        ).toOption
      }
    }
  }

  object EqualityContext extends KotlinParserBaseVisitor[Option[AST] => KtEquality] {
    override def visitEquality(ctx: KotlinParser.EqualityContext) = parentNode =>
      new KtEquality {
        parent = parentNode
        context = Some(ctx)
        comparison = Try(
          ctx.comparison.asScala.toVector
            .map(ComparisonContext.visit(_)(Some(this)))
        ).toOption
      }
  }

  object ExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtExpression] {
    override def visitExpression(ctx: KotlinParser.ExpressionContext) =
      parentNode =>
        new KtExpression {
          parent = parentNode
          context = Some(ctx)
          disjunction = Try(DisjunctionContext.visit(ctx.disjunction)(Some(this))).toOption
        }
  }

  object BlockExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtBlockExpression] {
    override def visitBlock(ctx: KotlinParser.BlockContext) = { parentNode =>
      new KtBlockExpression {
        statements = ctx.statements.statement.asScala.toVector
          .map(ExpressionContext.visit(_)(Some(this)))
      }
    }
  }

  object FunctionDeclarationContext
    extends KotlinParserBaseVisitor[Option[AST] => KtNamedFunction] {
    override def visitFunctionDeclaration(ctx: KotlinParser.FunctionDeclarationContext) = {
      parentNode =>
        new KtNamedFunction {
          parent = parentNode
          context = Some(ctx)
          name = Try(ctx.simpleIdentifier.getText).toOption
          `type` = Try(ctx.`type`.getText).toOption
          bodyExpression = Try(
            ExpressionContext.visit(ctx.functionBody.expression)(Some(this))
          ).toOption
          bodyBlockExpression = Try(
            BlockExpressionContext.visit(ctx.functionBody.block)(Some(this))
          ).toOption
        }
    }
  }

  object GenericCallLikeComparisonContext
    extends KotlinParserBaseVisitor[Option[AST] => KtGenericCallLikeComparison] {
    override def visitGenericCallLikeComparison(
      ctx: KotlinParser.GenericCallLikeComparisonContext
    ) = { parentNode =>
      new KtGenericCallLikeComparison {
        parent = parentNode
        context = Some(ctx)
        infixOperation = Try(
          InfixOperationContext.visit(ctx.infixOperation)(Some(this))
        ).toOption
      }
    }
  }

  object InfixFunctionCallContext
    extends KotlinParserBaseVisitor[Option[AST] => KtInfixFunctionCall] {
    override def visitInfixFunctionCall(
      ctx: KotlinParser.InfixFunctionCallContext
    ) = { parentNode =>
      new KtInfixFunctionCall {
        parent = parentNode
        context = Some(ctx)
        rangeExpressions = Try(
          ctx.rangeExpression.asScala.toVector
            .map(RangeExpressionContext.visit(_)(Some(this)))
        ).toOption
      }
    }
  }

  object InfixOperationContext extends KotlinParserBaseVisitor[Option[AST] => KtInfixOperation] {
    override def visitInfixOperation(ctx: KotlinParser.InfixOperationContext) = { parentNode =>
      new KtInfixOperation {
        parent = parentNode
        context = Some(ctx)
        elvisExpression = Try(
          ctx.elvisExpression.asScala.toVector
            .map(ElvisExpressionContext.visit(_)(Some(this)))
        ).toOption
      }
    }
  }

  object KotlinFileContext extends KotlinParserBaseVisitor[KtFile] {
    override def visitKotlinFile(ctx: KotlinParser.KotlinFileContext) =
      new KtFile {
        context = Some(ctx)
        topLevelObjects = Try(
          ctx.topLevelObject.asScala.toVector
            .map(TopLevelObjectContext.visit(_)(Some(this)))
        ).toOption
      }
  }

  object LineStringContentContext
    extends KotlinParserBaseVisitor[Option[AST] => KtLineStringContent] {
    override def visitLineStringContent(ctx: KotlinParser.LineStringContentContext) = parentNode =>
      new KtLineStringContent {
        parent = parentNode
        context = Some(ctx)
        lineStrText = Try(ctx.LineStrText.toString).toOption
      }
  }

  object LineStringLiteralContext
    extends KotlinParserBaseVisitor[Option[AST] => KtLineStringLiteral] {
    override def visitLineStringLiteral(ctx: KotlinParser.LineStringLiteralContext) = parentNode =>
      new KtLineStringLiteral {
        parent = parentNode
        context = Some(ctx)
        lineStringContent = Try(
          ctx.lineStringContent.asScala.toVector
            .map(LineStringContentContext.visit(_)(Some(this)))
        ).toOption
      }
  }

  object MultiplicativeExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => KtMultiplicativeExpression] {
    override def visitMultiplicativeExpression(
      ctx: KotlinParser.MultiplicativeExpressionContext
    ) = parentNode =>
      new KtMultiplicativeExpression {
        parent = parentNode
        context = Some(ctx)
        asExpression = Try(
          ctx.asExpression.asScala.toVector
            .map(AsExpressionContext.visit(_)(Some(this)))
        ).toOption
      }
  }

  object PostfixUnaryExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => KtPostfixUnaryExpression] {
    override def visitPostfixUnaryExpression(
      ctx: KotlinParser.PostfixUnaryExpressionContext
    ) = { parentNode =>
      new KtPostfixUnaryExpression {
        parent = parentNode
        context = Some(ctx)
      }
    }
  }

  object PrefixUnaryExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => KtPrefixUnaryExpression] {
    override def visitPrefixUnaryExpression(
      ctx: KotlinParser.PrefixUnaryExpressionContext
    ) = { parentNode =>
      new KtPrefixUnaryExpression {
        parent = parentNode
        context = Some(ctx)
        postfixUnaryExpression = Try(
          PostfixUnaryExpressionContext.visit(ctx.postfixUnaryExpression)(Some(this))
        ).toOption
      }
    }
  }

  object PrimaryConstructorContext
    extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryConstructor] {
    override def visitPrimaryConstructor(
      ctx: KotlinParser.PrimaryConstructorContext
    ) = { parentNode =>
      new KtPrimaryConstructor {
        context = Some(ctx)
        parent = parentNode
        classParameters = Try {
          ctx.classParameters.classParameter.asScala.toVector
            .map(ClassParameterContext.visit(_)(Some(this)))
        }.toOption
      }
    }
  }

  object PrimaryExpressionContext
    extends KotlinParserBaseVisitor[Option[AST] => KtPrimaryExpression] {
    override def visitPrimaryExpression(
      ctx: KotlinParser.PrimaryExpressionContext
    ) = { parentNode =>
      new KtPrimaryExpression {
        parent = parentNode
        context = Some(ctx)
        stringLiteral = Try(
          StringLiteralContext.visit(ctx.stringLiteral)(Some(this))
        ).toOption
      }
    }
  }

  object PropertyDeclarationContext extends KotlinParserBaseVisitor[Option[AST] => KtProperty] {
    override def visitPropertyDeclaration(
      ctx: KotlinParser.PropertyDeclarationContext
    ) = { parentNode =>
      new KtProperty {
        parent = parentNode
        context = Some(ctx)
        name = Try(ctx.variableDeclaration.simpleIdentifier.getText).toOption
        expression = Try(ctx.expression.getText).toOption
        dataType = Try(ctx.variableDeclaration.`type`.getText).toOption
      }
    }
  }

  object RangeExpressionContext extends KotlinParserBaseVisitor[Option[AST] => KtRangeExpression] {
    override def visitRangeExpression(ctx: KotlinParser.RangeExpressionContext) =
      parentNode =>
        new KtRangeExpression {
          parent = parentNode
          context = Some(ctx)
          additiveExpressions = Try(
            ctx.additiveExpression.asScala.toVector
              .map(AdditiveExpressionContext.visit(_)(Some(this)))
          ).toOption
        }
  }

  object StatementContext extends KotlinParserBaseVisitor[Option[AST] => KtStatement] {
    override def visitStatement(ctx: KotlinParser.StatementContext) = { parentNode =>
      new KtStatement {
        parent = parentNode
        context = Some(ctx)
        expression = Try(
          ExpressionContext.visit(ctx.expression)(Some(this))
        ).toOption
      }
    }
  }

  object StringLiteralContext extends KotlinParserBaseVisitor[Option[AST] => KtStringLiteral] {
    override def visitStringLiteral(ctx: KotlinParser.StringLiteralContext) =
      parentNode =>
        new KtStringLiteral {
          parent = parentNode
          context = Some(ctx)
          lineStringLiteral = Try(
            LineStringLiteralContext.visit(ctx.lineStringLiteral)(Some(this))
          ).toOption
        }
  }

  object TopLevelObjectContext extends KotlinParserBaseVisitor[Option[AST] => KtTopLevelObject] {
    override def visitTopLevelObject(ctx: KotlinParser.TopLevelObjectContext) = { parentNode =>
      new KtTopLevelObject {
        parent = parentNode
        context = Some(ctx)
        declaration = Try(
          DeclarationContext.visit(ctx.declaration)(Some(this))
        ).toOption
      }
    }
  }
}
