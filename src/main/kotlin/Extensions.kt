fun Double.toRadians() = this * kotlin.math.PI / 180

fun Double.sine() = kotlin.math.sin(this.toRadians())

fun Double.cosine() = kotlin.math.cos(this.toRadians())

fun Double.toFlooredInt() = kotlin.math.floor(this).toInt()

fun Double.toRoundedString() = this.asDynamic().toFixed(2) as String