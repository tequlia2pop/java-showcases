package com.gmail.tequlia2pop.pets.creator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.gmail.tequlia2pop.pets.model.Cat;
import com.gmail.tequlia2pop.pets.model.Cymric;
import com.gmail.tequlia2pop.pets.model.Dog;
import com.gmail.tequlia2pop.pets.model.EgyptianMau;
import com.gmail.tequlia2pop.pets.model.Hamster;
import com.gmail.tequlia2pop.pets.model.Manx;
import com.gmail.tequlia2pop.pets.model.Mouse;
import com.gmail.tequlia2pop.pets.model.Mutt;
import com.gmail.tequlia2pop.pets.model.Pet;
import com.gmail.tequlia2pop.pets.model.Pug;
import com.gmail.tequlia2pop.pets.model.Rat;
import com.gmail.tequlia2pop.pets.model.Rodent;

/**
 * 使用类字面常量来创建宠物。
 * 
 * 生成 Pet 继承结构中的对象存在着一个问题，即每次向该继承结构添加新的 Pet 类型时，
 * 必须将其添加为 LiteralPetCreator.java 中的项。
 * 如果在系统中已经存在了继承结构的常规的基础，然后在其上要添加更多的类，那么就有可能会出现问题。
 * 最佳的做法是，将这个列表置于一个位于中心的、位置明显的地方，
 * 而我们感兴趣的继承结构的基类可能就是这个最佳位置。
 * 
 * @author tequlia2pop
 */
public class LiteralPetCreator extends PetCreator {
	
	/**
	 * 所有的 Pet 类型。
	 */
	public static final List<Class<? extends Pet>> allTypes = Collections
			.unmodifiableList(Arrays.asList(Pet.class, Dog.class, Cat.class, Rodent.class,
					Mutt.class, Pug.class, EgyptianMau.class, Manx.class, Cymric.class, Rat.class,
					Mouse.class, Hamster.class));

	/**
	 * allTypes 的一部分，包含了确切的宠物类型，用于随机 Pet 生成。
	 */
	private static final List<Class<? extends Pet>> types = allTypes
			.subList(allTypes.indexOf(Mutt.class), allTypes.size());

	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}

	public static void main(String[] args) {
		// System.out.println(allTypes.size());
		// System.out.println(types.size());
		System.out.println(types);
	}
}
