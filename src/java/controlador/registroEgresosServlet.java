/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.DAO_Mes;
import dao.DAO_RegistroTabla;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.GuardarDatos;
import model.Mes;

/**
 *
 * @author Erik
 */
public class registroEgresosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }
        String accion = request.getParameter("accion");
        int idFlujo = Integer.parseInt(request.getParameter("idFlujo"));
        int idTipo = 2;
        String datosEgresos = request.getParameter("datosEgresos");

        try {
            int mesInicial = Integer.parseInt(request.getParameter("idPrimermes"));
            int contMes = 0;
            JsonArray arrayEgresos = convertirJSONArray(datosEgresos);

            List<GuardarDatos> lista = new ArrayList<>();

            for (int i = 1; i < arrayEgresos.size(); i++) {
                //reinicio del contador para el idmes
                contMes = mesInicial;

                //se rescata el jsonobject de la lista
                JsonObject jsonObject = (JsonObject) arrayEgresos.get(i);
                System.out.println(jsonObject);
                System.out.println("-------------------------------------------");

                for (int j = 1; j < jsonObject.size(); j++) {
                    //nombre accion puede ser ya sea sueldo/bono/etc
                    String nombreAccion = new Gson().toJson(jsonObject.get("Egresos"));
                    String nombreAccionReplace = nombreAccion.replaceAll("^[\"']+|[\"']+$", "");
                    System.out.println(nombreAccion);

                    //aqui vamos rescatando los meses por el id;
                    //el id esta sacado por un contador el cual se reinicia al id inicial cada vez que se pasa
                    //a un uevo objeto json
                    Mes mes = new DAO_Mes().getMesPorId(contMes);
                    System.out.println(mes.getNombre());

                    //aqui rescatamos el dato que pertenece al mes
                    //y ademas pertenece a la accion antes rescatada
                    //es decir, el siguiente dato de la lista
                    String dato = new Gson().toJson(jsonObject.get(mes.getNombre()));

                    String result = dato.replaceAll("^[\"']+|[\"']+$", "");

                    System.out.println(result);

                    //ej de insert
                    //null, nombreAccion, mes.getid, dato
                    new DAO_RegistroTabla().insertRegistros(String.valueOf(idFlujo), String.valueOf(idTipo), nombreAccionReplace, mes.getId(), result);
                    contMes++;
                }

            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("se cayo!!!!   " + ex.getLocalizedMessage());
        }
    }

    private JsonArray convertirJSONArray(String listaEnString) throws IOException {
        JsonParser parser = new JsonParser();

        Object obj = parser.parse(listaEnString);
        JsonArray array = (JsonArray) obj;
        System.out.println("djshakdj----" + array);
        return array;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
