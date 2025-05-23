package dev.carlos.admin_service.infra;

import dev.carlos.admin_service.ports.StoragePorts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3StorageAdapter implements StoragePorts {

    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    public S3StorageAdapter(@Value("${aws.s3.region}") String region, @Value("${aws.s3.bucket-name}") String bucketName, @Value("${aws.s3.provider}") String provider) {
        this.bucketName = bucketName;
        this.region = region;
        this.s3Client = S3Client.builder()
                .region(Region.of((this.region)))
                .credentialsProvider(ProfileCredentialsProvider.create(provider))
                .build();
    }

    @Override
    public String uploadFile(byte[] fileData, String filename, String contentType) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(filename)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(fileData));

        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, filename);
    }
}
