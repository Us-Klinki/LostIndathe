package main;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class UtilityToolTiles {
    // Method to scale the image using VolatileImage
    private VolatileImage scaleImage(BufferedImage original, int width, int height) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
        
        // Create a VolatileImage
        VolatileImage scaledImage = gc.createCompatibleVolatileImage(width, height);
        Graphics2D g2 = scaledImage.createGraphics();
        
        do {
            // Check if the volatile image needs to be validated or recreated
            int validationCode = scaledImage.validate(gc);
            
            if (validationCode == VolatileImage.IMAGE_INCOMPATIBLE) {
                // Recreate the image if it's incompatible
                scaledImage.flush();
                scaledImage = gc.createCompatibleVolatileImage(width, height);
                g2 = scaledImage.createGraphics();
            }
            
            // Draw the scaled image
            g2.drawImage(original, 0, 0, width, height, null);
            
        } while (scaledImage.contentsLost()); // Ensure the content isn't lost
        
        g2.dispose();
        
        return scaledImage;
    }
    
    // Method to return a scaled BufferedImage using the scaleImage method
    public VolatileImage getScaledImage(BufferedImage image, int width, int height) {
        return scaleImage(image, width, height);
    }
}

