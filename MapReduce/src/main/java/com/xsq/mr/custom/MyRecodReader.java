package com.xsq.mr.custom;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class MyRecodReader extends RecordReader {
	
	private Text key;
	private BytesWritable value;
	private String filename;
	private int length;
	private FileSystem fs;//声明文件系统
	private Path path ;
	private FSDataInputStream is;
	private boolean flag=true;//标记方法只调用一次

	@Override
	public void initialize(InputSplit split, TaskAttemptContext context) throws IOException, InterruptedException {		
		
		FileSplit fileSplit = (FileSplit) split;
		filename = fileSplit.getPath().getName();
		length = (int) fileSplit.getLength();
		path= fileSplit.getPath();
		//获取当前joB的配置
		Configuration conf= context.getConfiguration();
		//获取当前job 使用文件系统
		fs= FileSystem.get(conf);
		
		is = fs.open(path);
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		if(flag) {
			if(key==null) {
				key = new Text();
			}
			if(value==null) {
				value = new BytesWritable();
			}
			
			//将文件名封装到key
			key.set(filename);
			//读取文件内容到ByteWritable
			byte [] content=new byte[length];
			
			IOUtils.readFully(is, content, 0, length);
			value.set(content,0,length);
			flag = false;
			return true;
		}
		
		return false;
	}

	@Override
	public Object getCurrentKey() throws IOException, InterruptedException {
		
		return key;
	}

	@Override
	public Object getCurrentValue() throws IOException, InterruptedException {
		
		return value;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void close() throws IOException {
		if(is!=null) {
			IOUtils.closeStream(is);
		}
		
		if(fs!=null) {
			fs.close();
		}

	}

}
