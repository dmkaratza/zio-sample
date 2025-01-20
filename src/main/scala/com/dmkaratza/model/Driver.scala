package com.dmkaratza.model

import zio.stream.{ZPipeline, ZStream}

import java.io.File
import zio.*

final case class Driver(reportId: String, agency: String, reportType: String)

object Driver:
  def load(path: String): ZStream[Any, Throwable, Driver] =
    ZStream
      .fromFile(new File(path))
      .via(ZPipeline.utf8Decode)
      .via(ZPipeline.splitLines)
      .via(ZPipeline.map[String, Driver] { line =>
        val arr = line.split(",")
        Driver(arr(0), arr(1), arr(2))
      })
