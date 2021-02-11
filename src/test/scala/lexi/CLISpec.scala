package lexi

class CLISpec extends munit.FunSuite {
  test("parses kotlin language parameter") {
    val args = Array("-lang", "kotlin")
    val opts = CompilerOptions.fromCLI(args)
    val expected = new CompilerOptions(language = Some(Language.Kotlin))
    assertEquals(opts, expected)
  }

  test("parses scala language parameter") {
    val args = Array("-lang", "scala")
    val opts = CompilerOptions.fromCLI(args)
    val expected = new CompilerOptions(language = Some(Language.Scala))
    assertEquals(opts, expected)
  }
}
