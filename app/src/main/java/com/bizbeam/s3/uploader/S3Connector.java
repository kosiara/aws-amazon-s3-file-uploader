package com.bizbeam.s3.uploader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.log4j.Logger;

import java.io.File;

public class S3Connector {

    public String mAWS_S3_BUCKET;
    public String mAWS_ACCESS_KEY;
    public String mAWS_SECRET_KEY;
    public AmazonS3 mAmazonS3;

    public S3Connector(String s3Bucket, String s3AccessKey, String s3SecretKey) {
        mAWS_S3_BUCKET = s3Bucket;
        mAWS_ACCESS_KEY = s3AccessKey;
        mAWS_SECRET_KEY = s3SecretKey;
    }

    public void initialize() {
        if ((mAWS_ACCESS_KEY != null) && (mAWS_SECRET_KEY != null)) {
            AWSCredentials awsCredentials = new BasicAWSCredentials(mAWS_ACCESS_KEY, mAWS_SECRET_KEY);
            mAmazonS3 = new AmazonS3Client(awsCredentials);
            //mAmazonS3.createBucket(s3Bucket);

            LOGGER.info("Using S3 Bucket: " + mAWS_S3_BUCKET);
        }
    }

    public void uploadFile(File file) {

        PutObjectRequest putObjectRequest = new PutObjectRequest(mAWS_S3_BUCKET, file.getName(), file);
        putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead); // public for all
        mAmazonS3.putObject(putObjectRequest); // upload file
        
    }

    private static final Logger LOGGER = Logger.getLogger(S3Connector.class);
}
