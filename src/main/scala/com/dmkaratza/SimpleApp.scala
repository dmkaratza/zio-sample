package com.dmkaratza

import com.dmkaratza.model.Driver
import zio.*
import zio.stream.{ZSink, ZStream}

object SimpleApp extends ZIOAppDefault:

  private def countBy(stream: ZStream[Any, Throwable, Driver], f: Driver => String) =
    stream
      .groupByKey(f) { case (reportId, stream) =>
        ZStream.from(stream.map(f).runCount.map(count => reportId -> count))
      }

  def regroup(
      stream: ZStream[Any, Throwable, Driver]
  ): ZStream[Any, Throwable, (String, Long)] =
    countBy(stream, _.reportId).merge(countBy(stream, _.agency))

  override def run: ZIO[Any with ZIOAppArgs with Scope, Any, Any] =
    regroup(Driver.load("src/main/resources/drivers_data.csv")).run(ZSink.collectAll)
