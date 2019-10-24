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
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  	// 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 500; 	// 500MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 525; 	// 525MB
    
    //�ݴ���ʱĿ¼
    //E:\Myeclipse_program\MIPS246\WebRoot
    //private static final String UPLOAD_TEMP_DIRCTORY = "E:\\Myeclipse_program\\MIPS246\\WebRoot\\temp";
    private static final String UPLOAD_TEMP_DIRCTORY = System.getProperty("user.dir") + File.separator + "temp";
    
     //�ϴ����ݼ������ļ�
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // ����Ƿ�Ϊmultipart/form-data�ϴ������������ֹͣ
        if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Error: ��������� enctype=multipart/form-data");
            writer.flush();
            return;
        }
        
        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);// �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        //factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        factory.setRepository(new File(UPLOAD_TEMP_DIRCTORY));// ������ʱ�洢Ŀ¼
 
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);	// ��������ļ��ϴ�ֵ
        upload.setSizeMax(MAX_REQUEST_SIZE);	// �����������ֵ (�����ļ��ͱ�����)
        upload.setHeaderEncoding("UTF-8"); 		// ���Ĵ���

        // ����·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
        //String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        //String uploadPath = "E:\\Myeclipse_program\\MIPS246\\WebRoot\\homework\\�����߼�";
        //System.out.println(uploadPath);
        //String outerpath = "E:\\Myeclipse_program\\MIPS246\\WebRoot";
        String outerpath = System.getProperty("user.dir") + File.separator + "WebRoot";	//������ļ���
        String courseid	 = (String) request.getAttribute("courseid");					//ȡ�γ�id��Ϊ����ļ�����
        String roletype  = (String) request.getAttribute("roletype");					//ȡ��½�û��Ľ�ɫ����
        String userid 	 = null;														//ȡ�û�idΪ�ڲ��ļ�����
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
            writer.println("Error: ����ѧ������ʦ���ϴ��ļ���");
            writer.flush();
            return;
        }
        String uploadpath = outerpath + File.separator + courseid + File.separator + userid;//�������մ洢·��
        //String uploadPath = outerpath + "\\" + courseid + "\\" + userid;
        
        
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(uploadpath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // ���������������ȡ�ļ�����
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // ����������
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                    if (!item.isFormField()) {
                    	//��һ��getname()����ֵ��ʱֻ���ļ�������ʱ�Ǿ���·��
                        String fileName = new File(item.getName()).getName();//�ļ���file_name
                        String filePath = uploadpath + File.separator + fileName;//�ļ��ľ���·����file_url
                        File storeFile = new File(filePath);
                        System.out.println(filePath);// �ڿ���̨����ļ����ϴ�·��
                        item.write(storeFile);// �����ļ���Ӳ��
                        Date time = new Date(storeFile.lastModified());
                        String filetime = time.toString();//�ļ�����޸�ʱ�䣨����ʱ�䣩,create_time
                        request.setAttribute("message","�ļ��ϴ��ɹ�!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message", "������Ϣ: " +  ex.getMessage());
        }
        getServletContext().getRequestDispatcher("/homework1.jsp").forward(request, response);// ��ת
    }
}
