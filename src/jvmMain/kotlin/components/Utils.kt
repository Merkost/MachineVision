package components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.IntOffset
import org.jetbrains.skia.Image
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File

fun imageFromFile(file: File): ImageBitmap {
    return Image.makeFromEncoded(file.readBytes()).toComposeImageBitmap()
}

fun imageFromArr(arr: ByteArray): ImageBitmap {
    return Image.makeFromEncoded(arr).toComposeImageBitmap()
}

fun convertToGrayscale(inputImage: BufferedImage): BufferedImage {
    val width = inputImage.width
    val height = inputImage.height
    val outputImage = BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    for (x in 0 until width) {
        for (y in 0 until height) {
            val color = Color(inputImage.getRGB(x, y))
            val gray = (color.red * 0.299 + color.green * 0.587 + color.blue * 0.114).toInt()
            val grayColor = Color(gray, gray, gray)
            outputImage.setRGB(x, y, grayColor.rgb)
        }
    }

    return outputImage
}

//@Composable
//fun ScreenshotWindow() = Window({ }) {
//    var regionSelectionActive by remember { mutableStateOf(false) }
//    var screenshot: BufferedImage? = null
//    var selectionRect: Rectangle? = null
//
//    val robot = Robot()
//
//    Box(
//        modifier = Modifier.fillMaxSize().background(androidx.compose.ui.graphics.Color.White).pointerInput(Unit) {
//            var startPos: IntOffset? = null
//
//            do {
//                awaitPointerEventScope {
//                    val event = awaitFirstDown()
//                    startPos = event.position.toIntOffset()
//                }
//
//                awaitPointerEventScope {
////                    val event = awaitFirstUp()
////                    val endPos = event.position.toIntOffset()
//
//                    regionSelectionActive = true
//                    selectionRect = Rectangle(
//                        startPos!!.x,
//                        startPos!!.y,
////                        endPos.x - startPos.x,
////                        endPos.y - startPos.y
//                    )
//
//                    screenshot = robot.createScreenCapture(selectionRect)
////                    appWindow.window.isVisible = false
////                    appWindow.window.isVisible = true
//                }
//            } while (true)
//        }
//    )
//
//
////    onDispose {
////        screenshot?.let { img ->
////            ImageIO.write(img, "png", java.io.File("screenshot.png"))
////        }
////
////    }
//}

private fun Offset.toIntOffset(): IntOffset {
    return IntOffset(x.toInt(), y.toInt())
}