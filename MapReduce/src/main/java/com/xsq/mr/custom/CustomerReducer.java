package com.xsq.mr.custom;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CustomerReducer extends Reducer<Text, BytesWritable, Text, BytesWritable>{

}
