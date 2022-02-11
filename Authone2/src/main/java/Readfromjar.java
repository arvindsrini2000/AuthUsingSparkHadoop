import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
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

import org.apache.spark.launcher.SparkLauncher;

public class Readfromjar {
	
		public static String readfrom(String uname, String pwd) throws IOException, InterruptedException 
		{
			Process handletoread = new SparkLauncher()
			         .addAppArgs(uname, "true", pwd)
			         .setAppResource("/home/local/ZOHOCORP/arvind-pt4542/eclipse-workspace/Testhdfswrite/target/Line_count-0.0.1-SNAPSHOT.jar")
			         .setMainClass("Hdfsread")
			         .setMaster("local")
			         .setConf(SparkLauncher.DRIVER_MEMORY, "2g")
			         .setSparkHome("/home/local/ZOHOCORP/arvind-pt4542/Documents/spark-3.2.1-bin-hadoop3.2")
			         .launch();
			handletoread.waitFor();
			String result = new BufferedReader(new InputStreamReader(handletoread.getInputStream())).lines().collect(Collectors.joining("\n"));
			//System.out.println(result.split("\n")[0]);
			String sarr[] = result.split("\n");
			for(int i=0;i<sarr.length;i++)
			{
				if(sarr[i].equals("Yes"))
				{
					return "done";
				}
			}
			return "no";
			
		}
}
