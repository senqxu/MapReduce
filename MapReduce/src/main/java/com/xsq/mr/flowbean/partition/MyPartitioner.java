package com.xsq.mr.flowbean.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text, FlowBean>{

	@Override
	public int getPartition(Text key, FlowBean value, int numPartitions) {

		String suffix = key.toString().substring(0, 3);
		int partitionNum = 0;
		switch(suffix) {
		case"136":
			 partitionNum = 1;
			 break;
		case"137":
			 partitionNum = 2;
			 break;
		case"138":
			 partitionNum = 3;
			 break;
		case"139":
			 partitionNum = 4;
			 break;
			
		default:
			break;
		}
		
		
		return 0;
	}
	
}
