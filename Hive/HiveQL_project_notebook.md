## Note:

This file contains few commands used to learn and observe Hive. Please view the .json file to manually play around with the codes. This file exists purely for quick information lookup.





# Hive

This file contains a series of Hibernate Query Language (HQL) used for creating tables, comparing hive to other data managing services, and collecting data. HQL is similar to SQL except that it is fully object-oriented and understands notions like inheritance, polymorphism and association.

------

## Queries

\# Created a new table which loads data from the GS files’ wdi_gs table to the external wdi_csv_text table.

~~~HQL
DROP TABLE IF EXISTS wdi_gs

CREATE EXTERNAL TABLE wdi_gs
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
LOCATION 'gs://hive-testdata-shylesh/datasets/wdi_2016/'
TBLPROPERTIES ("skip.header.line.count"="1")
~~~



\# The file was checked to see if it was successfully created or not. If unsucessful, then the command will print out “*Table or view not found*”. If successful, then it will print the total number of rows in table.

~~~HQL
SELECT count(*) FROM wdi_gs
~~~

> count(1)
>
> -----------
>
> 3278811



> \# Elapsed time: 24.549 s



\# Created a directory in the local distributed file system and made parents directories if needed.

~~~sh
hdfs dfs -mkdir -p hdfs:///user/shylesh/hive/wdi
~~~



\# Created a new table in comma delimited format (csv). Then overwrited the data in table from data from previous table.

~~~HQL
DROP TABLE IF EXISTS wdi_csv_text

CREATE EXTERNAL TABLE wdi_csv_text
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
LOCATION 'hdfs:///user/shylesh/hive/wdi/wdi_csv_text'

INSERT OVERWRITE TABLE wdi_csv_text
SELECT * FROM wdi_gs
~~~

> #Elapsed time: 35.62 s



~~~sh
hdfs dfs -du -s -h hdfs:///user/shylesh/hive/wdi/wdi_csv_text
~~~

> 311.8 M  hdfs:///user/shylesh/hive/wdi/wdi_csv_text



~~~HQL
SELECT count(countryName) FROM wdi_csv_text
~~~

> count(countryName)
>
> ---
>
> 1167777

> \#Elapsed time: 16.4 s



\#Clear disk cache in worker nodes

~~~bash
echo 3 | sudo tee /proc/sys/vm/drop_caches
~~~



Compared Hive and Bash in terms of the computation of the number of rows.

~~~sh
hdfs dfs -get hdfs:///user/shylesh/hive/wdi/wdi_csv_text .

cd wdi_csv_text

#calculate current directory size
du -ch .
~~~

> \#195M total



\#clear fs cache

> echo 3 | sudo tee /proc/sys/vm/drop_caches



\#bash row count

~~~sh
date +%s && cat * | wc && date +%s
~~~



> 1568240814
> 3278806 5464259 203915688
> 1568240823

Hive would execute 2x faster than bash count, but that was not the case here. The problem of using hadoop approach is that the overhead of paralleliztion over multiple hosts is serious. A few GBs of data would be processed on one node rather than multiple nodes as hadoop parallelization is for large files.

\#Compared hive and bash using a ‘big’ file

~~~sh
hdfs dfs -cp hdfs:///user/shylesh/hive/wdi/wdi_csv_text hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big


#Created a ‘big’ data
for i in {1..20}
do
echo "Copying round $i"
hdfs dfs -cp hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big/000000_0 hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big/000000_0-$i
done

hdfs dfs -du -s -h hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big
~~~

> 1.2 G  hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big



~~~HQL
DROP TABLE IF EXISTS wdi_csv_text_big

CREATE EXTERNAL TABLE wdi_csv_text_big
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n'
LOCATION 'hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big'
~~~



#Clear disk cache of all worker nodes then compare the hive and bash count of a big file.

~~~bash
echo 3 | sudo tee /proc/sys/vm/drop_caches
~~~



~~~HQL
SELECT count(countryName) FROM wdi_csv_text_big
~~~

> \#Elapsed time: 28.91 s

\

#bash

~~~sh
hdfs dfs -get hdfs:///user/shylesh/hive/wdi/wdi_csv_text_big .

