package com.dilfer.tech;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.OpcPackage;
import org.docx4j.openpackaging.packages.ProtectDocument;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tr;

public class DocumentX {

	public static void main(String[] args) throws Exception {

		System.out.println(System.getProperty("user.dir"));
		String inputfilepath = System.getProperty("user.dir") + "/invoice.docx";
		
		WordprocessingMLPackage wordMLPackage = (WordprocessingMLPackage)OpcPackage
				.load(new java.io.File(inputfilepath), "foobaaQ");
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		List<Object> content = documentPart.getContent();
		content.forEach(v->{
			if(v instanceof javax.xml.bind.JAXBElement) {
				JAXBElement<Tbl> element = (JAXBElement<Tbl>)v;
				System.out.println("\t"+((Tr)element.getValue().getContent().get(0)).getContent());
				
			}
			System.out.println(v);
			
			
		});

		List<String> allowedStyleNames = new ArrayList<String>();
		allowedStyleNames.add("heading 2");
		allowedStyleNames.add("heading 3");
		
		ProtectDocument protection = new ProtectDocument(wordMLPackage);
		protection.restrictFormatting(allowedStyleNames, true, //remove!
				false, true, false);
		
		
		String filename = System.getProperty("user.dir") + "/invoice_modified.docx";
		
		Docx4J.save(wordMLPackage, new java.io.File(filename)); // , Docx4J.FLAG_SAVE_ENCRYPTED_AGILE, "foobaa");
		
		System.out.println("Saved " + filename);
		}

}
