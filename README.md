
# Лабораторна робота 1 (11 - 12)

## Тема: Інтерфейси як верхній рівень абстакції.

### Завдання

Створити програму яка міститиме набір класів що представлятимуть музичні інструменти
та набір класів що представлятимуть музикантів котрі вміють грати на конкретних інструментах,
певних видах музичних інструментів.
(Наприклад якщо музикант грає на фортепіано то він зіграє і на синтезаторі т.д.)  
Забезпечити програму зрозумілим та зручним меню.

------

### Інтерфейси музичних інструментів

Програма містить 5 інтерфейсів що представлять музичні інструменти:

- **InstrumentInterface**.  
Верхній інтерфейс від якого наслідують інші інтерфейси 

- **Percussion_Instrument**.  
Представляє собою ударні інструменти.

- **Stringed_Instrument**.  
Представляє собою струнні інструменти.

- **Wind_Instrument**.  
Представляє собою духовні інструменти.

- **Electronic_Instrument**.  
Представляє собою електронні інструменти.

---

### Класи музичних інтерфейсів

Програма містить 3 класи для представлення музичних інструментів: 

- **Instrument**  
Реалізує *InstrumentInterface*.  
При виклику методу Play() виводить в консоль:
  > Unknown instrument is playing

- **Guitar**  
Реалізує *Stringed_Instrument*.  
При виклику методу Play() виводить в консоль:
  > Guitar is playing

- **ElectroGuitar**
Реалізує *Stringed_Instrument* та *Electronic_Instrument*.  
При виклику методу Play() виводить в консоль:
  > ElectroGuitar is playing

----

### Клас музик
Клас **Musician** репрезентує музикантів, що вміють грати на різних видах музичних інструментів.

Інструменти на яких вміє грати музикант зберігаються в публічному незмінному списку `listOfPlayableInstruments`.  
Список задається під час створення об'єкта в конструкторі або під час виклику метода ***SetNewInstrumans***.  

```Java
public Musician (Collection<? extends InstrumentInterface> list){
		this.listOfPlayableInstruments = List.copyOf(list);
	}
public void SetNewInstrumans (Collection<? extends InstrumentInterface> list) {
		this.listOfPlayableInstruments = List.copyOf(list);
	}
```
#### Гра на музичних інструментах

Метод ***PlayInstrument*** надає можливість гри на музичних інструментах. Метод приймає як параметер `instrumentToPlay` - об'якт класу, що наслідує від ***InstrumentInterface***.  

Далі метод працює за таким алгоритмом:  
1. Дістає всі інтерфейси параметра `instrumentToPlay` і зберігає в змінній `instrumentToPlayInterfaces` .  
	```Java
	Class<?>[] instrumentToPlayInterfaces = instrumentToPlay.getClass().getInterfaces();
	```
2. Проходиться по кожному елементу списка `listOfPlayableInstruments` .  
Для кожного елемента дістає інтерфейси в змінну  `instrumentInterfaces` .  
	```Java
	for (InstrumentInterface instrument : listOfPlayableInstruments) {
			Class<?>[] instrumentInterfaces = instrument.getClass().getInterfaces();
			
			//частина коду відсутня
	```
3. Далі алгоритм порівнює всі інтерфейси змінних  `instrumentToPlayInterfaces` та `instrumentInterfaces` .  
Якщо є співпадіння, то викликається метод ***Play*** параметра `instrumentToPlay` .
	```Java
	for (Class<?> class1 : instrumentToPlayInterfaces) {
				for (Class<?> class2 : instrumentInterfaces) {

					if (class1 == class2) {
						instrumentToPlay.Play();
						return;
    // частина коду відсутня
	```
4. Якщо алгоритм не знайшов інтерфейсів, що співпадають, то програма виводить повідомлення, що музикант не вміє грати на інструменті, який був заданий, як параметер.
	```Java
	System.out.println(name + " cant play " + instrumentToPlay.getClass() + " instrument");
	```

