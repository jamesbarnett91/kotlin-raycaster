import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class Minimap(private val map: Map) {
  private val scale = 20

  private val canvas = (document.createElement("canvas") as HTMLCanvasElement)
    .apply {
      width = map.width * scale
      height = map.height * scale
      id = "minimap"
    }
  private val context = canvas.getContext("2d") as CanvasRenderingContext2D

  init {
    document.body!!.appendChild(canvas)
  }

  private fun drawMap() {
    for (y in 0 until map.height) {
      for (x in 0 until map.width) {
        val wall = map.data[y][x]
        if (wall > 0) {
          context.fillStyle = "#000000"
          context.fillRect((x * scale).toDouble(), (y * scale).toDouble(), scale.toDouble(), scale.toDouble())
        }
      }
    }
  }

  fun update(camera: Camera) {
    context.clearRect(0.0, 0.0, (map.width * scale).toDouble(), (map.height * scale).toDouble())
    drawMap()
    context.fillStyle = "#FF0000"
    context.fillRect((camera.xPos * scale)-5, (camera.yPos * scale)-5, 10.0, 10.0)

    context.strokeStyle = "#00FF00"
    context.lineWidth = 2.0
    context.beginPath()
    context.moveTo(camera.xPos * scale, camera.yPos * scale)

    val cameraCos = kotlin.math.cos(toRadians(camera.rotation))
    val cameraSin = kotlin.math.sin(toRadians(camera.rotation))

    val dirX = camera.xPos + cameraCos
    val dirY = camera.yPos + cameraSin

    context.lineTo(dirX * scale, dirY * scale)
    context.stroke()
  }
}