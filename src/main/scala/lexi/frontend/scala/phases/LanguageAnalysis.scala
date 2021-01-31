package lexi.frontend.scala.phases

import lexi.ir.nodes.IrNode
import dotty.tools._
import dotty.tools.dotc.core._
import dotty.tools.dotc.core.Contexts._

object LanguageAnalysis:
  def apply(source: String): IrNode = {
    implicit var context: Context = initialCtx
    val compiler = new dotc.Compiler
    val run = compiler.newRun
    run.compileFromStrings(List(source))
    new IrNode {}
  }

  protected def initialCtx: FreshContext = {
    val base = new ContextBase {}
    import base.settings._
    val ctx = base.initialCtx.fresh
    initializeCtx(ctx)
    // when classpath is changed in ctx, we need to re-initialize to get the
    // correct classpath from PathResolver
    base.initialize()(using ctx)
    ctx
  }

  protected def initializeCtx(fc: FreshContext): Unit = {
    fc.setSetting(fc.settings.encoding, "UTF8")
//    fc.setSetting(fc.settings.classpath, TestConfiguration.basicClasspath)
    fc.setSetting(fc.settings.YerasedTerms, true)
//    fc.setProperty(ContextDoc, new ContextDocstrings)
  }
