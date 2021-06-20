/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Erik
 */
public class editarAccion_Servlet extends HttpServlet {

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
        try {
            String valoresNuevos = request.getParameter("valoresNuevos");
            String valoresAntiguos = request.getParameter("valoresAntiguos");

            try {

                JsonArray arrayNuevos = convertirJSONArray(valoresNuevos);
                JsonArray arrayViejos = convertirJSONArray(valoresAntiguos);

                List<String> listaDeNuevos = new ArrayList<>();
                List<String> listaDeAntiguos = new ArrayList<>();
                List<String> listaFinal = new ArrayList<>();

                JsonParser parser = new JsonParser();
                Object obj1 = parser.parse(valoresNuevos);
                Object obj2 = parser.parse(valoresAntiguos);

                JsonArray array1 = (JsonArray) obj1;
                JsonArray array2 = (JsonArray) obj2;

                String accionAntigua = "";
                String accionNueva = "";
                for (int i = 0; i < arrayNuevos.size(); i++) {

                    for (int j = 0; j < 1; j++) {
                        String result = array1.get(i).toString().replaceAll("^[\"']+|[\"']+$", "");
                        accionNueva = array1.get(0).toString().replaceAll("^[\"']+|[\"']+$", "");
                        listaDeNuevos.add(result);
                    }

                    for (int g = 0; g < 1; g++) {
                        String result2 = array2.get(i).toString().replaceAll("^[\"']+|[\"']+$", "");
                        accionAntigua = array2.get(0).toString().toString().replaceAll("^[\"']+|[\"']+$", "");
                        listaDeAntiguos.add(result2);
                    }
                }
            
                
                for (int i = 0; i < array1.size(); i++) {
                    for (int j = 0; j < array2.size(); j++) {
                        if(listaDeNuevos.get(i).equals(listaDeAntiguos.get(j))){
                            System.out.println("Se repite este elemento: "+listaDeNuevos.get(i));
                        }else{
                            System.out.println("distintos: "+listaDeNuevos.get(i)+"-"+listaDeAntiguos.get(j));
                        }
                    }
                    
                }
                /*
                System.out.println("-----------------");
                System.out.println("largo lista arrayNueva: " + array1.size());
                System.out.println("largo lista arrayAntigua: " + array2.size());
                System.out.println("-----------------");
                */
                /*
                System.out.println("----------------");
                System.out.println(listaDeNuevos);
                System.out.println(listaDeAntiguos);
                System.out.println("Accion nueva: " + accionNueva);
                System.out.println("Accion Antigua: " + accionAntigua);
                System.out.println("----------------"); 
                */

            } catch (Exception e) {

            }
        } catch (Exception e) {
        }
    }

    private JsonArray convertirJSONArray(String listaEnString) throws IOException {
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(listaEnString);
        JsonArray array = (JsonArray) obj;
        System.out.println("Array----" + array);
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
