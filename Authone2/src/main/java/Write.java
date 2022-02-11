import org.apache.spark.SparkConf;	
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.DataFrameWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructType;
import scala.collection.immutable.Seq;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.spark.launcher.SparkLauncher;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;

public class Write
{
	public static boolean writeinto(String uname, String pwd) throws IOException, InterruptedException
	{
//		System.out.println(uname + " " + pwd);
//		SparkConf conf = new SparkConf().setAppName("writeto").setMaster("local[*]");
//		JavaSparkContext ctx = new JavaSparkContext(conf);
//		
//		SparkSession spark = SparkSession.builder().appName("csv-demo").getOrCreate();
		Process handletoread = new SparkLauncher()
		         .addAppArgs(uname, "false", pwd)
		         .setAppResource("/home/local/ZOHOCORP/arvind-pt4542/eclipse-workspace/Testhdfswrite/target/Line_count-0.0.1-SNAPSHOT.jar")
		         .setMainClass("Hdfsread")
		         .setMaster("local")
		         .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
		         .setSparkHome("/home/local/ZOHOCORP/arvind-pt4542/Documents/spark-3.2.1-bin-hadoop3.2")
		         .launch();
		handletoread.waitFor();
		
		String result = new BufferedReader(new InputStreamReader(handletoread.getInputStream())).lines().collect(Collectors.joining("\n"));
		String sarr[] = result.split("\n");
		boolean ispresent = false;
		for(int i=0;i<sarr.length;i++)
		{
			//System.out.println(sarr[i]);
			if(sarr[i].equals("Yes"))
			{
				ispresent = true;
			}
		}
		if(!ispresent)
		{
			Process handle = new SparkLauncher()
			         .addAppArgs(uname, pwd)
			         .setAppResource("/home/local/ZOHOCORP/arvind-pt4542/eclipse-workspace/Line_count/target/Line_count-0.0.1-SNAPSHOT.jar")
			         .setMainClass("Writing1")
			         .setMaster("local")
			         .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
			         .setSparkHome("/home/local/ZOHOCORP/arvind-pt4542/Documents/spark-3.2.1-bin-hadoop3.2")
			         .launch();
			handle.waitFor();
			return true;
		}
		return false;
//		if(result.split("\n")[0].equals("No"))
//		{
//		
//		Process handle = new SparkLauncher()
//		         .addAppArgs(uname, pwd)
//		         .setAppResource("/home/local/ZOHOCORP/arvind-pt4542/eclipse-workspace/Line_count/target/Line_count-0.0.1-SNAPSHOT.jar")
//		         .setMainClass("Writing1")
//		         .setMaster("local")
//		         .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
//		         .setSparkHome("/home/local/ZOHOCORP/arvind-pt4542/Documents/spark-3.2.1-bin-hadoop3.2")
//		         .launch();
//		handle.waitFor();
//		
//		return true;
//		}
//		else
//		{
//			return false;
//		}
		//BufferedReader fromserver = new BufferedReader (new InputStreamReader (handle.getOutputStream()));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		spark.sparkContext().setLogLevel("ERROR");
//		List<Row> list=new ArrayList<Row>();
//		list.add(RowFactory.create());
//		list.add(RowFactory.create());
//
//		
//		List<org.apache.spark.sql.types.StructField> listOfStructField=
//				new ArrayList<org.apache.spark.sql.types.StructField>();
//		
//		listOfStructField.add
//		(DataTypes.createStructField("test", DataTypes.StringType, true));
//		listOfStructField.add
//		(DataTypes.createStructField("test2", DataTypes.StringType, true));
//		
//		StructType structType=DataTypes.createStructType(listOfStructField);
//		Dataset<Row> data=spark.createDataFrame(list,structType);
//		DataFrameWriter dfw = new DataFrameWriter(data);
//		dfw.mode(SaveMode.Append).csv("/home/local/ZOHOCORP/arvind-pt4542/Documents/sample11.csv");
//		ctx.close();
	}
}
