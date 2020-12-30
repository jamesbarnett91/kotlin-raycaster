fun main() {
  val renderer = Renderer(viewportWidth = 320, viewportHeight = 240, outputScale = 3)
  val textureManager = TextureManager()
  val camera = Camera(
    fov = 90,
    xPos = 1.2,
    yPos = 2.5,
    rotation = 60.0
  )
  val map = Map()
  val minimap = Minimap(map)

  val context = RaycastContext(renderer, textureManager, camera, map, minimap)

  val raycaster = Raycaster(stepPrecision = 32)

  CameraController(camera, moveSpeed = 1.0, rotateSpeed = 15) {
    paint(raycaster, context)
  }

  val ui = Ui(textureManager) {
    paint(raycaster, context)
  }

  textureManager.loadTextures(ui.getSelectedTextureSet())

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
