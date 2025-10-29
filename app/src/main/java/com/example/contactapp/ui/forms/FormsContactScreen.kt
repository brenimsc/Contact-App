package com.example.contactapp.ui.forms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.contactapp.R
import com.example.contactapp.ui.components.DialogData
import com.example.contactapp.ui.components.DialogImage
import com.example.contactapp.ui.theme.ContactAppTheme

@Composable
fun FormsContactScreen(
    state: FormsContactUiState,
    modifier: Modifier = Modifier,
    onClickSave: () -> Unit = {},
    onLoadImage: (String) -> Unit = {}
) {
    Scaffold(
        topBar = {
            state.titleAppBar?.let { title ->
                FormsContactAppBar(stringResource(id = title))
            }
        },

        ) { paddingValues ->

        Column(
            modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .clickable {
                            state.onShowDialogImageChange(true)
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.photoPerfil).build(),
                    placeholder = painterResource(R.drawable.default_profile_picture),
                    error = painterResource(R.drawable.default_profile_picture),
                    contentScale = ContentScale.Crop,
                    contentDescription = stringResource(id = R.string.foto_perfil_contato),
                )
                Text(
                    text = stringResource(R.string.adicionar_foto),
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Column(
                Modifier
                    .padding(horizontal = 16.dp)
                    .weight(2f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val focus = LocalFocusManager.current
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    },
                    value = state.name,
                    onValueChange = state.onNameChange,
                    label = { Text(stringResource(id = R.string.nome)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focus.moveFocus(FocusDirection.Next) }))
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = state.lastname,
                    onValueChange = state.onLastnameChange,
                    label = { Text(stringResource(id = R.string.sobrenome)) },
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focus.moveFocus(FocusDirection.Next) }))
                )

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = null
                        )
                    },
                    value = state.phone,
                    onValueChange = state.onPhoneChange,
                    label = { Text(stringResource(id = R.string.telefone)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = (KeyboardActions(onNext = { focus.clearFocus() }))
                )

                OutlinedButton(
                    onClick = { state.onShowDialogDateChange(true) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)

                ) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        Modifier.padding(8.dp)
                    )
                    Text(text = state.textBirthday)
                }


                Spacer(Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(56.dp),
                    onClick = onClickSave
                ) {
                    Text(text = stringResource(R.string.salvar))
                }
            }

            if (state.showDialogImage) {
                DialogImage(
                    state.photoPerfil,
                    onPhotoChange = state.onPhotoPerfilChange,
                    onClickDismiss = { state.onShowDialogImageChange(false) },
                    onClickSave = { onLoadImage(it) }
                )
            }

            if (state.showDialogDate) {
                DialogData(
                    LocalContext.current,
                    currentDate = state.birthday,
                    onClickDismiss = { state.onShowDialogDateChange(false) },
                    onClickDateSelected = state.onBirthdayChange
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormsContactAppBar(titleAppBar: String) {
    TopAppBar(
        title = { Text(text = titleAppBar) },
    )
}

@Preview
@Composable
fun FormsContactTelaPreview() {
    ContactAppTheme {
        FormsContactScreen(
            state = FormsContactUiState()
        )
    }
}