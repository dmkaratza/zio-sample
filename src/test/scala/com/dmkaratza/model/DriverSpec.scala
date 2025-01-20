package com.dmkaratza.model

import zio.*
import zio.test.*
import zio.test.Assertion.*

object DriverSpec extends ZIOSpecDefault {

  override def spec: Spec[TestEnvironment with Scope, Any] =
    suite("Data Loading from file works")(
      test("Driver.load should load the driver's data successfully") {
        assertZIO(
          Driver.load("src/test/resources/drivers_data.csv").runCollect.map(_.toList)
        )(
          equalTo(
            List(
              Driver("DM8479000T", "Takoma Park Police Depart", "Property Damage Crash"),
              Driver("MCP2970000R", "MONTGOMERY", "Property Damage Crash")
            )
          )
        )
      }
    )
}
