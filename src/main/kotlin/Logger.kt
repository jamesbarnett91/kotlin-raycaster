import kotlinx.browser.document
import org.w3c.dom.HTMLElement

object Logger {
  private val logMessage = document.getElementById("log-message") as HTMLElement
  private val logPosition = document.getElementById("log-position") as HTMLElement

  fun log(message: String) {
    logMessage.textContent = message
    console.log(message)
  }

  fun logPosition(camera: Camera) {
    with(camera) {
      var normalisedRotation = rotation % 360
      if(normalisedRotation < 0) normalisedRotation += 360
      logPosition.textContent = "x: ${xPos.toRoundedString()} y: ${yPos.toRoundedString()} rotation: $normalisedRotation"
    }
  }


}