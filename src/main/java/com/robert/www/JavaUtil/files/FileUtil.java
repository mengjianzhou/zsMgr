package com.robert.www.JavaUtil.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileUtil {
	
	public static void main(String[] args) {
		
		Path path=Paths.get("d:\\a.txt");
		List<String> lines = null;
		try {
			lines =  Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(String line : lines){			
			System.out.println(line);
		}
	}

}
