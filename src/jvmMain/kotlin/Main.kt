import androidx.compose.animation.AnimatedContent
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import components.imageFromFile
import model.SelectedFile
import model.UiState
import java.io.File

@Composable
@Preview
fun App(onTitleChange: (String) -> Unit) {
    var uiState by remember {
        mutableStateOf(UiState())
    }
    val coroutineScope = rememberCoroutineScope()

    var showFilePicker by remember { mutableStateOf(false) }

    val fileType = listOf("jpg", "png")
    FilePicker(showFilePicker, fileExtensions = fileType) { file ->
        showFilePicker = false
        println(file)
        file?.path?.let {
            val bitmap = imageFromFile(File(it))
            uiState = uiState.copy(
                file = SelectedFile(
                    path = it,
                    bitmap = bitmap,
                ),
                isGrey = false
            )
            onTitleChange(it)
        }
    }

    MaterialTheme {
        Column {
            MenuRow(uiState,
                onGrayChanged = { uiState = uiState.copy(isGrey = it) },
                onUpdateFile = { file ->
                    file?.let {
                        uiState = uiState.copy(file = file)
                    }
                },
                onSelectFile = { showFilePicker = true })

            val colorMatrix = ColorMatrix().apply {
                setToSaturation(0f) // Set the saturation to 0 to make it grayscale
            }
            val colorFilter = ColorFilter.colorMatrix(colorMatrix)

            AnimatedContent(uiState.file) {
                it?.let {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Image(
                            bitmap = it.bitmap,
                            "",
                            colorFilter = colorFilter.takeIf { uiState.isGrey },
                            modifier = Modifier.sizeIn(minWidth = 500.dp, minHeight = 500.dp),
                            contentScale = ContentScale.Fit
                            )
                    }
                }
            }
        }
    }
}

fun main() = application {
    nu.pattern.OpenCV.loadShared()

    val state = rememberWindowState(
        placement = WindowPlacement.Floating,
        position = WindowPosition(Alignment.Center),
        isMinimized = false,
        width = 800.dp,
        height = 600.dp
    )


    var windowTitle by remember { mutableStateOf("Machine Vision") }
    Window(
        title = windowTitle, resizable = true, state = state,
//        icon = painterResource("drawable/logo.png"),
        onCloseRequest = ::exitApplication
    ) {
        App(
            onTitleChange = {
                windowTitle = it
            }
        )
    }
}


