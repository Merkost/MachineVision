package model

data class UiState(
    val selectedOption: Option? = null,
    val file: SelectedFile? = null,
    val isGrey: Boolean = false,
)

sealed class Option {
    class SingleImpulse(x: Int, y: Int)
    class SingleStep(x: Int, y: Int)
    class SingleCircle(x: Int, y: Int, time: Double)
    class SingleSquare(x: Int, y: Int, time: Double)
}
