package cn.lelight.replace.plane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version ����ʱ�䣺2016��12��31�� ����2:24:37 ��˵��
 */
public class AppInfoPlane extends JPanel implements ActionListener {
	private int width, height;

	public AppInfoPlane(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		setSize(width, height);
		initPlane();
	}

	/**
	 * ��ʼ������
	 */
	private void initPlane() {
		setLayout(null);
		// App ��
		JLabel appIcon = new JLabel();
		ImageIcon icon = new ImageIcon("D:\\itlowly\\Eclipse\\LeRePlaceUtils\\res\\icon.png");
		appIcon.setIcon(icon);
		appIcon.setSize(167,167);
		appIcon.setLocation(317, 32);
		add(appIcon);
		// ��ʼ�� ����
		initAppNameAndPackage();

		// initScrDrcFilePathPane();
		// TODO Auto-generated method stub

	}

	private void initAppNameAndPackage() {
		// app �� app ���� �û���Ϣ url��
		JPanel panel = new JPanel();
		// Dimension size = new Dimension(500, 100);
		// panel.setPreferredSize(size);
		panel.setSize(500, 100);
		panel.setLocation(150, 250);
		panel.setLayout(new GridLayout(2, 2, 5, 5));

		String[] info = new String[] { "App��", "����", "�û���Ϣ", "URL��д" };
		for (int i = 0; i < info.length; i++) {
			JPanel temPanel = new JPanel();
			temPanel.setLayout(new BorderLayout());
			Label label = new Label(info[i]);
			JTextField text = new JTextField(25);
			temPanel.add(label, BorderLayout.NORTH);
			temPanel.add(text, BorderLayout.CENTER);
			panel.add(temPanel);
		}
		add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
