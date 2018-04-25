import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * @author Dhrumil Vora
 *
 */
 public class taxidriver {

   public static void main(String[] args) throws IOException,ClassNotFoundException, InterruptedException {
     // Setup Job
     @SuppressWarnings("deprecation")
     Job j = new Job(new Configuration(),"Avg Taxi Time");

     j.setJarByClass(taxidriver.class);

     j.setMapperClass(avgTaxiTimeMapper.class);

     j.setReducerClass(avgTaxiTimeReducer.class);

     // Mentioned as final output by reducer dosent match with the output of the mapper
     j.setMapOutputKeyClass(Text.class);

     j.setMapOutputValueClass(Text.class);

     // Input Path
     Path input = new Path(args[0]);
     FileInputFormat.addInputPath(j,input);

     // output Path
     Path output = new Path(args[1]);
     FileOutputFormat.setOutputPath(j,output);

     //Exit program
     if (j.waitForCompletion(true)) {
       System.exit(0);
     }else{
       System.exit(1);
     }
   }
 }
