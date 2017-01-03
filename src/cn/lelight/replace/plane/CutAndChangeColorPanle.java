package cn.lelight.replace.plane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import cn.lelight.replace.utils.FileBean;
import cn.lelight.replace.utils.ImageUtils;
import cn.lelight.replace.utils.RePlaceUtils;

/**
 * Copyright 2016 Lelight
 *
 * All right reserved.
 *
 * @author itlowly
 * @version 创建时间：2016年12月31日 下午3:59:00 类说明
 */
public class CutAndChangeColorPanle extends JPanel implements AdjustmentListener, ActionListener {
	private int width, height;
	private ArrayList<ColorItem> colorList;
	private int mCurrenColor = 1;
	private JButton colorOne;
	private JButton colorTwo;
	private boolean notUpdate;

	private Color one, two;
	private JButton srcFileButton;
	private JTextField inputSrcFile;
	private JButton startBtn;

	public CutAndChangeColorPanle(int width, int height) throws HeadlessException {
		super();
		this.width = width;
		this.height = height;
		colorList = new ArrayList<>();
		colorList.add(new ColorItem());
		colorList.add(new ColorItem());
		colorList.add(new ColorItem());

		one = new Color(0, 0, 0);
		two = new Color(0, 0, 0);

		setLayout(null);

		initTwoColorPanle();

		initColorArea();

		initScrDrcFilePathPane();

		initBottomBtn();
	}

	private void initBottomBtn() {
		startBtn = new JButton("开  始  导  出");
		startBtn.setSize(600, 30);
		startBtn.setLocation(100, 500);
		startBtn.addActionListener(this);
		add(startBtn);
	}

	private void initTwoColorPanle() {
		int colorWidth = 200;
		JPanel panel = new JPanel();
		panel.setSize(800, 240);
		panel.setLocation(0, 0);
		panel.setLayout(null);
		panel.setBackground(new Color(220, 240, 220));

		colorOne = new JButton("源颜色");
		colorOne.setBackground(Color.BLACK);
		colorOne.setForeground(Color.white);
		colorOne.setSize(colorWidth, colorWidth);
		colorOne.setLocation((400 - colorWidth) / 2, (240 - colorWidth) / 2);
		colorOne.addActionListener(this);
		panel.add(colorOne);

		colorTwo = new JButton("目标颜色");
		colorTwo.setBackground(Color.BLACK);
		colorTwo.setForeground(Color.white);
		colorTwo.setSize(colorWidth, colorWidth);
		colorTwo.setLocation(colorOne.getX() + 400, colorOne.getY());
		colorTwo.addActionListener(this);
		panel.add(colorTwo);
		add(panel);
	}

	private void initScrDrcFilePathPane() {
		int lY = colorList.get(colorList.size() - 1).scrollBar.getY() + 50;
		// 选择源文件夹
		JPanel panel = new JPanel();
		panel.setSize(800, 40);
		panel.setLocation(0, lY);
		panel.setBackground(new Color(208, 208, 208));
		JLabel srcText = new JLabel("图片源文件夹:");
		srcText.setPreferredSize(new Dimension(100, 20));
		panel.add(srcText);
		inputSrcFile = new JTextField(40);
		inputSrcFile.setToolTipText("输入源文件夹");
		srcFileButton = new JButton("选择文件夹");
		srcFileButton.addActionListener(this);
		panel.add(inputSrcFile);
		panel.add(srcFileButton);
		add(panel);
	}

