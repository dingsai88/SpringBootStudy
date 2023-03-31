I.生产建立目录:

II.建立文件夹和下载
cd bin
mkdir flink
cd flink


curl  -x xx.j.idc:8000 -O https://mirrors.tuna.tsinghua.edu.cn/apache/flink/flink-1.17.0/flink-1.17.0-bin-scala_2.12.tgz



curl  -x x.c.corp:3128 -O https://mirrors.tuna.tsinghua.edu.cn/apache/flink/flink-1.17.0/flink-1.17.0-bin-scala_2.12.tgz

测试环境直接下载更好用
curl   -O https://mirrors.tuna.tsinghua.edu.cn/apache/flink/flink-1.17.0/flink-1.17.0-bin-scala_2.12.tgz


II.解压
--tar -xvf flink-1.17.0-bin-scala_2.12.tgz ./flink-1.17.0

当前目录
tar -xvf flink-1.17.0-bin-scala_2.12.tgz


3.配置环境变量
vi ~/.bash_profile

export FLINK_HOME=/bin/flink/flink-1.17.0
export PATH=$FLINK_HOME/bin:$PATH



4.刷新环境变量
source ~/.bash_profile


5.启动测试
cd /bin/flink/flink-1.17.0/bin
./start-cluster.sh

6.查看进程
[hadoop@hadoop000 ~]$ jps -m
 

 
----------------------------------------------------------------


 

idea新建项目
https://blog.csdn.net/Wing_kin666/article/details/118030808



不知道为什么缺少jar包 ，引入

D:\DingSai\Tools\flink-1.15.4-bin-scala_2.12\flink-1.15.4\lib\flink-dist-1.15.4.jar




	/**
	 * 批处理
	 * @param args
	 * @throws Exception
	 */
	public static void main1(String[] args) throws Exception {
		System.out.println("开始");
		// 第一个参数为输入路径，第二个参数为输出路径
		String inPath = "D:\\DingSai\\Tools\\flink-1.17.0-bin-scala_2.12\\data\\hello.txt";
		String outPath = "D:\\DingSai\\Tools\\flink-1.17.0-bin-scala_2.12\\data\\output.txt";
		// 获取Flink批处理执行环境
		ExecutionEnvironment executionEnvironment =
				ExecutionEnvironment.getExecutionEnvironment();
		// 获取文件中内容
		DataSet<String> text = executionEnvironment.readTextFile(inPath);
		// 对数据进行处理
		DataSet<Tuple2<String, Integer>> dataSet = text.flatMap(new LineSplitter()).groupBy(0).sum(1);
		//输出1：写入到文件
		dataSet.writeAsCsv(outPath,"\n"," ").setParallelism(1);

		//输出2：打印到日志
		//dataSet.print();
		// 触发执行程序
		executionEnvironment.execute("wordcount batch process");
	}
	static class LineSplitter implements FlatMapFunction<String, Tuple2<String,Integer>> {
		@Override
		public void flatMap(String line, Collector<Tuple2<String, Integer>>
				collector) throws Exception {
			for (String word:line.split(" ")) {
				collector.collect(new Tuple2<String, Integer>(word,1));
			}
		}
	}







/**
* 流处理1
* @param args
* @throws Exception
*/
public static void main(String[] args) throws Exception {
// 获取执行环境
StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//线程号
env.setParallelism(2);
// 从socket中读取数据
DataStream<String> text = env.socketTextStream("localhost", 9999);
// 将每行文本切分成单词，并进行计数
DataStream<Tuple2<String, Integer>> counts = text
.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
@Override
public void flatMap(String line, Collector<Tuple2<String, Integer>> out) {
for (String word : line.split(" ")) {
out.collect(new Tuple2<String, Integer>(word, 1));
}
}
})
.keyBy(0)
.sum(1);
// 打印结果
counts.print();
// 启动执行
env.execute("Streaming WordCount");
}




    /**
     * 流处理
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment().setParallelism(1);
        DataStreamSource<String> streamSource = env.socketTextStream("localhost", 9999);
        SingleOutputStreamOperator<Tuple2<String, Integer>> words = streamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] splits = value.split("\\s");
                for (String word : splits) {
                    out.collect(Tuple2.of(word, 1));
                }
            }
        });
        words.keyBy(0).sum(1).print();
        env.execute("WC");
    }





I.流处理
本机安装nc后

windows:
nc -l -p 9999


linux:
nc -lk 9999


-------------------------------------------------------------------------------












