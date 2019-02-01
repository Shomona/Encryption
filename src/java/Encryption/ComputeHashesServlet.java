/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author shomonamukherjee
 */
@WebServlet(name = "ComputeHashesServlet",
        urlPatterns = {"/getHash"})
public class ComputeHashesServlet extends HttpServlet {

    ComputeHashesModel cm = null;

    // Initiate this servlet by instantiating the model that it will use.
    @Override
    public void init() {
        cm = new ComputeHashesModel();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // This servlet will reply to HTTP GET requests via this doGet method
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {

        // get the search parameter if it exists
        String inputString = request.getParameter("inputstring");
        String hashType = request.getParameter("encoding");

        // determine what type of device our user is
        String ua = request.getHeader("User-Agent");

        boolean mobile;
        // prepare the appropriate DOCTYPE for the view pages
        if (ua != null && ((ua.indexOf("Android") != -1) || (ua.indexOf("iPhone") != -1))) {
            mobile = true;

            request.setAttribute("doctype", "<!DOCTYPE html PUBLIC \"-//WAPFORUM//DTD XHTML Mobile 1.2//EN\" \"http://www.openmobilealliance.org/tech/DTD/xhtml-mobile12.dtd\">");
        } else {
            mobile = false;
            request.setAttribute("doctype", "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">");
        }

        String nextView;

        if (inputString != null) {

            byte[] hashBytes = null;
            
            //compute the hash by passing the input string and type of hash to the model
            try {
                hashBytes = cm.computeHash(inputString, hashType);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ComputeHashesServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            //Find the result in base64
            String hash64 = DatatypeConverter.printBase64Binary(hashBytes);
            //Find the result in hashHex
            String hashHex = DatatypeConverter.printHexBinary(hashBytes);
            

            //Set the attributes to pass the two encoding to the view
            request.setAttribute("hash64", hash64);
            request.setAttribute("hashHex", hashHex);

            nextView = "results.jsp";
        } else {
            // no search parameter so choose the original index view
            nextView = "index.jsp";
        }
        // Transfer control over the the correct "view"
        RequestDispatcher view = request.getRequestDispatcher(nextView);
        view.forward(request, response);
    }

}
