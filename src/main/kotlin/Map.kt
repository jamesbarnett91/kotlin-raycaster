class Map {
  val data = listOf(
    listOf(1,1,1,1,1,1,1,1,1,1),
    listOf(1,0,0,0,0,0,0,0,0,1),
    listOf(1,0,0,0,0,0,0,0,0,1),
    listOf(1,0,0,2,2,0,2,0,0,1),
    listOf(1,0,0,2,0,0,2,0,0,1),
    listOf(1,0,0,2,0,0,2,0,0,1),
    listOf(1,0,0,2,0,2,2,0,0,1),
    listOf(1,0,0,0,0,0,0,0,0,1),
    listOf(1,0,0,0,0,0,0,0,0,1),
    listOf(1,1,1,1,1,1,1,1,1,1)
  )

  val width = data[0].size
  val height = data.size
}