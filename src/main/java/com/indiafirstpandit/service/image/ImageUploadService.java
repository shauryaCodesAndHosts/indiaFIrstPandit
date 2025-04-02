package com.indiafirstpandit.service.image;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageUploadService {

    public String uploadImage(MultipartFile file) throws IOException {
        // Generate unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Get Firebase bucket and upload file
        Bucket bucket = StorageClient.getInstance().bucket();
        Blob blob = bucket.create(fileName, file.getBytes(), file.getContentType());

        // Get the public URL of the uploaded file
        String imageUrl = String.format("https://firebasestorage.googleapis.com/v0/b/%s/o/%s?alt=media",
                bucket.getName(), blob.getName());

        return imageUrl;  // Return image URL to store in database
    }

}
