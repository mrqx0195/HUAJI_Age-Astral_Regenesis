package net.mrqx.huajiage.data.patchouli.category;

import com.reimnop.pgen.builder.PGenCategoryBuilder;
import com.reimnop.pgen.builder.PGenLangBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import net.mrqx.huajiage.HuaJiAgeMod;
import net.mrqx.huajiage.registy.HuaJiItems;

public class CategoryCosmicChronicles {
    public static final String ID = "cosmic_chronicles";

    public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(0);
    }

    public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
        builder.withSortnum(0);
    }

    public static void enUsEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "kichiku_warriors_the_shining_stars", "Kichiku Warriors: The Shining Stars", ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("1,145,141,919,810,000,000 years ago, with the $(7)$(l)alliance♂$() of $(l:https://en.wikipedia.org/wiki/Billy_Herrington)$(7)$(l)「THE KING」Billy$() and $(l:https://zh.wikipedia.org/wiki/范·达克霍姆)$(0)$(l)「Dark Knight」VAN-sama$(), the formerly warring Shinihogahara united overnight against external forces, FA♂ging a massive cam♂paign of conquest across the galaxies. This period became known as the $(0)$(l)\"Dark Tide\"$().", pageBuilder -> {
                        })
                        .addTextPage("In this time of crisis, numerous heroes stepped forward, forming the \"$(6)$(l)Kichiku All-Star Alliance$()\" to jointly resist the dark tyranny. During the protracted war, the names of many Kichiku stars resounded throughout the cosmos: $(l:https://zh.wikipedia.org/wiki/卢本伟)$(6)$(l)Lord.Lu$(), $(l:https://zh.wikipedia.org/wiki/王境泽)$(c)$(l)The King of \"So Fragrant!\"$(), $(l:https://zh.moegirl.org.cn/不要停下来啊！)$(4)$(l)The Flower of Hope · Orga$(), $(l:https://zh.moegirl.org.cn/波澜哥)$(b)$(l)The Sword Saint of Waves$(), $(l:https://zh.wikipedia.org/wiki/程书林)$(8)$(l)Chun · Jian · Laji · Cide$()... and many more Kichiku Senpais, whose brilliance shone brightly in that war.", pageBuilder -> {
                        })
                        .addTextPage("After arduous battles, $(6)$(l)Lord.Lu$() used his $(6)$(l)「Lu's Divine Palm」$() to seal away Billy and VAN. But at that very moment, the mastermind lurking in the shadows seized the opportunity to usurp the supreme power of the two emperors. This was $(0)$(l)Dark Priest Snake$(). He transformed Shinihogahara into his \"Heaven,\" gaining the power to accelerate time infinitely. With this power, he effortlessly decimated most of the $(6)$(l)Kichiku All-Star Alliance$().", pageBuilder -> {
                        })
                        .addTextPage("However, to his utter astonishment, just as the All-Stars faced annihilation, the dying $(4)$(l)The Flower of Hope · Orga$() stood up and detonated his ultimate $(b)$(l)Secret Art · $(4)$(l)「Don't Stop!」$(), forcibly pulling back all the fallen Kichiku stars. The final confrontation unfolded at the edge of the universe.", pageBuilder -> {
                        })
                        .addTextPage("In the end, the $(6)$(l)Kichiku All-Star Alliance$(), under the ultimate accelerated time, perished together with $(0)$(l)Dark Priest Snake$(). The universe collapsed into a singularity along with the time acceleration. After another Big Bang, the $(7)$(l)Radiance of the Infinite Stars$(), symbolizing the brilliantly shining souls of the Kichiku warriors, burst forth, forming the present-day HUAJI Universe.", pageBuilder -> {
                        })
        );
        builder.addEntry(ID + "/" + "archaeological_documents_from_the_previous_era", "Archaeological Documents from the Previous Era", ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(1)
                        .addTextPage("The previous era was destroyed in the cosmic collapse. However, fragments of ancient information were preserved within HUAJI particles. Using consciousness-particle super-resonance reconstruction technology, scientists have vividly recreated precious images left from that time.", pageBuilder -> {
                        })
                        .addImagePage(pageBuilder -> pageBuilder.addImage(HuaJiAgeMod.prefix("textures/book/story/cheer_up.png"))
                                .withText("Allied \"Oligei Fleet\" Commander Gimeng Jan Jone delivering a pre-battle speech at the Stardock"))
                        .addTextPage("“\"Whatever difficulties we encounter, let's not be afraid! Face it with a smile! The best way to eliminate fear is to face fear! Perseverance is victory! Go for it, Oligei!\"$()$(br)— Gimeng Jan Jone$(br2)Following this battle, nearly all 2,000 warships of the Oligei Fleet were annihilated.", pageBuilder -> {
                        })
        );
    }

    public static void zhCnEntryBuilder(PGenLangBuilder builder) {
        builder.addEntry(ID + "/" + "kichiku_warriors_the_shining_stars", "《鬼畜斗士·闪耀的星辰》", ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withPriority(true)
                        .addTextPage("114514亿亿亿年前，伴随着$(l:https://zh.wikipedia.org/wiki/比利·海灵顿)$(7)$(l)「THE KING」比利王$()与$(l:https://zh.wikipedia.org/wiki/范·达克霍姆)$(0)$(l)「Dark Knight」VAN様$()的联♂合，原先处于内战中的新日暮里一夜之间一致对外，向各大星系FA♂起了声势浩大的征♂服，史称“$(0)$(l)黑暗浪潮$()”。", pageBuilder -> {
                        })
                        .addTextPage("在危难之际，诸多豪杰挺身而出，组建了“$(6)$(l)鬼畜全明星联盟$()”，共同抵御黑暗的邪恶统治。在旷日持久的战争中，有许多鬼畜明星的名号响彻了全宇宙：$(l:https://zh.moegirl.org.cn/卢本伟)$(6)$(l)Lord.Lu$()、$(l:https://zh.moegirl.org.cn/我ooo就算饿死不会吃你一点东西)$(c)$(l)真香之王$()、$(l:https://zh.moegirl.org.cn/不要停下来啊！)$(4)$(l)希望之花·奥尔加$()、$(l:https://mzh.moegirl.org.cn/波澜哥)$(b)$(l)波澜剑圣$()、$(l:https://zh.moegirl.org.cn/面筋哥)$(8)$(l)淳·简·拉基·茨德$()……以及更多的鬼畜先辈，在那场战争中闪耀着光芒。", pageBuilder -> {
                        })
                        .addTextPage("经历艰难的战斗之后，$(6)$(l)Lord.Lu$()以$(6)$(l)「卢来神掌」$()封印了比利王与VAN，但就在此时，在幕后策划一切的阴谋家却借机篡取了两位帝王的无上神力，此人正是$(0)$(l)Dark蛇神父$()。他将新日暮里转化为了他的“天堂”，并以此得到了无尽加速时间的力量。借助这股力量，他轻松地消灭了$(6)$(l)鬼畜全明星联盟$()的绝大部分力量。", pageBuilder -> {
                        })
                        .addTextPage("然而，令他万万没想到的是，就在全明星即将覆灭之际，濒死的$(4)$(l)希望之花·奥尔加$()站了起来，引爆了其最终的$(b)$(l)奥义·$(4)$(l)「不要停下来啊」$()，强行拉回了所有牺牲的鬼畜明星，众人在宇宙尽头展开了最终的对决。", pageBuilder -> {
                        })
                        .addTextPage("最终，$(6)$(l)鬼畜全明星联盟$()在终极的加速时间下与$(0)$(l)Dark蛇神父$()同归于尽，宇宙也随着时间加速而坍缩为奇点，再一次宇宙大爆炸之后，象征着鬼畜斗士无比闪耀的灵魂的$(7)$(l)无尽星辰之辉$()喷涌而出，化作了如今的滑稽宇宙。", pageBuilder -> {
                        })
        );
        builder.addEntry(ID + "/" + "archaeological_documents_from_the_previous_era", "前纪元考古文献资料", ForgeRegistries.ITEMS.getKey(Items.WRITABLE_BOOK),
                HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(1)
                        .addTextPage("前纪元毁于宇宙坍缩，然而，一些古老的信息却在滑稽粒子的内部保留了下来，科学家利用意识-粒子超谐振脑补技术，生动的重现了当时留下的珍贵影像。", pageBuilder -> {
                        })
                        .addImagePage(pageBuilder -> pageBuilder.addImage(HuaJiAgeMod.prefix("textures/book/story/cheer_up.png"))
                                .withText("盟军奥利给舰队指挥官吉蒙·詹·琼恩在星港发表战前演讲"))
                        .addTextPage("“我们遇到什么困难，也不要怕，微笑着面对它！消除恐惧的最好办法就是面对恐惧！坚持，才是胜利！加油，奥利给！”$()$(br)——吉蒙·詹·琼恩$(br2)此战后，奥利给舰队近2000艘战舰几乎全灭。", pageBuilder -> {
                        })
        );
    }

    public static class CategoryKichikuAllStarCodex {
        public static final String ID = "kichiku_all_star_codex";

        public static void enUsCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(0)
                    .withParent(CategoryCosmicChronicles.ID);
        }

        public static void zhCnCategoryBuilder(PGenCategoryBuilder builder) {
            builder.withSortnum(0)
                    .withParent(CategoryCosmicChronicles.ID);
        }

        public static void enUsEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "codex_lord_lu", "Lord.Lu", HuaJiItems.FIFTY_FIFTY_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("50_50_helmet"))
                            .addTextPage("$(l:https://zh.wikipedia.org/wiki/卢本伟)Lord.Lu$(), also known as the Lu-lai Buddha, Fifty-Fifty, or the Gambling Ace, is one of the pinnacles of combat strength among the Kichiku All-Stars. It is said that no matter how powerful the enemy, he can, at worst, achieve a fifty-fifty stalemate.", pageBuilder -> {
                            })
                            .addTextPage("Class: Scientist$()$(br)" +
                                    "A unique class variant of the Archer class, possessing extremely powerful ranged and melee capabilities. However, as a trade-off, he can only use modern weaponry.$(br2)" +
                                    "$(l)Strength: B$()$(br)" +
                                    "$(l)Endurance: A$()$(br)" +
                                    "$(l)Agility: A$()$(br)" +
                                    "$(l)Mana: A$()$(br)" +
                                    "$(l)Luck: B$()$(br)" +
                                    "$(l)Noble Phantasm: A+$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class Skills:$()$(br)" +
                                    "$(l)Hacks (EX)$(): Abilities akin to using cheats in a game, including multiple sub-skills such as Aimbot (automatically aims when attempting to target), Wallhacks (can see living entities through any obstacle), and Headshot (hits on any part of the body deal damage equivalent to a vital strike).", pageBuilder -> {
                            })
                            .addTextPage("$(l)Personal Skills:$()$(br)" +
                                    "$(l)Seventeen Shots in Two Seconds (B++)$(): When using any weapon defined as a \"gun,\" fixes its rate of fire to seventeen shots in two seconds, regardless of whether its original fire rate was higher or lower.$(br)" +
                                    "$(l)Gambling Ace (A-)$(): The manifestation of Lord.Lu's melee combat ability, allowing him to materialize a deck of playing cards for mid-to-close range combat. As it is a skill, it is not restricted by his Class. However, there is a chance he might manifest the \"Fated Losing Deck,\" leading to defeat in battle.$(br)" +
                                    "$(l)Light Wings Deployment (A+)$(): Deploys eight angelic light wings (each wing formed from a condensed firearm), enabling the use of the derivative skill \"Overload Mode\" to bombard targets with high frequency.$(br)" +
                                    "$(l)Summon Friend from Nothingness (B)$(): Can summon a friend. The strength of the summoned individual varies and is typically used to draw enemy fire.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Noble Phantasm:$()$(br)" +
                                    "$(l)SKS Marksman Rifle - Lu Custom (A++)$(): A modified version based on the SKS rifle. Beyond having its rate of fire increased to full-auto seventeen shots in two seconds, it can simultaneously materialize eight rifles to fire together. Its base power is not high, but it can temporarily merge with other firearms and fire with their power, typically used in conjunction with \"Light Wings Deployment.\" Its activation triggers the battle cry \"LBWNB\".$(br)" +
                                    "$(l)The Fifty-Fifty Helmet (A+)$(): A helmet that, when worn, defends against omnidirectional attacks and unconditionally reflects half of the damage received back onto the attacker.", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_the_king_of_so_fragrant", "The King of \"So Fragrant!\"", HuaJiItems.ULTIMATE_EGG_RICE.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("ultimate_egg_rice"))
                            .addTextPage("Full title: $(l:https://zh.wikipedia.org/wiki/王境泽)The King of \"So Fragrant!\" · Jingze$(). An elder of the Kichiku All-Stars. Although he has retired from the forefront due to gradually being outmatched by newer-generation stars, he should not be underestimated.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class: Archer$()$(br)" +
                                    "Despite being an Archer, the King of \"So Fragrant!\" possesses considerable close-quarters combat ability. Under certain circumstances, he can even overcome class affinity and defeat Lancers of equal strength.$(br2)" +
                                    "$(l)Strength A$()$(br)" +
                                    "$(l)Endurance B$()$(br)" +
                                    "$(l)Agility A$()$(br)" +
                                    "$(l)Mana C$()$(br)" +
                                    "$(l)Luck B$()$(br)" +
                                    "$(l)Noble Phantasm A++$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class Skills:$()$(br)" +
                                    "$(l)Magic Resistance (E)$(): The King's Magic Resistance is extremely weak, making it difficult for him to defeat opponents who rely primarily on magic, which is one reason he fell behind the newer-generation stars.$(br)" +
                                    "$(l)Independent Action (A++)$(): As Kichiku stars have no Master restrictions, the utility of this skill is hard to manifest. However, for the King, this ability allows him to exhibit exceptional prowess when operating without teammates.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Personal Skills:$()$(br)" +
                                    "$(l)Firearm Projection (E~A++)$(): Can project and use any weapon defined as a \"firearm.\" This is the King's primary means of combat.$()$(br)" +
                                    "$(l)Gun-Kata (A)$(): When using certain firearms, he can employ Gun-Kata for close-quarters combat, often gaining significant advantage.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Noble Phantasm:$()$(br)" +
                                    "$(l)Treasure of \"So Fragrant!\" (A++)$(): Summons a vast number of firearms to bombard the target. These are typically matchlock muskets, but when going all out, he can also summon modern weaponry.$(br)" +
                                    "$(l)\"So Fragrant!\" Fried Rice (A++)$(): A plate of egg fried rice that, regardless of his current state as long as he is alive, instantly restores him to his prime condition. The usage time is very short, but it can still be interrupted.", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_the_flower_of_hope_orga", "The Flower of Hope · Orga", HuaJiItems.ORGA_REQUIEM.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("orga_requiem"))
                            .addTextPage("As the leader of Tekkadan, $(l:https://zh.moegirl.org.cn/不要停下来啊！)$(4)$(l)Orga$() does not typically stand on the front lines of battle—however, when the situation becomes critical, he is one of the very few Kichiku stars capable of turning the tide.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class: Ruler$()$(br)" +
                                    "Since the Kichiku Universe has no Holy Grail War, the Ruler class has lost its primary function. However, there still seems to be potential to utilize the abilities附带 (fùdài: attached/included with) this class...$(br2)" +
                                    "$(l)Strength D$()$(br)" +
                                    "$(l)Endurance D$()$(br)" +
                                    "$(l)Agility E$()$(br)" +
                                    "$(l)Mana A$()$(br)" +
                                    "$(l)Luck C$()$(br)" +
                                    "$(l)Noble Phantasm EX$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class Skills:$()$(br)" +
                                    "$(l)Magic Resistance (EX)$(): The supreme ability to completely ignore magecraft. But precisely because it completely ignores magecraft, there exists the risk of being defeated by purely physical means.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Personal Skills:$()$(br)" +
                                    "$(l)Flower of Hope → Secret Art · 「Don't Stop!」 (B → EX)$(): An ability to briefly sustain life when on the verge of death. Originally a skill meant to work briefly on himself, it evolved for unknown reasons during the final battle, pulling back all the Kichiku All-Stars and sustaining their lives until the end of the universe. Although the reason for its evolution is unknown, based on its manifestation, it ranks among the strongest even within EX-rank abilities.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Noble Phantasm:$()$(br)" +
                                    "$(l)Black Premium Sedan (B)$(): A car that can be summoned at any time, carrying three assassins armed with assault rifles. Possesses a trait making it difficult to detect; it will not be noticed by the target until it initiates its attack.", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_sword_saint_of_waves", "Sword Saint of Waves", HuaJiItems.WAVE_CRYSTAL.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("huaji_sword"))
                            .addTextPage("Once a musician with dreams, he failed to seize his opportunity and was eliminated in a talent show. With all hope lost, he abandoned music for the sword, embarking on the path of a swordsman.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class: Saber$()$(br)" +
                                    "A well-balanced swordsman in all aspects. However, due to the unique Noble Phantasm he possesses, he can hold his own against enemies on par with the King of \"So Fragrant\".$(br2)" +
                                    "$(l)Strength B$()$(br)" +
                                    "$(l)Endurance B$()$(br)" +
                                    "$(l)Agility B$()$(br)" +
                                    "$(l)Mana B$()$(br)" +
                                    "$(l)Luck C$()$(br)" +
                                    "$(l)Noble Phantasm E~EX$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class Skills:$()$(br)" +
                                    "$(l)Magic Resistance (A)$(): Conventional magecraft finds it difficult to affect the Sword Saint of Waves—according to legend, his sword can cut through anything, including magical energy.$(br)" +
                                    "$(l)Riding (C)$(): The Sword Saint of Waves rarely uses mounts, but when necessary, he can handle them as well as an ordinary person.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Personal Skills:$()$(br)" +
                                    "$(l)Sword Barrel Replication (E~A+)$(): Can replicate and use most melee weapons. Although the replicated weapons are one rank lower than the originals, his Noble Phantasm compensates for this quite well.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Noble Phantasm:$()$(br)" +
                                    "$(l)Unlimited Blade Works (EX)$(): A vast space storing countless weapons. Compared to $(l:https://en.wikipedia.org/wiki/Shirou_Emiya)the Reality Marble \"Unlimited Blade Works\" from a certain world$(), the Sword Saint of Waves' version is a veritable miniature world. It can drag others inside, or overlay the space around himself. Weapons replicated by his Personal Skill \"Sword Barrel Replication\" can perform on par with the originals within this Unlimited Blade Works, and in extreme cases, can even restore divine artifacts.", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_van_sama", "VAN-sama", HuaJiItems.NEUTRON_STAR_FRAGMENT.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("infinite_universe_star"))
                            .addTextPage("The man known as the 「Dark Knight」, the leader of the dark side of Shinihogahara, the Man of Black♂ness and Blazing Flame, the Conqueror of All Things.$()$(br)" +
                                    "$(o)My name is Van. I'm an artist, I'm a performance artist. I'm hired for people to fulfill their fantasies, their deep♂dark♂fantasies.$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class: Van♂quisher$()$(br)" +
                                    "A unique class belonging to VAN-sama, a variant of the Berserker class. Compared to the original, the Van♂quisher class skills lack the side effect of losing rationality and possess a remarkably balanced parameter set.$(br2)" +
                                    "$(l)Strength A$()$(br)" +
                                    "$(l)Endurance A$()$(br)" +
                                    "$(l)Agility A$()$(br)" +
                                    "$(l)Mana A$()$(br)" +
                                    "$(l)Luck A$()$(br)" +
                                    "$(l)Noble Phantasm A$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)Class Skills:$()$(br)" +
                                    "$(l)Demonic♂Metamorphosis (A+)$(): Condenses magical energy within oneself, greatly enhancing one's capabilities, while simultaneously activating the 「Dark♂Demon Sword」. Requires raising the right middle finger to the sky and shouting \"FA♂Q!\" at the moment of activation.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Personal Skills:$()$(br)" +
                                    "$(l)Blazing♂Male Soul (A)$(): Rubs his own body to accumulate heat, which can be released as flames during melee combat. If sufficient flame is accumulated, it can even cause an explosion.$()$(br)" +
                                    "$(l)Dark♂Prison (A+)$(): Creates a bounded field, restraining the target within it. When the target attempts to leave, they are pushed back and take damage. With continuous attacks, the target can be pinned against the field's boundary for maximum damage.$()$(br)" +
                                    "$(l)Dark♂Fairy (A)$(): The manifestation of the authority of Shinihogahara's dark side. Allows him to absorb energy through physical contact, restoring health and mana, which also grants VAN-sama considerable close-quarters combat ability.", pageBuilder -> {
                            })
                            .addTextPage("$(l)Noble Phantasm:$()$(br)" +
                                    "$(l)Dark♂Demon Sword (A)$(): A greatsword that can only be used during Demonic♂Metamorphosis. Swinging it leaves afterimages that cause consecutive hits. Its substance is VAN-sama's own magical energy; therefore, even if it is deliberately detonated using 「Broken Phantasm」, a new one can be regenerated through Demonic♂Metamorphosis.", pageBuilder -> {
                            })
            );
        }

        public static void zhCnEntryBuilder(PGenLangBuilder builder) {
            builder.addEntry(ID + "/" + "codex_lord_lu", "Lord.Lu", HuaJiItems.FIFTY_FIFTY_HELMET.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("50_50_helmet"))
                            .addTextPage("$(l:https://zh.moegirl.org.cn/卢本伟)Lord.Lu$()，又名卢来佛祖、五五开、赌怪，鬼畜全明星的战力巅峰之一，据说无论面对多强的敌人，最差都能打出五五开。", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶：Scientist / 科学家$()$(br)" +
                                    "Lord.Lu的专属职阶，是Archer职阶的变种，兼具极强远程与近战能力。但作为代价，其只能使用现代武器。$(br2)" +
                                    "$(l)筋力  B$()$(br)" +
                                    "$(l)耐久  A$()$(br)" +
                                    "$(l)敏捷  A$()$(br)" +
                                    "$(l)魔力  A$()$(br)" +
                                    "$(l)幸运  B$()$(br)" +
                                    "$(l)宝具  A+$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶技能：$()$(br)" +
                                    "$(l)外挂（EX）$()：如同在游戏中开挂一般的能力，包括自瞄（尝试瞄准目标时自动瞄准）、透视（能够透过任何障碍物窥视生命体）、爆头（命中身体任意位置均能造成与命中要害等同的伤害）等多项子能力。", pageBuilder -> {
                            })
                            .addTextPage("$(l)固有技能：$()$(br)" +
                                    "$(l)两秒十七发（B++）$()：使用任意符合“枪械”定义的武器时，将其射速固定为两秒十七发，无论其原射速是大于还是小于该值。$(br)" +
                                    "$(l)赌怪（A-）$()：Lord.Lu近战能力的体现，能够幻化出一副扑克牌进行中近距离战斗。因为是技能所以不受职阶限制，但有可能会幻化出“命运的必败牌组”导致其战斗失利。$(br)" +
                                    "$(l)光翼展开（A+）$()：展开如同天使一般的八片光翼（每片光翼都是由一把枪凝聚而成），随后可以使用衍生技能「过载模式」，对目标进行高频轰炸。$(br)" +
                                    "$(l)无中生友（B）$()：能够召唤一位朋友，被召唤者的实力参差不齐，通常被用于吸引火力。", pageBuilder -> {
                            })
                            .addTextPage("$(l)宝具：$()$(br)" +
                                    "$(l)SKS射手步枪-Lu改（A++）$()：基于SKS射手步枪的改造型。除射速提升至全自动两秒十七发外，能够同时幻化出八把枪同时射击。基础威力不高，但能够与其他枪支短暂融合、并以那把枪的威力射击，通常与「光翼展开」一同使用。射击时会触发战吼“LBWNB”。$(br)" +
                                    "$(l)五五开头盔（A+）$()：戴在头上就能防御全方位攻击的头盔，同时能够无条件将受到的一半伤害返还给攻击者。", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_the_king_of_so_fragrant", "真香之王", HuaJiItems.ULTIMATE_EGG_RICE.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("ultimate_egg_rice"))
                            .addTextPage("全称$(l:https://zh.moegirl.org.cn/我ooo就算饿死不会吃你一点东西)真香之王·境泽$()，鬼畜全明星元老之一，虽然因实力逐渐不敌新生代明星而隐退，但仍不可小觑。", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶：Archer / 弓兵$()$(br)" +
                                    "尽管是弓兵，但真香之王具有相当强的近身作战能力，在一些情况下甚至能顶着职阶克制打赢同等实力的Lancer。$(br2)" +
                                    "$(l)力量 A$()$(br)" +
                                    "$(l)耐久 B$()$(br)" +
                                    "$(l)敏捷 A$()$(br)" +
                                    "$(l)魔力 C$()$(br)" +
                                    "$(l)幸运 B$()$(br)" +
                                    "$(l)宝具 A++$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶技能：$()$(br)" +
                                    "$(l)对魔力（E）$()：真香之王的对魔力极为薄弱，面对以魔法为主要作战手段的对手时难以取胜，这也是其不敌新生代明星的原因之一。$(br)" +
                                    "$(l)单独行动（A++）$()：由于鬼畜明星没有Master的限制，单独行动这一能力的用处难以体现。但对于真香之王而言，该能力使他在没有队友的情况下能够发挥出超常的实力。", pageBuilder -> {
                            })
                            .addTextPage("$(l)固有技能：$()$(br)" +
                                    "$(l)枪械投影（E~A++）$()：能够投影任意符合“枪械”定义的武器并使用，是真香之王的主要作战手段。$(br)" +
                                    "$(l)枪斗术（A）$()：使用部分枪械时，能够以枪斗术进行近身作战，通常能够取得不小的优势。", pageBuilder -> {
                            })
                            .addTextPage("$(l)宝具：$()$(br)" +
                                    "$(l)真香财宝（A++）$()：召唤大量的枪械对目标进行轰击，一般为火绳枪，但在全力以赴的情况下也能召唤现代枪械。$(br)" +
                                    "$(l)真香炒饭（A++）$()：无论状态如何，只要还活着，就能直接恢复到全盛形态的蛋炒饭。使用时间很短，但仍能被打断使用。", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_the_flower_of_hope_orga", "希望之花·奥尔加", HuaJiItems.ORGA_REQUIEM.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("orga_requiem"))
                            .addTextPage("作为铁华团团长，$(l:https://zh.moegirl.org.cn/不要停下来啊！)$(4)$(l)奥尔加$()通常不会站在战场一线——然而，当战况陷入危机时，他是极少数能够逆转大局的鬼畜明星。", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶：Ruler / 裁定者$()$(br)" +
                                    "由于鬼畜宇宙没有圣杯战争，裁定者职阶失去了其最重要的功能。不过，对于这个职阶所附带的能力，似乎仍有可以开发的地方……$(br2)" +
                                    "$(l)力量 D$()$(br)" +
                                    "$(l)耐久 D$()$(br)" +
                                    "$(l)敏捷 E$()$(br)" +
                                    "$(l)魔力 A$()$(br)" +
                                    "$(l)幸运 C$()$(br)" +
                                    "$(l)宝具 EX$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶技能：$()$(br)" +
                                    "$(l)对魔力（EX）$()：能够完全无视魔法的至高能力。但也正因为完全无视魔法，反而存在被纯物理击败的风险。", pageBuilder -> {
                            })
                            .addTextPage("$(l)固有技能：$()$(br)" +
                                    "$(l)希望之花 → 奥义·「不要停下来啊」（B → EX）$()：在濒死时短暂维持生命的能力。本应只是对自己短暂起效的能力，却在最终之战中因未知原因得到了进化，拉回了所有的鬼畜全明星并将众人的生命维续至宇宙的终点。尽管进化的原因未知，但就表现而言，在EX级能力中也是最强的那一部分。", pageBuilder -> {
                            })
                            .addTextPage("$(l)宝具：$()$(br)" +
                                    "$(l)黑色高级轿车（B）$()：可以随时召唤的一辆车，其上有三名持有突击步枪的杀手。具有难以被发现的特性，在发起攻击前不会被目标注意到。", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_sword_saint_of_waves", "波澜剑圣", HuaJiItems.WAVE_CRYSTAL.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("huaji_sword"))
                            .addTextPage("曾经是一位有梦想的音乐人，却因没有把握住机会而在选秀中落选。万籁俱灰之下，他弃音执剑，走上了剑客之路。", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶：Saber / 剑士$()$(br)" +
                                    "各方面都相当均衡的剑士，但由于其所持有的特殊宝具，他在面对与真香之王同级的敌人时也能不落下风。$(br2)" +
                                    "$(l)力量 B$()$(br)" +
                                    "$(l)耐久 B$()$(br)" +
                                    "$(l)敏捷 B$()$(br)" +
                                    "$(l)魔力 B$()$(br)" +
                                    "$(l)幸运 C$()$(br)" +
                                    "$(l)宝具 E~EX$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶技能：$()$(br)" +
                                    "$(l)对魔力（A）$()：常规的魔法难以影响到波澜剑圣——就传说而言，他的剑可以斩开一切，自然也包括魔力。$(br)" +
                                    "$(l)骑乘（C）$()：波澜剑圣很少使用载具，但在需要时也能够像普通人一样驾驭载具。", pageBuilder -> {
                            })
                            .addTextPage("$(l)固有技能：$()$(br)" +
                                    "$(l)刀剑投影（E~A+）$()：能够投影大多数近战武器并使用。尽管投影出的武器会比原件弱一个等级，但其宝具能够很好地补足这一点。", pageBuilder -> {
                            })
                            .addTextPage("$(l)宝具：$()$(br)" +
                                    "$(l)无限剑制（EX）$()：一个存放着无数武器的巨大空间。与$(l:https://zh.moegirl.org.cn/%E6%97%A0%E9%99%90%E5%89%91%E5%88%B6)某世界作为固有结界的无限剑制$()相比，波澜剑圣的无限剑制是一个真正的小世界，能够将其他人拖入其中，也可以将其覆盖自身周围的空间。固有技能-刀剑投影投影出的武器在无限剑制中能够具有与原件相当的性能，在极限状态下甚至能够复原神器。", pageBuilder -> {
                            })
            );

            builder.addEntry(ID + "/" + "codex_van_sama", "VAN様", HuaJiItems.NEUTRON_STAR_FRAGMENT.getId(),
                    HuaJiAgeMod.prefix(ID), entryBuilder -> entryBuilder.withSortnum(0).withAdvancement(HuaJiAgeMod.prefix("infinite_universe_star"))
                            .addTextPage("被称作「Dark Knight」的男♂人，新日暮里黑暗之侧的领袖，黑♂暗与炽焱之男，世间万物的征服者。$()$(br)" +
                                    "$(o)My name is Van. I'm an artist, I'm a performance artist. I'm hired for people to fulfill their fantasies, their deep♂dark♂fantasies.$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶：Van♂quisher / 征♂服者$()$(br)" +
                                    "VAN様的专属职阶，是Berserker职阶的变种。与原先相比，Van♂quisher的职阶技能没有失去理性的副作用，并有着相当均衡的面板。$(br2)" +
                                    "$(l)力量 A$()$(br)" +
                                    "$(l)耐久 A$()$(br)" +
                                    "$(l)敏捷 A$()$(br)" +
                                    "$(l)魔力 A$()$(br)" +
                                    "$(l)幸运 A$()$(br)" +
                                    "$(l)宝具 A$()", pageBuilder -> {
                            })
                            .addTextPage("$(l)职阶技能：$()$(br)" +
                                    "$(l)魔♂男化（A+）$()：凝聚魔力于自身，大幅强化自身，同时激活「黑暗♂魔剑」。释放瞬间需要朝天竖右手中指并大喊“FA♂Q!”。", pageBuilder -> {
                            })
                            .addTextPage("$(l)固有技能：$()$(br)" +
                                    "$(l)烈焰♂男魂（A）$()：摩擦自己的身体积蓄热量，肉搏时能够将热量释放出来形成烈焰。如果积蓄了足够的火焰，甚至可以引发爆炸。$()$(br)" +
                                    "$(l)暗黑♂牢笼（A+）$()：制造一块领域，将目标束缚在领域之中，目标尝试离开时会被推回并受到伤害。在连续攻击的情况下，可以将目标按在领域边界连打以使伤害最大化。$()$(br)" +
                                    "$(l)暗♂之妖精（A）$()：新日暮里黑暗面的权能具现。能够通过身体接触汲取能量，恢复生命力及魔力，这也使得VAN様有着不俗的近身作战能力。", pageBuilder -> {
                            })
                            .addTextPage("$(l)宝具：$()$(br)" +
                                    "$(l)黑暗♂魔剑（A+）$()：只能在魔♂男化期间使用的大剑，挥舞时会留下残影造成连续打击。其实质是VAN様自身的魔力，因此即便使用「幻想崩坏」将其主动引爆，也可以通过魔♂男化重新生成一把。", pageBuilder -> {
                            })
            );
        }
    }
}
