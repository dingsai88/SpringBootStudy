package com.ding.study.temp;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * # 定义Excel文件路径和PDF目录路径
 * $excelFilePath = "C:\rename.xlsx"
 * $pdfDirectoryPath = "C:\pdf\"
 *
 * # 创建Excel应用程序对象
 * $excel = New-Object -ComObject Excel.Application
 * $workbook = $excel.Workbooks.Open($excelFilePath)
 * $sheet = $workbook.Sheets.Item(1)
 *
 * # 获取使用的行数
 * $rowCount = $sheet.UsedRange.Rows.Count
 *
 * for ($row = 1; $row -le $rowCount; $row++) {
 *     # 读取第一列和第二列的值
 *     $fileName1 = $sheet.Cells.Item($row, 1).Text.Trim()
 *     $fileName2 = $sheet.Cells.Item($row, 2).Text.Trim()
 *
 *     # 构建完整的文件路径
 *     $oldFilePath = Join-Path -Path $pdfDirectoryPath -ChildPath $fileName1
 *     $newFilePath = Join-Path -Path $pdfDirectoryPath -ChildPath $fileName2
 *
 *
 * # 重命名文件
 * if (Test-Path -LiteralPath $oldFilePath) {
 *     # 使用简单的字符串拼接来构建新文件路径
 *     Rename-Item -LiteralPath $oldFilePath -NewName $fileName2
 *     Write-Host "Renamed: '$fileName1' to '$fileName2'"
 * } else {
 *     Write-Host "Original file not found: '$fileName1'"
 * }
 *
 * }
 *
 * # 清理资源
 * $workbook.Close($false)
 * $excel.Quit()
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($sheet) | Out-Null
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($workbook) | Out-Null
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($excel) | Out-Null
 */
public class ExcelReaderXuMan {
    public static void main(String[] args) {
        String excelFilePath = "C:\\rename.xlsx"; // 替换为你的Excel文件路径
        String pdfPath="C:\\pdf\\";

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);
            // 遍历每一行
            for (Row row : sheet) {
                if (row != null) {
                    // 获取第一列（索引为0）
                    Cell firstCell = row.getCell(0);
                    String fileName1= getCellValue(firstCell);
                    System.out.println("First Column: " + getCellValue(firstCell));

                    // 获取第二列（索引为1）
                    Cell secondCell = row.getCell(1);
                    String fileName2= getCellValue(secondCell);
                    System.out.println("Second Column: " + getCellValue(secondCell));

                    // 构建完整的文件路径
                    File oldFile = new File(pdfPath + fileName1);
                    File newFile = new File(pdfPath + fileName2);

                    // 重命名文件
                    if (oldFile.exists()) {
                        boolean success = oldFile.renameTo(newFile);
                        if (success) {
                            System.out.println("Renamed: " + fileName1 + " to " + fileName2);
                        } else {
                            System.out.println("Failed to rename: " + fileName1);
                        }
                    }
                }

                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "NULL";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            default:
                return "UNKNOWN";
        }
    }
}
