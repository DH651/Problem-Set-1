package twitter;

import java.util.EnumSet;
import java.util.Set;

public enum validCharactersInUsername {
	//Uppercase Letters
	   A("A"), B("B"), C("C"), D("D"), E("E"), F("F"), G("G"), H("H"), I("I"), J("J"), K("K"), L("L"), M("M"), N("N"), 
	   O("O"), P("P"), Q("Q"), R("R"), S("S"), T("T"), U("U"), V("V"), W("W"), X("X"), Y("Y"), Z("Z"),
	
	//Lowercase Letters
	   a("a"), b("b"), c("c"), d("d"), e("e"), f("f"), g("g"), h("h"), i("i"), j("j"), k("k"), l("l"), m("m"), n("n"), 
	   o("o"), p("p"), q("q"), r("r"), s("s"), t("t"), u("u"), v("v"), w("w"), x("x"), y("y"), z("z"),
	
	//Digits
	ZERO("0"),ONE("1"),SECOND("2"),THIRD("3"),FOUR("4"),FIFTH("5"),SIX("6"),SEVEN("7"),EIGTH("8"),NINE("9"),
	
	//underscore,dash
	UNDERSCORE("_"),DASH("-");
	
	private String character;
	
	validCharactersInUsername(String character){
		this.character = character;
	}

}
