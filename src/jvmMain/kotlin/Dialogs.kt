import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import model.UiState

@Composable
fun ImageSizeDialog(
    onDone: (height: Int, width: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var height by remember {
        mutableStateOf("500")
    }
    var width by remember {
        mutableStateOf("500")
    }

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите размер изображения (высота x ширина)")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = height,
                            onValueChange = { height = it },
                            placeholder = { Text("Высота") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = width,
                            onValueChange = { width = it },
                            placeholder = { Text("Ширина") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(height.toInt(), width.toInt())
                                    onDismiss()
                                },
                                enabled = height.toIntOrNull() != null && width.toIntOrNull() != null
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}


@Composable
fun SaveFileDialog(
    onDone: (fileName: String) -> Unit,
    onDismiss: () -> Unit,
) {
    var fileName by remember {
        mutableStateOf("my_file")
    }

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите название")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = fileName,
                            onValueChange = { fileName = it },
                            placeholder = { Text("Высота") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(fileName)
                                    onDismiss()
                                },
                                enabled = true
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}
@Composable
fun XYDialog(
    uiState: UiState,
    onDone: (x: Int, y: Int, width: Int, height: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var x by remember {
        mutableStateOf("")
    }
    var y by remember {
        mutableStateOf("")
    }
    val bitmap = uiState.file?.bitmap ?: ImageBitmap(0, 0)

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите координаты(${bitmap.width} x ${bitmap.height})")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = x,
                            onValueChange = { x = it },
                            placeholder = { Text("X") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = y,
                            onValueChange = { y = it },
                            placeholder = { Text("Y") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(x.toInt(), y.toInt(), bitmap.width, bitmap.height)
                                    onDismiss()
                                },
                                enabled = x.toIntOrNull() != null && y.toIntOrNull() != null
                            ) {
                                Text("Подтвердить")

                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WhiteNoiseDialog(
    uiState: UiState,
    onDone: (x: Float, y: Float, width: Int, height: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var x by remember {
        mutableStateOf("")
    }
    var y by remember {
        mutableStateOf("")
    }
    val bitmap = uiState.file?.bitmap ?: ImageBitmap(0, 0)

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите значения (${bitmap.width} x ${bitmap.height})")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = x,
                            onValueChange = { x = it },
                            placeholder = { Text("Нижняя граница") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = y,
                            onValueChange = { y = it },
                            placeholder = { Text("Верхняя граница") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(x.toFloat(), y.toFloat(), bitmap.width, bitmap.height)
                                    onDismiss()
                                },
                                enabled = x.toFloatOrNull() != null && y.toFloatOrNull() != null
                            ) {
                                Text("Подтвердить")

                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun WhiteNoiseGuassianDialog(
    uiState: UiState,
    onDone: (x: Float, y: Float, width: Int, height: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var x by remember {
        mutableStateOf("")
    }
    var y by remember {
        mutableStateOf("")
    }
    val bitmap = uiState.file?.bitmap ?: ImageBitmap(0, 0)

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите значения (${bitmap.width} x ${bitmap.height})")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = x,
                            onValueChange = { x = it },
                            placeholder = { Text("a") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = y,
                            onValueChange = { y = it },
                            placeholder = { Text("Дисперсия") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(x.toFloat(), y.toFloat(), bitmap.width, bitmap.height)
                                    onDismiss()
                                },
                                enabled = x.toFloatOrNull() != null && y.toFloatOrNull() != null
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun XYTimeDialog(
    uiState: UiState,
    onDone: (x: Int, y: Int, time: Double, width: Int, height: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var x by remember {
        mutableStateOf("")
    }
    var y by remember {
        mutableStateOf("")
    }
    var time by remember {
        mutableStateOf("")
    }

    val bitmap = uiState.file?.bitmap ?: ImageBitmap(0, 0)

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите координаты (${bitmap.width} x ${bitmap.height}) и время")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = x,
                            onValueChange = { x = it },
                            placeholder = { Text("X") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = y,
                            onValueChange = { y = it },
                            placeholder = { Text("Y") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = time,
                            onValueChange = { time = it },
                            placeholder = { Text("Время") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(x.toInt(), y.toInt(), time.toDouble(), bitmap.width, bitmap.height)
                                    onDismiss()
                                },
                                enabled = x.toIntOrNull() != null && y.toIntOrNull() != null && time.toDoubleOrNull() != null
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ChessDialog(
    onDone: (cellsInRow: Int, cellSize: Int) -> Unit,
    onDismiss: () -> Unit,
) {
    var cellsInRow by remember {
        mutableStateOf("")
    }
    var cellSize by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите количество клеток в одном ряду и размер (сторона) клетки в пикселах")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = cellsInRow,
                            onValueChange = { cellsInRow = it },
                            placeholder = { Text("Клеток в ряду") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = cellSize,
                            onValueChange = { cellSize = it },
                            placeholder = { Text("Размер клетки") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(cellsInRow.toInt(), cellSize.toInt())
                                    onDismiss()
                                },
                                enabled = cellsInRow.toIntOrNull() != null && cellSize.toIntOrNull() != null
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}

enum class Orientation(val textName: String) {
    STRAIGHT("прямая"),
    ELSE("косая");
}

@Composable
fun CircleGridDialog(
    onDone: (K: Int, R: Int, D: Int, orientation: Orientation, t: Double, a: Double) -> Unit,
    onDismiss: () -> Unit,
) {
    var orientation by remember {
        mutableStateOf(Orientation.STRAIGHT)
    }
    var K by remember {
        mutableStateOf("")
    }
    var R by remember {
        mutableStateOf("")
    }
    var D by remember {
        mutableStateOf("")
    }
    var t by remember {
        mutableStateOf("0")
    }
    var a by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text("Введите количество клеток в одном ряду и размер (сторона) клетки в пикселах")
                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = K,
                            onValueChange = { K = it },
                            placeholder = { Text("Количество узлов решетки") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = R,
                            onValueChange = { R = it },
                            placeholder = { Text("Радиус кругов") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = D,
                            onValueChange = { D = it },
                            placeholder = { Text("Расстояние между узлами") })
                        Column(
                            modifier = Modifier.padding(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Ориентация")
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(32.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Orientation.values().forEach {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        RadioButton(
                                            selected = orientation == it,
                                            onClick = { orientation = it }
                                        )
                                        Text(it.textName)
                                    }
                                }
                            }
                        }
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = t,
                            onValueChange = { t = it },
                            placeholder = { Text("Время") })
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = a,
                            onValueChange = { a = it },
                            placeholder = { Text("Амплитуда") })
                        Row {
                            Button(
                                onClick = {
                                    onDone(K.toInt(), R.toInt(), D.toInt(), orientation, t.toDouble(), a.toDouble())
                                    onDismiss()
                                },
                                enabled = K.toIntOrNull() != null && R.toIntOrNull() != null && D.toIntOrNull() != null && a.toIntOrNull() != null
                            ) {
                                Text("Подтвердить")
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ImageInfoDialog(
    image: ImageBitmap?,
    onDismiss: () -> Unit,
) {

    Dialog(
        onDismissRequest = onDismiss,
        content = {
            Card(shape = RoundedCornerShape(12.dp)) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (image == null) {
                        Text("Изображение не выбрано")
                    } else {
                        val width = image.width // Image width in pixels
                        val height = image.height // Image height in pixels
                        val colorSpace = image.colorSpace.name ?: "Unknown" // Image color space (e.g., "SRGB")

                        Text("Image Information:")
                        Text("Width: $width pixels")
                        Text("Height: $height pixels")
                        Text("Color space: $colorSpace")
                    }
                }
            }
        }
    )
}