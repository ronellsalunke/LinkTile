package ronell.glancetiledemo.tile


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalSize
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.ContentScale
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.Text
import androidx.glance.wear.tiles.GlanceTileService
import ronell.glancetiledemo.R


class LinkTileService : GlanceTileService() {

    @Composable
    override fun Content() {
        val imageSize = LocalSize.current.times(0.65f)
        Column(
            modifier = GlanceModifier.fillMaxSize().background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "My",
                )
                Spacer(GlanceModifier.width(6.dp))
                Image(
                    provider = ImageProvider(R.drawable.linkedin_logo),
                    modifier = GlanceModifier.size(16.dp),
                    contentDescription = "LinkedIn Logo"
                )
            }

            Spacer(GlanceModifier.height(6.dp))

            Image(
                provider = ImageProvider(R.drawable.linkedin_qr),
                modifier = GlanceModifier.size(imageSize.width, imageSize.height),
                contentScale = ContentScale.Fit,
                contentDescription = "LinkedIn Tile"
            )
        }
    }
}

