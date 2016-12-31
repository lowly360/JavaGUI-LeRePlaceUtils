package cn.lelight.replace.plane;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.lelight.replace.bean.RePlaceItem;
import cn.lelight.replace.utils.RePlaceUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version ����ʱ�䣺2016��12��30�� ����3:18:29 ��˵��
 */
public class ImagePlane extends JPanel implements ActionListener {
	private java.util.List<RePlaceItem> rePlaceItems;
	// private Container containPane;
	private JTextField inputSrcFile;
	private JButton srcFileButton;
	private JTextField inputDrcFile;
	private JButton drcFileButton;
	private JButton startButton;
	private JLabel hintLable;

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// showJFrame obj = new showJFrame();
	// obj.testJFrame();
	// }

	public ImagePlane(int width, int height) {
		setSize(width, height);

		rePlaceItems = new ArrayList<>();
		rePlaceItems.add(new RePlaceItem("App Logo", "ic_launcher.png"));
		rePlaceItems.add(new RePlaceItem("����ҳ", "ic_splash_bg.png"));
		rePlaceItems.add(new RePlaceItem("����ҳ1", "ic_1.png"));
		rePlaceItems.add(new RePlaceItem("����ҳ2", "ic_2.png"));
		rePlaceItems.add(new RePlaceItem("����ҳ3", "ic_3.png"));
		rePlaceItems.add(new RePlaceItem("��¼ҳ logo", "ic_icon.png"));
		rePlaceItems.add(new RePlaceItem("�豸,�ֲ�ͼ", "ic_devcies_header.png"));

		// JFrame frame = new subJFrame("App 3.0+ �汾�滻");
		// containPane = frame.getContentPane();
		setLayout(null);
		initScrDrcFilePathPane();
		initSelectImgPane();
		initBottomButton();

		setVisible(true);
	}

	private void initScrDrcFilePathPane() {
		JPanel panel = new JPanel();
		panel.setSize(800, 40);
		panel.setLocation(0, 0);
		panel.setBackground(new Color(208,208,208));
		JLabel srcText = new JLabel("ͼƬԴ�ļ���:");
		srcText.setPreferredSize(new Dimension(100, 20));
		panel.add(srcText);
		inputSrcFile = new JTextField(40);
		inputSrcFile.setToolTipText("����Դ�ļ���");
		srcFileButton = new JButton("ѡ���ļ���");
		srcFileButton.addActionListener(this);
		panel.add(inputSrcFile);
		panel.add(srcFileButton);
		add(panel);

		JPanel panel2 = new JPanel();
		panel2.setSize(800, 40);
		panel2.setLocation(0, 40);
		panel2.setBackground(new Color(208,208,208));
		JLabel drcText = new JLabel("ͼƬĿ���ļ���:");
		drcText.setPreferredSize(new Dimension(100, 20));
		panel2.add(drcText);
		inputDrcFile = new JTextField(40);
		drcFileButton = new JButton("ѡ���ļ���");
		drcFileButton.addActionListener(this);
		panel2.add(inputDrcFile);
		panel2.add(drcFileButton);
		add(panel2);
	}

	private void initSelectImgPane() {
		int ds = 80;
		for (int i = 0; i < rePlaceItems.size(); i++) {
			ds = 80 + i * 54;
			add(initFileSelectPanel(rePlaceItems.get(i), ds));
		}
	}

	private JPanel initFileSelectPanel(RePlaceItem rePlaceItem, int ds) {
		JPanel fileSelectPanel = new JPanel();
		fileSelectPanel.setSize(800, 54);
		fileSelectPanel.setLocation(0, ds);
		fileSelectPanel.setBackground(Color.white);
		// ---------------------------------
		JLabel srcText = new JLabel(rePlaceItem.name);
		srcText.setPreferredSize(new Dimension(100, 20));
		// ---------------------------------
		rePlaceItem.actionText = new JTextField(40);
		rePlaceItem.actionButton = new JButton("ѡ �� ͼ Ƭ");
		rePlaceItem.actionButton.addActionListener(this);
		// ---------------------------------
		fileSelectPanel.add(srcText);
		fileSelectPanel.add(rePlaceItem.actionText);
		fileSelectPanel.add(rePlaceItem.actionButton);
		return fileSelectPanel;
	}

