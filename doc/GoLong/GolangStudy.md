




## I.程序执行和编译成二进制

II.运行源文件:
go run HelloWorld.go

II.编译成二进制exe：
go build HelloWorld.go

III.执行编译完的程序:HelloWorld



## I.基础语法

## II.开头大写小写
当标识符（包括常量、变量、类型、函数名、结构字段等等）以一个大写字母开头，
如：Group1，那么使用这种形式的标识符的对象就可以被外部包的代码所使用（客户端程序需要先导入这个包），这被称为导出（像面向对象语言中的 public）；

标识符如果以小写字母开头，则对包外是不可见的，但是他们在整个包的内部是可见并且可用的（像面向对象语言中的 protected ）。




## II.大括号不能单独在一行

II.正常: func main() {

II.异常: func main() 
{




## II.行分隔符(一行代表语句结束)不需要;等

在 Go 程序中，一行代表一个语句结束。每个语句不需要像 C 家族中的其它语言一样以分号 ; 结尾，因为这些工作都将由 Go 编译器自动完成。
如果你打算将多个语句写在同一行，它们则必须使用 ; 人为区分，但在实际开发中我们并不鼓励这种做法。



## II.注释
// 单行注释

/*
222
我是多行注释
*/




## II.标识符(变量名)开头只能是字母或_
标识符用来命名变量、类型等程序实体。一个标识符实际上就是一个或是多个字母(A~Z和a~z)数字(0~9)、下划线_组成的序列，但是第一个字符必须是字母或下划线而不能是数字。



## II.字符串连接(和java一样+)






## II.关键字

break	default	func	interface	select
case	defer	go	map	struct
chan	else	goto	package	switch
const	fallthrough	if	range	type
continue	for	import	return	var


## II.保留字


append	bool	byte	cap	close	complex	complex64	complex128	uint16
copy	false	float32	float64	imag	int	int8	int16	uint32
int32	int64	iota	len	make	new	nil	panic	uint64
print	println	real	recover	string	true	uint	uint8	uintptr



## II.格式化字符串,占位符
 

package main

import (
"fmt"
)

func main() {
// %d 表示整型数字，%s 表示字符串
var stockcode=123
var enddate="2020-12-31"
var url="Code=%d&endDate=%s"
var target_url=fmt.Sprintf(url,stockcode,enddate)
fmt.Println(target_url)
}



## II.数据类型
https://m.runoob.com/go/go-data-types.html


### 布尔型
布尔型的值只可以是常量 true 或者 false。一个简单的例子：var b bool = true。

### 数字类型
整型 int 和浮点型 float32、float64，Go 语言支持整型和浮点型数字，并且支持复数，其中位的运算采用补码。


### 字符串类型:
字符串就是一串固定长度的字符连接起来的字符序列。Go 的字符串是由单个字节连接起来的。Go 语言的字符串的字节使用 UTF-8 编码标识 Unicode 文本。



### 派生类型:
包括：
(a) 指针类型（Pointer）
(b) 数组类型
(c) 结构化类型(struct)
(d) Channel 类型
(e) 函数类型
(f) 切片类型
(g) 接口类型（interface）
(h) Map 类型



Go 也有基于架构的类型，例如：int、uint 和 uintptr。
1	uint8
无符号 8 位整型 (0 到 255)
2	uint16
无符号 16 位整型 (0 到 65535)
3	uint32
无符号 32 位整型 (0 到 4294967295)
4	uint64
无符号 64 位整型 (0 到 18446744073709551615)
5	int8
有符号 8 位整型 (-128 到 127)
6	int16
有符号 16 位整型 (-32768 到 32767)
7	int32
有符号 32 位整型 (-2147483648 到 2147483647)
8	int64
有符号 64 位整型 (-9223372036854775808 到 9223372036854775807)


浮点型
序号	类型和描述
1	float32
IEEE-754 32位浮点型数
2	float64
IEEE-754 64位浮点型数
3	complex64
32 位实数和虚数
4	complex128
64 位实数和虚数


其他数字类型
以下列出了其他更多的数字类型：

序号	类型和描述
1	byte
类似 uint8
2	rune
类似 int32
3	uint
32 或 64 位
4	int
与 uint 一样大小
5	uintptr
无符号整型，用于存放一个指针




## II.Go 语言变量
定义变量 声明变量


### 变量声明1

var identifier type  //定义一个: var a string = "Runoob"

var identifier1, identifier2 type  //定义多个  var b, c int = 1, 2



package main
import "fmt"
func main() {
var a string = "Runoob"
fmt.Println(a)

    var b, c int = 1, 2
    fmt.Println(b, c)
}


### 变量声明2 根据值自行判定变量类型。

var v_name = value  //自动判断类型：  var d = true


package main
import "fmt"
func main() {
var d = true
fmt.Println(d)
}


### 第三种，如果变量已经使用 var 声明过了，再使用 := 声明变量，就产生编译错误，格式：


等于
var intVal int=1

等于
intVal := 1

等于
var intVal=1


可以将 var f string = "Runoob" 简写为 f := "Runoob"：


### 多个变量声明
var vname1, vname2, vname3 = v1, v2, v3 // 和 python 很像,不需要显示声明类型，自动推断

vname1, vname2, vname3 := v1, v2, v3 // 出现在 := 左侧的变量不应该是已经被声明过的，否则会导致编译错误

### 全局变量声明
// 这种因式分解关键字的写法一般用于声明全局变量
var (
vname1 v_type1
vname2 v_type2
)

j = i


### 指针和内存地址
var strEquals1 string="abc"
var strEquals3 *string
strEquals3=&strEquals1
fmt.Println("赋值判断3 引用类型:",strEquals1,strEquals3)

赋值判断3 引用类型: xyz 0xc000042220





## II.Go 语言常量

const identifier [type] = value

显式类型定义： const b string = "abc"
隐式类型定义： const b = "abc"



常量还可以用作枚举：

const (
Unknown = 0
Female = 1
Male = 2
)


常量可以用len(), cap(), unsafe.Sizeof()函数计算表达式的值。常量表达式中，函数必须是内置函数，否则编译不过：
import "unsafe"
const (
a = "abc"
b = len(a)
c = unsafe.Sizeof(a)
)



#### iota
iota，特殊常量，可以认为是一个可以被编译器修改的常量。


import "fmt"

func main() {
const (
a = iota   //0
b          //1
c          //2
d = "ha"   //独立值，iota += 1
e          //"ha"   iota += 1
f = 100    //iota +=1
g          //100  iota +=1
h = iota   //7,恢复计数
i          //8
)
fmt.Println(a,b,c,d,e,f,g,h,i)

0 1 2 ha ha 100 100 7 8


#### 左移

import "fmt"
const (
i=1<<iota
j=3<<iota
k
l
)

func main() {
fmt.Println("i=",i)
fmt.Println("j=",j)
fmt.Println("k=",k)
fmt.Println("l=",l)
}

结果:
i= 1
j= 6
k= 12
l= 24

i=1：左移 0 位，不变仍为 1。
j=3：左移 1 位，变为二进制 110，即 6。
k=3：左移 2 位，变为二进制 1100，即 12。
l=3：左移 3 位，变为二进制 11000，即 24。


## II.Go 语言运算符

### 算术运算符


下表列出了所有Go语言的算术运算符。假定 A 值为 10，B 值为 20。

运算符	描述	实例
+	相加	A + B 输出结果 30
-	相减	A - B 输出结果 -10
*	相乘	A * B 输出结果 200 
/	相除	B / A 输出结果 2
%	求余	B % A 输出结果 0
++	自增	A++ 输出结果 11
--	自减	A-- 输出结果 9


### 关系运算符

==	检查两个值是否相等，如果相等返回 True 否则返回 False。	(A == B) 为 False
!=	检查两个值是否不相等，如果不相等返回 True 否则返回 False。	(A != B) 为 True
>	检查左边值是否大于右边值，如果是返回 True 否则返回 False。	(A > B) 为 False
<	检查左边值是否小于右边值，如果是返回 True 否则返回 False。	(A < B) 为 True
>=	检查左边值是否大于等于右边值，如果是返回 True 否则返回 False。	(A >= B) 为 False
<=	检查左边值是否小于等于右边值，如果是返回 True 否则返回 False。	(A <= B) 为 True



### 逻辑运算符

&&	逻辑 AND 运算符。 如果两边的操作数都是 True，则条件 True，否则为 False。	(A && B) 为 False
||	逻辑 OR 运算符。 如果两边的操作数有一个 True，则条件 True，否则为 False。	(A || B) 为 True
!	逻辑 NOT 运算符。 如果条件为 True，则逻辑 NOT 条件 False，否则为 True。	!(A && B) 为 True



### 位运算符

&, |, 和 ^ 

&	按位与运算符"&"是双目运算符。 其功能是参与运算的两数各对应的二进位相与。	(A & B) 结果为 12, 二进制为 0000 1100
|	按位或运算符"|"是双目运算符。 其功能是参与运算的两数各对应的二进位相或	(A | B) 结果为 61, 二进制为 0011 1101
^	按位异或运算符"^"是双目运算符。 其功能是参与运算的两数各对应的二进位相异或，当两对应的二进位相异时，结果为1。	(A ^ B) 结果为 49, 二进制为 0011 0001
<<	左移运算符"<<"是双目运算符。左移n位就是乘以2的n次方。 其功能把"<<"左边的运算数的各二进位全部左移若干位，由"<<"右边的数指定移动的位数，高位丢弃，低位补0。	A << 2 结果为 240 ，二进制为 1111 0000
>>	右移运算符">>"是双目运算符。右移n位就是除以2的n次方。 其功能是把">>"左边的运算数的各二进位全部右移若干位，">>"右边的数指定移动的位数。	A >> 2 结果为 15 ，二进制为 0000 1111





### 赋值运算符

运算符	描述	实例
=	简单的赋值运算符，将一个表达式的值赋给一个左值	C = A + B 将 A + B 表达式结果赋值给 C
+=	相加后再赋值	C += A 等于 C = C + A
-=	相减后再赋值	C -= A 等于 C = C - A
*=	相乘后再赋值	C *= A 等于 C = C * A
/=	相除后再赋值	C /= A 等于 C = C / A
%=	求余后再赋值	C %= A 等于 C = C % A
<<=	左移后赋值	C <<= 2 等于 C = C << 2
>>=	右移后赋值	C >>= 2 等于 C = C >> 2
&=	按位与后赋值	C &= 2 等于 C = C & 2
^=	按位异或后赋值	C ^= 2 等于 C = C ^ 2
|=	按位或后赋值	C |= 2 等于 C = C | 2




### 其他运算符


运算符	描述	实例
&	返回变量存储地址	&a; 将给出变量的实际地址。
*	指针变量。	*a; 是一个指针变量





## II.Go 语言条件语句


语句	描述
if 语句	if 语句 由一个布尔表达式后紧跟一个或多个语句组成。
if...else 语句	if 语句 后可以使用可选的 else 语句, else 语句中的表达式在布尔表达式为 false 时执行。
if 嵌套语句	你可以在 if 或 else if 语句中嵌入一个或多个 if 或 else if 语句。
switch 语句	switch 语句用于基于不同条件执行不同动作。
select 语句	select 语句类似于 switch 语句，但是select会随机执行一个可运行的case。如果没有case可运行，它将阻塞，直到有case可运行。


### Go语言if语句  没有小括号

/* 定义局部变量 */
var a int = 10

/* 使用 if 语句判断布尔表达式 */
if a < 20 {
/* 如果条件为 true 则执行以下语句 */
fmt.Printf("a 小于 20\n" )
}


### Go语言if语句else  没有小括号

/* 判断布尔表达式 */
if a < 20 {
/* 如果条件为 true 则执行以下语句 */
fmt.Printf("a 小于 20\n" );
} else {
/* 如果条件为 false 则执行以下语句 */
fmt.Printf("a 不小于 20\n" );
}
fmt.Printf("a 的值为 : %d\n", a);


### Go语言if语句嵌套  没有小括号


/* 定义局部变量 */
var a int = 100
var b int = 200

/* 判断条件 */
if a == 100 {
/* if 条件语句为 true 执行 */
if b == 200 {
/* if 条件语句为 true 执行 */
fmt.Printf("a 的值为 100 ， b 的值为 200\n" );
}
}
fmt.Printf("a 值为 : %d\n", a );
fmt.Printf("b 值为 : %d\n", b );


### Go语言switch语句  不需要break(默认带break功能c)、新增fallthrough(落空)



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




var marks int = 90

switch marks {
case 90: grade = "A"
case 80: grade = "B"
case 50,60,70 : grade = "C"
default: grade = "D"  
}





var x interface{}

switch i := x.(type) {
case nil:  
fmt.Printf(" x 的类型 :%T",i)                
case int:  
fmt.Printf("x 是 int 型")                      
case float64:
fmt.Printf("x 是 float64 型")          
case func(int) float64:
fmt.Printf("x 是 func(int) 型")                      
case bool, string:
fmt.Printf("x 是 bool 或 string 型" )      
default:
fmt.Printf("未知型")    
}  

#### fallthrough 落空
使用 fallthrough 会强制执行后面的 case 语句，fallthrough 不会判断下一条 case 的表达式结果是否为 true。



func main() {

	switch {
	case false:
		fmt.Println("1、case 条件语句为 false")
		fallthrough
	case true:
		fmt.Println("2、case 条件语句为 true")
		fallthrough
	case false:
		fmt.Println("3、case 条件语句为 false")
		fallthrough
	case true:
		fmt.Println("4、case 条件语句为 true")
	case false:
		fmt.Println("5、case 条件语句为 false")
		fallthrough
	default:
		fmt.Println("6、默认 case")
	}

}

以上代码执行结果为：

2、case 条件语句为 true
3、case 条件语句为 false
4、case 条件语句为 true



上面例子里的 case，好像都只从信道中读取数据，但实际上，select 里的 case 表达式只要求你是对信道的操作即可，不管你是往信道写入数据，还是从信道读出数据。




#### Go语言 select语句(没太看懂,每个条件都计算)

https://m.runoob.com/go/go-select-statement.html

	var c1, c2, c3 chan int

chan通道：



 
## II.Go 语言循环语句; { break 语句 ;新增返回值 break re   || re:}  、{continue 语句  ;新增 标记 标 || re: }

break 跳出一层for循环
breank re    可跳出多层指定的点 re:


无限循环

    for true  {
        fmt.Printf("这是无限循环。\n");
    }


for init; condition; post { }

init： 一般为赋值表达式，给控制变量赋初值；
condition： 关系表达式或逻辑表达式，循环控制条件；
post： 一般为赋值表达式，给控制变量增量或减量。

//普通循环 
sum := 0
for i := 0; i <= 10; i++ {
  sum += i
}
  fmt.Println(sum)
}


//类似while
sum := 1
for ; sum <= 10; {
  sum += sum
}
  fmt.Println(sum)


//循环数组 遍历数组  foreach
strings := []string{"google", "runoob"}
for i, s := range strings {
  fmt.Println(i, s)
}

0 google
1 runoob




numbers := [6]int{1, 2, 3, 5}
for i,x:= range numbers {
  fmt.Printf("第 %d 位 x 的值 = %d\n", i,x)
}  

第 0 位 x 的值 = 1
第 1 位 x 的值 = 2
第 2 位 x 的值 = 3
第 3 位 x 的值 = 5
第 4 位 x 的值 = 0
第 5 位 x 的值 = 0

// map遍历
func main() {
map1 := make(map[int]float32)
map1[1] = 1.0
map1[2] = 2.0
map1[3] = 3.0
map1[4] = 4.0

    // 读取 key 和 value
    for key, value := range map1 {
      fmt.Printf("key is: %d - value is: %f\n", key, value)
    }

    // 读取 key
    for key := range map1 {
      fmt.Printf("key is: %d\n", key)
    }

    // 读取 value
    for _, value := range map1 {
      fmt.Printf("value is: %f\n", value)
    }
}



## II.Go goto语句

Go 语言的 goto 语句可以无条件地转移到过程中指定的行。

goto 语句通常与条件语句配合使用。可用来实现条件转移， 构成循环，跳出循环体等功能。

但是，在结构化程序设计中一般不主张使用 goto 语句， 以免造成程序流程的混乱，使理解和调试程序都产生困难。






## II.Go 语言函数(方法)


函数是基本的代码块，用于执行一个任务。

Go 语言最少有个 main() 函数。

你可以通过函数来划分不同功能，逻辑上每个函数执行的是指定的任务。

函数声明告诉了编译器函数的名称，返回类型，和参数。

Go 语言标准库提供了多种可动用的内置的函数。例如，len() 函数可以接受不同类型参数并返回该类型的长度。如果我们传入的是字符串则返回字符串的长度，如果传入的是数组，则返回数组中包含的元素个数。


func function_name( [parameter list] ) [return_types] {
  函数体
}


func 函数名方法名字( 入参 ) [出参类型] {
  函数体
}

函数定义解析：

func：函数由 func 开始声明
function_name：函数名称，参数列表和返回值类型构成了函数签名。
parameter list：参数列表，参数就像一个占位符，当函数被调用时，你可以将值传递给参数，这个值被称为实际参数。参数列表指定的是参数类型、顺序、及参数个数。参数是可选的，也就是说函数也可以不包含参数。
return_types：返回类型，函数返回一列值。return_types 是该列值的数据类型。有些功能不需要返回值，这种情况下 return_types 不是必须的。
函数体：函数定义的代码集合。



 var retMax = max(111, 222)
//占位符
 fmt.Printf("function函数 方法测试 占位符: %d  \n",retMax)



/* 函数返回两个数的最大值 */
func max(num1, num2 int) int {
/* 定义局部变量 */
var result int

	if (num1 > num2) {
		result = num1
	} else {
		result = num2
	}
	return result
}




###  函数参数:值传递、引用传递
函数参数
函数如果使用参数，该变量可称为函数的形参。

形参就像定义在函数体内的局部变量。

调用函数，可以通过两种方式来传递参数：



传递类型	描述
值传递	值传递是指在调用函数时将实际参数复制一份传递到函数中，这样在函数中如果对参数进行修改，将不会影响到实际参数。
引用传递	引用传递是指在调用函数时将实际参数的地址传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。

**值传递(不影响原有值)默认**
值传递(不影响原有值)默认:默认情况下，Go 语言使用的是值传递，即在调用过程中不会影响到实际参数。

//不影响原结果
func swap1(x, y int) int {
  var temp int
  temp = x /* 保存 x 的值 */
  x = y    /* 将 y 值赋给 x */
  y = temp /* 将 temp 值赋给 y*/
  return temp;
}

**值传递(影响原有值):相当于C语言传地址**
引用传递是指在调用函数时将实际参数的地址传递到函数中，那么在函数中对参数所进行的修改，将影响到实际参数。

/* 影响原有结果*/
func swap2(x *int, y *int) {
  var temp int
  temp = *x    /* 保持 x 地址上的值 */
  *x = *y      /* 将 y 值赋给 x */
  *y = temp    /* 将 temp 值赋给 y */
}




###  函数用法(go语言有函数，有方法):作为另外一个函数的实参、闭包、方法  

https://github.com/dingsai88/GoStudy/blob/master/StudyFunction/GoFunctionStudy.go


函数作为另外一个函数的实参	函数定义后可作为另外一个函数的实参数传入
闭包	闭包是匿名函数，可在动态编程中使用
方法	方法就是一个包含了接受者的函数


**函数作为实参**

/* 声明函数变量 */  

getSquareRoot := func(x float64) float64 {  
  return math.Sqrt(x)  
}

/* 使用函数 */
fmt.Println(getSquareRoot(9))



**函数闭包:(不太好理解)可计数**  
 

func getSequence() func() int {  
    i:=0  
  return func() int {  
    i+=1
  return i  
 }
}

func main(){
  /* nextNumber 为一个函数，函数 i 为 0 */

  nextNumber := getSequence()

  /* 调用 nextNumber 函数，i 变量自增 1 并返回 */
  fmt.Println(nextNumber())
  fmt.Println(nextNumber())
  fmt.Println(nextNumber())

  /* 创建新的函数 nextNumber1，并查看结果 */
  nextNumber1 := getSequence()  
  fmt.Println(nextNumber1())
  fmt.Println(nextNumber1())
}



**函数方法:**

Go 语言中同时有函数和方法。一个方法就是一个包含了接受者的函数，接受者可以是命名类型或者结构体类型的一个值或者是一个指针。  
所有给定类型的方法属于该类型的方法集。  
语法格式如下：  

/* 定义结构体 */
type Circle struct {  
  radius float64  
}

func main() {  
  var c1 Circle  
  c1.radius = 10.00  
  fmt.Println("圆的面积 = ", c1.getArea())  
}

func (c Circle) getArea() float64 {  
   //c.radius 即为 Circle 类型对象中的属性  
   return 3.14 * c.radius * c.radius  
}         



## II.Go 语言变量作用域


作用域为已声明标识符所表示的常量、类型、变量、函数或包在源代码中的作用范围。

Go 语言中变量可以在三个地方声明：

函数内定义的变量称为局部变量
函数外定义的变量称为全局变量
函数定义中的变量称为形式参数



**局部变量**
在函数体内声明的变量称之为局部变量，它们的作用域只在函数体内，参数和返回值变量也是局部变量。




**全局变量**
在函数体外声明的变量称之为全局变量，全局变量可以在整个包甚至外部包（被导出后）使用。
 

Go 语言程序中全局变量与局部变量名称可以相同，但是函数内的局部变量会被优先考虑。实例如下：


**形式参数**
形式参数会作为函数的局部变量来使用。


**初始化局部和全局变量**
不同类型的局部和全局变量默认值为：

数据类型	初始化默认值
int	0
float32	0
pointer	nil


## II.Go 语言数组

Go 语言提供了数组类型的数据结构。

数组是具有相同唯一类型的一组已编号且长度固定的数据项序列，这种类型可以是任意的原始类型例如整型、字符串或者自定义类型。

相对于去声明 number0, number1, ..., number99 的变量，使用数组形式 numbers[0], numbers[1] ..., numbers[99] 更加方便且易于扩展。

数组元素可以通过索引（位置）来读取（或者修改），索引从 0 开始，第一个元素索引为 0，第二个索引为 1，以此类推。


**声明数组**
Go 语言数组声明需要指定元素类型及元素个数，语法格式如下：  

var variable_name [SIZE] variable_type  
  
以上为一维数组的定义方式。例如以下定义了数组 balance 长度为 10 类型为 float32：  

var balance [10] float32



**初始化数组**  
以下演示了数组初始化：

var balance = [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}   

balance := [5]float32{1000.0, 2.0, 3.4, 7.0, 50.0}  


I.数组长度不确定

，可以使用 ... 代替数组的长度，编译器会根据元素个数自行推断数组的长度：


var balance = [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
或
balance := [...]float32{1000.0, 2.0, 3.4, 7.0, 50.0}


I.初始化部分数组内容
如果设置了数组的长度，我们还可以通过指定下标来初始化元素：


//  将索引为 1 和 3 的元素初始化  
balance := [5]float32{1:2.0,3:7.0}  



**访问数组元素**

数组元素可以通过索引（位置）来读取。格式为数组名后加中括号，中括号中为索引的值。例如：  

var salary float32 = balance[9]  


**语言多维数组**

I.二维数组


// Step 1: 创建数组
values := [][]int{}

var values  = [][]int{}


row1 := []int{1, 2, 3}
row2 := []int{4, 5, 6}
values = append(values, row1)
values = append(values, row2)


I.初始化二维数组

a := [3][4]int{  
{0, 1, 2, 3} ,   /*  第一行索引为 0 */
{4, 5, 6, 7} ,   /*  第二行索引为 1 */
{8, 9, 10, 11},   /* 第三行索引为 2  最后一行必须有逗号，或者没有都有大括号往上一行 */
}


a := [3][4]int{  
{0, 1, 2, 3} ,   /*  第一行索引为 0 */
{4, 5, 6, 7} ,   /*  第二行索引为 1 */
{8, 9, 10, 11}}   /* 第三行索引为 2 */


I.访问二维数组

val := a[2][3]
或
var value int = a[2][3]



**Go 语言向函数传递数组**


void myFunction(param [10]int)
{
}

void myFunction(param []int)
{
}



## II.Go 语言指针

Go 语言中指针是很容易学习的，Go 语言中使用指针可以更简单的执行一些任务。

接下来让我们来一步步学习 Go 语言指针。

我们都知道，变量是一种使用方便的占位符，用于引用计算机内存地址。

Go 语言的取地址符是 &，放到一个变量前使用就会返回相应变量的内存地址。



I.什么是指针
一个指针变量指向了一个值的内存地址。

类似于变量和常量，在使用指针前你需要声明指针。指针声明格式如下：

var ip *int        /* 指向整型*/
var fp *float32    /* 指向浮点型 */


I.如何使用指针

指针使用流程：
定义指针变量。
为指针变量赋值。
访问指针变量中指向地址的值。
在指针类型前面加上 * 号（前缀）来获取指针所指向的内容。

// 普通变量   
var a int= 20    
//变量地址    
&a

//int 指针  
var ptrInt *int 

//指针赋值
ptrInt=&a

指针地址返回:ptrInt
指针指向的value 返回:*ptrInt

用指针访问数据: *ptrInt

I.Go 空指针
当一个指针被定义后没有分配到任何变量时，它的值为 nil。

nil 指针也称为空指针。

nil在概念上和其它语言的null、None、nil、NULL一样，都指代零值或空值。

一个指针变量通常缩写为 ptr。  


空指针判断：

if(ptr != nil)     /* ptr 不是空指针 */
if(ptr == nil)    /* ptr 是空指针 */




## II.Go 语言结构体

Go 语言中数组可以存储同一类型的数据，但在结构体中我们可以为不同项定义不同的数据类型。

结构体是由一系列具有相同类型或不同类型的数据构成的数据集合。

I.定义结构体

type Books struct {  
title string
author string
subject string
book_id int
}  

I.赋值
构造函数
fmt.Print(Books{"1", "2", "3",  5}," \n\n")

单个字段
fmt.Print(Books{title: "title",book_id: 3,author: "丁丁"}," \n\n")



I.访问结构体成员
var Book1 Books        /* 声明 Book1 为 Books 类型 */
 

/* book 1 描述 */
Book1.title = "Go 语言"
Book1.author = "www. .com"
Book1.subject = "Go  "
Book1.book_id = 6495407


I.结构体作为函数参数
你可以像其他数据类型一样将结构体类型作为参数传递给函数。并以以上实例的方式访问结构体变量：  

printBook(Book1)


func printBook( book Books ) {
fmt.Printf( "Book title : %s\n", book.title)
fmt.Printf( "Book author : %s\n", book.author)
fmt.Printf( "Book subject : %s\n", book.subject)
fmt.Printf( "Book book_id : %d\n", book.book_id)
}


I.结构体指针  

var struct_pointer *Books  


/* 打印 Book1 信息 */
printBook(&Book1)  



func printBook( book *Books ) {
fmt.Printf( "Book title : %s\n", book.title)
fmt.Printf( "Book author : %s\n", book.author)
fmt.Printf( "Book subject : %s\n", book.subject)
fmt.Printf( "Book book_id : %d\n", book.book_id)
}




## II.Go 语言切片(Slice)  type、 make() 函数
https://m.runoob.com/go/go-slice.html
Go 语言切片是对数组的抽象。  

Go 数组的长度不可改变，在特定场景中这样的集合就不太适用，Go 中提供了一种灵活，  
功能强悍的内置类型切片("动态数组")，与数组相比切片的长度是不固定的，可以追加元素，在追加时可能使切片的容量增大。  



**I.定义切片**
你可以声明一个未指定大小的数组来定义切片：
var identifier []type

或使用 make() 函数来创建切片:
var slice1 []type = make([]type, len)

slice1 := make([]type, len)


也可以指定容量，其中 capacity 为可选参数。
make([]T, length, capacity)



**I.切片初始化**

s :=[] int {1,2,3 }   
直接初始化切片，[] 表示是切片类型，{1,2,3} 初始化值依次是 1,2,3，其 cap=len=3。  



s := arr[:]
初始化切片 s，是数组 arr 的引用。  


s := arr[startIndex:endIndex]   
将 arr 中从下标 startIndex 到 endIndex-1 下的元素创建为一个新的切片。  




s := arr[startIndex:]   
将 arr 中从下标 startIndex 到 endIndex-1 下的元素创建为一个新的切片。


**len() 和 cap() 函数**
切片是可索引的，并且可以由 len() 方法获取长度。

切片提供了计算容量的方法 cap() 可以测量切片最长可以达到多少。


len()返回切片中的元素个数。 cap()返回切片中容量即切片可以容纳的元素个数


var numbers = make([]int,3,5)

make([]T, length, capacity)

fmt.Printf("len=%d cap=%d slice=%v\n",len(x),cap(x),x)

len=3 cap=5 slice=[0 0 0]


**空(nil)切片**
一个切片在未初始化之前默认为 nil，长度为 0，实例如下：


**切片截取、数组截取**

numbers := []int{0,1,2,3,4,5,6,7,8}  

/* 打印子切片从索引1(包含) 到索引4(不包含)*/
fmt.Println("numbers[1:4] ==", numbers[1:4])


/* 默认下限为 0  》0 :3*/
fmt.Println("numbers[:3] ==", numbers[:3])


/* 默认上限为 len(s) 》  4: len(s) */
fmt.Println("numbers[4:] ==", numbers[4:])


**append() 和 copy(目标new,老数据old) 函数**

	var numbers []int 

	/* 允许追加空切片,9放到切片里，数组里 */
	numbers = append(numbers, 9)

	/* 同时添加多个元素 */
	numbers = append(numbers, 2,3,4)


	/* 创建切片 numbers1 是之前切片的两倍容量*/
	numbers1 := make([]int, len(numbers), (cap(numbers))*2)

	/* 拷贝 numbers 的内容到 numbers1 */
	copy(numbers1,numbers)




