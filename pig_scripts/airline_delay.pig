{\rtf1\ansi\ansicpg1252\deff0\nouicompat\deflang1033{\fonttbl{\f0\fnil\fcharset0 Courier New;}}
{\*\generator Riched20 10.0.17120}\viewkind4\uc1 
\pard\f0\fs22 --Loading Data\par
dataTable = LOAD '/user/admin/airline_delay_output.txt' USING PigStorage('\\t') \par
AS (Airport:chararray,Probability:float,onschedulecount:long,totalcount:long);\par
\par
-- orderby Probability descending\par
orderTable = ORDER dataTable BY Probability DESC;\par
\par
-- highest probability for being on schedule \par
limittable = LIMIT orderTable 3;\par
\par
-- orderby Probability ascending\par
orderTable_low = ORDER dataTable BY Probability ASC;\par
\par
-- lowest probability for being on schedule \par
limittable_low = LIMIT orderTable_low 3;\par
\par
--merging two results\par
bothtable = UNION limittable_low,limittable;\par
\par
--DUMP bothtable;\par
STORE bothtable INTO 'pig_output_delay' USING PigStorage(',');\par
}
 