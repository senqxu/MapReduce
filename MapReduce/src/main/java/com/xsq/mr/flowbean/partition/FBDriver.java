package com.xsq.mr.flowbean.partition;

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
		Path outputPath = new Path("C:/BaiduNetdiskDownload/05-HadoopHadoopHAZookeeper/05-Mapreduce/mroutput/Partitionflowbean");
		//Path inputPath = new Path("/wordcount");
		//Path outputPath = new Path("/mroutput/wordcount");
		
		Configuration conf = new Configuration();
		
		//ä¿?è¯?ç›®å½•æ˜¯å?¦å­˜åœ¨
		FileSystem fs =FileSystem.get(conf);
		if(fs.exists(outputPath)) {
			fs.delete(outputPath,true);
		}
		
		//åˆ›å»ºjob 
		Job job = Job.getInstance(conf);

		//è®¾ç½®job å…?è®¸Mapper, Reducerç±»åž‹ï¼ŒMapper, Reducerè¾“å‡ºçš„key-valueç±»åž‹
		job.setMapperClass(FlowBeanMapper.class);
		job.setReducerClass(FlowBeanReducer.class);
		
		//å‡†å¤‡åº?åˆ—åŒ–å™¨
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//è®¾ç½®è¾“å…¥è¾“å‡ºç›®å½•
		FileInputFormat.setInputPaths(job, inputPath);
		FileOutputFormat.setOutputPath(job, outputPath);
		
		
		//设置ReduceTask数量为5
		job.setNumReduceTasks(5);
		//设置分区其
		job.setPartitionerClass(MyPartitioner.class);
		
		//è¿?è¡Œjob
		job.waitForCompletion(true);
	}
	
}

