package ronell.glancetiledemo.tile

import android.graphics.Color
import androidx.concurrent.futures.ResolvableFuture
import androidx.wear.protolayout.ColorBuilders.ColorProp
import androidx.wear.protolayout.DimensionBuilders
import androidx.wear.protolayout.LayoutElementBuilders
import androidx.wear.protolayout.ModifiersBuilders
import androidx.wear.protolayout.ResourceBuilders
import androidx.wear.protolayout.TimelineBuilders
import androidx.wear.tiles.RequestBuilders.ResourcesRequest
import androidx.wear.tiles.RequestBuilders.TileRequest
import androidx.wear.tiles.TileBuilders
import androidx.wear.tiles.TileService
import com.google.common.util.concurrent.ListenableFuture
import ronell.glancetiledemo.R

class LinkTileService : TileService() {

    override fun onTileRequest(requestParams: TileRequest): ListenableFuture<TileBuilders.Tile> {
        val deviceParams = requestParams.deviceConfiguration
        val qrSizeDp = (minOf(deviceParams.screenWidthDp, deviceParams.screenHeightDp) * QR_FRACTION).toInt()

        val layout = LayoutElementBuilders.Box.Builder()
            .setWidth(DimensionBuilders.expand())
            .setHeight(DimensionBuilders.expand())
            .setHorizontalAlignment(LayoutElementBuilders.HORIZONTAL_ALIGN_CENTER)
            .setVerticalAlignment(LayoutElementBuilders.VERTICAL_ALIGN_CENTER)
            .setModifiers(
                ModifiersBuilders.Modifiers.Builder()
                    .setBackground(
                        ModifiersBuilders.Background.Builder()
                            .setColor(ColorProp.Builder().setArgb(Color.BLACK).build())
                            .build()
                    )
                    .build()
            )
            .addContent(
                LayoutElementBuilders.Image.Builder()
                    .setResourceId(IMAGE_QR)
                    .setWidth(DimensionBuilders.dp(qrSizeDp.toFloat()))
                    .setHeight(DimensionBuilders.dp(qrSizeDp.toFloat()))
                    .build()
            )
            .build()

        val tile = TileBuilders.Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setTileTimeline(TimelineBuilders.Timeline.fromLayoutElement(layout))
            .build()
        return ResolvableFuture.create<TileBuilders.Tile>().apply { set(tile) }
    }

    override fun onTileResourcesRequest(requestParams: ResourcesRequest): ListenableFuture<ResourceBuilders.Resources> {
        val resources = ResourceBuilders.Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .addIdToImageMapping(IMAGE_QR, imageResource(R.drawable.qr_code))
            .build()
        return ResolvableFuture.create<ResourceBuilders.Resources>().apply { set(resources) }
    }

    companion object {
        private const val RESOURCES_VERSION = "1"
        private const val IMAGE_QR = "qr"
        private const val QR_FRACTION = 0.65f
    }
}

private fun imageResource(resId: Int): ResourceBuilders.ImageResource =
    ResourceBuilders.ImageResource.Builder()
        .setAndroidResourceByResId(
            ResourceBuilders.AndroidImageResourceByResId.Builder()
                .setResourceId(resId)
                .build()
        )
        .build()
