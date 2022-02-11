package io.techlab.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JOTCSolutionConsole {

  public static void main(String args[]) throws IOException {

    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));


    int n = Integer.parseInt(bufferedReader.readLine().trim());

    List<Integer> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
        .map(Integer::parseInt)
        .collect(Collectors.toList());

    int result = jumpingOnClouds(c);

    bufferedWriter.write(String.valueOf(result));
    bufferedWriter.newLine();

    bufferedReader.close();
    bufferedWriter.close();
  }
  public static int jumpingOnClouds(List<Integer> c) {
/**
 * If it would not be a standalone console app, then i could call the below service to get the required problem solution
 *
    JOTCService service = new JOTCService();
    JotcRequest request = new JotcRequest();
    request.setFullName("console");
    request.setJotcArray(c);
    return service.solveJotcProblem(request);
 */
    int count=0;
    int i=0;
    while(i<c.size()-1){
      if(i+2<c.size() && c.get(i+2)!=1){
        count++;
        i=i+2;
      }
      else{
        count++;
        i++;
      }
    }
    return count;
  }

}
