package com.migusdn.videotoimg.main;


import com.migusdn.videotoimg.utility.ImageUtility;
import com.migusdn.videotoimg.utility.VideoUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        PDDocument document = new PDDocument();

        String videoPath;
//        String currentTempPath = System.getProperty("user.dir") + "/temp/";
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your lecture video file");
        videoPath = sc.nextLine();
        VideoUtility videoUtility = new VideoUtility(videoPath);
        File[] imageFileArray = videoUtility.getImages();
        BufferedImage firstImage = null;
        File firstImageFile = null;
        boolean firstImageFlag = true;
        for (File imageFile : imageFileArray) {
            BufferedImage temp = ImageIO.read(imageFile);
            if (firstImageFile == null) {
                firstImageFile = imageFile;
                firstImage = temp;
                continue;
            } else {
                System.out.println("compare image: "+firstImageFile.getName()+", "+imageFile.getName());
                if (ImageUtility.compareImages(firstImage, temp)) {
                    if (firstImageFlag) {
                        firstImageFile.delete();
                        firstImageFile = imageFile;
                        firstImage = temp;
                        firstImageFlag = false;
                        PDPage page = new PDPage(new PDRectangle(firstImage.getWidth(),firstImage.getHeight()));
                        PDImageXObject pdImage = PDImageXObject.createFromFile(firstImageFile.getAbsolutePath(),document);
                        PDPageContentStream contents = new PDPageContentStream(document,page);
                        contents.drawImage(pdImage,0,0);
                        contents.close();
                        document.addPage(page);
                    } else {
                        imageFile.delete();
                    }
                } else {
                    firstImageFile = imageFile;
                    firstImage = temp;
                    firstImageFlag = true;
                }
            }
        }
        document.save(System.getProperty("user.dir")+"/result1.pdf");
    }
}
