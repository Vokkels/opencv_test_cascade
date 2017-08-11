A small java application for testing numerous cascades by providing quick and east feedback for debugging training data

Usage:
java -jar opencv_test_cascade.jar -cascade [ cascade name] -scan [ image 1][ image 2][ image 2]

Example:
java -jar opencv_test_cascade.jar -cascade haarcascade_fullbody.xml -scan img1.bmp img2.bmp img3.bmp
Creates 3 images as output: img1_PROCESSED.bmp, img2_PROCESSED.bmp, img3_PROCESSED.bmp
