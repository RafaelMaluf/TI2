package dao;

import java.io.*;

import java.util.*;

import javax.servlet.*;




public class SessionTeste extends HttpServlet

{

  public void doGet(HttpServletRequest req, HttpServletResponse resp)

     throws IOException, ServletException

  {

     resp.setContentType("text/html");



   PrintWriter out = resp.getWriter();

   out.println("<html><head>");

   out.println("<title>Teste de Sessao</title>");

   out.println("</head>");

   out.println("<body>");

   out.println("<h3>Teste de Sessao</h3>");

   HttpSession session = req.getSession(true);

   out.println("Identificador: " + session.getld());

   out.println("<br>");

   out.println("Data: ");

   out.println(new Date(session.getCreationTime()) + "<br>");

   out.println("Último acesso: ");

   out.println(new Date(session.getLastAccessedTime()));



     String nomedado = req.getParameter("nomedado");

     String valordado = req.getParameter("valordado");

     if (nomedado != null && valordado != null)

     {

        session.setAttribute(nomedado, valordado);

     }



   out.println("<P>");

   out.println("Dados da Sessao:" + "<br>");

   Enumeration valueNames = session.getAttributeNames();



     while (valueNames.hasMoreElements())

     {

         String name = (String)valueNames.nextElement();

         String value = (String) session.getAttribute(name);

         out.println(name + "=" + value+ "<br>");

     }



   out.println("<P>");

   out.println("<form action="+SessionTeste+" method=POST>");

   out.println("Nome: <input type=text size=20 name=nomedado><br>");

   out.println("Valor: <input type=text size=20 name=valordado><br>");

   out.println("<input type=submit>");

   out.println("</form>");

   out.println("</body></html>");

     }



  public void doPost(HttpServletRequest req, HttpServletResponse resp)

        throws IOException, ServletException

     {

        doGet(req, resp);

     }
 }