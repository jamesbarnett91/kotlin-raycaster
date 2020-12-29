import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class Renderer(val viewportWidth: Int, val viewportHeight: Int, private val outputScale: Int) {

  private val canvas = (document.createElement("canvas") as HTMLCanvasElement)
    .apply {
      width = viewportWidth * outputScale
      height = viewportHeight * outputScale
    }

  private val context = canvas.getContext("2d") as CanvasRenderingContext2D

  init {
    context.scale(outputScale.toDouble(), outputScale.toDouble())
    document.body!!.appendChild(canvas)
  }

  fun drawLine(startX: Double, startY: Double, endX: Double, endY: Double, cssColour: String = "#FF0000") {
    context.strokeStyle = cssColour
    context.lineWidth = 2.0
    context.beginPath()
    context.moveTo(startX, startY)
    context.lineTo(endX, endY)
    context.stroke()
  }

  fun drawLine(startX: Int, startY: Double, endX: Int, endY: Double, cssColour: String = "#FF0000") {
    drawLine(startX.toDouble(), startY, endX.toDouble(), endY, cssColour)
  }

  fun drawLine(startX: Int, startY: Double, endX: Int, endY: Int, cssColour: String = "#FF0000") {
    drawLine(startX.toDouble(), startY, endX.toDouble(), endY.toDouble(), cssColour)
  }

  fun clear() {
    context.clearRect(0.0, 0.0, viewportWidth.toDouble(), viewportHeight.toDouble())
  }

}