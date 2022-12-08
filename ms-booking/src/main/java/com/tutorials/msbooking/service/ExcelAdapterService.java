package com.tutorials.msbooking.service;

import com.tutorials.msbooking.model.FileModel;
import com.tutorials.msbooking.model.SheetModel;

public interface ExcelAdapterService {
    void createFile(FileModel fileModel);

    void addSheet(SheetModel sheetModel, String fileName);
}
