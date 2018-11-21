package ch.hugdata.svgwriter

import ch.hugdata.svgwriter.geometry.Location2D
import org.scalatest.FlatSpec

/**
  * Specification for the [[Point]] type
  */
class PointSpec extends FlatSpec {

  "A point" should "serialize to a svg element" in {
    val point2D = Location2D(200, 200)
    implicit val limits: Dimensions = Dimensions(0, 400, 0, 400)
    val properties = Seq(Property.size(2))
    val svgPoint = Point(point2D, properties)
    val svgString = svgPoint.toSVG()
    assert(svgString.isSuccess === true)
    assert(svgString.getOrElse("") === """<circle cx="200.0" cy="200.0" r="2" />""")
  }

  it should "not serialize if the point is outside the graphic's limits" in {
    val point2D = Location2D(600, 200)
    implicit val limits: Dimensions = Dimensions(0, 400, 0, 400)
    val svgPoint = Point(point2D)
    assert(svgPoint.toSVG().isSuccess === false)
  }

}
