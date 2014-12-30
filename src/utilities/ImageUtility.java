package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Read image
 * @author Qing
 *
 */
public class ImageUtility {
	
	public static BufferedImage getImage(String imagePath) {
    	try {
    		//read image
    		return ImageIO.read(new File(imagePath));
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    		return null;
    	}
	}
	
}
