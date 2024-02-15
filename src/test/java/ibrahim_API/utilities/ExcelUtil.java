package ibrahim_API.utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/*
 * This is a utility for reading from writing to excel files.
 * it works with xls and xlsx files.
 */
public class ExcelUtil {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;
    static private String sheetName;

    public ExcelUtil(String path, String sheetName) {
        this.path = path;
        this.sheetName = sheetName;
        try {
            FileInputStream ExcelFile = new FileInputStream(path);
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            return cellData;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getCellData2(int rowNum, int colNum) {
        T value;
        Cell cell = workSheet.getRow(rowNum).getCell(colNum);
        DataFormatter dataFormatter = new DataFormatter();
        String dataCell = dataFormatter.formatCellValue(cell);
        convertStringToDataType(dataCell);
        return (T) convertStringToDataType(dataCell);
    }


    private <T> T convertStringToDataType(String str) {
        if (str.equalsIgnoreCase("true") || str.equalsIgnoreCase("false")) {
            return (T) Boolean.valueOf(str);
        } else if (str.trim().equals("null")) {
            return null;
        }
        try {
            return (T) Integer.valueOf(str);
        } catch (NumberFormatException e1) {
            try {
                return (T) Long.valueOf(str);
            } catch (NumberFormatException e2) {
                try {
                    return (T) Float.valueOf(str);
                } catch (NumberFormatException e3) {
                    try {
                        return (T) Double.valueOf(str);
                    } catch (NumberFormatException e4) {
                        return (T) str;
                    }
                }
            }
        }
    }

    public String[][] getDataArray() {
        String[][] data = new String[rowCount()][columnCount()];
        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;
    }

    //this method will return data table as 2d array
    //so we need this format because of data provider.
    public Object[][] getDataArrayWithoutFirstRow() {
        Object[][] data = new Object[rowCount() - 1][columnCount()];
        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                data[i - 1][j] = getCellData2(i, j);
            }
        }
        return data;
    }



    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();
        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }

            data.add(rowMap);
        }

        return data;
    }

    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;
        try {
            row = workSheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            FileOutputStream fileOut = new FileOutputStream(path);
            workBook.write(fileOut);

            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(value, row, column);
    }

    public int columnCount() {
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return workSheet.getLastRowNum() + 1;
    }

    public ArrayList<Object> getColumnList(int colNum) {
        ArrayList<Object> currentList = new ArrayList<>();
        for (int i = 0; i < rowCount(); i++) {
            if (!getCellData(i, colNum).equals(" ") || !getCellData(i, colNum).isBlank()) {
                currentList.add(getCellData(i, colNum));
            }
        }
        return currentList;
    }

    public List<Object> getRowList(int row) {
        List<Object> wholeData = new ArrayList<>();
        for (int i = 0; i < columnCount(); i++) {
            wholeData.add(getCellData(row, i));
        }
        return wholeData;
    }

    public List<List<String>> getAllRowsAsList() {
        List<List<String>> wholeData = new ArrayList<>();
        for (int i = 1; i < rowCount(); i++) {
            List<String> singleRow = new ArrayList<>();
            for (int j = 0; j < columnCount(); j++) {
                singleRow.add(getCellData(i, j));
            }
            wholeData.add(singleRow);
        }
        return wholeData;
    }

    public List<List<String>> getAllColumnAsList() {
        List<List<String>> columns = new ArrayList<>();
        for (int i = 0; i < columnCount(); i++) {
            List<String> singleColumns = new ArrayList<>();
            for (int j = 1; j < rowCount(); j++) {
                singleColumns.add(getCellData(j, i));
            }
            columns.add(singleColumns);
        }
        return columns;
    }

}
