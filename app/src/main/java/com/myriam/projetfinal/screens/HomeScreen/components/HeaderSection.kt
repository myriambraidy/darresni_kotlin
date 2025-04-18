import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.filled.Close


@Composable
fun HeaderSection(
    title: String,
    count: Int? = null,
    icon: ImageVector? = null,
    showBack: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    onIconClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Optional back arrow
                if (showBack && onBackClick != null) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }

                Text(
                    text = title,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

            if (count != null || icon != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    icon?.let {
                        IconButton(onClick = { onIconClick?.invoke() }) {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    count?.let {
                        Text(
                            text = it.toString(),
                            color = Color.Black,
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp
        )
    }
}
