package com.bilab.lunsenluandroid.main.setting

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.bilab.lunsenluandroid.R
import com.bilab.lunsenluandroid.conf.Constant
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GeneRegisterScreen(
    onNavigateUp: () -> Unit,
) {
    val context = LocalContext.current
    var otx1 by remember { mutableStateOf("") }
    var znf154 by remember { mutableStateOf("") }
    var zic4 by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gene Register") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrow_white),
                            contentDescription = "Previous"
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GeneTextField(value = otx1, onValueChange = { otx1 = it }, label = "OTX1")
            GeneTextField(value = znf154, onValueChange = { znf154 = it }, label = "ZNF154")
            GeneTextField(value = zic4, onValueChange = { zic4 = it }, label = "ZIC4")

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    val intent = Intent(context, DNAmChartActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("跳過")
                }

                Button(onClick = {
                    val isValid = isValid(otx1) && isValid(znf154) && isValid(zic4)
                    if (isValid) {
                        val intent = Intent(context, DNAmChartActivity::class.java).apply {
                            putExtra(Constant.OTX1, otx1.toDouble())
                            putExtra(Constant.ZNF154, znf154.toDouble())
                            putExtra(Constant.ZIC4, zic4.toDouble())
                        }
                        context.startActivity(intent)
                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "請填寫完整資料或選擇跳過"
                            )
                        }
                    }
                }) {
                    Text("下一步")
                }
            }
        }
    }
}

@Composable
fun GeneTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}

private fun isValid(input: String): Boolean {
    return input.toDoubleOrNull() != null
}
