package com.migusdn.videotoimg.utility;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ImageUtilityTest {

    @Test
    void isSamePage() throws IOException {
        BufferedImage img1 = ImageIO.read(new File("/Users/hyunwoopark/Desktop/PPTExtractFromViedo/temp/temp_337.png"));
        BufferedImage img2 = ImageIO.read(new File("/Users/hyunwoopark/Desktop/PPTExtractFromViedo/temp/temp_315.png"));
        boolean result = ImageUtility.compareImages(img1,img2);
        assertEquals(true,result);
    }
}