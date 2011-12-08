package binpacking.mvc.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AboutDialog dialog = new AboutDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AboutDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(12, 122, 45, 40);
			lblNewLabel.setIcon(new ImageIcon(AboutDialog.class.getResource("/binpacking/mvc/view/resources/39px-Tetromino_O.svg.png")));
			contentPanel.add(lblNewLabel);
		}
		{
			JTextArea txtrPlaceHolder = new JTextArea();
			txtrPlaceHolder.setBounds(75, 12, 355, 210);
			txtrPlaceHolder.setText("Tetromino Bin Packer\nby Robert Jackson\nroboo.jack@gmail.com\n\nThis software is released under the Apache License 2.0");
			contentPanel.add(txtrPlaceHolder);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(AboutDialog.class.getResource("/binpacking/mvc/view/resources/53px-Tetromino_Z.svg.png")));
			label.setBounds(12, 176, 45, 40);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(AboutDialog.class.getResource("/binpacking/mvc/view/resources/54px-Tetromino_T.svg.png")));
			label.setBounds(12, 68, 45, 40);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon(AboutDialog.class.getResource("/binpacking/mvc/view/resources/56px-Tetromino_S.svg.png")));
			label.setBounds(12, 14, 45, 40);
			contentPanel.add(label);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
