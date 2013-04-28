How to use eclipse to write hadoop java program in Windows
=========================
Mac haven't tested but it should work
1. Prepare Hadoop source file
-------------------------
Download the hadoop source file on hadoop.apache.org, i.e the 1.0.4 version. You'll need the .tar.gz file
Unzip the gz then unzip the tar

2. Copy the essential .jar to you java project dir
-------------------------
You need to do this so that whenever you import org.apache.* you won't get an error
Copy the following jar to you project folder, i.e. My Project/lib
hadoop-core-1.0.4.jar
lib/*.jar on demand
Note: For exemple if you need is to import org.apache.commons.lang.StringUtils, 
check the content of lib/commons-lang-*.jar (unzip the jar) to see if it has the 
StringUtils.class file under org/apache/commons/lang folder

3. Configure you project's building path
-------------------------
Select you project in eclipse, right click menu->Refresh, then right click menu->Properties
Go to the java build path, select the Libraries tab
Press Add JARs... Add the jars from the lib folder

4.You are good to go!
-------------------------