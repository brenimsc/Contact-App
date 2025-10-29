package com.example.contactapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.contactapp.R
import com.example.contactapp.data.Contact
import com.example.contactapp.sampledata.contactSample
import com.example.contactapp.ui.components.AsyncImagePerfil
import com.example.contactapp.ui.theme.ContactAppTheme

@Composable
fun ListContactScreen(
    state: ListContactUiState,
    modifier: Modifier = Modifier,
    onClickExit: () -> Unit = {},
    onClickOpenDetails: (Long) -> Unit = {},
    onClickOpenRegister: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            AppBarListContact(state, onClickExit = onClickExit)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onClickOpenRegister() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.adicionar_novo_contato)
                )
            }
        }) { paddingValues ->

        LazyColumn(modifier.padding(paddingValues)) {
            items(state.contacts) { contact ->
                ContactItem(contact) { idContact ->
                    onClickOpenDetails(idContact)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarListContact(
    state: ListContactUiState,
    onClickExit: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.searchText,
                    onValueChange = state.onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            null
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Gray,
                        unfocusedTextColor = Color.Gray,
                        focusedPlaceholderColor = Color.LightGray,
                        unfocusedPlaceholderColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color.Gray,
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color.Gray,
                        unfocusedLabelColor = Color.LightGray,
                    ),
                    shape = RoundedCornerShape(100),
                    placeholder = { Text(text = "Buscar") },
                )
            }
        },
        colors = TopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White,
            actionIconContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(
                onClick = onClickExit
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    tint = Color.White,
                    contentDescription = stringResource(R.string.deslogar)
                )
            }
        }
    )
}

@Composable
fun ContactItem(
    contact: Contact,
    onClick: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .clickable { onClick(contact.id) }
            .background(MaterialTheme.colorScheme.background),
    ) {
        Row(
            Modifier.padding(16.dp),
        ) {
            AsyncImagePerfil(
                urlImage = contact.photo,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Column(
                Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = contact.lastName
                )
            }
        }
    }
}

@Preview
@Composable
fun ListContactsPreview() {
    ContactAppTheme {
        ListContactScreen(
            state = ListContactUiState(contactSample)
        )
    }
}

@Preview
@Composable
fun ContactItemPreview() {
    ContactAppTheme {
        ContactItem(contactSample.first()) {}
    }
}
