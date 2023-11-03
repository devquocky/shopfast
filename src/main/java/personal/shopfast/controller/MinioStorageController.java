package personal.shopfast.controller;

import io.minio.messages.Bucket;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import personal.shopfast.service.impl.MinioAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/files")
@Validated
public class MinioStorageController extends AbstractController<MinioAdapter> {


    @GetMapping(path = "/buckets")
    public ResponseEntity<List<Bucket>> listBuckets() {
        return response(service.getAllBuckets());
    }

    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
        service.uploadFile(files.getOriginalFilename(), files.getBytes());

        Map<String, String> result = new HashMap<>();
        result.put("key", files.getOriginalFilename());

        return result;
    }

    @GetMapping(path = "/download")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file) {
        byte[] data = service.getFile(file);
        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
                .body(resource);

    }
}
