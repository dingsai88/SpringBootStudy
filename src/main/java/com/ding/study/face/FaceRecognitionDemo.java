package com.ding.study.face;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.util.Arrays;

/**
 * https://blog.csdn.net/m0_54765221/article/details/129925975
 *
 *
 * 这是使用OpenCV进行人脸识别的演示。
 */
public class FaceRecognitionDemo {

    public static void main(String[] args) {
        // 加载OpenCV库
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // 加载人脸检测分类器
        CascadeClassifier faceDetector = new CascadeClassifier("D:\\DingSaiStudyProject\\Face\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt2.xml");

        // 加载要处理的图像
        Mat image1 = Imgcodecs.imread("D:\\DingSai\\face\\ziji.jpg");
        Mat image2 = Imgcodecs.imread("D:\\DingSai\\face\\ziji2.jpg");

        // 在图像中检测人脸
        MatOfRect faceDetections1 = new MatOfRect();
        MatOfRect faceDetections2 = new MatOfRect();
        faceDetector.detectMultiScale(image1, faceDetections1);
        faceDetector.detectMultiScale(image2, faceDetections2);

        // 打印检测到的人脸数
        System.out.println(String.format("第一张图片检测到%s张人脸", faceDetections1.toArray().length));
        System.out.println(String.format("第二张图片检测到%s张人脸", faceDetections2.toArray().length));

        // 在检测到的人脸周围画矩形
        for (Rect rect : faceDetections1.toArray()) {
            Imgproc.rectangle(image1, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }
        for (Rect rect : faceDetections2.toArray()) {
            Imgproc.rectangle(image2, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
        }

        // 保存结果图像
        Imgcodecs.imwrite("D:\\DingSai\\face\\result.jpg", image1);
        Imgcodecs.imwrite("D:\\DingSai\\face\\result2.jpg", image2);

        // 计算两张图片的相似度
        double similarity = compareImages(image1, image2);
        System.out.println(String.format("两张图片的相似度为%s", similarity));
    }

    /**
     * 计算两张图片的相似度
     * @param image1 第一张图片
     * @param image2 第二张图片
     * @return 相似度
     */
    private static double compareImages(Mat image1, Mat image2) {
        // 计算两张图片的直方图
        Mat hist1 = new Mat();
        Mat hist2 = new Mat();
        Imgproc.calcHist(Arrays.asList(image1), new MatOfInt(0), new Mat(), hist1, new MatOfInt(256), new MatOfFloat(0, 256));
        Imgproc.calcHist(Arrays.asList(image2), new MatOfInt(0), new Mat(), hist2, new MatOfInt(256), new MatOfFloat(0, 256));

        // 归一化直方图
        Core.normalize(hist1, hist1);
        Core.normalize(hist2, hist2);

        // 计算直方图相似度
        double similarity = Imgproc.compareHist(hist1, hist2, Imgproc.CV_COMP_CORREL);
        return similarity;
    }
}
