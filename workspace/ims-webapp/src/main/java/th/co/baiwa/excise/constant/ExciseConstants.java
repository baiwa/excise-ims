package th.co.baiwa.excise.constant;

import java.util.Locale;

public class ExciseConstants {
	
	    public static class LOCALE {
	        public static final Locale TH = new Locale("th", "TH");
	        public static final Locale EN = new Locale("en", "US");
	    }
	    
	    public static class FORMAT_DATE {
	        public static final String DDMMYYYY = "dd/MM/yyyy";
	        public static final String YYYYMMDD = "yyyyMMdd"; 
	        public static final String DD  = "dd";
	        public static final String MM  = "MM";
	        public static final String YYYY  = "yyyy";
	    }
	    
	    public static class SEARCH_FLAG {
	    	public static final String TRUE = "TRUE";
	    	public static final String FALSE = "FALSE";
	    }
	    
	    public static class TA {
	    	public static class STATUS {
		    	public static final String PROCESS = "2048";
		    	public static final String PASS = "2057";
		    	public static final String NOT_PASS = "2056";
		    	public static final String SUCCESS = "2055";		    			    			   
		    }
	    }

}
