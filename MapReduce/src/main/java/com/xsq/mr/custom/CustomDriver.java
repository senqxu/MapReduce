package com.xsq.mr.custom;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.sun.jersey.api.json.JSONConfigurated;



public class CustomDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Path inputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mrinput/custom");
		Path outputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mroutput/custom");

		
		Configuration conf = new Configuration();
		
		//保证输出目录不存在的方法
		FileSystem fs =FileSystem.get(conf);
		if(fs.exists(outputPath)) {
			fs.delete(outputPath,true);
		}
		
		//创建job 
		Job job = Job.getInstance(conf);
		//为job 创建一个名字
		job.setJobName("wordcount");
		
		
		
		//设置job 允许Mapper, Reducer类型，Mapper, Reducer输出的key-value类型
		job.setMapperClass(CustomMapper.class);
		job.setReducerClass(CustomerReducer.class);
		
		//准备序列化器
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);
		
		//设置输入输出目录
		FileInputFormat.setInputPaths(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		//设置输入和输出格式
		job.setInputFormatClass(MyInputFormat.class);
		job.setOutputFormatClass(org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat.class);
		
		//运行job
		job.waitForCompletion(true);
	}
	
}

