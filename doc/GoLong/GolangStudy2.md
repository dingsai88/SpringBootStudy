


Go 是Google的 Robert Griesemer, Rob Pike 及Ken Thompson(肯尼斯·蓝·汤普森)开发的一种编程语言

golong

c/c++很快，但是编译速度慢


Gopher
地鼠;金花鼠 ˈɡəʊfə


工具
vscode

Visual Studio Code




官网:
https://go.dev/doc/tutorial/getting-started



cd D:\DingSai\study\语言\go


I.go mod init example/hello
D:\DingSai\study\语言\go>go mod init example/hello
go: creating new go.mod: module example/hello


I.代码

package main
import "fmt"
func main(){

fmt.Println("hello")

}


I.运行:go run .
D:\DingSai\study\语言\go>go run .
hello


I.查看命令:go help
D:\DingSai\study\语言\go>go help
Go is a tool for managing Go source code.

Usage:

        go <command> [arguments]

The commands are:

        bug         start a bug report
        build       compile packages and dependencies
        clean       remove object files and cached files
        doc         show documentation for package or symbol
        env         print Go environment information
        fix         update packages to use new APIs
        fmt         gofmt (reformat) package sources
        generate    generate Go files by processing source
        get         add dependencies to current module and install them
        install     compile and install packages and dependencies
        list        list packages or modules
        mod         module maintenance
        work        workspace maintenance
        run         compile and run Go program
        test        test packages
        tool        run specified go tool
        version     print Go version
        vet         report likely mistakes in packages

Use "go help <command>" for more information about a command.


I.老版本代码

package main
import "fmt"
import "rsc.io/quote"
func main(){
fmt.Println("hello")
fmt.Println(quote.Go())
fmt.Println("end")
}




II.没有package
D:\DingSai\study\语言\go>go run .
hello.go:3:8: no required module provides package rsc.io/quote; to add it:
go get rsc.io/quote



II.go mod tidy


D:\DingSai\study\语言\go>go mod tidy
go: finding module for package rsc.io/quote
go: downloading rsc.io/quote v1.5.2
go: found rsc.io/quote in rsc.io/quote v1.5.2
go: downloading rsc.io/sampler v1.3.0
go: downloading golang.org/x/text v0.0.0-20170915032832-14c0d48ead0c



D:\DingSai\study\语言\go>go run .
hello
Don't communicate by sharing memory, share memory by communicating.
end


I.编译、执行go build hello.go
D:\DingSai\study\语言\go>go build hello.go

D:\DingSai\study\语言\go>hello.exe
hello
Don't communicate by sharing memory, share memory by communicating.
end


hello.exe没有go环境也可以运行

II.编译+执行 go run hello.go


go build hello.go


hello.exe没有go环境也可以运行


I.差异

(1)文件以”go”为扩展名
(2)程序的执行入口是main0函致
(3)严格区分大小写
(4)方法由一条条语句构成，每个语句后不需要分号(Go语言会在每行后自动加分号)，这也体现出Golang的简洁性。
(5)Go编译器是一行行进行编译的，因此我们一行就写一条语句，不能把多条语句写在同一个，否则报错。
(6)定义的变量或者import的包如果没有使用到，代码不编译通过
(7)大号都是成对出现的，缺一不可
(8)大括号不能随意换行
(9)字符串,可以相加

I.注释 和java一样
单行 //
多行 /** */


I.代码缩进

//只是效果展示 不写入源文件
gofmt  hello.go

//格式化并写入源文件
gofmt -w hello.go


I.API https://golang.org

中文 https://studygolang.com/pkgdoc



I.变量类型
II.var age int =18


II.var age int
age=18



II.var age =18
省去int


II. sex:="男"



I.一次申请多个变量

var n1 ,n2,n3 int

var n1 ,n2,n3 =10,"你好",3333

var (
n2 = 222
s2 = "string1"
)

I.全局变量(函数外)，局部变量(函数内)


I.数据类型

II.基本数据类型
数值
字符型
布尔
字符串


