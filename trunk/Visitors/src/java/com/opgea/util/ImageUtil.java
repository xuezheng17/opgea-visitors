/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opgea.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Ramesh
 */
public class ImageUtil {
    
    
    public void createImage(InputStream is, String fileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(is);
            ImageIO.write(image, "jpg",new File(fileName));
        } catch (IOException e) {
            System.out.println("Could not create image file");
        }
    }
}
