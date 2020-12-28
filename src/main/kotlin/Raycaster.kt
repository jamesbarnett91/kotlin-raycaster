import kotlin.math.pow

class Raycaster(private val stepPrecision: Int = 32) {

  fun raycast(raycastContext: RaycastContext) {
    val (renderer, camera, map, _) = raycastContext

    val viewportWidth = renderer.viewportWidth
    val viewportHeight = renderer.viewportHeight
    val viewportHeightHalf = viewportHeight / 2

    var raySweepAngle = camera.rotation - (camera.fov / 2)

    for (rayIndex in 0 until viewportWidth) {

      var rayX = camera.xPos
      var rayY = camera.yPos

      do {
        rayX += raySweepAngle.cosine() / stepPrecision
        rayY += raySweepAngle.sine() / stepPrecision
        // TODO bounds checking
        val wallHit = map.data[kotlin.math.floor(rayY).toInt()][kotlin.math.floor(rayX).toInt()] > 0
      } while (!wallHit)

      val distanceToWall = kotlin.math.sqrt((camera.xPos - rayX).pow(2) + (camera.yPos - rayY).pow(2))
      val wallHeight = kotlin.math.floor(viewportHeightHalf / distanceToWall).toInt()

      // Sky
      renderer.drawLine(rayIndex, 0, rayIndex, viewportHeightHalf - wallHeight, "cyan")
      // Wall
      renderer.drawLine(rayIndex, viewportHeightHalf - wallHeight, rayIndex, viewportHeightHalf + wallHeight, "red")
      // Floor
      renderer.drawLine(rayIndex, viewportHeightHalf + wallHeight, rayIndex, viewportHeight, "green")

      raySweepAngle += (camera.fov / viewportWidth.toDouble())
    }
  }



}