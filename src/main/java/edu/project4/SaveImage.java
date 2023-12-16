package edu.project4;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveImage {

    private static final int COLOR_CHANEL_COUNT = 3;

    private final static Logger LOGGER = LogManager.getLogger();

    private SaveImage() {}

    public static void save(FractalImage fractal, Path filePath, String format) {
        byte[] bytePixelArray = new byte[fractal.getScreenWidth() * fractal.getScreenHeight() * COLOR_CHANEL_COUNT];
        int byteIndex = 0;
        for (List<Pixel> raw: fractal.getMatrix()) {
            for (Pixel elem: raw) {
                bytePixelArray[byteIndex] = elem.bChanel;
                byteIndex++;
                bytePixelArray[byteIndex] = elem.gChanel;
                byteIndex++;
                bytePixelArray[byteIndex] = elem.rChanel;
                byteIndex++;
            }
        }

        try {
            BufferedImage image = new BufferedImage(fractal.getScreenWidth(), fractal.getScreenHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
            image.setData(Raster.createRaster(image.getSampleModel(), new DataBufferByte(bytePixelArray,
                bytePixelArray.length), new Point()));

            ImageIO.write(image, format, filePath.toFile());
        } catch (IOException error) {
            LOGGER.info("Saving error!");
        }
    }
}
