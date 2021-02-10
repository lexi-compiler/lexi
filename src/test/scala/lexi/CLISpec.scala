package lexi

class CLISpec extends munit.FunSuite {
  test("parses kotlin language parameter") {
    val args = Array("-language", "kotlin")
    val opts = CompilerOptions.fromCLI(args)
    val expected = new CompilerOptions(language = Some(Language.Kotlin))
    assertEquals(opts, expected)
  }
}
