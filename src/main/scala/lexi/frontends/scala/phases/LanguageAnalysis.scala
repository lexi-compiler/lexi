package lexi.frontends.scala.phases

import dotty.tools._
import dotty.tools.dotc.core.Contexts._
import dotty.tools.dotc.core._
import lexi.{Phase, Source}
import lexi.ir.nodes.IrNode

import java.io.File

object LanguageAnalysis extends Phase:
  def apply(source: Source): IrNode = {
    implicit var context: Context = initialCtx
    val compiler = new dotc.Compiler
    val run = compiler.newRun
    run.compileFromStrings(List(source.text))
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
    fc.setSetting(fc.settings.classpath, basicClasspath)
    fc.setSetting(fc.settings.YerasedTerms, true)
    //fc.setProperty(ContextDoc, new ContextDocstrings)
  }

  val basicClasspath = mkClasspath(List(
    "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-sbt/compiler-interface/1.3.5/compiler-interface-1.3.5.jar",
    "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.4/scala-library-2.13.4.jar",
    "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-asm/7.3.1-scala-1/scala-asm-7.3.1-scala-1.jar",
    "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jline/jline-terminal/3.15.0/jline-terminal-3.15.0.jar",
    "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jline/jline-reader/3.15.0/jline-reader-3.15.0.jar",
    "/Users/mattmoore/source/dotty/tests/semanticdb",
  ))

  def mkClasspath(classpaths: List[String]): String =
    classpaths
      .map({ p =>
        val file = new java.io.File(p)
        assert(file.exists, s"File $p couldn't be found.")
        file.getAbsolutePath
      })
      .mkString(File.pathSeparator)
