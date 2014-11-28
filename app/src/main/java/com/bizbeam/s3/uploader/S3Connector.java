package com.bizbeam.s3.uploader;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

public class S3Connector {

    public String mAWS_S3_BUCKET;
    public String mAWS_ACCESS_KEY;
    public String mAWS_SECRET_KEY;
    public AmazonS3 amazonS3;

    public S3Connector(String s3Bucket, String s3AccessKey, String s3SecretKey) {
        mAWS_S3_BUCKET = s3Bucket;
        mAWS_ACCESS_KEY = s3AccessKey;
        mAWS_SECRET_KEY = s3SecretKey;
    }

    public void initialize() {
        if ((mAWS_ACCESS_KEY != null) && (mAWS_SECRET_KEY != null)) {
            AWSCredentials awsCredentials = new BasicAWSCredentials(mAWS_ACCESS_KEY, mAWS_SECRET_KEY);
            amazonS3 = new AmazonS3Client(awsCredentials);
            //amazonS3.createBucket(s3Bucket);

            //Logger.info("Using S3 Bucket: " + s3Bucket);
        }
    }
}
