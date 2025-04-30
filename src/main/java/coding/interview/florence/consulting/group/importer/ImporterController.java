package coding.interview.florence.consulting.group.importer;

import coding.interview.florence.consulting.group.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/importer")
public class ImporterController {

    @Autowired
    private ImporterService importerService;

    @PostMapping
    public ResponseEntity<UserEntity> importUserListFromCsv(@RequestParam MultipartFile file) throws IOException {
        return ResponseEntity.ofNullable(importerService.importUsers(file.getInputStream()));
    }
}
