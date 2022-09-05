package com.migusdn.videotoimg.utility;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class that verifies that both input images are the same.
 *
 * @date 2022-09-01
 * @author hyunwoopark
 * @version 1.0
 */

public class ImageUtility {
    /**
     * @param img1             Image to compare
     * @param img2             Image to compare
     * @param startWidth       Width of the starting position.
     * @param startHeight      Height of the starting position.
     * @param endWidth         Width of the ending position.
     * @param endHeight        Height of the ending position.
     * @param differentPercent Difference Comparison Percentage
     * @return Returns true if image 1 is equal to image 2, otherwise false.
     */
    public static boolean compareImages(BufferedImage img1, BufferedImage img2, int startWidth, int startHeight, int endWidth, int endHeight, double differentPercent) {
        System.out.println("Comparing Both Img");
        long diff = 0;
        for (int j = startHeight; j < endHeight; j++) {
            for (int i = startWidth; i < endWidth; i++) {
                int pixel1 = img1.getRGB(i, j);
                Color color1 = new Color(pixel1, true);
                int red1 = color1.getRed();
                int green1 = color1.getGreen();
                int blue1 = color1.getBlue();
                int pixel2 = img2.getRGB(i, j);
                Color color2 = new Color(pixel2, true);
                int red2 = color2.getRed();
                int green2 = color2.getGreen();
                int blue2 = color2.getBlue();
                long data = Math.abs(red1 - red2) + Math.abs(green1 - green2) + Math.abs(blue1 - blue2);
                diff = diff + data;
            }
        }
        double average = diff / (endWidth - startWidth * endHeight - startHeight * 3);
        double percentage = (average / 255) * 100;
        System.out.println("Difference: " + percentage);
        if (percentage < differentPercent) {
            return true;
        }
        return false;
    }

    public static boolean compareImages(BufferedImage img1, BufferedImage img2, int startWidth, int startHeight) {
        return compareImages(img1,img2,startWidth,startHeight,img1.getWidth(),img1.getHeight(),5.0);
    }

    public static boolean compareImages(BufferedImage img1, BufferedImage img2) {
        return compareImages(img1,img2,0,0, img1.getWidth(),img1.getHeight(), 5.0);
    }
}
