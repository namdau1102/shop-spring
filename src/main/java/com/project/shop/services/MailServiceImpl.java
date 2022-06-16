package com.project.shop.services;

import com.project.shop.models.Order;
import com.project.shop.models.User;
import com.project.shop.services.declarations.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void send(Order order, User user,List<Order> listOrder) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject("Thông báo đặt hàng - Thanh toán hàng hoá 🕹");
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("namdvpk02051@fpt.edu.vn");
            helper.setTo(user.getEmail());
//            String stringBuilder = "<html><body>" + "Zamówienie " +
//                    order.getId() +
//                    "<hr>" +
//                    order.getDate() +
//                    "<hr>" +
//                    order.getItem().getName() +
//                    "</body></html>";
            String topMailText = "<html lang=\"en\">\r\n"
            		+ "<style>\r\n"
            		+ "  * {\r\n"
            		+ "    margin: 0;\r\n"
            		+ "    padding: 0;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  .text {\r\n"
            		+ "    padding: 15px;\r\n"
            		+ "    text-align: center;\r\n"
            		+ "    color: #000;\r\n"
            		+ "    font-weight: bold;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  .detail-w {\r\n"
            		+ "    text-align: center;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  #customers {\r\n"
            		+ "    font-family: Arial, Helvetica, sans-serif;\r\n"
            		+ "    border-collapse: collapse;\r\n"
            		+ "    width: 100%;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  #customers td,\r\n"
            		+ "  #customers th {\r\n"
            		+ "    border: 1px solid #ddd;\r\n"
            		+ "    padding: 8px;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  #customers tr:nth-child(even) {\r\n"
            		+ "    background-color: #f2f2f2;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  #customers tr:hover {\r\n"
            		+ "    background-color: #ddd;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  #customers th {\r\n"
            		+ "    padding-top: 12px;\r\n"
            		+ "    padding-bottom: 12px;\r\n"
            		+ "    text-align: left;\r\n"
            		+ "    background-color: #04AA6D;\r\n"
            		+ "    color: white;\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ "  .tabler-root {\r\n"
            		+ "    width: 50%;\r\n"
            		+ "    text-align: center;\r\n"
            		+ "    margin: auto;\r\n"
            		+ "\r\n"
            		+ "  }\r\n"
            		+ "\r\n"
            		+ " .w {\r\n"
            		+ "    \r\n"
            		+ "  }\r\n"
            		+ "</style>\r\n"
            		+ "\r\n"
            		+ "<body>\r\n"
            		+ "  <div>\r\n"
            		+ "    <div class=\"text\" style=\"\">THÔNG BÁO GIAO DỊCH</div>\r\n"
            		+ "    <p class=\"detail-w\">Cảm ơn bạn đã tin tưởng và mua hàng của chúng tôi</p>\r\n"
            		+ "    <h4 style=\"text-align:center;margin-top: 20px;\">Danh sách bạn đã mua</h4>\r\n"
            		+ "    <div class=\"tabler-root\">\r\n"
            		+ "      <h4>Mã đơn hàng : '"+order.getOrderId()+"'</h4>\r\n"
            		+ "      <h4>Ngày mua : '"+order.getDate()+"'</h4>\r\n"
            		+ "      \r\n"
            		+ "      <table id=\"customers\" style=\"margin-top:20px ;\">\r\n"
            		+ "        <tr>\r\n"
            		+ "          <th style=\"text-align: center;\">Tên mặt hàng </th>\r\n"
            		+ "          <th style=\"text-align: center;\">Số lượng </th>\r\n"
            		+ "          <th style=\"text-align: center;\">Giá tiền</th>\r\n"
            		+ "        </tr>\r\n";
            	
            	String aw = "";
            	float priceSum = 0;
            	for(Order item : listOrder) {
            		priceSum+=item.getItem().getPrice();
            		aw+= "<tr>\r\n"
                 			+ "  <th style=\"text-align: center;\" class=\"w\">'"+item.getItem().getName()+"'</th>\r\n"
                 			+ "  <th style=\"text-align: center;\" class=\"w\">'"+item.getAmount()+"'</th>\r\n"
                 			+ "  <th style=\"text-align: center;\" class=\"w\">'"+item.getItem().getPrice()+"'</th>\r\n"
                 			+ "</tr>";
            	}
            	String footer =  "       \r\n"
            		+ "      </table>\r\n"
            		+ "    </div>\r\n"
            		+ "<h4>Tổng tiền : '"+priceSum+"'</h4>\\r\\n\""
            		+ "  </div>\r\n"
            		+ "</body>\r\n"
            		+ "\r\n"
            		+ "</html>";
            String result=topMailText+aw+footer;
            helper.setText(result,true);
            mailSender.send(message);
        } catch (MessagingException ex) {
            System.out.println("Error ");
        }
    }

	@Override
	public void sendCode(int code, String title,String email) {
		// TODO Auto-generated method stub
		try {
            MimeMessage message = mailSender.createMimeMessage();

            message.setSubject(title);
            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setFrom("namdvpk02051@fpt.edu.vn");
            helper.setTo(email);
            String codeSend = "Mã của thay đổi mật khẩu của bạn là : '"+code+"'";
            helper.setText(codeSend,false);
            helper.setText(codeSend);
            mailSender.send(message);
		}
		catch (Exception e) {
			// TODO: handle exception
            System.out.println("Error ");

		}
	}
}
