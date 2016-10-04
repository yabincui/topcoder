import java.util.*;

public class Spamatronic {
	public int[] filter(String[] knownSpam, String[] newMail) {
		Set<String> spam_set = new HashSet<String>();
		for (String s : knownSpam) {
			Set<String> words = getWords(s);
			for (String word : words) {
				spam_set.add(word);
			}
		}
		ArrayList<Integer> okString = new ArrayList<Integer>();
		for (int i = 0; i < newMail.length; ++i) {
			String s = newMail[i];
			Set<String> words = getWords(s);
			int inCount = 0;
			for (String word : words) {
				if (spam_set.contains(word)) {
					inCount++;
				}
			}
			double percent = 0;
			if (words.size() > 0) {
				percent = (double)inCount / words.size();
			}
			if (percent >= 0.75 - 1e-6) {
				for (String word : words) {
					spam_set.add(word);
				}
			} else {
				okString.add(i);
			}
		}
		int[] result = new int[okString.size()];
		for (int i = 0; i < result.length; ++i) {
			result[i] = okString.get(i);
		}
		return result;
	}
	
	Set<String> getWords(String s) {
		s = s.toLowerCase();
		Set<String> result = new TreeSet<String>();
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				b.append(c);
			} else {
				if (b.length() > 0) {
					result.add(b.toString());
					b = new StringBuilder();
				}
			}
		}
		if (b.length() > 0) {
			result.add(b.toString());
		}
		return result;
	}

  public static void main(String[] args) {
    String[] knownSpams = 
{"zixJ dpIK nckL InuQ xDiM foJh Rntr DTQw jWcy vSTm ", "lyAF xgov qKlp pTCc DNsl ruSZ Vouo Unpj EXrA lxMO ", "JNjf lqGv oaDM Rxwu SrMK TcfR iJFX KhPq Hvsq XoVr ", "TXtc WjJs whkx ggEv iSSn NuGe yFYd XWTE wsyp tNZL ", "dWUR fMYm EOrA PJfr smDd NIpF Xoym FzcS jgTF ULmy ", "dSTu rdMJ cjuB NNxP psNj wcPz wdhO oKNa LahU lYcn ", "iobz AOFy wIfO hZgv fVqX pVaT seDa dSPm GYMI Mrkl ", "GzFF gtpE ghWy RPOE SFEF HFBG EMYP fPBU lOBM lNsW ", "cJtE rBKi HPQt zqea sYjm QTSR vtNM BhfN xwIQ zXQF ", "aijy OcXP WvJC beLG fPgV CUZU MbZQ tgqw fMmf qJWA ", "Lyai wdqP JoiT OPiM KaiE pNXs GVHh ZyNS mAiX rIEm ", "FqnJ EamI WwmB jaLk HuIr yKiR fGBE UmWY MXfn TYdV ", "oupP PSiS TbUe hebU hwIm AMqM UPeH heML gEXq mxAj ", "oEWe Sajw aZqR RySY nEdj xjdp uVIN uNtG IYgm BFih ", "auOt BPtx sYuz sQqw GGgp RUOd GLmb GcZO vTad LIXK ", "Cbfc oRiH oeUx SKRF erWo WLSQ mHBl Lkkc rWzm DhRw ", "dWqy RZgF MGVK TZSO MrPX lQtn mxFp HVbM YuET mqmO ", "ssUV bGiV MzVw NEkK cbTc OdwG kdtO tILj qmNR kgxZ ", "hOhf jDLe fZpb zQAM rwFE rFDZ OMCl kBft ZUAi gGUW ", "sTJQ GrAm gDQc GCqo tCyB eBIm TPDS GQhT Yskx DdGq ", "zixJ dpIK nckL InuQ xDiM foJh Rntr DTQw jWcy vSTm ", "lyAF xgov qKlp pTCc DNsl ruSZ Vouo Unpj EXrA lxMO ", "JNjf lqGv oaDM Rxwu SrMK TcfR iJFX KhPq Hvsq XoVr ", "TXtc WjJs whkx ggEv iSSn NuGe yFYd XWTE wsyp tNZL ", "dWUR fMYm EOrA PJfr smDd NIpF Xoym FzcS jgTF ULmy ", "dSTu rdMJ cjuB NNxP psNj wcPz wdhO oKNa LahU lYcn ", "iobz AOFy wIfO hZgv fVqX pVaT seDa dSPm GYMI Mrkl ", "GzFF gtpE ghWy RPOE SFEF HFBG EMYP fPBU lOBM lNsW ", "cJtE rBKi HPQt zqea sYjm QTSR vtNM BhfN xwIQ zXQF ", "aijy OcXP WvJC beLG fPgV CUZU MbZQ tgqw fMmf qJWA ", "Lyai wdqP JoiT OPiM KaiE pNXs GVHh ZyNS mAiX rIEm ", "FqnJ EamI WwmB jaLk HuIr yKiR fGBE UmWY MXfn TYdV ", "oupP PSiS TbUe hebU hwIm AMqM UPeH heML gEXq mxAj ", "oEWe Sajw aZqR RySY nEdj xjdp uVIN uNtG IYgm BFih ", "auOt BPtx sYuz sQqw GGgp RUOd GLmb GcZO vTad LIXK ", "Cbfc oRiH oeUx SKRF erWo WLSQ mHBl Lkkc rWzm DhRw ", "dWqy RZgF MGVK TZSO MrPX lQtn mxFp HVbM YuET mqmO ", "ssUV bGiV MzVw NEkK cbTc OdwG kdtO tILj qmNR kgxZ ", "hOhf jDLe fZpb zQAM rwFE rFDZ OMCl kBft ZUAi gGUW ", "sTJQ GrAm gDQc GCqo tCyB eBIm TPDS GQhT Yskx DdGq ", "zixJ dpIK nckL InuQ xDiM foJh Rntr DTQw jWcy vSTm ", "lyAF xgov qKlp pTCc DNsl ruSZ Vouo Unpj EXrA lxMO ", "JNjf lqGv oaDM Rxwu SrMK TcfR iJFX KhPq Hvsq XoVr ", "TXtc WjJs whkx ggEv iSSn NuGe yFYd XWTE wsyp tNZL ", "dWUR fMYm EOrA PJfr smDd NIpF Xoym FzcS jgTF ULmy ", "dSTu rdMJ cjuB NNxP psNj wcPz wdhO oKNa LahU lYcn ", "iobz AOFy wIfO hZgv fVqX pVaT seDa dSPm GYMI Mrkl ", "GzFF gtpE ghWy RPOE SFEF HFBG EMYP fPBU lOBM lNsW ", "cJtE rBKi HPQt zqea sYjm QTSR vtNM BhfN xwIQ zXQF ", "aijy OcXP WvJC beLG fPgV CUZU MbZQ tgqw fMmf qJWA "};
  
    String[] newMails = {"TIRn PZIV TPDS GYMI GLmb vQto OdwG MzVw sQqw hwIm ", // 0
      "hhLX whkx tRXp fGBE zogg wdhO iOIq OdwG Iisk GJXN ", //1
      "RZgF DTQw Rxwu NuGe MzVw UmWY qKWl WHfH KaiE FqnJ ", //2 a
      "psNj pVaT wdhO WLSQ XoVr PuEI auOt aijy GGgp zqea ",//3a
      "wdqP RPOE PJfr OcXP fPBU tAfF lqGv JNjf rwFE NuGe ",//4a
      "oKNa vtNM AMqM gGUW hLcY SrMK AGOT lqGv GYMI rdMJ ", //5a
      "bGiV GLmb TXtc HXon TIRn sYjm YuET pTCc jsHA KJbH ", //6
      "GLmb ELVH hebU fMmf OPiM GcZO rIEm NIpF uobt kgxZ ", //7a
      "AMqM beLG oeUx Lyai qJWA fPBU FzcS dWUR dERc IoUm ", //8a
      "gGUW uWxb pNXs Anjs LAuk IgpL ncls CEob xwIQ qJWA ", //9
      "ssUV uUhb Unpj SKRF qmNR UHeb KaiE wIfO CUZU DhRw ", // 10a
      "jDLe XoVr KVSQ MXgd LotM uRZg cPaZ NIpF wdqP heML ", // 11
      "JoiT KdgW ULmy OcXP yFYd mhHF oEWe mxFp WjJs GGgp ", // 12a
      "fPgV pNXs mhHF REtY dWUR pNXs pkRi hhLX cwTS tNMf ", //13
      "SFEF BPtx fMYm MXfn iSSn hLcY Vouo fPgV NEkK Yskx ", //14a
      "wIfO REtY RUOd foJh UMeV GYMI qKlp hhLX wdhO SrMK ", //15
      "ImBn oRiH QTSR tCyB ImBn tILj jaLk Hvsq smDd cwTS ", //16
      "mHBl IgpL AtdX FJPG lqGv cjuB kBft AGOT lQtn fGBE ", 
      "WwmB eDVX auOt iobz kjiX vGnN mxAj gGUW qKWl oaDM ",
      "dpIK hwIm zVvF lQtn wIfO dWqy JcGA fffv nmSZ IoUm ", 
      "sQqw NuGe RUOd sYuz WwmB IoUm dWUR dSPm jEcw seDa ", 
      "eBIm aijy ZBpw gDQc WLSQ xgov ekZG lqGv UPeH rwFE ",
      "oaDM PZIV cjuB DdGq Aehz mHBl Gxrz lyAF PZIV sYuz ", 
      "GLmb LIXK sYjm cjuB foJh NIpF IYgm IsXc KhPq kjiX ", 
      "cOSp rWzm pNXs uNtG qJWA hZgv cbTc OPiM Mvqs xDiM ", "ZUAi IsXc foJh qKlp ULmy fPBU aijy xwIQ QTSR nsjU ", "DGHS BniQ NIpF pVaT AClY xqxr xwIQ rIEm VBdj bkiA ", "tNZL oKNa qKWl gBqr sgHZ yOGN KJbH HVbM vhWp UPeH ", "Hvsq QTSR hwIm RPOE mxFp fffv oeUx HuIr dWqy EamI ", "XoVr CUZU YuET Lkkc DhRw aCgS oRiH lOBM nsjU dSqG ", "SKRF TZSO URym erWo gngf hLcY iONk bkiA rIEm rwFE ", "FzcS jYAW oaDM oRiH rPBb OdwG eBIm Mgjw sTJQ hhLX ", "whkx vhWp jyHe tEhO nEdj Gxrz xxpV Tpnj CEob oEWe ", "wIfO fZpb oupP pTCc fPBU IYgm UPeH JoiT iobz OMCl ", "fVqX qmNR QTSR qJWA RUHr EOrA tNZL rFDZ GaQr oKNa ", "kBft heML HFBG uUhb gGUW pTCc lUyI kgxZ cVdI SrMK ", "NuGe OPiM eDVX dERc RUHr qKWl MXgd kXqo Wjse BFih ", "NuGe zOrD aQdw ZoAm beLG PXRL SrMK GrAm WjJs RkfJ ", "GJXN oaDM QTSR cVdI uobt OPiM OdwG pVaT jgTF Lkkc ", "dWUR dWqy EMYP OMCl lQtn CUZU mAiX lxMO RPOE vTad ", "LahU JImO rIEm beLG sYuz QTSR TWLA MGVK TXtc MrPX ", "TYdV mAiX PJfr wsyp Rntr lxMO beLG uFEn RSvV ULmy ", "EBJf IYgm lCgO TZSO xgLy whkx Wjse ZXld nigB OdwG ", "DhRw XWTE wsyp WjJs lqGv Lkkc zixJ Hvsq oKNa hCuc ", "kXqo XUea gIaC Mgjw UIDx iONk ZXld FqnJ ETrG PvyH ", "vhWp fVqX GYMI gDQc oRiH yOGN MzVw cbTc kgxZ HPQt ", "MbZQ RUOd tgqw XWTE GzFF jCRp JNjf sTJQ AMqM tNZL ", "bGiV LUCg GVHh ULmy HWuD hCuc ZBpw gtpE iobz TPDS ", "lqGv Lyai MXfn nEdj wsyp PuEI sTJQ zXQF sYuz yFYd ", "gBqr jCRp OcXP gtpE JImO GLmb OPiM YohS uobt ssUV "};
      
    Spamatronic a = new Spamatronic();
    int[] result = a.filter(knownSpams, newMails);
    System.out.printf("result = ");
    for (int i = 0; i < result.length; ++i) {
      System.out.printf("%d ", result[i]);
    }
    System.out.printf("\n");
  }
}
