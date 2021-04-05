package com.company;

import javax.imageio.ImageIO;
import javax.media.MediaLocator;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class Main {
    static int count = 1;
    static Vector<File> images = new Vector<>();
    public static void main(String[] args) {

        Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                try {
                    Robot robot = new Robot();
                    Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
                    BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
                    File file = new File("C:\\Users\\Andrei\\Documents\\Recordings\\Pictures\\picture" + count + ".jpeg");
                    boolean status = ImageIO.write(bufferedImage, "jpeg", file);
                    images.add(file);

                } catch (AWTException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                count++;
                if(images.size() == 100){
                    timer.cancel();
                }
            }

        };
        timer.schedule(tt, 0,100);
        JpegImagesToMovie jpegImagesToMovie = new JpegImagesToMovie();
        MediaLocator locator = new MediaLocator("C:\\Users\\Andrei\\Documents\\Recordings\\Movies\\Movie.mp4");
        jpegImagesToMovie.doIt(3072, 1920, 10, images, locator);

    }
}
