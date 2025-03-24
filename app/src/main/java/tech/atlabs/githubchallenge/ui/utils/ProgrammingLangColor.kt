package tech.atlabs.githubchallenge.ui.utils

import androidx.compose.ui.graphics.Color
import tech.atlabs.githubchallenge.ui.theme.CColor
import tech.atlabs.githubchallenge.ui.theme.CppColor
import tech.atlabs.githubchallenge.ui.theme.CssColor
import tech.atlabs.githubchallenge.ui.theme.DartColor
import tech.atlabs.githubchallenge.ui.theme.ElixirColor
import tech.atlabs.githubchallenge.ui.theme.GoColor
import tech.atlabs.githubchallenge.ui.theme.HaskellColor
import tech.atlabs.githubchallenge.ui.theme.HtmlColor
import tech.atlabs.githubchallenge.ui.theme.JavaColor
import tech.atlabs.githubchallenge.ui.theme.JavaScriptColor
import tech.atlabs.githubchallenge.ui.theme.KotlinColor
import tech.atlabs.githubchallenge.ui.theme.ObjectiveCColor
import tech.atlabs.githubchallenge.ui.theme.PerlColor
import tech.atlabs.githubchallenge.ui.theme.PhpColor
import tech.atlabs.githubchallenge.ui.theme.PythonColor
import tech.atlabs.githubchallenge.ui.theme.RColor
import tech.atlabs.githubchallenge.ui.theme.RubyColor
import tech.atlabs.githubchallenge.ui.theme.RustColor
import tech.atlabs.githubchallenge.ui.theme.ScssColor
import tech.atlabs.githubchallenge.ui.theme.ShellColor
import tech.atlabs.githubchallenge.ui.theme.SwiftColor
import tech.atlabs.githubchallenge.ui.theme.TypeScriptColor

enum class ProgrammingLangColor(val displayName: String, val color: Color) {
    Shell("Shell", ShellColor),
    Python("Python", PythonColor),
    HTML("HTML", HtmlColor),
    JavaScript("JavaScript", JavaScriptColor),
    Java("Java", JavaColor),
    Kotlin("Kotlin", KotlinColor),
    Cpp("C++", CppColor),
    C("C", CColor),
    TypeScript("TypeScript", TypeScriptColor),
    Go("Go", GoColor),
    Ruby("Ruby", RubyColor),
    Swift("Swift", SwiftColor),
    PHP("PHP", PhpColor),
    SCSS("SCSS", ScssColor),
    CSS("CSS", CssColor),
    Dart("Dart", DartColor),
    Rust("Rust", RustColor),
    Elixir("Elixir", ElixirColor),
    Haskell("Haskell", HaskellColor),
    Perl("Perl", PerlColor),
    ObjectiveC("Objective-C", ObjectiveCColor),
    R("R", RColor);

    companion object {
        fun fromName(name: String): ProgrammingLangColor? =
            entries.firstOrNull { it.displayName.equals(name, ignoreCase = true) }
    }
}