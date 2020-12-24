package com.xsq.mr.flowbean;

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



public class FBDriver {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		
		Path inputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mrinput/flowbean");
		Path outputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mroutput/flowbean");
		//Path inputPath = new Path("/wordcount");
		//Path outputPath = new Path("/mroutput/wordcount");
		
		Configuration conf = new Configuration();
		
		//保证目录是否存在
		FileSystem fs =FileSystem.get(conf);
		if(fs.exists(outputPath)) {
			fs.delete(outputPath,true);
		}
		
		//创建job 
		Job job = Job.getInstance(conf);

		//设置job 允许Mapper, Reducer类型，Mapper, Reducer输出的key-value类型
		job.setMapperClass(FlowBeanMapper.class);
		job.setReducerClass(FlowBeanReducer.class);
		
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

