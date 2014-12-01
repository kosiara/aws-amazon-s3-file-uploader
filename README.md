aws-amazon-s3-file-uploader
===========================

uploads files to aws amazon s3 cloud storage.

What it does?
==============
Aws-amazon-s3-file-uploader uploads all files that reside in the application directory and deletes them from S3.
With this simple tool you can test the stability of your connection to Amazon (a week connection stability test checked
every 5 minutes). Heavy use can be simulated by copying large files into the program directory.


How to run:

java -jar app.jar

java -jar app.jar -i 5

java -jar app.jar -i 5 -k youAmAzONAWSKey -s 2mmsdSE34fCYourAwsAmazonSecret -b yourBucketName

Parameters
==========

* -i/-int/-interval  -  interval in seconds between calls
* -k/-key/-accessKey  -  AWSAccessKeyId to access Amazon S3 cloud storage that you can obtain from https://console.aws.amazon.com/iam/home
* -s/-secret/-secretKey - AWSSecretKey used as a password
* -b/-bucket/-bucketName - bucket name
