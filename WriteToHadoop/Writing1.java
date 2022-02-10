//import org.apache.avro.io.Encoder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.spark.SparkConf;			
//import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.DataFrameWriter;
//import org.apache.spark.network.protocol.Encoders;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;

//import com.google.common.io.Files;

import scala.collection.immutable.Seq;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
//import org.apache.spark.sql.Dataset;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.spark.launcher.SparkLauncher;
//import javax.xml.crypto.Data;

public class Writing1
{
	public static void main(String[] args) throws Exception
	{
		//System.out.println(args[0] + " " + args[1]);
		SparkConf conf = new SparkConf().setAppName("write_count").setMaster("local[*]");
		JavaSparkContext ctx = new JavaSparkContext(conf);
		
		SparkSession spark = SparkSession.builder().appName("csv-demo").getOrCreate();
		spark.sparkContext().setLogLevel("ERROR");
		List<Row> list=new ArrayList<Row>();
		list.add(RowFactory.create(args[0], args[1]));
		//list.add(RowFactory.create(args[2], args[3]));
//		list.add(RowFactory.create("two"));
//		list.add(RowFactory.create("three"));
//		list.add(RowFactory.create("four"));
		
		List<org.apache.spark.sql.types.StructField> listOfStructField=
				new ArrayList<org.apache.spark.sql.types.StructField>();
		
		listOfStructField.add
		(DataTypes.createStructField("test", DataTypes.StringType, true));
		listOfStructField.add
		(DataTypes.createStructField("test2", DataTypes.StringType, true));
		
		StructType structType = DataTypes.createStructType(listOfStructField);
		Dataset<Row> data = spark.createDataFrame(list,structType);
		DataFrameWriter dfw = new DataFrameWriter(data);

		dfw.mode(SaveMode.Append).csv("/home/local/ZOHOCORP/arvind-pt4542/Documents/sample18.csv");
		
		System.out.println("true");
		String uri = "hdfs://localhost:9000/user/arvind-pt4542/test_1/sample18.csv";	
		String hdfspath = "hdfs://localhost:9000/user/arvind-pt4542/test_1/sample18.csv";	
		Configuration conf2 = new Configuration();
		String localpath = "/home/local/ZOHOCORP/arvind-pt4542/Documents/sample18.csv/";
		FileSystem fs = FileSystem.get(URI.create(uri), conf2);
		File directoryPath = new File("/home/local/ZOHOCORP/arvind-pt4542/Documents/sample18.csv");
	     String contents[] = directoryPath.list();
	     for(int i=0; i<contents.length; i++) {
	    	 String local = localpath + contents[i];
	    	 fs.copyFromLocalFile(new org.apache.hadoop.fs.Path(local), new org.apache.hadoop.fs.Path (hdfspath));
	      }
		//ctx.close();
	}
}
