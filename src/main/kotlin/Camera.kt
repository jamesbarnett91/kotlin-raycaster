class Camera(
  val fov: Int,
  var xPos: Double,
  var yPos: Double,
  var rotation: Double
) {
  val halfFov = fov / 2
}