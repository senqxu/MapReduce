package com.xsq.mr.flowbean;

import java.io.IOException;

import javax.swing.text.AbstractDocument.Content;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FlowBeanMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

	private Text out_key = new Text();
	private FlowBean out_value = new FlowBean();
	
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, FlowBean>.Context context)
			throws IOException, InterruptedException {
		
		String[] words = value.toString().split("\t");
		out_key.set(words[1]);
		//封装上行
		out_value.setUpFlow(Long.parseLong(words[words.length-3]));
		//封装下行
		out_value.setDownFlow(Long.parseLong(words[words.length-3]));
		
		context.write(out_key,out_value);
		
	}
	
}
