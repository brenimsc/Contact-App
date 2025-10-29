package com.example.contactapp.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import com.example.contactapp.R

@Composable
fun AsyncImagePerfil(urlImage: String, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        model = urlImage.ifEmpty { null } ?: ImageRequest.Builder(LocalContext.current)
            .data(urlImage).build(),
        placeholder = painterResource(R.drawable.default_profile_picture),
        error = painterResource(R.drawable.default_profile_picture),
        contentDescription = stringResource(R.string.foto_perfil_contato),
    )
}