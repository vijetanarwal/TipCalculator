package com.example.tipcalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*


@Composable
fun TipCalculatorScreen(modifier: Modifier = Modifier) {
    billAmountInput()
}

@Composable
fun billAmountInput(modifier: Modifier = Modifier) {
    val billAmount = remember { mutableStateOf("") }
    val bill = billAmount.value.toDoubleOrNull()?: 0.0
    Column (modifier = Modifier.fillMaxSize()){
        Text("Tip Calculator", fontSize = 40.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(50.dp))
        val composition by rememberLottieComposition(LottieCompositionSpec.Asset("Money.json"))
        val progress by animateLottieCompositionAsState(composition)
        LottieAnimation(
            composition = composition,
            progress = progress,
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text("Enter bill amount",Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Medium)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            modifier = Modifier.fillMaxWidth().padding(15.dp),
            value = billAmount.value,
            onValueChange = {billAmount.value = it},
            label = {(Text("Enter bill amount"))},
            singleLine = true
        )
        Spacer(modifier = Modifier.height(20.dp))
        val tipPercent = remember { mutableStateOf(15f) }
        Text("Tip: ${tipPercent.value.toInt()}%",Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Slider(
            value = tipPercent.value,
            onValueChange = {tipPercent.value = it},
            valueRange = 0f..100f,
        )
        Spacer(modifier = Modifier.height(50.dp))
        val tipAmount = (tipPercent.value/100)* bill
        val totalAmount = bill+tipAmount
        Card(modifier = Modifier.fillMaxWidth().padding(20.dp), elevation = CardDefaults.cardElevation(5.dp)) { Column(modifier = Modifier.padding(10.dp)) { Text("TipAmount: $tipAmount", fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(30.dp))
            Text("TotalAmount: $totalAmount",fontSize = 30.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) }}
    }
}

@Composable
@Preview(showBackground = true)
fun TipCalculatorScreenPreview(modifier: Modifier = Modifier) {
    TipCalculatorScreen()
}