#### Повний код методу PlayInstrument
```Java
public void PlayInstrument(InstrumentInterface instrumentToPlay) {
		Class<?>[] instrumentToPlayInterfaces = instrumentToPlay.getClass().getInterfaces();
		for (InstrumentInterface instrument : listOfPlayableInstruments) {
			Class<?>[] instrumentInterfaces = instrument.getClass().getInterfaces();

			for (Class<?> class1 : instrumentToPlayInterfaces) {
				for (Class<?> class2 : instrumentInterfaces) {

					if (class1 == class2) {
						instrumentToPlay.Play();
						return;
					}
				}

			}
		}
		System.out.println(name + " cant play " + instrumentToPlay.getClass() + " instrument");
	}
```
---

## Клас Main
### Внутрішній клас InstrumentConst
Клас використовується для забезпечення виконання логіки програми та взаємодії користувача із програмою.
 
Клас мітить:
1. Статичні змінні.
	```Java
	final static public Instrument instrument = new Instrument();
	final static public Guitar guitar = new Guitar();
	final static public ElectroGuitar electroGuitar = new ElectroGuitar();

	private static boolean breakPoint = false;
	```
2. 	Метод **CreateListOfInstrumance** .  
Використовується для створення списку унікальних об'єктів, які імплементують ***InstrumentInterface*** . 

	Поки користувач не введе `4` програма буде давати запит на ввід і виводи  наступне повідомлення.
	>Choose which instruments Musician can play  
	>1.Instrument  
	>2.Guitar  
	>3.ElectroGuitar  
	>4.end  
	---
	```Java 
	static List<InstrumentInterface> CreateListOfInstrumance() {
			LinkedHashSet<InstrumentInterface> listToReturn = new LinkedHashSet<InstrumentInterface>();

			breakPoint = false;
			while (true) {
				System.out.println("Choose which instruments " + musician.name + " can play\n" + "1.Instrument\n"
						+ "2.Guitar\n" + "3.ElectroGuitar\n" + "4.end");
				try {
					int input = scanner.nextInt();
					listToReturn.add(GetInstrument(input));

				} catch (Exception e) {
					System.out.println(e);
					if (!breakPoint)
						scanner.next();
				}
				if (breakPoint)
					break;
			}
			return List.copyOf(listToReturn);
	}
	```

3. Метод ***InstrumentInterface*** , що використовує `swithc`, для того щоб повернути значення, або встановити завершення виконання частини коду.
	```java
	public static InstrumentInterface GetInstrument(int id) throws Exception {

			switch (id) {
			case 1: {
				return instrument;
			}
			case 2: {
				return guitar;
			}
			case 3: {
				return electroGuitar;
			}
			case 4: {
				breakPoint = true;
				throw new Exception("Exit");
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + id);
			}
	}
	```
---
### Метод main
Реалізує логіку взаємодії з користувачем. 
- Спершу програма просить користувача задати ім'я музиканту.  
- Далі іде створення списку інструментів на яких зможе грати музикант.  
- Далі, поки користувач на натисне `4`, програма буде давати запит на ввід та виводити наступне повідомлення : 
	>What instruments Musician should play  
	>1.Instrument   
	>2.Guitar  
	>3.ElectroGuitar  
	>4.end

```java
public static void main(String[] args) {

		System.out.println("Choose musician name");
		String name = scanner.nextLine().strip();

		musician.name = name == "" ? musician.name : name;
		musician.SetNewInstrumans(InstrumentConst.CreateListOfInstrumance());
		InstrumentConst.breakPoint = false;
		while (true) {
			System.out.println("What instruments " + musician.name + " should play\n" + "1.Instrument\n" + "2.Guitar\n"
					+ "3.ElectroGuitar\n" + "4.end");
			try {
				int n = scanner.nextInt();
				musician.PlayInstrument(InstrumentConst.GetInstrument(n));

			} catch (Exception e) {
				System.out.println(e);
				if (!InstrumentConst.breakPoint)
					scanner.next();
			}

			if (InstrumentConst.breakPoint)
				break;
		}
		scanner.close();
	}
```
----