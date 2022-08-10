

matlab :matrix laboratory
https://matlabacademy.mathworks.com/R2022a/cn/portal.html?course=gettingstarted#chapter=8&lesson=1&section=2
https://ww2.mathworks.cn/help/matlab/ref/randi.html

https://matlabacademy.mathworks.com/cn/details/matlab-onramp/gettingstarted

https://matlab.mathworks.com/
dingsai88@buaa.edu.cn

# I.基础命令


保存文件
save datafile.mat


清空工作区
clear   
clc

加载文件
load datafile.mat

仅加载myData.mat中的m变量
load myData  m


仅保存m变量到justm
save justm  m

格式化输出long
format long 

内置常量
x
i

format short


定义数组
x=[7 9]
x=[7,9]
x=1:2:3:4



二维数组  
x=[1 2 3 ; 4 5 6]

x=[sqrt(10)  pi^2]

I.  
创建一个名为 x 的行向量，该向量以 1 开头，以 5 结尾，每个元素的间距为 0.5。
x=1:0.5:5

从1-5 中间间隔1
x=1:1:5
输出：1、2、3、4、5 



创建一个名为 x 的行向量，该向量以 3 开头，以 13 结尾，每个元素的间距为 2。
x=3:2:13

II.
如果您知道向量中所需的元素数目（而不是每个元素之间的间距），则可以改用 linspace 函数：
linspace(first,last,number_of_elements)
创建一个名为 x 的行向量，该向量以 1 开头，以 10 结尾，共包含 5 个元素。
x=linspace(1,10,5)

1  3.25 5.5 7.75 10

II.行向量转列向量
x = 1:3;
x = x'
x =
1
2
3


I.行转列
您可以通过在一条命令中创建行向量并将其全部转置来创建列向量。注意此处使用圆括号来指定运算的顺序。
在一条命令中，创建一个名为 x 的列向量，该向量以 5 开头，以 9 结尾，并且元素之间的间隔为 2。

x=(5:2:9)'

返回
5
7
9



I.请注意，如果您使用 linspace 或 : 创建向量，则不需要使用方括号 ([])。

如果您要创建从 1 到 2π 的等间距向量，其中包含 100 个元素，您会使用 linspace 还是 :？

x=linspace(1,2*pi,100)


I.随机矩阵 随机数组

任务
创建一个名为 x 的变量，该变量是一个 5×5 的随机数矩阵。
x=rand(5)

I.
任务
使用 rand 创建一个包含 5 行 1 列的数组。将结果赋给名为 x 的变量。

x=rand(5,1)


I.任务
使用 zeros 函数创建一个包含 6 行 3 列 (6×3) 的全零矩阵。将结果赋给名为 x 的变量。
x=zeros(6,3)



I.任务
创建一个名为 x 的变量，其值为变量 data数组 中位于第 6 行第 3 列的值。

x=data(6,3)


I.任务
使用 end 关键字获取变量 data 的最后一行第三列的值。将该值赋给一个名为 x 的变量。
x=data(end,3)

I.任务
创建一个名为 density 的变量，其中包含名为 data 的矩阵中第 2 列上的所有元素。

density=data(:,2)




用作索引时，冒号运算符 (:) 可指代该维度中的所有元素。以下语法
x = A(2,:)
会创建一个包含 A 中第 2 行上所有元素的行向量。




I.任务
创建一个名为 volumes 的变量，其中包含 data 的最后两列的所有元素。
volumes = data(:,3:4)


I.任务
使用单个索引值创建一个名为 p 并且包含向量 density 中的第 6 个元素的变量。

p=density(6)



任务
创建一个名为 v2 并且包含 data 的最后一列元素的向量。

v2=data(:,end)



任务
将 v2 中的第一个元素从 NaN 更改为 0.5。
v2(1,1)=0.5
 
NaN
2.1328
3.6852
8.5389
10.1570
2.8739
4.4508


任务
将 data 的第一行和最后一列中元素的值更改为 0.5。
data(1,end)=0.5


任务
将 1 与 v1 的每个元素相加，然后将结果存储在名为 r 的变量中。
MATLAB 的设计让您能够自然地处理数组。例如，您可以将一个标量值与数组中的所有元素相加。 y = x + 2

r=1+v1


任务
创建一个向量 vs，它是向量 v1 和 v2 的和。
vs=v1+v2


任务
创建一个变量 vm，其值为 va 向量的最大值。
vm=max(va)


任务
使用 round 函数创建一个名为 vr 的变量，其中包含四舍五入为整数的平均体积 va。

vr=round(va)



任务
创建一个名为 mass 的变量，其中包含 density 和 va 的按元素乘积。
mass=density.*va



任务
创建一个名为 dsize 的变量，其值为 data 变量的大小。

dsize=size(data)



任务
创建变量 dr 和 dc，其中分别包含变量 data 的行数和列数。

