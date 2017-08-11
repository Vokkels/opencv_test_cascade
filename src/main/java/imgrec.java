import com.smartgwt.client.types.ValueEnum;

import javax.naming.spi.DirectoryManager;
import java.awt.*;
import java.io.File;


/**
 * Created by Deltamike76 on 8/11/2017.
 */
public class imgrec {

    private File cascade;

    public static void main(String[] args){

        out("**************** Image Recognizer *******************");
        out("");

        File files;
        // Load image from file
        if(args.length > 0) {

            ValueEnum enumval = ValueEnum.fromString(myString);
            switch (args[0]) {

                case "-h":
                    out("-scan\t => Analyze images, takes images as parameters");
                    out("-cascade\t => Supply new cascade file");
                    break;
                case "-scan":

                    // First param is method type
                    // scans passed images
                    for (int i = 1; i < args.length; i++) {
                        if (args[i].contains(".jpg")) {

                            // Gets path to file
                            File img = new File(System.getProperty("user.dir") + args[i]);
                            //run((Image) args[i],"");

                        } else out("Unsupported Image Format");
                    }
                    break;

                default: {
                    out("Type -h for Help");
                    break;
                }
                files = new File(args[0]);
            }
        }



        // Get path of image

        // Make copy

        // Call run



    }

    public static Image run(Image img, String path){


        // Create grayscale version of image

        // Load cascade trainer and pass gray image

        // Count recs

        // Draw rec on color img

        return img;


    }

    public static void out(String outTxt){

        System.out.println(outTxt);
    }
}
