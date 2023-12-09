import org.opencv.core.CvType
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.core.Scalar
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import kotlin.math.sin

fun generateImpulseImage(N: Int, M: Int, i0: Int = 0, j0: Int = 0): ByteArray {
    val image = Mat(N, M, CvType.CV_8UC1, Scalar(0.0))

    image.put(i0, j0, byteArrayOf(255.toByte()))

    val matOfByte = MatOfByte()
    Imgcodecs.imencode(".jpg", image, matOfByte)
    return matOfByte.toArray()
}

fun generateDelayedUnitStep(N: Int, M: Int, i0: Int = 0, j0: Int = 0): ByteArray {
    val image = Mat(N, M, CvType.CV_8UC1, Scalar(0.0))

    for (i in i0 until N) {
        for (j in j0 until M) {
            image.put(i, j, byteArrayOf(255.toByte()))
        }
    }

    val matOfByte = MatOfByte()
    Imgcodecs.imencode(".jpg", image, matOfByte)
    return matOfByte.toArray()
}

fun generateTimeDependentCircle(N: Int, M: Int, i0: Int, j0: Int, time: Double): ByteArray {
    val image = Mat(N, M, CvType.CV_8UC1, Scalar(0.0))

    val radius = 30 * (1 + sin(time))

    Imgproc.circle(image, org.opencv.core.Point(j0.toDouble(), i0.toDouble()), radius.toInt(), Scalar(255.0), -1)

    val matOfByte = MatOfByte()
    Imgcodecs.imencode(".jpg", image, matOfByte)
    return matOfByte.toArray()
}

fun generateTimeDependentSquare(N: Int, M: Int, i0: Int, j0: Int, time: Double): ByteArray {
    val image = Mat(N, M, CvType.CV_8UC1, Scalar(0.0))

    val sideLength = 30 * (1 + sin(time))

    val topLeft = org.opencv.core.Point((j0 - sideLength / 2).coerceAtLeast(0.0), (i0 - sideLength / 2).coerceAtLeast(0.0))
    val bottomRight = org.opencv.core.Point((j0 + sideLength / 2).coerceAtMost(M.toDouble()), (i0 + sideLength / 2).coerceAtMost(N.toDouble()))

    Imgproc.rectangle(image, topLeft, bottomRight, Scalar(255.0), -1)

    val matOfByte = MatOfByte()
    Imgcodecs.imencode(".jpg", image, matOfByte)
    return matOfByte.toArray()
}

fun generateChessboardImage(cellsInRow: Int, cellSize: Int): ByteArray {
    val cellWidth = cellSize
    val cellHeight = cellSize

    // Вычисляем размеры изображения
    val N = cellsInRow * cellHeight
    val M = cellsInRow * cellWidth

    // Создаем матрицу для изображения
    val image = Mat(N, M, CvType.CV_8UC1, Scalar(255.0))

    // Заполняем изображение шахматной доской
    for (i in 0 until cellsInRow) {
        for (j in 0 until cellsInRow) {
            // Определяем цвет клетки (черный или белый)
            val color = if ((i + j) % 2 == 0) 0.0 else 255.0

            // Заполняем квадратную область цветом
            val cell = Mat(image, org.opencv.core.Rect(j * cellWidth, i * cellHeight, cellWidth, cellHeight))
            cell.setTo(Scalar(color))
        }
    }

    val matOfByte = MatOfByte()
    Imgcodecs.imencode(".jpg", image, matOfByte)
    return matOfByte.toArray()
}