package Modelo;

import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class Eventos {

	public void SoloCaracteres(KeyEvent evt) {
		char car = evt.getKeyChar();
		if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_BACK_SPACE)
				&& (car != (char) KeyEvent.VK_SPACE)) {
			evt.consume();
		}
	}

	public void SoloNumeros(KeyEvent evt) {
		
		char car = evt.getKeyChar();
		if ((car < '0' || car > '9') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			evt.consume();
		}
	}

	public void SoloNumerosDecimal(KeyEvent evt, JTextField textField) {
		
		char car = evt.getKeyChar();
		if ((car < '0' || car > '9') && textField.getText().contains(".") && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			evt.consume();
		} else if ((car < '0' || car > '9') && (car != '.') && (car != (char) KeyEvent.VK_BACK_SPACE)) {
			evt.consume();
		}
	}

}