	private void initColorArea() {
		for (int i = 0; i < colorList.size(); i++) {
			ColorItem colorItme = colorList.get(i);
			colorItme.scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 255);
			colorItme.scrollBar.setSize(800 - 32 - 32 - 120 - 20, 30);
			if (i == 0) {
				colorItme.scrollBar.setLocation(32, 260);
			} else {
				colorItme.scrollBar.setLocation(colorList.get(i - 1).scrollBar.getX(),
						colorList.get(i - 1).scrollBar.getY() + 60);
			}
			colorItme.scrollBar.addAdjustmentListener(this);
			add(colorItme.scrollBar);
			// ------------------------------------------
			colorItme.editText = new JTextField();
			colorItme.editText.setSize(60, 30);
			colorItme.editText.setHorizontalAlignment(SwingConstants.CENTER);
			colorItme.editText.setLocation(colorItme.scrollBar.getX() + colorItme.scrollBar.getWidth() + 10,
					colorItme.scrollBar.getY());
			colorItme.editText.addCaretListener(new TextFieldInputListener());
			add(colorItme.editText);
			// ------------------------------------------
			colorItme.edit0XText = new JTextField();
			colorItme.edit0XText.setSize(60, 30);
			colorItme.edit0XText.setHorizontalAlignment(SwingConstants.CENTER);
			colorItme.edit0XText.setLocation(colorItme.editText.getX() + colorItme.editText.getWidth() + 10,
					colorItme.scrollBar.getY());
			colorItme.edit0XText.addCaretListener(new TextFieldInput0XListener());
			add(colorItme.edit0XText);
		}
	}

	/**
	 * 滑动条监听
	 */
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		for (ColorItem item : colorList) {
			if ((JScrollBar) e.getSource() == item.scrollBar) {
				item.value = e.getValue();
				notUpdate = true;
				item.editText.setText("" + e.getValue());
				item.edit0XText.setText(Integer.toHexString(e.getValue()));
				setRGBColor();
			}
		}
	}

	private void setRGBColor() {
		if (mCurrenColor == 1) {
			one = new Color(colorList.get(0).value, colorList.get(1).value, colorList.get(2).value);
			colorOne.setBackground(one);
		} else {
			two = new Color(colorList.get(0).value, colorList.get(1).value, colorList.get(2).value);
			colorTwo.setBackground(two);
		}
	}

	class ColorItem {
		private int value;
		JTextField editText;
		JTextField edit0XText;
		JScrollBar scrollBar;

		public void setValue(int value) {
			scrollBar.setValue(value);
		}
	}

	class TextFieldInputListener implements CaretListener {

		@Override
		public void caretUpdate(CaretEvent e) {
			JTextField textField = (JTextField) e.getSource(); // 获得触发事件的
			String text = textField.getText();
			if (text.length() == 0) {
				return;
			}
			char ch = text.charAt(text.length() - 1);
			if (!(ch >= '0' && ch <= '9')) {
				// JOptionPane.showMessageDialog(textField, "只能输入数字", "提示",
				// JOptionPane.INFORMATION_MESSAGE);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						// 去掉 JTextField 中的末尾字符
						textField.setText(text.substring(0, text.length() - 1));
					}
				});
			} else {
				if (Integer.valueOf(text) > 255) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							// 去掉 JTextField 中的末尾字符
							textField.setText(text.substring(0, text.length() - 1));
						}
					});
				} else {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							for (ColorItem coloritem : colorList) {
								if (coloritem.editText == (JTextField) e.getSource()) {
									coloritem.setValue(Integer.valueOf(text));
									if (!notUpdate) {
										setRGBColor();
									} else {
										notUpdate = false;
									}
								}
							}
						}
					});
				}
			}
		}
	}

	class TextFieldInput0XListener implements CaretListener {
		@Override
		public void caretUpdate(CaretEvent e) {
			JTextField textField = (JTextField) e.getSource(); // 获得触发事件的
			String text = textField.getText();
			if (text.length() == 0) {
				return;
			}
			char ch = text.charAt(text.length() - 1);
			if (!(ch >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f')) {
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						textField.setText(text.substring(0, text.length() - 1));
					}
				});
			} else {
				if (text.length() > 2) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							textField.setText(text.substring(0, text.length() - 1));
						}
					});
				}
				if (Integer.valueOf(text, 16) > 255) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							textField.setText(text.substring(0, text.length() - 1));
						}
					});
				} else {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							for (ColorItem coloritem : colorList) {
								if (coloritem.edit0XText == (JTextField) e.getSource()) {
									coloritem.setValue(Integer.valueOf(text, 16));
									if (!notUpdate) {
										setRGBColor();
									} else {
										notUpdate = false;
									}
								}
							}
						}
					});
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == colorOne) {
			mCurrenColor = 1;
			// setRGBColor();
		} else if (arg0.getSource() == colorTwo) {
			mCurrenColor = 2;
			// setRGBColor();
		} else if (arg0.getSource() == srcFileButton) {
			showFileChooserAndSetTextFild(inputSrcFile);
		} else if (arg0.getSource() == startBtn) {
			try {
				System.out
						.println("开始导出:" + inputSrcFile.getText() + "\n" + one.toString() + "  to  " + two.toString());
				File file = new File(inputSrcFile.getText());
				if (file.exists()) {
					if (file.isDirectory()) {
						List<FileBean> filesNameAndPath = RePlaceUtils.getFilesNameAndPath(file.getAbsolutePath());
						for (FileBean bean : filesNameAndPath) {
							ImageUtils.replaceImageColor(bean.path, one, two);
						}
					} else {
						ImageUtils.replaceImageColor(inputSrcFile.getText(), one, two);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showFileChooserAndSetTextFild(JTextField textField) {
		String srcFileDir = inputSrcFile.getText().toString().trim();
		JFileChooser jFileChooser;
		if (!"".equals(srcFileDir)) {
			// 源文件夹不为空
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
		jFileChooser.showDialog(new JLabel(), "选择");
		File file = jFileChooser.getSelectedFile();
		if (file.isDirectory()) {
			textField.setText(file.getAbsolutePath());
		} else if (file.isFile()) {
			textField.setText(file.getAbsolutePath());
		}
	}
}
