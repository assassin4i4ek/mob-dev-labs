// Task 1 
fun isEven(arg: Int?): String {
  	// there can be several options, I decided to go with this one
  	if (arg == null)
    	return ""
    
  	return if (arg % 2 == 0) "even" else "odd"
}
// task 1 bonus 1: extension property
fun Int?.getEvennessType(): String = isEven(this) // getEvenness() is not allowed due to "evenness property" declared later
// task 1 bonus 2: extension property
val Int?.evenness: String get() = isEven(this)

// Tests 1
fun testIsEven() {
    val testcases = mapOf(
    	0 to "even",
        1 to "odd",
        2 to "even",
        3 to "odd",
        null to "",
        -1 to "odd",
        -2 to "even"
    )
    
    for ((arg, expected) in testcases) {
        assert(isEven(arg) == expected)
        assert(arg.getEvennessType() == expected)
        assert(arg.evenness == expected)
    }
    
    println("Test testIsEven passed")
}

// Task 2
fun Int.isPrime(): Boolean {
    // prime numbers start with "2"
    if (this <= 1)
    	return false
    
    for (i in 2..this / 2) {
        // it makes sense to check
        if (this % i == 0)
        	return false
    }
    
	return true    
}

fun sumSimple(): Int {
    // can be changed further (possibly extract to fun args)
    val start = 1
    val limit = 5

    val numbers = mutableListOf<Int>()
	    
    var i = start
    while (numbers.size < limit) {
        if (i.isPrime())
        	numbers.add(i)
        i += 1
    }
    
    return numbers.sum()
}


// Test 2
fun testSumSimple() {
	assert(!1.isPrime())
	assert(2.isPrime())
	assert(3.isPrime())
	assert(!4.isPrime())
	assert(5.isPrime())
	assert(!6.isPrime())
   	
    assert(sumSimple() == 2 + 3 + 5 + 7 + 11)

    println("Test testSumSimple passed")
}

// Task 3
fun sumOnes(n: Int = 5): Int {
    var sum = 0
    
    for (i in 1..n) {
        // Implicit way:
        // sum = sum * 10 + i
        
        // Explicit way:
        val increment = "1".repeat(i).toInt()
        sum += increment
    }
    
    return sum;
}


// Test 3
fun testSumOnes() {
    assert(sumOnes(1) == 1)
    assert(sumOnes(2) == 12)
    assert(sumOnes(3) == 123)
    assert(sumOnes(4) == 1234)
    assert(sumOnes(5) == 12345)
    assert(sumOnes(0) == 0)
    assert(sumOnes(-1) == 0)

    println("Test testSumOnes passed")
}

fun main() {
	testIsEven()
	testSumSimple()
    testSumOnes()
}