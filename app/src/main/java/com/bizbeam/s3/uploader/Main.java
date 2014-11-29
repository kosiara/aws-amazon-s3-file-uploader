package com.bizbeam.s3.uploader;

import org.apache.log4j.Logger;

import java.io.File;

public class Main {

    private static String AWSAccessKeyId= "SAMPLESAMPLESAMPLE";
    private static String AWSSecretKey="2SamPleSample3456SaMpleSample3578SampleSaMpLe";
    private static String AWSBucketName = "bizbeamtest";
    private static String FILE_PREFIX_PATH = "app/src/main/resources";

	public static void main (String args[]) {

        int intervalSeconds = 0;
        long correctConnections = 0;
        long failedConnections = 0;

        try {
            LOGGER.info("Initializing connector...");

            if (args.length < 1)
                throw new Exception("You need to pass interval [s] as parameter!");

            intervalSeconds = Integer.parseInt(args[0]);
            LOGGER.info("interval : " + intervalSeconds + " [sec]");

            S3Connector s3Connector = new S3Connector(AWSBucketName, AWSAccessKeyId, AWSSecretKey);
            s3Connector.initialize();

            while (true) {

                try {
                    //put two files
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "AdobeXMLFormsSamples.pdf"));
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "nature-wallpaper-27.jpg"));

                    s3Connector.removeFile("AdobeXMLFormsSamples.pdf");
                    s3Connector.removeFile("nature-wallpaper-27.jpg");

                    //small files
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "cake.jpg"));
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "turtle.jpg"));

                    s3Connector.removeFile("cake.jpg");
                    s3Connector.removeFile("turtle.jpg");

                    correctConnections++;

                } catch (Exception exc) {
                    LOGGER.error("Error in connection: ", exc);
                    failedConnections++;
                }

                LOGGER.info("");
                LOGGER.info("Current results");
                LOGGER.info("==========================");
                LOGGER.info("Correct connections:" + correctConnections);
                LOGGER.info("Failed connections:" + failedConnections + "\r\n\r\n");

                LOGGER.info("Sleeping " + intervalSeconds + " seconds...");
                sleep(intervalSeconds * 1000);
            }

        } catch (Exception exc) {
            LOGGER.error(exc);
        }
    }

    private static void sleep(int millis) {
        try { Thread.sleep(millis); }  catch (Exception exc) { }
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);
}
