package is.ru.TicTacToe;

import static spark.Spark.*;
import spark.*;

public class TicTacToe {
    public static void main(String[] args) {
        staticFileLocation("/public");
        
        setPort(Integer.valueOf(System.getenv("PORT")));

        final Game g = new Game();

        post(new Route("/add") {
            @Override
            public Object handle(Request request, Response response) {
                Integer val = Integer.valueOf(request.queryParams("val"));
                int result = g.oneTurn(val);
                String ret = "";
                switch (result) {
                    case 1: 
                    case 2: ret = "Spilari " + result + " vann!!!";
                    g.newRound();
                    break;
                    case 3: ret = "Jafntefli!";
                    g.newRound();
                    break;
                    default:
                    break;
                }
                return ret;
            }
        });

        get(new Route("/new") {
            @Override
            public Object handle(Request request, Response response) {
                g.newRound();
                return "true";
            }
        });
    }
}
