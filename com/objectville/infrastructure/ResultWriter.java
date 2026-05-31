package com.objectville.infrastructure;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResultWriter {
    private List<String> logs;
    public ResultWriter(){
        this.logs = new ArrayList<>();
    }

    public void log(String message){
        logs.add(message);
    }

    public void writeResult(String filePath) {
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(filePath,true))){
            for(String log : logs){
                bw.write(log);
                bw.newLine();
            }
            logs.clear();
        } catch (IOException e){
            System.err.println("Error while writing the result: "+e.getMessage());
        }
    }
    public static void clearFile(String filePath){
        try(BufferedWriter bw=new BufferedWriter(new FileWriter(filePath,false))){
            bw.write("");
        }catch (IOException e){
            System.err.println("Error clearing file: "+e.getMessage());
        }
    }
}