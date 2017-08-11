package com.jellysoft.imgrec;

import javafx.application.Application;
import nu.pattern.OpenCV;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Platform.exit;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgcodecs.Imgcodecs.imwrite;

/**
 * Created by Deltamike76 on 8/11/2017.
 */
public class imgrec {

    private static File cascadeFile;
    private static List<File> imageFiles;
    private static boolean shouldRun;

    public static void main(String[] args){

        //init
        imageFiles = new ArrayList<File>();
        cascadeFile = null;
        shouldRun = false;
        OpenCV.loadLocally();

        // Load image from file
        if(args.length > 0) {

            int i = 0;
            for (String arg : args) {
                if(arg.substring(0,1).equals("-"))
                    command(arg,i, args);
                i++;
            }
        }
        else // if no arguments found, show help
            command("-h", 0, args);


        if(shouldRun) {
            out("**************** Image Recognizer *******************");
            out("");
            for (File f : imageFiles) {
                run(f);
            }
        }

    }

    private static void command(String cmd, int idx, String[] args){

        cmd = cmd.replace("-", "").toUpperCase();
        commands command = commands.valueOf(cmd);

        switch (command) {

            case H:
                out("-scan\t\t => Analyze images, takes images as parameters");
                out("-cascade\t => Supply new cascade file");
                break;
            case SCAN:
                        Scan(args, idx + 1);

                break;

            case CASCADE:
                cascadeFile = new File(args[idx + 1]);
                break;

            default: {
                out("Type -h for Help");
                break;
            }
            //files = new File(args[0]);
        }
    }

    private static void Scan(String[] args, int idx){

        // scans passed images

        shouldRun = true;
        for (int i = idx; i < args.length && !args[i].substring(0,1).equals("-"); i++) {
            if (!args[i].contains(".jpg")) {

                // Gets path to file
                // File img = new File(System.getProperty("user.dir") + args[i]);
                File file = new File(args[i]);
                imageFiles.add(file);
                out("Added: " + file.getAbsolutePath());


            } else out("Unsupported Image Format");
        }

    }

    private static void run(File img){

        try {

            Mat rgb = imread(img.getAbsolutePath());

            // If no cascade file is specified try opening default file
            if(cascadeFile == null) {
                cascadeFile = new File("cascade.xml");
                out("No cascade file specified, Trying default: cascade.xml");
            }

            // if default cascade cant be found stop
            if(cascadeFile == null) {
                out("Default cascade not found, Terminating");
                exit();
                return;
            }

            CascadeClassifier cascade = new CascadeClassifier(cascadeFile.getAbsolutePath());


            // Checks if xml is loaded correctly
            if (!cascade.load(cascadeFile.getAbsolutePath())) {
                out("Error loading cascade");
                return;
            }

            Mat imgGray = new Mat();
            Imgproc.cvtColor(rgb, imgGray, Imgproc.COLOR_RGB2GRAY);

            // Equalize Image
            Imgproc.equalizeHist(imgGray, imgGray);

            // Detect People

            // REC_MIN_FLAGS = CASCADE_SCALE_IMAGE;
            MatOfRect people = new MatOfRect();
            cascade.detectMultiScale(imgGray, people);

            java.util.List<Rect> list = people.toList();
            Scalar blue = new Scalar(0, 0, 255);

            for (int i = 0; i < list.size(); i++) {

                Rect box = list.get(i);
                Imgproc.rectangle(rgb, new org.opencv.core.Point(box.x, box.y), new org.opencv.core.Point(box.x + box.width, box.y + box.height), blue);
            }

            out("Total Objects Found " + list.size());

            String newPath = img.getAbsolutePath();
            newPath = newPath.replace(".","_PROCESSED.");

            // construct new path
            //String outPath = p[0] + "_PROCESSED." + p[0];
            imwrite(newPath, rgb);
            out("Written File: " + newPath);
        }
        catch (Exception x){
            out("Error! " + x.getMessage());
        }
    }

    public static void out(String outTxt){
        System.out.println("\n" + outTxt);
    }

    public enum commands{

        H,
        CASCADE,
        SCAN
    }
}
