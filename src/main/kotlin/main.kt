fun main() {
  val renderer = Renderer(viewportWidth = 320, viewportHeight = 240, outputScale = 3)
  val textureManager = TextureManager()
    .apply { loadTextures() }
  val camera = Camera(
    fov = 90,
    xPos = 2.0,
    yPos = 2.0,
    rotation = 90.0
  )
  val map = Map()
  val minimap = Minimap(map)

  val context = RaycastContext(renderer, textureManager, camera, map, minimap)

  val raycaster = Raycaster(stepPrecision = 32)

  CameraController(camera, moveSpeed = 0.5, rotateSpeed = 5) {
    paint(raycaster, context)
  }

  // Do an initial paint and wait for input
  paint(raycaster, context)
}

fun paint(raycaster: Raycaster, raycastContext: RaycastContext) {
  with(raycastContext) {
    renderer.clear()
    raycaster.raycast(this)
    minimap.update(camera)
  }
}

fun toRadians(degrees: Double): Double {
  return degrees * kotlin.math.PI / 180
}
