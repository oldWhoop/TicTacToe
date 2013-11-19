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
                int ret = g.oneTurn(val);

                return ret;
            }
        });
    }
}
