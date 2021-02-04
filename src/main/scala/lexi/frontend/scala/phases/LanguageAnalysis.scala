package lexi.frontend.scala.phases

import dotty.tools._
import dotty.tools.dotc.core.Contexts._
import dotty.tools.dotc.core._
import lexi.ir.nodes.IrNode

import java.io.File

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
    fc.setSetting(fc.settings.classpath, basicClasspath)
    fc.setSetting(fc.settings.YerasedTerms, true)
    //fc.setProperty(ContextDoc, new ContextDocstrings)
  }

  val basicClasspath = mkClasspath(
    List(
      //sys.props("dotty.tests.classes.scalaLibrary"),
      "/Users/mattmoore/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.4/scala-library-2.13.4.jar",
      "/Users/mattmoore/source/dotty/library/../out/bootstrap/scala3-library-bootstrapped/scala-3.0.0-RC1/scala3-library_3.0.0-RC1-3.0.0-RC1-bin-SNAPSHOT.jar"
    )
  )

  def mkClasspath(classpaths: List[String]): String =
    classpaths
      .map({ p =>
        val file = new java.io.File(p)
        assert(file.exists, s"File $p couldn't be found.")
        file.getAbsolutePath
      })
      .mkString(File.pathSeparator)
