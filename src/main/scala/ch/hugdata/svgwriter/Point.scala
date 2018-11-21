package ch.hugdata.svgwriter

import ch.hugdata.svgwriter.geometry.Location2D

import scala.util.{Failure, Success, Try}

/**
  * Represents an element in a svg-file
  */
case class Point(private val location: Location2D,
                 private val properties: Seq[Property] = Seq.empty)
  extends SvgElement {

  private def renderSvg(properties: Seq[Property]) = {
    s"""<circle cx="${location.x}" cy="${location.y}" ${properties.map(_.toSvg).mkString(" ")} />"""
  }

  override def toSVG()(implicit limits: Dimensions): Try[String] = {
    if (limits.withinLimits(location.x, location.y)) {
      Success(renderSvg(properties))
    } else {
      Failure(new Exception(s"The point $location is outside of the drawing area."))
    }
  }
}
