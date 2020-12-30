import kotlin.js.Date
import kotlin.math.pow

data class RaycastOptions(
  var fixFisheye: Boolean,
  var stepPrecision: Int
)

class Raycaster {

  fun raycast(raycastContext: RaycastContext) {
    val raycastStartMs = Date().getTime()
    val (options, renderer, textureManager, camera, map, _) = raycastContext

    val viewportWidth = renderer.viewportWidth
    val viewportHeight = renderer.viewportHeight
    val viewportHeightHalf = viewportHeight / 2

    var raySweepAngle = camera.rotation - (camera.fov / 2)

    for (rayIndex in 0 until viewportWidth) {

      var rayX = camera.xPos
      var rayY = camera.yPos
      var objectTypeHit: Int

      do {
        rayX += raySweepAngle.cosine() / options.stepPrecision
        rayY += raySweepAngle.sine() / options.stepPrecision

        // TODO bounds checking
        objectTypeHit = map.data[rayY.toFlooredInt()][rayX.toFlooredInt()]
      } while (objectTypeHit == 0)

      val texture = textureManager.getTexture(objectTypeHit)
      val textureXIndex = ((texture.width * (rayX + rayY)) % texture.width).toFlooredInt()

      var distanceToWall = kotlin.math.sqrt((camera.xPos - rayX).pow(2) + (camera.yPos - rayY).pow(2))
      if (options.fixFisheye) {
        distanceToWall *= (raySweepAngle-camera.rotation).cosine()
      }
      val wallHeight = viewportHeightHalf / distanceToWall

      // Ceiling
      renderer.drawLine(rayIndex, 0.0, rayIndex, viewportHeightHalf - wallHeight, "#505050")
      // Wall
      drawWallTexture(rayIndex, wallHeight, textureXIndex, texture, renderer)
      // Floor
      renderer.drawLine(rayIndex, viewportHeightHalf + wallHeight, rayIndex, viewportHeight, "#A9A9A9")

      raySweepAngle += (camera.fov / viewportWidth.toDouble())
    }

    console.log("Viewport raycast in ${Date().getTime() - raycastStartMs}ms")
  }

  private fun drawWallTexture(rayIndex: Int, wallHeight: Double, textureXIndex: Int, texture: Texture, renderer: Renderer) {
    val yIncrement = (wallHeight * 2) / texture.height
    var y = (renderer.viewportHeight/2) - wallHeight

    for (i in 0 until texture.height) {
      // Extend y length by 0.1 to overlap strips slightly to avoid screen-door like effect
      renderer.drawLine(rayIndex, y, rayIndex, y + yIncrement + 0.1, texture.cssColourPixelList[textureXIndex + i * texture.width])
      y += yIncrement
    }
  }

}