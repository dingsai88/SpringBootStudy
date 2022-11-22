package com.ding.study.util;
import org.apache.commons.codec.binary.Base64;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
import org.apache.pdfbox.util.Matrix;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author daniel
 * @date 2022/11/21 19:20
 **/
public class WaterMarkUtil {
    public static void main(String[] args) {
        File file=new File("D:\\Test\\test.pdf");
        String waterMark="测试水印";
        float fontSize=13;
        int[] color={0,200,0};
        int rowSize=150;
        int colSpace=150;
        try{
            System.out.println(  pdfWaterMark(file,waterMark,fontSize,color,rowSize,colSpace));
        }catch (Exception e){
            System.out.println("为PDF文件添加水印失败："+e);
        }

    }

    /**
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
     * @return void
     **/

    public static String pdfWaterMark(File tempFile,String waterMark,float fontSize,int[] color,int rowSpace,int colSpace) throws Exception{
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
                for (int w = - 10; w < pageWidth; w = w + colSpace) {
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

        return "str";
    }

    public static byte[] file2byte(File file) {
        if (file == null) {
            return null;
        }
        FileInputStream fileInputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0 , n);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
