package co.edu.uniquindio.programacionIII.alquilafacil.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Transmision {
	MANUAL("Manual"), AUTOMATICO("Automatico");

	@Getter
	private String text;

	public String[] textValues() {
		Transmision[] values = values();
		String[] textValues = new String[values.length];

		for (int i = 0; i < values.length; i++)
			textValues[i] = values[i].getText();

		return textValues;
	}

	public Transmision valueByText(String text) {
		Transmision[] values = values();
		for (Transmision transmision : values)
			if (transmision.getText().equals(text))
				return transmision;
		return null;
	}
}
