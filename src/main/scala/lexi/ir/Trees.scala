package lexi.ir

import lexi.Tree

import scala.collection.mutable.ListBuffer

class IrTree extends Tree {
  var parent: Option[IrTree] = None
  var children: ListBuffer[IrTree] = ListBuffer.empty
}

case class IrFile(
  topLevelObjects: ListBuffer[IrTopLevelObject] = ListBuffer.empty
) extends IrTree

case class IrTopLevelObject(
  declaration: Option[IrDeclaration] = None
) extends IrTree

case class IrClass(
  name: Option[String] = None,
  classBody: Option[IrClassBody] = None
) extends IrTree

case class IrClassBody(
  declarations: ListBuffer[IrDeclaration] = ListBuffer.empty
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
  block: ListBuffer[String] = ListBuffer.empty,
  expression: ListBuffer[IrExpression] = ListBuffer.empty
) extends IrTree

case class IrStatement(
  var expression: Option[IrExpression] = None
) extends IrTree

case class IrExpression() extends IrTree

case class IrCall(
  var call: Option[IrFunction] = None
) extends IrTree
