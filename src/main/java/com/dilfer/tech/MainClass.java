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
		try {
			
			Options options = getOptions();
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
	
	
	private static Options getOptions() {
		
		Options options = new Options();
		
		
		// Template location 
		Option tempLoc = Option
				.builder("t")
				.longOpt("template")
				.desc("Location of the template word document (default, invoice_template.docx)")
				.hasArg()
				.argName("classpath : template word documwnt")
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
		
		Option startDate = Option
				.builder("s")
				.longOpt("startDate")
				.desc("Start Date of the invoice. (default : Sunday of current week)")
				.hasArg()
				.argName("dd/mm/yyyy")
//				.required()
				.build();
		options.addOption(startDate);
		
		Option hrs = Option
				.builder("h")
				.longOpt("hours")
				.desc("Hrs worked/week (Default 40/wk)")
				.hasArgs()
				.numberOfArgs(4)
				.argName("wk1,wk2,. (number of weeks)")
//				.required()
				.build();
		options.addOption(hrs);
		
		Option rate = Option
				.builder("r")
				.longOpt("rate")
				.desc("Hourly Rate")
				.hasArg()
				.argName("rate")
//				.required()
				.build();
		options.addOption(rate);
		
		Option invNo = Option
				.builder("i")
				.longOpt("InvoiceNo")
				.desc("New Invoice Number")
				.hasArg()
				.argName("InvNo")
//				.required()
				.build();
		options.addOption(invNo);
		
		Option tax = Option
				.builder("tax")
				.longOpt("taxRate")
				.desc("Tax Rate (default 5%)")
				.hasArg()
				.argName("tax rate")
				.build();
		options.addOption(tax);
		
		Option out = Option
				.builder("o")
				.longOpt("output")
				.desc("Output folder (default current folder)")
				.hasArg()
				.argName("folder")
				.build();
		options.addOption(out);
		
		Option file = Option
				.builder("f")
				.longOpt("file")
				.desc("Output File (default Inv_yyyyMMdd_invNo.docx)")
				.hasArg()
				.argName("file name")
				.build();
		options.addOption(file);
		return options;
	}

}
