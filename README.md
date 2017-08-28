# InterGalacticTrade

Build from source:
==================
1.maven can be used to build from source and execute some basic tests
  > mvn clean package

How to run (Windows):
=====================
1. Copy the jar file and sample input file to say D:\runDir
2. Open command line and go to dir D:\runDir 
3. Run below command from command line:  
   > java -cp .;galaxyTrader-1.jar com.galaxy.trader.Launcher D:\runDir\sample.txt

   
Basic design:
=============
> Appplication creates a chain of line parser and feeds each line read from Iterator(File Iterator in this case) to parsers.
> Each parser will check if it can parse the given line else forwards the next parser in chain.
> Conversion of Roman to Decimal is encapsulated in Interface to allow for different algo.


Few Assumption:
==============
1. All Inputs are case sensitive including Roman letter and are of exact format as per sample input/output.
2. Each line ends with newline as given in sample input/output.
3. All Question ends with ? char.
4. For symbol to roman mapping(e.g glob is I) 'is' being used as delimiter.
5. Output is being collected in memory to show all in the last assuming not very heavy on memory.
6. Any failure while reading line from file will result in program termination with exception and without any output.
7. Duplicates while processing will override previous infos about roman mapping or credit value.
8. Credit values are in in number range.
9. System console is used for logging outputs.
