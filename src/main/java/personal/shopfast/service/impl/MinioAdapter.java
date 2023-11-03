package personal.shopfast.service.impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.messages.Bucket;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class MinioAdapter {

    @Autowired
    MinioClient minioClient;

    @Value("${minio.buckek.name}")
    String defaultBucketName;

    @Value("${minio.default.folder}")
    String defaultBaseFolder;

    public Optional<List<Bucket>> getAllBuckets() {
        try {
            return Optional.ofNullable(minioClient.listBuckets());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }


    public void uploadFile(String name, byte[] content) {
        File file = new File(name);
        file.canWrite();
        file.canRead();
        try {
            FileInputStream iofs = new FileInputStream(file);
            iofs.read(content);

            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(defaultBaseFolder + name)
                    .stream(iofs, -1, PutObjectArgs.MIN_MULTIPART_SIZE)
                    .build());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public byte[] getFile(String key) {
        try {
            InputStream obj = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(defaultBaseFolder + "/")
                    .build());
            byte[] content = IOUtils.toByteArray(obj);
            obj.close();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostConstruct
    public void init() {
    }
}
