import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class Renderer(val viewportWidth: Int, val viewportHeight: Int) {

  private val canvas = (document.createElement("canvas") as HTMLCanvasElement)
    .apply {
      width = viewportWidth
      height = viewportHeight
    }

  private val context = canvas.getContext("2d") as CanvasRenderingContext2D

  init {
    document.body!!.appendChild(canvas)
  }

  fun drawLine(startX: Int, startY: Int, endX: Int, endY: Int, cssColour: String = "#FF0000") {
    context.strokeStyle = cssColour
    context.lineWidth = 4.0
    context.beginPath()
    context.moveTo(startX.toDouble(), startY.toDouble())
    context.lineTo(endX.toDouble(), endY.toDouble())
    context.stroke()
  }

  fun clear() {
    context.clearRect(0.0, 0.0, viewportWidth.toDouble(), viewportHeight.toDouble())
  }

}