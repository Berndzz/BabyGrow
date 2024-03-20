package com.hardus.babygrow.main_content.home.laporanBayi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hardus.babygrow.R
import com.hardus.babygrow.util.components.CustomDetailTopAppBar
import com.hardus.babygrow.util.components.DatePickerForm
import com.hardus.babygrow.util.components.MyTextField
import com.hardus.babygrow.util.components.OptionalTextArea

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FormLaporanBayiScreen(
    navController: NavController,
    onHome: () -> Unit,
    viewModel:ViewModelLaporanBayi,
    userId: String?
) {
    val (focusName) = remember { FocusRequester.createRefs() }

    // Inisialisasi data jika userId tidak null (operasi edit)
    if (userId != null) {
        LaunchedEffect(userId) {
            viewModel.loadLaporanBayiForUserId(userId)
        }
    }

    Spacer(modifier = Modifier.padding(5.dp))
    Scaffold(
        topBar = {
            CustomDetailTopAppBar(
                navController = navController,
                title = "Form Laporan\nBerat Badan Bayi",
                modifier = Modifier.padding(5.dp)
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 10.dp, end = 10.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = "*sebagai pengingat dalam memantau tumbuh kembang bayi anda",
                        color = MaterialTheme.colorScheme.outline
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    MyTextField(
                        text = viewModel.onFullNameResponse,
                        labelValue = stringResource(id = R.string.name_baby),
                        imageVector = Icons.Outlined.Person,
                        onTextSelected = viewModel::onFullNameChange,
                        errorStatus = true,
                        focusName,
                        keyboardOptions = KeyboardOptions.Default,
                        keyboardActions = KeyboardActions.Default,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    DatePickerForm(
                        label = "Tanggal Lahir Bayi anda",
                        date = viewModel.onDateOfBirth,
                        onDateChanged = viewModel::onDateOfBirthChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .height(54.dp)
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        MyTextField(
                            text = viewModel.onWeightBabyResponse,
                            labelValue = stringResource(id = R.string.berat_badan),
                            imageVector = Icons.Outlined.MonitorWeight,
                            onTextSelected = viewModel::onWeightChange,
                            errorStatus = true,
                            focusName,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            keyboardActions = KeyboardActions.Default,
                            modifier = Modifier
                                .weight(1f)
                                .height(70.dp)
                        )
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(
                            text = "KG",
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    OptionalTextArea(
                        optionalText = viewModel.onOptionalData,
                        onOptionalText = viewModel::onOptionalDataChanged,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                    )
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp),
                        shape = RoundedCornerShape(6.dp),
                        onClick = {
                            viewModel.onDonePressed {
                                onHome()
                            }
                        }) {
                        Text(text = "Simpan Data", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    }
                }
            }
        }
    )
}