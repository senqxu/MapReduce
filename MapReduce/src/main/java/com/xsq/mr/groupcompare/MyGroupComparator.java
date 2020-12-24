package com.xsq.mr.groupcompare;

import org.apache.hadoop.io.RawComparator;

import com.xsq.mr.flowbean.partition.FlowBean;

public class MyGroupComparator implements RawComparator<FlowBean>{

	@Override
	public int compare(FlowBean o1, FlowBean o2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compare(byte[] b1, int s1, int l1, byte[] b2, int s2, int l2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
