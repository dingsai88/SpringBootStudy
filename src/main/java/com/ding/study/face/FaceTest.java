package com.ding.study.face;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class FaceTest {
    // 调用OpenCV库文件
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String args[]) {
        // 创建一个3X3的对角矩阵
        Mat a = Mat.eye(3, 3, CvType.CV_8UC1);
        System.out.println(a.dump());
    }
}
