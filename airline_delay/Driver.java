package airline_delay;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Driver {

  public static void main(String[] args) throws IOException,ClassNotFoundException, InterruptedException {
    // Setup Job
    @SuppressWarnings("deprecation")
    Job j = new Job(new Configuration());

    j.setJarByClass(Driver.class);

    j.setMapperClass(ReadingMapper.class);

    j.setReducerClass(WritingReducer.class);

    // Mentioned as final output by reducer dosent match with the output of the mapper
    j.setMapOutputKeyClass(Text.class);

    j.setMapOutputValueClass(IntWritable.class);

    // Input Path
    Path input = new Path(args[1]);
    FileInputFormat.addInputPath(j,input);

    // output Path
    Path output = new Path(args[2]);
    FileOutputFormat.addOutputPath(j,output);

    //Exit program
    if (j.waitForCompletion(true)) {
      Sysetm.exit(0);
    }else{
      Sysetm.exit(1);
    }
  }
}
