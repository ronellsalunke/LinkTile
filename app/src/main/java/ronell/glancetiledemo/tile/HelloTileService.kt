package ronell.glancetiledemo.tile


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.text.Text
import androidx.glance.wear.tiles.GlanceTileService
import ronell.glancetiledemo.R


class HelloTileService : GlanceTileService() {

    @Composable
    override fun Content() {
        val context = LocalContext.current
        val imageSize = LocalSize.current.times(0.6f)
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Ronell's LinkedIn"
            )

            Spacer(GlanceModifier.height(10.dp))

            Image(
                provider = ImageProvider(R.drawable.linkedin_qr),
                modifier = GlanceModifier.size(imageSize.width, imageSize.height),
                contentScale = ContentScale.Fit,
                contentDescription = "LinkedIn Tile"
            )
        }
    }
}