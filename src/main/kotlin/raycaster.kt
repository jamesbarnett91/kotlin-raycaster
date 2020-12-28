import kotlinx.browser.document
import kotlin.math.pow

private const val SCREEN_WIDTH = 640
private const val SCREEN_HEIGHT = 480
private const val MOVE_SPEED = 0.5
private const val ROTATE_SPEED = 5

private val map = listOf(
  listOf(1,1,1,1,1,1,1,1,1,1),
  listOf(1,0,0,0,0,0,0,0,0,1),
  listOf(1,0,0,0,0,0,0,0,0,1),
  listOf(1,0,0,1,1,0,1,0,0,1),
  listOf(1,0,0,1,0,0,1,0,0,1),
  listOf(1,0,0,1,0,0,1,0,0,1),
  listOf(1,0,0,1,0,1,1,0,0,1),
  listOf(1,0,0,0,0,0,0,0,0,1),
  listOf(1,0,0,0,0,0,0,0,0,1),
  listOf(1,1,1,1,1,1,1,1,1,1)
)

data class RaycastContext(
  val renderer: Renderer,
  val camera: Camera,
  val minimap: Minimap
)

fun main() {
  val renderer = Renderer(SCREEN_WIDTH, SCREEN_HEIGHT)
  val camera = Camera(
    fov = 60,
    xPos = 2.0,
    yPos = 2.0,
    rotation = 90.0
  )
  val minimap = Minimap(map)

  val context = RaycastContext(renderer, camera, minimap)

  document.onkeydown = {
    when (it.code) {
      "KeyW" -> {
        console.log("key w")
        val cameraCos = kotlin.math.cos(toRadians(camera.rotation)) * MOVE_SPEED
        val cameraSin = kotlin.math.sin(toRadians(camera.rotation)) * MOVE_SPEED
        camera.xPos += cameraCos
        camera.yPos += cameraSin
      }
      "KeyS" -> {
        console.log("key s")
        val cameraCos = kotlin.math.cos(toRadians(camera.rotation)) * MOVE_SPEED
        val cameraSin = kotlin.math.sin(toRadians(camera.rotation)) * MOVE_SPEED
        camera.xPos -= cameraCos
        camera.yPos -= cameraSin
      }
      "KeyA" -> {
        console.log("key a")
        camera.rotation -= ROTATE_SPEED
      }
      "KeyD" -> {
        console.log("key d")
        camera.rotation += ROTATE_SPEED
      }
    }
    paint(context)
    console.log("camera x:${camera.xPos} y:${camera.yPos} r: ${camera.rotation}")
  }

  paint(context)
}

fun paint(raycastContext: RaycastContext) {
  raycastContext.renderer.clear()
  raycast(raycastContext)
  raycastContext.minimap.update(raycastContext.camera)
}

fun raycast(raycastContext: RaycastContext) {
  val incrementAngle : Double = raycastContext.camera.fov / SCREEN_WIDTH.toDouble()
  val rayCastPrecision = 32

  var rayAngle = raycastContext.camera.rotation - raycastContext.camera.halfFov

  for (rayIndex in 0 until SCREEN_WIDTH) {

    var rayX = raycastContext.camera.xPos
    var rayY = raycastContext.camera.yPos

    val rayCos = kotlin.math.cos(toRadians(rayAngle)) / rayCastPrecision
    val raySin = kotlin.math.sin(toRadians(rayAngle)) / rayCastPrecision

    var isWall = false
    while(!isWall) {
      rayX += rayCos
      rayY += raySin

      // TODO bounds checking
      val foo = map[kotlin.math.floor(rayY).toInt()][kotlin.math.floor(rayX).toInt()]
      if (foo == 1) {
        isWall = true
      }
    }

    val distance = kotlin.math.sqrt((raycastContext.camera.xPos - rayX).pow(2) + (raycastContext.camera.yPos - rayY).pow(2))
    val wallHeight = kotlin.math.floor((SCREEN_HEIGHT/2) / distance).toInt()

    raycastContext.renderer.drawLine(rayIndex, 0, rayIndex, (SCREEN_HEIGHT/2)-wallHeight, "cyan")
    raycastContext.renderer.drawLine(rayIndex, (SCREEN_HEIGHT/2)-wallHeight, rayIndex, (SCREEN_HEIGHT/2)+wallHeight, "red")
    raycastContext.renderer.drawLine(rayIndex, (SCREEN_HEIGHT/2)+wallHeight, rayIndex, SCREEN_HEIGHT, "green")

    rayAngle += incrementAngle
  }
}

fun toRadians(degrees: Double): Double {
  return degrees * kotlin.math.PI / 180
}
