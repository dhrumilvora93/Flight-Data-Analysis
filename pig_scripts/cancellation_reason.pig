--Loading Data
dataTable = LOAD '/user/admin/cancel.txt' USING PigStorage('\t') AS (CancellationCode:chararray,Totalval:long);

-- Ordering table based on Totalval
orderTable = ORDER dataTable BY Totalval DESC;

--DUMP orderTable;
STORE orderTable INTO 'pig_output_cancel' USING PigStorage(',');