package lexi.frontends.scala.phases

import lexi.KotlinTestUtils.TestCompiler
import lexi.frontends.scala.phases.LanguageAnalysis
import lexi.{Language, Source}

class LanguageAnalysisSpec extends munit.FunSuite {
  test("scala code") {
    val code = """
      object HelloScala {
        def hello(name: String): String = "Hello ${name}"
      }
      """.stripMargin
//    val ir = TestCompiler.ir(code)
  }
}
