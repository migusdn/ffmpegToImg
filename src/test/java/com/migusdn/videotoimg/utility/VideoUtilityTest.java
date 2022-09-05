package com.migusdn.videotoimg.utility;


import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

class VideoUtilityTest {
    @Test
    void getAbsolutePath(){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(String.format("ffmpeg -i %s -r 0.3 %s","videoPath","tempPath"));
    }

    @Test
    void getImagesTest() throws InterruptedException {
        VideoUtility videoUtility = new VideoUtility("/Users/hyunwoopark/Desktop/test.mp4");

        File[] temp = videoUtility.getImages();
        Arrays.sort(temp, Comparator.comparingLong(File::lastModified).reversed());
        for(File f:temp){
            System.out.println(f.getName());
        }
    }
}