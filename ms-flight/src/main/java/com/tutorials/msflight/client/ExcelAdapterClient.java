package com.tutorials.msflight.client;


import com.tutorials.msflight.model.FileModel;
import com.tutorials.msflight.model.ResponseModel;
import com.tutorials.msflight.model.SheetModel;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ms-excel-adapter", url = "${client.url.excel-adapter}")
public interface ExcelAdapterClient {
    @PostMapping("/files")
    ResponseEntity<ResponseModel<Object>> createFile(@Valid @RequestBody FileModel requestBody);

    @PutMapping("/files/{file-name}")
    ResponseEntity<ResponseModel<Object>> addSheet(@Valid @RequestBody SheetModel requestBody,
                                           @PathVariable("file-name") String fileName);
}
