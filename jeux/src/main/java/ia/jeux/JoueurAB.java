package ia.jeux;

import java.util.ArrayList;
import java.util.List;

public class JoueurAB implements Joueur {
    int role;
    boolean libre[][];
    int n;

    public JoueurAB(int taille){
        n=taille;
        libre = new boolean[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                libre[i][j]=true;
    }

    public Domino joue() {
        List<Domino> possibles = possible(role);
//        for (Domino d:possibles) {
//            System.out.println(" x="+d.get_a().get_x()+" y="+d.get_a().get_y()+" x1= "+d.get_b().get_x()+" y1="+d.get_b().get_y());
//        }
        if (possibles.size()==0){
            System.err.println("Alpha Beta a perdu!");
        }

        Domino retour=new Domino(new Case(0,0),new Case(0,1));;
        int max=-100;
        int tmp=0;
        int alpha=-1000;
        int beta=1000;
        for (Domino d:possibles) {
            libre[d.get_a().get_x()][d.get_a().get_y()]=false;
            libre[d.get_b().get_x()][d.get_b().get_y()]=false;
           /* System.out.println("possibles = " + possibles.size());
            System.out.println("joue role="+role+" x="+d.get_a().get_x()+" y="+d.get_a().get_y()+" x1= "+d.get_b().get_x()+" y1="+d.get_b().get_y());
            */
           tmp=minValue(this.role, 3,alpha,beta);

            if(tmp>max){
                max=tmp;
                retour=d;
            }
            libre[d.get_a().get_x()][d.get_a().get_y()]=true;
            libre[d.get_b().get_x()][d.get_b().get_y()]=true;
        }
        //System.out.println(retour);
        return retour;
    }

    public int minValue(int role, int niveau, int alpha, int beta){
        List<Domino> possibles=possible((role+1)%2);

        int min=1000;
        int tmp;
        for (Domino d:possibles) {
            niveau--;
            libre[d.get_a().get_x()][d.get_a().get_y()]=false;
            libre[d.get_b().get_x()][d.get_b().get_y()]=false;
            tmp=maxValue((role+1)%2,niveau,alpha,beta);
            if(tmp<min){
                min=tmp;
            }
            if(min<=alpha){
                libre[d.get_a().get_x()][d.get_a().get_y()]=true;
                libre[d.get_b().get_x()][d.get_b().get_y()]=true;
                niveau++;
                return min;
            }
            if(min<beta){
                beta=min;
            }
            libre[d.get_a().get_x()][d.get_a().get_y()]=true;
            libre[d.get_b().get_x()][d.get_b().get_y()]=true;
            niveau++;
        }
        return min;
    }

    public int maxValue(int role, int niveau , int alpha, int beta){
        List<Domino> possibles=possible((role+1)%2);
        if(niveau<=0){
            return possibles.size()-possible(role).size()+eval(role);
        }
        int max=-1000;
        int tmp;

        for(Domino d:possibles){
            niveau--;
            libre[d.get_a().get_x()][d.get_a().get_y()]=false;
            libre[d.get_b().get_x()][d.get_b().get_y()]=false;

            tmp=minValue((role+1)%2,niveau,alpha,beta);

            if(tmp>max){
                max=tmp;
            }
            if(max>=beta){
                libre[d.get_a().get_x()][d.get_a().get_y()]=true;
                libre[d.get_b().get_x()][d.get_b().get_y()]=true;
                niveau++;
                return max;
            }
            if(max>alpha){
                alpha=max;
            }
            libre[d.get_a().get_x()][d.get_a().get_y()]=true;
            libre[d.get_b().get_x()][d.get_b().get_y()]=true;
            niveau++;
        }
        return max;
    }

    public void update(Domino l) {
        libre[l.a.i][l.a.j]=false;
        libre[l.b.i][l.b.j]=false;
    }

    public List<Domino> possible(int role){
        List<Domino> possible=new ArrayList<Domino>();
        if(role==0){
            for (int i=0;i<n;i++){
                for (int j=0;j<n-1;j++){
                    if (libre[i][j] && libre[i][j+1])
                        possible.add(new Domino(i,j,i,j+1));
                }
            }
        }
        else{
            for (int i=0;i<n-1;i++){
                for (int j=0;j<n;j++){
                    if (libre[i][j] && libre[i+1][j])
                        possible.add(new Domino(i,j,i+1,j));
                }
            }
        }
        return possible;
    }

    public int eval(int role){
        int ret=0;
        List<Domino> possible=possible(role);

        for (Domino d:possible) {
            if(((d.get_a().get_x())%2)==1 && ((d.get_a().get_y())%2)==1){
                ret++;
            }
        }
        return ret;
    }

    public void setRole(int direction) {
        role = direction;
    }

    public String getName() {
        return "Alpha Beta";
    }

    public void reset() {
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                libre[i][j]=true;
    }
}
