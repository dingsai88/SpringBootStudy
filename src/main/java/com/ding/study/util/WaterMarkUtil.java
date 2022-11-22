package com.ding.study.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.*;

/**pdf加水印  pdf下载
 * @author daniel
 * @date 2022/11/21 19:20
 **/
@Slf4j
public class WaterMarkUtil {
    public static void main(String[] args) {
        File file = new File("D:\\Test\\test.pdf");
        String waterMark = "测试水印";
        float fontSize = 13;
        int[] color = {0, 200, 0};
        int rowSize = 150;
        int colSpace = 150;
        try {
            System.out.println(pdfWaterMark(file, waterMark, fontSize, color, rowSize, colSpace));
        } catch (Exception e) {
            System.out.println("为PDF文件添加水印失败：" + e);
        }
    }


    @RequestMapping(value = "upload")
    public String streamUpload(HttpSession session, HttpServletRequest request,
                               HttpServletResponse response, @NotNull String waterMark, MultipartFile pdfStr) {
        try {
            waterMark += "水印";
            float fontSize = 13;
            int[] color = {0, 200, 0};
            int rowSize = 150;
            int colSpace = 150;
            File file = pdfWaterMark(multipartFileToFile(pdfStr), waterMark, fontSize, color, rowSize, colSpace);
            if (file.exists()) {
                //设置响应类型，这里是下载pdf文件
                response.setContentType("application/pdf");
                //设置Content-Disposition，设置attachment，浏览器会激活文件下载框；filename指定下载后默认保存的文件名
                //不设置Content-Disposition的话，文件会在浏览器内打卡，比如txt、img文件
                response.addHeader("Content-Disposition",
                        "attachment; filename=secret.pdf");
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                // if using Java 7, use try-with-resources
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (IOException ex) {
                    log.error("", ex);
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            log.error("", e);
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            log.error("", e);
                        }
                    }
                }
            }

        } catch (Exception e) {
            log.error("streamUpload异常", e);
            return "aa";
        }
        return null;
    }

    /**
     * 将MultipartFile转换为File
     *
     * @param multiFile
     * @return
     */
    public static File multipartFileToFile(MultipartFile multiFile) {
        // 获取文件名
        String fileName = multiFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 若须要防止生成的临时文件重复,能够在文件名后添加随机码

        try {
            File file = File.createTempFile(fileName, prefix);
            multiFile.transferTo(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return void
     * @Author czc
     * @Description 为pdf文件添加水印
     * @Date 2022/6/6
     * @Param [tempFile, waterMark, fontSize, color, rowSpace, colSpace]
     * tempFile     需要添加水印的文件
     * watermark    水印文字
     * fontSize     水印字体大小
     * color        字体颜色{r,g,b}
     * rowSpace     行间距，大中小分别对应150/100/50
     * colSpace     列间距，大中小分别对应150/100/50
     **/

    public static File pdfWaterMark(File tempFile, String waterMark, float fontSize, int[] color, int rowSpace, int colSpace) throws Exception {
        PDDocument document = PDDocument.load(tempFile);
        document.setAllSecurityToBeRemoved(true);
        // 加载水印字体

        PDFont font = PDType0Font.load(document, new FileInputStream(ResourceUtils.getFile("classpath:Font.ttf")), true);

        // 遍历PDF文件，在每一页加上水印
        for (PDPage page : document.getPages()) {
            PDPageContentStream stream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true, true);

            PDExtendedGraphicsState r = new PDExtendedGraphicsState();

            // 设置透明度
            r.setNonStrokingAlphaConstant(0.2f);
            r.setAlphaSourceFlag(true);
            stream.setGraphicsStateParameters(r);

            // 设置水印字体颜色
            if (color.length == 3) {
                stream.setNonStrokingColor(color[0], color[1], color[2]);
            }
            stream.beginText();
            stream.setFont(font, fontSize);
            stream.newLineAtOffset(0, -15);

            // 获取PDF页面大小
            float pageHeight = page.getMediaBox().getHeight();
            float pageWidth = page.getMediaBox().getWidth();

            // 根据纸张大小添加水印，30度倾斜
            for (int h = 10; h < pageHeight; h = h + rowSpace) {
                for (int w = -10; w < pageWidth; w = w + colSpace) {
                    stream.setTextMatrix(Matrix.getRotateInstance(0.3, w, h));
                    stream.showText(waterMark);
                }
            }

            // 结束渲染，关闭流
            stream.endText();
            stream.restoreGraphicsState();
            stream.close();
        }
        document.save(tempFile);
        document.close();

        return tempFile;
    }


}
