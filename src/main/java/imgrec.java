import java.io.File;


/**
 * Created by Deltamike76 on 8/11/2017.
 */
public class imgrec {


    public static void main(String[] args){

        out("****************Image Recognizer*******************");
        out("");

        File files;
        // Load image from file
        if(args.length > 0) {

            String method = args[0];
            switch (method){

                case "-h": {
                    out("-scan => Analyzes image, takes images as parameters");
                    break;
                }
                case "-scan":{

                    // First param is method type
                    // scans passed images
                    for(int i = 1; i < args.length; i++){
                        if(args[i].contains(".jpg")){

                            run(,"");

                        }else   out("Unsupported Image Format");
                    }
                    break;
                }
                default:{
                    out("Type -h for Help");
                    break;
                }
            }
            files = new File(args[0]);
        }



        // Get path of image

        // Make copy

        // Call run



    }

    public static Bitmap run(Bitmap img, String path){


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