II.派生数据类型\复杂数据类型
指针
数字
结构体
管道
函数内切片
接口
map



II.占位符

https://zhuanlan.zhihu.com/p/415843240

%v：获取数据的值，如果实现了error 接口，仅表示错误消息。


%T 获取数据类型。

%t 布尔占位符

整数占位符

%b  二进制。


%c Unicode 码转字符。

%d、%5d、%-5d、%05d  十进制整数表示。


%s 字符串或字节切片。

%p、%#p  指针： 地址，使用十六进制表示，%p 带 0x，%#p 不带。






II.整数类型介绍

III.有符号整数类型
int8(-128~127)、int16(-32768~32767)、int32(-2147483648 ~2147483647)、int64(-9223372036854775808 ~ 9223372036854775807)
分别占用 1个字节 2个字节 4个字节 8个字节

1.1字节8位,第一位符号位,标识正负，后边剩下7位
00000000

7位=127


2.溢出	var n5 int8 = 222
cannot use 222 (untyped int constant) as int8 value in variable declaration (overflows)

3.int 操作系统区别(32位系统4字节int32、64位系统8字节int64)
go语言中的 int 的大小是和操作系统位数相关的，如果是32位操作系统，int 类型的大小就是4字节。如果是64位操作系统，int 类型的大小就是8个字节
// int is a signed integer type that is at least 32 bits in size. It is a
// distinct type, however, and not an alias for, say, int32.



III.无符号整数类型
uint8(255)、uint16(2^16-1)、uint32(2^32-1)、uint64(2^64-1)







II.浮点型 float32、float64

float32
IEEE-754 32位浮点型数

float64
IEEE-754 64位浮点型数


complex64
32 位实数和虚数


complex128
64 位实数和虚数




II.其他数字类型


byte类型 : 无符号 等价uint8  
rune类型 :有符号 等价int32


uint 32 或 64 位: (和操作系统有关)
int与 uint 一样大小 (和操作系统有关)




uintptr 无符号整型:用于存放一个指针




III.byte(0-255): ascii


//97
var c1 byte='a'

var c2 byte='b'

//99
var c3 byte='c'

	fmt.Println(c1,c2,c3)

打印： 97  98 99

//注意是Printf
fmt.Printf(" 原始字符 %c",c3)
打印：  原始字符 c


byte(0-255): ascii


unicode字符集

utf-8是字符集其中一个编码方案


转义字符
\n 换行
\t 组成8位制表符
\t —制表符
\n—换行符
\ —1个
"—1个 ”
\r —回车





II.bool类型:只有true和false
只占用1个字节 底层是0、1
适用于逻辑运算和流程控制




II.字符串类型

字符串就是一串固定长度的字符连接起来的字符序列。Go 的字符串是由单个字节连接起来的。Go 语言的字符串的字节使用 UTF-8 编码标识 Unicode 文本。

1.字符串不可变：修改下标内容不可以
2.字符串拼接 + 在一行最后



I.基本数据类型默认值
数字和浮点都是0
bool false
字符串  "" 空串




II.数据类型转换成string


III.转成二进制输出
import "fmt"
import "strconv"

	var f1=22
	var f1str=strconv.FormatInt(int64(f1),2)
	//输出 数字:22  转成二进制:10110
	fmt.Printf("数字:%d  转成二进制:%s",f1,f1str)



II.string转换成 数据

无效数据，错误数据，给数据类型的默认值


var strB string="true"
var booaa bool
//可能会有error
booaa, _ =strconv.ParseBool(strB)
//字符转bool换后  类型: bool  value: true
fmt.Printf("字符转bool换后  类型: %T  value: %v ",booaa,booaa)



	var strC string="99"
	var  i99 int64
	//10代码10进制，64代表int64
	//base指定进制（2到36）
	//bitSize指定结果必须能无溢出赋值的整数类型，0、8、16、32、64 分别代表 int、int8、int16、int32、int64；
	i99, _ =strconv.ParseInt(strC,10,32)
	//字符转int换后  类型: int64  value: 99
  	fmt.Printf("字符转int换后  类型: %T  value: %v ",i99,i99)





	var strD string="99.9"
	var  ff1 float64
	ff1, _ =strconv.ParseFloat(strD,64)
	//字符转float64换后  类型: float64  value: 99.9 
	fmt.Printf("字符转float64换后  类型: %T  value: %v ",ff1,ff1)
	
	
	错误的转换给 数据类型的默认值
	var strE string="你好"
	var  ff2 float64
	ff2, _ =strconv.ParseFloat(strE,64) 
	//错误字符转float64换后  类型: float64  value: 0 
	fmt.Printf("错误字符转float64换后  类型: %T  value: %v ",ff2,ff2)




