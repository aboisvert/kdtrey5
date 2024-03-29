package io.ginger.kdtrey5.dynamo

import java.util.BitSet
import io.ginger.kdtrey5.coordinates.BitsetCoordinateSystem
import io.ginger.kdtrey5.data._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers._

class DynamoStoreTest extends AnyFunSuite {

  val prefix = "kdtrey5"

  val leaf = KDLeaf(
    id = "root",
    keys = Array(BitSetUtils.bitSetFromString("1011")),
    values = Array("11")
  )

  test("create tables") {
    val store = new TestStore()
    store.createVersionsTableIfNecessary()
    store.createNewVersion("v1")
  }

  test("store a node and set rootId") {
    val store = new TestStore()
    store.store("root", leaf)
    store.rootId = "root"
  }

  test("get rootId and load node") {
    val store = new TestStore()
    store.rootId shouldBe "root"
    store.load("root") shouldBe leaf
  }
}
