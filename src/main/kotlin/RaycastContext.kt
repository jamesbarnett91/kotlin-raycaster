data class RaycastContext(
  val raycastOptions: RaycastOptions,
  val renderer: Renderer,
  val textureManager: TextureManager,
  val camera: Camera,
  val map: Map,
  val minimap: Minimap
)