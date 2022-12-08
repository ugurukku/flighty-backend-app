package com.tutorials.msexceladapter.controller;

import static com.tutorials.msexceladapter.util.Constants.FILES_URL;
import static com.tutorials.msexceladapter.util.Constants.REQUEST_LOG_FORMAT;
import static com.tutorials.msexceladapter.util.Constants.RESPONSE_LOG_FORMAT;

import com.tutorials.msexceladapter.model.FileModel;
import com.tutorials.msexceladapter.model.SheetModel;
import com.tutorials.msexceladapter.service.FileService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(FILES_URL)
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Object> createFile(@Valid @RequestBody FileModel requestBody) {
        log.info(REQUEST_LOG_FORMAT, FILES_URL, requestBody);
        var response = fileService.createFile(requestBody);
        log.info(RESPONSE_LOG_FORMAT, FILES_URL, response);
        return ResponseEntity.status(201).body(response);
    }

    @PutMapping("/{file-name}")
    public ResponseEntity<Object> addSheet(@Valid @RequestBody SheetModel requestBody,
                                           @PathVariable("file-name") String fileName) {
        log.info(REQUEST_LOG_FORMAT, FILES_URL, requestBody);
        var response = fileService.addSheet(requestBody, fileName);
        log.info(RESPONSE_LOG_FORMAT, FILES_URL, response);
        return ResponseEntity.ok().body(response);
    }
}
