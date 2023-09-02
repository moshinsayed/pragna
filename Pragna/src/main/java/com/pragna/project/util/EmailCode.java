package com.pragna.project.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class EmailCode {

	public String sendEmail(JSONObject jsonObject)
	{
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
		}
		
		
		String postUrl = "https://api.zeptomail.in/v1.1/email";
        BufferedReader br = null;
        HttpURLConnection conn = null;
        String output = null;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(postUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Authorization", "Zoho-enczapikey PHtE6r1cQuDqiGcv8UNW5vO6E86gMd8t9O5vfwBDtocRA/BQG01UrN1+lD61ohYqXfIWRf7OzdlstuiZ5riCJD7lNGlLCWqyqK3sx/VYSPOZsbq6x00cslwfckfUVY/nctNj1i3Wud/bNA==");
           
            OutputStream os = conn.getOutputStream();
            os.write(jsonObject.toString().getBytes());
            os.flush();
            br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            br = new BufferedReader(new InputStreamReader((conn.getErrorStream())));
            try {
				while ((output = br.readLine()) != null) {
				    sb.append(output);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            System.out.println(sb.toString());
            return sb.toString();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        }
		    
	}
	
	public String verifyUserEmail(String userEmail, String userName)
	{
		JSONObject jsonObject = new JSONObject();
        jsonObject.put("from", new JSONObject()
            .put("address", "noreply@pragyaan.co")
            .put("name", "PRAGNA KIDS")
        );
        jsonObject.put("to", new JSONObject[] {
            new JSONObject().put("email_address", new JSONObject()
                    .put("address", userEmail)
                    .put("name", userName)
                )
        });
        jsonObject.put("subject", "Verify your email address");
        jsonObject.put("htmlbody", verify_email_html_code(123456, userEmail));
        
        return sendEmail(jsonObject); 

	}
	
	private String verify_email_html_code(int code, String mail)
	{
		String str = "";
		str += "<!DOCTYPE html>";
		str += "";
		str += "<html lang=\"en\" xmlns:o=\"urn:schemas-microsoft-com:office:office\" xmlns:v=\"urn:schemas-microsoft-com:vml\">";
		str += "<head>";
		str += "<title></title>";
		str += "<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\"/>";
		str += "<meta content=\"width=device-width, initial-scale=1.0\" name=\"viewport\"/><!--[if mso]><xml><o:OfficeDocumentSettings><o:PixelsPerInch>96</o:PixelsPerInch><o:AllowPNG/></o:OfficeDocumentSettings></xml><![endif]-->";
		str += "<style>";
		str += "		* {";
		str += "			box-sizing: border-box;";
		str += "		}";
		str += "";
		str += "		body {";
		str += "			margin: 0;";
		str += "			padding: 0;";
		str += "		}";
		str += "";
		str += "		a[x-apple-data-detectors] {";
		str += "			color: inherit !important;";
		str += "			text-decoration: inherit !important;";
		str += "		}";
		str += "";
		str += "		#MessageViewBody a {";
		str += "			color: inherit;";
		str += "			text-decoration: none;";
		str += "		}";
		str += "";
		str += "		p {";
		str += "			line-height: inherit";
		str += "		}";
		str += "";
		str += "		.desktop_hide,";
		str += "		.desktop_hide table {";
		str += "			mso-hide: all;";
		str += "			display: none;";
		str += "			max-height: 0px;";
		str += "			overflow: hidden;";
		str += "		}";
		str += "";
		str += "		.image_block img+div {";
		str += "			display: none;";
		str += "		}";
		str += "";
		str += "		@media (max-width:620px) {";
		str += "";
		str += "			.desktop_hide table.icons-inner,";
		str += "			.social_block.desktop_hide .social-table {";
		str += "				display: inline-block !important;";
		str += "			}";
		str += "";
		str += "			.icons-inner {";
		str += "				text-align: center;";
		str += "			}";
		str += "";
		str += "			.icons-inner td {";
		str += "				margin: 0 auto;";
		str += "			}";
		str += "";
		str += "			.mobile_hide {";
		str += "				display: none;";
		str += "			}";
		str += "";
		str += "			.row-content {";
		str += "				width: 100% !important;";
		str += "			}";
		str += "";
		str += "			.stack .column {";
		str += "				width: 100%;";
		str += "				display: block;";
		str += "			}";
		str += "";
		str += "			.mobile_hide {";
		str += "				min-height: 0;";
		str += "				max-height: 0;";
		str += "				max-width: 0;";
		str += "				overflow: hidden;";
		str += "				font-size: 0px;";
		str += "			}";
		str += "";
		str += "			.desktop_hide,";
		str += "			.desktop_hide table {";
		str += "				display: table !important;";
		str += "				max-height: none !important;";
		str += "			}";
		str += "		}";
		str += "	</style>";
		str += "</head>";
		str += "<body style=\"background-color: #fff; margin: 0; padding: 0; -webkit-text-size-adjust: none; text-size-adjust: none;\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"nl-container\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #fff;\" width=\"100%\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000; width: 600px; margin: 0 auto;\" width=\"600\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\" style=\"width:100%;\">";
		str += "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/download.jpg\" style=\"display: block; height: auto; border: 0; max-width: 341px; width: 100%;\" width=\"341\"/></div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000; width: 600px; margin: 0 auto;\" width=\"600\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"image_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\" style=\"width:100%;\">";
		str += "<div align=\"center\" class=\"alignment\" style=\"line-height:10px\"><img alt=\"I'm an image\" src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/1.png\" style=\"display: block; height: auto; border: 0; max-width: 125px; width: 100%;\" title=\"I'm an image\" width=\"125\"/></div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div style=\"font-family: sans-serif\">";
		str += "<div class=\"\" style=\"font-size: 12px; font-family: Arial, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">";
		str += "<p style=\"margin: 0; font-size: 16px; text-align: center; mso-line-height-alt: 19.2px;\"><span style=\"font-size:38px;\"><strong>Hey, thanks for signing up!</strong></span></p>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div style=\"font-family: sans-serif\">";
		str += "<div class=\"\" style=\"font-size: 12px; font-family: Arial, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #555555; line-height: 1.2;\">";
		str += "<p style=\"margin: 0; font-size: 14px; text-align: center; mso-line-height-alt: 16.8px;\"><span style=\"font-size:18px;\">Confirm your email and start exploring pragna kids</span></p>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"button_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div align=\"center\" class=\"alignment\"><!--[if mso]><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" style=\"height:42px;width:278px;v-text-anchor:middle;\" arcsize=\"10%\" stroke=\"false\" fillcolor=\"#dd2240\"><w:anchorlock/><v:textbox inset=\"0px,0px,0px,0px\"><center style=\"color:#ffffff; font-family:Arial, sans-serif; font-size:16px\"><![endif]-->";
		str += "<a href=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/loginRegister/"+mail+"\" style=\"color:white\"><div id=\"myButton\" style=\"text-decoration: none; display: inline-block; color: #ffffff; background-color: #dd2240; border-radius: 4px; width: auto; border-top: 0px solid transparent; font-weight: 700; border-right: 0px solid transparent; border-bottom: 0px solid transparent; border-left: 0px solid transparent; padding-top: 5px; padding-bottom: 5px; font-family: Arial, 'Helvetica Neue', Helvetica, sans-serif; font-size: 16px; text-align: center; mso-border-alt: none; word-break: keep-all; cursor: pointer;\">";
		str += "  <span style=\"padding-left: 20px; padding-right: 20px; font-size: 16px; display: inline-block; letter-spacing: normal;\">";
		str += "    <span style=\"margin: 0; word-break: break-word; line-height: 32px;\">CONFIRM MY EMAIL ADDRESS</span>";
		str += "  </span>";
		str += "</div></a><!--[if mso]></center></v:textbox></v:roundrect><![endif]-->";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<div class=\"spacer_block block-5\" style=\"height:60px;line-height:60px;font-size:1px;\"> </div>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; background-color: #272727;\" width=\"100%\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000; width: 600px; margin: 0 auto;\" width=\"600\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"html_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div align=\"center\" style=\"font-family:Arial, Helvetica, sans-serif;text-align:center;\"><div style=\"height:30px;\"> </div>";
		str += "<div style=\"height:50px;color:white\">Follow us on Social Media</div></div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social_block block-2\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\" style=\"text-align:center;padding-right:0px;padding-left:0px;\">";
		str += "<div align=\"center\" class=\"alignment\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"social-table\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; display: inline-block;\" width=\"208px\">";
		str += "<tr>";
		str += "<td style=\"padding:0 10px 0 10px;\"><a href=\"https://www.facebook.com/\" target=\"_blank\"><img alt=\"Facebook\" height=\"32\" src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/facebook2x.png\" style=\"display: block; height: auto; border: 0;\" title=\"Facebook\" width=\"32\"/></a></td>";
		str += "<td style=\"padding:0 10px 0 10px;\"><a href=\"https://twitter.com/\" target=\"_blank\"><img alt=\"Twitter\" height=\"32\" src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/twitter2x.png\" style=\"display: block; height: auto; border: 0;\" title=\"Twitter\" width=\"32\"/></a></td>";
		str += "<td style=\"padding:0 10px 0 10px;\"><a href=\"https://plus.google.com/\" target=\"_blank\"><img alt=\"Google+\" height=\"32\" src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/googleplus2x.png\" style=\"display: block; height: auto; border: 0;\" title=\"Google+\" width=\"32\"/></a></td>";
		str += "<td style=\"padding:0 10px 0 10px;\"><a href=\"https://instagram.com/\" target=\"_blank\"><img alt=\"Instagram\" height=\"32\" src=\"https://dev.zevoirtechnologies.com:8443/pragna_dev/images/instagram2x.png\" style=\"display: block; height: auto; border: 0;\" title=\"Instagram\" width=\"32\"/></a></td>";
		str += "</tr>";
		str += "</table>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"html_block block-3\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div align=\"center\" style=\"font-family:Arial, Helvetica, sans-serif;text-align:center;\"><div style=\"margin-top: 40px;border-top:1px dashed #D6D6D6;margin-bottom: 40px;\"></div></div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"10\" cellspacing=\"0\" class=\"text_block block-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; word-break: break-word;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div style=\"font-family: sans-serif\">";
		str += "<div class=\"\" style=\"font-size: 12px; font-family: Arial, Helvetica, sans-serif; mso-line-height-alt: 14.399999999999999px; color: #C0C0C0; line-height: 1.2;\">";
		str += "<p style=\"margin: 0; font-size: 12px; text-align: center; mso-line-height-alt: 14.399999999999999px;\"><span style=\"color:#C0C0C0;\">Copyright © 2023 <strong>Pragna Kids</strong>, All rights reserved.<br/></span></p>";
		str += "</div>";
		str += "</div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"html_block block-5\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\">";
		str += "<div align=\"center\" style=\"font-family:Arial, Helvetica, sans-serif;text-align:center;\"><div style=\"height-top: 20px;\"> </div></div>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row row-4\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td>";
		str += "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"row-content stack\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; color: #000; width: 600px; margin: 0 auto;\" width=\"600\">";
		str += "<tbody>";
		str += "<tr>";
		str += "<td class=\"column column-1\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt; font-weight: 400; text-align: left; padding-bottom: 5px; padding-top: 5px; vertical-align: top; border-top: 0px; border-right: 0px; border-bottom: 0px; border-left: 0px;\" width=\"100%\">";
		str += "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"icons_block block-1\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"pad\" style=\"vertical-align: middle; color: #9d9d9d; font-family: inherit; font-size: 15px; padding-bottom: 5px; padding-top: 5px; text-align: center;\">";
		str += "<table cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"mso-table-lspace: 0pt; mso-table-rspace: 0pt;\" width=\"100%\">";
		str += "<tr>";
		str += "<td class=\"alignment\" style=\"vertical-align: middle; text-align: center;\">";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table>";
		str += "</td>";
		str += "</tr>";
		str += "</tbody>";
		str += "</table><!-- End -->";
		str += "</body>";
		str += "    <script>";
		str += "document.getElementById('myButton').addEventListener('click', function() {";
		str += "    // Add your button functionality here";
		str += "    window.open('https://dev.zevoirtechnologies.com:8443/pragna_dev/loginRegister'); // Example: Show an alert";
		str += "    // You can add more code to perform the desired action when the button is clicked";
		str += "  });    </script>";
		str += "</html>";

		return str;
	}
}
