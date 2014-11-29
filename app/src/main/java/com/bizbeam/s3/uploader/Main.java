package com.bizbeam.s3.uploader;

import org.apache.log4j.Logger;

import java.io.File;

public class Main {

    private static String AWSAccessKeyId= "SAMPLESAMPLESAMPLE";
    private static String AWSSecretKey="2SamPleSample3456SaMpleSample3578SampleSaMpLe";
    private static String AWSBucketName = "bizbeamtest";
    private static String FILE_PREFIX_PATH = "app/src/main/resources";

	public static void main (String args[]) {

        try {
            System.out.println ("Initializing connector...");
            S3Connector s3Connector = new S3Connector(AWSBucketName, AWSAccessKeyId, AWSSecretKey);
            s3Connector.initialize();

            //put two files
            s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "AdobeXMLFormsSamples.pdf"));
            s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "nature-wallpaper-27.jpg"));

            s3Connector.removeFile("AdobeXMLFormsSamples.pdf");
            s3Connector.removeFile("nature-wallpaper-27.jpg");

        } catch (Exception exc) {
            LOGGER.error(exc);
        }
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);
}
