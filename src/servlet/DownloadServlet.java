package servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import dao.FileDAO;
import vo.MyFile;

public class DownloadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String path=this.getServletContext().getRealPath("/test/学习曲线.jpg");
//		FileInputStream f=new FileInputStream(path);
//		ServletOutputStream sos=resp.getOutputStream();
//		String filename=path.substring(path.lastIndexOf("\\"+1));
//		filename=URLEncoder.encode(filename,"utf-8");
//		resp.setHeader("content-disposition","attachment;filename="+filename);
//		resp.setHeader("content-type", "image/jpeg");
//		int len=1;
//		byte[] buff=new byte[1024];
//		while((len=f.read(buff))!=-1) {
//			sos.write(buff,0,len);
//		}
//		sos.close();
//		f.close();
		String method=req.getParameter("method");
		String courseid=req.getParameter("courseid");
		String teacherid=req.getParameter("teacherid");
		resp.setContentType("application/json");
    	resp.setCharacterEncoding("utf-8");
		PrintWriter out= resp.getWriter();
		if(method.equals("loadFileTable")) {
			System.out.println("进入loadfiletable");
			MyFile file=new MyFile();
			file.setCourseid(courseid);
			file.setTeacherid(teacherid);
			file.setFiletype(0);
			JSONArray coursehomeworktable=new JSONArray();
			try {
				coursehomeworktable=FileDAO.getCourseArchiveTable(file);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(coursearchivetable);
			out.println(coursehomeworktable);
		}
		else if(method.equals("loadArchiveTable")){
			System.out.println("开始提取课程资料");
			MyFile file=new MyFile();
			file.setCourseid(courseid);
			file.setTeacherid(teacherid);
			file.setFiletype(3);
			JSONArray coursearchivetable=new JSONArray();
			try {
				coursearchivetable=FileDAO.getCourseArchiveTable(file);
			} catch (SQLException | JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(coursearchivetable);
		}
	}
}
