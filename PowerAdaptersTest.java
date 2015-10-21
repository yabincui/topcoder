import org.junit.*;
import static org.junit.Assert.*;

public class PowerAdaptersTest {
	@Test
	public void test() {
		PowerAdapters adapter = new PowerAdapters();
		/*
		assertEquals(0, adapter.needed(
				new String[]{"USA EUROPE","EUROPE UK"},
				new String[]{"UK","EUROPE"},
				"USA"
				));
		assertEquals(1, adapter.needed(
				new String[]{"USA CANADA","USA UK","GERMANY AUSTRALIA","GERMANY CANADA","AUSTRALIA USA","UK CANADA","JAPAN USA","JAPAN USA"},
				new String[]{"AUSTRALIA","CANADA"},
				"UK"
				));
		assertEquals(1, adapter.needed(
				new String[]{"INDIA EGYPT","USA GERMANY","CHINA SPAIN","GERMANY NETHERLANDS","NETHERLANDS CHINA"},
				new String[]{"CHINA","GERMANY","SPAIN"},
				"NETHERLANDS"
				));
		assertEquals(1, adapter.needed(
				new String[]{"AUSTRALIA GERMANY","CANADA INDIA","AUSTRALIA USA","USA INDIA","USA AUSTRALIA","CANADA GERMANY","USA AUSTRALIA","USA CANADA"},
				new String[]{"AUSTRALIA","CANADA"},
				"CANADA"
				));
		assertEquals(1, adapter.needed(
				new String[]{"SPAIN AUSTRALIA","SPAIN NETHERLANDS","AUSTRALIA EGYPT"},
				new String[]{"AUSTRALIA","EGYPT","NETHERLANDS"},
				"UK"
				));
		assertEquals(2, adapter.needed(
				new String[]{"CMCUG MEIACWT","CMCUG QLLUJCMB","MEIACWT UKINFV"
				,"ODK QLLUJCMB","MEIACWT SXGBGF","CWW TUQ","YUAYI MEIACWT"
				,"MEIACWT ODK","QLLUJCMB AGNAE","AGNAE GACPM","QLLUJCMB MAO"
				,"KNCUTEW NNA","ODK MEIACWT","QJQUY ODK","AGNAE MEIACWT"},
				new String[]{"AGNAE","ODK","TUQ","YUAYI"},
				"AGNAE"
				));
		assertEquals(3, adapter.needed(
				new String[]{"LALJ DMZEQ","ANKNMMUQ YINE","MAYNYVOM KQWCGASA"
					,"YWEU DMZEQ","MAO YAE","CWNFS IAWGRCX","KQWCGASA CNUL"
					,"CWNFS DMZEQ","QBHQCU EPMAKOI","CNUL KQWCGASA"
					,"ANKNMMUQ YOXOQVO","YAE MAYNYVOM","IAWGRCX DMZEQ"},
					new String[]{"CWNFS","DMZEQ","ISO","YOKKMK"},
					"YINE"
				));
		*/
		assertEquals(2, adapter.needed(new String[]
				{"A B", "B C", "C D", "B F", "A D", "D G", "I G", "G X", "X R", "A R", "A V", "V R", "R Z", "AA BB", "CC DD", "EE FF", "GG HH", "II JJ", "KK LL", "MM NN", "OO PP", "QQ RR", "SS TT", "UU VV", "WW XX", "YY ZZ",
						"AAA BBB", "CCC DDD", "EEE FFF", "GGG HHH", "III JJJ", "KKK LLL", "MMM NNN", "OOO PPP", "QQQ RRR", "SSS TTT", "UUU VVV", "WWW XXX", "YYY ZZZ", "AAAA BBBB", "CCCC DDDD", "EEEE FFFF", "GGGG HHHH", "IIII JJJJ", "KKKK LLLL", "MMMM NNNN", "OOOO PPPP", "QQQQ RRRR", "SSSS TTTT", "UUUU VVVV"}, 
				new String[]{"A", "R", "G", "B", "C", "Z", "BBB", "FFF"}, "AAA"));
	}
}