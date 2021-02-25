package lexi.frontends.kotlin.phases

import lexi.frontends.kotlin._
import lexi.frontends.kotlin.transformations.IR.{irClass, irFile, irFunction, irProperty}
import lexi.{CompilationUnit, Context, Phase}

class Ir extends Phase {
  def run(context: Context): Unit = {
    context.compilationUnits.foreach { unit =>
      unit.ir = unit.ast.map {
        case node: KtFile          => irFile(node)
        case node: KtClass         => irClass(node)
        case node: KtNamedFunction => irFunction(node)
        case node: KtProperty      => irProperty(node)
      }
    }
  }
}
