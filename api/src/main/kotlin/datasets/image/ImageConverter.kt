package datasets.image

import datasets.Dataset
import java.awt.image.BufferedImage
import java.awt.image.DataBufferByte
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO

public class ImageConverter() {
    public companion object {
        /** All pixels has values in range [0; 255]. */
        public fun toRawFloatArray(inputStream: InputStream, imageType: ImageType = ImageType.JPG): FloatArray {
            return Dataset.toRawVector(
                toRawPixels(inputStream)
            )
        }

        /** All pixels has values in range [0; 255]. */
        public fun toRawFloatArray(imageFile: File, imageType: ImageType = ImageType.JPG): FloatArray {
            return Dataset.toRawVector(
                toRawPixels(imageFile.inputStream())
            )
        }

        /** All pixels in range [0;1) */
        public fun toNormalizedFloatArray(inputStream: InputStream, imageType: ImageType = ImageType.JPG): FloatArray {
            return Dataset.toNormalizedVector(
                toRawPixels(inputStream)
            )
        }

        /** All pixels in range [0;1) */
        public fun toNormalizedFloatArray(imageFile: File, imageType: ImageType = ImageType.JPG): FloatArray {
            return Dataset.toNormalizedVector(
                toRawPixels(imageFile.inputStream())
            )
        }

        private fun toRawPixels(inputStream: InputStream): ByteArray {
            val image = getImage(inputStream)

            val pixels = (image.raster.dataBuffer as DataBufferByte).data

            return pixels
        }

        @Throws(IOException::class)
        public fun getImage(inputStream: InputStream, imageType: String = "png"): BufferedImage {
            val image = ImageIO.read(inputStream)
            val baos = ByteArrayOutputStream()
            ImageIO.write(image, imageType, baos)
            return image
        }
    }
}

public enum class ImageType {
    JPG,
    PNG,
    GIF
}