import kotlinx.browser.document

private const val MOVE_SPEED = 0.5
private const val ROTATE_SPEED = 5

data class RaycastContext(
  val renderer: Renderer,
  val camera: Camera,
  val map: Map,
  val minimap: Minimap
)

fun main() {
  val renderer = Renderer(640, 480)
  val camera = Camera(
    fov = 60,
    xPos = 2.0,
    yPos = 2.0,
    rotation = 90.0
  )
  val map = Map()
  val minimap = Minimap(map)

  val context = RaycastContext(renderer, camera, map, minimap)

  val raycaster = Raycaster()

  document.onkeydown = {
    when (it.code) {
      "KeyW" -> {
        console.log("key w")
        val cameraCos = kotlin.math.cos(toRadians(camera.rotation)) * MOVE_SPEED
        val cameraSin = kotlin.math.sin(toRadians(camera.rotation)) * MOVE_SPEED
        camera.xPos += cameraCos
        camera.yPos += cameraSin
      }
      "KeyS" -> {
        console.log("key s")
        val cameraCos = kotlin.math.cos(toRadians(camera.rotation)) * MOVE_SPEED
        val cameraSin = kotlin.math.sin(toRadians(camera.rotation)) * MOVE_SPEED
        camera.xPos -= cameraCos
        camera.yPos -= cameraSin
      }
      "KeyA" -> {
        console.log("key a")
        camera.rotation -= ROTATE_SPEED
      }
      "KeyD" -> {
        console.log("key d")
        camera.rotation += ROTATE_SPEED
      }
    }
    paint(raycaster, context)
    console.log("camera x:${camera.xPos} y:${camera.yPos} r: ${camera.rotation}")
  }

  paint(raycaster, context)
}

fun paint(raycaster: Raycaster, raycastContext: RaycastContext) {
  raycastContext.renderer.clear()
  raycaster.raycast(raycastContext)
  raycastContext.minimap.update(raycastContext.camera)
}

fun toRadians(degrees: Double): Double {
  return degrees * kotlin.math.PI / 180
}
