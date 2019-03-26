package servlets;

import annotationsmodels.Car;
import logic.Store;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SelloutServlet extends HttpServlet {
    private final Store store = new Store();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String carId = req.getParameter("carId");
        int id = Integer.parseInt(carId);
        String login = String.valueOf(req.getSession().getAttribute("login"));
        Car car = store.getCarByOwnerAndId(login, id);
        if(car != null) {
            car.setSold(true);
            store.saveOrUpdate(car);
            writer.println("OK");
        } else {
            writer.println("ERROR");
        }
        writer.flush();
    }
}
