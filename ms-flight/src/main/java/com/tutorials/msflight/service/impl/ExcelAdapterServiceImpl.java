package com.tutorials.msflight.service.impl;

import com.tutorials.msflight.client.ExcelAdapterClient;
import com.tutorials.msflight.model.FileModel;
import com.tutorials.msflight.model.SheetModel;
import com.tutorials.msflight.service.ExcelAdapterService;
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
        Objects.requireNonNull(response.getBody());
    }

    @Override
    public void addSheet(@Valid @RequestBody SheetModel sheetModel, @PathVariable("file-name") String fileName) {
        var response = excelAdapterClient.addSheet(sheetModel, fileName);
        log.info("Add sheet for flights response: {}", response);
        Objects.requireNonNull(response.getBody());
    }
}
