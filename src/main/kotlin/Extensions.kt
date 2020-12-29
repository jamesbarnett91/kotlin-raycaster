fun Double.sine(): Double {
  return kotlin.math.sin(toRadians(this))
}

fun Double.cosine(): Double {
  return kotlin.math.cos(toRadians(this))
}

fun Double.toFlooredInt(): Int {
  return kotlin.math.floor(this).toInt()
}