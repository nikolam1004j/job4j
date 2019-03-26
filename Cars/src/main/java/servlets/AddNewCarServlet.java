package servlets;

import annotationsmodels.Cuzov;
import annotationsmodels.Dvigatel;
import logic.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@MultipartConfig(maxFileSize = 1048576L)
public class AddNewCarServlet extends HttpServlet {
    private Store store = new Store();
    private Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        map.put("owner", "Имя владельца");
        map.put("model", "Модель авто");
        map.put("dvigatel", "Двигатель");
        map.put("corobka", "Коробка");
        map.put("price", "Стоимость");
        map.put("customFile", "Приложенный файл");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cuzov> cuzovs = store.getCuzovs();
        List<Dvigatel> dvigatels = store.getDvigatels();
        req.setAttribute("cuzovs", cuzovs);
        req.setAttribute("dvigatels", dvigatels);
        req.getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain; charset=UTF-8");
        req.setCharacterEncoding("utf-8");

        boolean isAllFielsNotEmpty = true;
        boolean isAllFieldsChecked = false;
        PrintWriter writer = new PrintWriter(resp.getWriter(), false);

        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            if(entry.getValue()[0].trim().isEmpty()) {
                isAllFielsNotEmpty = false;
                writer.println("ERROR;");
                writer.println("Поле " + map.get(entry.getKey()) + " не заполнено");
                writer.flush();
                break;
            }

        }

        if(isAllFielsNotEmpty) {
            String owner = req.getParameter("owner").trim();
            String model = req.getParameter("model").trim();
            double price = Double.parseDouble(parameterMap.get("price")[0]);
            int dvigatel = Integer.parseInt(parameterMap.get("dvigatel")[0]);
            int corobka = Integer.parseInt(parameterMap.get("corobka")[0]);
            int cuzov = Integer.parseInt(parameterMap.get("cuzov")[0]);


            if (req.getPart("customFile").getSize() > 0) {
                isAllFieldsChecked = true;
            } else {
                writer.println("ERROR;Нет приложенного файла");
            }

            if (isAllFielsNotEmpty && isAllFieldsChecked) {
                Part customFile = req.getPart("customFile");
                String realPath = req.getServletContext().getRealPath("img");
                System.out.println(new File(".").getAbsolutePath());
                String link = String.format("%s/%s", realPath, customFile.getSubmittedFileName());
                File file = new File(link);
                if (!file.exists()) {
                    file.createNewFile();
                }
                try (FileInputStream fis = (FileInputStream) customFile.getInputStream()) {
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] bytes = new byte[fis.available()];
                        fis.read(bytes);
                        fos.write(bytes);
                    }
                }
                store.addNewCar(owner, model, cuzov, corobka, dvigatel,
                        String.format("img/%s", customFile.getSubmittedFileName()), price);
                writer.println("OK");
            }
        }
    }
}