package cn.lelight.replace.bean;

import javax.swing.JButton;
import javax.swing.JTextField;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version ����ʱ�䣺2016��12��30�� ����4:07:00 ��˵��
 */
public class RePlaceItem {
	public String name;
	public String fileName;
	public String drspath;
	public JButton actionButton;
	public JTextField actionText;

	public RePlaceItem(String name, String fileName) {
		this.name = name;
		this.fileName = fileName;
	}
}
