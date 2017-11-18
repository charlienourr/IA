package ia.jeux;


import java.util.Vector;

public class JoueurMinMax implements Joueur {
    int role;
    boolean libre[][];
    int n;

    public JoueurMinMax(int taille){
        n=taille;
        libre = new boolean[n][n];
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                libre[i][j]=true;
    }

    public Domino joue() {
       /* Vector<Domino> possibles = new Vector<Domino>();
        if (role == Jeu.LIGNE){
            for (int i=0;i<n-1;i++){
                for (int j=0;j<n;j++){
                    if (libre[i][j] && libre[i+1][j])
                        possibles.add(new Domino(i,j,i+1,j));
                }
            }
            if (possibles.size()==0){
                System.err.println("J'ai perdu!");
            }
            return possibles.get(gen.nextInt(possibles.size()));
        }
        else{
            for (int i=0;i<n;i++){
                for (int j=0;j<n-1;j++){
                    if (libre[i][j] && libre[i][j+1])
                        possibles.add(new Domino(i,j,i,j+1));
                }
            }
            return possibles.get(gen.nextInt(possibles.size()));
        }*/
       return null;
    }

    public void update(Domino l) {
        libre[l.a.i][l.a.j]=false;
        libre[l.b.i][l.b.j]=false;
    }

    public void setRole(int direction) {
        role = direction;
    }

    public String getName() {
        return "MinMax";
    }

    public void reset() {
        for (int i=0;i<n;i++)
            for (int j=0;j<n;j++)
                libre[i][j]=true;
    }
}
