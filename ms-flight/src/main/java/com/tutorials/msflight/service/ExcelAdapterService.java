package com.tutorials.msflight.service;

import com.tutorials.msflight.model.FileModel;
import com.tutorials.msflight.model.SheetModel;

public interface ExcelAdapterService {
    void createFile(FileModel fileModel);

    void addSheet(SheetModel sheetModel, String fileName);
}
