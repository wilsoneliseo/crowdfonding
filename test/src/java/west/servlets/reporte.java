/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package west.servlets;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "reporte", urlPatterns = {"/reporte"})
public class reporte extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");
        OutputStream out = response.getOutputStream();

        try {
            try {
                Document documento = new Document();
                PdfWriter.getInstance(documento, out);

                documento.open();
                switch (request.getParameter("cat")) {
                    case "Financiero":

                        Paragraph par1 = new Paragraph();
                        Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        par1.add(new Phrase("Recaudacion por subcategoria", fonttitulo));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par1);

                        List<String> lrc = lsRecaduacionSubcategoriaWS("sum");
                        PdfPTable tabla = new PdfPTable(2);
                        PdfPCell celda1 = new PdfPCell(new Paragraph("Subcategoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell celda2 = new PdfPCell(new Paragraph("Recaudacion", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tabla.addCell(celda1);
                        tabla.addCell(celda2);

                        for (int i = 0; i < lrc.size(); i++) {
                            tabla.addCell(lrc.get(i));
                            System.out.println(lrc.get(i));
                        }
                        par1.add(new Phrase(Chunk.NEWLINE));
                        par1.add(new Phrase(Chunk.NEWLINE));
                        documento.add(tabla);

                        //CICLO
                        Paragraph par2 = new Paragraph();
                        par2.add(new Phrase("Promedio por subcategoria", fonttitulo));
                        par2.setAlignment(Element.ALIGN_CENTER);
                        par2.add(new Phrase(Chunk.NEWLINE));
                        par2.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par2);

                        List<String> lavg = lsRecaduacionSubcategoriaWS("avg");
                        PdfPTable tavg = new PdfPTable(2);
                        PdfPCell tavg1 = new PdfPCell(new Paragraph("Subcategoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tavg2 = new PdfPCell(new Paragraph("Promedios", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tavg.addCell(tavg1);
                        tavg.addCell(tavg2);

                        for (int i = 0; i < lavg.size(); i++) {
                            tavg.addCell(lavg.get(i));
                        }
                        documento.add(tavg);

                        //CICLO
                        Paragraph par3 = new Paragraph();
                        par3.add(new Phrase("Maximo por subcategoria", fonttitulo));
                        par3.setAlignment(Element.ALIGN_CENTER);
                        par3.add(new Phrase(Chunk.NEWLINE));
                        par3.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par3);

                        List<String> lmax = lsRecaduacionSubcategoriaWS("max");
                        PdfPTable tmax = new PdfPTable(2);
                        PdfPCell tmax1 = new PdfPCell(new Paragraph("Subcategoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tmax2 = new PdfPCell(new Paragraph("Maximos", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tmax.addCell(tmax1);
                        tmax.addCell(tmax2);

                        for (int i = 0; i < lmax.size(); i++) {
                            tmax.addCell(lmax.get(i));
                        }
                        documento.add(tmax);

                        //CICLO
                        Paragraph par4 = new Paragraph();
                        par4.add(new Phrase("Minimo por subcategoria", fonttitulo));
                        par4.setAlignment(Element.ALIGN_CENTER);
                        par4.add(new Phrase(Chunk.NEWLINE));
                        par4.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par4);

                        List<String> lmin = lsRecaduacionSubcategoriaWS("min");
                        PdfPTable tmin = new PdfPTable(2);
                        PdfPCell tmin1 = new PdfPCell(new Paragraph("Subcategoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tmin2 = new PdfPCell(new Paragraph("Minimos", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tmin.addCell(tmin1);
                        tmin.addCell(tmin2);

                        for (int i = 0; i < lmin.size(); i++) {
                            tmin.addCell(lmin.get(i));
                        }
                        documento.add(tmin);

                        //CICLO
                        Paragraph par5 = new Paragraph();
                        par5.add(new Phrase("Recaudacion por CATEGORIA", fonttitulo));
                        par5.setAlignment(Element.ALIGN_CENTER);
                        par5.add(new Phrase(Chunk.NEWLINE));
                        par5.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par5);

                        List<String> lsumc = reporteCategoriaWS("sum");
                        PdfPTable tsumc = new PdfPTable(2);
                        PdfPCell tsumc1 = new PdfPCell(new Paragraph("Categoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tsumc2 = new PdfPCell(new Paragraph("Recaudacion", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tsumc.addCell(tsumc1);
                        tsumc.addCell(tsumc2);

                        for (int i = 0; i < lsumc.size(); i++) {
                            tsumc.addCell(lsumc.get(i));
                        }
                        documento.add(tsumc);

                        //CICLO
                        Paragraph par6 = new Paragraph();
                        par6.add(new Phrase("Promedio por CATEGORIA", fonttitulo));
                        par6.setAlignment(Element.ALIGN_CENTER);
                        par6.add(new Phrase(Chunk.NEWLINE));
                        par6.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par6);

                        List<String> lavgc = reporteCategoriaWS("avg");
                        PdfPTable tavgc = new PdfPTable(2);
                        PdfPCell tavgc1 = new PdfPCell(new Paragraph("Categoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tavgc2 = new PdfPCell(new Paragraph("Promedio", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tavgc.addCell(tavgc1);
                        tavgc.addCell(tavgc2);

                        for (int i = 0; i < lavgc.size(); i++) {
                            tavgc.addCell(lavgc.get(i));
                        }
                        documento.add(tavgc);

                        //CICLO
                        Paragraph par7 = new Paragraph();
                        par7.add(new Phrase("Maximo por CATEGORIA", fonttitulo));
                        par7.setAlignment(Element.ALIGN_CENTER);
                        par7.add(new Phrase(Chunk.NEWLINE));
                        par7.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par7);

                        List<String> lmaxc = reporteCategoriaWS("max");
                        PdfPTable tmaxc = new PdfPTable(2);
                        PdfPCell tmaxc1 = new PdfPCell(new Paragraph("Categoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tmaxc2 = new PdfPCell(new Paragraph("Maximo", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tmaxc.addCell(tmaxc1);
                        tmaxc.addCell(tmaxc2);

                        for (int i = 0; i < lmaxc.size(); i++) {
                            tmaxc.addCell(lmaxc.get(i));
                        }
                        documento.add(tmaxc);

                        //CICLO
                        Paragraph par8 = new Paragraph();
                        par8.add(new Phrase("Minimo por CATEGORIA", fonttitulo));
                        par8.setAlignment(Element.ALIGN_CENTER);
                        par8.add(new Phrase(Chunk.NEWLINE));
                        par8.add(new Phrase(Chunk.NEWLINE));
                        documento.add(par8);

                        List<String> lminc = reporteCategoriaWS("min");
                        PdfPTable tminc = new PdfPTable(2);
                        PdfPCell tminc1 = new PdfPCell(new Paragraph("Categoria", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell tminc2 = new PdfPCell(new Paragraph("Minimo", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        tminc.addCell(tminc1);
                        tminc.addCell(tminc2);

                        for (int i = 0; i < lminc.size(); i++) {
                            tminc.addCell(lminc.get(i));
                        }
                        documento.add(tminc);
                        break;
                    case "Cliente":
                        Paragraph clpar = new Paragraph();
                        Font cltitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        clpar.add(new Phrase("Historial por cliente de recompensas compradas", cltitulo));
                        clpar.setAlignment(Element.ALIGN_CENTER);
                        clpar.add(new Phrase(Chunk.NEWLINE));
                        clpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(clpar);

                        List<String> clrecom = recomComprada();
                        PdfPTable clt = new PdfPTable(5);
                        PdfPCell clt1 = new PdfPCell(new Paragraph("id Usuario", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell clt2 = new PdfPCell(new Paragraph("Nombre", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell clt3 = new PdfPCell(new Paragraph("id Compra", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell clt4 = new PdfPCell(new Paragraph("Recompensa", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell clt5 = new PdfPCell(new Paragraph("Iniciativa", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        clt.addCell(clt1);
                        clt.addCell(clt2);
                        clt.addCell(clt3);
                        clt.addCell(clt4);
                        clt.addCell(clt5);

                        for (int i = 0; i < clrecom.size(); i++) {
                            clt.addCell(clrecom.get(i));
                            System.out.println(clrecom.get(i));
                        }
                        clpar.add(new Phrase(Chunk.NEWLINE));
                        clpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(clt);

                        break;
                    case "Iniciativa":
                        Paragraph inipar = new Paragraph();
                        Font inititulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        inipar.add(new Phrase("Historial por iniciativa de recompensas compradas", inititulo));
                        inipar.setAlignment(Element.ALIGN_CENTER);
                        inipar.add(new Phrase(Chunk.NEWLINE));
                        inipar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(inipar);

                        List<String> inirecom = recomCompradaIni();
                        PdfPTable init = new PdfPTable(5);
                        PdfPCell ini1 = new PdfPCell(new Paragraph("id Iniciativa", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell ini2 = new PdfPCell(new Paragraph("Iniciativa", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell ini3 = new PdfPCell(new Paragraph("id compra", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell ini4 = new PdfPCell(new Paragraph("Descripcion", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell ini5 = new PdfPCell(new Paragraph("Donador", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        init.addCell(ini1);
                        init.addCell(ini2);
                        init.addCell(ini3);
                        init.addCell(ini4);
                        init.addCell(ini5);

                        for (int i = 0; i < inirecom.size(); i++) {
                            init.addCell(inirecom.get(i));
                        }
                        inipar.add(new Phrase(Chunk.NEWLINE));
                        inipar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(init);

                        break;
                    case "Recompensas mas compradas":
                        String p = "rmc";
                        Paragraph rmcpar = new Paragraph();
                        Font rmctitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        rmcpar.add(new Phrase("Recompensas mas compradas", rmctitulo));
                        rmcpar.setAlignment(Element.ALIGN_CENTER);
                        rmcpar.add(new Phrase(Chunk.NEWLINE));
                        rmcpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(rmcpar);

                        List<String> lrmctop = topWS(p);
                        PdfPTable rmct = new PdfPTable(2);
                        PdfPCell rmct1 = new PdfPCell(new Paragraph("Recompensa", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell rmct2 = new PdfPCell(new Paragraph("Total", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        rmct.addCell(rmct1);
                        rmct.addCell(rmct2);

                        for (int i = 0; i < lrmctop.size(); i++) {
                            rmct.addCell(lrmctop.get(i));
                        }
                        rmcpar.add(new Phrase(Chunk.NEWLINE));
                        rmcpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(rmct);

                        break;
                    case "Usuarios con mas iniciativas":
                        p = "ucmi";
                        Paragraph ucmipar = new Paragraph();
                        Font ucmititulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        ucmipar.add(new Phrase("Usuarios con mas iniciativas", ucmititulo));
                        ucmipar.setAlignment(Element.ALIGN_CENTER);
                        ucmipar.add(new Phrase(Chunk.NEWLINE));
                        ucmipar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(ucmipar);

                        List<String> lucmitop = topWS(p);
                        PdfPTable ucmit = new PdfPTable(2);
                        PdfPCell ucmit1 = new PdfPCell(new Paragraph("Usuario", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell ucmit2 = new PdfPCell(new Paragraph("Total", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        ucmit.addCell(ucmit1);
                        ucmit.addCell(ucmit2);

                        for (int i = 0; i < lucmitop.size(); i++) {
                            ucmit.addCell(lucmitop.get(i));
                        }
                        ucmipar.add(new Phrase(Chunk.NEWLINE));
                        ucmipar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(ucmit);
                        
                        break;
                    case "Usuarios que mas han donado":
                        p = "uqmdhd";
                        Paragraph uqmdhdpar = new Paragraph();
                        Font uqmdhdtitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        uqmdhdpar.add(new Phrase("Usuarios que mas han donado", uqmdhdtitulo));
                        uqmdhdpar.setAlignment(Element.ALIGN_CENTER);
                        uqmdhdpar.add(new Phrase(Chunk.NEWLINE));
                        uqmdhdpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(uqmdhdpar);

                        List<String> luqmdhdtop = topWS(p);
                        PdfPTable uqmdhdt = new PdfPTable(2);
                        PdfPCell uqmdhdt1 = new PdfPCell(new Paragraph("Usuario", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell uqmdhdt2 = new PdfPCell(new Paragraph("Total de aporte", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        uqmdhdt.addCell(uqmdhdt1);
                        uqmdhdt.addCell(uqmdhdt2);

                        for (int i = 0; i < luqmdhdtop.size(); i++) {
                            uqmdhdt.addCell(luqmdhdtop.get(i));
                        }
                        uqmdhdpar.add(new Phrase(Chunk.NEWLINE));
                        uqmdhdpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(uqmdhdt);
                        
                        break;
                    case "Usuarios que mas recompensas han comprado":
                        p = "uqmrhc";
                        Paragraph uqmrhcpar = new Paragraph();
                        Font uqmrhctitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        uqmrhcpar.add(new Phrase("Usuarios que mas recompensas han comprado", uqmrhctitulo));
                        uqmrhcpar.setAlignment(Element.ALIGN_CENTER);
                        uqmrhcpar.add(new Phrase(Chunk.NEWLINE));
                        uqmrhcpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(uqmrhcpar);

                        List<String> luqmrhctop = topWS(p);
                        PdfPTable uqmrhct = new PdfPTable(2);
                        PdfPCell uqmrhct1 = new PdfPCell(new Paragraph("Usuario", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));
                        PdfPCell uqmrhct2 = new PdfPCell(new Paragraph("Recompensas", FontFactory.getFont("Arial", 12, Font.BOLD, BaseColor.RED)));

                        uqmrhct.addCell(uqmrhct1);
                        uqmrhct.addCell(uqmrhct2);

                        for (int i = 0; i < luqmrhctop.size(); i++) {
                            uqmrhct.addCell(luqmrhctop.get(i));
                        }
                        uqmrhcpar.add(new Phrase(Chunk.NEWLINE));
                        uqmrhcpar.add(new Phrase(Chunk.NEWLINE));
                        documento.add(uqmrhct);
                        
                        break;
                }//fin switch

                documento.close();
            } catch (Exception e) {
                e.getMessage();
            }
        } finally {
            out.close();
        }
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

    private static java.util.List<java.lang.String> lsRecaduacionSubcategoriaWS(String op) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.lsRecaduacionSubcategoriaWS(op);
    }

    private static java.util.List<java.lang.String> reporteCategoriaWS(java.lang.String op) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.reporteCategoriaWS(op);
    }

    private static java.util.List<java.lang.String> recomComprada() {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.recomComprada();
    }

    private static java.util.List<java.lang.String> recomCompradaIni() {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.recomCompradaIni();
    }

    private static java.util.List<java.lang.String> topWS(java.lang.String modo) {
        west.ws.PruebaServicio_Service service = new west.ws.PruebaServicio_Service();
        west.ws.PruebaServicio port = service.getPruebaServicioPort();
        return port.topWS(modo);
    }

}
