package com.dmkaratza

import com.dmkaratza.model.Driver
import zio.*
import zio.stream.ZStream
import zio.test.Assertion.*
import zio.test.*

object SimpleAppSpec extends ZIOSpecDefault {
  override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("SimpleApp.regroup")(
      test("should produce an empty stream for an empty input") {
        assertZIO(
          SimpleApp
            .regroup(ZStream.empty)
            .runCollect
            .map(_.toList)
        )(equalTo(List.empty))
      },
      test("should group by `reportId` and `agencyName`") {
        assertZIO(
          SimpleApp
            .regroup(
              ZStream(
                Driver("report1", "agencyA", ""),
                Driver("report1", "agencyB", ""),
                Driver("report1", "agencyC", ""),
                Driver("report2", "agencyC", ""),
                Driver("report3", "agencyC", "")
              )
            )
            .runCollect
            .map(_.toList)
        )(
          hasSameElements(
            List(
              ("report1", 3L),
              ("agencyB", 1L),
              ("report2", 1L),
              ("agencyC", 3L),
              ("report3", 1L),
              ("agencyA", 1L)
            )
          )
        )
      }
    )
}
