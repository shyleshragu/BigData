# BigData

This repository contains programs done in:
1) Hive
2) Scala


The objective was to run various commands on the different data warehouse services and obtain an understanding on how they work.

## Hive

Apache Hive is a data warehouse service facilitated by Apache Hadoop for reading, writing and managing HDFS files. 

A Google Storage (GS) bucket with an exported BigQuery Table was created. The exported tables were of CSV (Comma-Separated Values) format and compressed in GZIP. A 3-node Hadoop cluster was created using GCP Dataproc. An external table was created with a HDFS location pointing to the GS bucket using HQL query.

Hive was connected to the terminal by using command line tools (like ssh) and JDBC driver. The Hive directory contains a series of Hibernate Query Language (HQL) used for creating tables, comparing hive to other data managing services, and collecting data. HQL is similar to SQL except that it is fully object-oriented and understands notions like inheritance, polymorphism and association.



## Scala

Scala is a high-level programming language that combines object-oriented and functional programming. It is highly efficient to use due to the standard JVM features and Java libraries.

Various commands, variables, functions, methods and classes were created and tested for practice. The programming was done in Intellij due to maven implementations.
