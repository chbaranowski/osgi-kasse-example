package basar.domain;

import java.io.Serializable;


public final class PositionKey implements Serializable {

	private String kasse;
	
	private int number;
	
	public PositionKey() {}
	
	public PositionKey(String kasse, int number) {
		this.kasse = kasse;
		this.number = number;
	}

	public String getKasse() {
		return kasse;
	}

	public void setKasse(String kasse) {
		this.kasse = kasse;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((kasse == null) ? 0 : kasse.hashCode());
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionKey other = (PositionKey) obj;
		if (kasse == null) {
			if (other.kasse != null)
				return false;
		} else if (!kasse.equals(other.kasse))
			return false;
		if (number != other.number)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return kasse + "-" + number;
	}
	
}
