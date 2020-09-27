package com.demo.model.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BinlistData {
	
	
	
	public   String getCountryFromBinlist(String BIN) {
		
		if (BIN!=null) {
			return getCardData(BIN).split("\"alpha2\":")[1].split(",",2)[0].replace("\"", "");
		} else {
			return "BIN is null";
		}
		
	}

	

	private String getCardData(String BIN) {
	
		
	    String url="https://lookup.binlist.net/"+BIN;
	    String[] command = {"curl", "-H", "Accept-Version: 3" , url};

        ProcessBuilder process = new ProcessBuilder(command); 
        Process p;
        try
        {
            p = process.start();
            BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                    builder.append(line);
                    builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        }
        catch (IOException e)
        {   
            e.printStackTrace();
            return null;
        }
	}
	
	
	
}
