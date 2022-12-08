package com.tutorials.msbooking.client;


import static com.tutorials.msbooking.util.Constants.ADD_SHEET_URL;
import static com.tutorials.msbooking.util.Constants.FILES_URL;

import com.tutorials.msbooking.model.FileModel;
import com.tutorials.msbooking.model.ResponseModel;
import com.tutorials.msbooking.model.SheetModel;
import javax.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "ms-excel-adapter", url = "${client.url.excel-adapter}")
public interface ExcelAdapterClient {
    @PostMapping(FILES_URL)
    ResponseEntity<ResponseModel<Object>> createFile(@Valid @RequestBody FileModel requestBody);

    @PutMapping(ADD_SHEET_URL)
    ResponseEntity<ResponseModel<Object>> addSheet(@Valid @RequestBody SheetModel requestBody,
                                           @PathVariable("file-name") String fileName);
}
