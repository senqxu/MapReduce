package com.xsq.mr.flowbean.partition;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import io.netty.handler.codec.http.HttpHeaders.Values;

public class FlowBeanReducer extends Reducer<Text, FlowBean, Text, FlowBean> {

	private  FlowBean out_value = new FlowBean();
	
	@Override
	protected void reduce(Text key, Iterable<FlowBean> values, Reducer<Text, FlowBean, Text, FlowBean>.Context arg2)
			throws IOException, InterruptedException {
		
		long sumUpFlow = 0;
		long sumDownFlow = 0;
		
		for(FlowBean flowBean: values) {
			sumUpFlow+=flowBean.getUpFlow();
			sumDownFlow+=flowBean.getDownFlow();
		}
		
		out_value.setUpFlow(sumUpFlow);
		out_value.setDownFlow(sumDownFlow);
		out_value.setDownFlow(sumDownFlow+sumUpFlow);
		
	}
}
