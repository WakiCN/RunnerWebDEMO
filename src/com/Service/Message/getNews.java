package com.Service.Message;

import com.Controller.Controller;
import com.Dao.HibernateEntity.Message.News;
import com.Dao.HibernateSessionFactory;
import com.alibaba.fastjson.JSONObject;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 为解决前台获得新闻而存在的类
 *
 * @author Waki
 */
public class getNews extends Controller {

    /**
     * 需要传入Number值来决定起始位置
     * 每次返回十条新闻标题
     */
    public void getNews(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int number = 0;     //起始位置  前台处理页数数据 1 是第一页 11是第二页 21是第三页
        int MaxNumber = 10;
        try {
            number = Integer.parseInt(req.getParameter("Number")) - 1;
        } catch (NumberFormatException n) {
        }
        SessionFactory sf = HibernateSessionFactory.getSf();
        Session se = sf.openSession();
        se.beginTransaction();
        try {
            NewsBean nb = new NewsBean();
            nb.setGoon(true);
            int DataMax = getNewsCount(se);
            if (number >= DataMax) {
                number = 0;           //判断分页起点是否大于数据库表的最终点
            }
            if (number + 10 >= DataMax) {
                MaxNumber = DataMax - number;       //分页起点+10是否大于数据库表的最终点，如果大于则减少返回数据的数量
                nb.setGoon(false);
            }
            Query q = se.createQuery("from News where news_display=0");
            q.setFirstResult(number);
            q.setMaxResults(MaxNumber);
            List<News> li = q.list();
            se.getTransaction().commit();
            for (News n :
                    li) {
                n.setNews_Text("");         //清空查询出来的新闻主体文件(只显示新闻标题的话，就不需要新闻主体了)
            }
            nb.setLi(li);
            resp.getWriter().print(JSONObject.toJSONString(nb));
        } catch (Exception ex) {
            se.getTransaction().rollback();
            ex.printStackTrace();
            resp.getWriter().print("服务端查询错误");
        } finally {
            se.close();
        }
    }

    /**
     * 需要New_Id来决定返回哪条新闻
     */
    public void getNew(HttpServletRequest req, HttpServletResponse resp) {
        int New_Id;
        try {
            New_Id = Integer.parseInt(req.getParameter("new_Id"));
        } catch (NumberFormatException nuf) {
            New_Id = 1;
        }
        SessionFactory sf = HibernateSessionFactory.getSf();
        Session se = sf.openSession();
        try {
            se.beginTransaction();
            News n = se.get(News.class, New_Id);
            se.getTransaction().commit();
            if (n.getNews_display()==0){
                resp.getWriter().print(JSONObject.toJSONString(n));
            }else{
                resp.getWriter().print("该条新闻不存在");
            }
        } catch (Exception e) {
            se.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            se.close();
        }
    }

    private int getNewsCount(Session se) {
        Query q = se.createQuery("from News where news_display=0");
        ScrollableResults scroll = q.scroll();
        scroll.last();
        return scroll.getRowNumber() + 1;
    }
}
