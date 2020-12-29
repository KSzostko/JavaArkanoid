package com.company;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ImgUtils {
    public static Image getImage(String filepath) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(filepath));
        } catch (IOException ex) {
            Logger.getLogger(ImgUtils.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return img;
    }
}
