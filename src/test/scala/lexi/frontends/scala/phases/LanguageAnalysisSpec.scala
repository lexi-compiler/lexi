package lexi.frontends.scala.phases

import lexi.{Language, Source}
import lexi.frontends.scala.phases.Frontend

class LanguageAnalysisSpec extends munit.FunSuite {
  test("scala code") {
    val code =
      """
        |object HelloScala {
        |  def hello(name: String): String = "Hello ${name}"
        |}
        |""".stripMargin
    val source = Source.fromString(code, Language.Scala)
    val ir = Frontend(source)
  }
}