cd wdi_csv_text_big

echo 3 | sudo tee /proc/sys/vm/drop_caches

date +%s && cat * | wc && date +%s
~~~

> 1568245885
> 17809666 29799239 1114957948
> 1568245932

\#Identified the parsing issue.

~~~HQL
SELECT distinct(indicatorcode)
FROM wdi_csv_text
order by indicatorcode
limit 20
~~~

The indicatorcode column was not parsed correctly as the result query was a strange string. So, an external table with one column and no csv parsing was created to resolve the issue.



~~~HQL
DROP TABLE IF EXISTS wdi_gs_debug

CREATE EXTERNAL TABLE wdi_gs_debug
(line STRING)
LOCATION 'gs://hive-testdata-shylesh/datasets/wdi_2016_gz'

SELECT * FROM wdi_gs_debug limit 10
~~~

~~~HQL
select distinct(line)
from wdi_gs_debug
where line like "%(\% of urban population)\"%"
limit 10
~~~

A new hive table was created using OpenCSVSerde on the GS data and then the data was exported to a hdfs location using another table.

~~~HQL
DROP TABLE IF EXISTS wdi_opencsv_gs

CREATE EXTERNAL TABLE wdi_opencsv_gs
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES (
    "separatorChar" = ",",
    "quoteChar" = "\"",
    "escapeChar" = "\\"
    )
    LOCATION 'gs://hive-testdata-shylesh/datasets/wdi_2016_gz'
    
select distinct(indicatorcode)
from wdi_opencsv_gs
order by indicatorcode
limit 20

DROP TABLE IF EXISTS wdi_opencsv_text

CREATE EXTERNAL TABLE wdi_opencsv_text
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
LOCATION 'hdfs:///user/shylesh/hive/wdi/wdi_opencsv_text'

INSERT OVERWRITE TABLE wdi_opencsv_text
SELECT * FROM wdi_opencsv_gs

select distinct(indicatorcode)
from wdi_opencsv_text
order by indicatorcode
limit 20

SELECT count(countryName) FROM wdi_opencsv_text
~~~

> count(countryName)
>
> ----
>
> 21759413

> #Elapsed time: 74.55 s



~~~HQL
SELECT count(countryName) FROM wdi_csv_text
~~~

> count(countryName)
>
> 1167777

> Elapsed time: 11.95 s



The aggregate sql query using OpenCSVSerde was found to be slower than the other. This was because the number of columns in the row were larger for the wdi_opencsv_text table than wdi_csv_text table due to SerDe.



~~~HQL
DROP VIEW IF EXISTS wdi_opencsv_text_view

CREATE VIEW IF NOT EXISTS wdi_opencsv_text_view
AS
SELECT cast(year AS integer),
countryName,
countryCode,
indicatorName,
indicatorCode,
cast(indicatorValue AS float)
FROM wdi_opencsv_text
~~~



Created a table that displays the 2015 GDP growth (annual %) for Canada

~~~HQL
SELECT
DISTINCT (indicatorcode),
indicatorname
FROM wdi_opencsv_text
WHERE indicatorcode LIKE '%GDP%'

SELECT indicatorValue, year, countryName
FROM wdi_opencsv_text
WHERE indicatorCode = 'NY.GDP.MKTP.KD.ZG'
AND year = '2015'
AND countryName = 'Canada'
~~~

> | indicatorValue   | year | countryName |
> | ---------------- | ---- | ----------- |
> | 1.07826875075281 | 2015 | Canada      |

> #Elapsed time: 73.83 s

Created a new table which was dynamic partitioned by year.

~~~HQL
DROP TABLE IF EXISTS wdi_opencsv_text_partitions

CREATE EXTERNAL TABLE wdi_opencsv_text_partitions
(countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT) PARTITIONED BY(year INTEGER)
ROW FORMAT DELIMITED
LOCATION 'hdfs:///user/shylesh/hive/wdi/wdi_opencsv_text_partitions'

set hive.exec.dynamic.partition.mode=nonstrict
~~~

> | key                              | value     |
> | -------------------------------- | --------- |
> | hive.exec.dynamic.partition.mode | nonstrict |

