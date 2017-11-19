package ia.csp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphColouring extends BinaryCSP<Integer> {


    public GraphColouring(String filename){
        super();
        try{

            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String line = reader.readLine();
            int numColors=0;
            if (line.equals("% number of colors"))
                numColors = Integer.parseInt(reader.readLine());
            List<Integer> domain = new ArrayList<Integer>();
            for (int i=1;i<=numColors;i++)
                domain.add(i);
            System.out.println("problem with " + numColors +" colors");
            line = reader.readLine();
            if (line.equals("% Nodes")){
                line = reader.readLine();
                while (!line.equals("% Constraints")){
                    Variable<Integer> var = new Variable<Integer>(domain);
                    var.setName(line);
                    vars.add(var);
                    line = reader.readLine();
                }
                System.out.println(vars);
                line = reader.readLine();
                while (line !=null){
                    String[] nodes = line.split(" ");
                    //Variable<Integer> x = getVariable(nodes[0]);
                    //Variable<Integer> y = getVariable(nodes[1]);
                    //constraints.add(new BinaryConstraint<>(x,y));
                    line = reader.readLine();
                }
            }
            reader.close();
        }catch(IOException e){
            System.err.println("problem reading the file " + filename + "\n" + e);
        }
    }

    public String toString(){
        String res="";
        for (Variable<Integer> v : vars)
            res+= v.toString() + "\n";
        return res;
    }

    public static void main(String[] args){
        GraphColouring gc = new GraphColouring("gc.txt");
        System.out.println("Problem to solve: " + gc.constraints.size() +" constraints");
        //System.out.println(gc);
        boolean cont = true;
        int numColors = 8;
        List<Integer> colors = new ArrayList<Integer>();
        for (int i=1;i<=numColors;i++)
            colors.add(i);
        while (cont){
            if (gc.forwardCheckAC3()){
                System.out.println("the graph can be colored with " + colors.size() + " colors");
                cont = false;
            }
            else{
                System.out.println("the graph can NOT be coloredwith " + colors.size() + " colors");
                colors.add(colors.size()+1);
                for (Variable<Integer> v : gc.vars){
                    v.resetPossibleValues(colors);
                }
            }
        }

    }



}