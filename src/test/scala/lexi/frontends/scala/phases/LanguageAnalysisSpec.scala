//package lexi.frontend.scala.phases
//
//import lexi.frontend.scala.phases.LanguageAnalysis
//import org.scalatest.funspec.AnyFunSpec
//import org.scalatest.matchers.should.Matchers
//
//class LanguageAnalysisSpec extends AnyFunSpec with Matchers:
//  describe("scala code") {
//    val source =
//      """
//        |object HelloScala {
//        |  def hello(): String = "Hello Scala"
//        |}
//        |""".stripMargin
//    val ir = LanguageAnalysis(source)
//
//    it("compiles") {
//      ir should not be null
//    }
//  }
