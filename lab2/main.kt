object NumUtils {
    fun minmax(a: Double, b: Double, c: Double): Double {
        return maxOf(a, minOf(b, c))
    }
    
    fun format(value: Double, n: Int): String {
        return "%.${n}f".format(value)
    }
}


abstract class Employee(
    var name: String,
    var surname: String,
    var baseSalary: Int,
    var experience: Int
) {
    open val countedSalary: Double
    	get() {
            val baseSalary = baseSalary.toDouble()
            return when {
                experience > 5 -> baseSalary * 1.2 + 500.0
                experience > 2 -> baseSalary + 200.0
                else -> baseSalary
            }
        }
}

class Developer(
    name: String,
    surname: String,
    baseSalary: Int,
    experience: Int
) : Employee(name, surname, baseSalary, experience) {
}

class Designer(
    name: String,
    surname: String,
    baseSalary: Int,
    experience: Int,
    effCoef: Double
) : Employee(name, surname, baseSalary, experience) {
    var effCoef = checkCoef(effCoef)
        set(value) {
            field = checkCoef(value)
        }
    
    private fun checkCoef(coef: Double): Double {
        return NumUtils.minmax(0.0, coef, 1.0)
    }
    
    override val countedSalary: Double
    	get() = this.effCoef * super.countedSalary
}

class Manager(
    name: String,
    surname: String,
    baseSalary: Int,
    experience: Int,
    val team: MutableList<Employee> = mutableListOf()
) : Employee(name, surname, baseSalary, experience) {
    override val countedSalary: Double
    	get() {
            val teamSizeRaise = when {
                team.size > 10 -> 300.0
                team.size > 5 -> 200.0
                else -> 0.0
            }
            val teamCompositionCoef = team
                .count { employee ->
                    employee is Developer 
                }.let { numDevs ->
                    if (numDevs > team.size / 2 ) 1.1 else 1.0
                }
            
            return (super.countedSalary + teamSizeRaise) * teamCompositionCoef
        }
}

class Department(
    val managers: MutableList<Manager> = mutableListOf()
) {
    fun giveSalary() {
        // For sure it's an overkill, but I decided it's a fun challange
        managers
            .flatMap { mgr ->
				sequence {
                	yield(mgr)
                	yieldAll(mgr.team)
            	}
        	}
            .map { emp ->
                emp.run {
                    "$name $surname отримав(ла) ${NumUtils.format(countedSalary, 2)} шекєлей"
                }
            }
            .forEach(::println)
        
    }
}

fun main() {
	val dev1 = Developer("Brad", "Pitt", 2000, 7)
    val dev2 = Developer("Christoph", "Waltz", 1900, 5)
    val dev3 = Developer("Michael", "Fassbender", 1000, 3)
    
    val des1 = Designer("Diane", "Kruger", 1000, 1, 0.5)
    
    val mgr1 = Manager("Quentin", "Tarantino", 1750, 9, mutableListOf(
    	dev1, dev2, dev3, des1
    ))
    
    val dev4 = Developer("Christian", "Bale", 1700, 5)
    
    val des2 = Designer("Heath", "Ledger", 1800, 5, 1.0)
    val des3 = Designer("Aaron", "Ekhart", 1300, 3, 0.7)
    val des4 = Designer("Michael", "Cane", 1700, 20, 0.3)
    
    val mgr2 = Manager("Robert", "Zemeckis", 1400, 8, mutableListOf(
    	dev4, des2, des3, des4
    ))
        
    val dep = Department(mutableListOf(mgr1, mgr2))
    
    dep.giveSalary()
}