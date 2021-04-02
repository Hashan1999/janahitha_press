/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Grn;
import hibernate.RawItem;
import hibernate.Stock;
import hibernate.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class addStock extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();

        int grnId = Integer.parseInt(request.getParameter("grnId"));
        int qty = Integer.parseInt(request.getParameter("qty"));
        String rawItemName = request.getParameter("rawItemName");
        Byte status = 1;

        System.out.println(grnId);
        Criteria c = session.createCriteria(RawItem.class);
        c.add(Restrictions.eq("name", rawItemName));
        RawItem ri = (RawItem) c.uniqueResult();

        Criteria c2 = session.createCriteria(Grn.class);
        c2.add(Restrictions.eq("idgrn", grnId));
        Grn g = (Grn) c2.uniqueResult();

        Criteria c3 = session.createCriteria(Stock.class);
        List<Stock> stockList = c3.list();

        Stock s = new Stock();
        
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        
        
        s.setAddedDate(date);
        s.setGrn(g);
        s.setQty(qty);
        s.setRawItem(ri);
        s.setStatus(status);

        session.save(s);
        Transaction tr = session.beginTransaction();
        tr.commit();

    }

}
