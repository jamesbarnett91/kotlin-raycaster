import kotlinx.browser.window

fun main() {
  window.onload = {
    val raycastOptions = RaycastOptions(fixFisheye = false, stepPrecision = 32)
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

    val context = RaycastContext(raycastOptions, renderer, textureManager, camera, map, minimap)

    val raycaster = Raycaster()

    CameraController(
      camera,
      moveSpeed = 1.0,
      rotateSpeed = 15,
      xMax = map.width - 1,
      yMax = map.height - 1
    ) {
      paint(raycaster, context)
    }

    val ui = Ui(context) {
      paint(raycaster, context)
    }

    textureManager.loadTextures(ui.getSelectedTextureSet())

    // Do an initial paint and wait for input
    paint(raycaster, context)
    ui.removeLoadingIndicator()
  }
}

private fun paint(raycaster: Raycaster, raycastContext: RaycastContext) {
  with(raycastContext) {
    renderer.clear()
    raycaster.raycast(this)
    minimap.update(camera)
    Logger.logPosition(camera)
  }
}
