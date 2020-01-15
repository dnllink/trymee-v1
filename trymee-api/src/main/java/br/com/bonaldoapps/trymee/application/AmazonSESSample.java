/**
 * 
 */
package br.com.bonaldoapps.trymee.application;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AmazonSESSample {

	// Replace sender@example.com with your "From" address.
	// This address must be verified.
	static final String FROM = "daniel.bonaldo@trymee.com.br";
	static final String FROMNAME = "Daniel Bonaldo";

	// Replace recipient@example.com with a "To" address. If your account
	// is still in the sandbox, this address must be verified.
	static final String TO = "daniel.bonaldo@trymee.com.br";

	// Replace smtp_username with your Amazon SES SMTP user name.
	static final String SMTP_USERNAME = "AKIAJ44N4QZC3CZDPGAQ";

	// Replace smtp_password with your Amazon SES SMTP password.
	static final String SMTP_PASSWORD = "AjJujdjsKbBVcBxC/Azh9AMMLk8s5Q7v8Bzzv/cMBlF/";

	// The name of the Configuration Set to use for this message.
	// If you comment out or remove this variable, you will also need to
	// comment out or remove the header on line 65.
	// static final String CONFIGSET = "ConfigSet";

	// Amazon SES SMTP host name. This example uses the US West (Oregon) Region.
	static final String HOST = "email-smtp.us-east-1.amazonaws.com";

	// The port you will connect to on the Amazon SES SMTP endpoint.
	static final int PORT = 465;

	static final String SUBJECT = "Amazon SES test (SMTP interface accessed using Java)";

//	static final String BODY = String.join(System.getProperty("line.separator"), "<h1>Amazon SES SMTP Email Test</h1>",
//			"<p>This email was sent with Amazon SES using the ",
//			"<a href='https://github.com/javaee/javamail'>Javamail Package</a>",
//			" for <a href='https://www.java.com'>Java</a>.");
	
	static final String BODY = new String("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> <html> <head> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> <title>Bem vindo ao Trymee!</title> <style type=\"text/css\"> @media(max-width:480px){   table[class=main_table],table[class=layout_table]{width:300px !important;}   table[class=layout_table] tbody tr td.header_image img{width:300px !important;height:auto !important;} } a{color:#37aadc} </style> </head> <body> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"> <tbody> <tr> <td align=\"center\" valign=\"top\"> <!--  M A I N T A B L E  S T A R T  --> <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"main_table\" width=\"600\">     <tbody>     <tr>     <td>       <!--  L A Y O U T _ T A B L E  S T A R T  -->     <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"layout_table\" style=\"border-collapse:collapse;border:1px solid #CCCCCC;\" width=\"100%\" >       <tbody>       <!--  H E A D E R R O W  S T A R T  -->       <tr>       <td align=\"left\" class=\"header_image\"><img height=\"64\" src=\"https://s3.amazonaws.com/trymee-files/images/site/header-tm-64-600.png\" width=\"600\" style=\"border:0;display:block;\"></td>       </tr>       <!--  H E A D E R R O W  E N D  -->       <tr><td style=\"font-size:13px;line-height:13px;margin:0;padding:0;\">&nbsp;</td></tr>       <!--  B O D Y R O W  S T A R T  -->       <tr>       <td align=\"center\" valign=\"top\">         <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\">           <tbody>           <tr>           <td align=\"center\">             <p style=\"font-family:Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;\"> 				Caro(a) {{name}}, 				<br/> 				<br/> 				Nós do Trymee desejamos as boas vindas e agradecemos o seu cadastro. 				<br/> 				Abaixo estão os detalhes do seu cadastro:             </p> 			<p> 				<table style=\"font-family:Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;\"> 					<tr> 						<td>Login:</td> 						<td>{{email}}</td> 					</tr> 					<tr> 						<td>Plano:</td> 						<td>{{plan}}</td> 					</tr> 					<tr> 						<td>Válido até:</td> 						<td>{{endDate}}</td> 					</tr> 					<tr> 						<td>Quantidade de Testes:</td> 						<td>{{quantity}}</td> 					</tr> 					<tr> 						<td>Preço:</td> 						<td>{{price}}</td> 					</tr> 				</table> 			</p> 			<p style=\"font-family:Arial, Helvetica, sans-serif;font-size:14px;line-height:22px;\"> 				 				Nosso objetivo é tornar os seus processos seletivos mais ágeis e produtivos, e para isso estamos a sua disposição para esclarecer qualquer dúvida e ouvir qualquer dica, crítica ou feedback. Entre em contato conosco respondendo esse e-mail ou envie para contato@trymee.com.br 				<br/> 				<br/> 				Novamente agradecemos a sua confiança e esperamos que a nossa plataforma seja útil e efetiva no seu dia a dia. 				<br/> 				<br/> 				Um grande abraço! 				Equipe Trymee App 			</p>           </td>           </tr>           </tbody>         </table>       </td>       </tr>       <!--  B O D Y R O W  E N D  -->       <tr><td style=\"font-size:13px;line-height:13px;margin:0;padding:0;\">&nbsp;</td></tr>       <!--  F O O T E R R O W  S T A R T        <tr>       <td align=\"left\" class=\"header_image\"><a href=\"https://www.sparkpost.com/\"><img height=\"67\" src=\"https://www.sparkpost.com/sites/default/files/pictures/email/footer-generic-600x67.png\" width=\"600\" style=\"border:0;display:block;\" alt=\"Sent via SparkPost\"></a></td>       </tr> 	  -->       <!--  F O O T E R R O W  E N D  -->       </tbody>     </table>     <!--  L A Y O U T _ T A B L E  E N D  -->     </td>     </tr>     </tbody> </table> <!--  M A I N _ T A B L E  E N D  --> </td> </tr> </tbody> </table> </body> </html>");  

	public static void main(String[] args) throws Exception {

		// Create a Properties object to contain connection configuration
		// information.
		Properties props = System.getProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.port", PORT);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.auth", "true");

		// Create a Session object to represent a mail session with the
		// specified properties.
		Session session = Session.getDefaultInstance(props);

		// Create a message with the specified information.
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(FROM, FROMNAME));
		msg.setRecipient(Message.RecipientType.TO, new InternetAddress(TO));
		msg.setSubject(SUBJECT);
		msg.setContent(BODY, "text/html");

		// Add a configuration set header. Comment or delete the
		// next line if you are not using a configuration set
		// msg.setHeader("X-SES-CONFIGURATION-SET", CONFIGSET);

		// Create a transport.
		Transport transport = session.getTransport();

		// Send the message.
		try {
			System.out.println("Sending...");

			// Connect to Amazon SES using the SMTP username and password you
			// specified above.
			transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

			// Send the email.
			transport.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Email sent!");
		} catch (Exception ex) {
			System.out.println("The email was not sent.");
			System.out.println("Error message: " + ex.getMessage());
		} finally {
			// Close and terminate the connection.
			transport.close();
		}
	}
}