package com.example.web.demo;

import java.util.ArrayList;

public class Designer {
    public static String createHtmlConfirm(String uuid, String name) {
        String confirmEmail = "<div class=\"\">\n" +
                "  <div class=\"aHl\"></div>\n" +
                "  <div id=\":io\" tabindex=\"-1\"></div>\n" +
                "  <div id=\":id\" class=\"ii gt\" jslog=\"20277; u014N:xr6bB; 4:W251bGwsbnVsbCxbXV0.\">\n" +
                "    <div id=\":ic\" class=\"a3s aiL \">\n" +
                "      <div dir=\"ltr\">\n" +
                "        <div>\n" +
                "          <div class=\"adM\"><br></div>\n" +
                "          <table style=\"width:1534px;height:600px;\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "            <tbody>\n" +
                "              <tr>\n" +
                "                <td style=\"vertical-align:top\">\n" +
                "                  <table style=\"width:600px\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" +
                "                    <tbody>\n" +
                "                      <tr>\n" +
                "                        <td style=\"text-align:center;padding-top:40px;padding-bottom:20px\"><a href=\"https://zoom.us/\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://zoom.us/&amp;source=gmail&amp;ust=1643811012255000&amp;usg=AOvVaw0BM6hd5d0yUHvcHhLB3iIj\"><img src=\"https://koios.hr/wp-content/themes/sapling/library/images/logo_koios_en_US.png\" style=\"width:140px;height:auto;border-width:0px;border-style:solid\" class=\"CToWUd\"></a></td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td style=\"background-color:rgb(255,255,255);padding-bottom:40px\">\n" +
                "                          <table style=\"width:520px\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;padding-top:40px;font-size:14px;line-height:20px;color:rgb(51,51,51)\">Hello "+name+",</td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;padding-top:12px;font-size:14px;line-height:20px;color:rgb(51,51,51)\">An account has been created for you. Please click the button below to activate your account within 30 days.</td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"padding-top:20px\" align=\"center\">\n" +
                "                                  <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" +
                "                                    <tbody>\n" +
                "                                      <tr>\n" +
                "                                        <td style=\"padding-left:30px;padding-top:10px;padding-bottom:10px;background-color:rgb(45,140,255);border-top-left-radius:4px;border-bottom-left-radius:4px\">&nbsp;</td>\n" +
                "                                        <td style=\"font-family:helvetica,arial;background-color:rgb(45,140,255);font-size:16px;line-height:24px;font-weight:700;color:rgb(255,255,255);padding-top:10px;padding-bottom:10px\"><a href=\"http://localhost:8080/activateEmail?uuid="+uuid+"\" style=\"color:rgb(255,255,255);text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://btb-012:3000/activate/cfc76438-74a9-408c-92c4-8d76c0c44333/ef877e89-94d8-4a40-a0e2-6def42c072ed&amp;source=gmail&amp;ust=1643811012255000&amp;usg=AOvVaw00o-qDs0F5uqs5Vz68dp3M\">Activate Your Account</a></td>\n" +
                "                                        <td style=\"padding-right:30px;padding-top:10px;padding-bottom:10px;background-color:rgb(45,140,255);border-top-right-radius:4px;border-bottom-right-radius:4px\">&nbsp;</td>\n" +
                "                                      </tr>\n" +
                "                                    </tbody>\n" +
                "                                  </table>\n" +
                "                                </td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;padding-top:30px;font-size:14px;line-height:20px;color:rgb(51,51,51)\">If the above button does not work for you, copy and paste the link to your browser address bar and try again.</td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;padding-top:12px;font-size:14px;line-height:20px;color:rgb(51,51,51);word-break:break-all\"><a href=\"http://localhost:8080/activateEmail?uuid="+uuid+"\" style=\"color:rgb(45,140,255);text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=http://btb-012:3000/activate/cfc76438-74a9-408c-92c4-8d76c0c44333/ef877e89-94d8-4a40-a0e2-6def42c072ed&amp;source=gmail&amp;ust=1643811012255000&amp;usg=AOvVaw00o-qDs0F5uqs5Vz68dp3M\">http://localhost:8080/activateEmail?uuid="+uuid+"</a></td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;padding-top:32px;font-size:14px;line-height:20px;color:rgb(51,51,51)\">Thank you for choosing us.</td>\n" +
                "                              </tr>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;font-size:14px;line-height:20px;color:rgb(51,51,51)\"></td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                      <tr>\n" +
                "                        <td style=\"padding-top:30px;padding-bottom:30px;background-color:rgb(255,255,255);border-top:1px solid rgb(240,242,244)\">\n" +
                "                          <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                              <tr>\n" +
                "                                <td><a href=\"https://twitter.com/zoom_us\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://twitter.com/zoom_us&amp;source=gmail&amp;ust=1643811012255000&amp;usg=AOvVaw3L0U9Dv9obXem4P6mRvUEI\"><img alt=\"\" src=\"https://ci6.googleusercontent.com/proxy/uiZ8FDTRpBcJMqzvH2jwJNFZOwOlICIV94kRlIac8qJSpxmsuLarNpMUqw4h_LJE1gcmmJW08CcfWV4q_vQ7wevHmTx0C5_lhjUY2oiphCStmtL603k=s0-d-e1-ft#https://go.pardot.com/l/84442/2015-06-24/wht/84442/5264/twitter.png\" style=\"width:25px;height:25px;border-width:0px;border-style:solid\" class=\"CToWUd\" height=\"25\" border=\"0\"></a></td>\n" +
                "                                <td style=\"padding-left:10px;padding-right:10px\"><a href=\"https://www.linkedin.com/company/zoom-video-communications/\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://www.linkedin.com/company/zoom-video-communications/&amp;source=gmail&amp;ust=1643811012256000&amp;usg=AOvVaw3cTmqBEMeUrm-cbpPw8ZaF\"><img alt=\"\" src=\"https://ci5.googleusercontent.com/proxy/yUHcKSby8CZygL-b6QTAm_Lnh_KvqXgBkdy1fIWtYWOLfH90ioMT00JjOJYdO8nC5GLBZdkVZZlNu17ad6rSzAv04o1M1r5HurzE_K1yy8G4X1KaFe9d=s0-d-e1-ft#https://go.pardot.com/l/84442/2015-06-24/wj1/84442/5270/linkedin.png\" style=\"width:25px;height:25px;border-width:0px;border-style:solid\" class=\"CToWUd\" height=\"25\" border=\"0\"></a></td>\n" +
                "                                <td><a href=\"https://blog.zoom.us/\" style=\"text-decoration-line:none\" target=\"_blank\" data-saferedirecturl=\"https://www.google.com/url?q=https://blog.zoom.us/&amp;source=gmail&amp;ust=1643811012256000&amp;usg=AOvVaw3Ap5iFwLSt3OCuK3988MCU\"><img alt=\"\" src=\"https://ci6.googleusercontent.com/proxy/ttNZ4YcoVg0uxIwHwT7k78oIcTPH3HIoVC1K6g9NkBEWODvSMzo4lRtEh7jzmBPPP1-WSwazxxSo9781sxNJONH6wbLMxF3uEejkjyzP8VHn2UquNTJR=s0-d-e1-ft#https://go.pardot.com/l/84442/2015-06-24/wv9/84442/5282/zoomblog.png\" style=\"width:25px;height:25px;border-width:0px;border-style:solid\" class=\"CToWUd\" border=\"0\"></a></td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                          <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                              <tr>\n" +
                "                                <td style=\"font-family:helvetica,arial;font-size:12px;line-height:18px;color:rgb(153,153,153);text-align:center;padding-top:10px\"><br>Copyright ©2022 Koios. All rights reserved.</td>\n" +
                "                              </tr>\n" +
                "                            </tbody>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </tbody>\n" +
                "                  </table>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tbody>\n" +
                "          </table>\n" +
                "        </div>\n" +
                "        <font color=\"#888888\"><br clear=\"all\">\n" +
                "          <div><br></div>-- <br>\n" +
                "          <div dir=\"ltr\" data-smartmail=\"gmail_signature\">\n" +
                "            <div dir=\"ltr\">\n" +
                "              <div dir=\"ltr\" style=\"color:rgb(34,34,34)\">\n" +
                "                <div dir=\"ltr\">\n" +
                "                  <div style=\"color:rgb(0,0,0);font-family:&quot;trebuchet ms&quot;,helvetica,sans-serif;font-size:13px;overflow:auto\"><strong style=\"float:left;display:inline;color:rgb(128,128,128)\">\n" +
                "                  <div style=\"color:rgb(0,0,0);font-weight:400;overflow:auto\"></div>";
        return confirmEmail;
    }

    public static StringBuilder designOpcije(ArrayList<String> string){///pravi html za selector od string liste datuma
        StringBuilder opcijeDatumi = new StringBuilder();
        for (String s : string) {
            opcijeDatumi.append(String.format("<option value=\"%s\">%s</option>",s,s));
        }
        return opcijeDatumi;
    }
}
