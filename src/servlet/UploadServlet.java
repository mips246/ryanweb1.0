package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.FileDAO;
import vo.MyFile;
 

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  	// 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 500; 	// 500MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 525; 	// 525MB
    
    //暂存临时目录
    //E:\Myeclipse_program\MIPS246\WebRoot
    //private static final String UPLOAD_TEMP_DIRCTORY = "E:\\Myeclipse_program\\MIPS246\\WebRoot\\temp";
    private static final String UPLOAD_TEMP_DIRCTORY = System.getProperty("user.dir") + File.separator + "temp";
    
     //上传数据及保存文件
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("< Upload >");
    	// 检测是否为multipart/form-data上传，如果不是则停止
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        
        //创建暂存目录
        File tempDir = new File(UPLOAD_TEMP_DIRCTORY);
        if (!tempDir.exists()) {
        	tempDir.mkdirs();
            System.out.println("<Create Folder "+UPLOAD_TEMP_DIRCTORY+" >");
        }
        
        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        factory.setRepository(new File(UPLOAD_TEMP_DIRCTORY));// 设置临时存储目录
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);	// 设置最大文件上传值
        upload.setSizeMax(MAX_REQUEST_SIZE);	// 设置最大请求值 (包含文件和表单数据)
        upload.setHeaderEncoding("UTF-8"); 		// 中文处理

        // 构造路径来存储上传的文件
        // 这个路径相对当前应用的目录
        //String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        //String uploadPath = "E:\\Myeclipse_program\\MIPS246\\WebRoot\\homework\\数字逻辑";
        //System.out.println(uploadPath);
        //String outerpath = "E:\\Myeclipse_program\\MIPS246\\WebRoot";
        String 	outerpath	  = System.getProperty("user.dir") + File.separator + "WebRoot";	//最外层文件夹
        
        /*
         * 错误的写法，无法直接用getParameter取得前端数据
        String 	courseid	  = (String) request.getParameter("courseid");						//取课程id作为外层文件夹名
        String 	roletype      = (String) request.getParameter("roletype");						//取登陆用户的角色类型
        
        
        System.out.println("outerpath:"+outerpath);
        System.out.println("courseid:"+courseid);
        System.out.println("roletype:"+roletype);
        
        
        String 	userid 	 	  = null;														//取用户id为内层文件夹名
        int		filetype 	  = Integer.parseInt(request.getParameter("filetype")) ;							//表示文件类型
        int 	coursesection = Integer.parseInt(request.getParameter("coursesection")) ;					//表示课程小节
        */
        
        /*
        MyFile myfile = new MyFile();
        myfile.setFiletype(filetype);
        myfile.setCoursesection(coursesection);
        myfile.setCourseid(courseid);
        myfile.setGrade(-1);
        */
        /*
        if (roletype.equals("student")) {
        	System.out.println("< Student Upload >");
        	
        	userid = (String) request.getParameter("studentid");
        	myfile.setStudentid(userid);
        	myfile.setTeacherid(null);
        }
        else if (roletype.equals("teacher")) {
        	System.out.println("< Teacher Upload >");
        	
        	userid = (String) request.getParameter("teacherid");
        	myfile.setStudentid(null);
        	myfile.setTeacherid(userid);
        }
        else {
        	PrintWriter writer = response.getWriter();
            writer.println("Error: 仅有学生和老师可上传文件！");
            writer.flush();
            return;
        }
        */
        /*
        String uploadpath = outerpath + File.separator + courseid + File.separator + userid;//构建最终存储路径
        //String uploadPath = outerpath + "\\" + courseid + "\\" + userid;
        
        // 如果目录不存在则创建
        File uploadDir = new File(uploadpath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        */
        String fieldName	 = null;//记录前端传来的input的名字
        String courseid 	 = null;
        String roletype 	 = null;
        String userid		 = null;
        String uploadpath    = null;
        String reurl		 = null;//相对路径
        int	   filetype 	 = 0;
        int	   coursesection = 1;
        
        MyFile myfile        = new MyFile();
        myfile.setGrade(-1);
        
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理文件
                    if (!item.isFormField()) {
                    	System.out.println("< Get File >");
                    	
                    	//第一个getname()返回值有时只是文件名，有时是绝对路径
                        String filename = new File(item.getName()).getName();//文件名file_name
                        String filePath = uploadpath + File.separator + filename;//文件的绝对路径，file_url
                        File storeFile = new File(filePath);
                        System.out.println(filePath);// 在控制台输出文件的上传路径
                        item.write(storeFile);// 保存文件到硬盘
                        Date time = new Date(storeFile.lastModified());
                        String filetime = time.toString();//文件最后修改时间（创建时间）,create_time
                        request.setAttribute("message","文件上传成功!");
                        
                        reurl += File.separator + filename;//相对路径
                        
                        myfile.setCreatetime(filetime);
                        myfile.setFileurl(reurl);
                        myfile.setFilename(filename);
                        
                        System.out.println("createtime:"+filetime);
                        System.out.println("filePath:"+filePath);
                        System.out.println("filename:"+filename);
                        
                        FileDAO.insert(myfile);
                    }
                    // 处理数据
                    else {
                    	fieldName = item.getFieldName();
                    	if("coursesection".equals(fieldName)) {
                    		coursesection = Integer.parseInt(item.getString());
                    		myfile.setCoursesection(coursesection);
                    		System.out.println("coursesection:"+coursesection);
                    	}
                    	else if("filetype".equals(fieldName)) {
                    		filetype = Integer.parseInt(item.getString());
                    		myfile.setFiletype(filetype);
                    		System.out.println("filetype:"+filetype);
                    	}
                    	else if("roletype".equals(fieldName)) {
                    		roletype = item.getString();
                    		System.out.println("roletype:"+roletype);
                    	}
                    	else if("courseid".equals(fieldName)) {
                    		courseid = item.getString();
                    		myfile.setCourseid(courseid);
                    		System.out.println("courseid:"+courseid);
                    	}
                    	else if("userid".equals(fieldName)) {
                    		userid = item.getString();
                    		
                    		if (roletype.equals("student")) {
                            	System.out.println("< Student Upload >");

                            	myfile.setStudentid(userid);
                            	myfile.setTeacherid(null);
                            	System.out.println("userid:"+userid);
                            }
                            else if (roletype.equals("teacher")) {
                            	System.out.println("< Teacher Upload >");

                            	myfile.setStudentid(null);
                            	myfile.setTeacherid(userid);
                            	System.out.println("userid:"+userid);
                            }
                    		
                    		//构建最终存储路径
                    		uploadpath = outerpath + File.separator + courseid + File.separator + userid;
                    		//构建相对路径
                    		reurl = File.separator + "WebRoot" + File.separator + courseid + File.separator + userid;
                    		// 如果目录不存在则创建
                            File uploadDir = new File(uploadpath);
                            if (!uploadDir.exists()) {
                                uploadDir.mkdirs();
                                System.out.println("<Create Folder "+uploadpath+" >");
                            }
                    	}
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "错误信息: " +  ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        /*
        if("teacher".equals(roletype)) {
        	//getServletContext().getRequestDispatcher("/teacher/teacher_course_manage.jsp").forward(request, response);// 跳转
        	getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
        }
        */
        //getServletContext().getRequestDispatcher("/homework1.jsp").forward(request, response);// 跳转
    }
}
