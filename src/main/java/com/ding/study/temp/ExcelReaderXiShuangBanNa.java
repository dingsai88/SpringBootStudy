package com.ding.study.temp;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ding.study.net.HttpClient4Util;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * # 定义Excel文件路径和PDF目录路径
 * $excelFilePath = "C:\rename.xlsx"
 * $pdfDirectoryPath = "C:\pdf\"
 * <p>
 * # 创建Excel应用程序对象
 * $excel = New-Object -ComObject Excel.Application
 * $workbook = $excel.Workbooks.Open($excelFilePath)
 * $sheet = $workbook.Sheets.Item(1)
 * <p>
 * # 获取使用的行数
 * $rowCount = $sheet.UsedRange.Rows.Count
 * <p>
 * for ($row = 1; $row -le $rowCount; $row++) {
 * # 读取第一列和第二列的值
 * $fileName1 = $sheet.Cells.Item($row, 1).Text.Trim()
 * $fileName2 = $sheet.Cells.Item($row, 2).Text.Trim()
 * <p>
 * # 构建完整的文件路径
 * $oldFilePath = Join-Path -Path $pdfDirectoryPath -ChildPath $fileName1
 * $newFilePath = Join-Path -Path $pdfDirectoryPath -ChildPath $fileName2
 * <p>
 * <p>
 * # 重命名文件
 * if (Test-Path -LiteralPath $oldFilePath) {
 * # 使用简单的字符串拼接来构建新文件路径
 * Rename-Item -LiteralPath $oldFilePath -NewName $fileName2
 * Write-Host "Renamed: '$fileName1' to '$fileName2'"
 * } else {
 * Write-Host "Original file not found: '$fileName1'"
 * }
 * <p>
 * }
 * <p>
 * # 清理资源
 * $workbook.Close($false)
 * $excel.Quit()
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($sheet) | Out-Null
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($workbook) | Out-Null
 * [System.Runtime.Interopservices.Marshal]::ReleaseComObject($excel) | Out-Null
 */
@Slf4j
public class ExcelReaderXiShuangBanNa {
    public static void main(String[] args) throws Exception {
        String excelFilePath = "C:\\xishuang.xlsx"; // 替换为你的Excel文件路径

        String outputFilePath = "C:\\xishuang.txt";

        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {
            // 获取第一个工作表
            Sheet sheet = workbook.getSheetAt(0);


            // 遍历每一行
            for (Row row : sheet) {
                if (row != null) {
                    try {


                        Cell firstCell = row.getCell(0);
                        String fileName1 = getCellValue(firstCell);
                        // System.out.println("First Column: " +fileName1);


                        // 获取第二列（索引为1）
                        Cell secondCell = row.getCell(1);
                        String fileName2 = getCellValue(secondCell);

                        String url = "http://xxxx/rc-server/api/faceController/getUserFaceMessage?customerCode=";

                        String result = HttpClient4Util.doGet(url + fileName1);
                        // 使用JsonParser解析JSON字符串
                        // 创建 ObjectMapper 实例
                        ObjectMapper objectMapper = new ObjectMapper();

                        // 将 JSON 字符串解析为 JsonNode 对象
                        JsonNode jsonNode = objectMapper.readTree(result);

                        // 获取 message 字段的信息
                        String message = jsonNode.get("message").asText();

                        System.out.println(fileName1 + " " + result);
                        // 调用静态方法将结果写入到文件中
                        appendToFile(outputFilePath, fileName1+"   " + (result.indexOf("A10001") != -1 ? "已开通" : "未开通"));
                    } catch (Exception e) {
                        log.error("异常");
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


    private static void appendToFile(String filePath, String content) {
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(content);
            bw.newLine();  // 换行

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
