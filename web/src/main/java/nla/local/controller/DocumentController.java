package nla.local.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import nla.local.exception.ServiceDaoException;
import nla.local.exception.ServiceException;
import nla.local.pojos.docs.Docdata;
import nla.local.pojos.docs.Document;

import nla.local.services.IDocumentService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
    public ResponseEntity<InputStreamResource> getDocs (Long doc_id) throws ServiceDaoException, SQLException {

        logger.info("root - /doc/getDocs");

        HttpHeaders headers = new HttpHeaders();

        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");

        headers.add("Pragma", "no-cache");

        headers.add("Expires", "0");

        Docdata ret_val = documentService.getDocument(doc_id);

        InputStreamResource isr = new InputStreamResource(ret_val.getDoc_content().getBinaryStream());

        return new ResponseEntity<InputStreamResource>(isr, headers, HttpStatus.OK);

    }


    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public void getFile( Long doc_id,  HttpServletResponse response) throws ServiceDaoException, SQLException, IOException {

            Docdata ret_val = documentService.getDocument(doc_id);

            InputStream is = ret_val.getDoc_content().getBinaryStream();

            IOUtils.copy(is, response.getOutputStream());

            response.flushBuffer();

    }


    @RequestMapping(value = "/uploadDocuments", method = RequestMethod.POST)
    public void uploadDocuments(@RequestParam("descs") List<Document> Docdescs,  @RequestParam("file") MultipartFile[] files) throws IOException, SQLException, ServiceDaoException {

        String fileName = null;

        List<Docdata> doc_data_list = new ArrayList<Docdata>();

        if (files != null && files.length >0) { return ;}

            for(int i =0; i< files.length; i++){

                Docdata doc_data = new Docdata();

                Blob docContent = new SerialBlob(files[i].getBytes());

                doc_data.setDoc_content(docContent);

                doc_data_list.add(doc_data);

            }

            documentService.addDocuments(Docdescs,doc_data_list);

        }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody void upload(  HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Part file = request.getPart("file");

        response.setContentType("text/plain");

        response.setCharacterEncoding("UTF-8");



    }


    @RequestMapping(value = "/uploadDocument", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
    public void uploadDocument(@RequestBody String doc_desc, MultipartFile file) throws IOException, SQLException, ServiceDaoException {

        Document docDesc = new ObjectMapper().readValue(doc_desc, Document.class);

        docDesc.setDocument_date(new Date());

        Docdata doc_data = new Docdata();

        Blob docContent = new SerialBlob(file.getBytes());

        doc_data.setDoc_content(docContent);

        documentService.addDocument(docDesc, doc_data);

    }

    @RequestMapping(value = "/uploadDoc", method = RequestMethod.POST, headers = "Content-Type=multipart/form-data")
    public void uploadDoc( @RequestParam("file") MultipartFile file) throws IOException, SQLException, ServiceDaoException {

        Docdata doc_data = new Docdata();

        Blob docContent = new SerialBlob(file.getBytes());

        doc_data.setDoc_content(docContent);

    }

}
