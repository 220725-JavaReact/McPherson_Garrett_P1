package com.revature.rbcGames.util;

public class HtmlFormater {
	public static String head = "<html>\r\n"
			+ "        <head>\r\n"
			+ "        <style>\r\n"
			+ "            body {\r\n"
			+ "                text-align: center;\r\n"
			+ "                background-color: white;\r\n"
			+ "                border-color: black;\r\n"
			+ "                border-radius: 10%;\r\n"
			+ "                font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;\r\n"
			+ "            }\r\n"
			+ "            p {\r\n"
			+ "                background-color: rgba(0, 0, 0,.05);\r\n"
			+ "                border-radius: 10%;\r\n"
			+ "            }\r\n"
			+ "            header{\r\n"
			+ "                background-color: black;\r\n"
			+ "            }\r\n"
			+ "            img {\r\n"
			+ "               align-self: left;\r\n"
			+ "            }\r\n"
			+ "\r\n"
			+ "        </style>\r\n"
			+ "		<title>";
	public static String head2=		"</title>"
			+ "    </head>\r\n"
			+ "    <header style=\"color: white;\">\r\n"
			+ "<p style=\"text-align: right;\">";
	public static String head3= "</p>"
			+ "        <img src=\"images/logo.png\" alt=\"company logo\" width=\"100px\" height=\"100px\">\r\n"
			+ "        <h1>RBC Games</h1>\r\n"
			+ "        <p ><i>Rediscover Retro</i></p>"
			+ "    </header>";
	
	static public String tail ="    </body>\r\n"
			+ "    <footer>\r\n"
			+ "        <form method=\"post\" action = \"/McPherson_Garrett_P1/Logout\"> \r\n"
			+ "            <input type=\"submit\" value=\"Logout\">\r\n"
			+ "        </form>\r\n"
			+ "    </footer>"
			+ "</html>";
	static public String tailNoLogout = "    </body>\r\n"
			+ "    <footer>\r\n"
			+ "    </footer>"
			+ "</html>";
	
	public static String format(String title, String user, String body) {
		return head + title + head2 +user +head3+ body + tail;
	}
	public static String format(String title, String body) {
		return head + title + head2 +head3 + body + tailNoLogout;
	}

}
