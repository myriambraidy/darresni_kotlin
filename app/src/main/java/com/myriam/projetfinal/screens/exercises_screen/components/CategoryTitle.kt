package com.myriam.projetfinal.screens.exercises_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.myriam.projetfinal.ui.theme.PrimaryColorGreen
import com.myriam.projetfinal.ui.theme.ProjetFinalTheme

@Composable
fun CategoryTitle(
    title: String,
    onViewMoreClick: () -> Unit
) {
    ProjetFinalTheme {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )

            Text(
                text = "view more",
                style = MaterialTheme.typography.bodyMedium.copy(color = PrimaryColorGreen),
                modifier = Modifier.clickable { onViewMoreClick() }
            )
        }

        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFF363333),
            thickness = 2.dp
        )
    }
}
