import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import model.UiState

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
                    Text("Введите координаты импульса (${bitmap.width} x ${bitmap.height})")
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