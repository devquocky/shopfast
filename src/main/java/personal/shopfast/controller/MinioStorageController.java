package personal.shopfast.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.shopfast.service.impl.MinioService;

@RestController
@RequestMapping(path = "/files")
@Validated
public class MinioStorageController extends AbstractController<MinioService> {


//    @GetMapping(path = "/buckets")
//    public ResponseEntity<List<Bucket>> listBuckets() {
//        return response(service.getAllBuckets());
//    }
//
//    @PostMapping(path = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public Map<String, String> uploadFile(@RequestPart(value = "file", required = false) MultipartFile files) throws IOException {
//        service.uploadFile(files.getOriginalFilename(), files.getBytes());
//
//        Map<String, String> result = new HashMap<>();
//        result.put("key", files.getOriginalFilename());
//
//        return result;
//    }
//
//    @GetMapping(path = "/download")
//    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value = "file") String file) {
//        byte[] data = service.getFile(file);
//        ByteArrayResource resource = new ByteArrayResource(data);
//
//        return ResponseEntity
//                .ok()
//                .contentLength(data.length)
//                .header("Content-type", "application/octet-stream")
//                .header("Content-disposition", "attachment; filename=\"" + file + "\"")
//                .body(resource);
//
//    }
}
