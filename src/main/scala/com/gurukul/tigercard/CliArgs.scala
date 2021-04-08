package com.gurukul.tigercard

import scopt.OParser

case class CliArgs (commutesPath: String = "", outputPath: String = "")

object CliArgs {
  val parser: String => OParser[Unit, CliArgs] = {
    appName: String =>
      val builder = OParser.builder[CliArgs]
      import builder._

      OParser.sequence(
        programName(appName),
        head(appName),

        opt[String]('c', name = "commutes-file")
          .required()
          .action((path, args) => args.copy(commutesPath = path))
          .text("Path to Commutes/Trips file"),

        opt[String]('p', name = "output-file")
          .required()
          .action((path, args) => args.copy(outputPath = path))
          .text("Path to output Fare file")
      )
  }
}