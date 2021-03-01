package lexi.frontends.kotlin.transformations

import lexi.frontends.kotlin._
import lexi.ir._

import scala.collection.mutable.ListBuffer

object IR {
  def irClassBody(ast: KtClassBody): IrClassBody = {
    new IrClassBody(
      declarations = ast.declarations.map(irDeclaration(_))
    )
  }

  def irClass(ast: KtClass): IrClass = {
    new IrClass(
      name = ast.simpleIdentifier.map(_.name),
      classBody = ast.classBody.map(irClassBody(_))
    )
  }

  def irDeclaration(ast: KtDeclaration): IrDeclaration = {
    new IrDeclaration(
      classDeclaration = ast.classDeclaration.map(irClass(_)),
      propertyDeclaration = ast.propertyDeclaration.map(irProperty(_)),
      functionDeclaration = ast.functionDeclaration.map(irFunction(_))
    )
  }

  def irExpression(ast: KtExpression): IrExpression = {
    IrExpression()
  }

  def irFile(ast: KtFile): IrFile = {
    new IrFile(
      topLevelObjects = ast.topLevelObjects.map(irTopLevelObject(_))
    )
  }

  def irBlockExpression(ast: KtBlockExpression): IrBlockExpression = {
    IrBlockExpression(
      block = ListBuffer.empty,
      expression = ast.statements.map(irExpression(_))
    )
  }

  def irFunction(ast: KtNamedFunction): IrFunction = {
    IrFunction(
      name = ast.name.map(_.name),
      `type` = ast.`type`.map(_.name),
      bodyExpression = ast.bodyExpression.map(irExpression(_)),
      bodyBlockExpression = ast.bodyBlockExpression.map(irBlockExpression(_))
    )
  }

  def irProperty(tree: KtProperty): IrProperty = {
    IrProperty(
      name = tree.name.map(_.name),
      expression = Option(tree.expression.toString),
      dataType = tree.`type`.map(_.name)
    )
  }

  def irTopLevelObject(ast: KtTopLevelObject): IrTopLevelObject = {
    IrTopLevelObject(
      declaration = ast.declaration.map(irDeclaration(_))
    )
  }
}
