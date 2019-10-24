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
        // 检测是否为multipart/form-data上传，如果不是则停止
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
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
        String outerpath = System.getProperty("user.dir") + File.separator + "WebRoot";	//最外层文件夹
        String courseid	 = (String) request.getAttribute("courseid");					//取课程id作为外层文件夹名
        String roletype  = (String) request.getAttribute("roletype");					//取登陆用户的角色类型
        String userid 	 = null;														//取用户id为内层文件夹名
        boolean isstudent = true;
        if (roletype.equals("student")) {
        	userid = (String) request.getAttribute("studentid");
        }
        else if (roletype.equals("teacher")) {
        	userid = (String) request.getAttribute("teacherid");
        	isstudent = false;
        }
        else {
        	PrintWriter writer = response.getWriter();
            writer.println("Error: 仅有学生和老师可上传文件！");
            writer.flush();
            return;
        }
        String uploadpath = outerpath + File.separator + courseid + File.separator + userid;//构建最终存储路径
        //String uploadPath = outerpath + "\\" + courseid + "\\" + userid;
        
        
        // 如果目录不存在则创建
        File uploadDir = new File(uploadpath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                    	//第一个getname()返回值有时只是文件名，有时是绝对路径
                        String fileName = new File(item.getName()).getName();//文件名file_name
                        String filePath = uploadpath + File.separator + fileName;//文件的绝对路径，file_url
                        File storeFile = new File(filePath);
                        System.out.println(filePath);// 在控制台输出文件的上传路径
                        item.write(storeFile);// 保存文件到硬盘
                        Date time = new Date(storeFile.lastModified());
                        String filetime = time.toString();//文件最后修改时间（创建时间）,create_time
                        request.setAttribute("message","文件上传成功!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "错误信息: " +  ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/homework1.jsp").forward(request, response);// 跳转
    }
}
