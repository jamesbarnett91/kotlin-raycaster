# Kotlin Raycaster

A simple pseudo 3D [raycasting](https://en.wikipedia.org/wiki/Ray_casting) engine written from scratch in Kotlin, targeting the browser with [Kotlin/JS](https://kotlinlang.org/docs/reference/js-overview.html) and rendered using only vertical lines drawn on an HTML5 canvas.

Interactive demo [here](https://jamesbarnett.io/raycaster)

![](https://jamesbarnett.io/files/raycaster/raycast2.png)

## Process
Raycasting is a simple rendering technique allowing [2D map data](https://github.com/jamesbarnett91/kotlin-raycaster/blob/main/src/main/kotlin/Map.kt) to be shown in 3D perspective.
It has several limitations, most notably that all walls must be vertical and of the same height, and the camera perspective can't move vertically up/down (although it's possible to implement these by extending the core raycasting concepts).

Raycasting was used in early 90's 3D games, most famously [Wolfenstein 3D](https://en.wikipedia.org/wiki/Wolfenstein_3D).

For a in-depth explanation of raycasting see the links below
- https://lodev.org/cgtutor/raycasting.html
- https://permadi.com/1996/05/ray-casting-tutorial-table-of-contents/
- https://github.com/vinibiavatti1/RayCastingTutorial/wiki
- https://dev.opera.com/articles/3d-games-with-canvas-and-raycasting-part-1/