II.指针

//ptr1指针类型返回地址， *ptr1返回指针的value  &ptr1:返回本指针的地址
var ptr1 *int64=&i
//i变量的类型:int64 value:1   内存地址:0xc00000a0e0
fmt.Printf("ptr1变量是指针类型:%T value:%v   内存地址:%p  指针原本的值是:%v  指针自己的地址是:%v",ptr1,ptr1,ptr1,*ptr1,&ptr1)
fmt.Println("\n ")
//用指针修改value
*ptr1=20
//ptr1变量是指针类型:*int64 value:0xc00000a0e0   内存地址:0xc00000a0e0  指针原本的值是:20  指针自己的地址是:0xc00005a028
fmt.Printf("ptr1变量是指针类型:%T value:%v   内存地址:%p  指针原本的值是:%v  指针自己的地址是:%v",ptr1,ptr1,ptr1,*ptr1,&ptr1)


	1.指针一定是地址值
	2.指针变量的地址类型必须匹配


II.起名规则


1.package main是程序的入口包
包名字和目录一致

2.驼峰

3.首字母大写外部可访问,首字母小写不可以
变量名、常量、函数




II.关键字


类别	关键字	含义
类、方法和变量修饰符	chan	定义一个channel
interface	定义接口
func	函数定义
map	map结构类型
struct	定义结构体
type	定义类型
var	声明变量
const	声明常量
程序控制	break	提前跳出一个块
continue	回到一次循环的开始处
return	从方法中返回
if	条件语句的判断
else	用在条件语句中，表明当条件不成立时的分支
for	循环
switch	分支语句结构的引导词
case	用在switch语句之中，表示其中的一个分支
fallthrough	如果case带有fallthrough，程序会继续执行下一条case,不会再判断下一条case的值
default	默认选项，用在switch和select语句种
select	go语言特有的channel选择结构
defer	延迟执行，函数的收尾工作。在函数结束前执行
go	并发
goto	跳转语句
range	从slice、map等结构中取元素
包相关	import	表明要访问指定的类或包
package	包




II.运算符

https://www.runoob.com/go/go-operators.html






II.获取终端输入，用户输入

//用户输入 获取终端输入，用户输入
fmt.Println("请输入 ")
var impStr string
//到换行结束输入;必须传入地址
fmt.Scanln(&impStr)
fmt.Println("您输入的是 "+impStr)


	//用户输入 获取终端输入，用户输入
	fmt.Println("请录入学生的年龄，姓名，成绩，是否是VIP ")
	var age int
	var name string
	var score float32
	var isVIP bool
	//到换行结束输入;必须传入地址
	fmt.Scanf("%d %s %f %t",&age,&name,&score,&isVIP)
	//您输入的 12 dingsai 33.4 true 
	fmt.Printf("您输入的 %v %v %v %v " ,age,name,score,isVIP)





I.流程控制




II.if

/* 局部变量定义 */
var a int = 100;

/* 判断布尔表达式 */
if a < 20 {
/* 如果条件为 true 则执行以下语句 */
fmt.Printf("a 小于 20\n" );
} else {
/* 如果条件为 false 则执行以下语句 */
fmt.Printf("a 不小于 20\n" );
}




II.switch


var grade string = "B"

