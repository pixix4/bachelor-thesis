fun matchStrings(string1: String, string2: String): Boolean {

    val a = stringToSet(string1)
    val b = stringToSet(string2)

    val longer: Set<String>
    val shorter: Set<String>
    if (a.size >= b.size) {
        longer = a
        shorter = b
    } else {
        longer = b
        shorter = a
    }

    if (
            shorter.isEmpty() ||
            shorter.size.toDouble() / longer.size.toDouble() < 
                    WORD_LENGTH_THRESHOLD
    ) {
        return false
    }

    return shorter.all { short ->
        val s = short.take(
                max(
                        MIN_WORD_LENGTH,
                        short.length - WORD_ENDING_LENGTH
                )
        )

        longer.any { long ->
            val l = long.take(
                    max(
                            MIN_WORD_LENGTH,
                            long.length - WORD_ENDING_LENGTH
                    )
            )

            long.startsWith(s) ||
                    short.startsWith(l) &&
                    abs(l.length - s.length) <= WORD_ENDING_LENGTH
        }
    }
}

private fun stringToSet(str: String): Set<String> = str
        .replace(SPLIT_CAMEL_CASE_REGEX, "$1 $2")
        .split(" ")
        .map { it.toLowerCase().trim() }
        .filter { it.length >= MIN_WORD_LENGTH }
        .toSet()

private val SPLIT_CAMEL_CASE_REGEX = "([a-z])([A-Z])".toRegex()
private const val WORD_LENGTH_THRESHOLD = 0.6
private const val MIN_WORD_LENGTH = 3
private const val WORD_ENDING_LENGTH = 2
