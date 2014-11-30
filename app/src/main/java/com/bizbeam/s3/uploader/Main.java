package com.bizbeam.s3.uploader;

import com.beust.jcommander.JCommander;
import com.bizbeam.s3.uploader.jcommander.JCommanderParams;
import org.apache.log4j.Logger;

import java.io.File;

public class Main {

    private static String AWSAccessKeyId= "SAMPLESAMPLESAMPLE";
    private static String AWSSecretKey="2SamPleSample3456SaMpleSample3578SampleSaMpLe";
    private static String AWSBucketName = "sampleBucketName";
    private static String FILE_PREFIX_PATH = "app/src/main/resources";

	public static void main (String args[]) {

        long correctConnections = 0;
        long failedConnections = 0;
        JCommanderParams jcp = new JCommanderParams();

        try {
            LOGGER.info("Initializing connector...");

            new JCommander(jcp, args);
            processArguments(jcp);

            LOGGER.info("interval : " + jcp.getIntervalSeconds() + " [sec]");
            LOGGER.info("access key : " + jcp.getAccessKey());
            LOGGER.info("secret key : " + jcp.getSecretKey());
            LOGGER.info("bucket name : " + jcp.getBucketName());

            S3Connector s3Connector = new S3Connector(jcp.getBucketName(), jcp.getAccessKey(), jcp.getSecretKey());
            s3Connector.initialize();

            while (true) {

                try {
                    //put two files
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "sampleFiles/AdobeXMLFormsSamples.pdf"));
                    s3Connector.uploadFile(new File(FILE_PREFIX_PATH, "nature-wallpaper-27.jpg"));

                    s3Connector.removeFile("sampleFiles/AdobeXMLFormsSamples.pdf");
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

                LOGGER.info("Sleeping " + jcp.getIntervalSeconds() + " seconds...");
                sleep(jcp.getIntervalSeconds() * 1000);
            }

        } catch (Exception exc) {
            LOGGER.error(exc);
            System.out.println("\r\n" + exc.toString());
        }
    }

    private static void processArguments(JCommanderParams jcp) {
        int paramNum = jcp.getParameters().size();

        if (jcp.getIntervalSeconds() ==  -1)
            jcp.setIntervalSeconds(5);

        if (jcp.getAccessKey() ==  null)
            jcp.setAccessKey(AWSAccessKeyId);

        if (jcp.getSecretKey() ==  null)
            jcp.setSecretKey(AWSSecretKey);

        if (jcp.getBucketName() ==  null)
            jcp.setBucketName(AWSBucketName);
    }

    private static void sleep(int millis) {
        try { Thread.sleep(millis); }  catch (Exception exc) { }
    }

    private static final Logger LOGGER = Logger.getLogger(Main.class);
}