switch {
case grade == "A" :
fmt.Printf("优秀!\n" )    
case grade == "B", grade == "C" :
fmt.Printf("良好\n" )      
case grade == "D" :
fmt.Printf("及格\n" )      
case grade == "F":
fmt.Printf("不及格\n" )
default:
fmt.Printf("差\n" );
}
fmt.Printf("你的等级是 %s\n", grade );      
}



II.select

select 是 Go 中的一个控制结构，类似于 switch 语句。

select 语句只能用于通道操作，每个 case 必须是一个通道操作，要么是发送要么是接收。

select 语句会监听所有指定的通道上的操作，一旦其中一个通道准备好就会执行相应的代码块。

如果多个通道都准备好，那么 select 语句会随机选择一个通道执行。如果所有通道都没有准备好，那么执行 default 块中的代码。




	//定义两个管道
	ch1 := make(chan int)
	ch2 := make(chan int)
	//开启线程
	go GoSelectTest(1, ch1)
	go GoSelectTest(2, ch2)
	//等待返回值
	for i:=0;i<2;i++{
		select {
		case msg1 := <-ch1:
			fmt.Println("received1", msg1)
		case msg2 := <-ch2:
			fmt.Println("received2", msg2)
		}
	}


II.break、continue、goto

switch 中 不用写break,java需要

for循环跳出



	//测试goto 和 label1;不打印 b和c
	fmt.Println("aaaaa")
	goto label1
	fmt.Println("bbbb")
	fmt.Println("ccccc")
	label1:
	fmt.Println("dddd")



II.函数 (可以有多个返回值)


func  fname(形参列表,入参)(返回值){

return fanhui
}


值传递:	值传递是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
引用传递:	引用传递是指在调用函数时将实际参数的地址传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。
默认情况下，Go 语言使用的是值传递，即在调用过程中不会影响到实际参数。




1.函数名，开头是字母，大写外部可用，小写本包可用

2.形参列表(入参)形参和实参要对应。

3.不支持方法重载，相同名字的函数
4.函数内修改值，函数外部值不变
5.函数可以是一种数据类型(变量)


func main() {
var a = 6
var b = 4
var result1, result2 = calc(a, b)
//全返回值: 10 2
fmt.Println("全返回值:" ,result1, result2)
//只要一个返回值,不需要的_
var result3, _ = calc(a, b)
fmt.Println("部分返回值:" ,result3)
}
//两个返回值
func calc(a int, b int) (int ,int) {
var sum = a + b
var minus = a - b
return sum, minus
}



//可变长参数
func test(args... int) (int) {
var sum int;
for  i:=0;i<len(args);i++{
sum+=args[i];
}
return sum
}




	//值传递和引用传递
	var result5 = 55
	fmt.Println("传入前:" ,result5)
	pointTest(&result5)
	fmt.Println("传入后:" ,result5)

//传入指针 地址
func pointTest(args *int)  {
*args=99
}



	//函数也可以是变量
	var result6 = 11
	//调用testFunc(&result6)  和这个一样 pointTest(&result6)
	testFunc:=pointTest
	testFunc(&result6)
	fmt.Println("传入后:" ,result6)
	
	
	
		//自定义数据类型 type myInt int ；自定义类型也可以是函数
	type myInt int
	var myInt1 myInt=30
	var tempMyInt1 int=20
	tempMyInt1=int(myInt1)
	fmt.Println("tempMyInt1:" ,tempMyInt1)




I.包引用

使用包的原因:
1.分类函数
2.解决同名问题


I.项目根目录 初始化模块
II.命令:go mod init goStudy.com/mymodule
项目跟目录会创建一个 go.mod
module goStudy.com/mymodule
go 1.21.0





II.命令 go mod tidy
更新和维护你的 Go 项目的 go.mod 文件和 go.sum 文件


II.这样就能导入文件

import "goStudy.com/mymodule/src/Study2023/package1"
func main()  {
package1.MethodTest()
}

任意名称
package package1
import "fmt"
func MethodTest()  {
fmt.Println(" 导入方法中输出:biliStudy5PackageTemp ")
}





I.init 函数 和顺序
//局部变量方法>init>main
var testFunc int=testOrder1();

