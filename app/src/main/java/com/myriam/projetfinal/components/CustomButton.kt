package com.myriam.projetfinal.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myriam.projetfinal.ui.theme.PrimaryColorGreen
import com.myriam.projetfinal.ui.theme.ProjetFinalTheme

enum class ButtonVariant {
    Default, Destructive, Outline, Secondary
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    variant: ButtonVariant = ButtonVariant.Default,
    height: Int = 40,
    width: Int = 300,
    label: String? = null,
    icon: @Composable (() -> Unit)? = null
) {
    ProjetFinalTheme {


        val colors = when (variant) {
            ButtonVariant.Default -> ButtonDefaults.buttonColors(
                containerColor = PrimaryColorGreen,
                contentColor = Color.White
            )

            ButtonVariant.Destructive -> ButtonDefaults.buttonColors(
                containerColor = PrimaryColorGreen,
                contentColor = Color.White
            )

            ButtonVariant.Outline -> ButtonDefaults.outlinedButtonColors(
                contentColor = PrimaryColorGreen,
                containerColor = Color.White
            )

            ButtonVariant.Secondary -> ButtonDefaults.buttonColors(
                containerColor = PrimaryColorGreen,
                contentColor = Color.White
            )
        }
        val shape = RoundedCornerShape(6.dp)
        val borderModifier = if (variant == ButtonVariant.Outline) {
            Modifier.border(
                width = 2.dp,
                color = PrimaryColorGreen,
                shape = shape
            )
        } else {
            Modifier
        }

        androidx.compose.material3.Button(
            onClick = onClick,
            colors = colors,
            modifier = modifier
                .then(borderModifier)
                .size(height = height.dp, width = width.dp),
            content = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icon != null) {
                        icon()
                    }
                    if (icon != null && label != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    if (label != null) {
                        Text(text = label)
                    }
                }
            },
            shape = shape
        )
    }
}