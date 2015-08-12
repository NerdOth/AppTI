/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managed;

import beans.OrdreMission;
import beans.Personnel;
import beans.Trimestre;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

/**
 *
 * @author machd
 */
@WebServlet(name = "GetOrdre", urlPatterns = {"/GetOrdre"})
public class GetOrdreServlet extends HttpServlet {

    @EJB
    private session.OrdreMissionFacade ejbFacade;
    private OrdreMission current;
    @EJB
    private session.PersonnelFacade ejbPers;
    private Personnel pers;
    @EJB
    private session.TrimestreFacade ejbTrim;
    private Trimestre trim;

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
    @SuppressWarnings("empty-statement")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            if (request.getParameter("action") != null) {
                if (request.getParameter("id") != null) {

                    int id = Integer.parseInt(request.getParameter("id"));
                    current = ejbFacade.find(id);

                }

                ejbFacade.remove(current);

            } else {

                SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat hourFormatter = new SimpleDateFormat("h");

                String attr = request.getParameter("attr");
                String output = request.getParameter("json");

                JSONObject object = null;
                String[] o = new String[9];
                for (int i = 0; i < 9; i++) {
                    o[i] = null;
                }
                String[] num = {"0", "1", "2", "3", "4", "5", "6", "7", "8"};

                Float mont = null;
                Float kilo = null;

                java.util.Date day = new java.util.Date();
                
                //java.sql.Date sqlday = new java.sql.Date(day.getTime());
                
                java.util.Date hour = new java.util.Date();
                
                //java.sql.Date sqlhour = new java.sql.Date(hour.getTime());
                
                

                try {
                    object = new JSONObject(output);

                    for (int i = 0; i < 8; i++) {
                        if (object.getString(num[i]) != null) {
                            o[i] = object.getString(num[i]);

                        }
                        System.out.println(o[i]);

                    }

                } catch (JSONException ex) {
                    Logger.getLogger(GetOrdreServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (!"".equals(o[7])) {
                    kilo = Float.parseFloat(o[7]);

                }

                if (!"".equals(o[1])) {
                    mont = Float.parseFloat(o[1]);

                }

                if ((!"".equals(o[3])) || (!"".equals(o[4]))) {
                    day = dateFormatter.parse(o[3]);
                    hour =  hourFormatter.parse(o[4]);
                }

                System.out.println("" + mont + kilo + day + hour);
                pers = ejbPers.FindByMatricule(o[0]);
                trim = ejbTrim.find(0);

                if ("disabled".equals(attr)) {
                    current = new OrdreMission(1, mont, o[2], day, hour, o[5], o[6], kilo, null, pers, trim);

                    ejbFacade.Create(current);

                } else {
                    if (request.getParameter("id") != null) {

                        int id = Integer.parseInt(request.getParameter("id"));
                        current = ejbFacade.find(id);

                    }

                    current.setMontant(mont);
                    current.setDateAller(day);
                    current.setHeureAller(hour);
                    current.setVille(o[2]);
                    current.setObjetMission(o[5]);
                    current.setMoyenTransport(o[6]);
                    current.setKilometres(kilo);

                    ejbFacade.edit(current);
                }
            }

        } catch (ParseException ex) {
            Logger.getLogger(GetOrdreServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

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
