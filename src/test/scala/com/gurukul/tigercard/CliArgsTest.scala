package com.gurukul.tigercard

import org.scalatest.{Matchers, WordSpec, OptionValues}
import scopt.OParser

class CliArgsTest extends WordSpec with Matchers with OptionValues {
  "CliArgs test" should {
    "perform test to validate both optional and positional input Cli Args" in {
      val args = Seq(
        "--commutes-file", "bucket/key/input-commutes.csv",
        "--output-file", "bucket/key/output-fare.csv"
      )

      val result = OParser.parse(CliArgs.parser("args"), args, CliArgs())
      result.value.commutesPath shouldEqual "bucket/key/input-commutes.csv"
      result.value.outputPath shouldEqual "bucket/key/output-fare.csv"
    }
  }
}
