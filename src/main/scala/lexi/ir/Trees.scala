package lexi.ir

import lexi.Tree

trait IrTree extends Tree {
  var parent: Option[IrTree] = None
  var children: List[IrTree] = List.empty
}

case class IrFile(
  name: String,
  topLevelObjects: Option[Vector[IrTopLevelObject]] = None
) extends IrTree

case class IrTopLevelObject(
  declaration: Option[IrDeclaration] = None
) extends IrTree

case class IrClass(
  name: Option[String] = None,
  classBody: Option[IrClassBody] = None
) extends IrTree

case class IrClassBody(
  declarations: Vector[IrDeclaration] = Vector.empty
) extends IrTree

case class IrDeclaration(
  classDeclaration: Option[IrClass] = None,
  propertyDeclaration: Option[IrProperty] = None,
  functionDeclaration: Option[IrFunction] = None
) extends IrTree

case class IrProperty(
  name: Option[String] = None,
  expression: Option[String] = None,
  dataType: Option[String] = None
) extends IrTree

case class IrFunction(
  name: Option[String] = None,
  `type`: Option[String] = None,
  bodyExpression: Option[IrExpression] = None,
  bodyBlockExpression: Option[IrBlockExpression] = None
) extends IrTree

case class IrBlockExpression(
  block: Option[Vector[String]] = None,
  expression: Vector[IrExpression] = Vector.empty
) extends IrTree

case class IrStatement(
  var expression: Option[IrExpression] = None
) extends IrTree

case class IrExpression() extends IrTree

case class IrCall(
  var call: Option[IrFunction] = None
) extends IrTree