func testOrder1() int {
fmt.Println("第1个输出 局部变量初始化")
return 1;
}

func init()  {
fmt.Println("第2个输出 init")
}



func main() {
fmt.Println("第3个输出 main")


II.匿名函数
func main() {

result:=fun(num1,num2 int)int{
return num1+num2;
}(10,20)

	fmt.Println(result)
}



II.函数闭包

    //闭包：一共公共sum变量，调用多次sum可以复用
    biBao:=getSum();
    fmt.Println(biBao(1))
	fmt.Println(biBao(2))
	fmt.Println(biBao(3))
/**
闭包：一共公共sum变量，调用多次sum可以复用
*/
func getSum() func(int)int {
var sum int=0
return func(num int) int {
sum=sum+num
return sum
}
}


II.defer推迟


遇到defer，后边的diam 压入栈中(先进后出)，不执行
函数执行完毕，执行栈中语句(先进后出)
func deferTest(num1,num2 int) int {
defer fmt.Println("num1=",num1)
defer fmt.Println("num2=",num2)	  
var sum int=num1+num2
fmt.Println("sum=",sum)
return sum
}




II.字符串比较

	//字符串转int
	var str2 string="12345"
	n,_:=strconv.Atoi(str2)
	fmt.Println("string >> int ",n)

	//int转字符串
	str=strconv.Itoa(123)
	fmt.Println("  int >>string ",str)

	//字符串包含
	bo:=strings.Contains(str2,"34")
	fmt.Println("  字符串包含 strings.Contains(str2,\"34\") :",bo)
	//字符串比较 区分不区分大小写
	bo=strings.EqualFold("aaa","AAA")
	fmt.Println("  不区分大小写比较 strings.EqualFold :",bo)
	fmt.Println("  区分大小写比较 \"a\"==\"A\":","a"=="A")


I.时间函数

	now:=time.Now()
	fmt.Println("Date now: ",now)
	fmt.Println("Date now:年 ",now.Year())
	fmt.Println("Date now:月 ",now.Month())
	fmt.Println("Date now: 日",now.Day())
	fmt.Println("Date now:时 ",now.Hour())
	fmt.Println("Date now:分 ",now.Minute())
	fmt.Println("Date now: 秒",now.Second())

	fmt.Println("日期格式化字符串 ",now.Format("2006-01-02 15:04:05"))


I.内置函数builtin.go
len("")
recover()

num := new(int)
//类型*int 值:0xc00000a5f8 地址:0xc00005a028 指针是:0
fmt.Printf(" 类型%T 值:%v 地址:%v 指针是:%v",num, num, &num, *num)




I.错误处理
defer延迟


Panic恐慌
在Go语言中，当程序遇到无法继续执行的错误时，会触发一个panic。
这个错误可能是由于空指针解引用、数组越界、除以零等情况引起的。
panic会导致程序立即停止执行，并开始沿着调用堆栈向上寻找recover函数。


recover是一个用于恢复程序执行的函数。
当panic发生时，Go语言运行时会查找调用栈中的defer函数，并检查是否存在recover函数。
如果存在，程序将停止继续向上传播panic，并开始执行recover函数。


/**
defer延迟 、panic恐慌、recover组合使用
例子日志:
panic 恐慌开始
panic 测试 recover函数收到的异常信息:panic 抛出的异常信息
*/
func recoverExample() {
defer func() {
if r := recover(); r != nil {
fmt.Println("panic 测试 recover函数收到的异常信息:", r)
}
}()
fmt.Println("panic 恐慌开始")
//抛异常
panic(" panic 抛出的异常信息")
// 这行代码不会被执行
fmt.Println("panic 此句不会执行")
}




I.数组
II.普通赋值
var numbers = [5]int{1, 2, 3, 4, 5}
numbers := [5]int{1, 2, 3, 4, 5}

II.不确定长度
var balance = [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
balance := [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}

II.通过下标赋值
balance := [5]float32{1:2.0,3:7.0}


	var arrTest =[5]int{1,2,3,4,5}
	fmt.Println(arrTest)

	/**
	连续内容：根据类型不同，间隔的内存地址不一样,int8字节
	0xc00000e2a0
	0xc00000e2a8
	0xc00000e2b0
	0xc00000e2b8
	0xc00000e2c0
	 */
	fmt.Println(&arrTest[0])
	fmt.Println(&arrTest[1])
	fmt.Println(&arrTest[2])
	fmt.Println(&arrTest[3])
	fmt.Println(&arrTest[4])


//数组遍历方法1
for i:=0;i<len(arrTest);i++{
fmt.Println(arrTest[i])
}

	//数组遍历2: range 范围遍历
	for key,value :=range arrTest{
		fmt.Println("range遍历key 下标 ",key,value)
	}


	var arrTest2 =[10]int{1,2,3,4,5}
	//数组的类型 arrTest1:[5]int  arrTest2:[10]int   ,数组是带长度的
	fmt.Printf("数组的类型 arrTest1:%T  arrTest2:%T",arrTest,arrTest2)




I.切片
Go 语言切片(Slice)
Go的切片是在数组之上的抽象数据类型，所以创建的切片始终都有一个数组存在。


	/* 创建切片 */
	numbers := []int{0,1,2,3,4,5,6,7,8}

	numbers1 := make([]int,0,5)
 
	/* 允许追加空切片 */
	numbers = append(numbers, 0)
 
	/* 拷贝 numbers 的内容到 numbers1 */
	copy(numbers1,numbers)



I.映射map


	// map创建
	var map1 =map[string]int{"key1":1,"key2":2}
	var map2 map[string]string= make(map[string]string)
	
	fmt.Println(map1)
	//加数据
	map1["ke3"]=3
	/* map 插入 key - value 对,各个国家对应的首都 */
	map2 [ "Google" ] = "谷歌"
	
	fmt.Println(map1)
	//删除数据
	delete(map1,"key1")
	fmt.Println(map1)

	//遍历
	for key := range map1 {
		fmt.Println(key, " value", map1 [ key ])
	}
 

	//遍历2
	for key,value :=range map2{
		fmt.Println("range遍历key 下标 ",key,value)
	}
	
	//删除数据
	delete(map1,"key2")
		/**
	清空，
	1 遍历删除
	2.重新make
	 */
	map2= make(map[string]string)



I. 面向对象struct结构体

II.创建
//创建方式1
var t1 Teacher

//创建方式2
var t2 Teacher=Teacher{1,"nnn",19}

//创建方式3 指针方式
var t3Point *Teacher=new(Teacher)

创建方式4
var t3Point *Teacher=&Teacher{}

II.赋值
t1.id=1
t1.name="daniel"
t1.age=18

II.强制类型转换

	var t2 Teacher=Teacher{1,"nnn",19}
	var t3 Student
	//1.个数类型相同可以强制类型转换
	t3=Student(t2)
	fmt.Println(t3)
	//2.结构体自己起了别名，也要强制类型转换

II.结构体绑定方法

/**值传递
1.Teacher结构体绑定本方法
2.值传递，修改不影响原数据
fmt.Println(t2.structBindMethod())
*/
func (t Teacher)structBindMethod()string  {
t.name="方法里的新名字"
return t.name
}
/**
引用传递:指针类型传递，会变对象传递
(&t2).structBindMethodPoint()
t2.structBindMethodPoint()
*/
func (t *Teacher)structBindMethodPoint()string  {
(*t).name="新名字2"
return t.name
}



type Teacher struct{
id int
name string
age int
}
type Student struct{
id int
name string
age int
}





curl -H "Content-Type:application/json" -X POST --data "{\"userId\":\"11111\"}" http://127.0.0.1:8080/person


curl -X POST -H "Content-Type: application/json" -d '{"id": 123, "name": "John", "age": 30}' http://localhost:8080/person

curl -X POST -H "Content-Type: application/json" -d '{\"id\": 123, \"name\": \"John\", \"age\": 30}' http://localhost:8080/person






灯塔全部标签梳理(共189)+提SQL清理空间




