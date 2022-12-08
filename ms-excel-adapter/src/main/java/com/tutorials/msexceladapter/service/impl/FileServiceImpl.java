package com.tutorials.msexceladapter.service.impl;

import com.tutorials.msexceladapter.exception.ExcelAdapterException;
import com.tutorials.msexceladapter.model.FileModel;
import com.tutorials.msexceladapter.model.SheetModel;
import com.tutorials.msexceladapter.service.FileService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class FileServiceImpl implements FileService {

    @Override
    public FileModel createFile(FileModel fileModel) {
        var path = "ms-excel-adapter/src/main/resources/" + fileModel.getFileName() + ".xlsx";
        var file = new File(path);

        if (!file.exists()) {
            try (var workbook = new XSSFWorkbook(); var outputStream = new FileOutputStream(path)) {
                workbook.createSheet();

                workbook.write(outputStream);
                log.info("File written successfully on: {}", path);
            } catch (Exception e) {
                throw new ExcelAdapterException(e.getMessage());
            }
        }
        return FileModel.builder().fileName(path).build();
    }

    @Override
    public FileModel addSheet(SheetModel sheetModel, String fileName) {
        var path = "ms-excel-adapter/src/main/resources/" + fileName + ".xlsx";
        File file = new File(path);
        XSSFWorkbook workbook;

        try (var fileInputStream = new FileInputStream(file)) {
            workbook = new XSSFWorkbook(fileInputStream);
            var sheet = workbook.createSheet(sheetModel.getSheetName());
            writeToFile(sheetModel.getData(), sheet, workbook);

            if (workbook.getSheetName(0).equalsIgnoreCase("sheet0"))
                workbook.removeSheetAt(0);

        } catch (Exception e) {
            throw new ExcelAdapterException(e.getMessage());
        }

        try (var fileOutputStream = new FileOutputStream(file)) {
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            throw new ExcelAdapterException(e.getMessage());
        }

        return FileModel.builder().fileName(path).build();
    }

    private void writeToFile(Map<String, List<String>> data, XSSFSheet sheet, XSSFWorkbook workbook) {
        var headerRow = sheet.createRow(0);
        AtomicInteger columnIndex = new AtomicInteger();

        var cellStyle = formatHeaders(workbook);

        data.keySet().forEach(header -> {
            var headerCell = headerRow.createCell(columnIndex.get());
            headerCell.setCellValue(header);
            headerCell.setCellStyle(cellStyle);

            var rows = data.get(header);
            setRows(sheet, rows, columnIndex);

            sheet.autoSizeColumn(columnIndex.get());
            columnIndex.addAndGet(1);
        });
    }

    private XSSFCellStyle formatHeaders(XSSFWorkbook workbook) {
        var headerStyle = workbook.createCellStyle();
        var font = workbook.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        return headerStyle;
    }

    private void setRows(XSSFSheet sheet, List<String> rows, AtomicInteger columnIndex) {
        var rowIndex = new AtomicInteger(1);

        rows.forEach(row -> {
            var sheetRow = sheet.getRow(rowIndex.get()) == null
                    ? sheet.createRow(rowIndex.get())
                    : sheet.getRow(rowIndex.get());

            var cell = sheetRow.createCell(columnIndex.get());
            cell.setCellValue(row);
            rowIndex.addAndGet(1);
        });
    }

}
