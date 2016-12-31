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
 * @version ����ʱ�䣺2016��12��31�� ����1:45:00 ��˵��
 */
public class MainJFrame {
	// ������
	private Container contentPane;
	// tab
	private JTabbedPane jtp;
	// ����
	private String[] stepName = new String[] { "��Ŀ��Ϣ", "�滻ͼƬ", "��ɫ��ͼ" };

	public void showJFrame() {
		JFrame frame = new subJFrame("App 3.0+ �汾�滻");
		// ----------------------------------------------
		frame.setSize(800, 650);
		frame.setResizable(false); // �̶����ڴ�С
		int windowWidth = frame.getWidth(); // ��ô��ڿ�
		int windowHeight = frame.getHeight(); // ��ô��ڸ�
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// ���ô��ھ�����ʾ
		// ----------------------------------------------
		contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());
		JPanel stepPanel = new JPanel();
		stepPanel.setLayout(new BorderLayout());
		jtp = new JTabbedPane();
		stepPanel.add(jtp, BorderLayout.CENTER);

		JPanel okPanel = new JPanel();
		okPanel.setLayout(new FlowLayout());
		JButton okButton = new JButton("һ  ��  ��  ��");
		okButton.setPreferredSize(new Dimension(800, 35));
		okPanel.add(okButton);

		for (int i = 0; i < stepName.length; i++) {
			// ��Ӳ�ͬ���ܽ���
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
