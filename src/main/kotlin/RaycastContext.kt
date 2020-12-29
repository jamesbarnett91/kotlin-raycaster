data class RaycastContext(
  val renderer: Renderer,
  val textureManager: TextureManager,
  val camera: Camera,
  val map: Map,
  val minimap: Minimap
)