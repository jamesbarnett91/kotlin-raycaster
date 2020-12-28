import kotlinx.browser.document
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement

class Minimap(private val map: List<List<Int>>) {

  private val scale = 30
  private val mapWidth = map[0].size
  private val mapHeight = map.size

  private val canvas = (document.createElement("canvas") as HTMLCanvasElement)
    .apply {
      width = mapWidth * scale
      height = mapHeight * scale
      id = "minimap"
      style.width = "${width}px"
      style.height = "${height}px"
    }
  private val context = canvas.getContext("2d") as CanvasRenderingContext2D

  init {
    document.body!!.appendChild(canvas)
  }

  private fun drawMap() {
    for (y in 0 until mapHeight) {
      for (x in 0 until mapWidth) {
        val wall = map[y][x]
        if (wall > 0) {
          context.fillStyle = "#000000"
          context.fillRect((x * scale).toDouble(), (y * scale).toDouble(), scale.toDouble(), scale.toDouble())
        }
      }
    }
  }

  fun update(camera: Camera) {
    context.clearRect(0.0, 0.0, (mapWidth * scale).toDouble(), (mapHeight * scale).toDouble())
    drawMap()
    context.fillStyle = "#FF0000"
    context.fillRect(camera.xPos * scale, camera.yPos * scale, scale.toDouble(), scale.toDouble())

    context.strokeStyle = "#00FF00"
    context.lineWidth = 2.0
    context.beginPath()
    context.moveTo(camera.xPos * scale, camera.yPos * scale)

    val cameraCos = kotlin.math.cos(toRadians(camera.rotation)) * 4
    val cameraSin = kotlin.math.sin(toRadians(camera.rotation)) * 4

    val dirX = camera.xPos + cameraCos
    val dirY = camera.yPos + cameraSin

    context.lineTo(dirX * scale, dirY * scale)
    context.stroke()
  }
}