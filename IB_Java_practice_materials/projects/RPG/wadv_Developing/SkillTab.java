public class SkillTab extends wadv
{
	wadv main = new wadv();
	private double ska;
	private double skb;
	private double skc;
	private double skd;
	private double ske;
	private double skf;
	private double skg;
	private double skh;
	SkillTab(double s1, double s2, double s3, double s4, double s5, double s6, double s7, double s8)
	{
		ska =s1;
		skb =s2;
		skc = s3;
		skd =s4;
		ske =s5;
		skf = s6;
		skg = s7;
		skh = s8;
	}
	void print()
	{
		if (main.callClassType==2)
		{
			System.out.println("");
		}
		System.out.println("		-----------------Combat Spells-----------------");
		System.out.println("1. Stupefy:	Commercial attacking cast. It can fatally harm opponent.");	//20
		System.out.println("						dmg:100% delay:1 mana:"+(20+(int)(lv/3)));
		System.out.println("2. Expeliarmus:	"+(int)ska+"/"+"5"+" Pierces any enemy defence. Deals additional damage.");
		System.out.println("						dmg:+"+(int)(2+ska*2)+" delay:"+(double)((int)((1.15-ska*0.03)*100))/100+" mana: "+(47+((int)ska*3)));	//50
		System.out.println("3. Expulso:	"+(int)skb+"/"+"10"+" Rapid fire cast.");
		System.out.println("						dmg:"+(int)(65+skb/2)+"% delay:"+(double)((int)((0.5-skb*0.015)*100))/100+" mana:"+(30+((int)skb*1)));	//80
		System.out.println("4. Impedimenta:	"+(int)ske+"/"+"10"+" Poisoning cast. Deals critical damage.");
		System.out.println("					  dur:"+(int)(4+ske/2.5)+" dmg:"+(int)(170+ske*6)+"% delay:"+(int)(1.5-ske*0.02)+" mana:"+(160+((int)ske*4)));	//130
		System.out.println("5. Pet..Totalus:"+(int)skf+"/10 Stunning cast. stuns opponent and slows him.");
		System.out.println("					   dur:"+(int)(0.7+skf*0.03)+" dmg:"+(int)(100+skf*3)+"% delay:1 mana:"+(180+((int)skf*5)));	//180
		System.out.println("6. Confringo:	"+(int)skg+"/10 Explosive cast. Requires casting time."); //200
		System.out.println("						dmg:"+(int)(250+skg*25)+"% time: 2 mana:"+(300+((int)skb*15)));
		System.out.println("7. Protego:	"+(int)skh+"/"+"10"+" Defensive Shield Cast. Mana absorbs some damage");	//70
		System.out.println("					   dur:"+(3)+" ref:"+(int)(10+skh*2)+"% delay:"+(0.5-skh*0.015)+" mana:"+(50+((int)skh*3)));
		System.out.println("8. Ennervate:	"+(int)skc+"/"+"10"+" Healing Cast.");	//150
		System.out.println("						heal:"+(int)(30+skg*2)+"% delay:1 mana:"+(150+((int)skb*5)));
		System.out.println("9. Fianto Duri:	"+(int)skd+"/"+"10"+" Heals Player's mana by "+(int)(8+skd*0.5)+"%.		mana:"+(220+((int)skd*5)));
		System.out.println("						");
		System.out.println("		-----------------Passive Enchantments-----------------");
		System.out.println("11. Critical blow:	"+(int)pskCritical+"/10 "+(int)(pskCritical*1.5+125)+"% spell damage at "+(int)pskCritical*3.5+"% chance.");
		System.out.println("12. Mana Restoration:	"+(int)pskManaRest+"/10 Restores "+(int)(pskManaRest*0.8+5)+"% mana each turn.");
		System.out.println("13. Dodge:		"+(int)pskDodge+"/10 "+(int)(pskDodge*2)+"% chance dodging incoming spell");
	}
}

