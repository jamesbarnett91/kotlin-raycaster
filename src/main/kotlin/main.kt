fun main() {
  val raycaster = Raycaster()
  val renderer = Renderer(viewportWidth = 640, viewportHeight = 480)
  val camera = Camera(
    fov = 90,
    xPos = 2.0,
    yPos = 2.0,
    rotation = 90.0
  )
  val map = Map()
  val minimap = Minimap(map)

  val context = RaycastContext(renderer, camera, map, minimap)

  CameraController(camera, moveSpeed = 0.5, rotateSpeed = 5) {
    paint(raycaster, context)
  }

  // Do an initial paint and wait for input
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