~~~HQL
INSERT OVERWRITE TABLE wdi_opencsv_text_partitions PARTITION (year)
SELECT countryname, countrycode, indicatorname, indicatorcode, indicatorvalue, YEAR
FROM wdi_opencsv_text
~~~

~~~sh
hdfs dfs -ls hdfs:///user/shylesh/hive/wdi/wdi_opencsv_text_partitions
~~~

~~~HQL
SELECT indicatorValue, year, countryName
FROM wdi_opencsv_text_partitions
WHERE indicatorCode = 'NY.GDP.MKTP.KD.ZG'
AND year = '2015'
AND countryName = 'Canada'
~~~

> | indicatorValue | year | countryName |
> | -------------- | ---- | ----------- |
> | 1.07826880     | 2015 | Canada      |

> \#Elapsed time: 3.98 s

A new table was created with columnar file optimization.

~~~HQL
DROP TABLE IF EXISTS wdi_csv_parquet

CREATE EXTERNAL TABLE wdi_csv_parquet
(year INTEGER, countryName STRING, countryCode STRING, indicatorName STRING, indicatorCode STRING, indicatorValue FLOAT)
STORED AS PARQUET
LOCATION 'hdfs:///user/shylesh/hive/wdi/wdi_csv_parquet'

INSERT OVERWRITE TABLE wdi_csv_parquet
SELECT * FROM wdi_opencsv_gs

hdfs dfs -du -s -h hdfs:///user/shylesh/hive/wdi/wdi_csv_parquet
~~~

> 93.9 M  hdfs:///user/shylesh/hive/wdi/wdi_csv_parquet

~~~sh
hdfs dfs -du -s -h hdfs:///user/shylesh/hive/wdi/wdi_opencsv_text
~~~

> 2.3 G  hdfs:///user/shylesh/hive/wdi/wdi_opencsv_text

~~~HQL
SELECT count(countryName) FROM  wdi_csv_parquet
~~~

> count(countryName)
>
> ---
>
> 21759413

> #Elapsed time: 18.42 s

~~~HQL
SELECT count(countryName) FROM wdi_opencsv_text
~~~

> count(countryName)
>
> ---
>
> 21759413

> #Elapsed time: 80.51 s



~~~HQL
SELECT indicatorvalue, year, countryname
FROM wdi_opencsv_text
WHERE indicatorcode = 'NY.GDP.MKTP.KD.ZG'
AND year = '2015'
~~~

> #Elapsed time: 69.89 s

~~~HQL
SELECT indicatorvalue, year, countryname
FROM wdi_csv_parquet
WHERE indicatorcode = 'NY.GDP.MKTP.KD.ZG'
AND year = '2015'
~~~

> #Elapsed time: 0.25 s, Fetched: 264 row(s)

The highest GDP growth year for each country was found.

~~~HQL
SELECT wdi_csv_parquet.indicatorvalue AS value, wdi_csv_parquet.year AS year, wdi_csv_parquet.countryname AS country
FROM (SELECT Max(indicatorvalue) AS ind, countryname
	FROM wdi_csv_parquet
	WHERE indicatorcode = 'NY.GDP.MKTP.KD.ZG'
	AND indicatorvalue <> 0
	GROUP BY countryname) t1
	INNER JOIN wdi_csv_parquet
	ON t1.ind = wdi_csv_parquet.indicatorvalue
	AND t1.countryname = wdi_csv_parquet.countryname
~~~

> #Elapsed time: 110.24 s

The same query was run on spark to check the difference.

~~~sh
spark-sql --conf spark.executor.memory=2g --conf spark.executor.cores=2
~~~

~~~HQL
SELECT countryname, year, indicatorcode, indicatorvalue
FROM wdi_csv_parquet
WHERE indicatorcode = 'NY.GDP.MKTP.KD.ZG'
DISTRIBUTE BY countryname
SORT BY countryname, year
LIMIT 200SELECT countryname, year, indicatorcode, indicatorvalue
FROM wdi_csv_parquet
WHERE indicatorcode = 'NY.GDP.MKTP.KD.ZG'
DISTRIBUTE BY countryname
SORT BY countryname, year
LIMIT 200
~~~

