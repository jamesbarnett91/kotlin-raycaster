import kotlinx.browser.document
import kotlinx.dom.removeClass
import org.w3c.dom.*

class Ui(private val context: RaycastContext, private val afterChange: () -> Unit) {

  private val textureSelect = registerTextureSetHandler()

  init {
    registerFovInputHandler()
    registerRaycastPrecisionInputHandler()
    registerOverlayToggleHandler()
    registerFisheyeToggleHandler()
  }

  private fun registerTextureSetHandler(): HTMLSelectElement {
    val select = document.getElementById("texture-set") as HTMLSelectElement
    select.onchange = {
      context.textureManager.loadTextures(select.value)
      afterChange()
    }
    return select
  }

  private fun registerFovInputHandler() {
    val fov = document.getElementById("fov") as HTMLInputElement
    val fovValue = document.getElementById("fov-value") as HTMLElement
    fov.oninput = {
      context.camera.fov = fov.value.toInt()
      fovValue.textContent = fov.value
      afterChange()
    }
  }

  private fun registerRaycastPrecisionInputHandler() {
    val precision = document.getElementById("raycast-precision") as HTMLInputElement
    val precisionValue = document.getElementById("raycast-precision-value") as HTMLElement
    precision.oninput = {
      context.raycastOptions.stepPrecision = precision.value.toInt()
      precisionValue.textContent = precision.value
      afterChange()
    }
  }

  private fun registerOverlayToggleHandler() {
    val toggle = document.getElementById("overlay-toggle") as HTMLInputElement
    val minimap = document.getElementById("minimap") as HTMLElement
    val log = document.getElementById("log") as HTMLElement
    toggle.onchange = {
      minimap.hidden = !toggle.checked
      log.hidden = !toggle.checked
      afterChange()
    }
  }

  private fun registerFisheyeToggleHandler() {
    val fixFisheye = document.getElementById("fisheye-fix") as HTMLInputElement
    fixFisheye.oninput = {
      context.raycastOptions.fixFisheye = fixFisheye.checked
      afterChange()
    }
  }

  fun getSelectedTextureSet() = textureSelect.value

  fun removeLoadingIndicator() = document.getElementById("output-wrapper")?.removeClass("loading")
}