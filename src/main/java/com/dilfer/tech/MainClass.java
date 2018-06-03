package com.dilfer.tech;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;





public class MainClass {

	/*
	 *  -t           =  template location (Default, invoice_template.docx located in the jar file)
	 *	-w | b | m   = Weekly, bi-weekly, or Monthly (Default = w)
	 *	-s           =  Start Date (dd/mm/yy) (Default, First of the current month, or Sunday of the current week)
	 *	-hrs         = nn_wk1,nn_wk2,nn_wk3,nn_wk4 (Most match the number of weeks) (Default 40)
	 *	-rate        = Hourly rate (default 70)
	 *	-invNo       = Invoice Number  nnnn or AUTO (Default AUTO)
	 *	-taxRate     = Tax rates (default 5%)
	 *	-out         = Output folder (Default current folder)
	 *	-file        = Output File (default Inv_yyyyMMdd_invNo.docx)
	 */
	
	
	public static void main( String[] args ) {
		CommandLineParser parser = new DefaultParser();

		// create the Options
		Options options = new Options();
		
		
		// Template location 
		Option tempLoc = Option
				.builder("t")
				.longOpt("template")
				.desc("Location of the template word document (default, invoice_template.docx)")
				.hasArg(false)
				.build();
		options.addOption(tempLoc);
		
		Option period = Option
				.builder("p")
				.longOpt("period")
				.desc("Invoicing period (w|b|m)")
				.hasArgs()
				.argName("w=weekly, b=biweekly, m=monthly")
//				.required()
				.build();
		options.addOption(period);
		
		Option invNo = Option
				.builder("i")
				.longOpt("InvoiceNo")
				.desc("New Invoice Number")
				.hasArg()
				.argName("InvNo")
//				.required()
				.build();
		
		
		
		
		options.addOption(invNo);
		
	

		try {
		    // parse the command line arguments
		    CommandLine line = parser.parse( options, args );

		    HelpFormatter formatter = new HelpFormatter();
		    formatter.printHelp("Syntax", options);
		    // validate that block-size has been set
		    if( line.hasOption( "block-size" ) ) {
		        // print the value of block-size
		        System.out.println( line.getOptionValue( "block-size" ) );
		    }
		}
		catch( ParseException exp ) {
		    System.out.println( "Unexpected exception:" + exp.getMessage() );
		}
	}

}
