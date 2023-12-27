// License: LGPL-3.0 License (c) find-sec-bugs
package file;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FileUploadFileName {

    public void handleFileCommon(HttpServletRequest req) throws FileUploadException {
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> fileItems = upload.parseRequest(req);

        for (FileItem item : fileItems) {
            System.out.println("Saving " + item.getName() + "...");
        }
    }

}
