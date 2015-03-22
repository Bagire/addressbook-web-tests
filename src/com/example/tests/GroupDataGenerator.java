package com.example.tests;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import static com.example.fw.DataGenerator.generateRandomString;

public class GroupDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data>, <file>, <format>");
			return;
		}
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()){
			System.out.println("File exists, plese remove it manually: " + file);
			return;
		}
		
		List<GroupData> groups = generateRandomGroups(amount);
		if ("csv".equals(format)){
			saveGroupsToCsvFile(groups, file);
		} else if ("xml".equals(format)) {
			saveGroupsToXmlFile(groups, file);
		} else {
			System.out.println("Unknown format " + format);
		}
		return;

	}

	private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("group", GroupData.class);
		String xml = xstream.toXML(groups);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}

	private static void saveGroupsToCsvFile(List<GroupData> groups, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (GroupData group : groups) {
			writer.write(group.getNameGroup() + "," + group.getHeader() + "," + group.getFooter() + ",!\n");
		}
		writer.close();
	}

	public static List<GroupData> loadGroupsFromXmlFile(File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("group", GroupData.class);
		return (List<GroupData>) xstream.fromXML(file);
	}

	public static List<GroupData> loadGroupsFromCsvFile(File file) throws IOException {
		  List<GroupData> list = new ArrayList<GroupData>();
		  FileReader reader = new FileReader(file);
		  BufferedReader buffReader = new BufferedReader(reader);
		  String line = buffReader.readLine();
		  while (line != null){
			  String[] part = line.split(",");
			  GroupData group = new GroupData()
		    	.withNameGroup(part[0])
		    	.withHeader(part[1])
		    	.withFooter(part[2]);
			  list.add(group);
			  line = buffReader.readLine();
		  }
		  buffReader.close();
		  return list;
	}

		public static List<GroupData> generateRandomGroups(int amount) {
		  List<GroupData> list = new ArrayList<GroupData>();
		  for (int i=0; i<amount; i++) {
		    GroupData group = new GroupData()
		    	.withNameGroup(generateRandomString())
		    	.withHeader(generateRandomString())
		    	.withFooter(generateRandomString());
		    list.add(group);
		  }
		  return list;
	}

}
