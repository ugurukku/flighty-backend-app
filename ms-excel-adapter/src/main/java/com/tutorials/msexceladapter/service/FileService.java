package com.tutorials.msexceladapter.service;

import com.tutorials.msexceladapter.model.FileModel;
import com.tutorials.msexceladapter.model.SheetModel;

public interface FileService {
    FileModel createFile(FileModel fileModel);

    FileModel addSheet(SheetModel sheetModel, String fileName);
}
