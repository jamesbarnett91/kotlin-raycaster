import kotlinx.browser.document
import org.w3c.dom.HTMLSelectElement

class Ui(private val textureManager: TextureManager, private val afterChange: () -> Unit) {
  private val textureSelect: HTMLSelectElement
  init {
    textureSelect = registerTextureSetHandler()
  }

  private fun registerTextureSetHandler(): HTMLSelectElement {
    val select = document.getElementById("texture-set") as HTMLSelectElement
    select.onchange = {
      textureManager.loadTextures(select.value)
      afterChange()
    }

    return select
  }

  fun getSelectedTextureSet(): String {
    return textureSelect.value
  }
}