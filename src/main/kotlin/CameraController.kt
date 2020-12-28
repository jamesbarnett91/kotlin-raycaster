import kotlinx.browser.document

class CameraController(
  private val camera: Camera,
  private val moveSpeed: Double = 0.5,
  private val rotateSpeed: Int = 5,
  private val afterInput: () -> Unit
) {

  init {
    document.onkeydown = { inputHandler(it.code) }
  }

  private fun inputHandler(code: String) {
    when (code) {
      "KeyW" -> moveForward()
      "KeyS" -> moveBack()
      "KeyA" -> rotateAntiClockwise()
      "KeyD" -> rotateClockwise()
    }
    afterInput()
  }

  private fun moveForward() {
    val cameraCos = camera.rotation.cosine() * moveSpeed
    val cameraSin = camera.rotation.sine() * moveSpeed
    camera.xPos += cameraCos
    camera.yPos += cameraSin
  }

  private fun moveBack() {
    val cameraCos = camera.rotation.cosine() * moveSpeed
    val cameraSin = camera.rotation.sine() * moveSpeed
    camera.xPos -= cameraCos
    camera.yPos -= cameraSin
  }

  private fun rotateClockwise() {
    camera.rotation += rotateSpeed
  }

  private fun rotateAntiClockwise() {
    camera.rotation -= rotateSpeed
  }

}