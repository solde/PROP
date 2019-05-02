package capaDomini;

//TOTALLY USELESS CLASS
import Exception.chessException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.util.Pair;

public class Statistics {

    public double time;
    public boolean WL;
    public boolean OpRating;

    public Statistics() {
    }

    public void showStatsPlayer(Player p) throws chessException {
        double elo;
        try {
            elo = p.getELO();
        } catch (Exception ObjectNotFoundException) {
            throw new chessException("Player not found");
        }
        System.out.print("--");
        System.out.print(p.getId());
        System.out.println("--  \n");
        System.out.print("ELO:");
        System.out.println(elo);
        System.out.print("Rank: ");
        if (elo >= 1000.0 && elo <= 1599) {
            System.out.println("Noob");
        }
        if (elo >= 1600 && elo <= 2000) {
            System.out.println("Experimented");
        }
        if (elo >= 2001 && elo <= 2300) {
            System.out.println("Hardo-bolido");
        }
        if (elo >= 2301 && elo <= 2700) {
            System.out.println("Master Mutenroshi");
        }
        if (elo >= 2700 && elo <= 3000) {
            System.out.println("Poke Super Mega Hyper Master League Digimon Champion");
        }
        if (elo >= 3000) {
            System.out.println("Cheater, please report");
        }

        System.out.print("Wins: ");
        System.out.println(p.getWins());

    }
/*
    public void showStatsGame(Game g) {
        //Donde sea que esta nuestro main, tiene que tener una lista de jugadores existentes

        float max = 0;
        float max2 = 0;
        float max3 = 0;
        float max4 = 0;
        float max5 = 0;
        String topH = null, toph2 = null, toph3 = null, toph4 = null, toph5 = null;
        ArrayList<Human> players;
        players = g.getListOfPlayers(); //no existe
        List<String> problems = g.getListOfProblems(); //tampoco
        int i = 0;
        int cont = 0;
        while (!problems.isEmpty()) {
            String show = problems.get(i);

            for (Iterator<Human> it = players.iterator(); it.hasNext();) {
                Human movl = it.next();
                if (movl.getTimes().containsKey(show)) {
                    float time = movl.getTimes().get(show);
                    cont++;
                    if (max <= time) {
                        max = time;
                        topH = movl.getId();
                    }
                    if (max2 <= time && max > time) {
                        max2 = time;
                        toph2 = movl.getId();
                    }
                    if (max3 <= time && max > time && max2 > time) {
                        max3 = time;
                        toph3 = movl.getId();
                    }
                    if (max4 <= time && max > time && max2 > time && max3 > time) {
                        max4 = time;
                        toph4 = movl.getId();
                    }
                    if (max5 <= time && max >= time && max2 > time && max3 > time && max4 > time) {
                        max5 = time;
                        toph5 = movl.getId();
                    }
                }
                switch (cont) {
                    case 0:
                        System.out.print("Para el problema: ");
                        System.out.print(show);
                        System.out.println(" no hay registros");
                        break;
                    case 1:
                        System.out.print("Primer puesto: ");
                        System.out.print(movl.getId());
                        System.out.print("Con un timepo de: ");
                        System.out.println(max);
                        break;
                    case 2:
                        System.out.print("Primer puesto: ");
                        System.out.print(topH);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max);
                        System.out.print("Segundo puesto: ");
                        System.out.print(toph2);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max2);
                        break;
                    case 3:
                        System.out.print("Primer puesto: ");
                        System.out.print(topH);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max);
                        System.out.print("Segundo puesto: ");
                        System.out.print(toph2);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max2);
                        System.out.print("Tercer puesto: ");
                        System.out.print(toph3);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max3);
                        break;

                    case 4:
                        System.out.print("Primer puesto: ");
                        System.out.print(topH);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max);
                        System.out.print("Segundo puesto: ");
                        System.out.print(toph2);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max2);
                        System.out.print("Tercer puesto: ");
                        System.out.print(toph3);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max3);
                        System.out.print("Tercer puesto: ");
                        System.out.print(toph4);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max4);
                        break;

                    case 5:
                        System.out.print("Primer puesto: ");
                        System.out.print(topH);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max);
                        System.out.print("Segundo puesto: ");
                        System.out.print(toph2);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max2);
                        System.out.print("Tercer puesto: ");
                        System.out.print(toph3);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max3);
                        System.out.print("Cuarto puesto: ");
                        System.out.print(toph4);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max4);
                        System.out.print("Quinto puesto: ");
                        System.out.print(toph5);
                        System.out.print("Con un timepo de: ");
                        System.out.println(max5);
                        break;

                }
            }
            cont = 0;
            i++;
            max = 0;
            max2 = 0;
            max3 = 0;
            max4 = 0;
            max5 = 0;
        }
    }
*/
}
