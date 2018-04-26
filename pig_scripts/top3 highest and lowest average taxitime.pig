--Loading Data
taxiTable = LOAD '/user/admin/taxi.txt' USING PigStorage('\t') AS (Airport:chararray,Taxi:chararray,AvgTime:int);
--removing all NA values
removezerotable = FILTER taxiTable by AvgTime>0;
--sorting the table in Descending
orderTable = ORDER removezerotable BY AvgTime DESC;
--Filtering based on in or out results are still sorted
taxiinTable = FILTER orderTable by Taxi=='IN';
taxioutTable = FILTER orderTable by Taxi=='OUT';
-- picking highest 3 from both
limit_in_table =  LIMIT taxiinTable 3;
limit_out_table =  LIMIT taxioutTable 3;
--sorting the table in Ascending
orderTable_low = ORDER removezerotable BY AvgTime ASC;
--Filtering based on in or out results are still sorted
taxiinTable_low = FILTER orderTable_low by Taxi=='IN';
taxioutTable_low = FILTER orderTable_low by Taxi=='OUT';
-- picking lowest 3 from both
limit_in_table_low =  LIMIT taxiinTable_low 3;
limit_out_table_low =  LIMIT taxioutTable_low 3;
--Mergigin high low of Taxi in and Taxi out
in_high_low =  UNION ONSCHEMA limit_in_table,limit_in_table_low;
out_high_low = UNION ONSCHEMA limit_out_table, limit_out_table_low;
all3 =  UNION ONSCHEMA out_high_low,in_high_low;

--DUMP all3;
STORE all3 INTO 'pig_output' USING PigStorage(',');