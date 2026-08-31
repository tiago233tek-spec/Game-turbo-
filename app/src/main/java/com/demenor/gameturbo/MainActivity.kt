package com.demenor.gameturbo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { GameTurboApp() }
    }
}

@Composable
fun GameTurboApp() {
    var performance by remember { mutableStateOf(true) }
    var dnd by remember { mutableStateOf(false) }
    var touch by remember { mutableStateOf(true) }
    var network by remember { mutableStateOf(true) }

    MaterialTheme(
        colorScheme = darkColorScheme(
            background = Color(0xFF07090D),
            surface = Color(0xFF11151C),
            primary = Color(0xFF00E5FF)
        )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(Color(0xFF07090D)),
            contentPadding = PaddingValues(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text("DEMENOR", color = Color(0xFF00E5FF), fontSize = 14.sp,
                    fontWeight = FontWeight.Bold)
                Text("GAME TURBO", color = Color.White, fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold)
                Text("Painel de desempenho", color = Color.Gray)
            }

            item {
                Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF11151C)),
                    shape = RoundedCornerShape(22.dp)) {
                    Column(Modifier.padding(20.dp)) {
                        Text("MODO DESEMPENHO", color = Color.White,
                            fontSize = 19.sp, fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(12.dp))
                        Text(if (performance) "ATIVO • jogos priorizados"
                             else "DESATIVADO",
                            color = if (performance) Color(0xFF00E5FF) else Color.Gray)
                        Switch(checked = performance,
                            onCheckedChange = { performance = it })
                    }
                }
            }

            item { SectionTitle("MONITORAMENTO") }
            item { StatCard("FPS", "Monitor disponível durante a sessão") }
            item { StatCard("REDE", if (network) "Monitoramento ativado" else "Desativado") }
            item { StatCard("MEMÓRIA", "Uso do sistema • informações permitidas pelo Android") }

            item { SectionTitle("ATALHOS DE JOGO") }
            item { ToggleRow("Bloquear notificações", dnd) { dnd = it } }
            item { ToggleRow("Proteção contra toques acidentais", touch) { touch = it } }
            item { ToggleRow("Monitorar conexão", network) { network = it } }

            item {
                Button(
                    onClick = { },
                    modifier = Modifier.fillMaxWidth().height(54.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("ABRIR PAINEL DE JOGO", fontWeight = FontWeight.Bold)
                }
            }

            item {
                Text(
                    "Alguns recursos dependem das permissões e APIs disponíveis na versão do Android e no fabricante do aparelho.",
                    color = Color.Gray, fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun SectionTitle(text: String) {
    Text(text, color = Color.White, fontSize = 14.sp,
        fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 4.dp))
}

@Composable
fun StatCard(title: String, value: String) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF11151C)),
        shape = RoundedCornerShape(18.dp)) {
        Column(Modifier.padding(18.dp).fillMaxWidth()) {
            Text(title, color = Color(0xFF00E5FF), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(5.dp))
            Text(value, color = Color.LightGray, fontSize = 13.sp)
        }
    }
}

@Composable
fun ToggleRow(title: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFF11151C)),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.fillMaxWidth()) {
        Row(Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically) {
            Text(title, color = Color.White, modifier = Modifier.weight(1f))
            Switch(checked = checked, onCheckedChange = onChange)
        }
    }
}
