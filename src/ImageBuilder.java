import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;


public class ImageBuilder {
    public static String generate(String fileName, int[][] rgbs, int width, int height) throws Exception {
        int x, y;

        BufferedImage bi = new BufferedImage(width, height,
                BufferedImage.TYPE_3BYTE_BGR);

        Graphics2D g = (Graphics2D) bi.getGraphics();
        String filename = fileName + "img.jpg";

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                x = j;
                y = i;

                int index = y * width + x;
                int rVal = Math.min(255, Math.max(0, rgbs[index][0]));
                int gVal = Math.min(255, Math.max(0, rgbs[index][1]));
                int bVal = Math.min(255, Math.max(0, rgbs[index][2]));

                g.setColor(new Color(rVal, gVal, bVal));

                //fil the rectangles with the pixel blocks in chosen color
                g.fillRect(x, y, 1, 1);
            }
        }


        g.dispose();

        saveToFile(bi, new File(filename));

        return filename;
    }


    public static void saveToFile(BufferedImage img, File file) throws IOException {
        ImageWriter writer = null;
        java.util.Iterator iter = ImageIO.getImageWritersByFormatName("jpg");

        if (iter.hasNext()) {
            writer = (ImageWriter) iter.next();
        }


        ImageOutputStream ios = ImageIO.createImageOutputStream(file);

        writer.setOutput(ios);


        ImageWriteParam param = new JPEGImageWriteParam(java.util.Locale.getDefault());

        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(0.98f);


        writer.write(null, new IIOImage(img, null, null), param);
    }
}