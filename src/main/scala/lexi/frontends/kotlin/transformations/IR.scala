package lexi.frontends.kotlin.transformations

import lexi.frontends.kotlin._
import lexi.ir._

object IR {
  def irClassBody(ast: KtClassBody): IrClassBody = {
    new IrClassBody(
      declarations = ast.declarations.map(irDeclaration(_))
    )
  }

  def irClass(ast: KtClass): IrClass = {
    new IrClass(
      name = ast.name,
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
      name = ast.name.get,
      topLevelObjects = ast.topLevelObjects.map(_.map(irTopLevelObject(_)))
    )
  }

  def irBlockExpression(ast: KtBlockExpression): IrBlockExpression = {
    IrBlockExpression(
      block = None,
      expression = ast.statements.map(irExpression(_))
    )
  }

  def irFunction(ast: KtNamedFunction): IrFunction = {
    IrFunction(
      name = ast.name,
      `type` = ast.`type`,
      bodyExpression = ast.bodyExpression.map(irExpression(_)),
      bodyBlockExpression = ast.bodyBlockExpression.map(irBlockExpression(_))
    )
  }

  def irProperty(tree: KtProperty): IrProperty = {
    IrProperty(
      name = tree.name,
      expression = tree.expression,
      dataType = tree.dataType
    )
  }

  def irTopLevelObject(ast: KtTopLevelObject): IrTopLevelObject = {
    IrTopLevelObject(
      declaration = ast.declaration.map(irDeclaration(_))
    )
  }
}