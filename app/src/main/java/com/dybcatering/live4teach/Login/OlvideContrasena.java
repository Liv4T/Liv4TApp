package com.dybcatering.live4teach.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.dybcatering.live4teach.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class OlvideContrasena extends AppCompatActivity {

	public EditText usuario;
	public Button btnEnviar;
	public TextView regresar;
	private static String URL_FORGOT_PASSWORD= "https://dybcatering.com/back_live_app/forgot_password_user.php";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_olvide_contrasena);
		usuario = findViewById(R.id.user_name);
		btnEnviar = findViewById(R.id.btn_enviar);
		regresar = findViewById(R.id.text_olvide_contrasena);
		btnEnviar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!usuario.getText().toString().equals("")){

					enviarDatos(usuario.getText().toString());
				}else{
					Toast.makeText(OlvideContrasena.this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show();
				}
			}
		});
		regresar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(OlvideContrasena.this, LoginActivity.class);
				finish();
				startActivity(intent);
			}
		});
	}

	private void sendRegistrationEmail( final String emails, final String nuevapass) {


		BackgroundMail.newBuilder(OlvideContrasena.this)
				.withSendingMessage("Envio de su contraseña al correo registrado!")
				.withSendingMessageSuccess("Compruebe amablemente su correo electrónico ahora!")
				.withSendingMessageError("¡Error al enviar la contraseña! Inténtalo de nuevo !")
				.withUsername("prueba.correo7521@gmail.com")
				.withPassword("N1m2g3e4r57791")
				.withMailto(emails)
				.withType(BackgroundMail.TYPE_HTML)
				.withSubject("Reestablecimiento de Contraseña Live4Teach")
				.withBody("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><meta name=\"x-apple-disable-message-reformatting\" /><meta name=\"apple-mobile-web-app-capable\" content=\"yes\" /><meta name=\"apple-mobile-web-app-status-bar-style\" content=\"black\" /><meta name=\"format-detection\" content=\"telephone=no\" /><title></title><style type=\"text/css\">\n" +
						"/* Resets */\n" +
						".ReadMsgBody { width: 100%; background-color: #ebebeb;}\n" +
						".ExternalClass {width: 100%; background-color: #ebebeb;}\n" +
						".ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div {line-height:100%;}\n" +
						"a[x-apple-data-detectors]{\n" +
						"color:inherit !important;\n" +
						"text-decoration:none !important;\n" +
						"font-size:inherit !important;\n" +
						"font-family:inherit !important;\n" +
						"font-weight:inherit !important;\n" +
						"line-height:inherit !important;\n" +
						"}        \n" +
						"body {-webkit-text-size-adjust:none; -ms-text-size-adjust:none;}\n" +
						"body {margin:0; padding:0;}\n" +
						".yshortcuts a {border-bottom: none !important;}\n" +
						".rnb-del-min-width{ min-width: 0 !important; }\n" +
						"\n" +
						"/* Add new outlook css start */\n" +
						".templateContainer{\n" +
						"max-width:590px !important;\n" +
						"width:auto !important;\n" +
						"}\n" +
						"/* Add new outlook css end */\n" +
						"\n" +
						"/* Image width by default for 3 columns */\n" +
						"img[class=\"rnb-col-3-img\"] {\n" +
						"max-width:170px;\n" +
						"}\n" +
						"\n" +
						"/* Image width by default for 2 columns */\n" +
						"img[class=\"rnb-col-2-img\"] {\n" +
						"max-width:264px;\n" +
						"}\n" +
						"\n" +
						"/* Image width by default for 2 columns aside small size */\n" +
						"img[class=\"rnb-col-2-img-side-xs\"] {\n" +
						"max-width:180px;\n" +
						"}\n" +
						"\n" +
						"/* Image width by default for 2 columns aside big size */\n" +
						"img[class=\"rnb-col-2-img-side-xl\"] {\n" +
						"max-width:350px;\n" +
						"}\n" +
						"\n" +
						"/* Image width by default for 1 column */\n" +
						"img[class=\"rnb-col-1-img\"] {\n" +
						"max-width:550px;\n" +
						"}\n" +
						"\n" +
						"/* Image width by default for header */\n" +
						"img[class=\"rnb-header-img\"] {\n" +
						"max-width:590px;\n" +
						"}\n" +
						"\n" +
						"/* Ckeditor line-height spacing */\n" +
						".rnb-force-col p, ul, ol{margin:0px!important;}\n" +
						".rnb-del-min-width p, ul, ol{margin:0px!important;}\n" +
						"\n" +
						"/* tmpl-2 preview */\n" +
						".rnb-tmpl-width{ width:100%!important;}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".rnb-social-width{padding-right:15px!important;}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".rnb-social-align{float:right!important;}\n" +
						"\n" +
						"/* Ul Li outlook extra spacing fix */\n" +
						"li{mso-margin-top-alt: 0; mso-margin-bottom-alt: 0;}        \n" +
						"\n" +
						"/* Outlook fix */\n" +
						"table {mso-table-lspace:0pt; mso-table-rspace:0pt;}\n" +
						"\n" +
						"/* Outlook fix */\n" +
						"table, tr, td {border-collapse: collapse;}\n" +
						"\n" +
						"/* Outlook fix */\n" +
						"p,a,li,blockquote {mso-line-height-rule:exactly;} \n" +
						"\n" +
						"/* Outlook fix */\n" +
						".msib-right-img { mso-padding-alt: 0 !important;}\n" +
						"\n" +
						"/* Fix text line height on preview */ \n" +
						".content-spacing div {display: list-item; list-style-type: none;}\n" +
						"\n" +
						"@media only screen and (min-width:590px){\n" +
						"/* mac fix width */\n" +
						".templateContainer{width:590px !important;}\n" +
						"}\n" +
						"\n" +
						"@media screen and (max-width: 360px){\n" +
						"/* yahoo app fix width \"tmpl-2 tmpl-10 tmpl-13\" in android devices */\n" +
						".rnb-yahoo-width{ width:360px !important;}\n" +
						"}\n" +
						"\n" +
						"@media screen and (max-width: 380px){\n" +
						"/* fix width and font size \"tmpl-4 tmpl-6\" in mobile preview */\n" +
						".element-img-text{ font-size:24px !important;}\n" +
						".element-img-text2{ width:230px !important;}\n" +
						".content-img-text-tmpl-6{ font-size:24px !important;}\n" +
						".content-img-text2-tmpl-6{ width:220px !important;}\n" +
						"}\n" +
						"\n" +
						"@media screen and (max-width: 480px) {\n" +
						"td[class=\"rnb-container-padding\"] {\n" +
						"padding-left: 10px !important;\n" +
						"padding-right: 10px !important;\n" +
						"}\n" +
						"\n" +
						"/* force container nav to (horizontal) blocks */\n" +
						"td.rnb-force-nav {\n" +
						"display: inherit;\n" +
						"}\n" +
						"\n" +
						"/* fix text alignment \"tmpl-11\" in mobile preview */\n" +
						".rnb-social-text-left {\n" +
						"width: 100%;\n" +
						"text-align: center;\n" +
						"margin-bottom: 15px;\n" +
						"}\n" +
						".rnb-social-text-right {\n" +
						"width: 100%;\n" +
						"text-align: center;\n" +
						"}\n" +
						"}\n" +
						"\n" +
						"@media only screen and (max-width: 600px) {\n" +
						"\n" +
						"/* center the address &amp; social icons */\n" +
						".rnb-text-center {text-align:center !important;}\n" +
						"\n" +
						"/* force container columns to (horizontal) blocks */\n" +
						"td.rnb-force-col {\n" +
						"display: block;\n" +
						"padding-right: 0 !important;\n" +
						"padding-left: 0 !important;\n" +
						"width:100%;\n" +
						"}\n" +
						"\n" +
						"table.rnb-container {\n" +
						" width: 100% !important;\n" +
						"}\n" +
						"\n" +
						"table.rnb-btn-col-content {\n" +
						"width: 100% !important;\n" +
						"}\n" +
						"table.rnb-col-3 {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"\n" +
						"/* change left/right padding and margins to top/bottom ones */\n" +
						"margin-bottom: 10px;\n" +
						"padding-bottom: 10px;\n" +
						"/*border-bottom: 1px solid #eee;*/\n" +
						"}\n" +
						"\n" +
						"table.rnb-last-col-3 {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"}\n" +
						"\n" +
						"table.rnb-col-2 {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"\n" +
						"/* change left/right padding and margins to top/bottom ones */\n" +
						"margin-bottom: 10px;\n" +
						"padding-bottom: 10px;\n" +
						"/*border-bottom: 1px solid #eee;*/\n" +
						"}\n" +
						"\n" +
						"table.rnb-col-2-noborder-onright {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"\n" +
						"/* change left/right padding and margins to top/bottom ones */\n" +
						"margin-bottom: 10px;\n" +
						"padding-bottom: 10px;\n" +
						"}\n" +
						"\n" +
						"table.rnb-col-2-noborder-onleft {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"\n" +
						"/* change left/right padding and margins to top/bottom ones */\n" +
						"margin-top: 10px;\n" +
						"padding-top: 10px;\n" +
						"}\n" +
						"\n" +
						"table.rnb-last-col-2 {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"}\n" +
						"\n" +
						"table.rnb-col-1 {\n" +
						"/* unset table align=\"left/right\" */\n" +
						"float: none !important;\n" +
						"width: 100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-col-3-img {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-col-2-img {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-col-2-img-side-xs {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-col-2-img-side-xl {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-col-1-img {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"img.rnb-header-img {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"margin:0 auto;\n" +
						"}\n" +
						"\n" +
						"img.rnb-logo-img {\n" +
						"/**max-width:none !important;**/\n" +
						"width:100% !important;\n" +
						"}\n" +
						"\n" +
						"td.rnb-mbl-float-none {\n" +
						"float:inherit !important;\n" +
						"}\n" +
						"\n" +
						".img-block-center{text-align:center !important;}\n" +
						"\n" +
						".logo-img-center\n" +
						"{\n" +
						"float:inherit !important;\n" +
						"}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".rnb-social-align{margin:0 auto !important; float:inherit !important;}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".rnb-social-center{display:inline-block;}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".social-text-spacing{margin-bottom:0px !important; padding-bottom:0px !important;}\n" +
						"\n" +
						"/* tmpl-11 preview */\n" +
						".social-text-spacing2{padding-top:15px !important;}\n" +
						"\n" +
						"}</style><!--[if gte mso 11]><style type=\"text/css\">table{border-spacing: 0; }table td {border-collapse: separate;}</style><![endif]--><!--[if !mso]><!--><style type=\"text/css\">table{border-spacing: 0;} table td {border-collapse: collapse;}</style> <!--<![endif]--><!--[if gte mso 15]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--><!--[if gte mso 9]><xml><o:OfficeDocumentSettings><o:AllowPNG/><o:PixelsPerInch>96</o:PixelsPerInch></o:OfficeDocumentSettings></xml><![endif]--></head><body>\n" +
						"\n" +
						"<table border=\"0\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" class=\"main-template\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255);\">\n" +
						"\n" +
						"<tbody><tr style=\"display:none !important; font-size:1px; mso-hide: all;\"><td></td><td></td></tr><tr>\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"<!--[if gte mso 9]>\n" +
						"<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"590\" style=\"width:590px;\">\n" +
						"<tr>\n" +
						"<td align=\"center\" valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
						"<![endif]-->\n" +
						"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\" style=\"max-width:590px!important; width: 590px;\">\n" +
						"<tbody><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_2620\" id=\"Layout_2620\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-del-min-width\" valign=\"top\" align=\"center\" style=\"min-width:590px;\">\n" +
						"<table width=\"100%\" cellpadding=\"0\" border=\"0\" height=\"30\" cellspacing=\"0\">\n" +
						"<tbody><tr>\n" +
						"<td valign=\"top\" height=\"30\">\n" +
						"<img width=\"20\" height=\"30\" style=\"display:block; max-height:30px; max-width:20px;\" alt=\"\" src=\"http://img.mailinblue.com/new_images/rnb/rnb_space.gif\">\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
						"<tr>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
						"<![endif]-->\n" +
						"<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_1\" id=\"Layout_1\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" style=\"min-width:590px;\">\n" +
						"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255); border-bottom: 1px solid rgb(200, 200, 200); border-radius: 0px; padding-left: 20px; padding-right: 20px; border-collapse: separate;\">\n" +
						"<tbody><tr>\n" +
						"<td height=\"40\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td valign=\"top\" class=\"rnb-container-padding\" align=\"left\">\n" +
						"<table width=\"100%\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\">\n" +
						"<tbody><tr>\n" +
						"<td valign=\"top\" align=\"center\">\n" +
						"<table cellpadding=\"0\" border=\"0\" align=\"left\" cellspacing=\"0\" class=\"logo-img-center\"> \n" +
						"<tbody><tr>\n" +
						"<td valign=\"middle\" align=\"center\" style=\"line-height: 1px;\">\n" +
						"<div style=\"border-top:0px None #000;border-right:0px None #000;border-bottom:0px None #000;border-left:0px None #000;display:inline-block; \" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><div><img width=\"200\" vspace=\"0\" hspace=\"0\" border=\"0\" alt=\"Sendinblue\" style=\"float: left;max-width:200px;display:block;\" class=\"rnb-logo-img\" src=\"https://imageneslive4teach.000webhostapp.com/imagenes/liveteach_logo.png\"></div></div></td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table></td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td height=\"40\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"<!--[if mso]>\n" +
						"</td>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"</tr>\n" +
						"</table>\n" +
						"<![endif]-->\n" +
						"\n" +
						"</div></td>\n" +
						"</tr><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
						"<tr>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
						"<![endif]-->\n" +
						"<table class=\"rnb-del-min-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:100%;\" name=\"Layout_5\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\">\n" +
						"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-container\" bgcolor=\"#ffffff\" style=\"background-color: rgb(255, 255, 255); padding-left: 20px; padding-right: 20px; border-collapse: separate; border-radius: 0px; border-bottom: 2px solid rgb(200, 200, 200);\">\n" +
						"\n" +
						"<tbody><tr>\n" +
						"<td height=\"30\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td valign=\"top\" class=\"rnb-container-padding\" align=\"left\">\n" +
						"\n" +
						"<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"rnb-columns-container\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-force-col\" valign=\"top\" style=\"padding-right: 0px;\">\n" +
						"\n" +
						"<table border=\"0\" valign=\"top\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" align=\"left\" class=\"rnb-col-1\">\n" +
						"\n" +
						"<tbody><tr>\n" +
						"<td class=\"content-spacing\" style=\"font-size:16px; font-family:'Arial',Helvetica,sans-serif, sans-serif; color:#999; line-height: 21px;\"><div>\n" +
						"<div style=\"line-height:150%;\"><span style=\"font-size:18px;\"><span style=\"color:#33c0c9;\"><strong>Hola Sr/Srta "+ emails +" ,</strong></span></span></div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\">&nbsp;</div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\">¡Estás recibiendo este mensaje porque hemos recibido la solicitud de cambio de contraseña para tu cuenta.<a href=\"#\" style=\"text-decoration: solid; color: rgb(51, 192, 201);\" target=\"_blank\"></a>. ¡Prometemos brindarle una experiencia de compra inolvidable en el futuro!.</div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\">&nbsp;</div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\"><strong>Tu contraseña es " +nuevapass +" </strong></div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\">&nbsp;</div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\"><span style=\"color:#33c0c9;\"><span style=\"font-size:18px;\"><strong>Saludos cordiales,</strong></span></span></div>\n" +
						"\n" +
						"<div style=\"line-height:150%;\">&nbsp;</div>\n" +
						"\n" +
						"<div style=\"line-height:100%;\">Live4Teach<br>\n" +
						"&nbsp;</div>\n" +
						"\n" +
						"<div style=\"line-height:100%;\"><span style=\"font-size:12px;\"><em>President/CEO<br>\n" +
						"Live4Teach</em></span></div>\n" +
						"</div>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"\n" +
						"</td></tr>\n" +
						"</tbody></table></td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td height=\"30\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table><!--[if mso]>\n" +
						"</td>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"</tr>\n" +
						"</table>\n" +
						"<![endif]-->\n" +
						"\n" +
						"</div></td>\n" +
						"</tr><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<div style=\"background-color: rgb(255, 255, 255); border-radius: 0px;\">\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
						"<tr>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"<td valign=\"top\" width=\"590\" style=\"width:590px;\">\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"</td>\n" +
						"<![endif]-->\n" +
						"\n" +
						"<!--[if mso]>\n" +
						"</tr>\n" +
						"</table>\n" +
						"<![endif]-->\n" +
						"\n" +
						"</div></td>\n" +
						"</tr><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<div style=\"background-color: rgb(255, 255, 255);\">\n" +
						"\n" +
						"<table class=\"rnb-del-min-width rnb-tmpl-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_3\" id=\"Layout_3\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" bgcolor=\"#ffffff\" style=\"min-width:590px; background-color: #ffffff; text-align: center;\">\n" +
						"<table width=\"590\" class=\"rnb-container\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\" bgcolor=\"#ffffff\" style=\"padding-right: 20px; padding-left: 20px; background-color: rgb(255, 255, 255);\">\n" +
						"<tbody><tr>\n" +
						"<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td>\n" +
						"<div style=\"font-size:14px; color:#919191; font-weight:normal; text-align:center; font-family:'Arial',Helvetica,sans-serif;\"><div>\n" +
						"<div style=\"line-height:200%;\">Este correo fue enviado a "+emails+"</div>\n" +
						"\n" +
						"<div style=\"line-height:200%;\">You received this email because you are registered with Your Company</div>\n" +
						"\n" +
						"<div>&nbsp;</div>\n" +
						"</div>\n" +
						"</div>\n" +
						"<div style=\"display: block; text-align: center;\">\n" +
						"<span style=\"font-size:14px; font-weight:normal; display: inline-block; text-align:center; font-family:'Arial',Helvetica,sans-serif;\">\n" +
						"<a style=\"text-decoration:none; color:#ccc;font-size:14px;font-weight:normal;font-family:'Arial',Helvetica,sans-serif;\" target=\"_blank\" href=\"{{ unsubscribe }}\"></a></span>\n" +
						"</div>\n" +
						"</td></tr>\n" +
						"<tr>\n" +
						"<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"\n" +
						"</tr><tr>\n" +
						"<td height=\"10\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr></tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"\n" +
						"</div></td>\n" +
						"</tr><tr>\n" +
						"\n" +
						"<td align=\"center\" valign=\"top\">\n" +
						"\n" +
						"<div style=\"background-color: rgb(51, 192, 201);\">\n" +
						"\n" +
						"<table class=\"rnb-del-min-width rnb-tmpl-width\" width=\"100%\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\" style=\"min-width:590px;\" name=\"Layout_4\" id=\"Layout_4\">\n" +
						"<tbody><tr>\n" +
						"<td class=\"rnb-del-min-width\" align=\"center\" valign=\"top\" style=\"min-width:590px;\">\n" +
						"<table width=\"100%\" cellpadding=\"0\" border=\"0\" align=\"center\" cellspacing=\"0\" bgcolor=\"#33c0c9\" style=\"padding-right: 20px; padding-left: 20px; background-color: rgb(51, 192, 201);\">\n" +
						"<tbody><tr>\n" +
						"<td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"<tr>\n" +
						"<td style=\"font-size:14px; color:#ffffff; font-weight:normal; text-align:center; font-family:'Arial',Helvetica,sans-serif;\">\n" +
						"<div><div>© 2020 Live4Teach</div>\n" +
						"</div>\n" +
						"</td></tr>\n" +
						"<tr>\n" +
						"<td height=\"20\" style=\"font-size:1px; line-height:0px; mso-hide: all;\">&nbsp;</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"\n" +
						"</div></td>\n" +
						"</tr></tbody></table>\n" +
						"<!--[if gte mso 9]>\n" +
						"</td>\n" +
						"</tr>\n" +
						"</table>\n" +
						"<![endif]-->\n" +
						"</td>\n" +
						"</tr>\n" +
						"</tbody></table>\n" +
						"\n" +
						"</body></html>")
				//"<p>Hola Mr/Miss, <b>'"+ name + "'</b></p>"+ "\n " + getString(R.string.registermail1))
				.send();



	}

	public void enviarDatos(final String usuario){
		final ProgressDialog progressDialog = new ProgressDialog(OlvideContrasena.this);
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);

		final String usuario_enviado= usuario;
		final String password = UUID.randomUUID().toString().substring(0,6);

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FORGOT_PASSWORD,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();

						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");

							if (success.equals("1")) {
								sendRegistrationEmail(usuario_enviado, password);
								Toasty.success(OlvideContrasena.this, "Se ha enviado tu contraseña al correo "+ usuario_enviado, Toast.LENGTH_SHORT).show();
								//Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
								//	sessionManager.createSession(nombre, usuario, id, "3");
								finish();
							}else{
								Toasty.error(OlvideContrasena.this, "Usuario no existe", Toast.LENGTH_LONG).show();
							}

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast.makeText(OlvideContrasena.this, "Error " + e.toString(), Toast.LENGTH_SHORT).show();
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						progressDialog.dismiss();
						Toast.makeText(OlvideContrasena.this, "Error " + error.toString(), Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				params.put("email", usuario_enviado);
				params.put("password", password);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(OlvideContrasena.this);
		requestQueue.add(stringRequest);

	}


}