size 函数可以应用于矩阵，以生成单个输出变量或两个输出变量。使用方括号 ([ ]) 获得多个输出。
[xrow,xcol] = size(x)

[dr,dc]=size(data)


任务
创建变量 vMax 和 ivMax，其中分别包含 v2 向量的最大值和对应的索引值。
可以使用 max 函数确定向量的最大值及其对应的索引值。max 函数的第一个输出为输入向量的最大值。执行带两个输出的调用时，第二个输出为索引值。
[xMax,idx] = max(x)

[vMax,ivMax]=max(v2)







任务
参考 randi 的文档以完成以下任务。

创建一个名为 x 的矩阵，
其中包含 1 到 20 范围的随机整数
行数为 5
列数为 7

x=randi(20,5,7)
https://ww2.mathworks.cn/help/matlab/ref/randi.html



任务
创建一个绘图，其中 sample 位于 x 轴，mass1 位于 y 轴。
可以使用 plot 函数在一张图上绘制两个相同长度的向量。
plot(x,y)

plot(sample,mass1)


load datafile
sample = data(:,1);
density = data(:,2);
v1 = data(:,3);
v2 = data(:,4);
mass1 = density.*v1;
mass2 = density.*v2;

plot(sample,mass1)



任务
绘制 mass2（y 轴）对 sample（x 轴）的图。在绘图中使用红色 (r) 星号 (*) 标记，并且不使用线条。
plot 函数接受一个附加参数。使用该参数，您可以通过在引号中包含不同符号的方式来指定与之对应的颜色、线型和标记样式。
plot(x,y,"r--o")
以上命令将会绘制一条红色 (r) 虚线 (--)，并使用圆圈 (o) 作为标记。您可以在线条设定的文档中了解有关可用符号的详细信息。


plot(sample,mass2,"r*")



任务
输入 hold on 命令。
然后绘制 mass1（y 轴）对 sample（x 轴）的图，并带有黑色 (k) 方形 (s) 标记，不带线条。
请注意，每个绘图命令都创建了一个单独的绘图。要在一张图上先后绘制两条线，请使用 hold on 命令保留之前的绘图，然后添加另一条线。

hold on
plot(sample,mass1,"ks")



启用保留状态时，将继续在同一坐标区上绘图。要恢复默认绘图行为，即其中每个绘图都有自己的坐标区，请输入 hold off。




当您单独绘制一个向量时，MATLAB 会使用向量值作为 y 轴数据，并将 x 轴数据的范围设置为从 1 到 n（向量中的元素数目）。
plot(v1)


plot 函数接受可选的附加输入，这些输入由一个属性名称和一个关联的值组成。
plot(y,"LineWidth",5)
以上命令将绘制一条粗线。您可以在线条属性文档中了解更多可用属性的详细信息。
任务
绘制 v1，线宽为 3。

plot(v1,"LineWidth",3)



使用 plot 函数时，您可在绘图参数和线条设定符之后添加属性名称-属性值对组。
plot(x,y,"ro-","LineWidth",5)



使用 plot 函数时，您可在绘图参数和线条设定符之后添加属性名称-属性值对组。
plot(x,y,"ro-","LineWidth",5)
任务
绘制 v1（y 轴）对 sample（x 轴）的图，使用红色 (r) 圆圈 (o) 标记，线宽为 4。

plot(sample,v1,"ro","LineWidth",4)



可以使用绘图注释函数（例如 title）在绘图中添加标签。此类函数的输入是一个字符串。MATLAB 中的字符串是用双引号 (") 引起来的。
title("Plot Title")

load datafile
sample = data(:,1);
density = data(:,2);
v1 = data(:,3);
v2 = data(:,4);
mass1 = density.*v1;
mass2 = density.*v2;

plot(sample,mass1,"ks")
hold on
plot(sample,mass2,"r*")
hold off

title("Sample Mass")


使用 ylabel 函数添加标签 "Mass (g)"。



ylabel("Mass (g)")



您可以使用 legend 函数为绘图添加图例。
legend("Exp A","Exp B")




任务
创建一个包含所有三列数据的绘图。使用 yrs 作为 x 数据。按下面的顺序和绘图设定绘制数据：
res：蓝色 (b) 虚线 (--)
comm：黑色 (k) 点线 (:)
ind：洋红色 (m) 点划线 (-.)

plot(yrs,res,"b--")
hold on
plot(yrs,comm,"k:")
plot(yrs,ind,"m-.")
hold off




任务
将标题 "July Electricity Usage" 添加到现有绘图。

用值 "res"、"comm" 和 "ind" 创建图例。


title("July Electricity Usage")
legend("res","comm","ind")


# I.音频频率



t=t/fs
plot(t,y)

yfft=abs(fft(y))


f=0:n-1


f=f*fs/n
plot(f,yfft)
xlim([0 1000])



