package com.xsq.mr.wordcount;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.api.json.JSONConfigurated;



public class WCDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		//Path inputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mrinput/wordcount");
		//Path outputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mroutput/wordcount");
		Path inputPath = new Path("/wordcount");
		Path outputPath = new Path("/mroutput/wordcount");
		
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://hadoop101:9000");
		//保证输出目录不存在的方法
		FileSystem fs =FileSystem.get(new URI("hdfs://hadoop101:9000"),conf);
		if(fs.exists(outputPath)) {
			fs.delete(outputPath,true);
		}
		
		//创建job 
		Job job = Job.getInstance(conf);
		//为job 创建一个名字
		job.setJobName("wordcount");
		
		//在YARN上运行
		conf.set("mapreduce.framework.name", "yarn");
		//RM所在的机器
		conf.set("yarn.resourcemanage.hostname", "hadoop102");
		
		//设置job 允许Mapper, Reducer类型，Mapper, Reducer输出的key-value类型
		job.setMapperClass(WCMapper.class);
		job.setReducerClass(WCReducer.class);
		
		//准备序列化器
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//设置输入输出目录
		FileInputFormat.setInputPaths(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		//运行job
		job.waitForCompletion(true);
	}
	
}

