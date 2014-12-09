package com.utils;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Excel {

    String filePath;
    WritableWorkbook workbook;
    int sheetIndex = 0;
    String sheetName;

    public Excel(String filePath) throws WriteException {
        this.filePath = filePath;
        WritableCellFormat cellFormat = new WritableCellFormat();
        cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        cellFormat.setAlignment(Alignment.LEFT);
    }

    public void create_book() throws WriteException, IOException {
        String[] path = filePath.split("/");
        new File(filePath.replace(path[path.length - 1], "")).mkdirs();
        workbook = Workbook.createWorkbook(new File(filePath), new WorkbookSettings());
    }

    public WritableSheet create_sheet(String sheetName) throws IOException {

        if(sheetName.isEmpty())
            sheetName = "sheet";
        this.sheetName = sheetName;
        WritableSheet sheet = workbook.createSheet(sheetName, sheetIndex);
        sheet.getSettings().setDefaultColumnWidth(20);
        sheetIndex++;
        return sheet;
    }

    public void add_header(String[] header, String sheetName) throws WriteException {

        WritableSheet sheet = get_sheet(sheetName);
        for (int i = 0; i < header.length; i++)
            sheet.addCell(new Label(i, 0, header[i], setBoldText()));
    }

    public void writeToCell(String sheetName, String text, int col, int row) throws RowsExceededException, WriteException {
        get_sheet(sheetName).addCell(new Label(col, row, text));
    }

    public WritableSheet get_sheet(String name) {

        if(name.isEmpty())
            name = this.sheetName;
        try {
            return workbook.getSheet(name);
        } catch (Exception e) {
            return null;
        }
    }

    public int get_rows(String sheetName) {
        return get_sheet(sheetName).getRows();
    }

    public void writeLinkToCell(WritableSheet sheet, String path, int col, int row) throws RowsExceededException, WriteException, MalformedURLException {

        WritableHyperlink link = new WritableHyperlink(col, row, new URL(path));

        sheet.addHyperlink(link);
        WritableFont blueFont = new WritableFont(WritableFont.ARIAL);
        blueFont.setColour(Colour.BLUE);

        sheet.addCell(new Label(col, row, path, new WritableCellFormat(blueFont)));
    }

    public WritableCellFormat setBoldText() throws WriteException {
        WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 10);
        cellFont.setBoldStyle(WritableFont.BOLD);
        WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
        return cellFormat;
    }

    public void save() throws IOException, WriteException {
        workbook.write();
        workbook.close();
    }

}
