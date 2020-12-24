package com.xsq.mr.groupcompare;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable>{
	
	
	private OrderBean out_key = new OrderBean();
	private NullWritable out_Value = NullWritable.get();
	
	@Override
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, OrderBean, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		String[] words = value.toString().split("\t");
		
		//fengzhuang
		out_key.setOrderId(words[0]);
		out_key.setpId(words[1]);
		out_key.setAcount(words[2]);
		
		context.write(out_key,out_Value);
		
	}
	
}
