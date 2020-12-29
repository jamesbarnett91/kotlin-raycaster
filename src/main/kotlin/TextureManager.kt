import kotlinx.browser.document
import org.khronos.webgl.get
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import org.w3c.dom.HTMLImageElement
import org.w3c.dom.asList

class TextureManager {

  private lateinit var textures: List<Texture>

  fun getTexture(id: Int): Texture {
    return textures.first { it.id == id }
  }

  fun loadTextures() {
    textures = document.getElementsByClassName("texture-definition").asList()
      .map { it as HTMLImageElement }
      .map {
        val id = it.id.toInt()
        val width = it.getAttribute("data-width")?.toInt() ?: 64
        val height = it.getAttribute("data-height")?.toInt() ?: 64
        Texture(id, width, height, parseImage(it, width, height))
      }

    console.log("Loaded ${textures.size} texture(s)")
  }

  private fun parseImage(image: HTMLImageElement, imageWidth: Int, imageHeight: Int): List<String> {
    val canvas = (document.createElement("canvas") as HTMLCanvasElement)
      .apply {
        width = imageWidth
        height = imageHeight
      }

    // Draw image to canvas and read back out to get RGBA data
    val imageData = with(canvas.getContext("2d") as CanvasRenderingContext2D) {
      drawImage(image, 0.0, 0.0, imageWidth.toDouble(), imageHeight.toDouble())
      getImageData(0.0, 0.0, imageWidth.toDouble(), imageHeight.toDouble()).data
    }

    val cssColourPixelList = mutableListOf<String>()
    for (i in 0 until imageData.length step 4) {
      cssColourPixelList.add("rgb(${imageData[i]},${imageData[i+1]},${imageData[i+2]}")
    }

    return cssColourPixelList
  }
}