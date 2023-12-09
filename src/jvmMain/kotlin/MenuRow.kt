import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import components.Icon
import components.imageFromArr
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.SelectedFile
import model.UiState

@Composable
fun MenuRow(
    uiState: UiState,
    onGrayChanged: (Boolean) -> Unit,
    onUpdateFile: (SelectedFile?) -> Unit,
    onSelectFile: () -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()

    var buildPanelVisible by remember {
        mutableStateOf(false)
    }
    var delayedSingleImpulseDialog by remember {
        mutableStateOf(false)
    }
    var delayedSingleStepDialog by remember {
        mutableStateOf(false)
    }
    var singleCircleDialog by remember {
        mutableStateOf(false)
    }
    var singleSquareDialog by remember {
        mutableStateOf(false)
    }
    var chessDialog by remember {
        mutableStateOf(false)
    }



    if (delayedSingleImpulseDialog) {
        XYDialog(
            uiState,
            onDismiss = { delayedSingleImpulseDialog = false },
            onDone = { x, y, maxX, maxY ->
                coroutineScope.launch(Dispatchers.Default) {
                    val photo = generateImpulseImage(maxY, maxX, x, y)
                    val bitmap = imageFromArr(photo)
                    onUpdateFile(uiState.file?.copy(bitmap = bitmap))
                }
            }
        )
    }

    if (delayedSingleStepDialog) {
        XYDialog(
            uiState,
            onDismiss = { delayedSingleStepDialog = false },
            onDone = { x, y, maxX, maxY ->
                coroutineScope.launch(Dispatchers.Default) {
                    val photo = generateDelayedUnitStep(maxY, maxX, x, y)
                    val bitmap = imageFromArr(photo)
                    onUpdateFile(uiState.file?.copy(bitmap = bitmap))
                }
            }
        )
    }

    if (singleCircleDialog) {
        XYTimeDialog(uiState,
            onDismiss = { singleCircleDialog = false },
            onDone = { x, y, time, maxX, maxY ->
                coroutineScope.launch(Dispatchers.Default) {
                    val photo = generateTimeDependentCircle(maxY, maxX, x, y, time)
                    val bitmap = imageFromArr(photo)
                    onUpdateFile(uiState.file?.copy(bitmap = bitmap))
                }
            })
    }

    if (singleSquareDialog) {
        XYTimeDialog(uiState,
            onDismiss = { singleSquareDialog = false },
            onDone = { x, y, time, maxX, maxY ->
                coroutineScope.launch(Dispatchers.Default) {
                    val photo = generateTimeDependentSquare(maxY, maxX, x, y, time)
                    val bitmap = imageFromArr(photo)
                    onUpdateFile(uiState.file?.copy(bitmap = bitmap))
                }
            })
    }

    if (chessDialog) {
        ChessDialog(onDismiss = { chessDialog = false },
            onDone = { cellsInRow, cellSize ->
                coroutineScope.launch(Dispatchers.Default) {
                    val photo = generateChessboardImage(cellsInRow, cellSize)
                    val bitmap = imageFromArr(photo)
                    onUpdateFile(uiState.file?.copy(bitmap = bitmap))
                }
            }
        )
    }

    Column {
        Row(modifier = Modifier.padding(4.dp), verticalAlignment = Alignment.CenterVertically) {

            Button(onClick = {
                coroutineScope.launch(Dispatchers.Default) {
                    delay(200)
                    onSelectFile()
                }
            }) {
                Text("Открыть файл")
            }


            AnimatedContent(uiState.file) {
                val color by animateColorAsState(if (uiState.isGrey) Color.Unspecified else MaterialTheme.colors.primary)
                it?.let {
                    IconButton(onClick = {
                        onGrayChanged(uiState.isGrey.not())
                    }) {
                        Icon(Icons.Default.Palette, color = color)
                    }
                }
            }


            IconButton(onClick = {
                if (uiState.file != null) {
                    buildPanelVisible = !buildPanelVisible
                }
            }) {
                Icon(Icons.Default.Build)
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.Edit)
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Settings)
            }
            IconButton(onClick = {}) {
                Icon(Icons.Default.Info)
            }
        }
        AnimatedVisibility(buildPanelVisible) {
            if (buildPanelVisible) {
                LazyRow(
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    item {
                        Button(onClick = { delayedSingleImpulseDialog = true }) {
                            Text("Задержанный единичный импульс")
                        }
                    }
                    item {
                        Button(onClick = { delayedSingleStepDialog = true }) {
                            Text("Задержанный единичный скачок")
                        }
                    }
                    item {
                        Button(onClick = { singleCircleDialog = true }) {
                            Text("Одиночный круг")
                        }
                    }
                    item {
                        Button(onClick = { singleSquareDialog = true }) {
                            Text("Одиночный квадрат")
                        }
                    }
                    item {
                        Button(onClick = { chessDialog = true }) {
                            Text("Шахматная доска")
                        }
                    }
                }
            }
        }
    }
}