public class SkillTab extends wadv
{
	wadv main = new wadv();
	private double ska;
	private double skb;
	private double skc;
	private double skd;
	private double ske;
	private double skf;
	SkillTab(double s1, double s2, double s3, double s4, double s5, double s6)
	{
		ska =s1;
		skb =s2;
		skc = s3;
		skd =s4;
		ske =s5;
		skf = s6;
	}
	void print()
	{
		if (main.callClassType==2)
		{
			System.out.println("Skills tab all skills heavily rely on special attack points.");
			System.out.println("percentage data is not a reliable information on active skills.");
			System.out.println("");
		}
		System.out.println("		-----------------Active Skills-----------------");
		System.out.println("2. Stab:	"+(int)ska+"/"+"5"+"   deals additional damage(+"+(int)(2+ska*2)+"). def piercing.mana:"+(47+((int)ska*3)));
		System.out.println("");
		System.out.println("3. DoubleStrike:"+(int)skb+"/"+"10"+"  hits enemy twice with "+(int)(70+skb*2)+"% damage.		mana:"+(100+((int)skb*4)));
		System.out.println("");
		System.out.println("4. Heal:	"+(int)skc+"/"+"10"+"  heals your HP by "+(int)(20+skc*2)+"%.			mana:"+(140+((int)skc*6)));
		System.out.println("");
		System.out.println("5. Blue Aura:	"+(int)skd+"/"+"10"+"  Increases Player's stats by "+(int)(8+skd*0.5)+"%.		mana:"+(220+((int)skd*5)));
		System.out.println("");
		System.out.println("6. PsionicStorm:"+(int)ske+"/"+"10"+"  critical damage and poisons enemy.("+(int)(150+ske*4)+"%)  mana:"+(260+((int)ske*4)));
		System.out.println("");
		System.out.println("7. SpeedBoost:	"+(int)skf+"/"+"10"+"  Increase attack speed by "+(int)(120+skf*3)+"% for 5 turns.mana:"+(int)(200+skf*10));
		System.out.println("");
		System.out.println("8. Annihilation: Deals massive damage. ");
		System.out.println("");
		System.out.println("		-----------------Passive Skills-----------------");
		System.out.println("11. Critical blow:	"+(int)pskCritical+"/10 "+(int)(pskCritical*2+130)+"% damage at "+(int)pskCritical*4+"% chance.");
		System.out.println("12. Mana Restoration:	"+(int)pskManaRest+"/10 Restores "+(int)(pskManaRest*0.8+0.2)+"% mana each turn.");
		System.out.println("13. Dodge:		"+(int)pskDodge+"/10 "+(int)(pskDodge*3.5)+"% chance dodging opponent attack.");
		System.out.println("");
	}
}

