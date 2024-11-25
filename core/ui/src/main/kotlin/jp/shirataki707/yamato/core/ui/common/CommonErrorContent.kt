package jp.shirataki707.yamato.core.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.shirataki707.core.ui.R
import jp.shirataki707.yamato.core.designsystem.component.YamatoButton

// TODO: Refactor Design
@Composable
fun CommonErrorContent(
    onReloadButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.yamato_icon),
            contentDescription = null,
            modifier = Modifier.size(150.dp),
        )
        YamatoButton(
            text = {
                Text(text = "再読み込み")
            },
            onClick = onReloadButtonClick,
            modifier = Modifier.padding(top = 16.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CommonErrorContentPreview() {
    CommonErrorContent(
        onReloadButtonClick = {},
        modifier = Modifier.fillMaxSize(),
    )
}
