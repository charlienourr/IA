package ia.csp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BinaryCSP<D> {

    List<Variable<D>> vars;
    List<BinaryConstraint<D>> constraints;

    public BinaryCSP() {
        vars = new LinkedList<Variable<D>>();
        constraints = new LinkedList<BinaryConstraint<D>>();
    }

    public boolean ac3() {
    return true;
    }

    public boolean revise(BinaryConstraint<D> c) {
        return true;
    }

    public boolean forwardCheckAC3() {
        return true;
    }


    public boolean forwardCheck() {
        return true;
    }



}

/*
package csp;

        import java.util.*;

public class BinaryCSP<D> {

    List<Variable<D>> vars;
    List<BinaryConstraint<D>> constraints;

    public BinaryCSP() {
        vars = new LinkedList<Variable<D>>();
        constraints = new LinkedList<BinaryConstraint<D>>();
    }

    public BinaryCSP(BinaryCSP binaryCSP){
        vars=binaryCSP.vars;
        constraints=binaryCSP.constraints;
    }

    public boolean ac3() {
        for (int i=0;i<vars.size();i++) {
            if(revise(constraints.get(i))){//wrong
                if(vars.get(i).getPossibleValues().size()==0){
                    return false;
                }
                //???
            }
        }
        //System.out.println(this);
        return true;
    }

    public boolean revise(*/
/*Variable<D> v, *//*
BinaryConstraint<D> c) {
        boolean change = false;
            */
/*for (D d : c.getX().getPossibleValues()) {
                if(c.getY().getPossibleValues().contains(d)){ // &&
                    c.getX().removePossibleValue(d);
                    change=true;
                }
            }*//*

        //System.out.println("c.getX().getPossibleValues() = " + c.getX().getPossibleValues());
        //System.out.println("c.getY().getPossibleValues() = " + c.getY().getPossibleValues());

        if(c.getX().isAssigned() && c.getY().getPossibleValues().contains(c.getX().getValue())){
            c.getY().removePossibleValue(c.getX().getValue());
        }
        if(c.getY().isAssigned() && c.getX().getPossibleValues().contains(c.getY().getValue())){
            c.getX().removePossibleValue(c.getY().getValue());
        }
        //System.out.println("c.getY2().getPossibleValues() = " + c.getX().getPossibleValues());
        return change;
    }

    public boolean forwardCheckAC3() {


        //System.out.println(this);
        return true;
    }


    public boolean forwardCheck() {

        List<BinaryCSP> list = new ArrayList<BinaryCSP>();

        list.add(this);
        boolean result = false;
        int index =0;


        BinaryCSP<D> binaryCSP=new BinaryCSP<D>();
        binaryCSP.vars=list.get(list.size()-1).vars;
        binaryCSP.constraints=list.get(list.size()-1).constraints;

        for (BinaryConstraint b:binaryCSP.constraints) {//update the constraints
            if(b.getX().isAssigned() || b.getY().isAssigned()){
                revise(b);
            }
            if(b.getX().getPossibleValues().size()==0 || b.getY().getPossibleValues().size()==0){
                System.out.println("false");
                //return false;   //if the sudoku is not possible
            }
        }

        while(binaryCSP.vars.get(index).isAssigned()) {   //find a first variable
            System.out.println(binaryCSP.vars.get(index));
            index++;
        }
        if(index==binaryCSP.vars.size()){
            System.out.println("true");
            return true;
        }

        for (D d: binaryCSP.vars.get(index).getPossibleValues()) {
            //System.out.println("d"+d);

            binaryCSP.vars.get(index).setValue(d);
            System.out.println(binaryCSP);
            //forwardCheck();

        }

        return false;
    }



}*/
