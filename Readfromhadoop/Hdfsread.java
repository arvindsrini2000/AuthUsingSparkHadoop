import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

//import javax.xml.crypto.Data;

public class Hdfsread
{
	public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

//        Configuration conf = new Configuration();
//        conf.addResource(new Path("/home/local/ZOHOCORP/arvind-pt4542/Documents/hadoop-3.3.1/etc/hadoop/core-site.xml"));
//        conf.addResource(new Path("/home/local/ZOHOCORP/arvind-pt4542/Documents/hadoop-3.3.1/etc/hadoop/hdfs-site.xml"));
//        FileSystem fs = FileSystem.get(conf);
//        FSDataInputStream inputStream = fs.open(new Path("hdfs://localhost:9000"));
//        System.out.println(inputStream.readChar());  
		
		String un = args[0];
		String withpwd = args[1];
		String pwd = args[2];
		String uri = "hdfs://localhost:9000/user/arvind-pt4542/test_1/sample18.csv";	
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri), conf);

		InputStream in = null;
		FileStatus[] fileStatus = fs.listStatus(new Path("hdfs://localhost:9000/user/arvind-pt4542/test_1/sample18.csv"));
		    for(FileStatus status : fileStatus){
		        //System.out.println();
		        in = fs.open(new Path(status.getPath().toString()));
		        //System.out.println(in);
		        InputStreamReader isr = new InputStreamReader(in,StandardCharsets.UTF_8);
		        BufferedReader br = new BufferedReader(isr);
		        br.lines().forEach(line -> {
		        	String sarr[] = line.split(",");
		        	if(withpwd.equals("true"))
		        	{
		        		if ((((String) sarr[0]).equals(un)) && (((String) sarr[1]).equals(pwd))) 
		        		{
		        			System.out.println("Yes");
		        			return;
		        		}
		        	System.out.println("No");
		        	}
		        	else
		        	{
		        		if ((((String) sarr[0]).equals(un))) 
		        		{
							System.out.println("Yes");
							return;
						}
		        		System.out.println("No");
		        	}
		        });
		    }
//		try
//		{
//			in = fs.open(new Path(uri));
//			IOUtils.copyBytes(in, System.out, 4096, false);
//		}
//		finally
//		{
//			IOUtils.closeStream(in);
//		}
	}		
}