	private void showFileChooserAndSetTextFild(JTextField textField) {
		String srcFileDir = inputSrcFile.getText().toString().trim();
		JFileChooser jFileChooser;
		if (!"".equals(srcFileDir)) {
			// Դ�ļ��в�Ϊ��
			File file = new File(srcFileDir);
			if (file.exists() && file.isDirectory()) {
				jFileChooser = new JFileChooser(srcFileDir);
			} else {
				jFileChooser = new JFileChooser();
			}
		} else {
			jFileChooser = new JFileChooser();
		}

		jFileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		jFileChooser.showDialog(new JLabel(), "ѡ��");
		File file = jFileChooser.getSelectedFile();
		if (file.isDirectory()) {
			textField.setText(file.getAbsolutePath());
		} else if (file.isFile()) {
			textField.setText(file.getAbsolutePath());
		}
	}

	private void initBottomButton() {

		JPanel panel2 = new JPanel();
		panel2.setSize(800, 40);
		panel2.setLocation(0, 80 + 54 * 7);
		panel2.setBackground(new Color(208,208,208));

		hintLable = new JLabel("��ʾ��Ϣ�ڴ���ʾ");
		hintLable.setPreferredSize(new Dimension(800, 40));
		hintLable.setBackground(Color.gray);
		hintLable.setForeground(Color.red);
		hintLable.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(hintLable);
		add(panel2);

		JPanel panel = new JPanel();
		panel.setSize(800, 40);
		panel.setLocation(0, 80 + 54 * 7 + 40);

		startButton = new JButton("��  ʼ  ��  ��");
		startButton.setPreferredSize(new Dimension(600, 30));
		startButton.addActionListener(this);
		panel.add(startButton);
		add(panel);
	}

	/**
	 * �����¼�
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == srcFileButton) {
			showFileChooserAndSetTextFild(inputSrcFile);
		} else if (e.getSource() == drcFileButton) {
			showFileChooserAndSetTextFild(inputDrcFile);
		} else if (e.getSource() == startButton) {
			// ��ʼ�滻
			// ����Ƿ�ȫ����Ϣ��д���
			String srcFilePath = inputSrcFile.getText().trim();
			String drcFilePath = inputDrcFile.getText().trim();

			if (srcFilePath.equals("")) {
				hintLable.setText("Դ�ļ���·������Ϊ��");
				return;
			}
			if (drcFilePath.equals("")) {
				hintLable.setText("Ŀ���ļ���·������Ϊ��");
				return;
			}

			// for (int i = 0; i < rePlaceItems.size(); i++) {
			// if (rePlaceItems.get(i).actionText.getText().trim().equals("")) {
			// hintLable.setText(rePlaceItems.get(i).name + "·������Ϊ��");
			// return;
			// }
			// }

			String filePath = rePlaceItems.get(0).actionText.getText();
			RePlaceUtils.reNameFile(filePath, rePlaceItems.get(0).fileName);

			// ��ʼ��������Դ
			// for (int i = 0; i < rePlaceItems.size(); i++) {
			// String filePath = rePlaceItems.get(i).actionText.getText();
			// RePlaceUtils.reNameFile(filePath, rePlaceItems.get(i).fileName);
			// }
			// ��ʼ�滻�ļ�

			RePlaceUtils.startRePlace(srcFilePath, drcFilePath);

			// ���?
			hintLable.setText(
					"                                                                                                                                      �滻���                                                                                                                                                                                                         ");

		} else {
			for (int i = 0; i < rePlaceItems.size(); i++) {
				if (e.getSource() == rePlaceItems.get(i).actionButton) {
					System.out.println("�����˵�" + i + "��");
					showFileChooserAndSetTextFild(rePlaceItems.get(i).actionText);
				}
			}
		}
	}

}
