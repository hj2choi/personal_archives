public class CharacterStat
{
	public String hebar(double gg, char dtt)
	{
		String hebar = "";
		gg = (int)(gg*20);
		for (int e = 0;e<gg ;e++ )
		{
			hebar = hebar+dtt;
		}
		if (gg<0)
			gg=0;
		for (int kl = 0;kl<(20-gg) ;kl++ )
		{
			hebar = hebar+"_";
		}
		return hebar;
	}
		{
		wadv main = new wadv();
		main.str = main.sa+main.wep_stat[3];				//final stat
		main.dex = main.sb+main.wep_stat[4];
		main.intel = main.sc+main.wep_stat[5];
		main.sta = main.sd+main.wep_stat[6];
		main.hp = (long)((((double)main.lv*(double)main.lv)/5.3)+(main.lv*1)+main.sta*3.3+ (main.sta*(main.sta/2)) + 45 + main.wep_stat[7]);					//hp.mp 
		main.mp = (long)((main.lv*main.lv/8)/4+main.lv*2+(Math.sqrt(main.intel)*main.intel)*1.2 + main.intel*4 + main.wep_stat[8] + 10);
		if (main.intel>14)
			main.mp=main.mp+(main.intel-14)*3+((main.intel-14)*(main.intel-14)/4);
		main.Gamehp = main.hp;			// hp.mp in battle
		main.Gamemp = main.mp;
		main.atkmax = (long)((main.str*(main.str/7))*(main.wep_stat[0]/20)+(main.str*(main.wep_stat[0]/20))+Math.sqrt(main.dex*(main.dex/30))+main.sta/5-main.str+2);// + (dex*(dex/10)-1) + (sta/4) + ((wep_stat[0])/4)-str);		//battle statistics
		main.atkmin = (long)((main.atkmax/4.5)+(main.dex*(main.dex-1)/7)*(main.wep_stat[0]/20)+1);
		if (main.atkmin>(main.atkmax*0.95))
			main.atkmin=(main.atkmax*0.95);
		main.spatk = (long)((main.intel*main.intel/8)*(main.wep_stat[1]/20)+(main.dex*main.dex/16));
		main.def = (long)(main.str/12+main.sta/10+(main.sta*Math.sqrt(Math.sqrt(main.sta))/40)+main.wep_stat[2]);
		main.acc = 100-main.difficulty+main.dex+main.wep_stat[9];				// use with random(range = 0~100) (hit is determined when (num is 1~acc))
		main.speed = (20+main.dex/1.1);
		if (main.callClassType==2 || main.callClassType==3)
		{
			if (main.callClassType==2)
			{
				System.out.println("			L.V: "+main.lv);
				System.out.println("			EXP: "+main.exp+"/"+main.expmax);
				String hpb = hebar((double)main.exp/(double)main.expmax, main.bardot);
				System.out.println("			"+hpb);
				System.out.println("			Cash: $"+main.money+"			Crystal: "+main.chrystal);
				System.out.println("");
			}
			System.out.println("Health Point:	"+(int)main.hp);
			System.out.println("Mana Point:	"+(int)main.mp);
			System.out.println("Damage:		"+(int)main.atkmin+"~"+(int)main.atkmax);
			System.out.println("Special Attack:	"+(int)main.spatk);
			System.out.println("Defence:	"+(int)main.def);
			System.out.println("Accuracy:	"+(int)main.acc);
			System.out.println("Speed:		"+(int)main.speed);
			System.out.println("");
			if (main.callClassType==3){System.out.print("[1]	");}System.out.print("Strength:	"+(int)main.str+"	");	 if (main.str-main.sa!=0){System.out.println("(+"+(int)(main.str-main.sa)+")");}else{System.out.println("");}
			if (main.callClassType==3){System.out.print("[2]	");}System.out.print("Agility:	"+(int)main.dex+"	");	 if (main.dex-main.sb!=0){System.out.println("(+"+(int)(main.dex-main.sb)+")");}else{System.out.println("");}
			if (main.callClassType==3){System.out.print("[3]	");}System.out.print("Intelligence:	"+(int)main.intel+"	");	 if (main.intel-main.sc!=0){System.out.println("(+"+(int)(main.intel-main.sc)+")");}else{System.out.println("");}
			if (main.callClassType==3){System.out.print("[4]	");}System.out.print("Stamina:	"+(int)main.sta+"	");	 if (main.sta-main.sd!=0){System.out.println("(+"+(int)(main.sta-main.sd)+")");}else{System.out.println("");}
			if (main.callClassType==2)
			{
				System.out.println("");
				System.out.println("		Weapon:	"+main.wep);
				System.out.println("		Armor:	"+main.arm);
				System.out.println("		Head:   "+main.head);
				System.out.println("");
			}
		}

	}
}

