package lexi.ir.phases

import lexi.{Context, Phase}
import lexi.ir.IrTree

class IrAnalysis extends Phase {
  def run(context: Context): Unit = {
    val proofs = new Proofs
    proofs.run(context)
  }
}
