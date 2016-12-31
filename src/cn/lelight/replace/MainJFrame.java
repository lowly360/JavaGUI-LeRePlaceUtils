package cn.lelight.replace;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import cn.lelight.replace.plane.AppInfoPlane;
import cn.lelight.replace.plane.CutAndChangeColorPanle;
import cn.lelight.replace.plane.ImagePlane;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version 创建时间：2016年12月31日 下午1:45:00 类说明
 */
public class MainJFrame {
	// 主界面
	private Container contentPane;
	// tab
	private JTabbedPane jtp;
	// 步骤
	private String[] stepName = new String[] { "项目信息", "替换图片", "颜色切图" };

	public void showJFrame() {
		JFrame frame = new subJFrame("App 3.0+ 版本替换");
		// ----------------------------------------------
		frame.setSize(800, 650);
		frame.setResizable(false); // 固定窗口大小
		int windowWidth = frame.getWidth(); // 获得窗口宽
		int windowHeight = frame.getHeight(); // 获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
		Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
		int screenWidth = screenSize.width; // 获取屏幕的宽
		int screenHeight = screenSize.height; // 获取屏幕的高
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
		// ----------------------------------------------
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		JPanel stepPanel = new JPanel();
		stepPanel.setLayout(new BorderLayout());
		jtp = new JTabbedPane();
		stepPanel.add(jtp, BorderLayout.CENTER);

		JPanel okPanel = new JPanel();
		okPanel.setLayout(new FlowLayout());
		JButton okButton = new JButton("一  键  替  换");
		okButton.setPreferredSize(new Dimension(800, 35));
		okPanel.add(okButton);

		for (int i = 0; i < stepName.length; i++) {
			// 添加不同功能界面
			JPanel card;
			if (i == 0) {
				card = new AppInfoPlane(800, 600);
			} else if (i == 1) {
				card = new ImagePlane(800, 600);
			} else {
				card = new CutAndChangeColorPanle(800, 600);
			}
			jtp.add(stepName[i], card);
			// card.setBackground(color[i]);
		}

		contentPane.add(stepPanel, BorderLayout.CENTER);
		// contentPane.add(okPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

}
