import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author Dhrumil Vora
 *
 */
 public class delayReducer extends Reducer<Text, IntWritable, Text,Text>{
   public void reduce(Text airlines, Iterable<IntWritable> arrDelayInMinutes, Context c) throws IOException,InterruptedException{

    /* negative value indicates early arrival and positive value indicates late arrival
 		* for airline to be called on schedule == 0 and negative values are considered on schedule
 		* use float as numbers will get really BIG!
    */
     float total = 0;
     float onSchedule = 0;

     Iterator<IntWritable> iter = arrDelayInMinutes.iterator();

     while(iter.hasNext()){
       total++;

       int timeInMinutes = iter.next().get();
       if(timeInMinutes <=0 ){
         onSchedule++;
       }
     }
     // Check Probability
     float prob = onSchedule/total;

     c.write(airlines,new Text(prob + "\t" + onSchedule + "\t" + total));
   }
 }
