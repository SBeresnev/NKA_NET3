package nla.local.controller;


import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.docs.Document;

import nla.local.services.IDocumentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by beresnev on 25.03.2016.
 */

@RestController
@RequestMapping({"/doc"})
public class DocumentController {

    @Autowired
    IDocumentService documentService;

    private static final Logger logger = Logger.getLogger(DocumentController.class);

    @RequestMapping(value = {"/getDocsDesc"}, method = {RequestMethod.GET})
    public List<Document> getDocsDesc(Long entyti_id, Long doc_id) throws ServiceDaoException, ServiceException {

      logger.info("root - /doc/getDocsDescription");

       List<Document> ret_val = documentService.getDescDocuments(entyti_id, doc_id);

     return ret_val;

    }

    @RequestMapping(value = {"/getDocs"}, method = {RequestMethod.GET})
    public List<Document> getDocs (Long entyti_id, Long doc_id)  throws ServiceDaoException, ServiceException{

        logger.info("root - /doc/getDocs");

        List<Document> ret_val = documentService.getDocuments(entyti_id, doc_id);

        return ret_val;


    }

    @RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
    public void uploadDocument(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files) {

        if (files.length != names.length) return ;

        /*
        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = names[i];
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("catalina.home");
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();

                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());

                message = message + "You successfully uploaded file=" + name
                        + "<br />";
            } catch (Exception e) {
                return null; //"You failed to upload " + name + " => " + e.getMessage();
            } */

        }

}
