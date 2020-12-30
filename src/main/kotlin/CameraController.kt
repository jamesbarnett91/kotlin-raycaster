import kotlinx.browser.document
import org.w3c.dom.HTMLButtonElement

class CameraController(
  private val camera: Camera,
  private val moveSpeed: Double,
  private val rotateSpeed: Int,
  private val afterInput: () -> Unit
) {

  init {
    document.onkeydown = { keyHandler(it.code) }
    (document.getElementById("up") as HTMLButtonElement).onclick = { moveForward() }
    (document.getElementById("down") as HTMLButtonElement).onclick = { moveBack() }
    (document.getElementById("left") as HTMLButtonElement).onclick = { rotateAntiClockwise() }
    (document.getElementById("right") as HTMLButtonElement).onclick = { rotateClockwise() }
  }

  private fun keyHandler(code: String) {
    when (code) {
      "KeyW" -> moveForward()
      "KeyS" -> moveBack()
      "KeyA" -> rotateAntiClockwise()
      "KeyD" -> rotateClockwise()
    }
  }

  private fun moveForward() {
    val cameraCos = camera.rotation.cosine() * moveSpeed
    val cameraSin = camera.rotation.sine() * moveSpeed
    camera.xPos += cameraCos
    camera.yPos += cameraSin
    afterInput()
  }

  private fun moveBack() {
    val cameraCos = camera.rotation.cosine() * moveSpeed
    val cameraSin = camera.rotation.sine() * moveSpeed
    camera.xPos -= cameraCos
    camera.yPos -= cameraSin
    afterInput()
  }

  private fun rotateClockwise() {
    camera.rotation += rotateSpeed
    afterInput()
  }

  private fun rotateAntiClockwise() {
    camera.rotation -= rotateSpeed
    afterInput()
  }

}