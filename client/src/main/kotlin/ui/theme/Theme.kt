package ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val CustomColorPalette = lightColors(
    primary = Color(0xFF555555),
    primaryVariant = Color(0xFF949494),
    secondary = Color(0xFF03DAC6),
    background = Color(0xFF1A1B1C),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

val CustomTypography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    // TODO: other text styles
)


@Composable
fun CustomTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = CustomColorPalette,
        typography = CustomTypography,
        content = content
    )
}