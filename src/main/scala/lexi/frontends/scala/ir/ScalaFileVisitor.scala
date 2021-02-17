package lexi.frontends.scala.ir

import lexi.ir.nodes.{IrDeclaration, IrFile, IrFunction, IrFunctionBody, IrTopLevelObject}

import scala.meta._

object ScalaFileVisitor extends ScalaVisitor {
  override def visit(tree: Tree): IrFile = {
//    new IrFile(
//      name = "",
//      topLevelObjects = tree.topLevelObjects.map(_.map(KtTopLevelObjectVisitor.visit(_)))
//    )

    new IrFile(
      name = "",
      topLevelObjects = Some(
        Vector(
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
                    functionBody = Some(
                      IrFunctionBody(
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
    )
  }
}
