package lexi.frontends.scala.ir

import lexi.ir.{IrBlockExpression, IrDeclaration, IrFile, IrFunction, IrTopLevelObject}

import scala.meta._

object ScalaFileVisitor extends ScalaVisitor {
  override def visit(tree: Tree): IrFile = {
//    new IrFile(
//      name = "",
//      topLevelObjects = tree.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
//    )

    new IrFile(
      topLevelObjects = Vector(
        IrTopLevelObject(
          declaration = Some(
            IrDeclaration(
              functionDeclaration = Some(
                IrFunction(
                  name = Some(
                    tree
                      .children(0)
                      .asInstanceOf[Defn.Object]
                      .children(1)
                      .asInstanceOf[Template]
                      .children(1)
                      .asInstanceOf[Defn.Def]
                      .name
                      .value
                  ),
                  bodyBlockExpression = Some(
                    IrBlockExpression(
//                        block = Some(
//                          tree
//                            .children(0)
//                            .asInstanceOf[Defn.Object]
//                            .children(1)
//                            .asInstanceOf[Template]
//                            .children(1)
//                            .asInstanceOf[Defn.Def]
//                            .body
//                            .value
//                        )
                    )
                  )
                )
              )
            )
          )
        )
      )
    )
  }
}
