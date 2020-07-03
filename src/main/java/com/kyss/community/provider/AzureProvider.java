package com.kyss.community.provider;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName AzureProvider
 * @Description TODO
 * @Author davidt
 * @Date 7/3/2020 4:46 PM
 * @Version 1.0
 **/

@Component
public class AzureProvider {

    @Value("${AZURE_STORAGE_CONNECTION_STRING}")
    private String connectStr;

    private BlobContainerClient blobContainerClient;

    public AzureProvider() {
        // Create a BlobServiceClient object which will be used to create a container client
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString("DefaultEndpointsProtocol=https;AccountName=proactivestorage;AccountKey=2KT9kKNZlkkg1MSpDy7UPbI1mDw6hGiniZaBlwNmCQEUY4AxEM4QT63DskAnm0jhuyswLAHcCK6//ludF5G2Lg==;EndpointSuffix=core.windows.net").buildClient();
        //Create a unique name for the container
        String containerName = "community";

        // Create the container and return a container client object
        blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
        if (blobContainerClient == null) {
            blobContainerClient = blobServiceClient.createBlobContainer(containerName);
        }
    }

    public void createBlob(InputStream fileStream, Long size, String fileName) throws IOException {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        System.out.println("\nUploading to Blob storage as blob:\n\t" + blobClient.getBlobUrl());

        // Upload the blob
        if (!blobClient.exists()) {
            blobClient.upload(fileStream, size);
        }
    }

    public String getBlob(String fileName) {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);
        return blobClient.getBlobUrl();
    }
}




