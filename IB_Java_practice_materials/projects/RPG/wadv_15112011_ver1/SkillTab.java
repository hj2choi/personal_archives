public class SkillTab
{
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
		System.out.println("");
		System.out.println("2. Stab:	"+(int)ska+"/"+"8"+" penetrates enemy with certainty.	(+"+(int)(5+ska*10)+"), mana:"+(47+((int)ska*3)));
		System.out.println("");
		System.out.println("3. MagicClaw:	"+(int)skb+"/"+"12"+"  strikes enemy twice.		("+(int)(69+skb*1)+"%), mana:"+(89+((int)skb*1)));
		System.out.println("");
		System.out.println("4. Heal:	"+(int)skc+"/"+"15"+"  heals yourself.			("+(int)(19+skc*0.8)+"%), mana:"+(177+((int)skc*3)));
		System.out.println("");
		System.out.println("5. Debuff:	"+(int)skd+"/"+"15"+"  decreases enemy's stats.		("+(int)(8.5+skd*0.5)+"%), mana:"+150);
		System.out.println("");
		System.out.println("6. PsionicStorm:"+(int)ske+"/"+"20"+" critical damage and poisons enemy.("+(int)(300+ske*10)+"%) mana:"+(246+((int)ske*4)));
		System.out.println("");
		System.out.println("7. Allin:	"+(int)skf+"/"+"5"+"  costs half hp and all mp. Deals massive damage ("+(int)(280+ske*20)+"%)");
		System.out.println("");
		System.out.println("8. Attribute:	increase all stat points by 1.");
	}
}

