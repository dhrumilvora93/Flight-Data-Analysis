package arrdelay;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


/**
 * @author Dhrumil Vora
 *
 */
public class delayMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
  /*
		 * Format in CSV
			Year	0
			Month	1
			DayofMonth	2
			DayOfWeek	3
			DepTime	4
			CRSDepTime	5
			ArrTime	6
			CRSArrTime	7
			UniqueCarrier	8
			FlightNum	9
			TailNum	10
			ActualElapsedTime	11
			CRSElapsedTime	12
			AirTime	13
			ArrDelay	14
			DepDelay	15
			Origin	16
			Dest	17
			Distance	18
			TaxiIn	19
			TaxiOut	20
			Cancelled	21
			CancellationCode	22
			Diverted	23
			CarrierDelay	24
			WeatherDelay	25
			NASDelay	26
			SecurityDelay	27
			LateAircraftDelay	28

		 * */
  public void map(LongWritable key, Text value, Context c) throws IOException,InterruptedException{
    String[] file = value.toString().split(",");
    if(!"NA".equalsIgnoreCase(file[14])) {
      int arrDelayInMinutes = Integer.parseInt(file[14]);
      String airlines = file[8];

      // Intermediate <K,V> as <airlines,arrDelayInMinutes>
      c.write(new Text(airlines),new IntWritable(arrDelayInMinutes));
  }
}
