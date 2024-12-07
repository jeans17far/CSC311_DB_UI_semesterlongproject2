package dao;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

/**
 * Handles file uploads to Azure Blob Storage.
 */
public class UpdateStorage {

    /**
     * Client for interacting with the blob container.
     */
    private BlobContainerClient containerClient;

    /**
     * Initializes the connection to Azure Blob Storage.
     */
    public UpdateStorage() {
        this.containerClient = new BlobContainerClientBuilder()
                .connectionString("DefaultEndpointsProtocol=https;AccountName=csc311pjtorage;AccountKey=SDbJQX9KT0faiz+pW92OfW73sePMTldigqFCsEYIJiQmmfke6jMT5DgkKgffhhBkf9QhS8CosTd9+AStOxMP1Q==;EndpointSuffix=core.windows.net")
                .containerName("media-files")
                .buildClient();
    }

    /**
     * Uploads a file to Azure Blob Storage.
     */
    public void uploadFile(String filePath, String blobName) {
        BlobClient blobClient = containerClient.getBlobClient(blobName);
        blobClient.uploadFromFile(filePath);
    }

    /**
     * Gets the BlobContainerClient instance.
     */
    public BlobContainerClient getContainerClient() {
        return containerClient;
    }
}
