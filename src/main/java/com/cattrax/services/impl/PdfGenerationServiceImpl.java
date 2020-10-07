package com.cattrax.services.impl;

import com.cattrax.services.PdfGenerationService;
import com.lowagie.text.DocumentException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dell User on 31/03/2017.
 * <p>
 * Copyright (C) 2017 Cattrax Ltd. All rights reserved.
 * This software is the confidential and proprietary information of Cat-trax Ltd.
 * You shall not disclose such confidential information and shall use it only in accordance
 * with the terms of the license agreement you entered into with Cat-trax Ltd.
 * Distribution or reproducing is strictly not allowed.
 */
@Service
public class PdfGenerationServiceImpl implements PdfGenerationService {
    private  org.apache.log4j.Logger logger= Logger.getLogger(PdfGenerationServiceImpl.class);
    //TODO: unit test following methods of this pdf service:
    @Override
    public void generatePdf(String url) throws DocumentException, IOException {
        logger.info("generatePdf() start");
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        String fileNameWithPath = url+"pdf file No."+(int) (Math.random()* (1000 - 1)) + 1;
        FileOutputStream fos = new FileOutputStream( fileNameWithPath );
        renderer.createPDF( fos );
        fos.close();
        logger.info("generatePdf() start");
    }

    @Override
    public void generatePdfFromXHTMLStr(String xhtmlString) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(xhtmlString);
        renderer.layout();

        String fileNameWithPath = "pdf_file"+(int) (Math.random()* (1000 - 1)) + 1;
        FileOutputStream fos = new FileOutputStream(fileNameWithPath );
        renderer.createPDF( fos );
        fos.close();

    }

    @Override
    public void generateAndSavePdf(String url, String pdfFileName)
            throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(url);
        renderer.layout();
        FileOutputStream fos = new FileOutputStream( pdfFileName );
        renderer.createPDF(fos);
        fos.close();
    }

    @Override
    public void generateAndSavePdfFromXHTMLStr(String xhtmlString, String pdfFileName) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(xhtmlString);
        renderer.layout();

        FileOutputStream fos = new FileOutputStream(pdfFileName );
        renderer.createPDF(fos);
        fos.close();
    }
}
