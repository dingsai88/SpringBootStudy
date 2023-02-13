
https://matlab.mathworks.com/
https://matlabacademy.mathworks.com/R2022b/cn/portal.html?course=deeplearning#chapter=1&lesson=1&section=1


深度学习

任务
将文件 file01.jpg 中的图像导入 MATLAB 工作区中。将其存储在名为 img1 的变量中。
img1 = imread("file01.jpg");




任务
显示变量 img1 中的导入图像。

imshow(img1);


任务
使用 googlenet 函数加载一个预训练网络。将结果保存为一个名为 deepnet 的变量。


deepnet  = googlenet


任务
使用 classify 函数和预训练的 GoogLeNet 网络 deepnet 来预测变量 img1 中存储的图像的主题类别。将网络的预测存储在名为 pred1 的变量中。

您可以省略分号以查看结果。


pred1  = classify(deepnet,img1 )



任务
在脚本中添加命令，对变量 img2 和 img3 中存储的图像的内容进行预测。将预测的类分别存储在名为 pred2 和 pred3 的变量中。

您可以省略分号以查看结果。



pred2  = classify(deepnet,img2  );

pred3  = classify(deepnet,img3  );








