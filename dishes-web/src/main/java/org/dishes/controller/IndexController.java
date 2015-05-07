package org.dishes.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dishes.commons.ConstantsValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/index")
public class IndexController {
	
	@RequestMapping("/getCheckImg")
	public String getCheckImg(HttpServletResponse response,HttpServletRequest request){
		try {
			
			// 创建图片 -- 在内存中
			int width = 80;
			int height = 40;
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			//创建图层，获得画板
			Graphics g = image.getGraphics();
			//确定画笔颜色
			g.setColor(Color.BLACK);
			//填充一个矩形
			g.fillRect(0, 0, width, height);
			//只需要一个边框
			//设置颜色
			g.setColor(Color.WHITE);
			//填充一个矩形
			g.fillRect(1, 1, width -2, height -2);
			
			//填充字符
			String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
			//设置字体
			g.setFont(new Font("微软雅黑",Font.BOLD,30));
			
			//缓存随机生成的字符
			StringBuffer buf = new StringBuffer();
			
			//随机获得4个字符
			Random random = new Random();
			for(int i = 0 ; i < 4 ; i++){
				//设置随机颜色
				g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				//获得一个随机字符
				int index = random.nextInt(62);
				//截取字符串
				String str = data.substring(index, index + 1);  //[)
				//需要将随机的字符，写到图片中
				g.drawString(str, 20 * i, 30);
				//缓存
				buf.append(str);
			}
			
			//将获得随机字符串，保存到session
			// * 获得session
			
			HttpSession session = request.getSession();
			// * 保存值
			session.setAttribute(ConstantsValue.CHECK_NUM_NAME_SESSION, buf.toString());
			//干扰线
			for(int i = 0 ; i < 10 ; i ++){
				//设置随机颜色
				g.setColor(new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255)));
				//随机画直线
				g.drawLine(random.nextInt(width), random.nextInt(height), random.nextInt(width), random.nextInt(height));
			}
			
			//通知浏览器发送的数据时一张图片
			response.setContentType("image/jpeg");
			//将图片发送给浏览器
			ImageIO.write(image, "jpg", response.getOutputStream());
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
}
