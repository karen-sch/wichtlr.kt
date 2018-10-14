package ren.iamka.wichtlr

// Put your real santas here
private val santas = mapOf( "fakeemail1@fakeprovider.com" to "Bob",
                            "fakeemail2@fakeprovider.com" to "Alice",
                            "fakeemail3@fakeprovider.com" to "Carol",
                            "fakeemail4@fakeprovider.com" to "Dave",
                            "fakeemail5@fakeprovider.com" to "Eve",
                            "fakeemail6@fakeprovider.com" to "Frank",
                            "fakeemail7@fakeprovider.com" to "Ted")

fun main(args: Array<String>) {
    val matches = matchSecretSantas(santas.keys)

    for ((santaMail, partnerMail) in matches){
        val partnerName = santas.getValue(partnerMail)
        sendEmail(santaMail, partnerName)
    }
}

internal fun matchSecretSantas(santas: Set<String>): List<Pair<String, String>> {
   var matches: List<Pair<String, String>>
    do {
        val partners = santas.shuffled()
        matches = santas.mapIndexed { i, santa -> santa to partners[i] }
    } while (matches.any { it.first == it.second })
    return matches
}