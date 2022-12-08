package com.tutorials.msbooking.service.impl;

import com.tutorials.msbooking.client.ExcelAdapterClient;
import com.tutorials.msbooking.model.FileModel;
import com.tutorials.msbooking.model.SheetModel;
import com.tutorials.msbooking.service.ExcelAdapterService;
import java.util.Objects;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Log4j2
@Service
public class ExcelAdapterServiceImpl implements ExcelAdapterService {
    private final ExcelAdapterClient excelAdapterClient;

    @Override
    public void createFile(FileModel fileModel) {
        var response = excelAdapterClient.createFile(fileModel);
        log.info("Create excel file: {}", response);
    }

    @Override
    public void addSheet(@Valid @RequestBody SheetModel sheetModel, @PathVariable("file-name") String fileName) {
        var response = excelAdapterClient.addSheet(sheetModel, fileName);
        log.info("Add sheet for flights response: {}", response);
    }
}
