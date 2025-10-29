package com.example.contactapp.ui.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.contactapp.R
import com.example.contactapp.extensions.FORMAT_DATE_D_M_YYYY
import com.example.contactapp.extensions.formatDateToString
import com.example.contactapp.ui.theme.ContactAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

fun DialogData(
    context: Context,
    currentDate: Date?,
    onClickDismiss: () -> Unit = {},
    onClickDateSelected: (dateSelected: String) -> Unit = {}
) {
    val dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE_D_M_YYYY)
    val dataLocal = if (currentDate == null) LocalDate.now()
    else LocalDate.parse(currentDate.formatDateToString(), dateFormatter)

    val year = dataLocal.year
    val month = dataLocal.monthValue
    val day = dataLocal.dayOfMonth

    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, ano, mes, dia ->
            val dateSelected = LocalDate.parse("$dia/${mes + 1}/$ano", dateFormatter)
            onClickDateSelected(dateSelected.format(dateFormatter))
        }, year, (month - 1), day
    )

    datePickerDialog.setOnDismissListener {
        onClickDismiss()
    }
    datePickerDialog.show()
}

@Composable
fun DialogImage(
    photo: String,
    modifier: Modifier = Modifier,
    onPhotoChange: (String) -> Unit = {},
    onClickDismiss: () -> Unit = {},
    onClickSave: (urlImage: String) -> Unit = {}
) {
    Dialog(
        onDismissRequest = onClickDismiss,
        content = {
            Column(
                modifier
                    .clip(RoundedCornerShape(5))
                    .heightIn(250.dp, 400.dp)
                    .widthIn(200.dp)
                    .background(Color.White)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImagePerfil(
                    urlImage = photo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(5, 5))
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .heightIn(max = 80.dp),
                    value = photo,
                    onValueChange = onPhotoChange,
                    label = { Text(stringResource(id = R.string.link_imagem)) })

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onClickDismiss) {
                        Text(text = stringResource(R.string.cancelar))
                    }
                    TextButton(onClick = { onClickSave(photo) }) {
                        Text(text = stringResource(R.string.salvar))
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun CaixaDialogImagePreview() {
    ContactAppTheme {
        DialogImage("")
    }
}