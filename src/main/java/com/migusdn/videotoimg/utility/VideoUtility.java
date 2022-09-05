package com.migusdn.videotoimg.utility;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;


/**
 * A utility that extract image from video using ffmpeg
 * @date 2022-09-01
 * @author hyunwoopark
 * @version 1.0
 */
public class VideoUtility {
    private String tempPath;
    private String command;

    public VideoUtility(String videoPath){
        this.tempPath = System.getProperty("user.dir")+"/temp";
        this.command = String.format("ffmpeg -i %s -r 0.3 %s/temp_%%d.png",videoPath,tempPath);
    }
    public File[] getImages() throws InterruptedException {
        createTempFolder();
        Runtime runtime = Runtime.getRuntime();
        Process pc = null;
        try{
            pc = runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pc.waitFor();
            pc.destroy();
            File dir = new File(tempPath);
            File[] result = dir.listFiles();
            Arrays.sort(result, Comparator.comparingLong(File::lastModified));
            return result;
        }
    }
    private void createTempFolder(){
        File dir = new File(tempPath);
        if(dir.exists()){
            for(String fileName: dir.list()){
                File currentFile = new File(tempPath,fileName);
                currentFile.delete();
            }
        }else{
            dir.mkdir();
        }
    }

}
