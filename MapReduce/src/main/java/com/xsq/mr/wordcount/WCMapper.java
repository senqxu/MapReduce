package com.xsq.mr.wordcount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WCMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private Text out_key = new Text();
	private IntWritable out_value = new IntWritable();
	
	//正对输入的Keyin-valuein 调用一次
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		System.out.println("keyin" + key +"--------keyout:"+ value);
		
		String[] words =value.toString().split("\t");
		for(String word: words) {
			out_key.set(word);
			
			context.write(out_key, out_value);
		}
	}
	
}
