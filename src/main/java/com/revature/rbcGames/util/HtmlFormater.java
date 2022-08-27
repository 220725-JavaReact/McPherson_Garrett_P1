package com.revature.rbcGames.util;

public class HtmlFormater {
	static public String head = "<html>\r\n"
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
			+ "    </head>\r\n"
			+ "    <header style=\"color: white;\">\r\n"
			+ "        <img src=\"images/logo.png\" alt=\"company logo\" width=\"100px\" height=\"100px\">\r\n"
			+ "        <h1>RBC Games</h1>\r\n"
			+ "        <p ><i>Rediscover Retro</i></p>"
			+ "    </header>";
	
	static public String tail ="    </body>\r\n"
			+ "</html>";

}
