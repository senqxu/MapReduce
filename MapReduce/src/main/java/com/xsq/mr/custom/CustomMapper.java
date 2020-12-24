package com.xsq.mr.custom;

import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.ChainMapper;
import org.apache.hadoop.mapreduce.Mapper;

public class CustomMapper extends Mapper<Text, BytesWritable, Text, BytesWritable>{

}
