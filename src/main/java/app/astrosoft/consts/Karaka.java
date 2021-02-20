/**
 * Karakas.java
 * Created On 2006, Jan 21, 2006 5:40:11 PM
 * @author E. Rajasekar
 */

package app.astrosoft.consts;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import app.astrosoft.util.Internalization;
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public enum Karaka {

	AtmaKaraka,
	AmatyaKaraka,
	BhratruKaraka,
	MatruKaraka,
	PuthraKaraka,
	GnatiKaraka,
	DaraKaraka,
	NoKaraka;
	
	private static Karaka vals[] = values();

	public static Karaka ofIndex(int index) {
		return vals[index % vals.length];
	}
	
	public String toString() {

		return Internalization.getString(this.name());
	}

}
